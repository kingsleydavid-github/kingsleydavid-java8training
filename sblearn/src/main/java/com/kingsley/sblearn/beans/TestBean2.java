package com.kingsley.sblearn.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class TestBean2 {

	public String helloTestBean() {
		return "Hello! World";
	}
	
}
