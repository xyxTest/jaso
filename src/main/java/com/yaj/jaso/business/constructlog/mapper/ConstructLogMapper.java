package com.yaj.jaso.business.constructlog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yaj.common.mybatisplus.SuperMapper;
import com.yaj.jaso.business.constructlog.entity.po.ConstructLogPO;

/*
 * @Description: 
 * @date: 2019-08-09
 */
public interface ConstructLogMapper extends SuperMapper<ConstructLogPO> {

	List<Integer> selectRoleTypeList(@Param("companyId")Long companyId, @Param("jasoUserId")Long jasoUserId);
	List<Long> selectUserListByName(@Param("companyId")Long companyId,@Param("projectId")Long projectId,@Param("userRealName")String userRealName);
	List<Integer> selectRoleTypeLists(@Param("companyId")Long companyId,@Param("jasoUserId")Object jasoUserId);
}
