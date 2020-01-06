package com.yaj.jaso.business.studyevent.service;

import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yaj.core.util.BeanUtil;
import com.yaj.jaso.business.rolemenu.entity.po.RoleMenuPO;
import com.yaj.jaso.business.studyevent.entity.po.StudyEventPO;
import com.yaj.jaso.business.studyevent.entity.vo.StudyEventVo;
import com.yaj.jaso.business.studyevent.mapper.StudyEventMapper;
import com.yaj.jaso.global.PageVoUtil;

/*
 * @Description: 
 * @date: 2019-10-30
 */
@Service
public class StudyEventService extends ServiceImpl<StudyEventMapper, StudyEventPO> {

    @Resource
    StudyEventMapper studyEventMapper;

	public Object selectLists(StudyEventVo rmp) {
		Page<StudyEventPO> page = new Page<StudyEventPO>();
		page.setCurrent(rmp.getPageVo().getPageNo());
		page.setSize(rmp.getPageVo().getPageSize());
		RoleMenuPO po = new RoleMenuPO();
		BeanUtil.copy(rmp, po);
		Wrapper<StudyEventPO> wrapper = new EntityWrapper<StudyEventPO>();
		wrapper.eq(rmp.getCompanyId()!=null,"company_id", rmp.getCompanyId());
		page=selectPage(page, wrapper);
        return PageVoUtil.setPage(page);
	}

	public Object deleteByIds(List<StudyEventPO> rmps) {
		// TODO Auto-generated method stub
		for(int i=0;i<rmps.size();i++){
			rmps.get(i).setIfDelete(1);
		}
		return updateBatchById(rmps);
	}

}
