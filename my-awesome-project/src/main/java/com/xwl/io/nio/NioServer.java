package com.xwl.io.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
/**
* @author xiewanlin
* @date 2019年2月26日
*/
/**
 * NIO 也称 New IO， Non-Block IO，非阻塞同步通信方式
 * 从BIO的阻塞到NIO的非阻塞，这是一大进步。功归于Buffer，Channel，Selector三个设计实现。
 * Buffer   ：  缓冲区。NIO的数据操作都是在缓冲区中进行。缓冲区实际上是一个数组。而BIO是将数据直接写入或读取到Stream对象。
 * Channel  ：  通道。NIO可以通过Channel进行数据的读，写和同时读写操作。
 * Selector ：  多路复用器。NIO编程的基础。多路复用器提供选择已经就绪状态任务的能力。
 * 客户端和服务器通过Channel连接，而这些Channel都要注册在Selector。Selector通过一个线程不停的轮询这些Channel。找出已经准备就绪的Channel执行IO操作。
 * NIO通过一个线程轮询，实现千万个客户端的请求，这就是非阻塞NIO的特点。
 */
public class NioServer implements Runnable{

	private final int BUFFER_SIZE = 1024;
	private final int PORT = 8888;
	private Selector selector;
	private ByteBuffer readBuffer = ByteBuffer.allocate(BUFFER_SIZE);
	
	public NioServer() {
		startServer();
	}
	
	private void startServer() {
		try{
			//1.开启多路复用器
			selector = Selector.open();
			//2.打开网络读写通道
			ServerSocketChannel channel = ServerSocketChannel.open();
			//3.设置阻塞通道为非阻塞模式，true阻塞，false非阻塞
			channel.configureBlocking(false);
			//4.绑定端口
			channel.socket().bind(new InetSocketAddress(PORT));
			//5.把通道注册到多路复用器上，并监听事件
			/**
			 * SelectionKey.OP_READ   : 表示关注读数据就绪事件  
			 * SelectionKey.OP_WRITE  : 表示关注写数据就绪事件  
			 * SelectionKey.OP_CONNECT: 表示关注socket channel的连接完成事件  
			 * SelectionKey.OP_ACCEPT : 表示关注server-socket channel的accept事件
			 */
			channel.register(selector, SelectionKey.OP_ACCEPT);
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while(true){
			try {
				 /** 
	              * a.select() 阻塞到至少有一个通道在你注册的事件上就绪  
	              * b.select(long timeOut) 阻塞到至少有一个通道在你注册的事件上就绪或者超时timeOut 
	              * c.selectNow() 立即返回。如果没有就绪的通道则返回0  
	              * select方法的返回值表示就绪通道的个数。 
	              */
				//1.多路复用器监听阻塞
				selector.select();
				//2.多路复用器已选择的结果集
				Iterator<SelectionKey> sIterator = selector.selectedKeys().iterator();
				//3.不停轮询
				while(sIterator.hasNext()){
					//4.获取一个选中的key
					SelectionKey key = sIterator.next();
					//5.获取后从容器中移除
					sIterator.remove();
					//6.只获取有效key
					if(!key.isValid()){
						continue;
					}
					//阻塞状态处理
					if(key.isAcceptable()){
						accept(key);
					}
					//可读状态处理
					if(key.isReadable()){
						read(key);
					}
					//可写状态
					if(key.isWritable()){
//						write(key);
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// 设置阻塞，等待Client请求。在传统IO编程中，用的是ServerSocket和Socket。在NIO中采用的ServerSocketChannel和SocketChannel
	private void accept(SelectionKey key) {
		try {
			//1.获取通道服务
			ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
			//2.执行阻塞方法
			SocketChannel socketChannel = serverSocketChannel.accept();
			//3.设置服务器通道为非阻塞模式，true为阻塞，false为非阻塞
			socketChannel.configureBlocking(false);
			//4.把通道注册到多路复用器上，并设置读取标识  
			socketChannel.register(selector, SelectionKey.OP_READ);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void read(SelectionKey selectionKey) {  
	      try {  
	          // 1.清空缓冲区数据  
	          readBuffer.clear();  
	          // 2.获取在多路复用器上注册的通道  
	          SocketChannel socketChannel = (SocketChannel) selectionKey.channel();  
	          // 3.读取数据，返回  
	          int count = socketChannel.read(readBuffer);  
	          // 4.返回内容为-1 表示没有数据  
	          if (-1 == count) {  
	              selectionKey.channel().close();  
	              selectionKey.cancel();  
	              return ;  
	          }  
	          // 5.有数据则在读取数据前进行复位操作  
	          readBuffer.flip();  
	          // 6.根据缓冲区大小创建一个相应大小的bytes数组，用来获取值  
	          byte[] bytes = new byte[readBuffer.remaining()];  
	          // 7.接收缓冲区数据  
	          readBuffer.get(bytes);
	          // 8.打印获取到的数据  
	          System.out.println("NIO Server : " + new String(bytes)); // 不能用bytes.toString() 
	          // 可以写回给客户端数据
	      } catch (Exception e) {  
	          e.printStackTrace();  
	      }  
	  }  
	
	@SuppressWarnings("unused")
	private void write(SelectionKey selectionKey){
		try {
			ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
			serverSocketChannel.register(selector, SelectionKey.OP_WRITE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Thread(new NioServer()).start();
	}
}
