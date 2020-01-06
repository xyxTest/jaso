package com.yaj.jaso.business.qualitycheckusers.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableLogic;

/*
 * @Description: 
 * @date: 2019-09-05
 */
@TableName(value = "quality_check_users")
public class QualityCheckUsersPO {
    /*
    *
    */
    @TableId
    private Long qualityCheckUsersId;
    /*
    *(1、质量管理 2、安全管理)
    */
    private String uniqueKey;
    private Integer type;
    /*
    *(1、整改人 2、参与人 3、通知人)
    */
    private Integer userType;
    /*
     * 用户id
     */
    private Long jasoUserId;
    /*
     *用户真实姓名 
     */
    private String userRealName;
    /*
    *
    */
    private Long aboutId;
    /*
    *
    */
    private Long companyId;
    /*
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

    public void setQualityCheckUsersId(Long qualityCheckUsersId) {
        this.qualityCheckUsersId = qualityCheckUsersId;
    }

    public Long getQualityCheckUsersId() {
        return this.qualityCheckUsersId;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return this.type;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getUserType() {
        return this.userType;
    }

    public void setAboutId(Long aboutId) {
        this.aboutId = aboutId;
    }

    public Long getAboutId() {
        return this.aboutId;
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

	public String getUserRealName() {
		return userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}

	public Long getJasoUserId() {
		return jasoUserId;
	}

	public void setJasoUserId(Long jasoUserId) {
		this.jasoUserId = jasoUserId;
	}

	public String getUniqueKey() {
		return uniqueKey;
	}

	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

}