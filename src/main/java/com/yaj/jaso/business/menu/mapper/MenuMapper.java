package com.yaj.jaso.business.menu.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.yaj.common.mybatisplus.SuperMapper;
import com.yaj.jaso.business.menu.entity.po.MenuPO;

/*
 * @Description: 
 * @date: 2019-07-22
 */
public interface MenuMapper extends SuperMapper<MenuPO> {

	List<MenuPO> selectListByRole(@Param("jasoUserId") Long jasoUserId, @Param("companyId")Long companyId);

}
