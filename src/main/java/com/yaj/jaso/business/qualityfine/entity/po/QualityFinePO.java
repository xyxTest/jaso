package com.yaj.jaso.business.qualityfine.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableLogic;

/*
 * @Description: 质量奖惩单
 * @date: 2019-09-07
 */
@TableName(value = "quality_fine")
public class QualityFinePO {
    /*
    *
    */
    @TableId
    private Long qualityFineId;
    /*
    *
    */
    private Long jasoUserId;
    /*
    *
    */
    private Long projectId;
    /*
    *
    */
    private Integer type;//1、奖励 2、惩罚
    private Long companyId;
    /*
    *
    */
    private String remark;
    /*
    *
    */
    private Long qualityCheckId;
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

    public void setQualityFineId(Long qualityFineId) {
        this.qualityFineId = qualityFineId;
    }

    public Long getQualityFineId() {
        return this.qualityFineId;
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

    public void setQualityCheckId(Long qualityCheckId) {
        this.qualityCheckId = qualityCheckId;
    }

    public Long getQualityCheckId() {
        return this.qualityCheckId;
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