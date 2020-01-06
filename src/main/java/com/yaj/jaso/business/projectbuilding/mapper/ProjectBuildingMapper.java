package com.yaj.jaso.business.projectbuilding.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yaj.common.mybatisplus.SuperMapper;
import com.yaj.jaso.business.department.entity.vo.DepartmentTree;
import com.yaj.jaso.business.projectbuilding.entity.po.GetsResult;
import com.yaj.jaso.business.projectbuilding.entity.po.ProjectBuildingPO;

/*
 * @Description: 
 * @date: 2019-08-07
 */
public interface ProjectBuildingMapper extends SuperMapper<ProjectBuildingPO> {

	List<DepartmentTree> selectListTree(@Param("companyId") Long companyId, @Param("projectId") Long projectId);
	List<GetsResult> selectListTreeByPid(@Param("pid") Long pid,@Param("companyId") Long companyId, 
			@Param("projectId") Long projectId,@Param("limit1") Integer limit1,@Param("limit2") Integer limit2);
	Integer selectListTreeByPidCount(@Param("pid") Long pid,@Param("companyId") Long companyId, 
			@Param("projectId") Long projectId);
	List<Long> selectIdListByPid(@Param("pid") Long pid,@Param("companyId") Long companyId, 
			@Param("projectId") Long projectId);
}
