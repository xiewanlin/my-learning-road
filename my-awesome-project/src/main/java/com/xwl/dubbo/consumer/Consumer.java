package com.xwl.dubbo.consumer;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.dubbo.config.spring.ReferenceBean;
import com.xwl.dubbo.api.Service;

/**
 * @author xiewanlin
 * @date 2019年3月5日
 */
public class Consumer {
	public static void main(String[] args) {
		// 测试常规服务
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("consumer.xml");

		Map<String, ReferenceBean<Service>> map = new HashMap<String, ReferenceBean<Service>>();
		ReferenceBean<Service> referenceBean = null;
		if (map.containsKey("dafyHawkEye")) {
			referenceBean = map.get("dafyHawkEye");
		} else {
			referenceBean = new ReferenceBean<Service>();
			referenceBean.setInterface(Service.class);
			referenceBean.setApplicationContext(context);
			referenceBean.setGroup("dafyHawkEye");
			try {
				referenceBean.afterPropertiesSet();
				map.put("dafyHawkEye", referenceBean);
			} catch (Exception e) {

			}
		}

		try {
			Service service = referenceBean.get();
			for (int i = 0; i < 20; i++)
				System.out.println(service.call("", ""));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
