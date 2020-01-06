package com.yaj.jaso.business.materiallog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yaj.common.base.pojo.MulSelect;
import com.yaj.common.base.service.ServiceMain;
import com.yaj.common.threadlocal.ThreadlocalManager;
import com.yaj.core.util.BeanUtil;
import com.yaj.jaso.business.material.entity.po.MaterialPO;
import com.yaj.jaso.business.material.service.MaterialService;
import com.yaj.jaso.business.materiallog.entity.po.MaterialLogPO;
import com.yaj.jaso.business.materiallog.entity.vo.MaterialLogAdd;
import com.yaj.jaso.business.materiallog.entity.vo.MaterialLogPcAdd;
import com.yaj.jaso.business.materiallog.entity.vo.MaterialLogVo;
import com.yaj.jaso.business.materiallog.mapper.MaterialLogMapper;
import com.yaj.jaso.business.materialloglist.entity.po.MaterialLogListPO;
import com.yaj.jaso.business.materialloglist.service.MaterialLogListService;
import com.yaj.jaso.business.jasouser.entity.po.JasoUserPO;

/*
 * @Description: 
 * @date: 2019-07-22
 */
@Service
public class MaterialLogService extends ServiceImpl<MaterialLogMapper, MaterialLogPO> {

    @Resource
    MaterialLogMapper materialLogMapper;
    @Autowired
    MaterialService materialService;
    @Autowired
    MaterialLogListService materialLogListService;
    @Autowired
    ServiceMain serviceMain;
	public Object add(MaterialLogPO company) {
		return null;
	}
	public Object selectListByPages(MaterialLogVo company) {
		// TODO Auto-generated method stub
		JasoUserPO cacheUser=(JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		MulSelect mul = MulSelect.newInstance("${1}.userRealName,${2}.materialName,${2}.materialSize,${2}.materialUnit,${2}.price", new MaterialLogPO(), new JasoUserPO(),new MaterialPO());
		mul.setPage(company.getPageVo().getPageNo(), company.getPageVo().getPageSize());
		mul.where("${material_log}")
			.eq(company.getProjectId()!=null,"projectId", company.getProjectId())
			.eq(company.getLogType()!=null,"logType", company.getLogType())
			.eq(company.getMaterialId()!=null,"materialId", company.getMaterialId())
			.eq(company.getMaterialLogListId()!=null,"materialLogListId", company.getMaterialLogListId())
			.eq("companyId", cacheUser.getCompanyId());
		mul.setOrderBy("materiallog.create_time desc");		
		return serviceMain.mulSelect(mul);
	}
	public Object addList(MaterialLogAdd list) {
		boolean result=false;
		JasoUserPO cacheUser=(JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		if(!list.getMaterialList().isEmpty()){
			List<MaterialPO> materialList = new ArrayList<>();
			for(int i=0;i<list.getMaterialList().size();i++){
				MaterialPO materialpo = new MaterialPO();
				BeanUtil.copy(list.getMaterialList().get(i), materialpo);
				if(list.getLog().getLogType()==0){
					if(materialpo.getLeaveNum()==null){
						materialpo.setLeaveNum(0);
					}
					materialpo.setLeaveNum(materialpo.getLeaveNum()+list.getMaterialList().get(i).getCurrentInputNum());
					materialpo.setPutNum(materialpo.getPutNum()+list.getMaterialList().get(i).getCurrentInputNum());
				}else if(list.getLog().getLogType()==1){
					materialpo.setLeaveNum(materialpo.getLeaveNum()-list.getMaterialList().get(i).getCurrentInputNum());
					materialpo.setOutNum(materialpo.getOutNum()+list.getMaterialList().get(i).getCurrentInputNum());
				}
				materialpo.setCompanyId(cacheUser.getCompanyId());
				materialList.add(materialpo);
			}
			if(materialService.insertOrUpdateBatch(materialList)){
				MaterialLogListPO listpo = new MaterialLogListPO();
				if(list.getLog().getJasoUserId()!=null){
					listpo.setJasoUserId(list.getLog().getJasoUserId());
				}else{
					listpo.setJasoUserId(cacheUser.getJasoUserId());
				}
				listpo.setCreateTime(list.getLog().getInputDate());
				listpo.setCompanyId(list.getLog().getCompanyId());
				listpo.setLogType(list.getLog().getLogType());
				listpo.setProjectId(list.getLog().getProjectId());
				listpo.setListNum(materialList.size());
				listpo.setMaterialFrom(list.getLog().getMaterialFrom());
				if(materialLogListService.insert(listpo)){
					List<MaterialLogPO> logList = new ArrayList<>();
					for(int i=0;i<materialList.size();i++){
						MaterialLogPO logpo = new MaterialLogPO();
						logpo.setCompanyId(materialList.get(i).getCompanyId());
						if(list.getLog().getJasoUserId()!=null){
							logpo.setJasoUserId(list.getLog().getJasoUserId());
						}else{
							logpo.setJasoUserId(cacheUser.getJasoUserId());
						}
						logpo.setLogType(list.getLog().getLogType());
						logpo.setLogNum(list.getMaterialList().get(i).getCurrentInputNum());
						logpo.setInputDate(list.getLog().getInputDate());
						logpo.setMaterialFrom(list.getLog().getMaterialFrom());
						logpo.setRemark(list.getLog().getRemark());
						logpo.setMaterialId(materialList.get(i).getMaterialId());
						logpo.setMaterialPrice(list.getLog().getMaterialPrice());
						logpo.setProjectId(list.getLog().getProjectId());
						logpo.setMaterialLogListId(listpo.getMaterialLogListId());
						logList.add(logpo);
					}
					result=insertBatch(logList);
				}
			}	
		}
		return result;
	}
	public Object addPcList(MaterialLogPcAdd gets) {
		// TODO Auto-generated method stub
		boolean result=false;
		JasoUserPO cacheUser=(JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		if(gets.getMaterialId()!=null){
			MaterialPO material = new MaterialPO();
			material=materialService.selectById(gets.getMaterialId());
			if(material!=null){
				if(gets.getLogType()==0){
					if(material.getLeaveNum()==null){
						material.setLeaveNum(0);
					}
					material.setLeaveNum(material.getLeaveNum()+gets.getLogNum());
					material.setPutNum(material.getPutNum()+gets.getLogNum());
				}else if(gets.getLogType()==1){
					material.setLeaveNum(material.getLeaveNum()-gets.getLogNum());
					material.setOutNum(material.getOutNum()+gets.getLogNum());
				}
				if(materialService.insertOrUpdate(material)){
					MaterialLogPO log = new MaterialLogPO();
					log.setCompanyId(cacheUser.getCompanyId());
					log.setInputDate(new Date());
					log.setProjectId(gets.getProjectId());
					log.setLogType(gets.getLogType());
					log.setLogNum(gets.getLogNum());
					log.setJasoUserId(cacheUser.getJasoUserId());
					log.setMaterialId(material.getMaterialId());
					return insertOrUpdate(log);
				}
			}
		}
		return result;
	}
}
