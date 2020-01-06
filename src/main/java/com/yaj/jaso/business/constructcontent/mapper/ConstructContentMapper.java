package com.yaj.jaso.business.constructcontent.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yaj.common.mybatisplus.SuperMapper;
import com.yaj.jaso.business.constructcontent.entity.po.ConstructContentPO;
import com.yaj.jaso.business.constructcontent.entity.vo.ConstructContentTree;

/*
 * @Description: 
 * @date: 2019-08-09
 */
public interface ConstructContentMapper extends SuperMapper<ConstructContentPO> {

	List<ConstructContentTree> selectTreeLists(@Param("companyId") Long companyId);
	List<ConstructContentPO> selectListByRole(@Param("jasoUserId") Long jasoUserId,@Param("projectId") Long projectId);

}
