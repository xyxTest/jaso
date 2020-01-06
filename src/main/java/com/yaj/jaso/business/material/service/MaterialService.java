package com.yaj.jaso.business.material.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yaj.common.base.pojo.MulSelect;
import com.yaj.common.base.pojo.WhereCustomSegment;
import com.yaj.common.base.service.ServiceMain;
import com.yaj.common.threadlocal.ThreadlocalManager;
import com.yaj.core.util.BeanUtil;
import com.yaj.jaso.business.material.entity.po.MaterialPO;
import com.yaj.jaso.business.material.entity.vo.ImportMaterial;
import com.yaj.jaso.business.material.entity.vo.MaterialVo;
import com.yaj.jaso.business.material.mapper.MaterialMapper;
import com.yaj.jaso.business.materialimportlog.entity.po.MaterialImportLogPO;
import com.yaj.jaso.business.materialimportlog.service.MaterialImportLogService;
import com.yaj.jaso.business.materiallog.entity.po.MaterialLogPO;
import com.yaj.jaso.business.materiallog.service.MaterialLogService;
import com.yaj.jaso.business.materialloglist.entity.po.MaterialLogListPO;
import com.yaj.jaso.business.materialloglist.service.MaterialLogListService;
import com.yaj.jaso.business.materialtype.entity.MaterialTypePO;
import com.yaj.jaso.business.materialtype.service.MaterialTypeService;
import com.yaj.jaso.business.jasouser.entity.po.JasoUserPO;
import com.yaj.jaso.global.PageVoUtil;
import com.yaj.xyx.util.MD5Util;
import com.yaj.xyx.util.ReadMaterialExcel;
import com.yaj.xyx.util.WebFileUtils;

/*
 * @Description: 
 * @date: 2019-07-22
 */
@Service
public class MaterialService extends ServiceImpl<MaterialMapper, MaterialPO> {

    @Resource
    MaterialMapper MaterialMapper;
    @Autowired
    MaterialImportLogService logService;
    @Autowired
    MaterialLogService logsService;
    @Autowired
    MaterialLogListService logListService;
    @Autowired
    MaterialTypeService typeService;
    @Autowired
    ServiceMain service;
	public Object selectListByCompanyId(MaterialPO dpo) {
		// TODO Auto-generated method stub
		Wrapper<MaterialPO> wrapper = new EntityWrapper<MaterialPO>();
		wrapper.eq("company_id", dpo.getCompanyId());
        return selectList(wrapper);
	}

	public Object add(MaterialPO dpo) {
		// TODO Auto-generated method stub
		JasoUserPO cacheUser=(JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		dpo.setCompanyId(cacheUser.getCompanyId());
		return insert(dpo);
	}

	public Object deleteMaterialList(List<MaterialPO> dpo) {
		for(int i=0;i<dpo.size();i++){
			dpo.get(i).setIfDelete(1);
		}
		// TODO Auto-generated method stub
		return updateBatchById(dpo);
	}

	public Object selectListPage(MaterialVo mpo) {
		// TODO Auto-generated method stub
		Page<MaterialPO> page = new Page<MaterialPO>();
		page.setCurrent(mpo.getPageVo().getPageNo());
		page.setSize(mpo.getPageVo().getPageSize());
		Wrapper<MaterialPO> wrapper = new EntityWrapper<MaterialPO>();
		wrapper.orderDesc(Arrays.asList("create_time"));
		if(mpo.getMaterialName()!=null){
			wrapper.like("material_name", mpo.getMaterialName());
		}
		if(mpo.getProjectId()!=null){
			wrapper.eq("project_id", mpo.getProjectId());
		}
		if(mpo.getCompanyId()!=null){
			wrapper.eq("company_id", mpo.getCompanyId());
		}
		if(mpo.getMaterialTypeId()!=null){
			wrapper.eq("material_type_id", mpo.getMaterialTypeId());
		}
		MaterialPO po = new MaterialPO();
		BeanUtil.copy(mpo, po);
		page=selectPage(page, wrapper);
        return PageVoUtil.setPage(page);
	}

	public Object importMaterial(MultipartFile file, HttpServletRequest request, Long projectId) {
		boolean result=true;
		JasoUserPO cacheUser=(JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		List<ImportMaterial> ms = new ArrayList<ImportMaterial>();
		ReadMaterialExcel rm = new ReadMaterialExcel();
		String newFileName = MD5Util.getMD5String(file.getOriginalFilename() + new Date() + UUID.randomUUID().toString()).replace(".","")
                + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		ms=rm.getExcelInfo(newFileName, file);
		List<MaterialPO> polist = new ArrayList<MaterialPO>();
		List<Integer> numList = new ArrayList<Integer>();
		for(ImportMaterial im:ms){
			MaterialPO m = new MaterialPO();
			m.setProjectId(projectId);
			m.setMaterialName(im.getMaterialName());
			m.setMaterialUnit(im.getUnit());
			m.setMaterialSize(im.getSize());
			m.setPrice(Double.valueOf(im.getPrice()));
			m.setRemark(im.getRemark());
			m.setLeaveNum(Integer.parseInt(im.getNums()));
			numList.add(Integer.parseInt(im.getNums()));
			m.setPutNum(Integer.parseInt(im.getNums()));
			if(im.getMaterialType()!=null){
				MaterialTypePO sp = new MaterialTypePO();
				sp.setProjectId(projectId);
				if(typeService.getMaterialTypeByName(im.getMaterialType())!=null){
					sp=typeService.getMaterialTypeByName(im.getMaterialType());
					m.setMaterialTypeId(sp.getMaterialTypeId());
				}else{
					sp.setCompanyId(cacheUser.getCompanyId());
					sp.setProjectId(projectId);
					sp.setMaterialTypeName(im.getMaterialType());
					if(typeService.insert(sp)){
						m.setMaterialTypeId(sp.getMaterialTypeId());
					}
				}
			}
			MaterialPO finds = new MaterialPO();
			Wrapper<MaterialPO> likeWrapper = new EntityWrapper<MaterialPO>();
			likeWrapper.eq("project_id", m.getProjectId());
			likeWrapper.eq("material_type_id", m.getMaterialTypeId());
			likeWrapper.eq("material_name", m.getMaterialName());
			likeWrapper.eq("material_size", m.getMaterialSize());
			if(!selectList(likeWrapper).isEmpty()){
				finds=selectList(likeWrapper).get(0);
				finds.setPutNum(finds.getPutNum()+Integer.parseInt(im.getNums()));
				finds.setLeaveNum(finds.getLeaveNum()+Integer.parseInt(im.getNums()));
			}else{
				finds=m;
			}
			polist.add(finds);
		}
		if(insertOrUpdateBatch(polist)){
			MaterialLogListPO loglist = new MaterialLogListPO();
			loglist.setJasoUserId(cacheUser.getJasoUserId());
			loglist.setCreateTime(new Date());
			loglist.setListNum(polist.size());
			loglist.setProjectId(projectId);
			loglist.setLogType(0);
			loglist.setMaterialFrom(polist.get(0).getRemark());
			if(logListService.insert(loglist)){
				List<MaterialLogPO> logPOs = new ArrayList<MaterialLogPO>();
				for(int i=0;i<polist.size();i++){
					MaterialLogPO ml = new MaterialLogPO();
					ml.setMaterialLogListId(loglist.getMaterialLogListId());
					ml.setLogType(0);
					ml.setCreateTime(new Date());
					ml.setCompanyId(cacheUser.getCompanyId());
					ml.setJasoUserId(cacheUser.getJasoUserId());
					ml.setMaterialId(polist.get(i).getMaterialId());
					ml.setLogNum(numList.get(i));
					ml.setRemark(polist.get(i).getMaterialName());
					ml.setProjectId(projectId);
					logPOs.add(ml);
				}
				logsService.insertBatch(logPOs);
			}
			
		}else{
			result=false;
		}
		return result;
	}

	public Object importAppMaterial(String htmlUrl, String fileUrl, MaterialVo material) {
		JasoUserPO cacheUser=(JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		boolean result = true;
		if(htmlUrl!=null){
			MaterialImportLogPO mil = new MaterialImportLogPO();
			mil.setImportFileUrl(htmlUrl);
			mil.setProjectId(material.getProjectId());
			mil.setCompanyId(cacheUser.getCompanyId());
			mil.setCreateUser(cacheUser.getJasoUserId());
			logService.insert(mil);
		}
		if(fileUrl!=null){
			//MultipartFile file = WebFileUtils.getMultipartFile(fileUrl);
			InputStream fileIn=WebFileUtils.getInputStream(fileUrl);
			List<ImportMaterial> ms = new ArrayList<ImportMaterial>();
			ReadMaterialExcel rm = new ReadMaterialExcel();
			String newFileName = MD5Util.getMD5String(new Date() + UUID.randomUUID().toString()).replace(".","")+".xls";
			ms=rm.getExcelInfoByStream(newFileName, fileIn);
			List<MaterialPO> polist = new ArrayList<MaterialPO>();
			List<Integer> numList = new ArrayList<Integer>();
			for(ImportMaterial im:ms){
				MaterialPO m = new MaterialPO();
				m.setProjectId(material.getProjectId());
				m.setMaterialName(im.getMaterialName());
				m.setMaterialUnit(im.getUnit());
				m.setMaterialSize(im.getSize());
				m.setPrice(Double.valueOf(im.getPrice()));
				m.setRemark(im.getRemark());
				m.setLeaveNum(Integer.parseInt(im.getNums()));
				numList.add(Integer.parseInt(im.getNums()));
				m.setPutNum(Integer.parseInt(im.getNums()));
				if(im.getMaterialType()!=null){
					MaterialTypePO sp = new MaterialTypePO();
					sp.setProjectId(material.getProjectId());
					if(typeService.getMaterialTypeByName(im.getMaterialType())!=null){
						sp=typeService.getMaterialTypeByName(im.getMaterialType());
						m.setMaterialTypeId(sp.getMaterialTypeId());
					}else{
						sp.setCompanyId(cacheUser.getCompanyId());
						sp.setProjectId(material.getProjectId());
						sp.setMaterialTypeName(im.getMaterialType());
						if(typeService.insert(sp)){
							m.setMaterialTypeId(sp.getMaterialTypeId());
						}
					}
				}
				MaterialPO finds = new MaterialPO();
				Wrapper<MaterialPO> likeWrapper = new EntityWrapper<MaterialPO>();
				likeWrapper.eq("project_id", m.getProjectId());
				likeWrapper.eq("material_type_id", m.getMaterialTypeId());
				likeWrapper.eq("material_name", m.getMaterialName());
				likeWrapper.eq("material_size", m.getMaterialSize());
				if(!selectList(likeWrapper).isEmpty()){
					finds=selectList(likeWrapper).get(0);
					finds.setPutNum(finds.getPutNum()+Integer.parseInt(im.getNums()));
					finds.setLeaveNum(finds.getLeaveNum()+Integer.parseInt(im.getNums()));
				}else{
					finds=m;
				}
				polist.add(finds);
			}
			if(insertOrUpdateBatch(polist)){
				MaterialLogListPO loglist = new MaterialLogListPO();
				loglist.setJasoUserId(cacheUser.getJasoUserId());
				loglist.setCreateTime(new Date());
				loglist.setListNum(polist.size());
				loglist.setMaterialFrom(polist.get(0).getRemark());
				if(logListService.insert(loglist)){
					List<MaterialLogPO> logPOs = new ArrayList<MaterialLogPO>();
					for(int i=0;i<polist.size();i++){
						MaterialLogPO ml = new MaterialLogPO();
						ml.setMaterialLogListId(loglist.getMaterialLogListId());
						ml.setLogType(0);
						ml.setCreateTime(new Date());
						ml.setCompanyId(cacheUser.getCompanyId());
						ml.setJasoUserId(cacheUser.getJasoUserId());
						ml.setMaterialId(polist.get(i).getMaterialId());
						ml.setLogNum(numList.get(i));
						ml.setRemark(polist.get(i).getMaterialName());
						ml.setProjectId(material.getProjectId());
						logPOs.add(ml);
					}
					logsService.insertBatch(logPOs);
				}
				
			}else{
				result=false;
			}
		}
		return result;
	}

	public Object selectLists(MaterialVo company) {
		// TODO Auto-generated method stub
		MulSelect mul = MulSelect.newInstance("${1}.materialTypeName", new MaterialPO(), new MaterialTypePO());
		mul.setPage(company.getPageVo().getPageNo(), company.getPageVo().getPageSize());
		mul.where("${material}")
		.eq(company.getCompanyId()!=null,"companyId", company.getCompanyId())
		.eq(company.getProjectId()!=null,"projectId", company.getProjectId())
		.eq(company.getMaterialTypeId()!=null,"materialTypeId", company.getMaterialTypeId())
		.like(company.getMaterialName()!=null,"materialName", company.getMaterialName());
		mul.setOrderBy("material.material_id desc");		
		return service.mulSelect(mul);
	}

	public Object deleteListById(List<MaterialPO> company) {
		for(int i=0;i<company.size();i++){
			company.get(i).setIfDelete(1);
		}
		// TODO Auto-generated method stub
		return updateBatchById(company);
	}
}
