package com.yaj.jaso.business.securitycheck.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yaj.common.mybatisplus.SuperMapper;
import com.yaj.jaso.business.qualitycheck.entity.vo.AboutUserInfo;
import com.yaj.jaso.business.securitycheck.entity.po.SecurityCheckPO;
import com.yaj.jaso.business.securitycheck.entity.vo.QualityCheckTypeDetail;
import com.yaj.jaso.business.securitycheck.entity.vo.SecurityCheckGet;

/*
 * @Description: 
 * @date: 2019-09-07
 */
public interface SecurityCheckMapper extends SuperMapper<SecurityCheckPO> {
	List<SecurityCheckGet> selectLists(@Param("companyId")Long companyId,@Param("projectId") Long projectId, 
			@Param("natureId")Long natureId,@Param("type")Integer type, @Param("status")Integer status,
			@Param("jasoUserId") Long jasoUserId,@Param("state")Integer state, @Param("startTime")Date start, 
			@Param("endTime")Date end, @Param("searchContent")String searchContent);
	List<AboutUserInfo> selectAboutUserList(@Param("companyId")Long companyId,@Param("projectId") Long projectId);
	SecurityCheckGet selectDetail(@Param("companyId")Long companyId,@Param("projectId") Long projectId,@Param("securityCheckId") Long securityCheckId);
	List<AboutUserInfo> selectAboutUserDetail(@Param("companyId")Long companyId,@Param("projectId") Long projectId,@Param("securityCheckId") Long securityCheckId);
	List<QualityCheckTypeDetail> getListByAboutId(@Param("securityCheckId") Long securityCheckId);
}
