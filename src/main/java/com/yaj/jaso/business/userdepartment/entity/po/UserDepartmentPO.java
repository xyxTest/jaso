package com.yaj.jaso.business.userdepartment.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;


/*
 * @Description: 
 * @date: 2019-07-31
 */
@TableName(value = "user_department")
public class UserDepartmentPO {
    /*
    *
    */
    @TableId
    private Long userDepartmentId;
    /*
    *
    */
    private Long jasoUserId;
    /*
    *
    */
    private Long departmentId;
    @TableLogic
    private Integer ifDelete;

    public void setUserDepartmentId(Long userDepartmentId) {
        this.userDepartmentId = userDepartmentId;
    }

    public Long getUserDepartmentId() {
        return this.userDepartmentId;
    }


    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getDepartmentId() {
        return this.departmentId;
    }

	public Long getJasoUserId() {
		return jasoUserId;
	}

	public void setJasoUserId(Long jasoUserId) {
		this.jasoUserId = jasoUserId;
	}

	public Integer getIfDelete() {
		return ifDelete;
	}

	public void setIfDelete(Integer ifDelete) {
		this.ifDelete = ifDelete;
	}

	
}