package com.yaj.jaso.business.company.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.yaj.jaso.business.company.entity.po.CompanyPO;
import com.yaj.jaso.business.company.mapper.CompanyMapper;

/*
 * @Description: 
 * @date: 2019-07-22
 */
@Service
public class CompanyService extends ServiceImpl<CompanyMapper, CompanyPO> {

    @Resource
    CompanyMapper companyMapper;

}
