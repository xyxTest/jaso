package com.yaj.common.base.pojo;

import com.yaj.core.util.BeanUtil;

public abstract class BaseParameters<T> {
	private PageVo page;
	public T subParam;
	private String orderBy = null;
	protected Object[] entities = null;
	public abstract String operational(String currentColumn); 
	
	public abstract Class<?>[] entityClass();
	public abstract String columns();
	public T getSubParam() {
		return subParam;
	}

	public void setSubParam(T subParam) {
		this.subParam = subParam;
	}

	public PageVo getPage() {
		return page;
	}

	public void setPage(PageVo page) {
		this.page = page;
	}
	
	public Object[] entities() {
		if (entities == null) {
			entities  = BeanUtil.getEntities(getSubParam(), entityClass());
		}
		return entities;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	
}