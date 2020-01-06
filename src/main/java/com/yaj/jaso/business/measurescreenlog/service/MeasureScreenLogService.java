package com.yaj.jaso.business.measurescreenlog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yaj.common.threadlocal.ThreadlocalManager;
import com.yaj.jaso.business.jasouser.entity.po.JasoUserPO;
import com.yaj.jaso.business.measurepoint.service.MeasurePointService;
import com.yaj.jaso.business.measurescreenlog.entity.po.MeasureScreenLogPO;
import com.yaj.jaso.business.measurescreenlog.mapper.MeasureScreenLogMapper;
import com.yaj.jaso.business.measuresite.service.MeasureSiteService;
import com.yaj.jaso.business.measuresitepoint.service.MeasureSitePointService;
import com.yaj.jaso.business.pointdatainputlog.service.PointDataInputLogService;

/*
 * @Description: 
 * @date: 2019-08-29
 */
@Service
public class MeasureScreenLogService extends ServiceImpl<MeasureScreenLogMapper, MeasureScreenLogPO> {

    @Resource
    MeasureScreenLogMapper measureScreenLogMapper;
    @Autowired
    PointDataInputLogService logService;
    @Autowired
    MeasureSitePointService pointService;
    @Autowired
    MeasurePointService poService;
    @Autowired
    MeasureSiteService measureSiteService;
	public Object add(MeasureScreenLogPO po) {
		// TODO Auto-generated method stub
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		po.setJasoUserId(userInCache.getJasoUserId());
		po.setCompanyId(userInCache.getCompanyId());
		return insertOrUpdate(po);
	}

	public Object deleteBatchByIds(List<MeasureScreenLogPO> po) {
		// TODO Auto-generated method stub
		for(int i=0;i<po.size();i++){
			po.get(i).setIfDelete(1);
		}
		return updateBatchById(po);
	}

	public List<MeasureScreenLogPO> selectList(MeasureScreenLogPO po) {
		// TODO Auto-generated method stub
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		/*拿取实测实量的筛选记录表*/
		List<MeasureScreenLogPO> screens = new ArrayList<>();
		Wrapper<MeasureScreenLogPO> wrapper = new EntityWrapper<>();
		wrapper.eq("company_id", userInCache.getCompanyId());
		wrapper.eq("jaso_user_id", userInCache.getJasoUserId());
		wrapper.eq(po.getProjectId()!=null,"project_id", po.getProjectId());
		screens=selectList(wrapper);
		return screens;
	}

}
