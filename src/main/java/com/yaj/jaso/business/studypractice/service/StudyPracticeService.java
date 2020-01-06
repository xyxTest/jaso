package com.yaj.jaso.business.studypractice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yaj.common.base.pojo.MulSelect;
import com.yaj.common.base.service.ServiceMain;
import com.yaj.common.threadlocal.ThreadlocalManager;
import com.yaj.jaso.business.jasouser.entity.po.JasoUserPO;
import com.yaj.jaso.business.studyanswerrecord.entity.po.StudyAnswerRecordPO;
import com.yaj.jaso.business.studyanswerrecord.service.StudyAnswerRecordService;
import com.yaj.jaso.business.studydata.entity.po.StudyDataPO;
import com.yaj.jaso.business.studydata.service.StudyDataService;
import com.yaj.jaso.business.studypersonalpreference.entity.po.StudyPersonalPreferencePO;
import com.yaj.jaso.business.studypersonalpreference.service.StudyPersonalPreferenceService;
import com.yaj.jaso.business.studypractice.entity.po.StudyPracticePO;
import com.yaj.jaso.business.studypractice.entity.vo.RecordModo;
import com.yaj.jaso.business.studypractice.mapper.StudyPracticeMapper;
import com.yaj.jaso.business.studyworkertype.entity.po.StudyWorkerTypePO;
import com.yaj.jaso.business.userintegrallog.entity.po.UserIntegralLogPO;
import com.yaj.jaso.business.userintegrallog.service.UserIntegralLogService;
/*
 * @Description: 
 * @date: 2019-11-01
 */
@Service
public class StudyPracticeService extends ServiceImpl<StudyPracticeMapper, StudyPracticePO> {

    @Resource
    StudyPracticeMapper studyPracticeMapper;
    @Autowired
    StudyPersonalPreferenceService personalService;
    @Autowired
    ServiceMain serviceMain;
    @Autowired
    StudyDataService dataService;
    @Autowired
    UserIntegralLogService logService;
    @Autowired
    StudyAnswerRecordService recordService;
	public Object add(StudyPracticePO po) {
		// TODO Auto-generated method stub
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		po.setCompanyId(userInCache.getCompanyId());
		po.setJasoUserId(userInCache.getJasoUserId());
		return insertOrUpdate(po);
	}

	public Object selectLists(StudyPracticePO po) {
		// TODO Auto-generated method stub
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		Wrapper<StudyPracticePO> wrapper = new EntityWrapper<StudyPracticePO>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		String nowstr=df.format(new Date());
		Date now = null;
		try {
			now = df.parse(nowstr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date weekAgo = new Date();
		Calendar cal = Calendar.getInstance();
	    cal.setTime(now);//设置起时间
	    cal.add(Calendar.DATE, -7);//减7天  
	    weekAgo=cal.getTime();
		wrapper.eq("company_id", userInCache.getCompanyId())
		.eq("jaso_user_id", userInCache.getJasoUserId())
		.le("create_time", now)
		.ge("create_time", weekAgo);
		List<StudyPracticePO> list = new ArrayList<>();
		list = selectList(wrapper);
		if(list.isEmpty()){
			List<StudyPersonalPreferencePO> likeList = new ArrayList<>();
			Wrapper<StudyPersonalPreferencePO> like = new EntityWrapper<StudyPersonalPreferencePO>();
			like.eq("jaso_user_id", userInCache.getJasoUserId());
			likeList=personalService.selectList(like);
			if(likeList.isEmpty()){
				return new String[]{};
			}
			List<Long> workerTypeIds = new ArrayList<>();
			for(StudyPersonalPreferencePO item :likeList){
				workerTypeIds.add(item.getStudyWorkerTypeId());
			}
			List<StudyDataPO> dataList = new ArrayList<>();
			Wrapper<StudyDataPO> dataWrapper = new EntityWrapper<StudyDataPO>();
			dataWrapper.eq("company_id", userInCache.getCompanyId())
			.in("study_worker_type_id", workerTypeIds);
			dataList = dataService.selectList(dataWrapper);
			//随机八道题
			if(dataList.size()>=8){
				List<Integer> intList = new ArrayList<>();
				while(intList.size()<8){
					boolean flag=true;
					int num=(int)(Math.random() * (dataList.size()+1));
					for(int i=0;i<intList.size();i++){
						if(intList.get(i)==num){
							flag=false;
						}
					}
					if(flag){
						intList.add(num);
					}
				} 
				List<StudyPracticePO> inserts = new ArrayList<>();
				for(int j=0;j<intList.size();j++){
					StudyPracticePO ppo = new StudyPracticePO();
					ppo.setCompanyId(userInCache.getCompanyId());
					Calendar cals = Calendar.getInstance();
				    cals.setTime(new Date());//设置起时间
					cals.add(Calendar.DATE, -j);//减7天  
					ppo.setCreateTime(cals.getTime());
					ppo.setJasoUserId(userInCache.getJasoUserId());
					ppo.setStudyDataId(dataList.get(intList.get(j)).getStudyDataId());
					inserts.add(ppo);
				}
				if(!inserts.isEmpty()){
					if(insertOrUpdateBatch(inserts)){				
						MulSelect mul = MulSelect.newInstance("${1}.dataName,${1}.studyDataOptions,${1}.rightKey,${1}.answerAnalysis,${2}.name", new StudyPracticePO(),new StudyDataPO(),new StudyWorkerTypePO());
						mul.where("${study_practice}")
							.eq("jasoUserId", userInCache.getJasoUserId())
							.le(true,"createTime", now)
							.ge(true,"createTime", weekAgo);
						mul.setOrderBy("studypractice.create_time asc");
						return serviceMain.mulSelect(mul);
					}
				}
			}else{
				return new String[]{};
			}
		}else if(list.size()<8){
			List<Long> dataIds = new ArrayList<>();
			for(StudyPracticePO spo:list){
				dataIds.add(spo.getStudyDataId());
			}
			List<StudyPersonalPreferencePO> likeList = new ArrayList<>();
			Wrapper<StudyPersonalPreferencePO> like = new EntityWrapper<StudyPersonalPreferencePO>();
			like.eq("jaso_user_id", userInCache.getJasoUserId());
			likeList=personalService.selectList(like);
			List<Long> workerTypeIds = new ArrayList<>();
			for(StudyPersonalPreferencePO item :likeList){
				workerTypeIds.add(item.getStudyWorkerTypeId());
			}
			List<StudyDataPO> dataList = new ArrayList<>();
			Wrapper<StudyDataPO> dataWrapper = new EntityWrapper<StudyDataPO>();
			dataWrapper.eq("company_id", userInCache.getCompanyId())
			.in("study_worker_type_id", workerTypeIds)
			.notIn("study_data_id", dataIds);
			dataList = dataService.selectList(dataWrapper);
			if(dataList.size()>=(8-list.size())){
				List<Integer> intList = new ArrayList<>();
				while(intList.size()<(8-list.size())){
					boolean flag=true;
					int num=(int)(Math.random() * (dataList.size()+1));
					for(int i=0;i<intList.size();i++){
						if(intList.get(i)==num){
							flag=false;
						}
					}
					if(flag){
						intList.add(num);
					}
				} 
				List<StudyPracticePO> inserts = new ArrayList<>();
				for(int j=0;j<intList.size();j++){
					StudyPracticePO ppo = new StudyPracticePO();
					ppo.setCompanyId(userInCache.getCompanyId());
					Calendar cals = Calendar.getInstance();
				    cals.setTime(new Date());//设置起时间
					cals.add(Calendar.DATE, -j);//减7天  
					ppo.setCreateTime(cals.getTime());
					ppo.setJasoUserId(userInCache.getJasoUserId());
					ppo.setStudyDataId(dataList.get(intList.get(j)).getStudyDataId());
					inserts.add(ppo);
				}
				if(!inserts.isEmpty()){
					if(insertOrUpdateBatch(inserts)){
						MulSelect mul = MulSelect.newInstance("${1}.dataName,${1}.studyDataOptions,${1}.rightKey,${1}.answerAnalysis,${2}.name", new StudyPracticePO(),new StudyDataPO(),new StudyWorkerTypePO());
						mul.where("${study_practice}")
							.eq("jasoUserId", userInCache.getJasoUserId())
							.le(true,"createTime", now)
							.ge(true,"createTime", weekAgo);
						mul.setOrderBy("studypractice.create_time asc");
						return serviceMain.mulSelect(mul);
					}
				}
			}else{
				return new String[]{};
			}
		}else{
			MulSelect mul = MulSelect.newInstance("${1}.dataName,${1}.studyDataOptions,${1}.rightKey,${1}.answerAnalysis,${2}.name", new StudyPracticePO(),new StudyDataPO(),new StudyWorkerTypePO());
			mul.where("${study_practice}")
				.eq("jasoUserId", userInCache.getJasoUserId())
				.le(true,"createTime", now)
				.ge(true,"createTime", weekAgo);
			mul.setOrderBy("studypractice.create_time asc");
			return serviceMain.mulSelect(mul);
		}
		return true;
	}

	public Object submit(RecordModo po) {
		// TODO Auto-generated method stub
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		if(po!=null){
			StudyPracticePO old = new StudyPracticePO();
			old = selectById(po.getStudyPracticeId());
			old.setIsRight(po.getIsRight());
			old.setPersonalAnswer(po.getPersonalAnswer());
			po.setCompanyId(userInCache.getCompanyId());
			po.setJasoUserId(userInCache.getJasoUserId());
			insertOrUpdate(old);
			///设置用户阅读文章、视听文件记录，每天一次一分，封顶一天六次
			java.util.Date date=new java.util.Date();
	        java.sql.Date sdate=new java.sql.Date(date.getTime());
	        Wrapper<UserIntegralLogPO> logWrapper = new EntityWrapper<>();
	        logWrapper.eq("company_id", userInCache.getCompanyId())
	        .eq("jaso_user_id", userInCache.getJasoUserId())
	        .eq("create_time", sdate)
	        .eq("type", 4);
	        if(logService.selectList(logWrapper).size()<6){
	        	if(po.getIsRight()==1){
	        		UserIntegralLogPO log = new UserIntegralLogPO();
					log.setCompanyId(userInCache.getCompanyId());
					log.setJasoUserId(userInCache.getJasoUserId());
					log.setNum(1);
					log.setType(4);
					log.setCreateTime(sdate);
					return logService.insertOrUpdate(log);
	        	}
	        }
		}
		StudyAnswerRecordPO save = new StudyAnswerRecordPO();
		save.setCompanyId(userInCache.getCompanyId());
		save.setCreateTime(new Date());
		save.setDataNum(po.getDataNum());
		save.setIsRight(po.getIsRight());
		save.setPersonalAnswer(po.getPersonalAnswer());
		save.setStudyDataId(po.getStudyDataId());
		save.setStudyPaperId(po.getStudyPaperId());
		return recordService.insertOrUpdate(save);
	}

}
