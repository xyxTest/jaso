package com.yaj.jaso.business.projectpaper.service;

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
import com.yaj.jaso.business.projectpaper.entity.po.ProjectPaperPO;
import com.yaj.jaso.business.projectpaper.entity.vo.ProjectPaperVo;
import com.yaj.jaso.business.projectpaper.mapper.ProjectPaperMapper;
import com.yaj.jaso.global.PageVoUtil;

/*
 * @Description: 
 * @date: 2019-09-05
 */
@Service
public class ProjectPaperService extends ServiceImpl<ProjectPaperMapper, ProjectPaperPO> {

    @Resource
    ProjectPaperMapper projectPaperMapper;

	public Object add(ProjectPaperPO po) {
		// TODO Auto-generated method stub
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		po.setCompanyId(userInCache.getCompanyId());
		return insertOrUpdate(po);
	}

	public Object deleteBatchByIds(List<ProjectPaperPO> po) {
		for(int i=0;i<po.size();i++){
			po.get(i).setIfDelete(1);
		}
		// TODO Auto-generated method stub
		return updateBatchById(po);
	}

	public Object selectListByPage(ProjectPaperVo po) {
		Page<ProjectPaperPO> page = new Page<ProjectPaperPO>();
		page.setCurrent(po.getPageVo().getPageNo()); 
		page.setSize(po.getPageVo().getPageSize());
		Wrapper<ProjectPaperPO> wrapper = new EntityWrapper<>();
		wrapper.eq(po.getProjectId()!=null,"project_id", po.getProjectId());
		wrapper.like(po.getPaperName()!=null,"paper_name", po.getPaperName());
		wrapper.orderDesc(Arrays.asList("create_time"));
		page=selectPage(page, wrapper);
		return PageVoUtil.setPage(page);
	}

	public Object selectLists(ProjectPaperPO po) {
		JasoUserPO cacheUser = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		Wrapper<ProjectPaperPO> wrapper = new EntityWrapper<>();
		wrapper.eq(po.getProjectId()!=null,"project_id", po.getProjectId());
		wrapper.eq("company_id", cacheUser.getCompanyId());
		wrapper.orderDesc(Arrays.asList("create_time"));
		return selectList(wrapper);
	}

}
