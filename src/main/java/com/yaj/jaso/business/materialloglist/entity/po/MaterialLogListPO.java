package com.yaj.jaso.business.materialloglist.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;

import java.util.Date;

/*
 * @Description: 
 * @date: 2019-08-15
 */
@TableName(value = "material_log_list")
public class MaterialLogListPO {
    /*
    *
    */
    @TableId
    private Long materialLogListId;
    
    private Long projectId;
    private Long companyId;
    /*
    *材料项总数
    */
    private Integer listNum;
    /*
    *
    */
    private Long jasoUserId;
    /*
    *
    */
    private Integer logType;
    /**/
    private String materialFrom;
    /*
    *
    */
    private Date createTime;
    @TableLogic
    private Integer ifDelete;

    public void setMaterialLogListId(Long materialLogListId) {
        this.materialLogListId = materialLogListId;
    }

    public Long getMaterialLogListId() {
        return this.materialLogListId;
    }

    public void setListNum(Integer listNum) {
        this.listNum = listNum;
    }

    public Integer getListNum() {
        return this.listNum;
    }

    public void setMaterialFrom(String materialFrom) {
        this.materialFrom = materialFrom;
    }

    public String getMaterialFrom() {
        return this.materialFrom;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

	public Long getJasoUserId() {
		return jasoUserId;
	}

	public void setJasoUserId(Long jasoUserId) {
		this.jasoUserId = jasoUserId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Integer getLogType() {
		return logType;
	}

	public void setLogType(Integer logType) {
		this.logType = logType;
	}

	public Integer getIfDelete() {
		return ifDelete;
	}

	public void setIfDelete(Integer ifDelete) {
		this.ifDelete = ifDelete;
	}

}