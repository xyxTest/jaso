package com.yaj.jaso.business.worktype.service;
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
import com.yaj.jaso.business.role.entity.po.RolePO;
import com.yaj.jaso.business.userworktype.entity.po.UserWorkTypePO;
import com.yaj.jaso.business.userworktype.service.UserWorkTypeService;
import com.yaj.jaso.business.worktype.entity.po.WorkTypePO;
import com.yaj.jaso.business.worktype.entity.vo.GetByRoles;
import com.yaj.jaso.business.worktype.entity.vo.WorkTypeVo;
import com.yaj.jaso.business.worktype.mapper.WorkTypeMapper;
/*
 * @Description: 
 * @date: 2019-11-13
 */
@Service
public class WorkTypeService extends ServiceImpl<WorkTypeMapper, WorkTypePO> {

    @Resource
    WorkTypeMapper workTypeMapper;
    @Autowired
    ServiceMain serviceMain;
    @Autowired
    UserWorkTypeService userWorkTypeService;
    public Object selectListByPage(WorkTypeVo rmp) {
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		MulSelect mul = MulSelect.newInstance("${1}.roleName",new WorkTypePO(),new RolePO());
		mul.setPage(rmp.getPageVo().getPageNo(), rmp.getPageVo().getPageSize());
		mul.where("${work_type}")
			.eq(rmp.getRoleId()!=null,"roleId", rmp.getRoleId())
			.eq("companyId", userInCache.getCompanyId())
			.like(rmp.getWorkTypeName()!=null, "workTypeName",rmp.getWorkTypeName());
		mul.setOrderBy("worktype.create_time desc");
		return serviceMain.mulSelect(mul);
	}

	public Object selectRoleTypeAll(WorkTypePO rmp) {
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		Wrapper<WorkTypePO> wrapper = new EntityWrapper<WorkTypePO>();
		wrapper.eq("company_id", userInCache.getCompanyId())
		.eq(rmp.getRoleId()!=null,"role_id", rmp.getRoleId());
		return selectList(wrapper);
	}

	public Object deleteWorkType(List<WorkTypePO> rmp) {
		// TODO Auto-generated method stub
		for(int i=0;i<rmp.size();i++){
			rmp.get(i).setIfDelete(1);
		}
		// TODO Auto-generated method stub
		return updateBatchById(rmp);
	}

	public Object selectByRoleIds(GetByRoles rmp) {
		// TODO Auto-generated method stub
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		Wrapper<WorkTypePO> wrapper = new EntityWrapper<WorkTypePO>();
		wrapper.eq("company_id", userInCache.getCompanyId())
		.in(!rmp.getRoleIdList().isEmpty(),"role_id",rmp.getRoleIdList());
		return selectList(wrapper);
	}

	public Object selectByUserId(JasoUserPO user) {
		// TODO Auto-generated method stub
		Wrapper<UserWorkTypePO> wrapper = new EntityWrapper<UserWorkTypePO>();
		wrapper.eq("jaso_user_id", user.getJasoUserId());
		List<UserWorkTypePO> gets = new ArrayList<>();
		gets=userWorkTypeService.selectList(wrapper);
		List<Long> ids = new ArrayList<>();
		for(int i=0;i<gets.size();i++){
			ids.add(gets.get(i).getWorkTypeId());
		}
		return ids;
	}
}
