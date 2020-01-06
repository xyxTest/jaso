package com.yaj.jaso.business.projectbuilding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import javax.annotation.Resource;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yaj.common.base.pojo.MulSelect;
import com.yaj.common.base.service.ServiceMain;
import com.yaj.common.threadlocal.ThreadlocalManager;
import com.yaj.core.util.BeanUtil;
import com.yaj.jaso.business.apartment.entity.po.ApartmentPO;
import com.yaj.jaso.business.apartment.service.ApartmentService;
import com.yaj.jaso.business.department.entity.vo.DepartmentTree;
import com.yaj.jaso.business.jasouser.entity.po.JasoUserPO;
import com.yaj.jaso.business.measurepaper.service.MeasurePaperService;
import com.yaj.jaso.business.measuresite.service.MeasureSiteService;
import com.yaj.jaso.business.project.entity.po.ProjectPO;
import com.yaj.jaso.business.projectbuilding.entity.po.CountByPage;
import com.yaj.jaso.business.projectbuilding.entity.po.GetsResult;
import com.yaj.jaso.business.projectbuilding.entity.po.ProjectBuildingPO;
import com.yaj.jaso.business.projectbuilding.entity.vo.BuildingInfo;
import com.yaj.jaso.business.projectbuilding.entity.vo.FlloorInfo;
import com.yaj.jaso.business.projectbuilding.entity.vo.ProjectBuildingAdd;
import com.yaj.jaso.business.projectbuilding.entity.vo.ProjectBuildingUpdate;
import com.yaj.jaso.business.projectbuilding.entity.vo.ProjectBuildingVo;
import com.yaj.jaso.business.projectbuilding.entity.vo.RoomInfo;
import com.yaj.jaso.business.projectbuilding.mapper.ProjectBuildingMapper;
import com.yaj.jaso.global.PageVo;
import com.yaj.xyx.util.BuildingNodeUtil;
/*
 * @Description: 
 * @date: 2019-08-07
 */
@Service
public class ProjectBuildingService extends ServiceImpl<ProjectBuildingMapper, ProjectBuildingPO> {

    @Resource
    ProjectBuildingMapper projectBuildingMapper;
    @Autowired
    ServiceMain serviceMain;
    @Autowired
    MeasureSiteService measureSiteService;
    @Autowired
    ApartmentService apartmentService;
    @Autowired
    MeasurePaperService paperService;
	public Object selectPageList(ProjectBuildingVo company) {
		JasoUserPO cacheUser=(JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		MulSelect mul = MulSelect.newInstance("${1}.projectName", new ProjectBuildingPO(), new ProjectPO());
		mul.setOrderBy("projectbuilding.create_time desc");
		mul.setPage(company.getPageVo().getPageNo(), company.getPageVo().getPageSize());		
		mul.where("${project_building}")
			.eq(company.getProjectId()!=null,"projectId", company.getProjectId())
			.eq("companyId", cacheUser.getCompanyId());
		return serviceMain.mulSelect(mul);
	}

	public Object deleteBatchByIds(List<ProjectBuildingPO> po) {
		for(int i=0;i<po.size();i++){
			po.get(i).setIfDelete(1);
		}
		// TODO Auto-generated method stub
		return insertOrUpdateBatch(po);
	}
	public Object deleteChildByPid(ProjectBuildingPO po){
		JasoUserPO cacheUser=(JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		List<Long> ids = new ArrayList<>();
		List<ProjectBuildingPO> list = new ArrayList<>();
		if(po.getProjectBuildingId()!=null){
			ids = projectBuildingMapper.selectIdListByPid(po.getProjectBuildingId(),cacheUser.getCompanyId(),po.getProjectId());
			if(!ids.isEmpty()){
				for(Long id:ids){
					ProjectBuildingPO pos = new ProjectBuildingPO();
					pos.setProjectBuildingId(id);
					list.add(pos);
				}
			}
			ProjectBuildingPO test = new ProjectBuildingPO();
			test.setProjectBuildingId(po.getProjectBuildingId());
			list.add(test);
			return deleteBatchByIds(list);
		}
		return false;
	}

	public Object selectListTree(ProjectBuildingPO company) {
		// TODO Auto-generated method stub
		JasoUserPO jasoUser = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		List<DepartmentTree> lists = projectBuildingMapper.selectListTree(jasoUser.getCompanyId(),company.getProjectId());//所有数据未转化成树结构
		if(!lists.isEmpty()){
			BuildingNodeUtil nodeUtil = new BuildingNodeUtil();
		    String resultString=nodeUtil.getJasonString(lists);
		    if(resultString!=null){
		        return JSONArray.parse(resultString);
		    }
		}
		List<?> result = new ArrayList<>();
		return result;
	}

	public Object selectLists(ProjectBuildingPO dpo) {
		JasoUserPO cacheUser=(JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		Wrapper<ProjectBuildingPO> wrapper = new EntityWrapper<ProjectBuildingPO>();
		wrapper.orderDesc(Arrays.asList("create_time"));
		wrapper.like(dpo.getBuildingName()!=null,"building_name", dpo.getBuildingName());
		wrapper.eq(dpo.getProjectId()!=null,"project_id", dpo.getProjectId());
		wrapper.eq(dpo.getCompanyId()!=null,"company_id", cacheUser.getCompanyId());
		wrapper.eq(dpo.getPid()!=null,"pid", dpo.getPid());
        return selectList(wrapper);
	}

	public Object selectInfoList(ProjectBuildingPO company) {
		JasoUserPO cacheUser=(JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		Wrapper<ProjectBuildingPO> wrapper = new EntityWrapper<ProjectBuildingPO>();
		wrapper.eq("company_id", cacheUser.getCompanyId());
		wrapper.eq(company.getProjectId()!=null,"project_id", company.getProjectId())
		.eq("status", 1)
		.eq(company.getPid()!=null,"pid", company.getPid());
        return selectList(wrapper);
	}

	public Object selectListByPid(ProjectBuildingVo company) {
		CountByPage result = new CountByPage();
		List<GetsResult> resultItem = new ArrayList<>();
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
		resultItem=projectBuildingMapper.selectListTreeByPid(company.getPid(),cacheUser.getCompanyId(),company.getProjectId(),limit1,limit2);
		total=projectBuildingMapper.selectListTreeByPidCount(company.getPid(),cacheUser.getCompanyId(),company.getProjectId());
		result.setResultList(resultItem);
		PageVo pageVo = new PageVo();
		pageVo.setPageNo(company.getPageVo().getPageNo());
		pageVo.setPageSize(company.getPageVo().getPageSize());
		pageVo.setTotal(total);
		result.setPageVo(pageVo);
		return result;
	}

	public Object add(ProjectBuildingPO company) {
		// TODO Auto-generated method stub
		JasoUserPO jasouser = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		company.setCompanyId(jasouser.getCompanyId());
		return insertOrUpdate(company);
	}
	public static List removeDuplicate(List list) {   
	    HashSet h = new HashSet(list);   
	    list.clear();   
	    list.addAll(h);   
	    return list;   
	}
	public Object editProjectBuilding(ProjectBuildingAdd po) {
		// TODO Auto-generated method stub
		JasoUserPO jasouser = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		ProjectBuildingPO build = new ProjectBuildingPO();
		build.setBuildingName(po.getBuildingName());
		build.setApartmentNum(po.getApartmentNum());
		build.setFloorNum(po.getApartmentNum());
		build.setRoomNum(po.getRoomNum());
		build.setStatus(1);
		build.setCompanyId(jasouser.getCompanyId());
		build.setProjectId(po.getProjectId());
		if(insert(build)){
			//更新楼栋信息
			if(build.getProjectBuildingId()!=null){
				if(po.getFloorNum()!=null){//楼层添加
					List<ProjectBuildingPO> floorList = new ArrayList<>();
					for(int i=0;i<po.getFloorNum();i++){
						ProjectBuildingPO floor = new ProjectBuildingPO();
						floor.setPid(build.getProjectBuildingId());
						String name = (i+1)+"F";
						floor.setBuildingName(name);
						floor.setStatus(2);
						floor.setProjectId(po.getProjectId());
						floor.setCompanyId(jasouser.getCompanyId());
						floorList.add(floor);
					}
					if(insertOrUpdateBatch(floorList)){
						if(po.getApartmentName()!=null && !po.getApartmentName().isEmpty()){
							List<String> anames = new ArrayList<>();
							List<String> oldNameList = new ArrayList<>();
							for(int p=0;p<po.getApartmentName().size();p++){
								oldNameList.add(po.getApartmentName().get(p));
							}
							anames = removeDuplicate(oldNameList);
							//添加户型
							List<ApartmentPO> oldList = new ArrayList<>();//老的
							Wrapper<ApartmentPO> awrapper = new EntityWrapper<>();
							awrapper.eq("company_id", jasouser.getCompanyId())
							.eq("project_id", po.getProjectId())
							.in("apartment_name", anames);
							oldList=apartmentService.selectList(awrapper);
							List<ApartmentPO> paperList = new ArrayList<>();
							for(String e:anames){
								ApartmentPO paper = new ApartmentPO();
								if(!oldList.isEmpty()){
									for(ApartmentPO apartment:oldList){
										if(e.equals(apartment.getApartmentName())){
											paper=apartment;
										}
									}
									if(paper.getApartmentId()==null){
										paper.setCompanyId(jasouser.getCompanyId());
										paper.setApartmentName(e);
										paper.setProjectId(po.getProjectId());
									}
								}else{
									paper.setCompanyId(jasouser.getCompanyId());
									paper.setApartmentName(e);
									paper.setProjectId(po.getProjectId());
								}
								paperList.add(paper);
							}
							if(apartmentService.insertOrUpdateBatch(paperList)){//户型添加
								List<ApartmentPO> newnames = new ArrayList<>();
								for(int h=0;h<po.getApartmentName().size();h++){
									ApartmentPO newpo = new ApartmentPO();
									for(int k=0;k<paperList.size();k++){
										if(po.getApartmentName().get(h).equals(paperList.get(k).getApartmentName())){
											newpo = paperList.get(k);
										}
									}
									newnames.add(newpo);
								}
								if(po.getRoomNum()!=null){//房间添加
									List<ProjectBuildingPO> roomList = new ArrayList<>();
									for(int x=0;x<floorList.size();x++){
										for(int y=0;y<newnames.size();y++){
											ProjectBuildingPO room = new ProjectBuildingPO();
											room.setPid(floorList.get(x).getProjectBuildingId());
											room.setApartmentId(newnames.get(y).getApartmentId());
											String roomName = (x+1)+"0"+(y+1);
											room.setBuildingName(roomName);
											room.setProjectId(po.getProjectId());
											room.setCompanyId(jasouser.getCompanyId());
											room.setStatus(3);
											roomList.add(room);
										}
									}
									return insertOrUpdateBatch(roomList);
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

	public Object updatePaper(ProjectBuildingUpdate po) {
		// TODO Auto-generated method stub
		if(po.getProjectBuildingId()!=null){
			if(po.getApartmentId()!=null){
				ApartmentPO apartment = new ApartmentPO();
				apartment = apartmentService.selectById(po.getApartmentId());
				if(apartment!=null){
					if(!po.getApartmentName().equals(apartment.getApartmentName())){
						ApartmentPO apartmentnew = new ApartmentPO();
						apartmentnew.setApartmentName(po.getApartmentName());
						apartmentnew.setProjectId(po.getProjectId());
						apartmentnew.setCompanyId(po.getCompanyId());
						if(apartmentService.insertOrUpdate(apartmentnew)){
							po.setApartmentId(apartmentnew.getApartmentId());
							ProjectBuildingPO newpo = new ProjectBuildingPO();
							BeanUtil.copy(po, newpo);
							return insertOrUpdate(newpo);
						}
					}
				}
			}
		}
		return false;
	}
}
