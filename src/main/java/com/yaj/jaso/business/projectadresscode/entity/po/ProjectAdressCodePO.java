package com.yaj.jaso.business.projectadresscode.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;


/*
 * @Description: 
 * @date: 2019-08-13
 */
@TableName(value = "project_adress_code")
public class ProjectAdressCodePO {
    /*
    *
    */
    @TableId
    private Long projectAdressCodeId;
    /*
    *
    */
    private String cityName;
    /*
    *
    */
    private String adcode;
    /*
    *
    */
    private String citycode;
    @TableLogic
    private Integer ifDelete;

    public void setProjectAdressCodeId(Long projectAdressCodeId) {
        this.projectAdressCodeId = projectAdressCodeId;
    }

    public Long getProjectAdressCodeId() {
        return this.projectAdressCodeId;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return this.cityName;
    }

    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }

    public String getAdcode() {
        return this.adcode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getCitycode() {
        return this.citycode;
    }

	public Integer getIfDelete() {
		return ifDelete;
	}

	public void setIfDelete(Integer ifDelete) {
		this.ifDelete = ifDelete;
	}
    
}