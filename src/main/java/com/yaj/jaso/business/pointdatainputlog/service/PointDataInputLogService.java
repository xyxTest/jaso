package com.yaj.jaso.business.pointdatainputlog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import javax.annotation.Resource;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yaj.common.threadlocal.ThreadlocalManager;
import com.yaj.jaso.business.jasouser.entity.po.JasoUserPO;
import com.yaj.jaso.business.measurepoint.service.MeasurePointService;
import com.yaj.jaso.business.measureproblem.service.MeasureProblemService;
import com.yaj.jaso.business.measureproblemuser.service.MeasureProblemUserService;
import com.yaj.jaso.business.pointdatainputlog.entity.po.PointDataInputLogPO;
import com.yaj.jaso.business.pointdatainputlog.mapper.PointDataInputLogMapper;

/*
 * @Description: 
 * @date: 2019-08-28
 */
@Service
public class PointDataInputLogService extends ServiceImpl<PointDataInputLogMapper, PointDataInputLogPO> {

    @Resource
    PointDataInputLogMapper pointDataInputLogMapper;
    @Autowired
    MeasurePointService measurePointService;
    @Autowired
    MeasureProblemService problemService;
    @Autowired
    MeasureProblemUserService problemUserService;
	public Object add(List<PointDataInputLogPO> po) {
		// TODO Auto-generated method stub
		/*JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		List<MeasurePointPO> list = new ArrayList<>();
		List<MeasureProblemPO> problemList = new ArrayList<>();
		提取问题测点，更新这些测点状态
		for(int i=0;i<po.size();i++){
			MeasurePointPO mpo = new MeasurePointPO();
			MeasureProblemPO measureProblem = new MeasureProblemPO();
			if(!list.isEmpty()){
				if(po.get(i).getStatus()==2){
					mpo.setMeasurePointId(po.get(i).getMeasurePointId());
					list.add(mpo);
					measureProblem.setCheckSite(po.get(i).getMeasureSiteInfo());
					measureProblem.setMeasurePointId(po.get(i).getMeasurePointId());
					measureProblem.setCompanyId(userInCache.getCompanyId());
					measureProblem.setProjectId(po.get(i).getProjectId());
					measureProblem.setProjectCheckTypeId(po.get(i).getProjectCheckTypeId());
					measureProblem.setJasoUserId(userInCache.getJasoUserId());
					measureProblem.setStatus(1);
					problemList.add(measureProblem);
				}
			}else{
				boolean flag = true;
				for(int j=0;j<list.size();j++){
					if(list.get(j).getMeasurePointId().equals(po.get(i).getMeasurePointId())){
						flag=false;
					}
				}
				if(flag){
					if(po.get(i).getStatus()==2){
						mpo.setMeasurePointId(po.get(i).getMeasurePointId());
						mpo.setPointStatus(2);
						list.add(mpo);
						measureProblem.setCheckSite(po.get(i).getMeasureSiteInfo());
						measureProblem.setCompanyId(userInCache.getCompanyId());
						measureProblem.setProjectId(po.get(i).getProjectId());
						measureProblem.setProjectCheckTypeId(po.get(i).getProjectCheckTypeId());
						measureProblem.setJasoUserId(userInCache.getJasoUserId());
						measureProblem.setStatus(1);
						problemList.add(measureProblem);
					}
				}
			}		
		}
		if(insertBatch(po)){
			//更新测点状态(更新状态为爆点)
			if(measurePointService.updateBatchById(list)){
				生成标点清单
				if(problemService.insertBatch(problemList)){
					List<MeasureProblemUserPO> users = new ArrayList<>();
					for(int s=0;s<problemList.size();s++){
						MeasureProblemUserPO user = new MeasureProblemUserPO();
						user.setAboutId(problemList.get(s).getMeasureProblemId());
						user.setProjectId(problemList.get(s).getProjectId());
						user.setType(1);
						user.setJasoUserId(userInCache.getJasoUserId());
						user.setCompanyId(userInCache.getCompanyId());
						users.add(user);
					}
					problemUserService.insertBatch(users);
				}
			}
			
		}else{
			return false;
		}*/
		return true;
	}

	public Object deleteBatchByIds(List<PointDataInputLogPO> po) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<PointDataInputLogPO> selectLists(PointDataInputLogPO po) {
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		Wrapper<PointDataInputLogPO> wrapper = new EntityWrapper<>();
		wrapper.eq("company_id", userInCache.getCompanyId());
		wrapper.eq(po.getProjectId()!=null,"project_id", po.getProjectId());
		return selectList(wrapper);
	}

}
