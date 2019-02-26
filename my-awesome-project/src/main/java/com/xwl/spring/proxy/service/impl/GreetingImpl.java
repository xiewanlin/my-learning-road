package com.xwl.spring.proxy.service.impl;

import com.xwl.spring.proxy.service.Greeting;
/**
* @author xiewanlin
* @date 2019年2月26日
*/
public class GreetingImpl implements Greeting {
	@Override
	public void sayHello(String name) {
		System.out.println("Hello," + name);
	}
}
