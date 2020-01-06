package com.yaj.jaso.business.qualitychecktype.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.yaj.jaso.business.qualitychecktype.entity.po.QualityCheckTypePO;
import com.yaj.jaso.business.qualitychecktype.mapper.QualityCheckTypeMapper;

/*
 * @Description: 
 * @date: 2019-09-05
 */
@Service
public class QualityCheckTypeService extends ServiceImpl<QualityCheckTypeMapper, QualityCheckTypePO> {

    @Resource
    QualityCheckTypeMapper qualityCheckTypeMapper;

}
