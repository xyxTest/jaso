package com.yaj.common.exception;

public enum BusinessExceptionErrorEnum {
	//1000 系统异常
	TOKEN_EXPIRED(1001,"TOKEN过期！"),
	TOKEN_PARSE_ERROR(1002,"TOKEN解析错误！"),
	TOKEN_SECRET_ERROR(1003,"没有访问权限！"),
	ALIYUN_OSS_UPLOAD_ERROR(1004,"阿里云上传图片异常"),

	//==================v自定义异常定义 2000 开始==================//
	//wx 2000
	LOGIN_ERROR(4001,"用户名或密码错误！"),
	//
	LOGIN_SYSTEM_ERROR(4005,"该用户没有登录权限"),
	//200
	ID_CARD_MATCH_ERROR(4002,"身份证号码无法识别！"),
	//
	APP_ILLEGAL_ACTION(4004,"APP列表非法操作"),
	//
	USER_NAME_OR_TEL_REPEAT(4003,"（APP/PC）用户名或手机号码已存在"),
	//==================^自定义异常定义 ==================//
	SYSTEM_ERROR(9900,"服务器处理异常！");
    //==================登录出错========================//


	private int code;
	private String message;
	private BusinessExceptionErrorEnum(int code, String desc) {
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
