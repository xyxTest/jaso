package com.yaj.jaso.business.jasouser.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yaj.common.mybatisplus.SuperMapper;
import com.yaj.jaso.business.jasouser.entity.po.JasoUserPO;
import com.yaj.jaso.business.jasouser.entity.vo.UserListInfo;

/*
 * @Description: 
 * @date: 2019-07-22
 */
public interface JasoUserMapper extends SuperMapper<JasoUserPO> {

	List<UserListInfo> getListByProjectId(@Param("projectId") Long projectId, @Param("companyId")Long companyId);

	List<UserListInfo> getUserListByProjectId(@Param("projectId") Long projectId, 
			@Param("companyId")Long companyId,@Param("userIds")List<Long> userIds);
	
}
