package com.yaj.jaso.business.measurepoint.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableLogic;

/*
 * @Description: 
 * @date: 2019-08-27
 */
@TableName(value = "measure_point")
public class MeasurePointPO {
    /*
    *
    */
    @TableId
    private Long measurePointId;
    /*
    *
    */
    private Long projectId;
    /*
    *
    */
    private Long companyId;
    /*
    *
    */
    private Long measurePaperId;
    /*
    *横坐标
    */
    private Integer x;
    /*
    *纵坐标
    */
    private Integer y;
    /*
    *测点序号
    */
    private Integer label;
   
    /*
    *
    */
    private Long measureSiteId;
    /*
    *创建人
    */
    private Long jasoUserId;
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
     *1、在图纸内设置的（默认） 2、房间设置的 
     */
    private Integer pointType;

    public void setMeasurePointId(Long measurePointId) {
        this.measurePointId = measurePointId;
    }

    public Long getMeasurePointId() {
        return this.measurePointId;
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

    public void setMeasurePaperId(Long measurePaperId) {
        this.measurePaperId = measurePaperId;
    }

    public Long getMeasurePaperId() {
        return this.measurePaperId;
    }

  

    public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public Integer getLabel() {
		return label;
	}

	public void setLabel(Integer label) {
		this.label = label;
	}

    public void setMeasureSiteId(Long measureSiteId) {
        this.measureSiteId = measureSiteId;
    }

    public Long getMeasureSiteId() {
        return this.measureSiteId;
    }

    public void setJasoUserId(Long jasoUserId) {
        this.jasoUserId = jasoUserId;
    }

    public Long getJasoUserId() {
        return this.jasoUserId;
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

	public Integer getPointType() {
		return pointType;
	}

	public void setPointType(Integer pointType) {
		this.pointType = pointType;
	}

}