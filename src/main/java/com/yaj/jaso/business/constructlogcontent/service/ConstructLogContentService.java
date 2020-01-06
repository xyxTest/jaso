package com.yaj.jaso.business.constructlogcontent.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.yaj.jaso.business.constructlogcontent.entity.po.ConstructLogContentPO;
import com.yaj.jaso.business.constructlogcontent.mapper.ConstructLogContentMapper;

/*
 * @Description: 
 * @date: 2019-08-09
 */
@Service
public class ConstructLogContentService extends ServiceImpl<ConstructLogContentMapper, ConstructLogContentPO> {

    @Resource
    ConstructLogContentMapper constructLogContentMapper;

}
