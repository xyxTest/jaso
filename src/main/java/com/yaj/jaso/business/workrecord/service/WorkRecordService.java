package com.yaj.jaso.business.workrecord.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.yaj.jaso.business.workrecord.entity.po.WorkRecordPO;
import com.yaj.jaso.business.workrecord.mapper.WorkRecordMapper;

/*
 * @Description: 
 * @date: 2019-12-02
 */
@Service
public class WorkRecordService extends ServiceImpl<WorkRecordMapper, WorkRecordPO> {

    @Resource
    WorkRecordMapper workRecordMapper;

}
