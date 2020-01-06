package com.yaj.jaso.business.rolemenu.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableLogic;

/*
 * @Description: 
 * @date: 2019-07-23
 */
@TableName(value = "role_menu")
public class RoleMenuPO {
    /*
    *
    */
    @TableId
    private Long roleMenuId;
    /*
    *
    */
    private Long roleId;
    /*
    *
    */
    private Long menuId;
    /*
    *
    */
    private Long companyId;
    /*
    *
    */
    private Date createTime;
    /*
    *
    */
    @TableLogic
    private Integer ifDelete;

    public void setRoleMenuId(Long roleMenuId) {
        this.roleMenuId = roleMenuId;
    }

    public Long getRoleMenuId() {
        return this.roleMenuId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getRoleId() {
        return this.roleId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getMenuId() {
        return this.menuId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getCompanyId() {
        return this.companyId;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setIfDelete(Integer ifDelete) {
        this.ifDelete = ifDelete;
    }

    public Integer getIfDelete() {
        return this.ifDelete;
    }

}