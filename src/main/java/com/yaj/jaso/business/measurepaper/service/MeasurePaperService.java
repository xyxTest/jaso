package com.yaj.jaso.business.measurepaper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yaj.common.threadlocal.ThreadlocalManager;
import com.yaj.jaso.business.jasouser.entity.po.JasoUserPO;
import com.yaj.jaso.business.measurepaper.entity.po.MeasurePaperPO;
import com.yaj.jaso.business.measurepaper.entity.vo.MeasurePaperVo;
import com.yaj.jaso.business.measurepaper.entity.vo.MeasurePointInfo;
import com.yaj.jaso.business.measurepaper.entity.vo.PaperPointList;
import com.yaj.jaso.business.measurepaper.entity.vo.PointCheckResult;
import com.yaj.jaso.business.measurepaper.mapper.MeasurePaperMapper;
import com.yaj.jaso.business.measurepoint.entity.po.MeasurePointPO;
import com.yaj.jaso.business.measurepoint.service.MeasurePointService;
import com.yaj.jaso.business.measuresitepoint.entity.po.MeasureSitePointPO;
import com.yaj.jaso.business.measuresitepoint.service.MeasureSitePointService;
import com.yaj.jaso.business.pointcheck.entity.po.PointCheckPO;
import com.yaj.jaso.business.pointcheck.service.PointCheckService;
import com.yaj.jaso.business.projectchecktype.entity.po.ProjectCheckTypePO;
import com.yaj.jaso.business.projectchecktype.service.ProjectCheckTypeService;
import com.yaj.jaso.global.PageVoUtil;

/*
 * @Description: 
 * @date: 2019-08-27
 */
@Service
public class MeasurePaperService extends ServiceImpl<MeasurePaperMapper, MeasurePaperPO> {

    @Resource
    MeasurePaperMapper measurePaperMapper;
    @Autowired
    MeasurePointService pointService;
    @Autowired
    MeasureSitePointService sitePointService;
    @Autowired
    PointCheckService checkService;
    @Autowired
    ProjectCheckTypeService checkTypeService;
	public Object add(MeasurePaperPO po) {
		// TODO Auto-generated method stub
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		po.setCompanyId(userInCache.getCompanyId());
		po.setMeasureNum(0);
		po.setJasoUserId(userInCache.getJasoUserId());
		po.setMeasurePaperStatus(1);
		po.setType(1);
		return insertOrUpdate(po);
	}

	public Object deleteBatchByIds(List<MeasurePaperPO> po) {
		// TODO Auto-generated method stub
		for(int i=0;i<po.size();i++){
			po.get(i).setIfDelete(1);
		}
		return updateBatchById(po);
	}

	public Object selectListByPage(MeasurePaperVo po) {
		// TODO Auto-generated method stub
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		po.setCompanyId(userInCache.getCompanyId());
		Page<MeasurePaperPO> page = new Page<MeasurePaperPO>();
		page.setSize(po.getPageVo().getPageSize());
		page.setCurrent(po.getPageVo().getPageNo());
		Wrapper<MeasurePaperPO> wrapper = new EntityWrapper<>();
		wrapper.eq("company_id", userInCache.getCompanyId());
		wrapper.eq(po.getProjectId()!=null,"project_id", po.getProjectId());
		wrapper.orderDesc(Arrays.asList("create_time"));
		page=selectPage(page, wrapper);
        return PageVoUtil.setPage(page);
	}

	public List<MeasurePaperPO> selectList(MeasurePaperPO po) {
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		po.setCompanyId(userInCache.getCompanyId());
		Wrapper<MeasurePaperPO> wrapper = new EntityWrapper<>();
		wrapper.eq("company_id", userInCache.getCompanyId());
		wrapper.eq(po.getProjectId()!=null,"project_id", po.getProjectId());
		wrapper.orderDesc(Arrays.asList("create_time"));
        return selectList(wrapper);
	}

	public Object getPointList(MeasurePaperPO po) {
		// TODO Auto-generated method stub
		PaperPointList result = new PaperPointList();
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		if(po.getMeasurePaperId()!=null){
			List<MeasurePointInfo> measurePointInfo = new ArrayList<>();
			List<MeasurePointPO> measurePointList = new ArrayList<>();
			Wrapper<MeasurePointPO> measureWrapper = new EntityWrapper<>();
			measureWrapper.eq("measure_paper_id", po.getMeasurePaperId())
			.eq("project_id", po.getProjectId())
			.eq("company_id", userInCache.getCompanyId())
			.eq("point_type", 1)
			.orderBy("label");
			measurePointList = pointService.selectList(measureWrapper);
			if(!measurePointList.isEmpty()){
				List<Long> measurePointId = new ArrayList<>();
				for(MeasurePointPO point:measurePointList){
					measurePointId.add(point.getMeasurePointId());
				}
				List<PointCheckPO> pointCheckList = new ArrayList<>();
				Wrapper<PointCheckPO> checkWrapper = new EntityWrapper<>();
				checkWrapper.eq("project_id", po.getProjectId())
				.eq("company_id", userInCache.getCompanyId())
				.eq("is_add",0)//标注新增
				.in("measure_point_id", measurePointId);
				pointCheckList=checkService.selectList(checkWrapper);
				List<ProjectCheckTypePO> checkList = new ArrayList<>();
				Wrapper<ProjectCheckTypePO> checkTypeWrapper = new EntityWrapper<>();
				checkTypeWrapper.eq("company_id", userInCache.getCompanyId())
				.eq("check_type", 1);
				checkList = checkTypeService.selectList(checkTypeWrapper);
				for(MeasurePointPO point:measurePointList){
					MeasurePointInfo pointInfo = new MeasurePointInfo();
					pointInfo.setCompanyId(point.getCompanyId());
					pointInfo.setLabel(point.getLabel());
					pointInfo.setX(point.getX());
					pointInfo.setY(point.getY());
					pointInfo.setProjectId(point.getProjectId());
					pointInfo.setMeasureSiteId(point.getMeasureSiteId());
					pointInfo.setMeasurePointId(point.getMeasurePointId());
					pointInfo.setMeasurePaperId(point.getMeasurePaperId());
					pointInfo.setCreateTime(point.getCreateTime());
					pointInfo.setJasoUserId(point.getJasoUserId());
					pointInfo.setIfDelete(point.getIfDelete());
					List<PointCheckResult> pointCheck = new ArrayList<>();
					for(int q=0;q<checkList.size();q++){
						PointCheckResult checkItem = new PointCheckResult();
						checkItem.setCompanyId(checkList.get(q).getCompanyId());
						checkItem.setErrorLowerLimit(-5);
						checkItem.setErrorUpperLimit(5);
						checkItem.setIsAdd(0);
						checkItem.setJasoUserId(userInCache.getJasoUserId());
						checkItem.setMeasurePointId(point.getMeasurePointId());
						checkItem.setProjectCheckTypeId(checkList.get(q).getProjectCheckTypeId());
						checkItem.setProjectId(point.getProjectId());
						checkItem.setStandardNum(0);
						checkItem.setCheckName(checkList.get(q).getCheckName());
						for(int i=0;i<pointCheckList.size();i++){
							if(point.getMeasurePointId().equals(pointCheckList.get(i).getMeasurePointId())
									&& pointCheckList.get(i).getProjectCheckTypeId().equals(checkList.get(q).getProjectCheckTypeId())){
								if(pointCheckList.get(i).getErrorLowerLimit()!=null){
									checkItem.setErrorLowerLimit(pointCheckList.get(i).getErrorLowerLimit());
								}
								if(pointCheckList.get(i).getErrorUpperLimit()!=null){
									checkItem.setErrorUpperLimit(pointCheckList.get(i).getErrorUpperLimit());
								}
								if(pointCheckList.get(i).getStandardNum()!=null){
									checkItem.setStandardNum(pointCheckList.get(i).getStandardNum());
								}
							}
						}
						pointCheck.add(checkItem);
					}
					pointInfo.setPointCheckList(pointCheck);
					measurePointInfo.add(pointInfo);
				}
				result.setMeasurePointInfo(measurePointInfo);
			}
			
		}
		return result;
	}
	public Object pushPoint(List<MeasurePointInfo> pointList) {
		// TODO Auto-generated method stub
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		if(!pointList.isEmpty()){
			List<MeasurePointPO> points = new ArrayList<>();
			for(int i=0;i<pointList.size();i++){
				MeasurePointPO point = new MeasurePointPO();
				point.setCompanyId(userInCache.getCompanyId());
				point.setMeasurePointId(pointList.get(i).getMeasurePointId());
				point.setJasoUserId(userInCache.getJasoUserId());
				point.setLabel(pointList.get(i).getLabel());
				point.setX(pointList.get(i).getX());
				point.setY(pointList.get(i).getY());
				point.setProjectId(pointList.get(i).getProjectId());
				point.setMeasurePaperId(pointList.get(i).getMeasurePaperId());
				points.add(point);
			}
			if(pointService.insertOrUpdateBatch(points)){
				
				for(int j=0;j<pointList.size();j++){
					if(pointList.get(j).getMeasurePointId()==null){
						for(int x=0;x<pointList.get(j).getPointCheckList().size();x++){
							pointList.get(j).getPointCheckList().get(x).setMeasurePointId(points.get(j).getMeasurePointId());
						}
					}
				}
				List<PointCheckPO> checkList = new ArrayList<>();
				for(int j=0;j<pointList.size();j++){
					for(int x=0;x<pointList.get(j).getPointCheckList().size();x++){
						if(pointList.get(j).getPointCheckList().get(x).getStandardNum()!=null 
								&& pointList.get(j).getPointCheckList().get(x).getStandardNum()!=0){
							PointCheckPO test = new PointCheckPO();
							test.setJasoUserId(userInCache.getJasoUserId());
							test.setIsAdd(0);
							test.setErrorLowerLimit(pointList.get(j).getPointCheckList().get(x).getErrorLowerLimit());
							test.setErrorUpperLimit(pointList.get(j).getPointCheckList().get(x).getErrorUpperLimit());
							test.setCompanyId(userInCache.getCompanyId());
							test.setStandardNum(pointList.get(j).getPointCheckList().get(x).getStandardNum());
							test.setMeasurePointId(points.get(j).getMeasurePointId());
							test.setProjectCheckTypeId(pointList.get(j).getPointCheckList().get(x).getProjectCheckTypeId());
							test.setProjectId(points.get(j).getProjectId());
							checkList.add(test);
						}
					}
				}
				if(checkService.insertBatch(checkList)){
					List<Long> siteList = new ArrayList<>();
					siteList=measurePaperMapper.selectMeasureSiteIdList(points.get(0).getProjectId(),points.get(0).getMeasurePaperId(),points.get(0).getCompanyId());
					List<MeasureSitePointPO> sitePoints = new ArrayList<>();
					for(Long measureSiteId:siteList){
						for(int k=0;k<points.size();k++){
							for(PointCheckPO type:pointList.get(k).getPointCheckList()){
								MeasureSitePointPO sitepoint = new MeasureSitePointPO();
								sitepoint.setMeasureSiteId(measureSiteId);
								sitepoint.setCompanyId(userInCache.getCompanyId());
								sitepoint.setMeasurePointId(points.get(k).getMeasurePointId());
								sitepoint.setPointType(1);
								sitepoint.setProjectCheckTypeId(type.getProjectCheckTypeId());
								sitepoint.setProjectId(points.get(k).getProjectId());
								sitepoint.setSiteType(3);
								sitepoint.setStatus(1);
								sitePoints.add(sitepoint);
							}
						}
					}
					if(!sitePoints.isEmpty()){
						if(sitePointService.insertBatch(sitePoints)){
							MeasurePaperPO paper = new MeasurePaperPO();
							paper = selectById(pointList.get(0).getMeasurePaperId());
							paper.setMeasureNum(pointList.size());
							insertOrUpdate(paper);
						}
					}
				}
			}
		}
		
		return false;
	}

}
