package com.xwl.spring.proxy;

import com.xwl.spring.proxy.service.Greeting;
import com.xwl.spring.proxy.service.impl.GreetingImpl;

public class StaticProxyGreet {
	public static void main(String[] args) {
		Greeting proxy = new StaticProxy(new GreetingImpl());
		proxy.sayHello("xwl");
	}
}

class StaticProxy implements Greeting{
	private GreetingImpl greetingImpl;
	public StaticProxy(GreetingImpl greetingImpl) {
		this.greetingImpl = greetingImpl;
	}
	
	@Override
	public void sayHello(String name) {
		before();
		this.greetingImpl.sayHello(name);
		after();
	}
	
	private void before(){
		System.out.println("before");
	}
	
	private void after(){
		System.out.println("after");
	}
	
}
