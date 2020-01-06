package com.yaj.jaso.business.projectchecktype.service;

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
import com.yaj.jaso.business.projectchecktype.entity.po.ProjectCheckTypePO;
import com.yaj.jaso.business.projectchecktype.entity.vo.ProjectCheckTypeVo;
import com.yaj.jaso.business.projectchecktype.mapper.ProjectCheckTypeMapper;
import com.yaj.jaso.global.PageVoUtil;

/*
 * @Description: 
 * @date: 2019-08-26
 */
@Service
public class ProjectCheckTypeService extends ServiceImpl<ProjectCheckTypeMapper, ProjectCheckTypePO> {

    @Resource
    ProjectCheckTypeMapper projectCheckTypeMapper;

	public Object add(ProjectCheckTypePO po) {
		// TODO Auto-generated method stub
		JasoUserPO cacheUser = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		po.setCompanyId(cacheUser.getCompanyId());
		return insertOrUpdate(po);
	}

	public Object deleteBatchByIds(List<ProjectCheckTypePO> po) {
		for(int i=0;i<po.size();i++){
			po.get(i).setIfDelete(1);
		}
		// TODO Auto-generated method stub
		return updateBatchById(po);
	}

	public Object selectListByPage(ProjectCheckTypeVo po) {
		// TODO Auto-generated method stub
		Page<ProjectCheckTypePO> page = new Page<ProjectCheckTypePO>();
		page.setCurrent(po.getPageVo().getPageNo());
		page.setSize(po.getPageVo().getPageSize());
		Wrapper<ProjectCheckTypePO> wrapper = new EntityWrapper<>();
		wrapper.eq(po.getCheckType()!=null,"check_type", po.getCheckType());
		wrapper.like(po.getCheckName()!=null,"check_name", po.getCheckName());
		wrapper.orderDesc(Arrays.asList("create_time"));
		page=selectPage(page, wrapper);
		return PageVoUtil.setPage(page);
	}

	public List<ProjectCheckTypePO> selectLists(ProjectCheckTypePO checkTypePO) {
		JasoUserPO cacheUser = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		Wrapper<ProjectCheckTypePO> wrapper = new EntityWrapper<>();
		wrapper.eq(checkTypePO.getCheckType()!=null,"check_type", checkTypePO.getCheckType());
		wrapper.eq("company_id", cacheUser.getCompanyId());
		return selectList(wrapper);
	}
}
