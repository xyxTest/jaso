package com.yaj.jaso.business.measuresite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Resource;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yaj.common.base.pojo.MulSelect;
import com.yaj.common.base.service.ServiceMain;
import com.yaj.common.threadlocal.ThreadlocalManager;
import com.yaj.core.util.BeanUtil;
import com.yaj.jaso.business.apartment.entity.po.ApartmentPO;
import com.yaj.jaso.business.apartment.service.ApartmentService;
import com.yaj.jaso.business.jasouser.entity.po.JasoUserPO;
import com.yaj.jaso.business.jasouser.service.JasoUserService;
import com.yaj.jaso.business.measurepaper.entity.po.MeasurePaperPO;
import com.yaj.jaso.business.measurepaper.mapper.MeasurePaperMapper;
import com.yaj.jaso.business.measurepaper.service.MeasurePaperService;
import com.yaj.jaso.business.measurepoint.entity.po.MeasurePointPO;
import com.yaj.jaso.business.measurepoint.service.MeasurePointService;
import com.yaj.jaso.business.measureproblem.entity.po.MeasureProblemPO;
import com.yaj.jaso.business.measureproblem.service.MeasureProblemService;
import com.yaj.jaso.business.measurescreenlog.entity.po.MeasureScreenLogPO;
import com.yaj.jaso.business.measurescreenlog.service.MeasureScreenLogService;
import com.yaj.jaso.business.measuresite.entity.po.MeasureSitePO;
import com.yaj.jaso.business.measuresite.entity.vo.ApartmentInfo;
import com.yaj.jaso.business.measuresite.entity.vo.MeasureBuildingList;
import com.yaj.jaso.business.measuresite.entity.vo.MeasureCacheData;
import com.yaj.jaso.business.measuresite.entity.vo.MeasureFloorList;
import com.yaj.jaso.business.measuresite.entity.vo.MeasureRoomList;
import com.yaj.jaso.business.measuresite.entity.vo.MeasureScreenList;
import com.yaj.jaso.business.measuresite.entity.vo.MeasureSiteVo;
import com.yaj.jaso.business.measuresite.entity.vo.MeasureSiteWebResult;
import com.yaj.jaso.business.measuresite.entity.vo.PointAndCheckTypeList;
import com.yaj.jaso.business.measuresite.entity.vo.SiteCountNum;
import com.yaj.jaso.business.measuresite.mapper.MeasureSiteMapper;
import com.yaj.jaso.business.measuresitepoint.entity.po.MeasureSitePointPO;
import com.yaj.jaso.business.measuresitepoint.service.MeasureSitePointService;
import com.yaj.jaso.business.pointcheck.entity.po.PointCheckPO;
import com.yaj.jaso.business.pointcheck.entity.vo.PointCheckInput;
import com.yaj.jaso.business.pointcheck.service.PointCheckService;
import com.yaj.jaso.business.pointdatainputlog.entity.po.PointDataInputLogPO;
import com.yaj.jaso.business.pointdatainputlog.service.PointDataInputLogService;
import com.yaj.jaso.business.measuresite.entity.po.CountByPageMeasure;
import com.yaj.jaso.business.measuresite.entity.po.GetsResultMeasure;
import com.yaj.jaso.business.projectbuilding.entity.po.ProjectBuildingPO;
import com.yaj.jaso.business.projectbuilding.service.ProjectBuildingService;
import com.yaj.jaso.business.projectchecktype.entity.po.ProjectCheckTypePO;
import com.yaj.jaso.business.projectchecktype.service.ProjectCheckTypeService;
import com.yaj.jaso.global.PageVo;
import com.yaj.jaso.global.PageVoUtil;
import com.yaj.xyx.util.NetImageToBase64Util;

/*
 * @Description: 
 * @date: 2019-08-26
 */
@Service
public class MeasureSiteService extends ServiceImpl<MeasureSiteMapper, MeasureSitePO> {

    @Resource
    MeasureSiteMapper measureSiteMapper;
    @Autowired
    MeasureProblemService measureProblemService;
    @Autowired
    ServiceMain serviceMain;
    @Autowired
    MeasureScreenLogService screenLogService;
    @Autowired
    ProjectCheckTypeService checkTypeService;
    @Autowired
    MeasureSitePointService sitePointService;
    @Autowired
    MeasurePaperService paperService;
    @Autowired
    MeasurePointService pointService;
    @Autowired
    PointCheckService pointCheckService;
    @Autowired
    PointDataInputLogService inputLogService;
    @Autowired
    JasoUserService jasoUserService;
    @Autowired
    ProjectBuildingService buildingService;
    @Autowired
    ApartmentService apartmentService;
    public Object add( List<MeasureSitePO>  pos) {
		// TODO Auto-generated method stub
		JasoUserPO cacheUser = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		for(int i=0;i<pos.size();i++){
			pos.get(i).setCheckedStatus(1);
			pos.get(i).setCompanyId(cacheUser.getCompanyId());
			pos.get(i).setMajorType(1);
			pos.get(i).setMeasureSiteId(null);
		}
		return insertOrUpdateBatch(pos);
	}

	public Object deleteBatchByIds(List<MeasureSitePO> po) {
		for(int i=0;i<po.size();i++){
			po.get(i).setIfDelete(1);
		}
		// TODO Auto-generated method stub
		return updateBatchById(po);
	}

	public Object selectListByPage(MeasureSiteVo po) {
		// TODO Auto-generated method stub
		JasoUserPO cacheUser = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		Page<MeasureSitePO> page = new Page<MeasureSitePO>();
		page.setCurrent(po.getPageVo().getPageNo());
		page.setSize(po.getPageVo().getPageSize());
		Wrapper<MeasureSitePO> wrapper = new EntityWrapper<>();
		wrapper.eq("company_id", cacheUser.getCompanyId());
		wrapper.eq(po.getSiteType()!=null,"site_type", po.getSiteType());
		wrapper.eq("checked_status", 1);
		wrapper.like(po.getMeasureSiteName()!=null,"measure_site_name", po.getMeasureSiteName());
		wrapper.orderDesc(Arrays.asList("create_time"));
		page=selectPage(page, wrapper);
		return PageVoUtil.setPage(page);
	}

	public Object selectLists(MeasureSitePO po) {
		JasoUserPO cacheUser = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		Wrapper<MeasureSitePO> wrapper = new EntityWrapper<>();
		wrapper.eq(po.getSiteType()!=null,"site_type", po.getSiteType());
		wrapper.eq(po.getCheckedStatus()!=null,"checked_status", po.getCheckedStatus());
		wrapper.eq("project_id", po.getProjectId());
		wrapper.eq("company_id", cacheUser.getCompanyId());
		wrapper.like(po.getMeasureSiteName()!=null,"measure_site_name", po.getMeasureSiteName());
		wrapper.orderDesc(Arrays.asList("create_time"));
		return selectList(wrapper);
	}

	public Object selectPapersList(MeasureSitePO po) {
		// TODO Auto-generated method stub
		JasoUserPO cacheUser = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		MulSelect mul = MulSelect.newInstance("${1}.paperUrl", new MeasureSitePO(), new MeasurePaperPO());
		mul.where("${measure_site}")
			.eq(po.getProjectId()!=null,"projectId", po.getProjectId())
			.eq("companyId", cacheUser.getCompanyId())
			.notEq("siteType", 1);
		return serviceMain.mulSelect(mul);
	}
	public Object selectListByPid(MeasureSitePO company) {
			// TODO Auto-generated method stub
		List<MeasureSiteWebResult> resultList = new ArrayList<>();
		/////////*获取项目下的所有的测点记录*//////////
		List<PointDataInputLogPO> pointDataList = new ArrayList<>();
		PointDataInputLogPO pointLogPO = new PointDataInputLogPO();
		pointLogPO.setProjectId(company.getProjectId());
		pointDataList=inputLogService.selectLists(pointLogPO);
		/*获取项目下的所有测量位置-测点-检查项关系表*/
		List<MeasureSitePointPO> sitePointList = new ArrayList<>();
		MeasureSitePointPO sitePointPO = new MeasureSitePointPO();
		sitePointPO.setProjectId(company.getProjectId());
		sitePointList = sitePointService.selectLists(sitePointPO);
		List<MeasureSitePO> result = new ArrayList<>();
		result=measureSiteMapper.selectListTreeByPid(company.getPid());
		for(int i=0;i<result.size();i++){
			MeasureSiteWebResult item = new MeasureSiteWebResult();
			item.setJasoUserId(result.get(i).getJasoUserId());
			item.setMajorType(result.get(i).getMajorType());
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			item.setMeasurePaperId(result.get(i).getApartmentId());
			item.setMeasureSiteId(result.get(i).getMeasureSiteId());
			item.setMeasureSiteName(result.get(i).getMeasureSiteName());
			item.setCreateTime(result.get(i).getCreateTime());
			if(result.get(i).getJasoUserId()!=null){
				JasoUserPO user = new JasoUserPO();
				user=jasoUserService.selectById(result.get(i).getJasoUserId());
				item.setUserRealName(user.getUserRealName());
			}
			//总测点获取
			List<MeasureSitePointPO> news = new ArrayList<>();
			for(int j=0;j<sitePointList.size();j++){
				if(sitePointList.get(j).getMeasureSiteId().equals(result.get(i).getMeasureSiteId())){
					news.add(sitePointList.get(j));
				}
			}
			List<MeasureSitePointPO> noRepeat= new ArrayList<>();
			noRepeat=getNoRepeatByPoint(news);
			item.setAllNum(noRepeat.size());
			//已测点获取
			List<PointDataInputLogPO> pointDataListNews = new ArrayList<>();
			List<PointDataInputLogPO> pointDataListNew = new ArrayList<>();
			pointDataListNew=getPointDataInputLogGroupByPoint(pointDataList);
			for(int k=0;k<pointDataListNew.size();k++){
				if(pointDataListNew.get(k).getMeasureSiteId().equals(result.get(i).getMeasureSiteId())){
					pointDataListNews.add(pointDataListNew.get(k));
				}
			}
			item.setDoneNum(pointDataListNews.size());
			item.setMeasureNum(pointDataListNews.size()+"/"+noRepeat.size());
			resultList.add(item);
		}
		return resultList;
	}
	@Async
	public List<MeasurePaperPO> getPaperList(MeasureSitePO site){
		List<MeasurePaperPO> paperList = new ArrayList<>();
		MeasurePaperPO paper = new MeasurePaperPO();
		paper.setProjectId(site.getProjectId());
		paperList=paperService.selectList(paper);
		for(int i=0;i<paperList.size();i++){
			if(paperList.get(i).getPaperUrl()!=null){
				String strBase64 = NetImageToBase64Util.NetImageToBase64("http://localhost:8085"+paperList.get(i).getPaperUrl());
				paperList.get(i).setPaperUrl(strBase64);
			}
		}
		return paperList;
	}
	@Async
	public List<ProjectCheckTypePO> getProjectCheckTypeList(MeasureSitePO site){
		List<ProjectCheckTypePO> checkTypeList = new ArrayList<>();
		ProjectCheckTypePO checkTypePO = new ProjectCheckTypePO();
		checkTypePO.setCheckType(1);//实测实量
		checkTypeList=checkTypeService.selectLists(checkTypePO);
		return checkTypeList;
	}
	public Object getCacheData(MeasureSitePO company) {
		// TODO Auto-generated method stub
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		MeasureCacheData measureCacheData = new MeasureCacheData();
		/////////*所有的检查项获取*//////////
		new Thread(){
			public void run(){
				List<ProjectCheckTypePO> checkTypeList = new ArrayList<>();
				checkTypeList=getProjectCheckTypeList(company);
				measureCacheData.setCheckTypeList(checkTypeList);		
			}
		}.start();
		/////////*获取项目下的所有的测点记录*//////////
		List<PointDataInputLogPO> pointDataList = new ArrayList<>();
		PointDataInputLogPO pointLogPO = new PointDataInputLogPO();
		pointLogPO.setProjectId(company.getProjectId());
		pointDataList=inputLogService.selectLists(pointLogPO);
		////////*获取所有的户型图纸*//////////
		new Thread(){
			public void run(){
				List<MeasurePaperPO> paperList = new ArrayList<>();
				paperList=getPaperList(company);
				measureCacheData.setMeasurePaperList(paperList);
			}
		}.start();
		///////*获取该项目的所有测点及测点下的检查项*////////////
		/*获取项目下的所有测点数据*/
		List<MeasurePointPO> pointList = new ArrayList<>();
		MeasurePointPO pointPO = new MeasurePointPO();
		pointPO.setProjectId(company.getProjectId());
		pointList = pointService.selectList(pointPO);
		/*获取项目下的所有测点-检查项关系表*/
		List<PointCheckPO> pointCheckList = new ArrayList<>();
		PointCheckPO checkPO = new PointCheckPO();
		checkPO.setProjectId(company.getProjectId());
		pointCheckList=pointCheckService.selectLists(checkPO);
		/*获取项目下的所有测量位置-测点-检查项关系表*/
		List<MeasureSitePointPO> sitePointList = new ArrayList<>();
		MeasureSitePointPO sitePointPO = new MeasureSitePointPO();
		sitePointPO.setProjectId(company.getProjectId());
		sitePointList = sitePointService.selectLists(sitePointPO);		
		MeasureScreenList measureScreenHeader = new MeasureScreenList();
		List<MeasureBuildingList> headBuildingInfoList = new ArrayList<>(); 
		List<MeasureSitePointPO> allPointList = new ArrayList<>();
		allPointList=getNoRepeatByPoint(sitePointList);
		List<MeasureSitePointPO> problemPointList = new ArrayList<>();//未通过测点id、位置id去重的问题点位总数
		for(int r=0;r<sitePointList.size();r++){
			if(sitePointList.get(r).getStatus()==2){
				problemPointList.add(sitePointList.get(r));
			}
		}
		///归类整合
		List<PointAndCheckTypeList> pointAndCheckTypeList = new ArrayList<>();
		for(int i=0;i<allPointList.size();i++){
			PointAndCheckTypeList pointAndCheckType = new PointAndCheckTypeList();
			pointAndCheckType.setMeasureSitePointId(allPointList.get(i).getMeasureSitePointId());
			for(int s=0;s<pointList.size();s++){
				if(pointList.get(s).getMeasurePointId().equals(allPointList.get(i).getMeasurePointId())){
					pointAndCheckType.setMeasurePaperId(pointList.get(s).getMeasurePaperId());
					pointAndCheckType.setMeasurePointId(pointList.get(s).getMeasurePointId());
					pointAndCheckType.setLabel(pointList.get(s).getLabel());
					pointAndCheckType.setX(pointList.get(s).getX());
					pointAndCheckType.setY(pointList.get(s).getY());
					//对比总的点位、位置、检查项数据
					boolean isPass = true;
					for(int q=0;q<sitePointList.size();q++){
						if(sitePointList.get(q).getMeasurePointId().equals(allPointList.get(i).getMeasurePointId()) 
								&& sitePointList.get(q).getMeasureSiteId().equals(allPointList.get(i).getMeasureSiteId())){
							if(sitePointList.get(q).getStatus()==2){
								isPass=false;
							}
						}
					}
					if(isPass){
						pointAndCheckType.setPointStatus(1);
						
					}else{
						pointAndCheckType.setPointStatus(2);
					}
					pointAndCheckType.setProjectId(pointList.get(s).getProjectId());
					pointAndCheckType.setMeasureSiteId(allPointList.get(i).getMeasureSiteId());
				}
			}
			List<PointCheckInput> pointCheckLists = new ArrayList<>();
			for(int j=0;j<pointCheckList.size();j++){
				PointCheckInput checkItem = new PointCheckInput();
				if(allPointList.get(i).getMeasurePointId().equals(pointCheckList.get(j).getMeasurePointId())){
					checkItem.setErrorLowerLimit(pointCheckList.get(j).getErrorLowerLimit());
					checkItem.setErrorUpperLimit(pointCheckList.get(j).getErrorUpperLimit());
					checkItem.setIsAdd(pointCheckList.get(j).getIsAdd());
					checkItem.setMeasurePointId(pointCheckList.get(j).getMeasurePointId());
					checkItem.setMeasureSiteId(allPointList.get(i).getMeasureSiteId());
					checkItem.setPointCheckId(pointCheckList.get(j).getPointCheckId());
					checkItem.setIsSave(pointCheckList.get(j).getIsSave());
					checkItem.setProjectCheckTypeId(pointCheckList.get(j).getProjectCheckTypeId());
					checkItem.setProjectId(pointCheckList.get(j).getProjectId());
					checkItem.setStandardNum(pointCheckList.get(j).getStandardNum());
					//BeanUtil.copy(checkItem, pointCheckList.get(j));
					List<PointDataInputLogPO> logList = new ArrayList<>();
					for(int q=0;q<pointDataList.size();q++){
						if(pointDataList.get(q).getProjectCheckTypeId().equals(pointCheckList.get(j).getProjectCheckTypeId())
								&& pointDataList.get(q).getMeasurePointId().equals(allPointList.get(i).getMeasurePointId())
								&& pointDataList.get(q).getMeasureSiteId().equals(allPointList.get(i).getMeasureSiteId())){
							logList.add(pointDataList.get(q));
						}
					}
					checkItem.setDataLogList(logList);
					pointCheckLists.add(checkItem);
				}
			}
			pointAndCheckType.setPointCheckList(pointCheckLists);
			pointAndCheckTypeList.add(pointAndCheckType);
		}
		measureCacheData.setPointList(pointAndCheckTypeList);
		/////////*获取过滤入口的筛选记录*///////////
		List<MeasureScreenList> measureFilterDataList = new ArrayList<>();
		List<MeasureScreenLogPO> screenLogList = new ArrayList<>();
		MeasureScreenLogPO screenLogPO = new MeasureScreenLogPO();
		screenLogPO.setProjectId(company.getProjectId());
		screenLogList = screenLogService.selectList(screenLogPO);
		
		
		/*获取该项目的所有区域信息*/
		List<MeasureSitePO> measureSiteList = new ArrayList<>();
		Wrapper<MeasureSitePO> wrapper = new EntityWrapper<>();
		wrapper.eq("company_id", userInCache.getCompanyId());
		wrapper.eq("project_id", company.getProjectId());
		measureSiteList=selectList(wrapper);
		
		List<MeasureSitePO> measureSiteLists = new ArrayList<>();
		Wrapper<MeasureSitePO> wrapper2 = new EntityWrapper<>();
		wrapper2.eq("company_id", userInCache.getCompanyId());
		wrapper2.eq("project_id", company.getProjectId());
		//wrapper.eq("checked_status", 1);
		measureSiteLists=selectList(wrapper2);
		////////
		measureScreenHeader.setMeasureScreenLogName("实测实量");
		measureScreenHeader.setAllNum(allPointList.size());
		measureScreenHeader.setProblemNum(getNoRepeatByPoint(problemPointList).size());
		headBuildingInfoList=getMeasureBuildingList(measureSiteList,sitePointList,pointDataList,problemPointList,pointList,measureSiteLists);
		measureScreenHeader.setBuildingInfoList(headBuildingInfoList);
		///////////////获取已测点数
		List<PointDataInputLogPO> pointDataListGroupByPoint = new ArrayList<>();
		pointDataListGroupByPoint=getPointDataInputLogGroupByPoint(pointDataList);
		measureScreenHeader.setDoneNum(pointDataListGroupByPoint.size());
		measureFilterDataList.add(measureScreenHeader);
		/////////////////
		/////////////////
		for(int k=0;k<screenLogList.size();k++){
			MeasureScreenList measureScreen = new MeasureScreenList();
			BeanUtil.copy(screenLogList.get(k), measureScreen);
			int allNum=0;
			int doneNum=0;
			int problemNum=0;
			/*统计测点总数、爆点点数、已测点数*/
			List<MeasureSitePointPO> sitePointListGroupByPoint = new ArrayList<>();//通过检查项获取的所有测量点
			List<MeasureSitePointPO> checkProblemPointList = new ArrayList<>();//问题点list
			List<PointDataInputLogPO> checkPointDataInputLogList = new ArrayList<>();
			int isAnd = 0;
			if(screenLogList.get(k).getProjectCheckTypeId()!=null){//通过检查项过滤获取该项目下的所有测点
				isAnd=1;
				checkPointDataInputLogList=getPointDataInputLogGroupByPointAndCheck(pointDataList,screenLogList.get(k).getProjectCheckTypeId());
				sitePointListGroupByPoint=getNoRepeatByCheckTypeId(sitePointList,screenLogList.get(k).getProjectCheckTypeId());
				for(int x=0;x<problemPointList.size();x++){
					for(int y=0;y<sitePointListGroupByPoint.size();y++){
						if(problemPointList.get(x).getMeasurePointId().equals(sitePointListGroupByPoint.get(y).getMeasurePointId())
								&& problemPointList.get(x).getMeasureSiteId().equals(sitePointListGroupByPoint.get(y).getMeasureSiteId())){
							if(!checkProblemPointList.contains(sitePointListGroupByPoint.get(y))){
								checkProblemPointList.add(sitePointListGroupByPoint.get(y));
							}
						}
					}
				}
			}
			List<MeasureSitePointPO> buildingPointListGroupByPoints = new ArrayList<>();
			List<MeasureSitePointPO> buildingIdProblemPointList = new ArrayList<>();//问题点list
			List<PointDataInputLogPO> buildingIdPointDataInputLogList = new ArrayList<>();
			if(screenLogList.get(k).getMeasureSiteId()!=null){//通过楼栋号过滤该项目下的所有测点
				List<MeasureSitePO> roomList = new ArrayList<>();//所有房间
				Long siteId=null;
				for(int y=0;y<measureSiteList.size();y++){
					if(screenLogList.get(k).getMeasureSiteId().equals(measureSiteList.get(y).getMeasureSiteId())){
						siteId=measureSiteList.get(y).getProjectBuildingId();
					}
				}
				roomList=getMeasureSiteListByType(measureSiteList,siteId,measureSiteLists);
				//////////////结合检查项搜索
				if(isAnd==0){
					// 通过比对房间id计数
					List<MeasureSitePointPO> allPoint=getNoRepeatByPoint(sitePointList);
					List<MeasureSitePointPO> roomPoints = new ArrayList<>();
					for(int i=0;i<allPoint.size();i++){
						boolean status = false;
						for(int j=0;j<roomList.size();j++){
							if(allPoint.get(i).getMeasureSiteId().equals(roomList.get(j).getMeasureSiteId())){
								status=true;
							}
						}
						if(status){
							roomPoints.add(allPoint.get(i));
						}
					}
					List<MeasureSitePointPO> noRepeatPoints = new ArrayList<>();
					noRepeatPoints=getNoRepeatByPoint(roomPoints);
					allNum=noRepeatPoints.size();
					for(int x=0;x<problemPointList.size();x++){
						boolean sta=false;
						for(int y=0;y<noRepeatPoints.size();y++){
							if(problemPointList.get(x).getMeasurePointId().equals(noRepeatPoints.get(y).getMeasurePointId())
									&& problemPointList.get(x).getMeasureSiteId().equals(noRepeatPoints.get(y).getMeasureSiteId())){
								sta=true;
							}
						}
						if(sta){
							buildingIdProblemPointList.add(problemPointList.get(x));
						}
					}
					problemNum=buildingIdProblemPointList.size();
					for(int q=0;q<pointDataListGroupByPoint.size();q++){
						boolean sta=false;
						for(int y=0;y<roomPoints.size();y++){
							if(pointDataListGroupByPoint.get(q).getMeasureSiteId().equals(roomPoints.get(y).getMeasureSiteId())){
								sta=true;
							}
						}
						if(sta){
							buildingIdPointDataInputLogList.add(pointDataListGroupByPoint.get(q));
						}
					}
					doneNum=buildingIdPointDataInputLogList.size();
				}
				
				if(sitePointListGroupByPoint.isEmpty()){//检查项条件为空，根据楼栋号搜索
					
				}else{//检查项条件不为空，根据楼栋号搜索
					for(int g=0;g<sitePointListGroupByPoint.size();g++){
						boolean inputState = false;
						for(int q=0;q<roomList.size();q++){
							if(sitePointListGroupByPoint.get(g).getMeasureSiteId().equals(roomList.get(q).getMeasureSiteId())){
								inputState = true;
							}
						}
						if(inputState){
							buildingPointListGroupByPoints.add(sitePointListGroupByPoint.get(g));
						}
					}
					for(int x=0;x<problemPointList.size();x++){
						boolean sta=false;
						for(int y=0;y<buildingPointListGroupByPoints.size();y++){
							if(problemPointList.get(x).getMeasurePointId().equals(buildingPointListGroupByPoints.get(y).getMeasurePointId()) 
									&& problemPointList.get(x).getMeasureSiteId().equals(buildingPointListGroupByPoints.get(y).getMeasureSiteId())){
								sta=true;
							}
						}
						if(sta){
							buildingIdProblemPointList.add(problemPointList.get(x));
						}
					}
					for(int w=0;w<checkPointDataInputLogList.size();w++){
						boolean sta=false;
						for(int z=0;z<roomList.size();z++){
							if(checkPointDataInputLogList.get(w).getMeasureSiteId().equals(roomList.get(z).getMeasureSiteId())){
								sta=true;
							}
						}
						if(sta){
							buildingIdPointDataInputLogList.add(checkPointDataInputLogList.get(w));
						}
					}
					allNum=buildingPointListGroupByPoints.size();
					problemNum=buildingIdProblemPointList.size();
					doneNum=buildingIdPointDataInputLogList.size();
				}
			}else{
				problemPointList=checkProblemPointList;
				allNum=sitePointListGroupByPoint.size();
				problemNum=checkProblemPointList.size();
				doneNum=checkPointDataInputLogList.size();
			}
			measureScreen.setMeasureScreenLogName(screenLogList.get(k).getMeasureScreenLogName());
			measureScreen.setAllNum(allNum);
			measureScreen.setProblemNum(problemNum);
			measureScreen.setDoneNum(doneNum);
			List<MeasureBuildingList> buildingInfoList = new ArrayList<>();
			buildingInfoList=getMeasureBuildingListByScreen(screenLogList.get(k),measureScreen,measureSiteList,sitePointList,pointDataList,problemPointList,pointList,measureSiteLists);
			measureScreen.setBuildingInfoList(buildingInfoList);
			//通过楼栋id、检查项ids筛选搜索测点的爆点点数
			measureFilterDataList.add(measureScreen);
		}
		measureCacheData.setMeasureFilterDataList(measureFilterDataList);
		return measureCacheData;
	}
	////////////////////////////////////////////按楼栋号统计
	public List<MeasureBuildingList> getMeasureBuildingListByScreen(MeasureScreenLogPO screenLogPO,MeasureScreenList measureScreen,
			List<MeasureSitePO> measureSiteList,List<MeasureSitePointPO> sitePointList,List<PointDataInputLogPO> logList,
			List<MeasureSitePointPO> measurePointProblemList,List<MeasurePointPO> pointList,List<MeasureSitePO> measureSiteLists){
		List<MeasureBuildingList> result = new ArrayList<>();
		if(screenLogPO.getMeasureSiteId()!=null && screenLogPO.getProjectCheckTypeId()==null){
			MeasureBuildingList test = new MeasureBuildingList();
			test.setAllNum(measureScreen.getAllNum());
			test.setDoneNum(measureScreen.getDoneNum());
			test.setProblemNum(measureScreen.getProblemNum());
			test.setMeasureSiteId(screenLogPO.getMeasureSiteId());
			Long floorId=null;
			for(int i=0;i<measureSiteList.size();i++){
				if(measureSiteList.get(i).getMeasureSiteId().equals(screenLogPO.getMeasureSiteId())){
					test.setMeasureSiteName(measureSiteList.get(i).getMeasureSiteName());
					floorId=measureSiteList.get(i).getProjectBuildingId();
				}
			}
			List<MeasureFloorList> floorList = new ArrayList<>();
			floorList=getMeasureFloorList(measureSiteList,floorId,sitePointList,logList,measurePointProblemList,pointList,measureSiteLists);
			test.setMeasureFloorInfo(floorList);
			result.add(test);
		}
		//
		if(screenLogPO.getProjectCheckTypeId()!=null && screenLogPO.getMeasureSiteId()==null){
			List<MeasureSitePointPO> sitePoints = new ArrayList<>();
			sitePoints=getNoRepeatByCheckTypeId(sitePointList,screenLogPO.getProjectCheckTypeId());
			List<PointDataInputLogPO> logs = new ArrayList<>();
			List<PointDataInputLogPO> logs2 = new ArrayList<>();
			logs = getPointDataInputLogGroupByPointAndCheck(logList,screenLogPO.getProjectCheckTypeId());
			for(int k=0;k<logs.size();k++){
				boolean flag2 = false;
				{for(int m=0;m<sitePoints.size();m++)
					if(sitePoints.get(m).getMeasurePointId().equals(logs.get(k).getMeasurePointId()))
						flag2 = true;
				}
				if(flag2){
					logs2.add(logs.get(k));
				}
			}
			result=getMeasureBuildingList(measureSiteList,sitePoints,logs2,measurePointProblemList,pointList,measureSiteLists);
		}
		if(screenLogPO.getProjectCheckTypeId()!=null && screenLogPO.getMeasureSiteId()!=null){
			String[] checkids = screenLogPO.getProjectCheckTypeId().split(",");
			List<MeasureSitePO> siteList = new ArrayList<>();//根据楼栋号获取所有的房间号
			Long buildingId=(long) 0;
			for(int i=0;i<measureSiteList.size();i++){
				if(screenLogPO.getMeasureSiteId().equals(measureSiteList.get(i).getMeasureSiteId())){
					buildingId=measureSiteList.get(i).getProjectBuildingId();
				}
			}
			siteList=getMeasureSiteListByType(measureSiteList,buildingId,measureSiteLists);
			//根据检查项获取所有房间号的所有测点
			List<MeasureSitePointPO> allpointsRepeat = new ArrayList<>();
			List<MeasureSitePointPO> allpoints = new ArrayList<>();
			for(int i=0;i<sitePointList.size();i++){
				for(int j=0;j<siteList.size();j++){
					if(siteList.get(j).getMeasureSiteId().equals(sitePointList.get(i).getMeasureSiteId())){
						allpointsRepeat.add(sitePointList.get(i));
					}
				}
			}
			for(int t=0;t<allpointsRepeat.size();t++){
				for(int k=0;k<checkids.length;k++){
					Long checkid=Long.valueOf(checkids[k]);
					if(allpointsRepeat.get(t).getProjectCheckTypeId().equals(checkid)){
						allpoints.add(allpointsRepeat.get(t));
					}
				}
			}
			//根据检查项获取所有房间号的所有爆点
			List<MeasureSitePointPO> problems = new ArrayList<>();
			for(int h=0;h<allpoints.size();h++){
				boolean flag=false;
				for(int q=0;q<measurePointProblemList.size();q++){
					if(measurePointProblemList.get(q).getMeasurePointId().equals(allpoints.get(h).getMeasurePointId())){
						flag=true;
					}
				}
				if(flag){
					problems.add(allpoints.get(h));
				}
			}
			//根据检查项获取所有房间号的所有已测点位
			List<PointDataInputLogPO> dones = new ArrayList<>();
			List<PointDataInputLogPO> logs = new ArrayList<>();
			logs=getPointDataInputLogGroupByPoint(logList);
			for(int q=0;q<logs.size();q++){
				boolean flag=false;
				for(int h=0;h<allpoints.size();h++){
					if(logs.get(q).getMeasurePointId().equals(allpoints.get(h).getMeasurePointId())){
						flag=true;
					}
				}
				if(flag){
					dones.add(logs.get(q));
				}
			}
			MeasureBuildingList test = new MeasureBuildingList();
			test.setAllNum(allpoints.size());
			test.setDoneNum(dones.size());
			test.setProblemNum(problems.size());
			test.setMeasureSiteId(screenLogPO.getMeasureSiteId());
			MeasureSitePO get = new MeasureSitePO();
			for(int i=0;i<measureSiteList.size();i++){
				if(measureSiteList.get(i).getMeasureSiteId().equals(screenLogPO.getMeasureSiteId())){
					get=measureSiteList.get(i);
					test.setMeasureSiteName(measureSiteList.get(i).getMeasureSiteName());
				}
			}
			test.setMeasureSiteId(screenLogPO.getMeasureSiteId());
			List<MeasureFloorList> floorList = new ArrayList<>();
			floorList=getMeasureFloorList(measureSiteList,get.getProjectBuildingId(),allpoints,dones,problems,pointList,measureSiteLists);
			test.setMeasureFloorInfo(floorList);
			result.add(test);
		}
		return result;
	}
	
	///获取第一层List信息（楼栋基本信息和统计数据）
	public List<MeasureBuildingList> getMeasureBuildingList(List<MeasureSitePO> measureSiteList,List<MeasureSitePointPO> sitePointList,
			List<PointDataInputLogPO> logList,List<MeasureSitePointPO> measurePointProblemList,List<MeasurePointPO> pointList,List<MeasureSitePO> measureSiteLists){
		//获取该楼栋下面的所有房间号
		List<MeasureBuildingList> result = new ArrayList<>();
		for(int i=0;i<measureSiteList.size();i++){
			if(measureSiteList.get(i).getSiteType()==1){
				MeasureBuildingList test = new MeasureBuildingList();
				test.setMeasureSiteId(measureSiteList.get(i).getMeasureSiteId());
				test.setMeasureSiteName(measureSiteList.get(i).getMeasureSiteName());
				///
				List<MeasureSitePO> rooms=new ArrayList<>();//根据楼栋号获取当前房间号的总数
				rooms=getMeasureSiteListByType(measureSiteList,measureSiteList.get(i).getProjectBuildingId(),measureSiteLists);
				
				//获取当前的总点数
				List<MeasureSitePointPO> newSitePointList= new ArrayList<>();
				for(int j=0;j<sitePointList.size();j++){
					for(int k=0;k<rooms.size();k++){
						if(sitePointList.get(j).getMeasureSiteId().equals(rooms.get(k).getMeasureSiteId())){
							newSitePointList.add(sitePointList.get(j));
						}
					}
				}
				test.setAllNum(getNoRepeatByPoint(newSitePointList).size());
				//已侧点数
				List<PointDataInputLogPO> newPointDataInputLogList = new ArrayList<>();
				for(int m=0;m<logList.size();m++){
					for(int n=0;n<rooms.size();n++){
						if(logList.get(m).getMeasureSiteId().equals(rooms.get(n).getMeasureSiteId())){
							newPointDataInputLogList.add(logList.get(m));
						}
					}
				}
				test.setDoneNum(getPointDataInputLogGroupByPoint(newPointDataInputLogList).size());
				//问题点数
				List<MeasureSitePointPO> news = new ArrayList<>();
				for(int x=0;x<measurePointProblemList.size();x++){
					for(int y=0;y<rooms.size();y++){
						if(measurePointProblemList.get(x).getMeasureSiteId().equals(rooms.get(y).getMeasureSiteId())){
							news.add(measurePointProblemList.get(x));
						}
					}
				}
				test.setProblemNum(news.size());
				List<MeasureFloorList> measureFloorInfo = new ArrayList<>();
				measureFloorInfo=getMeasureFloorList(measureSiteList,measureSiteList.get(i).getProjectBuildingId(),sitePointList,logList,measurePointProblemList,pointList,measureSiteLists);
				test.setMeasureFloorInfo(measureFloorInfo);
				if(test.getAllNum()==null){
					test.setAllNum(0);
				}
				if(test.getDoneNum()==null){
					test.setDoneNum(0);
				}
				if(test.getProblemNum()==null){
					test.setProblemNum(0);
				}
				result.add(test);
			}
		}
		
		return result;
	}
	//获取第二层List
	public List<MeasureFloorList> getMeasureFloorList(List<MeasureSitePO> measureSiteList,
			Long buildingId,List<MeasureSitePointPO> sitePointList,List<PointDataInputLogPO> logList,
			List<MeasureSitePointPO> measurePointProblemList,List<MeasurePointPO> pointList,List<MeasureSitePO> measureSiteLists){
		List<MeasureFloorList> floors = new ArrayList<>();
		for(int i=0;i<measureSiteLists.size();i++){
			if(measureSiteLists.get(i).getSiteType()==2 && buildingId.equals(measureSiteLists.get(i).getPid()) && measureSiteLists.get(i).getFromProjectBuilding()==1){
				MeasureFloorList floor = new MeasureFloorList();
				List<MeasureRoomList> measureRoomInfo = new ArrayList<>();
				floor.setMeasureSiteId(measureSiteLists.get(i).getMeasureSiteId());
				floor.setMeasureSiteName(measureSiteLists.get(i).getMeasureSiteName());
				measureRoomInfo=getMeasureRoomList(measureSiteList,measureSiteLists.get(i).getProjectBuildingId(),sitePointList,logList,measurePointProblemList,pointList,measureSiteLists);
				floor.setMeasureRoomInfo(measureRoomInfo);
				floors.add(floor);
			}
		}
		return floors;
	}
	//获取第三层List
	public List<MeasureRoomList> getMeasureRoomList(List<MeasureSitePO> measureSiteList,Long floorId,
			List<MeasureSitePointPO> sitePointList,List<PointDataInputLogPO> logList,
			List<MeasureSitePointPO> measurePointProblemList,List<MeasurePointPO> pointList,List<MeasureSitePO> measureSiteLists){
		List<MeasureRoomList> measureRoomList = new ArrayList<>();
		List<MeasureSitePO> newSites = new ArrayList<>();
		for(int l=0;l<measureSiteList.size();l++){
			if(measureSiteList.get(l).getSiteType()==3 && floorId.equals(measureSiteList.get(l).getPid())){
				newSites.add(measureSiteList.get(l));
			}
		}
		for(int k=0;k<newSites.size();k++){
			MeasureRoomList room = new MeasureRoomList();
			room.setMeasureSiteId(newSites.get(k).getMeasureSiteId());
			/////////////////////////////////////////////////////////////////////
			room.setMeasurePaperIds(newSites.get(k).getApartmentId());
			room.setApartmentId(newSites.get(k).getApartmentId());
			room.setMeasureSiteName(newSites.get(k).getMeasureSiteName());
			List<MeasurePointPO> measureRoomPointInfo = new ArrayList<>();
			measureRoomPointInfo=getMeasurePointPOList(newSites.get(k).getMeasureSiteId(),sitePointList,pointList);
			room.setMeasureRoomPointInfo(measureRoomPointInfo);
			//总测点数
			//获取当前的总点数
			List<MeasureSitePointPO> newSitePointList= new ArrayList<>();
			for(int j=0;j<sitePointList.size();j++){
				if(sitePointList.get(j).getMeasureSiteId().equals(newSites.get(k).getMeasureSiteId())){
					newSitePointList.add(sitePointList.get(j));
				}
			}
			List<MeasureSitePointPO> newSitePointList2 = new ArrayList<>();
			newSitePointList2=getNoRepeatByPoint(newSitePointList);
			room.setAllNum(newSitePointList2.size());
			//已侧点数
			List<PointDataInputLogPO> newPointDataInputLogList = new ArrayList<>();
			for(int m=0;m<logList.size();m++){
				if(logList.get(m).getMeasureSiteId().equals(newSites.get(k).getMeasureSiteId())){
					newPointDataInputLogList.add(logList.get(m));
				}
			}
			room.setDoneNum(getPointDataInputLogGroupByPoint(newPointDataInputLogList).size());
			//问题点数
			List<MeasureSitePointPO> news = new ArrayList<>();
			for(int x=0;x<measurePointProblemList.size();x++){
				for(int h=0;h<newSitePointList2.size();h++){
					if(measurePointProblemList.get(x).getMeasureSiteId().equals(newSitePointList2.get(h).getMeasureSiteId()) 
							&& measurePointProblemList.get(x).getMeasurePointId().equals(newSitePointList2.get(h).getMeasurePointId())){
						news.add(measurePointProblemList.get(x));
					}
				}
				
			}
			room.setProblemNum(getNoRepeatByPoint(news).size());
			if(room.getAllNum()==null){
				room.setAllNum(0);
			}
			if(room.getDoneNum()==null){
				room.setDoneNum(0);
			}
			if(room.getProblemNum()==null){
				room.setProblemNum(0);
			}
			measureRoomList.add(room);
			
		}
		return measureRoomList;
	}
	//获取第四层List
	public List<MeasurePointPO> getMeasurePointPOList(Long roomId,List<MeasureSitePointPO> sitePointList,List<MeasurePointPO> pointList){
		List<MeasurePointPO> result = new ArrayList<>();
		List<MeasureSitePointPO> news = new ArrayList<>();
		for(int i=0;i<sitePointList.size();i++){
			if(sitePointList.get(i).getMeasureSiteId().equals(roomId)){
				news.add(sitePointList.get(i));
			}
		}
		List<MeasureSitePointPO> noRepeat= new ArrayList<>();
		noRepeat=getNoRepeatByPoint(news);
		for(int j=0;j<pointList.size();j++){
			for(int k=0;k<noRepeat.size();k++){
				if(pointList.get(j).getMeasurePointId().equals(noRepeat.get(k).getMeasurePointId())){
					result.add(pointList.get(j));
				}
			}
			
		}
		return result;
	}
	public List<PointDataInputLogPO> getPointDataInputLogGroupByPoint(List<PointDataInputLogPO> allList){
		List<PointDataInputLogPO> pointDataListGroupByPoint = new ArrayList<>();
		for(int n=0;n<allList.size();n++){
			if(pointDataListGroupByPoint.isEmpty()){
				pointDataListGroupByPoint.add(allList.get(n));
			}else{
				boolean flag = true;
				for(int s=0;s<pointDataListGroupByPoint.size();s++){
					if(allList.get(n).getMeasurePointId().equals(pointDataListGroupByPoint.get(s).getMeasurePointId()) && allList.get(n).getMeasureSiteId().equals(pointDataListGroupByPoint.get(s).getMeasureSiteId())){
						flag=false;
					}
				}
				if(flag){
					pointDataListGroupByPoint.add(allList.get(n));
				}
			}
		}
		return pointDataListGroupByPoint;
	}
	public List<PointDataInputLogPO> getPointDataInputLogGroupByPointAndCheck(List<PointDataInputLogPO> allList,String checkTypeId){
		List<PointDataInputLogPO> sitePointListGroupByPoint = new ArrayList<>();
		List<PointDataInputLogPO> sitePointListByFilter = new ArrayList<>();
		String[] checkTypeIds = checkTypeId.split(",");
		for(int t=0;t<allList.size();t++){
			boolean inputFlag = false;
			for(int ti=0;ti<checkTypeIds.length;ti++){
				Long ids = Long.valueOf(checkTypeIds[ti]);
				if(allList.get(t).getProjectCheckTypeId().equals(ids)){
					inputFlag=true;
				}
			}
			if(inputFlag){
				sitePointListByFilter.add(allList.get(t));
			}
		}
		/*过滤有相同测点的关系记录*/
		for(int n=0;n<sitePointListByFilter.size();n++){
			if(sitePointListGroupByPoint.isEmpty()){
				sitePointListGroupByPoint.add(sitePointListByFilter.get(n));
			}else{
				boolean flag = true;
				for(int s=0;s<sitePointListGroupByPoint.size();s++){
					if(sitePointListByFilter.get(n).getMeasurePointId().equals(sitePointListGroupByPoint.get(s).getMeasurePointId())
							&& sitePointListByFilter.get(n).getMeasureSiteId().equals(sitePointListGroupByPoint.get(s).getMeasureSiteId())){
						flag=false;
					}
				}
				if(flag){
					sitePointListGroupByPoint.add(sitePointListByFilter.get(n));
				}
			}
		}
		return sitePointListGroupByPoint;
	}
	//通过楼栋、楼层筛选房间获取房间id
	public List<MeasureSitePO> getMeasureSiteListByFloorId(List<MeasureSitePO> measureSiteList,Long floorId){
		//获取该楼栋下面的所有房间号
		List<MeasureSitePO> roomList = new ArrayList<>();//所有房间
		for(int j=0;j<measureSiteList.size();j++){
			if(measureSiteList.get(j).getPid().equals(floorId)){
				roomList.add(measureSiteList.get(j));
			}
		}
		return roomList;
	}
	//通过楼栋、楼层筛选房间获取房间id
	public List<MeasureSitePO> getMeasureSiteListByType(List<MeasureSitePO> measureSiteList,Long siteBuildingId,List<MeasureSitePO> measureSiteLists){
		//获取该楼栋下面的所有房间号
		List<MeasureSitePO> roomList = new ArrayList<>();//所有房间
		if(!measureSiteList.isEmpty()){
			List<MeasureSitePO> floorList = new ArrayList<>(); 
			for(int i=0;i<measureSiteLists.size();i++){
				if(measureSiteLists.get(i).getPid()!=null){
					if(siteBuildingId.equals(measureSiteLists.get(i).getPid())){
						floorList.add(measureSiteLists.get(i));
					}
				}
			}
			for(int j=0;j<measureSiteList.size();j++){
				boolean inputStatus=false;
				if(!floorList.isEmpty()){
					for(int h=0;h<floorList.size();h++){
						if(measureSiteList.get(j).getPid()!=null){
							if(floorList.get(h).getProjectBuildingId().equals(measureSiteList.get(j).getPid())){
								inputStatus=true;
							}
						}
					}
				}else{
					if(measureSiteList.get(j).getPid()!=null){
						if(siteBuildingId.equals(measureSiteList.get(j).getPid())){
							inputStatus=true;
						}
					}
				}
				if(inputStatus){
					roomList.add(measureSiteList.get(j));
				}
			}
		}
		return roomList;
	}
	/*通过检查项、测点去重*/
	public List<MeasureSitePointPO> getNoRepeatByPoint(List<MeasureSitePointPO> sitePointList){
		List<MeasureSitePointPO> sitePointScreenList = new ArrayList<>();
		///去重后的所有测点
		for(int n=0;n<sitePointList.size();n++){
			if(sitePointScreenList.isEmpty()){
				sitePointScreenList.add(sitePointList.get(n));
			}else{
				boolean flag = true;
				for(int s=0;s<sitePointScreenList.size();s++){
					if(sitePointList.get(n).getMeasurePointId().equals(sitePointScreenList.get(s).getMeasurePointId()) 
							&& sitePointList.get(n).getMeasureSiteId().equals(sitePointScreenList.get(s).getMeasureSiteId())){
						flag=false;
					}
				}
				if(flag){
					sitePointScreenList.add(sitePointList.get(n));
				}
			}
		}
		return sitePointScreenList;
	}
	/*通过检查项和房间id查找并去重*/
	public List<MeasureSitePointPO> getNoRepeatByCheckTypeId(List<MeasureSitePointPO> sitePointList,String checkTypeId){
		List<MeasureSitePointPO> sitePointListGroupByPoint = new ArrayList<>();
		List<MeasureSitePointPO> sitePointListByFilter = new ArrayList<>();
		String[] checkTypeIds = checkTypeId.split(",");
		List<Long> checkTypeIdList = new ArrayList<>();
		for(int j=0;j<checkTypeIds.length;j++){
			checkTypeIdList.add(Long.valueOf(checkTypeIds[j]));
		}
		for(int i=0;i<sitePointList.size();i++){
			if(checkTypeIdList.contains(sitePointList.get(i).getProjectCheckTypeId())){
				sitePointListGroupByPoint.add(sitePointList.get(i));
			}
		}
		for(int i=0;i<sitePointListGroupByPoint.size();i++){
			boolean flag=true;
			if(sitePointListByFilter.isEmpty()){
				sitePointListByFilter.add(sitePointListGroupByPoint.get(i));
			}else{
				for(int k=0;k<sitePointListByFilter.size();k++){
					if(sitePointListByFilter.get(k).getMeasurePointId().equals(sitePointListGroupByPoint.get(i).getMeasurePointId()) && sitePointListByFilter.get(k).getMeasureSiteId().equals(sitePointListGroupByPoint.get(i).getMeasureSiteId())){
						flag=false;
					}
				}
				if(flag){
					sitePointListByFilter.add(sitePointListGroupByPoint.get(i));
				}
			}
		}
		return sitePointListByFilter;
	}

	public Object selectListByPids(MeasureSiteVo company) {
		CountByPageMeasure result = new CountByPageMeasure();
		List<GetsResultMeasure> resultItem = new ArrayList<>();
		Integer total=0;
		JasoUserPO cacheUser=(JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		// TODO Auto-generated method stub
		int limit1=company.getPageVo().getPageNo();
		int limit2=company.getPageVo().getPageSize();
		if(company.getPageVo().getPageNo()==1){
			limit1=0;
		}
		if(company.getPageVo().getPageNo()>1){
			limit1=(company.getPageVo().getPageNo()-1)*company.getPageVo().getPageSize();
		}
		limit2=company.getPageVo().getPageSize();
		resultItem=measureSiteMapper.selectListTreeByPid(company.getPid(),cacheUser.getCompanyId(),company.getProjectId(),limit1,limit2);
		total=measureSiteMapper.selectListTreeByPidCount(company.getPid(),cacheUser.getCompanyId(),company.getProjectId());
		result.setResultList(resultItem);
		if(!resultItem.isEmpty()){
			List<Long> roomIdList = new ArrayList<>();
			for(GetsResultMeasure item:resultItem){
				roomIdList.add(item.getMeasureSiteId());
			}
			//统计当前所有房间的所有测点数
			List<SiteCountNum> allNum = new ArrayList<>();
			allNum = measureSiteMapper.countAllPoint(company.getProjectId(),cacheUser.getCompanyId(),roomIdList);
			//统计当前所有房间的已测点数
			List<SiteCountNum> doneNum = new ArrayList<>();
			doneNum = measureSiteMapper.countDonePoint(company.getProjectId(),cacheUser.getCompanyId(),roomIdList);
			for(int i=0;i<resultItem.size();i++){
				for(int j=0;j<allNum.size();j++){
					if(resultItem.get(i).getMeasureSiteId().equals(allNum.get(j).getMeasureSiteId())){
						resultItem.get(i).setAllNum(allNum.get(j).getNum());
					}
				}
				for(int k=0;k<doneNum.size();k++){
					if(resultItem.get(i).getMeasureSiteId().equals(doneNum.get(k).getMeasureSiteId())){
						resultItem.get(i).setDoneNum(doneNum.get(k).getNum());
					}
				}
			}
		}
		PageVo pageVo = new PageVo();
		pageVo.setPageNo(company.getPageVo().getPageNo());
		pageVo.setPageSize(company.getPageVo().getPageSize());
		pageVo.setTotal(total);
		result.setPageVo(pageVo);
		return result;
	}

	public Object initMeasureSite(MeasureSitePO po) {
		// TODO Auto-generated method stu
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		Wrapper<MeasureSitePO> wrapper = new EntityWrapper<>();
		wrapper.eq("company_id", userInCache.getCompanyId());
		wrapper.eq("project_id", po.getProjectId());
		if(delete(wrapper)){//清除该公司下面的测区位置数据------然后同步新的楼栋数据
			//清理区域-测点信息
			Wrapper<MeasureSitePointPO> sitePointWrapper = new EntityWrapper<>();
			sitePointWrapper.eq("company_id", userInCache.getCompanyId());
			sitePointWrapper.eq("project_id", po.getProjectId());
			sitePointService.delete(sitePointWrapper);
			//清理测点-检查项信息
			Wrapper<PointCheckPO> checkWrapper = new EntityWrapper<>();
			checkWrapper.eq("company_id", userInCache.getCompanyId())
			.eq("is_add", 1)
			.eq("project_id", po.getProjectId());
			pointCheckService.delete(checkWrapper);
			///清理测点信息
			Wrapper<MeasurePointPO> pointWrapper = new EntityWrapper<>();
			pointWrapper.eq("company_id", userInCache.getCompanyId())
			.eq("project_id", po.getProjectId())
			.eq("point_type", 2);
			pointService.delete(pointWrapper);
			List<ProjectBuildingPO> projectBuildingList = new ArrayList<>();
			Wrapper<ProjectBuildingPO> buildingWrapper = new EntityWrapper<>();
			buildingWrapper.eq("company_id", userInCache.getCompanyId())
			.eq(po.getProjectId()!=null,"project_id", po.getProjectId());
			projectBuildingList=buildingService.selectList(buildingWrapper);
			List<MeasureSitePO> newSites = new ArrayList<>();
			for(int i=0;i<projectBuildingList.size();i++){
				MeasureSitePO site = new MeasureSitePO();
				site.setProjectBuildingId(projectBuildingList.get(i).getProjectBuildingId());
				site.setCheckedStatus(0);
				site.setPid(projectBuildingList.get(i).getPid());
				site.setCompanyId(projectBuildingList.get(i).getCompanyId());
				site.setFromProjectBuilding(1);
				site.setMajorType(1);
				site.setApartmentId(projectBuildingList.get(i).getApartmentId());
				site.setSiteType(projectBuildingList.get(i).getStatus());
				site.setMeasureSiteName(projectBuildingList.get(i).getBuildingName());
				site.setProjectId(projectBuildingList.get(i).getProjectId());
				newSites.add(site);
			}
			if(!newSites.isEmpty()){
				return insertOrUpdateBatch(newSites);
			}
		}
		return false;
	}

	public Object update(MeasureSitePO po) {
		// TODO Auto-generated method stub
		if(po.getMeasureSiteId()==null){
			return false;
		}
		return insertOrUpdate(po);
	}

	public Object getBuildingInfo(MeasureSitePO po) {
		// TODO Auto-generated method stub
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		Wrapper<MeasureSitePO> wrapper = new EntityWrapper<>();
		wrapper.eq("company_id", userInCache.getCompanyId())
		.eq(po.getProjectId()!=null,"project_id", po.getProjectId())
		.eq("site_type", 1);
		List<MeasureSitePO> list = new ArrayList<>();
		list = selectList(wrapper);
		return list;
	}

	public Object getAllApartment(MeasureSitePO po) {
		// TODO Auto-generated method stub
		List<ApartmentInfo> result = new ArrayList<>();
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		Wrapper<MeasureSitePO> wrapper = new EntityWrapper<>();
		wrapper.eq("company_id", userInCache.getCompanyId())
		.eq(po.getProjectId()!=null,"project_id", po.getProjectId()).groupBy("apartment_id");
		List<MeasureSitePO> list = new ArrayList<>();
		list = selectList(wrapper);
		if(!list.isEmpty()){
			List<Long> ids = new ArrayList<>();
			for(MeasureSitePO item:list){
				if(item.getApartmentId()!=null){
					ids.add(item.getApartmentId());
				}
			}
			Wrapper<ApartmentPO> awrapper = new EntityWrapper<>();
			awrapper.eq("company_id", userInCache.getCompanyId())
			.eq(po.getProjectId()!=null,"project_id", po.getProjectId())
			.in("apartment_id", ids);
			List<ApartmentPO> lists = new ArrayList<>();
			lists = apartmentService.selectList(awrapper);
			if(!lists.isEmpty()){
				for(ApartmentPO ite:lists){
					ApartmentInfo info = new ApartmentInfo();
					info.setApartmentId(ite.getApartmentId());
					info.setApartmentName(ite.getApartmentName());
					info.setProjectId(ite.getProjectId());
					result.add(info);
				}
			}
		}
		return result;
	}
	

}
