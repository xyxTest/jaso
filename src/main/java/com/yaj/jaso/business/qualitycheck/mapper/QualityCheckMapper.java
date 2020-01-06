package com.yaj.jaso.business.qualitycheck.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yaj.common.mybatisplus.SuperMapper;
import com.yaj.jaso.business.qualitycheck.entity.po.QualityCheckPO;
import com.yaj.jaso.business.qualitycheck.entity.vo.AboutUserInfo;
import com.yaj.jaso.business.qualitycheck.entity.vo.QualityCheckGet;
import com.yaj.jaso.business.securitycheck.entity.vo.QualityCheckTypeDetail;

/*
 * @Description: 
 * @date: 2019-09-05
 */
public interface QualityCheckMapper extends SuperMapper<QualityCheckPO> {

	List<QualityCheckGet> selectLists(@Param("companyId")Long companyId,@Param("projectId") Long projectId, 
			@Param("natureId")Long natureId,@Param("type")Integer type, @Param("status")Integer status,
			@Param("jasoUserId") Long jasoUserId,@Param("state")Integer state, @Param("startTime")Date start, 
			@Param("endTime")Date end, @Param("searchContent")String searchContent);
	List<AboutUserInfo> selectAboutUserList(@Param("companyId")Long companyId,@Param("projectId") Long projectId);
	QualityCheckGet selectDetail(@Param("companyId")Long companyId,@Param("projectId") Long projectId, @Param("qualityCheckId")Long qualityCheckId);
	List<AboutUserInfo> selectAboutUserDetail(@Param("companyId")Long companyId, @Param("projectId") Long projectId,@Param("qualityCheckId") Long qualityCheckId);
	List<QualityCheckTypeDetail> getListByAboutId(@Param("qualityCheckId") Long qualityCheckId);
}
