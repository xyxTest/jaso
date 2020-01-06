package com.yaj.jaso.business.projectbuilding.entity.vo;

public class RoomInfo {
	/** 
	  * 节点编号 
	  */  
	 public String value;  
	 /** 
	  * 节点内容 
	  */  
	 public String label;  
	 /** 
	  * 父节点编号 
	  */  
	 public String parentId;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}  
	 
}
