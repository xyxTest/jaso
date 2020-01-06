package com.yaj.jaso.business.securityfine.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableLogic;

/*
 * @Description: 质量奖惩单
 * @date: 2019-09-07
 */
@TableName(value = "security_fine")
public class SecurityFinePO {
    /*
    *
    */
    @TableId
    private Long securityFineId;
    /*
    *
    */
    private Long jasoUserId;
    /*
    *
    */
    private Integer type;
    private Long projectId;
    /*
    *
    */
    private Long companyId;
    /*
    *
    */
    private String remark;
    /*
    *
    */
    private Long securityCheckId;
    /*
    *
    */
    private String voiceFiles;
    /*
    *
    */
    private String imageFiles;
    /*
    *
    */
    private Integer money;
    /*
    *
    */
    private Date createTime;
    /*
    *
    */
    @TableLogic
    private Integer ifDelete;

    public void setSecurityFineId(Long securityFineId) {
        this.securityFineId = securityFineId;
    }

    public Long getSecurityFineId() {
        return this.securityFineId;
    }

    public void setJasoUserId(Long jasoUserId) {
        this.jasoUserId = jasoUserId;
    }

    public Long getJasoUserId() {
        return this.jasoUserId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getProjectId() {
        return this.projectId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getCompanyId() {
        return this.companyId;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setSecurityCheckId(Long securityCheckId) {
        this.securityCheckId = securityCheckId;
    }

    public Long getSecurityCheckId() {
        return this.securityCheckId;
    }


    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getMoney() {
        return this.money;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getVoiceFiles() {
		return voiceFiles;
	}

	public void setVoiceFiles(String voiceFiles) {
		this.voiceFiles = voiceFiles;
	}

	public String getImageFiles() {
		return imageFiles;
	}

	public void setImageFiles(String imageFiles) {
		this.imageFiles = imageFiles;
	}

}