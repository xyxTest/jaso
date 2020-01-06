package com.yaj.jaso.business.studyfile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yaj.common.base.pojo.MulSelect;
import com.yaj.common.base.service.ServiceMain;
import com.yaj.common.threadlocal.ThreadlocalManager;
import com.yaj.jaso.business.jasouser.entity.po.JasoUserPO;
import com.yaj.jaso.business.studyfile.entity.po.StudyFilePO;
import com.yaj.jaso.business.studyfile.entity.vo.StudyFileAdd;
import com.yaj.jaso.business.studyfile.entity.vo.StudyFileVo;
import com.yaj.jaso.business.studyfile.mapper.StudyFileMapper;
import com.yaj.jaso.business.studypersonalpreference.entity.po.StudyPersonalPreferencePO;
import com.yaj.jaso.business.studypersonalpreference.service.StudyPersonalPreferenceService;
import com.yaj.jaso.business.studyworkertype.entity.po.StudyWorkerTypePO;
import com.yaj.jaso.business.userintegrallog.entity.po.UserIntegralLogPO;
import com.yaj.jaso.business.userintegrallog.service.UserIntegralLogService;

/*
 * @Description: 视听学习资料+阅读学习资料
 * @date: 2019-10-24
 */
@Service
public class StudyFileService extends ServiceImpl<StudyFileMapper, StudyFilePO> {

    @Resource
    StudyFileMapper studyFileMapper;
    @Autowired
    ServiceMain serviceMain;
    @Autowired
    StudyPersonalPreferenceService personalService;
    @Autowired
    UserIntegralLogService logService;
    public Object add(StudyFileAdd po) {
		// TODO Auto-generated method stub
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		List<StudyFilePO> list = new ArrayList<>();
		if(!po.getWorkTypeIdList().isEmpty()){
			for(int i=0;i<po.getWorkTypeIdList().size();i++){
				StudyFilePO file = new StudyFilePO();
				file.setCompanyId(userInCache.getCompanyId());
				file.setFileUrl(po.getFileUrl());
				file.setPic(po.getPic());
				file.setSize(po.getSize());
				file.setStudyWorkerTypeId(po.getWorkTypeIdList().get(i));
				file.setTitle(po.getTitle());
				file.setType(po.getType());
				list.add(file);
			}
		}
		return insertOrUpdateBatch(list);
	}

	public Object deleteBatchByIds(List<StudyFilePO> po) {
		for(int i=0;i<po.size();i++){
			po.get(i).setIfDelete(1);
		}
		return updateBatchById(po);
	}

	public Object selectListByPage(StudyFileVo po) {
		// TODO Auto-generated method stub
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		/*List<StudyPersonalPreferencePO> likeList = new ArrayList<>();
		Wrapper<StudyPersonalPreferencePO> like = new EntityWrapper<StudyPersonalPreferencePO>();
		like.eq("jaso_user_id", userInCache.getJasoUserId());
		likeList=personalService.selectList(like);
		List<Long> workerTypeIds = new ArrayList<>();
		for(StudyPersonalPreferencePO item :likeList){
			workerTypeIds.add(item.getStudyWorkerTypeId());
		}*/
		MulSelect mul2 = MulSelect.newInstance("${1}.name", new StudyFilePO(),new StudyWorkerTypePO());
		mul2.setPage(po.getPageVo().getPageNo(), po.getPageVo().getPageSize());
		mul2.where("${study_file}")
			.eq("companyId", userInCache.getCompanyId())
			.eq(po.getStudyWorkerTypeId()!=null,"studyWorkerTypeId", po.getStudyWorkerTypeId())
			.eq(po.getType()!=null,"type",po.getType());
		mul2.setOrderBy("studyfile.create_time desc");
		return serviceMain.mulSelect(mul2);
	}

	public Object selectLists(StudyFilePO po) {
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		MulSelect mul2 = MulSelect.newInstance("${1}.name", new StudyFilePO(),new StudyWorkerTypePO());
		mul2.where("${study_file}")
			.eq("companyId", userInCache.getCompanyId())
			.eq(po.getStudyWorkerTypeId()!=null,"studyWorkerTypeId", po.getStudyWorkerTypeId());
		mul2.setOrderBy("studyfile.create_time desc");
        return serviceMain.mulSelect(mul2);
	}

	public Object getById(StudyFilePO po) {
		// TODO Auto-generated method stub
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		if(po.getType()!=null && po.getStudyFileId()!=null){
			StudyFilePO old = selectById(po.getStudyFileId());
			int type = 3;
			if(old.getType()==2){
				type = 2;
			}
			///设置用户阅读文章、视听文件记录，每天一次一分，封顶一天六次
			java.util.Date date=new java.util.Date();
	        java.sql.Date sdate=new java.sql.Date(date.getTime());
	        Wrapper<UserIntegralLogPO> logWrapper = new EntityWrapper<>();
	        logWrapper.eq("company_id", userInCache.getCompanyId())
	        .eq("jaso_user_id", userInCache.getJasoUserId())
	        .eq("create_time", sdate)
	        .eq("type", type);
	        if(logService.selectList(logWrapper).size()<6){
	        	UserIntegralLogPO log = new UserIntegralLogPO();
				log.setCompanyId(userInCache.getCompanyId());
				log.setJasoUserId(userInCache.getJasoUserId());
				log.setNum(1);
				if(po.getType()==1){//视听学习
					log.setType(3);
				}
				if(po.getType()==2){//阅读文章
					log.setType(2);
				}
				log.setCreateTime(sdate);
				return logService.insertOrUpdate(log);
	        }
		}
		return true;
	}

}
