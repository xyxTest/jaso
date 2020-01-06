package com.yaj.jaso.business.materialimportlog.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yaj.jaso.business.materialimportlog.entity.po.MaterialImportLogPO;
import com.yaj.jaso.business.materialimportlog.mapper.MaterialImportLogMapper;
@Service
public class MaterialImportLogService extends ServiceImpl<MaterialImportLogMapper, MaterialImportLogPO>{
	@Resource
	MaterialImportLogMapper mapper;
}
