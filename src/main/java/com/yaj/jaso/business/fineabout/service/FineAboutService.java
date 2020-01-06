package com.yaj.jaso.business.fineabout.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.yaj.jaso.business.fineabout.entity.po.FineAboutPO;
import com.yaj.jaso.business.fineabout.mapper.FineAboutMapper;

/*
 * @Description: 
 * @date: 2019-09-07
 */
@Service
public class FineAboutService extends ServiceImpl<FineAboutMapper, FineAboutPO> {

    @Resource
    FineAboutMapper fineAboutMapper;

}
