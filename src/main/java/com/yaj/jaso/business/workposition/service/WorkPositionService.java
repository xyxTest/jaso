package com.yaj.jaso.business.workposition.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.yaj.jaso.business.workposition.entity.po.WorkPositionPO;
import com.yaj.jaso.business.workposition.mapper.WorkPositionMapper;

/*
 * @Description: 
 * @date: 2019-12-02
 */
@Service
public class WorkPositionService extends ServiceImpl<WorkPositionMapper, WorkPositionPO> {

    @Resource
    WorkPositionMapper workPositionMapper;

}
