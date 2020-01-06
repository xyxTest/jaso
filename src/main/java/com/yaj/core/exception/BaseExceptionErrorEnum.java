package com.yaj.core.exception;

public enum BaseExceptionErrorEnum {
	//10000
	PARAMETER_VERIFICATION_ERROR(10001,"数据参数校验异常"),
	INVALID_TOKEN_ERROR(10002,"无效的Token"),
	INVALID_DATA_PERMISSION(10003,"无数据权限"),
	INIT_GENERICITY_BEAN_ERROR(10004,"泛型实例化异常"),
	SYSTEM_ERROR(10000,"服务器异常！");
	
	private int code;
	private String message;
	private BaseExceptionErrorEnum(int code, String desc) {
		this.setCode(code);
		this.setMessage(desc);
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
}
