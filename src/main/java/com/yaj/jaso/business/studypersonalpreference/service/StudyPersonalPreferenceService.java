package com.yaj.jaso.business.studypersonalpreference.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yaj.common.base.pojo.MulSelect;
import com.yaj.common.base.service.ServiceMain;
import com.yaj.common.threadlocal.ThreadlocalManager;
import com.yaj.jaso.business.jasouser.entity.po.JasoUserPO;
import com.yaj.jaso.business.studypersonalpreference.entity.po.StudyPersonalPreferencePO;
import com.yaj.jaso.business.studypersonalpreference.mapper.StudyPersonalPreferenceMapper;
import com.yaj.jaso.business.studyworkertype.entity.po.StudyWorkerTypePO;

/*
 * @Description: 个人喜好（针对工种）记录表
 * @date: 2019-11-01
 */
@Service
public class StudyPersonalPreferenceService extends ServiceImpl<StudyPersonalPreferenceMapper, StudyPersonalPreferencePO> {

    @Resource
    StudyPersonalPreferenceMapper studyPersonalPreferenceMapper;
    @Autowired
    ServiceMain serviceMain;
	public Object add(List<StudyPersonalPreferencePO> list) {
		// TODO Auto-generated method stub
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		for(int i=0;i<list.size();i++){
			list.get(i).setCompanyId(userInCache.getCompanyId());
			list.get(i).setJasoUserId(userInCache.getJasoUserId());
			list.get(i).setCreateTime(new Date());
		}
		return insertOrUpdateBatch(list);
	}

	public Object deleteBatchByIds(List<StudyPersonalPreferencePO> po) {
		for(int i=0;i<po.size();i++){
			po.get(i).setIfDelete(1);
		}
		// TODO Auto-generated method stub
		return updateBatchById(po);
	}

	public Object selectLists(StudyPersonalPreferencePO po) {
		// TODO Auto-generated method stub
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		MulSelect mul = MulSelect.newInstance("${1}.name", new StudyPersonalPreferencePO(),new StudyWorkerTypePO());
		mul.where("${study_personal_preference}")
			.eq("jasoUserId", userInCache.getJasoUserId())
			.eq("companyId", userInCache.getCompanyId());
		return serviceMain.mulSelect(mul);
	}
	

}
