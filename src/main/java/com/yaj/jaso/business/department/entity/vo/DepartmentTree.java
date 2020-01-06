package com.yaj.jaso.business.department.entity.vo;

public class DepartmentTree {
	private Long value;
	private String label;
	private Long pid;
	private Integer ifDelete;
	public Long getValue() {
		return value;
	}
	public void setValue(Long value) {
		this.value = value;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	public Integer getIfDelete() {
		return ifDelete;
	}
	public void setIfDelete(Integer ifDelete) {
		this.ifDelete = ifDelete;
	}
	
}
