package com.yaj.jaso.business.roleconstruct.service;

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
import com.yaj.jaso.business.constructcontent.entity.po.ConstructContentPO;
import com.yaj.jaso.business.constructcontent.service.ConstructContentService;
import com.yaj.jaso.business.jasouser.entity.po.JasoUserPO;
import com.yaj.jaso.business.role.entity.po.RolePO;
import com.yaj.jaso.business.roleconstruct.entity.po.RoleConstructPO;
import com.yaj.jaso.business.roleconstruct.entity.vo.RoleConstructVo;
import com.yaj.jaso.business.roleconstruct.mapper.RoleConstructMapper;
import com.yaj.jaso.business.userworktype.entity.po.UserWorkTypePO;
import com.yaj.jaso.business.userworktype.service.UserWorkTypeService;

/*
 * @Description: 
 * @date: 2019-11-15
 */
@Service
public class RoleConstructService extends ServiceImpl<RoleConstructMapper, RoleConstructPO> {

    @Resource
    RoleConstructMapper roleConstructMapper;
    @Autowired
    ServiceMain mainService;
    @Autowired
    UserWorkTypeService userWorkTypeService;
    @Autowired
    ConstructContentService constructContentService;
	public Object selectPageList(RoleConstructVo roleConstruct) {
		JasoUserPO cacheUser=(JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		MulSelect mul = MulSelect.newInstance("${1}.roleName", new RoleConstructPO(), new RolePO());
		mul.setPage(roleConstruct.getPageVo().getPageNo(), roleConstruct.getPageVo().getPageSize());
		mul.where("${role_construct}")
			.eq(cacheUser.getCompanyId()!=null,"companyId", cacheUser.getCompanyId())
			.eq(roleConstruct.getRoleId()!=null, "roleId",roleConstruct.getRoleId());
		return mainService.mulSelect(mul);
	}
	/*通过当前用户id获取该用户的角色，通过该用户的角色获取角色所对应的施工内容*/
	public Object selectPageLists(RoleConstructVo roleConstruct) {
		JasoUserPO cacheUser=(JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		Wrapper<UserWorkTypePO> wrapperRole = new EntityWrapper<>();
		wrapperRole.eq("jaso_user_id", cacheUser.getJasoUserId());
		List<UserWorkTypePO> userRoleList=userWorkTypeService.selectList(wrapperRole);
		List<Long> roleIds = new ArrayList<Long>();
		for(int i=0;i<userRoleList.size();i++){
			roleIds.add(userRoleList.get(i).getWorkTypeId());
		}
		Wrapper<ConstructContentPO> RoleConstructWrapper = new EntityWrapper<>();
		RoleConstructWrapper.in(!roleIds.isEmpty(),"work_type_id", roleIds);
		RoleConstructWrapper.groupBy("construct_content_id");
		return constructContentService.selectList(RoleConstructWrapper);
	}

	public Object add(RoleConstructPO roleConstruct) {
		// TODO Auto-generated method stub
		JasoUserPO cacheUser=(JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		RoleConstructPO po = new RoleConstructPO();
		po.setCompanyId(roleConstruct.getCompanyId());
		po.setRoleConstructId(roleConstruct.getRoleConstructId());
		po.setRoleId(roleConstruct.getRoleId());
		po.setRoleType(roleConstruct.getRoleType());
		po.setCompanyId(cacheUser.getCompanyId());
		return insertOrUpdate(po);
	}

	public Object deleteList(List<RoleConstructPO> RoleConstruct) {
		for(int i=0;i<RoleConstruct.size();i++){
			RoleConstruct.get(i).setIfDelete(1);
		}
		return updateBatchById(RoleConstruct);
	}

}
