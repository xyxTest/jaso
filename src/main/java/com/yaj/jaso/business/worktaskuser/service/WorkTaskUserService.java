package com.yaj.jaso.business.worktaskuser.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.yaj.jaso.business.worktaskuser.entity.po.WorkTaskUserPO;
import com.yaj.jaso.business.worktaskuser.mapper.WorkTaskUserMapper;

/*
 * @Description: 
 * @date: 2019-09-19
 */
@Service
public class WorkTaskUserService extends ServiceImpl<WorkTaskUserMapper, WorkTaskUserPO> {

    @Resource
    WorkTaskUserMapper workTaskUserMapper;

}
