package org.wip;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Wip {

	public static void main(String[] args) {

		
		String in ="AABBCCDSS";
		char[] a = in.toCharArray();
		Map count = new HashMap<String, Integer>();
		for(char c : a)
		{
			String key = Character.toString(c);
			if(count.containsKey(key)) {
				Integer val = (Integer) count.get(key);
				val++;
				count.put(key, val);
			}
			else {
				count.put(key, 1);
			}
		}
		count.entrySet().stream().forEach(i -> {
			Entry<String, Integer> item = (Entry<String, Integer>) i;
			System.out.print(item.getValue() + "" + item.getKey() );
		});
	}

}
