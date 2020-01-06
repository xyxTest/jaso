package com.yaj.jaso.business.qualitychecktype.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;

/*
 * @Description: 
 * @date: 2019-09-05
 */
@TableName(value = "quality_check_type")
public class QualityCheckTypePO {
    /*
    *
    */
    @TableId
    private Long qualityCheckTypeId;
    private String name;
    private Long companyId;
    private Long projectId;
    /*
    *1、质量  2、安全 
    */
    private Integer type;
    /*
     * 
     *质量或安全id 
     */
    private Long aboutId;
    /*
    *
    */
    private Long projectCheckTypeId;
    /*
    *0、合格 1、不合格
    */
    private Integer state;
    /*
    *描述
    */
    private String describe;
    /*
    *语音或图片
    */
    private String voiceFiles;
    /*
     *图片
     */
     private String imageFiles;
     @TableLogic
     private Integer ifDelete;

    public Integer getIfDelete() {
		return ifDelete;
	}

	public void setIfDelete(Integer ifDelete) {
		this.ifDelete = ifDelete;
	}

	public void setQualityCheckTypeId(Long qualityCheckTypeId) {
        this.qualityCheckTypeId = qualityCheckTypeId;
    }

    public Long getQualityCheckTypeId() {
        return this.qualityCheckTypeId;
    }

    public void setProjectCheckTypeId(Long projectCheckTypeId) {
        this.projectCheckTypeId = projectCheckTypeId;
    }

    public Long getProjectCheckTypeId() {
        return this.projectCheckTypeId;
    }

   

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getDescribe() {
        return this.describe;
    }

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getAboutId() {
		return aboutId;
	}

	public void setAboutId(Long aboutId) {
		this.aboutId = aboutId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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