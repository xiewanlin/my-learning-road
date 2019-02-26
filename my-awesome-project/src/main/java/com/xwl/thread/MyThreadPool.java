package com.xwl.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyThreadPool {

	private static MyThreadPool myThreadPool = null;
	/*** 线程的最小数 */
	private static int corePoolSize = 8;

	private static ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, corePoolSize*20,
            30L, TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>());//一个任务

	private MyThreadPool() {
	}

	public static MyThreadPool getInstance() {
		if (null == myThreadPool) {
			synchronized (MyThreadPool.class){
				if(null == myThreadPool){
					System.out.println("MyThreadPool is creating!");
					executor.allowCoreThreadTimeOut(true);
					myThreadPool = new MyThreadPool();
				}
			}
		}
		return myThreadPool;
	}

	/**
	 * 执行任务
	 */
	public void exeWork(final Callable<?> task) {
		FutureTask<?> future = new FutureTask<>(task);
		executor.execute(future);
	}

	public int getActiveCount() {
		return executor.getActiveCount();
	}

	public void closeExecutor() {
		if (executor != null && executor.getActiveCount() == 0
				&& executor.getQueue().isEmpty()) {
			executor.shutdown();
		}
	}

	public static int getCorePoolSize() {
		return corePoolSize;
	}

	public static void setCorePoolSize(int corePoolSize) {
		MyThreadPool.corePoolSize = corePoolSize;
	}

}