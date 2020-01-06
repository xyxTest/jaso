package com.yaj.jaso.business.userintegrallog.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yaj.common.threadlocal.ThreadlocalManager;
import com.yaj.jaso.business.jasouser.entity.po.JasoUserPO;
import com.yaj.jaso.business.userintegrallog.entity.po.UserIntegralLogPO;
import com.yaj.jaso.business.userintegrallog.entity.vo.CountResult;
import com.yaj.jaso.business.userintegrallog.entity.vo.ResultList;
import com.yaj.jaso.business.userintegrallog.mapper.UserIntegralLogMapper;

/*
 * @Description: 用户学习模块的积分记录表
 * @date: 2019-11-04
 */
@Service
public class UserIntegralLogService extends ServiceImpl<UserIntegralLogMapper, UserIntegralLogPO> {

    @Resource
    UserIntegralLogMapper userIntegralLogMapper;

	public Object add(UserIntegralLogPO po) {
		// TODO Auto-generated method stub
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		///设置用户登录记录，每天签到一次一分，封顶一次
		java.util.Date date=new java.util.Date();
        java.sql.Date sdate=new java.sql.Date(date.getTime());
        Wrapper<UserIntegralLogPO> logWrapper = new EntityWrapper<>();
        logWrapper.eq("company_id", userInCache.getCompanyId())
        .eq("jaso_user_id", userInCache.getJasoUserId())
        .eq("create_time", sdate)
        .eq("type", 1);
        if(selectList(logWrapper).isEmpty()){
        	UserIntegralLogPO log = new UserIntegralLogPO();
			log.setCompanyId(userInCache.getCompanyId());
			log.setJasoUserId(userInCache.getJasoUserId());
			log.setNum(1);
			log.setType(1);
			log.setCreateTime(sdate);
			return insertOrUpdate(log);
        }else{
        	return false;
        }
	}

	public Object selectLists() {
		// TODO Auto-generated method stub
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		CountResult result = new CountResult();
		java.util.Date date=new java.util.Date();
        java.sql.Date sdate=new java.sql.Date(date.getTime());
        List<UserIntegralLogPO> list = new ArrayList<>();
        List<UserIntegralLogPO> alllist = new ArrayList<>();
        Wrapper<UserIntegralLogPO> logWrapper = new EntityWrapper<>();
        logWrapper.eq("company_id", userInCache.getCompanyId())
        .eq("jaso_user_id", userInCache.getJasoUserId())
        .eq("create_time", sdate);
        Wrapper<UserIntegralLogPO> logWrapper2 = new EntityWrapper<>();
        logWrapper.eq("company_id", userInCache.getCompanyId())
        .eq("jaso_user_id", userInCache.getJasoUserId());
        list=selectList(logWrapper);
        alllist=selectList(logWrapper2);
        int sumNum = 0;
    	int historyNum=0;
    	int[] types={0,0,0,0,0};//
    	String[] names ={"签到","阅读文章","视听学习","题目","试卷答题"};
    	String[] rules ={"1分/每日首次签到","1分/每有效阅读一篇","1分/每有效观看一个","每日一练，答对一道题积1分","得分80分以上的3分，其他的1分"};
    	for(int h=0;h<alllist.size();h++){
    		historyNum=historyNum+alllist.get(h).getNum();
    	}
    	List<ResultList> lists = new ArrayList<>();
        if(!list.isEmpty()){
        	for(int i=0;i<list.size();i++){
        		sumNum=sumNum+list.get(i).getNum();
        		if(list.get(i).getType()==1){
        			types[0]=types[0]+list.get(i).getNum();
        		}else if(list.get(i).getType()==2){
        			types[1]=types[1]+list.get(i).getNum();
        		}else if(list.get(i).getType()==3){
        			types[2]=types[2]+list.get(i).getNum();
        		}else if(list.get(i).getType()==4){
        			types[3]=types[3]+list.get(i).getNum();
        		}else if(list.get(i).getType()==5){
        			types[4]=types[4]+list.get(i).getNum();
        		}
        	}
        }
        for(int j=0;j<5;j++){
    		ResultList item = new ResultList();
    		if(j==0){
    			item.setAllNum(1);
    		}else{
    			item.setAllNum(6);
    		}
        	item.setNum(types[j]);
        	item.setName(names[j]);
        	item.setType(j+1);
        	item.setRule(rules[j]);
        	lists.add(item);
    	}
        result.setTodayNum(sumNum);
    	result.setHistoryNum(historyNum);
    	result.setResultList(lists);
		return result;
	}
}
