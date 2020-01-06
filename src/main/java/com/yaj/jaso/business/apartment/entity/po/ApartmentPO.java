package com.yaj.jaso.business.apartment.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;

import com.baomidou.mybatisplus.annotations.TableLogic;
import java.math.BigDecimal;

/*
 * @Description: 
 * @date: 2019-11-28
 */
@TableName(value = "apartment")
public class ApartmentPO {
    /*
    *
    */
    @TableId
    private Long apartmentId;
    /*
    *户型名称
    */
    private String apartmentName;
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
    @TableLogic
    private Integer ifDelete;

    public void setApartmentId(Long apartmentId) {
        this.apartmentId = apartmentId;
    }

    public Long getApartmentId() {
        return this.apartmentId;
    }

    public void setApartmentName(String apartmentName) {
        this.apartmentName = apartmentName;
    }

    public String getApartmentName() {
        return this.apartmentName;
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

    public void setIfDelete(Integer ifDelete) {
        this.ifDelete = ifDelete;
    }

    public Integer getIfDelete() {
        return this.ifDelete;
    }

}