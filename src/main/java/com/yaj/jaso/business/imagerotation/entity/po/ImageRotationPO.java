package com.yaj.jaso.business.imagerotation.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableLogic;

/*
 * @Description: 
 * @date: 2019-07-25
 */
@TableName(value = "image_rotation")
public class ImageRotationPO {
    /*
    *
    */
    @TableId
    private Long imageRotationId;
    /*
    *图片地址
    */
    private String imageRotationUrl;
    /*
    *图片链接地址
    */
    private String imageRotationLinkUrl;
    /*
    *
    */
    private Long createUser;
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

    public void setImageRotationId(Long imageRotationId) {
        this.imageRotationId = imageRotationId;
    }

    public Long getImageRotationId() {
        return this.imageRotationId;
    }

    public void setImageRotationUrl(String imageRotationUrl) {
        this.imageRotationUrl = imageRotationUrl;
    }

    public String getImageRotationUrl() {
        return this.imageRotationUrl;
    }

    public void setImageRotationLinkUrl(String imageRotationLinkUrl) {
        this.imageRotationLinkUrl = imageRotationLinkUrl;
    }

    public String getImageRotationLinkUrl() {
        return this.imageRotationLinkUrl;
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

}