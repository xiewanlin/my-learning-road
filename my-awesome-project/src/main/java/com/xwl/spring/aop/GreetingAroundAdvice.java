package com.xwl.spring.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author xiewanlin
 * @date 2019年2月26日
 */
public class GreetingAroundAdvice implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation arg0) throws Throwable {
		before();
		Object result = arg0.proceed();
		after();
		return result;
	}

	private void before() {
		System.out.println("Before");
	}

	private void after() {
		System.out.println("After");
	}

}
