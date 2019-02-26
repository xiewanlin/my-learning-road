package com.xwl.spring.aop;

import org.springframework.aop.framework.ProxyFactory;

import com.xwl.spring.proxy.service.Greeting;
import com.xwl.spring.proxy.service.impl.GreetingImpl;

/**
* @author xiewanlin
* @date 2019年2月26日
*/
public class AdviceClient {
	public static void main(String[] args) {
		//代理工厂
		ProxyFactory proxyFactory = new ProxyFactory();
		//设置目标类对象
		proxyFactory.setTarget(new GreetingImpl());
		
//		proxyFactory.addAdvice(new GreetingBeforeAdvice());//前置增强
//		proxyFactory.addAdvice(new GreetingAfterAdvice());//后置增强
		//合并前后置增强
//		proxyFactory.addAdvice(new GreetingBeforeAndAfterAdvice());
		//环绕增强
		proxyFactory.addAdvice(new GreetingAroundAdvice());
		//从代理工厂获取代理
		Greeting greeting = (Greeting)proxyFactory.getProxy();
		greeting.sayHello("xwl");
	}
}
