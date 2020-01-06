package com.yaj.xyx.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
 
@SuppressWarnings("all")
public class MetaClassUtil {
 
	/**
	 * 用于将map转成对象
	 * @param map
	 * @param object
	 * @return
	 * @throws Exception 
	 */
	public static Object mapToObject(Map<String, Object> map,Object object){
		
		Field[] fields = object.getClass().getDeclaredFields();
		for(int i=0;i<fields.length;i++){
			String fieldName = fields[i].getName();
			String key = toUpperCaseFirstOne(fieldName);
			String mapValue = (map.get(key)==null?"":map.get(key).toString());
			try {
				object.getClass().getMethod("set"+key,String.class).invoke(object, mapValue);		
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return object;		
	}
	
	
	//首字母转小写
	public static String toLowerCaseFirstOne(String s){
	  if(Character.isLowerCase(s.charAt(0)))
	    return s;
	  else
	    return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
	}
 
 
	//首字母转大写
	public static String toUpperCaseFirstOne(String s){
	  if(Character.isUpperCase(s.charAt(0)))
	    return s;
	  else
	    return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
	}
}
