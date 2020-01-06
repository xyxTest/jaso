package com.yaj.jaso.business.imagerotation.service;

import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Resource;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yaj.common.threadlocal.ThreadlocalManager;
import com.yaj.jaso.business.imagerotation.entity.po.ImageRotationPO;
import com.yaj.jaso.business.imagerotation.entity.vo.ImageRotationVo;
import com.yaj.jaso.business.imagerotation.mapper.ImageRotationMapper;
import com.yaj.jaso.business.jasouser.entity.po.JasoUserPO;
import com.yaj.jaso.global.PageVoUtil;

/*
 * @Description: 
 * @date: 2019-07-25
 */
@Service
public class ImageRotationService extends ServiceImpl<ImageRotationMapper, ImageRotationPO> {

    @Resource
    ImageRotationMapper imageRotationMapper;

	public Object selectListByPage(ImageRotationVo po) {
		// TODO Auto-generated method stub
		Page<ImageRotationPO> page = new Page<ImageRotationPO>();
		page.setCurrent(po.getPageVo().getPageNo());
		page.setSize(po.getPageVo().getPageSize());
		Wrapper<ImageRotationPO> wrapper = new EntityWrapper<>();
		wrapper.orderDesc(Arrays.asList("create_time"));
		page=selectPage(page, wrapper);
		return PageVoUtil.setPage(page);
	}

	public Object deleteBatchByIds(List<ImageRotationPO> po) {
		for(int i=0;i<po.size();i++){
			po.get(i).setIfDelete(1);
		}
		// TODO Auto-generated method stub
		return updateBatchById(po);
	}

	public Object add(ImageRotationPO po) {
		// TODO Auto-generated method stub
		JasoUserPO cacheUser = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		po.setCompanyId(cacheUser.getCompanyId());
		return insertOrUpdate(po);
	}
}
