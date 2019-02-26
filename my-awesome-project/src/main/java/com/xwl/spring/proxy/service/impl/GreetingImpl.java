package com.xwl.spring.proxy.service.impl;

import com.xwl.spring.proxy.service.Greeting;

public class GreetingImpl implements Greeting {
	@Override
	public void sayHello(String name) {
		System.out.println("Hello," + name);
	}
}
