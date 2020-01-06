package com.yaj.jaso.business.constructcontenttype.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;

/*
 * @Description: 工作内容的内容分类表
 * @date: 2019-08-10
 */
@TableName(value = "construct_content_type")
public class ConstructContentTypePO {
    /*
    *
    */
    @TableId
    private Long constructContentTypeId;
    /*
    *施工内容类型名称
    */
    private String constructContentTypeName;
    /*
    *
    */
    private Long companyId;
    /*
    *项目id
    */
    private Long projectId;
    /*
    *
    */
    @TableLogic
    private Integer ifDelete;

    public void setConstructContentTypeId(Long constructContentTypeId) {
        this.constructContentTypeId = constructContentTypeId;
    }

    public Long getConstructContentTypeId() {
        return this.constructContentTypeId;
    }

   
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getCompanyId() {
        return this.companyId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getProjectId() {
        return this.projectId;
    }

    public void setIfDelete(Integer ifDelete) {
        this.ifDelete = ifDelete;
    }

    public Integer getIfDelete() {
        return this.ifDelete;
    }

	public String getConstructContentTypeName() {
		return constructContentTypeName;
	}

	public void setConstructContentTypeName(String constructContentTypeName) {
		this.constructContentTypeName = constructContentTypeName;
	}

}