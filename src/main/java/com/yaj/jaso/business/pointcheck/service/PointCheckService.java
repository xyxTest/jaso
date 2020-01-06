package com.yaj.jaso.business.pointcheck.service;

import org.springframework.stereotype.Service;
import java.util.List;
import javax.annotation.Resource;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yaj.common.threadlocal.ThreadlocalManager;
import com.yaj.jaso.business.jasouser.entity.po.JasoUserPO;
import com.yaj.jaso.business.pointcheck.entity.po.PointCheckPO;
import com.yaj.jaso.business.pointcheck.mapper.PointCheckMapper;

/*
 * @Description: 
 * @date: 2019-08-27
 */
@Service
public class PointCheckService extends ServiceImpl<PointCheckMapper, PointCheckPO> {

    @Resource
    PointCheckMapper pointCheckMapper;

	public Object deleteBatchByIds(List<PointCheckPO> po) {
		// TODO Auto-generated method stub
		return null;
	}
	/*通过测点id查询测点下面的检查项*/
	public List<PointCheckPO> selectLists(PointCheckPO po) {
		// TODO Auto-generated method stub
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		Wrapper<PointCheckPO> wrapper = new EntityWrapper<>();
		wrapper.eq("company_id", userInCache.getCompanyId());
		wrapper.eq(po.getProjectId()!=null,"project_id", po.getProjectId());
		wrapper.eq(po.getMeasurePointId()!=null,"measure_point_id",po.getMeasurePointId());
		return selectList(wrapper);
	}

	public Object add(PointCheckPO po) {
		// TODO Auto-generated method stub
		return null;
	}

}
