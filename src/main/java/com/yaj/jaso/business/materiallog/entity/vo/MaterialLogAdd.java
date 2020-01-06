package com.yaj.jaso.business.materiallog.entity.vo;

import java.util.List;
import com.yaj.jaso.business.material.entity.vo.MaterialAdd;
import com.yaj.jaso.business.materiallog.entity.po.MaterialLogPO;

public class MaterialLogAdd {
	private MaterialLogPO log;
	private List<MaterialAdd> materialList;
	public MaterialLogPO getLog() {
		return log;
	}
	public void setLog(MaterialLogPO log) {
		this.log = log;
	}
	public List<MaterialAdd> getMaterialList() {
		return materialList;
	}
	public void setMaterialList(List<MaterialAdd> materialList) {
		this.materialList = materialList;
	}
	
}
