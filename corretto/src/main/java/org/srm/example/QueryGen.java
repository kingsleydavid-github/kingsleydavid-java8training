package org.srm.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueryGen {

	private static String four = "2 CUSTOM_FIELD_2"; 
	private static String five = "3 CUSTOM_FIELD_3"; 
	private static String six = "4 CUSTOM_FIELD_4"; 
	private static String seven = "5 CUSTOM_FIELD_5"; 
	private static String three = "1 CUSTOM_FIELD_1"; 

	public static void main(String[] args) {
		
		//BeginTasklet
		
		String prefix = "select * from abc where fields in (  :placeholder  ) where subr_id = 1203812038";
		
		//call generateCustomHeaders();
		//read the header and generate a list like this.
		String[] headers = {"duns", "tpid", "entityname", "cf1", null, "cf3", null, "cf5"};
		
		//iterate over the list and determine what fields are required
		List<String> resultSet = Arrays.asList(headers);
		System.out.println(resultSet);
		List<String> headerToAdd = new ArrayList<>();
		
		//here we are dermining wat fields are needed.
		for(int headerIndex = 0; headerIndex < resultSet.size(); headerIndex++)
		{
			if(headerIndex > 2)
			{
				String header = resultSet.get(headerIndex);
				if(header != null)
				{
					switch (headerIndex) {
					case 3:
						headerToAdd.add(three);
						break;
					case 4:
						headerToAdd.add(four);
						break;
					case 5:
						headerToAdd.add(five);
						break;
					case 6:
						headerToAdd.add(six);
						break;
					case 7:
						headerToAdd.add(seven);
						break;
					}
				}
			}
		}
		System.out.println(headerToAdd);
		
		// construct query based on required fields
		String query = prefix.replace(":placeholder", String.join(",", headerToAdd));
		
		// save this query in jobContext
		System.out.println(query);
		
		//This will be stored in JobContext during begin tasklet
		
		//#[jobContext(cstomQuery)]
	}

}
