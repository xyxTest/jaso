package com.yaj.jaso.business.nature.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableLogic;

/*
 * @Description: 
 * @date: 2019-09-04
 */
@TableName(value = "nature")
public class NaturePO {
    /*
    *
    */
    @TableId
    private Long natureId;
    /*
    *
    */
    private String natureName;
    /*
    *1、质量管理性质 2、安全管理性质
    */
    private Integer type;
    /*
    *
    */
    private Long companyId;
     /*
     * 
     * 
     */
    private Long projectId;
    /*
    *
    */
    private Date createTime;
    /*
    *
    */
    @TableLogic
    private Integer ifDelete;
    /*
    *
    */
    private Long jasoUserId;

    public void setNatureId(Long natureId) {
        this.natureId = natureId;
    }

    public Long getNatureId() {
        return this.natureId;
    }

    public void setNatureName(String natureName) {
        this.natureName = natureName;
    }

    public String getNatureName() {
        return this.natureName;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return this.type;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getProjectId() {
        return this.projectId;
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

    public void setJasoUserId(Long jasoUserId) {
        this.jasoUserId = jasoUserId;
    }

    public Long getJasoUserId() {
        return this.jasoUserId;
    }

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

}