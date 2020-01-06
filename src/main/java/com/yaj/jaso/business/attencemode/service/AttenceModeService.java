package com.yaj.jaso.business.attencemode.service;

import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yaj.common.threadlocal.ThreadlocalManager;
import com.yaj.jaso.business.attencemode.entity.po.AttenceModePO;
import com.yaj.jaso.business.attencemode.entity.vo.AttenceModeVo;
import com.yaj.jaso.business.attencemode.mapper.AttenceModeMapper;
import com.yaj.jaso.business.jasouser.entity.po.JasoUserPO;

/*
 * @Description: 考勤模板（上班打卡时间、下班打卡时间、打卡有效范围、打卡地点）
 * @date: 2019-11-05
 */
@Service
public class AttenceModeService extends ServiceImpl<AttenceModeMapper, AttenceModePO> {

    @Resource
    AttenceModeMapper attenceModeMapper;

	public Object add(AttenceModePO attenceMode) {
		// TODO Auto-generated method stub
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		attenceMode.setCompanyId(userInCache.getCompanyId());
		return insertOrUpdate(attenceMode);
	}

	public Object deleteList(List<AttenceModePO> list) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object selectPage(AttenceModeVo log) {
		// TODO Auto-generated method stub
		return null;
	}

}
