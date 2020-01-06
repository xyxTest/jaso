package com.yaj.jaso.business.message.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yaj.common.mybatisplus.SuperMapper;
import com.yaj.jaso.business.message.entity.po.MessagePO;
import com.yaj.jaso.business.message.entity.vo.GetResult;

/*
 * @Description: 
 * @date: 2019-10-29
 */
public interface MessageMapper extends SuperMapper<MessagePO> {

	List<GetResult> selectListByJasoUserId(@Param("jasoUserId")Long jasoUserId, @Param("companyId")Long companyId, @Param("limit1")int limit1, @Param("limit2")int limit2);

}
