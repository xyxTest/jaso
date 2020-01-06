package com.yaj.jaso.business.userproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yaj.jaso.business.project.entity.po.ProjectPO;
import com.yaj.common.base.pojo.MulSelect;
import com.yaj.common.base.service.ServiceMain;
import com.yaj.jaso.business.jasouser.entity.po.JasoUserPO;
import com.yaj.jaso.business.userproject.entity.po.UserProjectPO;
import com.yaj.jaso.business.userproject.mapper.UserProjectMapper;

/*
 * @Description: 
 * @date: 2019-07-22
 */
@Service
public class UserProjectService extends ServiceImpl<UserProjectMapper, UserProjectPO> {

    @Resource
    UserProjectMapper userProjectMapper;
    @Autowired
    ServiceMain serviceMain;
    public boolean addUserProject(JasoUserPO user,List<ProjectPO> projectList){
    	List<UserProjectPO> addList = new ArrayList<UserProjectPO>();
    	for(ProjectPO ppo:projectList){
    		UserProjectPO uppo = new UserProjectPO();
    		uppo.setCompanyId(user.getCompanyId());
    		uppo.setProjectId(ppo.getProjectId());
    		uppo.setJasoUserId(user.getJasoUserId());
    	}
    	return insertBatch(addList);
    }
    public List<UserProjectPO> getProjectList(JasoUserPO user){
    	Wrapper<UserProjectPO> po = new EntityWrapper<UserProjectPO>();
    	po.eq("jaso_user_id", user.getJasoUserId());
    	po.eq("company_id", user.getCompanyId());
    	return selectList(po);
    }
    public boolean deleteUserProject(JasoUserPO user){
    	Wrapper<UserProjectPO> po = new EntityWrapper<UserProjectPO>();
    	po.eq("jaso_user_id", user.getJasoUserId());
    	return delete(po);
    }
	public Object selectProjectList(JasoUserPO po) {
		// TODO Auto-generated method stub
		MulSelect mul = MulSelect.newInstance("${1}.projectName", new UserProjectPO(), new ProjectPO());
		mul.where("${user_project}")
		.eq("companyId", po.getCompanyId())
		.eq("jasoUserId", po.getJasoUserId());
		return serviceMain.mulSelect(mul);
	}
}
