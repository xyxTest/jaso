package com.yaj.jaso.business.constructcontent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yaj.common.base.pojo.MulSelect;
import com.yaj.common.base.service.ServiceMain;
import com.yaj.common.threadlocal.ThreadlocalManager;
import com.yaj.jaso.business.constructcontent.entity.po.ConstructContentPO;
import com.yaj.jaso.business.constructcontent.entity.vo.ConstructContentTree;
import com.yaj.jaso.business.constructcontent.entity.vo.ConstructContentVo;
import com.yaj.jaso.business.constructcontent.entity.vo.ImportConstructContent;
import com.yaj.jaso.business.constructcontent.mapper.ConstructContentMapper;
import com.yaj.jaso.business.department.entity.vo.DepartmentTree;
import com.yaj.jaso.business.jasouser.entity.po.JasoUserPO;
import com.yaj.jaso.business.userworktype.entity.po.UserWorkTypePO;
import com.yaj.jaso.business.userworktype.service.UserWorkTypeService;
import com.yaj.jaso.business.worktype.entity.po.WorkTypePO;
import com.yaj.jaso.business.worktype.service.WorkTypeService;
import com.yaj.xyx.util.MD5Util;
import com.yaj.xyx.util.NodeUtil;
import com.yaj.xyx.util.ReadConstructContentExcel;
/*
 * @Description: 
 * @date: 2019-08-09
 */
@Service
public class ConstructContentService extends ServiceImpl<ConstructContentMapper, ConstructContentPO> {

    @Resource
    ConstructContentMapper constructContentMapper;
    @Autowired
    ServiceMain serviceMain;
    @Autowired
    WorkTypeService workTypeService;
    @Autowired
    UserWorkTypeService userWorkTypeService;
	public Object deleteLists(List<ConstructContentPO> list) {
		for(int i=0;i<list.size();i++){
			list.get(i).setIfDelete(1);
		}
		// TODO Auto-generated method stub
		return updateBatchById(list);
	}

	public Object selectPageList(ConstructContentVo constructContent) {
		// TODO Auto-generated method stub
		JasoUserPO jasoUser = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		MulSelect mul = MulSelect.newInstance("${1}.workTypeName", new ConstructContentPO(), new WorkTypePO());
		mul.setPage(constructContent.getPageVo().getPageNo(), constructContent.getPageVo().getPageSize());
		mul.where("${construct_content}")
			.eq(constructContent.getConstructContentName()!=null,"constructContentName", constructContent.getConstructContentName())
			.eq("companyId", jasoUser.getCompanyId());
		mul.setOrderBy("constructcontent.construct_content_id desc");		
        return serviceMain.mulSelect(mul);
	}

	public Object selectAll(ConstructContentPO constructContent) {
		// TODO Auto-generated method stub
		Wrapper<ConstructContentPO> wrapper = new EntityWrapper<ConstructContentPO>();
		wrapper.eq(constructContent.getCompanyId()!=null,"company_id", constructContent.getCompanyId())
		.like(constructContent.getConstructContentName()!=null,"construct_content_name", constructContent.getConstructContentName())
		.eq(constructContent.getWorkTypeId()!=null,"work_type_id", constructContent.getWorkTypeId());
		return selectList(wrapper);
	}

	public Object addOrUpdate(ConstructContentPO constructContent) {
		// TODO Auto-generated method stub
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		constructContent.setCompanyId(userInCache.getCompanyId());
		return insertOrUpdate(constructContent);
	}

	public Object selectTree(ConstructContentPO constructContent) {
		List<ConstructContentTree> lists = constructContentMapper.selectTreeLists(constructContent.getCompanyId());//所有数据未转化成树结构
		List<DepartmentTree> getLists = new ArrayList<DepartmentTree>();
		for(int i=0;i<lists.size();i++){
			if(lists.get(i).getIfDelete()!=1){
				DepartmentTree tree = new DepartmentTree();
				tree.setValue(lists.get(i).getValue());
				tree.setLabel(lists.get(i).getLabel());
				tree.setPid(lists.get(i).getPid());
				tree.setIfDelete(lists.get(i).getIfDelete());
				getLists.add(tree);
			}
		}
		NodeUtil nodeUtil = new NodeUtil();
	    String resultString=nodeUtil.getJasonString(getLists);
	    if(resultString!=null){
	        return JSONArray.parse(resultString);
	    }
		return null;
	}

	public Object selectListByRole(ConstructContentPO constructContent) {
		// TODO Auto-generated method stub
		//用户根据工种获取施工类容
		JasoUserPO cacheUser=(JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		List<UserWorkTypePO> userWorkTypes = new ArrayList<>();
		Wrapper<UserWorkTypePO> wrapper = new EntityWrapper<>();
		wrapper.eq("jaso_user_id", cacheUser.getJasoUserId());
		userWorkTypes = userWorkTypeService.selectList(wrapper);
		List<Long> workTypeIds = new ArrayList<>();
		for(UserWorkTypePO item:userWorkTypes){
			workTypeIds.add(item.getWorkTypeId());
		}
		if(workTypeIds.isEmpty()){
			return workTypeIds;
		}
		Wrapper<ConstructContentPO> wrapper2 = new EntityWrapper<>();
		wrapper2.eq("company_id", cacheUser.getCompanyId())
		.like("construct_content_name",constructContent.getConstructContentName())
		.in("work_type_id", workTypeIds);
		return selectList(wrapper2);
	}

	public Object importConstructContent(MultipartFile file, HttpServletRequest request) {
		JasoUserPO cacheUser=(JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		List<ImportConstructContent> ms = new ArrayList<ImportConstructContent>();
		ReadConstructContentExcel rm = new ReadConstructContentExcel();
		String newFileName = MD5Util.getMD5String(file.getOriginalFilename() + new Date() + UUID.randomUUID().toString()).replace(".","")
                + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		ms=rm.getExcelInfo(newFileName, file);
		List<ConstructContentPO> polist = new ArrayList<ConstructContentPO>();
		List<WorkTypePO> workTypeList = new ArrayList<>();
		List<WorkTypePO> alreadys = new ArrayList<>();
		//去重工种列表
		for(int k=0;k<ms.size();k++){
			WorkTypePO role = new WorkTypePO();
			role.setCompanyId(cacheUser.getCompanyId());
			role.setWorkTypeName(ms.get(k).getWorkTypeName());
			if(workTypeList.isEmpty()){
				workTypeList.add(role);
			}else{
				boolean setFlag = true;
				for(int m=0;m<workTypeList.size();m++){
					if(workTypeList.get(m).getWorkTypeName().equals(ms.get(k).getWorkTypeName())){
						setFlag=false;
					}
				}
				if(setFlag){
					workTypeList.add(role);
				}
			}
		}
		//查询所有工种
		Wrapper<WorkTypePO> wrapperRole = new EntityWrapper<>();
		List<WorkTypePO> newRoleList = new ArrayList<>();
		wrapperRole.eq("company_id", cacheUser.getCompanyId());
		alreadys=workTypeService.selectList(wrapperRole);
		for(int n=0;n<workTypeList.size();n++){
			boolean flag = true;
			for(int s=0;s<alreadys.size();s++){
				if(workTypeList.get(n).getWorkTypeName().equals(alreadys.get(s).getWorkTypeName())){
					flag = false;
				}
			}
			if(flag){
				newRoleList.add(workTypeList.get(n));
			}
		}
		if(workTypeService.insertBatch(newRoleList)){
			List<WorkTypePO> allList = new ArrayList<>();
			wrapperRole.eq("company_id", cacheUser.getCompanyId());
			allList=workTypeService.selectList(wrapperRole);
			for(int j=0;j<ms.size();j++){
					ConstructContentPO m = new ConstructContentPO();
					m.setCompanyId(cacheUser.getCompanyId());
					m.setConstructContentName(ms.get(j).getConstructContentName());
					m.setConstructContentUnit(ms.get(j).getConstructContentUnit());
					for(int i=0;i<allList.size();i++){
						if(allList.get(i).getWorkTypeName().equals(ms.get(j).getWorkTypeName())){
							m.setWorkTypeId(allList.get(i).getWorkTypeId());
						}
					}
					m.setContentCode(ms.get(j).getContentCode());
					polist.add(m);
			}
		}
		return insertOrUpdateBatch(polist);
	}

}
