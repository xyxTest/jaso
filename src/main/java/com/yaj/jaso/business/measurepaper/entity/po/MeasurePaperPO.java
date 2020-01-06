package com.yaj.jaso.business.measurepaper.entity.po;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableLogic;

/*
 * @Description: 
 * @date: 2019-08-27
 */
@TableName(value = "measure_paper")
public class MeasurePaperPO {
    /*
    *
    */
    @TableId
    private Long measurePaperId;
    /*
    *
    */
    private Long projectId;
    /*
    *
    */
    private Long companyId;
    /*
     *图纸url 
     */
    private String paperUrl;
    /*
     *类型（1、设备安装 2、土建）默认设备安装 
     */
    private Integer type;
    /*
    *
    */
    private String measurePaperName;
    private Long apartmentId;
    /*
    *测点数量
    */
    private Integer measureNum;
    /*
    *(1、未标注 2、已标注)
    */
    private Integer measurePaperStatus;
    /*
    *
    */
    private Date createTime;
    /*
    *创建人id
    */
    private Long jasoUserId;
    /*
    *
    */
    @TableLogic
    private Integer ifDelete;

    public void setMeasurePaperId(Long measurePaperId) {
        this.measurePaperId = measurePaperId;
    }

    public Long getMeasurePaperId() {
        return this.measurePaperId;
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

    public void setMeasurePaperName(String measurePaperName) {
        this.measurePaperName = measurePaperName;
    }

    public String getMeasurePaperName() {
        return this.measurePaperName;
    }

    public void setMeasureNum(Integer measureNum) {
        this.measureNum = measureNum;
    }

    public Integer getMeasureNum() {
        return this.measureNum;
    }

    public void setMeasurePaperStatus(Integer measurePaperStatus) {
        this.measurePaperStatus = measurePaperStatus;
    }

    public Integer getMeasurePaperStatus() {
        return this.measurePaperStatus;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setJasoUserId(Long jasoUserId) {
        this.jasoUserId = jasoUserId;
    }

    public Long getJasoUserId() {
        return this.jasoUserId;
    }

    public void setIfDelete(Integer ifDelete) {
        this.ifDelete = ifDelete;
    }

    public Integer getIfDelete() {
        return this.ifDelete;
    }

	public String getPaperUrl() {
		return paperUrl;
	}

	public void setPaperUrl(String paperUrl) {
		this.paperUrl = paperUrl;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getApartmentId() {
		return apartmentId;
	}

	public void setApartmentId(Long apartmentId) {
		this.apartmentId = apartmentId;
	}

}