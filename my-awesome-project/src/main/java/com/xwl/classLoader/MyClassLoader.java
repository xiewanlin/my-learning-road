package com.xwl.classLoader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

/**
 * @Author: xiewanlin
 * @Date: 2020/1/13
 */
public class MyClassLoader extends ClassLoader {

  private String path="c:\\";
  private final String fileType = ".class";

  // 类加载器名字
  private String name = null;

  public MyClassLoader(String name){
    super();
    this.name = name;
  }

  public MyClassLoader(ClassLoader parent,String name){
    super(parent);
    this.name = name;
  }

  // 调用getClassLoader()时返回此方法，如果不重载，则显示MyClassLoader的引用地址
  public String toString(){
    return this.name;
  }

  // 设置文件加载路径
  public void setPath(String path){
    this.path = path;
  }

  // 重写文件加载路径
  protected Class findClass(String name) throws ClassNotFoundException{
    byte[] data = loadClassData(name);
    return defineClass(name,data,0,data.length);
  }

  // 将.class文件读入内存中，并且以字节数形式返回
  private byte[] loadClassData(String name) throws ClassNotFoundException{
    FileInputStream fis = null;
    ByteArrayOutputStream baos = null;
    byte[] data = null;
    try{
      // 读取文件内容
      name = name.replaceAll("\\.","\\\\");
      System.out.println("加载文件名："+name);
      // 将文件读取到数据流中
      fis = new FileInputStream(path+name+fileType);
      baos = new ByteArrayOutputStream();
      int ch = 0;
      while ((ch = fis.read()) != -1){
        baos.write(ch);
      }
      data = baos.toByteArray();
    }catch (Exception e){
      throw new ClassNotFoundException("Class is not found:"+name,e);
    }finally {
      // 关闭数据流
      try {
        fis.close();
        baos.close();
      }catch (Exception e){
        e.printStackTrace();
      }
    }
    return data;
  }

  public static void main(String[] args) throws Exception {
//    MyClassLoader loader = new MyClassLoader("loader");
//    // 获取MyClassLoader加载器
//    System.out.println("MyClassLoader 加载器：" + MyClassLoader.class.getClassLoader());
//    // 设置加载类查找文件路径
//    loader.setPath("c:\\");
//    Object test = loader.loadClass("com.xwl.netty.NettyClient").newInstance();
//    System.out.println(test.getClass());

    System.out.println(System.getProperty("sun.boot.class.path").replaceAll(";",";\n"));
//    System.out.println(System.getProperty("java.ext.dirs").replaceAll(";",";\n"));
//    System.out.println(System.getProperty("java.class.path").replaceAll(";",";\n"));
  }


}