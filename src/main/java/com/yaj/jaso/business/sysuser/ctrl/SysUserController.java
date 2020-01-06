package com.yaj.jaso.business.sysuser.ctrl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.yaj.jaso.business.sysuser.entity.po.SysUserPO;
import com.yaj.jaso.business.sysuser.entity.vo.SysUserVo;
import com.yaj.jaso.business.sysuser.service.SysUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(value="B端用户controller",tags="用户操作接口")
@Controller
@RequestMapping(value="SysUser")
public class SysUserController{
	@Autowired
	private SysUserService sus;
	
	/**
	 * 
	 * @param userName、password、realName   //必须
	 * @param email、tel可有可无
	 * 其他参数不需要，由程序指定，如日期，用户类型
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="用户注册接口",notes="注册")
	@RequestMapping(value="/register", method = RequestMethod.POST)
    @ResponseBody
    public Object register(
    		@RequestBody SysUserPO user) {
		//EntityWrapper<User> ew = new EntityWrapper<User>(); 
        return sus.insert(user);
    }
	/**
	 * 
	 * @param userName、password、realName   //必须
	 * @param email、tel可有可无
	 * 其他参数不需要，由程序指定，如日期，用户类型
	 * @return
	 */

	@ApiOperation(value="用户登录接口",notes="注册")
	@RequestMapping(value="/login", method = RequestMethod.POST)
    @ResponseBody
    public Object login(
    		@RequestBody SysUserPO user) {
		return sus.sysLogin(user);
    }
	/**
	 * 
	 * @param userName、password、realName   //必须
	 * @param email、tel可有可无
	 * 其他参数不需要，由程序指定，如日期，用户类型
	 * @return
	 */

	@ApiOperation(value="用户列表获取接口",notes="列表获取")
	@RequestMapping(value="/getSysUserList", method = RequestMethod.GET)
	@ResponseBody
    public Object getSysUserList(@RequestBody SysUserVo user) {
		return sus.selectPages(user);
    }
}
