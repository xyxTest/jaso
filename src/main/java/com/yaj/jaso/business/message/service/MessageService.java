package com.yaj.jaso.business.message.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import javax.annotation.Resource;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yaj.common.base.service.ServiceMain;
import com.yaj.common.threadlocal.ThreadlocalManager;
import com.yaj.jaso.business.jasouser.entity.po.JasoUserPO;
import com.yaj.jaso.business.message.entity.po.MessagePO;
import com.yaj.jaso.business.message.entity.vo.MessageVo;
import com.yaj.jaso.business.message.mapper.MessageMapper;

/*
 * @Description: 
 * @date: 2019-10-29
 */
@Service
public class MessageService extends ServiceImpl<MessageMapper, MessagePO> {

    @Resource
    MessageMapper messageMapper;
    @Autowired
    ServiceMain serviceMain;
	public Object deleteList(List<MessagePO> mpo) {
		// TODO Auto-generated method stub
		for(int i=0;i<mpo.size();i++){
			mpo.get(i).setIfDelete(1);
		}
		return insertOrUpdateBatch(mpo);
	}

	public Object selectList(MessageVo mpo) {
		// TODO Auto-generated method stub
		JasoUserPO userInCache=(JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		int limit1=mpo.getPageVo().getPageNo();
		int limit2=mpo.getPageVo().getPageSize();
		if(mpo.getPageVo().getPageNo()==1){
			limit1=0;
		}
		if(mpo.getPageVo().getPageNo()>1){
			limit1=(mpo.getPageVo().getPageNo()-1)*mpo.getPageVo().getPageSize();
		}
		limit2=mpo.getPageVo().getPageSize();
        return messageMapper.selectListByJasoUserId(userInCache.getJasoUserId(),userInCache.getCompanyId(),limit1,limit2);
	}

	public Object getCount() {
		// TODO Auto-generated method stub
		JasoUserPO userInCache=(JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		Wrapper<MessagePO> wrapper = new EntityWrapper<>();
		wrapper.eq("jaso_user_id", userInCache.getJasoUserId())
		.eq("company_id", userInCache.getCompanyId())
		.eq("state", 0);
		return selectList(wrapper).size();
	}

}
