package com.yaj.jaso.business.studyworkertype.service;

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
import com.yaj.jaso.business.studyworkertype.entity.po.StudyWorkerTypePO;
import com.yaj.jaso.business.studyworkertype.entity.vo.StudyWorkerTypeVo;
import com.yaj.jaso.business.studyworkertype.mapper.StudyWorkerTypeMapper;
import com.yaj.jaso.global.PageVoUtil;

/*
 * @Description: 
 * @date: 2019-10-24
 */
@Service
public class StudyWorkerTypeService extends ServiceImpl<StudyWorkerTypeMapper, StudyWorkerTypePO> {

    @Resource
    StudyWorkerTypeMapper studyWorkerTypeMapper;

    public Object add(StudyWorkerTypePO po) {
		// TODO Auto-generated method stub
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		po.setCompanyId(userInCache.getCompanyId());
		return insertOrUpdate(po);
	}

	public Object deleteBatchByIds(List<StudyWorkerTypePO> po) {
		for(int i=0;i<po.size();i++){
			po.get(i).setIfDelete(1);
		}
		// TODO Auto-generated method stub
		return updateBatchById(po);
	}

	public Object selectListByPage(StudyWorkerTypeVo po) {
		// TODO Auto-generated method stub
		Page<StudyWorkerTypePO> page = new Page<StudyWorkerTypePO>();
		page.setCurrent(po.getPageVo().getPageNo()); 
		page.setSize(po.getPageVo().getPageSize());
		Wrapper<StudyWorkerTypePO> wrapper = new EntityWrapper<>();
		wrapper.like(po.getName()!=null,"nature_name", po.getName());
		wrapper.orderDesc(Arrays.asList("create_time"));
		page=selectPage(page, wrapper);
		return PageVoUtil.setPage(page);
	}

	public List<StudyWorkerTypePO> selectLists(StudyWorkerTypePO po) {
		JasoUserPO cacheUser = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		Wrapper<StudyWorkerTypePO> wrapper = new EntityWrapper<>();
		wrapper.eq("company_id", cacheUser.getCompanyId());
		return selectList(wrapper);
	}

}
