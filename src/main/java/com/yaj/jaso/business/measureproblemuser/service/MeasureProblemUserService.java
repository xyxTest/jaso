package com.yaj.jaso.business.measureproblemuser.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.yaj.jaso.business.measureproblemuser.entity.po.MeasureProblemUserPO;
import com.yaj.jaso.business.measureproblemuser.mapper.MeasureProblemUserMapper;

/*
 * @Description: 
 * @date: 2019-09-02
 */
@Service
public class MeasureProblemUserService extends ServiceImpl<MeasureProblemUserMapper, MeasureProblemUserPO> {

    @Resource
    MeasureProblemUserMapper measureProblemUserMapper;

}
