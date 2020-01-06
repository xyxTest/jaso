package com.yaj.jaso.business.projectteams.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yaj.common.threadlocal.ThreadlocalManager;
import com.yaj.jaso.business.jasouser.entity.po.JasoUserPO;
import com.yaj.jaso.business.projectteams.entity.po.ProjectTeamsPO;
import com.yaj.jaso.business.projectteams.entity.vo.ProjectTeamsVo;
import com.yaj.jaso.business.projectteams.mapper.ProjectTeamsMapper;
import com.yaj.jaso.global.PageVoUtil;

/*
 * @Description: 
 * @date: 2019-08-07
 */
@Service
public class ProjectTeamsService extends ServiceImpl<ProjectTeamsMapper, ProjectTeamsPO> {

    @Resource
    ProjectTeamsMapper projectTeamsMapper;

	public Object selectPageList(ProjectTeamsVo pt) {
		// TODO Auto-generated method stub
		JasoUserPO jasoUser = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		Page<ProjectTeamsPO> page = new Page<ProjectTeamsPO>();
		page.setCurrent(pt.getPageVo().getPageNo());
		page.setSize(pt.getPageVo().getPageSize());
		Wrapper<ProjectTeamsPO> wrapper = new EntityWrapper<ProjectTeamsPO>();
		wrapper.eq(pt.getProjectId()!=null,"project_id", pt.getProjectId());
		wrapper.eq("company_id", jasoUser.getCompanyId());
		wrapper.like(pt.getTeamsName()!=null,"teams_name", pt.getTeamsName());
		wrapper.orderDesc(Arrays.asList("create_time"));
		page=selectPage(page, wrapper);
		return PageVoUtil.setPage(page);
	}

	public Object deleteBatchByIds(List<ProjectTeamsPO> po) {
		for(int i=0;i<po.size();i++){
			po.get(i).setIfDelete(1);
		}
		// TODO Auto-generated method stub
		return updateBatchById(po);
	}

	public Object selectAllList(ProjectTeamsPO pt) {
		JasoUserPO jasoUser = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		Wrapper<ProjectTeamsPO> wrapper = new EntityWrapper<ProjectTeamsPO>();
		wrapper.eq(pt.getProjectId()!=null,"project_id", pt.getProjectId());
		wrapper.eq("company_id", jasoUser.getCompanyId());
		wrapper.like(pt.getTeamsName()!=null,"teams_name", pt.getTeamsName());
		wrapper.orderDesc(Arrays.asList("create_time"));
		return selectList(wrapper);
	}
}
