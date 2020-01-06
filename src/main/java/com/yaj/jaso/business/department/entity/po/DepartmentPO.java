package com.yaj.jaso.business.department.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableLogic;

/*
 * @Description: 
 * @date: 2019-07-22
 */
@TableName(value = "department")
public class DepartmentPO {
    /*
    *
    */
    @TableId
    private Long departmentId;
    /*
    *
    */
    private Long pid;
    /*
    *部门名称
    */
    private String departmentName;
    /*
    *等级排序
    */
    private Integer sortId;
    private Long createUser;
    /*
     *公司id
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

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getDepartmentId() {
        return this.departmentId;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Long getPid() {
        return this.pid;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentName() {
        return this.departmentName;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Long getCreateUser() {
        return this.createUser;
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

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Integer getSortId() {
		return sortId;
	}

	public void setSortId(Integer sortId) {
		this.sortId = sortId;
	}

}