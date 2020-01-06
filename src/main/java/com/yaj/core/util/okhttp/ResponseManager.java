package com.yaj.core.util.okhttp;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;


public class ResponseManager {
    /**
    * @Title: parseObject
    * @Description: 将json字符串转化为对象
    * @param @param jsonStr
    * @param @param clazz
    * @param @return    参数
    * @return T    返回类型
    * @throws
    * @author Peach
    * @date 2017年4月17日
    */
    public  static  <T> T parseObject(String jsonStr, Class clazz){
        T t=null;
        try {
            t = (T) JSON.parseObject(jsonStr, clazz);
        }catch (Exception e){
        	e.printStackTrace();
        }
        return t;
    }
    
    /**
    * @Title: parseArray
    * @Description: 把json 转化成类对象列表
    * @param @param json
    * @param @param t
    * @param @return    参数
    * @return List<T>    返回类型
    * @throws
    * @author Peach
    * @date 2017年4月17日
    */
    public static <T> List<T> parseArray(String json, Class<T> t) {
        try {
            if (json != null && !"".equals(json.trim())) {
                List<T> res = JSONArray.parseArray(json.trim(), t);
                return res;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
    * @Title: toJSON
    * @Description: 将对象转化为JSON字符串
    * @param @param obj
    * @param @return    参数
    * @return String    返回类型
    * @throws
    * @author Peach
    * @date 2017年4月17日
    */
    public static String toJSON(Object obj) {
        try {
            return JSONObject.toJSONString(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}

