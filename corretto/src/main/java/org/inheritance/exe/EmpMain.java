package org.inheritance.exe;

import java.util.HashMap;
import java.util.Map;

public class EmpMain {

	public static void main(String[] args) {
		
		Emp emp1 = new Emp("david", 1); 
		Emp emp2 = new Emp("david", 1);
		
		System.out.println(emp1.hashCode());
		System.out.println(emp2.hashCode());
		
		Map m = new HashMap<String, Emp>(); 
		m.put("1", emp1);
		m.put("2", emp2);
		
		System.out.println(m);

	}

}
