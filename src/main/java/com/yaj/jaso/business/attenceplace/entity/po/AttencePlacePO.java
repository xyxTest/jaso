package com.yaj.jaso.business.attenceplace.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableLogic;

/*
 * @Description: 考勤地点表
 * @date: 2019-11-05
 */
@TableName(value = "attence_place")
public class AttencePlacePO {
    /*
    *
    */
    @TableId
    private Long attencePlaceId;
    /*
    *地点名称
    */
    private String placeName;
    /*
    *经度
    */
    private String lng;
    /*
    *
    */
    private Long companyId;
    /*
    *纬度
    */
    private String lat;
    /*
    *
    */
    private Date createTime;
    /*
    *
    */
    @TableLogic
    private Integer ifDelete;

    public void setAttencePlaceId(Long attencePlaceId) {
        this.attencePlaceId = attencePlaceId;
    }

    public Long getAttencePlaceId() {
        return this.attencePlaceId;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPlaceName() {
        return this.placeName;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLng() {
        return this.lng;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getCompanyId() {
        return this.companyId;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLat() {
        return this.lat;
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

}