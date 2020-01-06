package com.yaj.jaso.business.sysuser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import javax.annotation.Resource;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yaj.common.cache.CacheManager;
import com.yaj.common.cache.CacheConfig.CacheTypeEnum;
import com.yaj.common.constant.UserTypeEnum;
import com.yaj.common.exception.BusinessException;
import com.yaj.common.exception.BusinessExceptionErrorEnum;
import com.yaj.common.jwt.JwtTokenUtil;
import com.yaj.common.jwt.JwtUserInfo;
import com.yaj.common.response.ResponseData;
import com.yaj.common.threadlocal.ThreadlocalContext;
import com.yaj.common.threadlocal.ThreadlocalManager;
import com.yaj.core.util.BeanUtil;
import com.yaj.core.util.PasswordUtil;
import com.yaj.jaso.business.sysuser.entity.po.SysUserPO;
import com.yaj.jaso.business.sysuser.entity.vo.SysUserVo;
import com.yaj.jaso.business.sysuser.mapper.SysUserMapper;
import com.yaj.jaso.global.PageVoUtil;

/*
 * @Description: 
 * @date: 2019-07-18
 */
@Service
public class SysUserService extends ServiceImpl<SysUserMapper, SysUserPO> {

    @Resource
    SysUserMapper sysUserMapper;
    @Autowired
    JwtTokenUtil tokenUtil;
	public Object sysLogin(SysUserPO user) {
		ResponseData<SysUserPO> result = new ResponseData<SysUserPO>();
		if(user.getSysUserName()!=null && user.getSysUserPassword()!=null){
			Wrapper<SysUserPO> wrapper = new EntityWrapper<>();
			wrapper.eq("sys_user_name", user.getSysUserName());
			List<SysUserPO> userlist=sysUserMapper.selectList(wrapper);
			if(!userlist.isEmpty()){
				SysUserPO getUser = new SysUserPO();
				getUser=userlist.get(0);
				if(PasswordUtil.isPasswordValid(getUser.getSysUserPassword(), user.getSysUserPassword(), PasswordUtil.getSalt())){
					ThreadlocalContext context = new ThreadlocalContext(); 
					JwtUserInfo jui = new JwtUserInfo();
					String key = UUID.randomUUID().toString().replaceAll("-", "");
					jui.setToken(key);
					jui.setType(UserTypeEnum.WEB);
					String token = tokenUtil.generateToken(jui.toJsonString(), tokenUtil.getRandomKey());
					context.setToken(token);
					context.setCurUser(getUser); 
					ThreadlocalManager.setThreadContext(context);
					CacheManager.put(CacheTypeEnum.webTokenCache, key, context);
					result.setToken(token);
					result.setData(getUser);
				}else{
					throw new BusinessException(BusinessExceptionErrorEnum.LOGIN_ERROR);
				}
			}else{
				throw new BusinessException(BusinessExceptionErrorEnum.LOGIN_ERROR);
			}
		}else{
			throw new BusinessException(BusinessExceptionErrorEnum.LOGIN_ERROR);
		}		
		return result;
	}
	
	public Object selectPages(SysUserVo user) {
		Page<SysUserPO> page = new Page<SysUserPO>();
		page.setCurrent(user.getPageVo().getPageNo());
		page.setSize(user.getPageVo().getPageSize());
		SysUserPO po = new SysUserPO();
		BeanUtil.copy(user, po);
		Wrapper<SysUserPO> wrapper = new EntityWrapper<SysUserPO>();
		page=selectPage(page, wrapper);
        return PageVoUtil.setPage(page);
	}

}
