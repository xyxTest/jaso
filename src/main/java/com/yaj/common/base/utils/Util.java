package com.yaj.common.base.utils;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtilsBean; 
public class Util {
	public static List<Map<String, String>> getOptions(List<Object> list, String prop, String label) {
		List<Map<String, String>> result = new ArrayList<>();
		PropertyUtilsBean beanUtil = new PropertyUtilsBean();
		list.forEach(obj -> {
			try {
				Object propValue = beanUtil.getProperty(obj, prop);
				Object labelValue = beanUtil.getProperty(obj, label);
				
				Map<String, String> item = new HashMap<>();
				item.put("prop", propValue + "");
				item.put("label", labelValue + "");
				result.add(item);
			} catch (Exception e) {
				 e.printStackTrace();
			}  
		});
		return  result;
	}
}
