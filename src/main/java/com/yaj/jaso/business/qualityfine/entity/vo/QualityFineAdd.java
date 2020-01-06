package com.yaj.jaso.business.qualityfine.entity.vo;

import java.util.List;
import com.yaj.jaso.business.fineabout.entity.po.FineAboutPO;
import com.yaj.jaso.business.qualityfine.entity.po.QualityFinePO;
public class QualityFineAdd extends QualityFinePO{

	private List<FineAboutPO> aboutIds;


	public List<FineAboutPO> getAboutIds() {
		return aboutIds;
	}
	public void setAboutIds(List<FineAboutPO> aboutIds) {
		this.aboutIds = aboutIds;
	}
	
}
