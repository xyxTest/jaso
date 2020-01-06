package com.yaj.jaso.business.role.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yaj.common.mybatisplus.SuperMapper;
import com.yaj.jaso.business.menu.entity.po.MenuPO;
import com.yaj.jaso.business.role.entity.po.RolePO;

/*
 * @Description: 
 * @date: 2019-11-13
 */
public interface RoleMapper extends SuperMapper<RolePO> {
List<MenuPO> selectMenuList(@Param("jasoUserId")Long jasoUserId); 
}
