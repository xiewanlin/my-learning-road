package com.xwl.DesignPattern.CreationType;

/**
 * @Author: xiewanlin
 * @Date: 2019/11/11
 */
public class SingletonPattern {

  public static void main(String[] args) {
    SingletonHungry hungry = SingletonHungry.getInstance();
    System.out.println(hungry.getClass());

    SingletonLazy lazy = SingletonLazy.getInstance();
    System.out.println(lazy.getClass());

  }

}

class SingletonHungry {
  private static SingletonHungry instance = new SingletonHungry();
  private SingletonHungry(){};
  public static SingletonHungry getInstance() {
    return instance;
  }
}

class SingletonLazy {
  private volatile static SingletonLazy instance=null;//延迟加载
  private SingletonLazy(){};
  public static SingletonLazy getInstance() {
    if (instance == null) {
      synchronized(instance){
        if (instance == null) {
          instance = new SingletonLazy();
        }
      }
    }
    return instance;
  }
}

class SingletonInner {
  private SingletonInner(){}

  private static class SingletonFactory {
    private static SingletonInner instance = new SingletonInner();
  }
  /** 获取实例*/
  public static SingletonInner getInstance() {
    return SingletonFactory.instance;
  }

}
