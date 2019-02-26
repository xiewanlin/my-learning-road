package com.xwl.spring.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.xwl.spring.proxy.service.Greeting;
import com.xwl.spring.proxy.service.impl.GreetingImpl;

public class DynamicProxyGreet {
	public static void main(String[] args) {
		//不能替换为GreetingImpl，动态代理只能代理接口
		Greeting dynamicProxy = new DynamicProxy(new GreetingImpl()).getProxy();
		dynamicProxy.sayHello("zzz");
		//②
//		Greeting dynamicProxy = (Greeting)Proxy.newProxyInstance(Greeting.class.getClassLoader(), 
//				new Class[]{Greeting.class}, new DynamicProxy(new GreetingImpl()));
//		dynamicProxy.sayHello("z");
		
	}
}

class DynamicProxy implements InvocationHandler{

	private Object object;
	
	public DynamicProxy(Object object) {
		this.object = object;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getProxy(){
		return (T)Proxy.newProxyInstance(object.getClass().getClassLoader(), 
				object.getClass().getInterfaces(), this);
	}
	
	@Override
	public Object invoke(Object paramObject, Method paramMethod, Object[] paramArrayOfObject) throws Throwable {
		before();
		Object result = paramMethod.invoke(object, paramArrayOfObject);
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
