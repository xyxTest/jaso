package com.yaj.common.base.service;
 
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yaj.common.base.mapper.MainMapper;
import com.yaj.common.base.pojo.MulSelect;
import com.yaj.common.base.pojo.PageVo;
import com.yaj.jaso.business.qualitycheck.entity.vo.QualityCheckGet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
 
@Service
public class ServiceMain {
	@Resource
	private MainMapper mainMapper;

    public Object mulSelect(MulSelect param) {
    	Object data = mainMapper.mulSelect(param);
    	if (param.getStart() != null) {
    		Integer total = mainMapper.countMulSelect(param);
    		Map<String, Object> result = new  HashMap<>();
	    	result.put("data", data);
	    	PageVo page = new PageVo();
		    	page.setPageSize(param.getEnd());
		    	page.setTotal(total); 
		    	page.setPageNo(param.getStart() / param.getEnd() +1);
	    	result.put("page", page);
	    	return result;
    	}
    	return data;
    	 
    }
   
    
//    public ExcelDto readExcel(MultipartFile file, String keys) throws Exception{
//    	
//		List<ExcelSheet> esList = ExcelFactory.getExcelSheets(file.getInputStream());
//		
//		if (esList.size() > 0) {
//			ExcelSheet es = esList.get(0);
//			List<Map<String, Object>> data = es.getMapListBySheetData(keys);
//			List<Object> header = es.getSheetHeader();
//			String[] keysArr = keys.split(",");
//			Map<String,Object> columns = new LinkedHashMap();
//			for (int i = 0; i < keysArr.length; i++) {
//				columns.put(keysArr[i], header.get(i));
//			}
//			ExcelDto excelDto = new ExcelDto();
//			excelDto.setColumns(columns);
//			excelDto.setDatas(data);
//			return excelDto;
//		}
//		return null;
//	}
    
  
}
