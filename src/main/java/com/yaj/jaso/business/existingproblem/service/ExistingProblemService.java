package com.yaj.jaso.business.existingproblem.service;

import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Resource;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yaj.common.threadlocal.ThreadlocalManager;
import com.yaj.jaso.business.existingproblem.entity.po.ExistingProblemPO;
import com.yaj.jaso.business.existingproblem.entity.vo.ExistingProblemVo;
import com.yaj.jaso.business.existingproblem.mapper.ExistingProblemMapper;
import com.yaj.jaso.business.jasouser.entity.po.JasoUserPO;
import com.yaj.jaso.global.PageVoUtil;

/*
 * @Description: 
 * @date: 2019-08-09
 */
@Service
public class ExistingProblemService extends ServiceImpl<ExistingProblemMapper, ExistingProblemPO> {

    @Resource
    ExistingProblemMapper existingProblemMapper;
    public Object deleteLists(List<ExistingProblemPO> list) {
		for(int i=0;i<list.size();i++){
			list.get(i).setIfDelete(1);
		}
		// TODO Auto-generated method stub
		return updateBatchById(list);
	}

	public Object selectPageList(ExistingProblemVo constructContent) {
		// TODO Auto-generated method stub
		JasoUserPO currentUser = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		Page<ExistingProblemPO> page = new Page<ExistingProblemPO>();
		page.setCurrent(constructContent.getPageVo().getPageNo());
		page.setSize(constructContent.getPageVo().getPageSize());
		Wrapper<ExistingProblemPO> wrapper = new EntityWrapper<ExistingProblemPO>();
		wrapper.orderDesc(Arrays.asList("existing_problem_id"));
		wrapper.like(constructContent.getExistingProblemName()!=null,"existing_problem_name", constructContent.getExistingProblemName());
		wrapper.eq("company_id", currentUser.getCompanyId());
		wrapper.eq(constructContent.getProjectId()!=null,"project_id", constructContent.getProjectId());
		page=selectPage(page, wrapper);
        return PageVoUtil.setPage(page);
	}

	public Object add(ExistingProblemPO existingProblem) {
		// TODO Auto-generated method stub
		JasoUserPO cacheUser = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		existingProblem.setCompanyId(cacheUser.getCompanyId());
		return insertOrUpdate(existingProblem);
	}

	public Object getExistingProblemList(ExistingProblemPO existingProblem) {
		// TODO Auto-generated method stub
		JasoUserPO currentUser = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		Wrapper<ExistingProblemPO> wrapper = new EntityWrapper<>();
		wrapper.eq("company_id", currentUser.getCompanyId());
		wrapper.eq(existingProblem.getProjectId()!=null,"project_id", existingProblem.getProjectId());
		wrapper.like(existingProblem.getExistingProblemName()!=null,"existing_problem_name", existingProblem.getExistingProblemName());
		return selectList(wrapper);
	}

}
