package com.yaj.jaso.business.materialloglist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yaj.common.base.pojo.MulSelect;
import com.yaj.common.base.service.ServiceMain;
import com.yaj.jaso.business.jasouser.entity.po.JasoUserPO;
import com.yaj.jaso.business.materialloglist.entity.po.MaterialLogListPO;
import com.yaj.jaso.business.materialloglist.entity.vo.MaterialLogListVo;
import com.yaj.jaso.business.materialloglist.mapper.MaterialLogListMapper;

/*
 * @Description: 
 * @date: 2019-08-15
 */
@Service
public class MaterialLogListService extends ServiceImpl<MaterialLogListMapper, MaterialLogListPO> {

    @Resource
    MaterialLogListMapper materialLogListMapper;
    @Autowired
    ServiceMain serviceMain;
	public Object selectLists(MaterialLogListVo mll) {
		// TODO Auto-generated method stub
		MulSelect mul = MulSelect.newInstance("${1}.userRealName", new MaterialLogListPO(), new JasoUserPO());
		mul.setPage(mll.getPageVo().getPageNo(), mll.getPageVo().getPageSize());
		mul.where("${material_log_list}")
			.eq(mll.getProjectId()!=null,"projectId", mll.getProjectId())
			.eq(mll.getLogType()!=null,"logType", mll.getLogType())
			.eq(mll.getCompanyId()!=null,"companyId", mll.getCompanyId());
		mul.setOrderBy("materialloglist.create_time desc");
		return serviceMain.mulSelect(mul);
	}

}
