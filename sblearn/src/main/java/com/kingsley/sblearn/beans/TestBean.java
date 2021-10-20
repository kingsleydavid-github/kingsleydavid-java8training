package com.kingsley.sblearn.beans;

import org.springframework.stereotype.Component;

@Component
public class TestBean {

	public String helloTestBean() {
		return "Hello! World";
	}

	public void initmd() {
		System.out.println("This is init Method");
	}
}
