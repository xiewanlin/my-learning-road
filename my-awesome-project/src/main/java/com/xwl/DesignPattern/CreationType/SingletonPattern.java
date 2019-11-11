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
  private volatile static SingletonLazy instance;
  private SingletonLazy(){};
  public static SingletonLazy getInstance() {
    if (instance == null) {
      synchronized(SingletonLazy.class){
        if (instance == null) {
          instance = new SingletonLazy();
        }
      }
    }
    return instance;
  }
}
