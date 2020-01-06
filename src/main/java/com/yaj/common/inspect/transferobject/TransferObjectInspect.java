package com.yaj.common.inspect.transferobject;

import com.baomidou.mybatisplus.plugins.Page;
import com.yaj.common.response.ResponseData;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class TransferObjectInspect {
    public static boolean check(Object obj){
//		if(Serializable.class.isInstance(obj)){
//			return true;
//		}
//    	if(obj==null){
//            return true;
//        }
    	if(Boolean.class.isInstance(obj)){
            return true;
        }
    	if(Integer.class.isInstance(obj)){
            return true;
        }
    	if(Page.class.isInstance(obj)){
            return true;
        }
        if(List.class.isInstance(obj)){
            return true;
        }
        if(Map.class.isInstance(obj)){
            return true;
        }
        if(ResponseData.class.isInstance(obj)){
            return true;
        }
        if(obj.getClass().getPackage()!=null){
        	if(obj.getClass().getPackage().toString().toLowerCase().indexOf("entity.vo")>0){
                return true;
            }
            if(obj.getClass().getPackage().toString().toLowerCase().indexOf("entity.po")>0){
                return true;
            }
            if(obj.getClass().getPackage().toString().toLowerCase().indexOf("entity.dto")>0){
                return true;
            }
        }
        
        return false;
    }
}
