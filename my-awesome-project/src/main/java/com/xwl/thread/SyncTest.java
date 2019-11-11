package com.xwl.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @Author: xiewanlin
 * @Date: 2019/10/10
 */
public class SyncTest {

  private Object lock = new Object();

  public void codeBlock() {
    synchronized (lock) {
      System.out.println(Thread.currentThread().getId());


      /** 定长线程池*/
      ExecutorService fiexdExecutor = Executors.newFixedThreadPool(10);
      /** 可缓存线程池*/
      ExecutorService newCacheExecutor = Executors.newCachedThreadPool();
      /** 单线程化的线程池*/
      ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
      /** 定时线程池*/
      ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(10);

    }
  }

  public synchronized void method() {
    System.out.println(Thread.currentThread().getId());
  }

  public synchronized static void statisMethod() {
    System.out.println(Thread.currentThread().getId());
  }

}
