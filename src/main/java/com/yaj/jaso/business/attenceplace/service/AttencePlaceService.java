package com.yaj.jaso.business.attenceplace.service;

import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Resource;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yaj.common.threadlocal.ThreadlocalManager;
import com.yaj.jaso.business.attenceplace.entity.po.AttencePlacePO;
import com.yaj.jaso.business.attenceplace.entity.vo.AttencePlaceVo;
import com.yaj.jaso.business.attenceplace.mapper.AttencePlaceMapper;
import com.yaj.jaso.business.jasouser.entity.po.JasoUserPO;
import com.yaj.jaso.global.PageVoUtil;

/*
 * @Description: 考勤地点表
 * @date: 2019-11-05
 */
@Service
public class AttencePlaceService extends ServiceImpl<AttencePlaceMapper, AttencePlacePO> {

    @Resource
    AttencePlaceMapper attencePlaceMapper;

	public Object add(AttencePlacePO attencePlace) {
		// TODO Auto-generated method stub
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		attencePlace.setCompanyId(userInCache.getCompanyId());
		return insertOrUpdate(attencePlace);
	}

	public Object deleteList(List<AttencePlacePO> list) {
		// TODO Auto-generated method stub
		for(int i=0;i<list.size();i++){
			list.get(i).setIfDelete(1);
		}
		// TODO Auto-generated method stub
		return updateBatchById(list);
	}

	public Object selectPage(AttencePlaceVo placeList) {
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		Page<AttencePlacePO> page = new Page<AttencePlacePO>();
		page.setCurrent(placeList.getPageVo().getPageNo());
		page.setSize(placeList.getPageVo().getPageSize());
		Wrapper<AttencePlacePO> wrapper = new EntityWrapper<AttencePlacePO>();
		wrapper.orderDesc(Arrays.asList("feedback_id"));
		wrapper.like(placeList.getPlaceName()!=null,"place_name", placeList.getPlaceName())
		.eq("company_id", userInCache.getCompanyId());
		page=selectPage(page, wrapper);
        return PageVoUtil.setPage(page);
	}

}
