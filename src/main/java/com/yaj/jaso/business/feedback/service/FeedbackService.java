package com.yaj.jaso.business.feedback.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yaj.common.threadlocal.ThreadlocalManager;
import com.yaj.jaso.business.feedback.entity.po.FeedbackPO;
import com.yaj.jaso.business.feedback.entity.vo.FeedbackVo;
import com.yaj.jaso.business.feedback.mapper.FeedbackMapper;
import com.yaj.jaso.business.jasouser.entity.po.JasoUserPO;
import com.yaj.jaso.business.jasouser.service.JasoUserService;
import com.yaj.jaso.global.PageVoUtil;

/*
 * @Description: 
 * @date: 2019-09-09
 */
@Service
public class FeedbackService extends ServiceImpl<FeedbackMapper, FeedbackPO> {

    @Resource
    FeedbackMapper feedbackMapper;
	public Object add(FeedbackPO po) {
		// TODO Auto-generated method stub
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		po.setCompanyId(userInCache.getCompanyId());
		po.setUserRealName(userInCache.getUserRealName());
		return insertOrUpdate(po);
	}

	public Object deleteLists(List<FeedbackPO> pos) {
		for(int i=0;i<pos.size();i++){
			pos.get(i).setIfDelete(1);
		}
		// TODO Auto-generated method stub
		return updateBatchById(pos);
	}

	public Object selectPageList(FeedbackVo pos) {
		// TODO Auto-generated method stub
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		Page<FeedbackPO> page = new Page<FeedbackPO>();
		page.setCurrent(pos.getPageVo().getPageNo());
		page.setSize(pos.getPageVo().getPageSize());
		Wrapper<FeedbackPO> wrapper = new EntityWrapper<FeedbackPO>();
		wrapper.orderDesc(Arrays.asList("feedback_id"));
		wrapper.like(pos.getContent()!=null,"content", pos.getContent());
		wrapper.eq("company_id", userInCache.getCompanyId());
		wrapper.like(pos.getUserRealName()!=null,"project_id", pos.getUserRealName());
		page=selectPage(page, wrapper);
        return PageVoUtil.setPage(page);
	}
	

}
