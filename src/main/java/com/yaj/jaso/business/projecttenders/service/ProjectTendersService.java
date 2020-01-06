package com.yaj.jaso.business.projecttenders.service;

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
import com.yaj.jaso.business.projecttenders.entity.po.ProjectTendersPO;
import com.yaj.jaso.business.projecttenders.entity.vo.ProjectTendersVo;
import com.yaj.jaso.business.projecttenders.mapper.ProjectTendersMapper;
import com.yaj.jaso.global.PageVoUtil;

/*
 * @Description: 
 * @date: 2019-08-07
 */
@Service
public class ProjectTendersService extends ServiceImpl<ProjectTendersMapper, ProjectTendersPO> {

    @Resource
    ProjectTendersMapper projectTendersMapper;

	public Object selectPageList(ProjectTendersVo po) {
		JasoUserPO user = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		Page<ProjectTendersPO> page = new Page<ProjectTendersPO>();
		page.setCurrent(po.getPageVo().getPageNo());
		page.setSize(po.getPageVo().getPageSize());
		Wrapper<ProjectTendersPO> wrapper = new EntityWrapper<>();
		wrapper.eq(po.getProjectId()!=null,"project_id", po.getProjectId());
		wrapper.eq(user.getCompanyId()!=null,"company_id", user.getCompanyId());
		wrapper.orderDesc(Arrays.asList("create_time"));
		page=selectPage(page, wrapper);
		return PageVoUtil.setPage(page);
	}
	public Object deleteBatchByIds(List<ProjectTendersPO> po) {
		for(int i=0;i<po.size();i++){
			po.get(i).setIfDelete(1);
		}
		// TODO Auto-generated method stub
		return updateBatchById(po);
	}
	public Object selectListByProjectId(ProjectTendersPO po) {
		// TODO Auto-generated method stub
		JasoUserPO user = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		Wrapper<ProjectTendersPO> wrapper = new EntityWrapper<>();
		wrapper.eq(po.getProjectId()!=null,"project_id", po.getProjectId());
		wrapper.eq(user.getCompanyId()!=null,"company_id", user.getCompanyId());
		return selectList(wrapper);
	}
}
