package com.yaj.jaso.business.role.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableLogic;

/*
 * @Description: 
 * @date: 2019-11-13
 */
@TableName(value = "role")
public class RolePO {
    /*
    *
    */
    @TableId
    private Long roleId;
    /*
    *
    */
    private String roleName;
    /*
    *
    */
    private Long companyId;
    /*
    *等级
    */
    private Integer sort;
    /*
    *
    */
    private Date createTime;
    /*
    *
    */
    @TableLogic
    private Integer ifDelete;

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getRoleId() {
        return this.roleId;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getCompanyId() {
        return this.companyId;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getSort() {
        return this.sort;
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