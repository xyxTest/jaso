package com.yaj.jaso.business.project.service;

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
import com.yaj.jaso.business.project.entity.po.ProjectPO;
import com.yaj.jaso.business.project.entity.vo.CityCode;
import com.yaj.jaso.business.project.entity.vo.ProjectDetail;
import com.yaj.jaso.business.project.entity.vo.ProjectVo;
import com.yaj.jaso.business.project.mapper.ProjectMapper;
import com.yaj.jaso.business.projectadresscode.entity.po.ProjectAdressCodePO;
import com.yaj.jaso.business.projectadresscode.service.ProjectAdressCodeService;
import com.yaj.jaso.business.projecttenders.entity.po.ProjectTendersPO;
import com.yaj.jaso.business.projecttenders.service.ProjectTendersService;
import com.yaj.jaso.business.role.entity.po.RolePO;
import com.yaj.jaso.business.role.service.RoleService;
import com.yaj.jaso.business.userproject.entity.po.UserProjectPO;
import com.yaj.jaso.business.userproject.service.UserProjectService;
import com.yaj.jaso.business.userrole.entity.po.UserRolePO;
import com.yaj.jaso.business.userrole.service.UserRoleService;
import com.yaj.jaso.business.jasouser.entity.po.JasoUserPO;
import com.yaj.jaso.business.jasouser.service.JasoUserService;

/*
 * @Description: 
 * @date: 2019-07-22
 */
@Service
public class ProjectService extends ServiceImpl<ProjectMapper, ProjectPO> {

    @Resource
    ProjectMapper projectMapper;
    @Autowired
    ServiceMain serviceMain;
    @Autowired
    ProjectAdressCodeService codeService;
    @Autowired
    UserRoleService userRoleService;
    @Autowired
    RoleService roleService;
    @Autowired
    UserProjectService userProjectService;
    @Autowired
    JasoUserService userService;
    @Autowired
    ProjectTendersService tenderService;
	public Object selectProjectList(ProjectVo ppo) {
		// TODO Auto-generated method stub
		JasoUserPO cacheUser=(JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		MulSelect mul = MulSelect.newInstance("${1}.userRealName", new ProjectPO(), new JasoUserPO());
		mul.setPage(ppo.getPageVo().getPageNo(), ppo.getPageVo().getPageSize());
		mul.where("${project}")
		.eq("companyId", cacheUser.getCompanyId())
		.eq(ppo.getProjectId()!=null,"projectId", ppo.getProjectId())
		.like(ppo.getProjectName()!=null,"projectName", ppo.getProjectName())
		.like(ppo.getBuildingUnit()!=null,"buildingUnit", ppo.getBuildingUnit())
		.like(ppo.getConstructUnit()!=null,"constructUnit", ppo.getConstructUnit())
		.like(ppo.getDesignUnit()!=null,"designUnit", ppo.getDesignUnit());
		mul.setOrderBy("project.create_time desc");		
        return serviceMain.mulSelect(mul);
	}

	public Object selectProjectLists(ProjectPO po) {
		// TODO Auto-generated method stub
		JasoUserPO cacheUser=(JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		List<UserRolePO> userRoles = new ArrayList<>();
		Wrapper<UserRolePO> users = new EntityWrapper<>();
		users.eq("jaso_user_id", cacheUser.getJasoUserId()).eq("company_id", cacheUser.getCompanyId());
		userRoles=userRoleService.selectList(users);
		List<Long> ids = new ArrayList<>();
		for(int i=0;i<userRoles.size();i++){
			ids.add(userRoles.get(i).getRoleId());
		}
		if(ids.isEmpty()){
			return ids;
		}else{
			List<RolePO> roles = new ArrayList<>();
			Wrapper<RolePO> roleWrapper = new EntityWrapper<>();
			roleWrapper.eq("company_id", cacheUser.getCompanyId())
			.in("role_id", ids);
			roles = roleService.selectList(roleWrapper);
			boolean isAdmin = false;
			for(int i=0;i<roles.size();i++){
				if(roles.get(i).getSort()<2){
					isAdmin = true;
				}
			}
			if(isAdmin){
				MulSelect mul = MulSelect.newInstance("${1}.userRealName", new ProjectPO(), new JasoUserPO());
				mul.where("${project}")
				.eq("companyId", cacheUser.getCompanyId())
				.eq(po.getProjectId()!=null,"projectId", po.getProjectId())
				.eq(po.getType()!=null,"type", po.getType())
				.like(po.getProjectName()!=null,"projectName", po.getProjectName());
				mul.setOrderBy("project.create_time desc");		
				return serviceMain.mulSelect(mul);
			}else{
				List<UserProjectPO> userProjects = new ArrayList<>();
				Wrapper<UserProjectPO> wrapper = new EntityWrapper<>();
				wrapper.eq("company_id", cacheUser.getCompanyId())
				.eq("jaso_user_id", cacheUser.getJasoUserId());
				userProjects = userProjectService.selectList(wrapper);
				if(userProjects.isEmpty()){
					return userProjects;
				}else{
					List<Long> projectIds = new ArrayList<>();
					for(UserProjectPO item:userProjects){
						projectIds.add(item.getProjectId());
					}
					MulSelect mul = MulSelect.newInstance("${1}.userRealName", new ProjectPO(), new JasoUserPO());
					mul.where("${project}")
					.eq("companyId", cacheUser.getCompanyId())
					.eq(po.getType()!=null,"type", po.getType())
					.eq(po.getProjectId()!=null,"projectId", po.getProjectId())
					.like(po.getProjectName()!=null,"projectName", po.getProjectName())
					.in("projectId", projectIds);
					mul.setOrderBy("project.create_time desc");		
					return serviceMain.mulSelect(mul);
				}
			}
		}
	}

	public Object deleteList(List<ProjectPO> ppo) {
		for(int i=0;i<ppo.size();i++){
			ppo.get(i).setIfDelete(1);
		}
		// TODO Auto-generated method stub
		return updateBatchById(ppo);
	}

	public Object add(ProjectPO ppo) {
		// TODO Auto-generated method stub
		JasoUserPO cacheUser=(JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		ppo.setCompanyId(cacheUser.getCompanyId());
		ppo.setCreateUser(cacheUser.getJasoUserId());
		return insertOrUpdate(ppo);
	}

	public Object getCityCode() {
		// TODO Auto-generated method stub
		Wrapper<ProjectAdressCodePO> wrapper = new EntityWrapper<>();
		return codeService.selectList(wrapper);
	}

	public Object selectProjectDetail(ProjectPO po) {
		// TODO Auto-generated method stub
		JasoUserPO cacheUser=(JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		ProjectDetail result = new ProjectDetail();
		List<ProjectPO> project = new ArrayList<>();
		Wrapper<ProjectPO> wrapper = new EntityWrapper<>();
		wrapper.eq("project_id", po.getProjectId())
		.eq("company_id", cacheUser.getCompanyId());
		project=selectList(wrapper);
		if(project!=null && !project.isEmpty()){
			result.setProjectName(project.get(0).getProjectName());
			result.setProjectIcon(project.get(0).getProjectIcon());
			result.setBuildingUnit(project.get(0).getBuildingUnit());
			result.setConstructUnit(project.get(0).getConstructUnit());
			result.setDesignUnit(project.get(0).getDesignUnit());
			result.setProjectAddress(project.get(0).getProjectAddress());
			result.setProjectDate(project.get(0).getProjectDate());
			result.setProjectDescribe(project.get(0).getProjectDescribe());
			result.setType(project.get(0).getType());
			if(project.get(0).getJasoUserId()!=null){
				JasoUserPO leader = userService.selectById(project.get(0).getJasoUserId());
				if(leader!=null){
					result.setLeader(leader.getUserRealName());
				}
			}
			Wrapper<ProjectTendersPO> tender = new EntityWrapper<>();
			tender.eq("company_id", cacheUser.getCompanyId())
			.eq("project_id", po.getProjectId());
			result.setProjectTenders(tenderService.selectList(tender));
		}
		return result;
	}

	public Object updateProjectCityCodeInfo(List<CityCode> list) {
		// TODO Auto-generated method stub
		JasoUserPO cacheUser=(JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		Wrapper<ProjectAdressCodePO> wrapper = new EntityWrapper<>();
		if(!list.isEmpty()){
			if(codeService.delete(wrapper)){
				List<ProjectAdressCodePO> codes = new ArrayList();
				for(CityCode code:list){
					ProjectAdressCodePO item = new ProjectAdressCodePO();
					item.setCitycode(code.getWeaid());
					item.setAdcode(code.getWeaid());
					item.setCityName(code.getCitynm());
					codes.add(item);
				}
				codeService.insertOrUpdateBatch(codes);
			}
		}
		
		return null;
	}

}
