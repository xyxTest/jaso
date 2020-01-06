package com.yaj.common.threadlocal.base;




/**
* @Description: 线程变量
* @author Orange
* @date 2017年8月23日
*
*/
public abstract class BaseThreadlocalContext {
	//当前用户的token密文
	protected String token;
	//service层异常hashcode
	protected int BusinessExceptionHashCode = 0;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getBusinessExceptionHashCode() {
		return BusinessExceptionHashCode;
	}

	public void setBusinessExceptionHashCode(int businessExceptionHashCode) {
		BusinessExceptionHashCode = businessExceptionHashCode;
	}


			
}
