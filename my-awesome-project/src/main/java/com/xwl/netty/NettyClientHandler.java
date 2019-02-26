package com.xwl.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
/**
* @author xiewanlin
* @date 2019年2月26日
*/
public class NettyClientHandler extends ChannelHandlerAdapter {

	@Override
	public void channelActive(final ChannelHandlerContext ctx) {
		try {
			// 客户端发送数据
			byte[] bytes = new byte[1024];
			System.in.read(bytes);
			final ByteBuf out = ctx.alloc().buffer(4);
			out.writeBytes(bytes);

			final ChannelFuture f = ctx.writeAndFlush(out);
			f.addListener(new ChannelFutureListener() {
				@Override
				public void operationComplete(ChannelFuture future) {
					assert f == future;
					ctx.close();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		try {
			// 服务器返回数据
			ByteBuf in = (ByteBuf) msg;
			System.out.print(in.toString(CharsetUtil.UTF_8));
		} finally {
			ReferenceCountUtil.release(msg);
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

}
