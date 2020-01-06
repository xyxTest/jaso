package com.yaj.jaso.business.jasouser.ctrl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.yaj.jaso.business.jasouser.entity.po.JasoUserPO;
import com.yaj.jaso.business.jasouser.entity.vo.MapUserList;
import com.yaj.jaso.business.jasouser.entity.vo.UserAddVo;
import com.yaj.jaso.business.jasouser.entity.vo.UserInfo;
import com.yaj.jaso.business.jasouser.entity.vo.UserVo;
import com.yaj.jaso.business.jasouser.service.JasoUserService;
import com.yaj.jaso.business.project.entity.po.ProjectPO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(value="jasouser端用户接口")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="JasoUser")
public class JasoUserController {
	@Autowired
	JasoUserService ups;
	/*
	 * PC端用户登录，查询jaso_user表
	 **/
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="PC端用户登录接口",notes="登录")
	@RequestMapping(value="/loginPc",method = RequestMethod.POST)
	public Object pcLogin(@RequestBody JasoUserPO user){
		return ups.pcLogin(user);
	}
	/*
	 * PC端用户登录，查询user_pc表
	 **/
	@ApiOperation(value="App端用户登录接口",notes="登录")
	@RequestMapping(value="/loginApp",method = RequestMethod.POST)
	public Object appLogin(@RequestBody JasoUserPO user){
		return ups.appLogin(user);
	}
	
	@ApiOperation(value="pc端登录用户详情")
	@RequestMapping(value="/getJasoUserMessage",method = RequestMethod.GET)
	public Object getJasoUserMessage(){
		return ups.getJasoUserMsg();
	}
	/**
	 * 
	 * @param userName、password、realName   //必须
	 * @param email、tel可有可无
	 * 其他参数不需要，由程序指定，如日期，用户类型
	 * @return
	 */

	@ApiOperation(value="PC端用户添加接口",notes="添加")
	@RequestMapping(value="/add", method = RequestMethod.POST)
    public Object addUserPC(
    		@RequestBody UserAddVo user) {
        return ups.add(user);
    }
	/**
	 * 
	 *超级管理员注册接口 
	 **/
	@ApiOperation(value="PC端用户注册接口",notes="注册")
	@RequestMapping(value="/register", method = RequestMethod.POST)
    public Object registerUserPc(
    		@RequestBody JasoUserPO user) {
        return ups.insert(user);
    }
	/**
	 *用户列表查询接口 
	 **/
	@ApiOperation(value="pc用户列表获取接口",notes="查询")
	@RequestMapping(value="/select",method = RequestMethod.POST)
	public Object selectUserPc(
			@RequestBody UserVo user ){
		return ups.selectPages(user);
	}
	/**
	 *所有用户查询接口 
	 **/
	@ApiOperation(value="所有用户查询接口",notes="查询")
	@RequestMapping(value="/selectList",method = RequestMethod.POST)
	public Object selectUserList(){
		return ups.selectList();
	}
	/**
	 *用户、角色详情查询接口 
	 **/
	@ApiOperation(value="pc用户详情获取接口",notes="查询")
	@RequestMapping(value="/selectDetail",method = RequestMethod.POST)
	public Object selectDetail(
			@RequestBody JasoUserPO user ){
		return ups.selectDetail(user);
	}
	/**
	 *用户、组织架构详情查询接口 
	 **/
	@ApiOperation(value="pc用户、组织架构详情获取接口",notes="查询")
	@RequestMapping(value="/selectDepartmentDetail",method = RequestMethod.POST)
	public Object selectDepartmentDetail(
			@RequestBody JasoUserPO user ){
		return ups.selectDepartmentDetail(user);
	}
	/**
	 *用户、组织架构详情查询接口 
	 **/
	@ApiOperation(value="pc用户、组织架构结构树获取接口",notes="查询")
	@RequestMapping(value="/selectDepartmentTree",method = RequestMethod.POST)
	public Object selectDepartmentTree(){
		return ups.selectDepartmentTree();
	}
	/**
	 *用户列表删除接口 
	 * 
	 */
	@ApiOperation(value="用户删除接口")
	@RequestMapping(value="/delete",method = RequestMethod.POST)
	public Object deleteUserPc(@RequestBody List<JasoUserPO> users){
		return ups.deleteList(users);
	}
	/**
	 *通过项目id获取当前项目的所有成员 
	 */
	@ApiOperation(value="通过项目id获取当前项目的所有成员 ")
	@RequestMapping(value="/getListByProjectId",method = RequestMethod.POST)
	public Object getListByProjectId(
			@RequestBody ProjectPO po){
		return ups.getListByProjectId(po);
	}
	/**
	 *通过项目id获取当前项目的管理人员
	 */
	@ApiOperation(value="通过项目id获取当前项目的管理人员")
	@RequestMapping(value="/getUserListByProjectId",method = RequestMethod.POST)
	public Object getUserListByProjectId(
			@RequestBody MapUserList user){
		return ups.getUserListByProjectId(user);
	}
	/**
	 *通过用户id获取用户所选角色 
	 */
	@ApiOperation(value="通过用户id获取用户所选角色")
	@RequestMapping(value="/getRoleListByUserId",method = RequestMethod.POST)
	public Object getRoleListByUserId(
			@RequestBody JasoUserPO user){
		return ups.getRoleListByUserId(user);
	}
	/**
	 * 获取用户所对应的工种
	 */
	@ApiOperation(value="通过用户id获取用户所选工种")
	@RequestMapping(value="/getWorkTypeListByUserId",method = RequestMethod.POST)
	public Object getWorkTypeListByUserId(
			@RequestBody JasoUserPO user){
		return ups.getWorkTypeListByUserId(user);
	}
	/*
	* 获取手机短信验证码接口
	* 参数：mobile 手机号码;systemType 系统（0.移动端 1.电脑端）
	* */
	@ApiOperation(value="获取手机短信验证码接口")
	@RequestMapping(value="/getIdentifyingCode" , method = RequestMethod.POST)
	public Object getIdentifyingCode(
			@RequestBody UserInfo user) {
		return ups.getIdentifyingCode(user);
	}
	/**
	* 验证手机验证码接口，有效时间一分钟
	* 参数：mobile 手机号码; code 验证码
	* 
	* */
	@ApiOperation(value="验证手机验证码接口，有效时间一分钟")
	@RequestMapping(value="/getIdentifyingInfo" , method = RequestMethod.POST)
	public Object getIdentifyingInfo(
			@RequestBody UserInfo user) {
		return ups.getIdentifyingInfo(user);
	}
	/**
	 *通讯录获取，即获取所有用户的姓名、联系方式 
	 **/
	@ApiOperation(value="通讯录获取接口")
	@RequestMapping(value="/getPhoneBook" , method = RequestMethod.POST)
	public Object getPhoneBook(@RequestBody UserVo user) {
		return ups.getPhoneBook(user);
	}
}
