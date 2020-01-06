package com.yaj.jaso.business.materialtype.entity;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
@TableName(value = "material_type")
public class MaterialTypePO {
	 /*
    *
    */
    @TableId
    private Long materialTypeId;
    /*
     * 
     */
    private Long companyId;
    /*
     * 
     */
    private Long projectId;
    /*
    *物资分类名称
    */
    private String materialTypeName;
    /*
    *
    */
    @TableLogic
    private Integer ifDelete;
	public Long getMaterialTypeId() {
		return materialTypeId;
	}
	public void setMaterialTypeId(Long materialTypeId) {
		this.materialTypeId = materialTypeId;
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
	public String getMaterialTypeName() {
		return materialTypeName;
	}
	public void setMaterialTypeName(String materialTypeName) {
		this.materialTypeName = materialTypeName;
	}
	public Integer getIfDelete() {
		return ifDelete;
	}
	public void setIfDelete(Integer ifDelete) {
		this.ifDelete = ifDelete;
	}
    
}
