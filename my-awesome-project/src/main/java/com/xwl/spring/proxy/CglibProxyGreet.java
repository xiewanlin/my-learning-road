package com.xwl.spring.proxy;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import com.xwl.spring.proxy.service.Greeting;
import com.xwl.spring.proxy.service.impl.GreetingImpl;

public class CglibProxyGreet {
	public static void main(String[] args) {
		//可以代替为GreetingImpl，即cglib可以代理没有接口的类
		Greeting cglibProxy = CglibProxy.getInstance().getProxy(GreetingImpl.class);
		cglibProxy.sayHello("xiewanlin");
	}
}

class CglibProxy implements MethodInterceptor{
	//单例模式
	private static CglibProxy instance = new CglibProxy();
	private CglibProxy() {
	}
	public static CglibProxy getInstance(){
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getProxy(Class<T> c){
		return (T)Enhancer.create(c, this);
	}
	
	@Override
	public Object intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy arg3) throws Throwable {
		before();
		Object result = arg3.invokeSuper(arg0, arg2);
		after();
		return result;
	}
	
	private void before(){
		System.out.println("before");
	}
	
	private void after(){
		System.out.println("after");
	}
	
}
