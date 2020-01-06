package com.yaj.common.response;

import com.yaj.common.threadlocal.ThreadlocalManager;
import com.yaj.core.exception.BaseBusinessException;

public class ResponseData<T> {
	private Integer code = 0;//结果代码
	private String message = "调用成功。";//结果信息
	private String token;//令牌
	private T data;//数据集
	
	public ResponseData() throws BaseBusinessException {
		this(0,"调用成功！",null);
	}

	public ResponseData(T data) throws BaseBusinessException {
		this(0,"调用成功！",data);
	}
	
	public ResponseData(Integer code, String message) throws BaseBusinessException {
		this(code,message,null);
	}
	
	public ResponseData(Integer code, String message, T data) throws BaseBusinessException {
		this.code = code;
		this.message = message;
		this.data = data;
		/*if(ThreadlocalManager.getThreadContext()!=null&& ThreadlocalManager.getThreadContext().getToken()!=null) {
			this.token = ThreadlocalManager.getThreadContext().getToken();
		}*/
	}
	

	

	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}
