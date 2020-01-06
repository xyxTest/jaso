package com.yaj.jaso.business.studyanswerrecord.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yaj.common.threadlocal.ThreadlocalManager;
import com.yaj.jaso.business.jasouser.entity.po.JasoUserPO;
import com.yaj.jaso.business.studyanswerrecord.entity.po.StudyAnswerRecordPO;
import com.yaj.jaso.business.studyanswerrecord.mapper.StudyAnswerRecordMapper;

/*
 * @Description: 
 * @date: 2019-11-01
 */
@Service
public class StudyAnswerRecordService extends ServiceImpl<StudyAnswerRecordMapper, StudyAnswerRecordPO> {

    @Resource
    StudyAnswerRecordMapper studyAnswerRecordMapper;

	public Object selectLists(StudyAnswerRecordPO po) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object add(StudyAnswerRecordPO po) {
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		po.setCompanyId(userInCache.getCompanyId());
		po.setJasoUserId(userInCache.getJasoUserId());
		return insertOrUpdate(po);
	}

}
