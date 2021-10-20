package com.kingsley.orders.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.kingsley.orders.handler.OrdersHandlerInterceptor;

public class OrderMvcCfg implements WebMvcConfigurer {

	@Autowired
	OrdersHandlerInterceptor ordersHandlerInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		//WebMvcConfigurer.super.addInterceptors(registry);
		//registry.addInterceptor(ordersHandlerInterceptor);
	}

	
	
}
