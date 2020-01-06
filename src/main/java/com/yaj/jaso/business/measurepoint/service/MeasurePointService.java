package com.yaj.jaso.business.measurepoint.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yaj.common.base.pojo.MulSelect;
import com.yaj.common.base.service.ServiceMain;
import com.yaj.common.threadlocal.ThreadlocalManager;
import com.yaj.jaso.business.jasouser.entity.po.JasoUserPO;
import com.yaj.jaso.business.measurepoint.entity.po.MeasurePointPO;
import com.yaj.jaso.business.measurepoint.entity.vo.MeasurePointAdd;
import com.yaj.jaso.business.measurepoint.entity.vo.MeasurePointAddApp;
import com.yaj.jaso.business.measurepoint.mapper.MeasurePointMapper;
import com.yaj.jaso.business.measureproblem.entity.po.MeasureProblemPO;
import com.yaj.jaso.business.measureproblem.service.MeasureProblemService;
import com.yaj.jaso.business.measureproblemuser.service.MeasureProblemUserService;
import com.yaj.jaso.business.measuresite.entity.po.MeasureSitePO;
import com.yaj.jaso.business.measuresite.service.MeasureSiteService;
import com.yaj.jaso.business.measuresitepoint.entity.po.MeasureSitePointPO;
import com.yaj.jaso.business.measuresitepoint.service.MeasureSitePointService;
import com.yaj.jaso.business.pointcheck.entity.po.PointCheckPO;
import com.yaj.jaso.business.pointcheck.service.PointCheckService;
import com.yaj.jaso.business.pointdatainputlog.entity.po.PointDataInputLogPO;
import com.yaj.jaso.business.pointdatainputlog.service.PointDataInputLogService;
import com.yaj.jaso.business.projectchecktype.entity.po.ProjectCheckTypePO;

/*
 * @Description: 
 * @date: 2019-08-27
 */
@Service
public class MeasurePointService extends ServiceImpl<MeasurePointMapper, MeasurePointPO> {

    @Resource
    MeasurePointMapper measurePointMapper;
    @Autowired
    PointCheckService pointService;
    @Autowired
    MeasureSiteService measureSiteService;
    @Autowired
    ServiceMain serviceMain;
    @Autowired
    PointDataInputLogService pointDataInputLogService;
    @Autowired
    MeasureSitePointService measureSitePointService;
    @Autowired
    MeasureProblemService measureProblemService;
    @Autowired
    MeasureProblemUserService measureProblemUserService;
	public Object selectListById(MeasurePointPO po) {
		Wrapper<MeasurePointPO> wrapper = new EntityWrapper<>();
		wrapper.eq(po.getProjectId()!=null,"project_id", po.getProjectId())
		.eq(po.getCompanyId()!=null,"company_id", po.getCompanyId())
		.eq(po.getMeasurePointId()!=null,"measure_point_id", po.getMeasurePointId());
		return selectList(wrapper);
	}

	public Object deleteBatchByIds(List<MeasurePointPO> po) {
		// TODO Auto-generated method stub
		return null;
	}
	/*网页web端-测点即测点所对应的检查项信息添加*/
	public Object add(MeasurePointAdd po) {
		// TODO Auto-generated method stub
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		MeasurePointPO pointpo = new MeasurePointPO();
		pointpo.setCompanyId(userInCache.getCompanyId());
		pointpo.setX(po.getX());
		pointpo.setY(po.getY());
		pointpo.setJasoUserId(userInCache.getJasoUserId());
		pointpo.setMeasurePaperId(po.getMeasurePaperId());
		pointpo.setMeasureSiteId(po.getMeasureSiteId());
		pointpo.setLabel(po.getLabel());
		pointpo.setProjectId(po.getProjectId());
		if(insert(pointpo)){
			List<PointCheckPO> pointChecks = new ArrayList<>();
			pointChecks=po.getPointCheckList();
			for(int i=0;i<pointChecks.size();i++){
				pointChecks.get(i).setMeasurePointId(pointpo.getMeasurePointId());
				pointChecks.get(i).setCompanyId(userInCache.getCompanyId());
				pointChecks.get(i).setProjectId(po.getProjectId());
				pointChecks.get(i).setJasoUserId(userInCache.getJasoUserId());
			}
			if(pointService.insertBatch(pointChecks)){
				List<MeasureSitePO> measureSites = new ArrayList<>(); 
				Wrapper<MeasureSitePO> wrapper = new EntityWrapper<>();
				wrapper.eq("project_id", pointpo.getProjectId());
				wrapper.eq("company_id", pointpo.getCompanyId());
				wrapper.eq("checked_status", 1);
				wrapper.ne("site_type", 1);
				wrapper.ne("site_type", 2);
				measureSites = measureSiteService.selectList(wrapper);
				List<MeasureSitePointPO> relations = new ArrayList<>();
				for(int k=0;k<pointChecks.size();k++){
					for(int m=0;m<measureSites.size();m++){
						MeasureSitePointPO spo = new MeasureSitePointPO();
						spo.setMeasurePointId(pointChecks.get(k).getMeasurePointId());
						spo.setProjectCheckTypeId(pointChecks.get(k).getProjectCheckTypeId());
						spo.setProjectId(pointChecks.get(k).getProjectId());
						spo.setCompanyId(pointChecks.get(k).getCompanyId());
						spo.setSiteType(measureSites.get(m).getSiteType());/*设置楼栋类型*/
						spo.setMeasureSiteId(measureSites.get(m).getMeasureSiteId());
						spo.setSiteType(measureSites.get(m).getSiteType());
						spo.setPointType(1);
						spo.setStatus(1);
						relations.add(spo);
					}
				}
				//添加测点-房间号对应关系数据
				measureSitePointService.insertBatch(relations);
			}
		}else{
			return false;
		}
		return true;
	}
	/*app端添加*/
	public Object appAdd(List<MeasurePointAddApp> po) {
		long startTime = System.currentTimeMillis(); //获取开始时间
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		List<MeasurePointPO> pointList = new ArrayList<>();
		/*提取测点添加或修改*/
		for(int i=0;i<po.size();i++){
			MeasurePointPO pos = new MeasurePointPO();
			pos.setCompanyId(userInCache.getCompanyId());
			pos.setProjectId(po.get(i).getProjectId());
			pos.setCreateTime(po.get(i).getCreateTime());
			pos.setIfDelete(po.get(i).getIfDelete());
			if(po.get(i).getMeasurePointId()!=null){
				pos.setJasoUserId(po.get(i).getJasoUserId());
			}else{
				pos.setJasoUserId(userInCache.getJasoUserId());
			}
			if(po.get(i).getMeasurePointId()==null){
				pos.setPointType(2);
			}
			pos.setLabel(po.get(i).getLabel());
			pos.setMeasurePaperId(po.get(i).getMeasurePaperId());
			pos.setMeasurePointId(po.get(i).getMeasurePointId());
			pos.setMeasureSiteId(po.get(i).getMeasureSiteId());
			pos.setX(po.get(i).getX());
			pos.setY(po.get(i).getY());
			pointList.add(pos);
		}
		//添加测点-》添加测点-位置-检查项关系表
		List<MeasureSitePointPO> allpoints = new ArrayList<>();
		if(insertOrUpdateBatch(pointList)){
			List<PointDataInputLogPO> logs = new ArrayList<>();
			List<PointCheckPO> checkList = new ArrayList<>();
			for(int i=0;i<po.size();i++){
				for(int j=0;j<po.get(i).getPointCheckList().size();j++){
					boolean flag = true;
					if(po.get(i).getPointCheckList().get(j).getErrorLowerLimit()==null){
						po.get(i).getPointCheckList().get(j).setErrorLowerLimit(-5);
					}
					if(po.get(i).getPointCheckList().get(j).getErrorUpperLimit()==null){
						po.get(i).getPointCheckList().get(j).setErrorUpperLimit(5);
					}
					for(int k=0;k<po.get(i).getPointCheckList().get(j).getDataLogList().size();k++){
						po.get(i).getPointCheckList().get(j).getDataLogList().get(k).setMeasurePointId(pointList.get(i).getMeasurePointId());
						PointDataInputLogPO log = new PointDataInputLogPO();
						log=po.get(i).getPointCheckList().get(j).getDataLogList().get(k);
						log.setCompanyId(userInCache.getCompanyId());
						log.setProjectId(po.get(i).getProjectId());
						log.setJasoUserId(userInCache.getJasoUserId());
						log.setPointStatus(po.get(i).getPointCheckList().get(j).getDataLogList().get(k).getPointStatus());
						log.setLabel(po.get(i).getLabel());
						log.setProjectCheckTypeId(po.get(i).getPointCheckList().get(j).getProjectCheckTypeId());
						logs.add(log);
						int intputdata = po.get(i).getPointCheckList().get(j).getDataLogList().get(k).getInputData();
						int standarddata = po.get(i).getPointCheckList().get(j).getStandardNum();
						int	limitupper = po.get(i).getPointCheckList().get(j).getErrorUpperLimit();
						int	limitlower=po.get(i).getPointCheckList().get(j).getErrorLowerLimit();
						if((intputdata-standarddata)<limitlower || (intputdata-standarddata)>limitupper){
							flag=false;
							po.get(i).getPointCheckList().get(j).getDataLogList().get(k).setPointStatus(2);
						}else{
							po.get(i).getPointCheckList().get(j).getDataLogList().get(k).setPointStatus(1);
						}
					}
					if(po.get(i).getPointCheckList().get(j).getIsAdd()==1 && po.get(i).getPointCheckList().get(j).getPointCheckId()==null){
						po.get(i).getPointCheckList().get(j).setMeasurePointId(pointList.get(i).getMeasurePointId());
						PointCheckPO checkpo = new PointCheckPO();
						checkpo=po.get(i).getPointCheckList().get(j);
						checkpo.setCreateTime(null);
						checkpo.setMeasureSiteId(po.get(i).getMeasureSiteId());
						checkpo.setProjectId(po.get(i).getProjectId());
						checkpo.setJasoUserId(userInCache.getJasoUserId());
						checkpo.setIsSave(po.get(i).getPointCheckList().get(j).getIsSave());
						checkList.add(checkpo);
						MeasureSitePointPO sitepoint = new MeasureSitePointPO();
						sitepoint.setCompanyId(userInCache.getCompanyId());
						sitepoint.setMeasurePointId(pointList.get(i).getMeasurePointId());
						sitepoint.setMeasureSiteId(po.get(i).getMeasureSiteId());
						sitepoint.setPointType(2);
						sitepoint.setProjectId(po.get(i).getProjectId());
						sitepoint.setSiteType(3);
						if(flag){
							sitepoint.setStatus(1);
						}else{
							sitepoint.setStatus(2);
						}
						allpoints.add(sitepoint);
					}
				} 
			}
			if(!checkList.isEmpty()){
				if(pointService.insertBatch(checkList)){//新增测点下面的新添检查项
					List<MeasureProblemPO> measureProblemList = new ArrayList<>(); 
					for(int x=0;x<logs.size();x++){
						if(logs.get(x).getPointStatus()==2 && logs.get(x).getPointDataInputLogId()==null){
							MeasureProblemPO measure = new MeasureProblemPO();
							measure.setCheckSite(logs.get(x).getMeasureSiteInfo()+"-"+logs.get(x).getLabel());
							measure.setCheckUser(userInCache.getJasoUserId());
							measure.setJasoUserId(userInCache.getJasoUserId());
							measure.setCompanyId(userInCache.getCompanyId());
							measure.setProjectId(logs.get(x).getProjectId());
							measure.setMeasurePointId(logs.get(x).getMeasurePointId());
							measure.setMeasureSiteId(logs.get(x).getMeasureSiteId());
							measure.setProjectCheckTypeId(logs.get(x).getProjectCheckTypeId());
							measure.setInputData(logs.get(x).getInputData());
							measure.setStatus(1);
							measureProblemList.add(measure);
						}
					}
					if(!measureProblemList.isEmpty())
						measureProblemService.insertBatch(measureProblemList);
					if(!logs.isEmpty() && pointDataInputLogService.insertOrUpdateBatch(logs)){
						for(int j=0;j<checkList.size();j++){
							allpoints.get(j).setProjectCheckTypeId(checkList.get(j).getProjectCheckTypeId());
						}
					}
				}
			}else if(!logs.isEmpty()){
				List<MeasureProblemPO> measureProblemList = new ArrayList<>(); 
				for(int x=0;x<logs.size();x++){
					if(logs.get(x).getPointStatus()==2 && logs.get(x).getPointDataInputLogId()==null){
						MeasureProblemPO measure = new MeasureProblemPO();
						measure.setCheckSite(logs.get(x).getMeasureSiteInfo()+"-"+logs.get(x).getLabel());
						measure.setCheckUser(userInCache.getJasoUserId());
						measure.setJasoUserId(userInCache.getJasoUserId());
						measure.setCompanyId(userInCache.getCompanyId());
						measure.setProjectId(logs.get(x).getProjectId());
						measure.setMeasurePointId(logs.get(x).getMeasurePointId());
						measure.setMeasureSiteId(logs.get(x).getMeasureSiteId());
						measure.setProjectCheckTypeId(logs.get(x).getProjectCheckTypeId());
						measure.setInputData(logs.get(x).getInputData());
						measure.setStatus(1);
						measureProblemList.add(measure);
					}
				}
				if(!measureProblemList.isEmpty())
					measureProblemService.insertBatch(measureProblemList);
				if(pointDataInputLogService.insertOrUpdateBatch(logs)){
					List<MeasureSitePointPO> sitePoints = new ArrayList<>();
					Wrapper<MeasureSitePointPO> wrapper = new EntityWrapper<>();
					wrapper.eq("company_id", userInCache.getCompanyId())
					.eq(po.get(0).getProjectId()!=null,"project_id", po.get(0).getProjectId());
					sitePoints=measureSitePointService.selectList(wrapper);
					for(int y=0;y<sitePoints.size();y++){
						for(int z=0;z<logs.size();z++){
							if(sitePoints.get(y).getMeasurePointId().equals(logs.get(z).getMeasurePointId()) 
									&& sitePoints.get(y).getMeasureSiteId().equals(logs.get(z).getMeasureSiteId())
									&& sitePoints.get(y).getProjectCheckTypeId().equals(logs.get(z).getProjectCheckTypeId())){
								if(logs.get(z).getPointStatus()==2){
									sitePoints.get(y).setStatus(2);
								}
							}
						}
					}
					measureSitePointService.insertOrUpdateBatch(sitePoints);
				}
			}
		}
		if(!allpoints.isEmpty() && !measureSitePointService.insertBatch(allpoints)){
			return false;
		}
		long endTime = System.currentTimeMillis(); //获取结束时间
		System.out.println("程序运行时间：" + (endTime - startTime) + "ms"); //输出程序运行时间
		return true;
	}

	public Object selectAppAll(MeasurePointPO po) {
		// TODO Auto-generated method stub
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		Wrapper<MeasureSitePointPO> pointWrapper = new EntityWrapper<>();
		pointWrapper.eq(po.getProjectId()!=null,"project_id", po.getProjectId());
		pointWrapper.eq("company_id", userInCache.getCompanyId());
		MulSelect mul = MulSelect.newInstance("${1}.measurePaperId,${1}.abscissa,${1}.ordinate,${1}.pointTag,${1}.pointStatus,"
				+ "${1}.pointType,${1}.measureSiteId,${2}.standardNum,${2}.errorUpperLimit,${2}.errorLowerLimit,"
				+ "${3}.checkName", new MeasureSitePointPO(), new MeasurePointPO(),new PointCheckPO(),new ProjectCheckTypePO());
		mul.where("${measure_site_point}")
			.eq(po.getProjectId()!=null,"projectId", po.getProjectId())
			.eq("companyId", userInCache.getCompanyId());
		return serviceMain.mulSelect(mul);
	}

	public List<MeasurePointPO> selectList(MeasurePointPO pointPO) {
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		Wrapper<MeasurePointPO> pointWrapper = new EntityWrapper<>();
		pointWrapper.eq(pointPO.getProjectId()!=null,"project_id", pointPO.getProjectId());
		pointWrapper.eq("company_id", userInCache.getCompanyId())
		.orderBy("label");
		return selectList(pointWrapper);
	}

	

}
