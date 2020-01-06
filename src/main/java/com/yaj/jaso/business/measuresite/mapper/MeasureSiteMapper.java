package com.yaj.jaso.business.measuresite.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yaj.common.mybatisplus.SuperMapper;
import com.yaj.jaso.business.measuresite.entity.po.GetsResultMeasure;
import com.yaj.jaso.business.measuresite.entity.po.MeasureSitePO;
import com.yaj.jaso.business.measuresite.entity.vo.SiteCountNum;

/*
 * @Description: 
 * @date: 2019-08-26
 */
public interface MeasureSiteMapper extends SuperMapper<MeasureSitePO> {

	List<MeasureSitePO> selectListTreeByPid(@Param("pid") Long pid);
	List<GetsResultMeasure> selectListTreeByPid(@Param("pid") Long pid,@Param("companyId") Long companyId, 
			@Param("projectId") Long projectId,@Param("limit1") Integer limit1,@Param("limit2") Integer limit2);
	Integer selectListTreeByPidCount(@Param("pid") Long pid,@Param("companyId") Long companyId, 
			@Param("projectId") Long projectId);
	List<SiteCountNum> countAllPoint(@Param("projectId")Long projectId, @Param("companyId")Long companyId, @Param("roomIdList")List<Long> roomIdList);
	List<SiteCountNum> countDonePoint(@Param("projectId")Long projectId, @Param("companyId")Long companyId, @Param("roomIdList")List<Long> roomIdList);

}
