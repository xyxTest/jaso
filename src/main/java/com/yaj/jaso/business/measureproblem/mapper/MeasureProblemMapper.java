package com.yaj.jaso.business.measureproblem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yaj.common.mybatisplus.SuperMapper;
import com.yaj.jaso.business.measureproblem.entity.po.MeasureProblemPO;
import com.yaj.jaso.business.measureproblem.entity.vo.MeasureProblemResult;

/*
 * @Description: 
 * @date: 2019-08-28
 */
public interface MeasureProblemMapper extends SuperMapper<MeasureProblemPO> {
	List<MeasureProblemResult> selectLists(@Param("companyId")Long companyId,@Param("projectId")Long projectId,@Param("jasoUserId")Long jasoUserId,@Param("status")Integer status);
	MeasureProblemResult selectDetail(@Param("companyId")Long companyId,@Param("projectId")Long projectId, @Param("measureProblemId")Long measureProblemId);
}
