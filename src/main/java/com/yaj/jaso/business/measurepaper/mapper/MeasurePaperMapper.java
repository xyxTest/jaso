package com.yaj.jaso.business.measurepaper.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yaj.common.mybatisplus.SuperMapper;
import com.yaj.jaso.business.measurepaper.entity.po.MeasurePaperPO;
import com.yaj.jaso.business.measuresite.entity.po.MeasureSitePO;

/*
 * @Description: 
 * @date: 2019-08-27
 */
public interface MeasurePaperMapper extends SuperMapper<MeasurePaperPO> {

	List<Long> selectMeasureSiteIdList(@Param("projectId")Long projectId, @Param("measurePaperId")Long measurePaperId,@Param("companyId")Long companyId);
	List<MeasureSitePO> selectListTreeByPid(@Param("projectId")Long projectId, @Param("companyId")Long companyId);
}
