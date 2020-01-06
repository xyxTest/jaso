package com.yaj.jaso.business.constructcontent.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;

/*
 * @Description: 
 * @date: 2019-08-09
 */
@TableName(value = "construct_content")
public class ConstructContentPO {
    /*
    *
    */
    @TableId
    private Long constructContentId;
    /*
    *
    */
    private String constructContentName;
    /*
    *单位
    */
    private String constructContentUnit;
    /*
     *编码
     */
    private String contentCode;
    /*
    *
    */
    private Long companyId;
    /*
    *
    */
    private Long workTypeId;//工种id
    /*
    *
    */
    private Long pid;
    /*
    *
    */
    @TableLogic
    private Integer ifDelete;

    public void setConstructContentId(Long constructContentId) {
        this.constructContentId = constructContentId;
    }

    public Long getConstructContentId() {
        return this.constructContentId;
    }

    public void setConstructContentName(String constructContentName) {
        this.constructContentName = constructContentName;
    }

    public String getConstructContentName() {
        return this.constructContentName;
    }

    public void setConstructContentUnit(String constructContentUnit) {
        this.constructContentUnit = constructContentUnit;
    }

    public String getConstructContentUnit() {
        return this.constructContentUnit;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getCompanyId() {
        return this.companyId;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Long getPid() {
        return this.pid;
    }

    public void setIfDelete(Integer ifDelete) {
        this.ifDelete = ifDelete;
    }

    public Integer getIfDelete() {
        return this.ifDelete;
    }

	public String getContentCode() {
		return contentCode;
	}

	public void setContentCode(String contentCode) {
		this.contentCode = contentCode;
	}

	public Long getWorkTypeId() {
		return workTypeId;
	}

	public void setWorkTypeId(Long workTypeId) {
		this.workTypeId = workTypeId;
	}

}