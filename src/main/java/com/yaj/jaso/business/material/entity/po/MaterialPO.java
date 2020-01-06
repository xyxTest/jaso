package com.yaj.jaso.business.material.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableLogic;

/*
 * @Description: 
 * @date: 2019-08-05
 */
@TableName(value = "material")
public class MaterialPO {
    /*
    *
    */
    @TableId
    private Long materialId;
    /*
     * 
     */
    private Long companyId;
    /*
     *项目id
     */
    private Long projectId;
    /*
    *物资名称
    */
    private String materialName;
    /*
     * 
     */
    private String materialCode;
    /*
    *物资型号、尺寸
    */
    private String materialSize;
    /*
    *物资单位
    */
    private String materialUnit;
    /*
     *单价 
     */
    private Double	price;
    
    /*
    *物资分类id
    */
    private Long materialTypeId;
    /*
    *入库数量
    */
    private Integer putNum;
    /*
     * 出库数量
     */
    private Integer outNum;
    /*
    *库存数量
    */
    private Integer leaveNum;
    /*
    *备注
    */
    private String remark;
    /*
    *是否常用（0、常用 1、不常用）
    */
    private Integer isCommon;
    /*
    *
    */
    private Date createTime;
    /*
    *
    */
    @TableLogic
    private Integer ifDelete;

   

    public Long getMaterialId() {
		return materialId;
	}

	public void setMaterialId(Long materialId) {
		this.materialId = materialId;
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

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getMaterialSize() {
		return materialSize;
	}

	public void setMaterialSize(String materialSize) {
		this.materialSize = materialSize;
	}

	public String getMaterialUnit() {
		return materialUnit;
	}

	public void setMaterialUnit(String materialUnit) {
		this.materialUnit = materialUnit;
	}

	public Long getMaterialTypeId() {
		return materialTypeId;
	}

	public void setMaterialTypeId(Long materialTypeId) {
		this.materialTypeId = materialTypeId;
	}

	public Integer getPutNum() {
		return putNum;
	}

	public void setPutNum(Integer putNum) {
		this.putNum = putNum;
	}

	public Integer getOutNum() {
		return outNum;
	}

	public void setOutNum(Integer outNum) {
		this.outNum = outNum;
	}

	public Integer getLeaveNum() {
		return leaveNum;
	}

	public void setLeaveNum(Integer leaveNum) {
		this.leaveNum = leaveNum;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getIsCommon() {
		return isCommon;
	}

	public void setIsCommon(Integer isCommon) {
		this.isCommon = isCommon;
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

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}