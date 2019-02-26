package com.xwl.spring.aop;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

/**
* @author xiewanlin
* @date 2019年2月26日
*/
//前置增强类
public class GreetingBeforeAdvice implements MethodBeforeAdvice{

	@Override
	public void before(Method arg0, Object[] arg1, Object arg2) throws Throwable {
		System.out.println("Before");
	}
}
