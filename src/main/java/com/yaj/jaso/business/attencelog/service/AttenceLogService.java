package com.yaj.jaso.business.attencelog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xyx.spring.Enums.ErrorCodeEnum;
import com.yaj.common.threadlocal.ThreadlocalManager;
import com.yaj.jaso.business.attencelog.entity.po.AttenceLogPO;
import com.yaj.jaso.business.attencelog.entity.vo.AttenceCount;
import com.yaj.jaso.business.attencelog.entity.vo.AttenceImport;
import com.yaj.jaso.business.attencelog.entity.vo.AttenceLogVo;
import com.yaj.jaso.business.attencelog.entity.vo.CountResult;
import com.yaj.jaso.business.attencelog.mapper.AttenceLogMapper;
import com.yaj.jaso.business.attencemode.entity.po.AttenceModePO;
import com.yaj.jaso.business.attencemode.service.AttenceModeService;
import com.yaj.jaso.business.attenceplace.entity.po.AttencePlacePO;
import com.yaj.jaso.business.attenceplace.service.AttencePlaceService;
import com.yaj.jaso.business.jasouser.entity.po.JasoUserPO;
import com.yaj.jaso.global.Parameters;
import com.yaj.xyx.util.InstanceUtil;

/*
 * @Description: 
 * @date: 2019-11-05
 */
@Service
public class AttenceLogService extends ServiceImpl<AttenceLogMapper, AttenceLogPO> {

    @Resource
    AttenceLogMapper attenceLogMapper;
    @Autowired
    AttenceModeService modeService;
    @Autowired
    AttencePlaceService placeService;
	public Object add(AttenceImport attencePlace) {
		JasoUserPO user = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		AttenceLogPO log = new AttenceLogPO();
		Date nowDate = null;
		try {
			nowDate = Parameters.getSdfs().parse(Parameters.getSdfs().format(new Date()));
			//log.setCreateDate(nowDate);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		//////判断当前是否已有打卡记录
		List<AttenceLogPO> logList = new ArrayList<>();
		Wrapper<AttenceLogPO> wrapper = new EntityWrapper<>();
		wrapper.eq("create_time", nowDate)
		.eq("jaso_user_id", user.getJasoUserId())
		.eq("company_id", user.getCompanyId());
		logList=selectList(wrapper);
		/////当前系统时间（精确到天）已有打卡记录，则根据打卡记录来更新
		///////当打卡记录不为空时,判断是否签退，如果已签退，不允许在签退，否则签退
		if(!logList.isEmpty()){
			AttenceLogPO old =logList.get(0);
			if(old.getEndWorkTime()==null){
				old.setEndWorkTime(new Date());
				List<AttenceModePO> mode = new ArrayList<>();
				mode = modeService.selectList(new EntityWrapper<AttenceModePO>());
				if(!mode.isEmpty()){
					AttenceModePO aml = mode.get(0);
					AttencePlacePO place = new AttencePlacePO();
					place = placeService.selectById(aml.getAttencePlaceId());
					old.setAttenceModeId(aml.getAttenceModeId());
					Double instanc = InstanceUtil.distanceSimplifyMore(Double.valueOf(attencePlace.getLat()),
							Double.valueOf(attencePlace.getLng()), Double.valueOf(place.getLat()), Double.valueOf(place.getLng()));
					if(instanc>aml.getAttenceRange()){
						return ErrorCodeEnum.Instance_Not_Fit;
					}else{
						Date realWorkTime=null;
						//////判断上班打卡时间是否符合规定
						realWorkTime = nowDate;
						Date requiredWorkTime=null;
						try {
							String now=Parameters.getSdfs().format(nowDate);
							requiredWorkTime = Parameters.getSdf().parse(now+" "+aml.getClosingTime()+":00");
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						if(!requiredWorkTime.before(realWorkTime)){
							old.setLeaveEarly(1);////1表示早退
						}else{
							old.setLeaveEarly(0);////0表示正常
						}
						return insertOrUpdate(old);
					}
				}
			}else{
				return ErrorCodeEnum.Already_Done;
			}
		}else{
			log.setStartWorkTime(new Date());
			AttenceModePO aml =  modeService.selectOne(new EntityWrapper<>());
			if(aml!=null){
				AttencePlacePO place = new AttencePlacePO();
				place = placeService.selectById(aml.getAttencePlaceId());
				if(place!=null){
					log.setAttenceModeId(aml.getAttenceModeId());
					Double instanc = InstanceUtil.distanceSimplifyMore(Double.valueOf(attencePlace.getLat()), Double.valueOf(attencePlace.getLng()), Double.valueOf(place.getLat()), Double.valueOf(place.getLng()));
					if(instanc>aml.getAttenceRange()){
						return ErrorCodeEnum.Instance_Not_Fit;
					}else{
						Date realWorkTime=null;
						//////判断上班打卡时间是否符合规定
						realWorkTime = nowDate;
						Date requiredWorkTime=null;
						try {
							String now=Parameters.getSdfs().format(nowDate);
							requiredWorkTime = Parameters.getSdf().parse(now+" "+aml.getWorkTime()+":00");
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
						if(!requiredWorkTime.before(realWorkTime)){
							log.setLate(1);;////1表示迟到
							log.setLeaveEarly(0);
						}else{
							log.setLate(0);////0表示正常
							log.setLeaveEarly(0);
						}
						if(!insertOrUpdate(log)){
							return ErrorCodeEnum.Error;
						}else{
							return log;
						}
					}
				}
			}
		}
		return null;
	}

	public Object deleteList(List<AttenceLogPO> list) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object selectPage(AttenceLogVo log) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getCountNum(AttenceCount count) {
		JasoUserPO user = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		CountResult result = new CountResult();
		return null;
	}

}
