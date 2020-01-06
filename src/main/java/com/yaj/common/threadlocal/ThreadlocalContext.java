package com.yaj.common.threadlocal;


import com.yaj.common.constant.UserTypeEnum;
import com.yaj.common.threadlocal.base.BaseThreadlocalContext;

/**
* @Description: 线程变量
* @author ...
* @date 2017年8月23日
*
*/
public class ThreadlocalContext extends BaseThreadlocalContext {
	//用户id
	private Integer userId;
	//当前登录用户类型(1为公司登陆用户，2为微信登陆用户)
	private UserTypeEnum userType;
	
	private Object curUser;
	
	public Object getCurUser() {
		return curUser;
	}
	public void setCurUser(Object curUser) {
		this.curUser = curUser;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public UserTypeEnum getUserType() {
		return userType;
	}
	public void setUserType(UserTypeEnum userType) {
		this.userType = userType;
	}
}
