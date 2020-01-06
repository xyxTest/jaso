package com.yaj.jaso.business.studyimage.service;

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
import com.yaj.jaso.business.studyimage.entity.po.StudyImagePO;
import com.yaj.jaso.business.studyimage.entity.vo.StudyImageVo;
import com.yaj.jaso.business.studyimage.mapper.StudyImageMapper;
import com.yaj.jaso.global.PageVoUtil;

/*
 * @Description: 
 * @date: 2019-10-31
 */
@Service
public class StudyImageService extends ServiceImpl<StudyImageMapper, StudyImagePO> {

    @Resource
    StudyImageMapper studyImageMapper;

	public Object add(StudyImagePO po) {
		// TODO Auto-generated method stub
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		po.setCompanyId(userInCache.getCompanyId());
		return insertOrUpdate(po);
	}

	public Object deleteBatchByIds(List<StudyImagePO> po) {
		for(int i=0;i<po.size();i++){
			po.get(i).setIfDelete(1);
		}
		// TODO Auto-generated method stub
		return updateBatchById(po);
	}

	public Object selectListByPage(StudyImageVo po) {
		Page<StudyImagePO> page = new Page<StudyImagePO>();
		page.setCurrent(po.getPageVo().getPageNo()); 
		page.setSize(po.getPageVo().getPageSize());
		Wrapper<StudyImagePO> wrapper = new EntityWrapper<>();
		wrapper.orderDesc(Arrays.asList("create_time"));
		page=selectPage(page, wrapper);
		return PageVoUtil.setPage(page);
	}

}
