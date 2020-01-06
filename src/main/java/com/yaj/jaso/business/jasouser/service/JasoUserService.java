package com.yaj.jaso.business.jasouser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import javax.annotation.Resource;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yaj.common.base.pojo.MulSelect;
import com.yaj.common.base.service.ServiceMain;
import com.yaj.common.cache.CacheManager;
import com.yaj.common.cache.CacheConfig.CacheTypeEnum;
import com.yaj.common.constant.UserTypeEnum;
import com.yaj.common.exception.BusinessException;
import com.yaj.common.exception.BusinessExceptionErrorEnum;
import com.yaj.common.jwt.JwtTokenUtil;
import com.yaj.common.jwt.JwtUserInfo;
import com.yaj.common.response.ResponseData;
import com.yaj.common.threadlocal.ThreadlocalContext;
import com.yaj.common.threadlocal.ThreadlocalManager;
import com.yaj.core.util.BeanUtil;
import com.yaj.core.util.PasswordUtil;
import com.yaj.jaso.business.department.service.DepartmentService;
import com.yaj.jaso.business.userdepartment.entity.po.UserDepartmentPO;
import com.yaj.jaso.business.userdepartment.service.UserDepartmentService;
import com.yaj.jaso.business.userintegrallog.service.UserIntegralLogService;
import com.yaj.jaso.business.userproject.entity.po.UserProjectPO;
import com.yaj.jaso.business.userproject.service.UserProjectService;
import com.yaj.jaso.business.jasouser.entity.po.JasoUserPO;
import com.yaj.jaso.business.jasouser.entity.vo.GetUserInfo;
import com.yaj.jaso.business.jasouser.entity.vo.MapUserList;
import com.yaj.jaso.business.jasouser.entity.vo.PhoneBook;
import com.yaj.jaso.business.jasouser.entity.vo.ProjectList;
import com.yaj.jaso.business.jasouser.entity.vo.UserAddVo;
import com.yaj.jaso.business.jasouser.entity.vo.UserInfo;
import com.yaj.jaso.business.jasouser.entity.vo.UserInfoDetail;
import com.yaj.jaso.business.jasouser.entity.vo.UserSignResult;
import com.yaj.jaso.business.jasouser.entity.vo.UserVo;
import com.yaj.jaso.business.jasouser.mapper.JasoUserMapper;
import com.yaj.jaso.business.menu.entity.po.MenuPO;
import com.yaj.jaso.business.project.entity.po.ProjectPO;
import com.yaj.jaso.business.project.service.ProjectService;
import com.yaj.jaso.business.role.entity.po.RolePO;
import com.yaj.jaso.business.role.mapper.RoleMapper;
import com.yaj.jaso.business.signuser.entity.po.SignUserPO;
import com.yaj.jaso.business.signuser.service.SignUserService;
import com.yaj.jaso.business.userrole.entity.po.UserRolePO;
import com.yaj.jaso.business.userrole.service.UserRoleService;
import com.yaj.jaso.business.userworktype.entity.po.UserWorkTypePO;
import com.yaj.jaso.business.userworktype.service.UserWorkTypeService;
import com.yaj.jaso.global.PageVoUtil;
import com.yaj.xyx.util.ChartGraphics;
import com.yaj.xyx.util.IDCardScreen;
import com.yaj.xyx.util.JSMSExample;
import com.yaj.xyx.util.PhoneFormatCheckUtils;

/*
 * @Description: 
 * @date: 2019-07-22
 */
@Service
public class JasoUserService extends ServiceImpl<JasoUserMapper, JasoUserPO> {

    @Resource
    JasoUserMapper jasoUserMapper;
    @Autowired
    UserRoleService userRoleService;
   /* @Autowired
    RoleMapper roleMapper;*/
    @Autowired
    DepartmentService departmentService;
    @Autowired
    SignUserService signService;
    @Autowired
    UserDepartmentService userDepartmentService;
    @Autowired
    UserIntegralLogService logService;
    @Autowired
    UserProjectService userProjectService;
    @Autowired
    UserWorkTypeService userWorkTypeService;
    @Autowired
    ProjectService projectService;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    JwtTokenUtil tokenUtil;
    @Autowired
    ServiceMain serviceMain;
    public Object appLogin(JasoUserPO user) {
		// TODO Auto-generated method stub
    	ResponseData<JasoUserPO> result = new ResponseData<JasoUserPO>();
		if(user.getUserName()!=null && user.getPassword()!=null){
			Wrapper<JasoUserPO> wrapper = new EntityWrapper<JasoUserPO>();
			wrapper.eq("user_name", user.getUserName());
			List<JasoUserPO> userlist=jasoUserMapper.selectList(wrapper);
			if(!userlist.isEmpty()){
				JasoUserPO getUser = new JasoUserPO();
				getUser=userlist.get(0);
				if(getUser.getIsApp()!=2){
					if(PasswordUtil.isPasswordValid(getUser.getPassword(), user.getPassword(), PasswordUtil.getSalt())){
						ThreadlocalContext context = new ThreadlocalContext(); 
						JwtUserInfo jui = new JwtUserInfo();
						String key = UUID.randomUUID().toString().replaceAll("-", "");
						jui.setToken(key);
						jui.setType(UserTypeEnum.APP);
						String token = tokenUtil.generateToken(jui.toJsonString(), tokenUtil.getRandomKey());
						context.setToken(token);
						context.setCurUser(getUser); 
						ThreadlocalManager.setThreadContext(context);
						CacheManager.put(CacheTypeEnum.appTokenCache, key, context);
						result.setToken(token);
						result.setData(getUser);
					}else{
						throw new BusinessException(BusinessExceptionErrorEnum.LOGIN_ERROR);
					}
				}else{
					throw new BusinessException(BusinessExceptionErrorEnum.LOGIN_SYSTEM_ERROR);
				}
			}else{
				throw new BusinessException(BusinessExceptionErrorEnum.LOGIN_ERROR);
			}
		}else{
			throw new BusinessException(BusinessExceptionErrorEnum.LOGIN_ERROR);
		}		
		return result;
	}
	public Object pcLogin(JasoUserPO user) {
		// TODO Auto-generated method stub
		ResponseData<JasoUserPO> result = new ResponseData<JasoUserPO>();
		if(user.getUserName()!=null && user.getPassword()!=null){
			Wrapper<JasoUserPO> wrapper = new EntityWrapper<JasoUserPO>();
			wrapper.eq("user_name", user.getUserName());
			List<JasoUserPO> userlist=jasoUserMapper.selectList(wrapper);
			if(!userlist.isEmpty()){
				 /////判断是否是管理员
				Integer isAdmin=0;
				MulSelect mul2 = MulSelect.newInstance("${1}.roleName,${1}.sort", new UserRolePO(), new RolePO());
				mul2.where("${user_role}")
					.eq("jasoUserId",userlist.get(0).getJasoUserId())
					.eq("companyId", user.getCompanyId());	
				List<Map<String,Object>> tests=(List<Map<String, Object>>) serviceMain.mulSelect(mul2);
				for(Map<String,Object> items:tests){
					int sort=Integer.valueOf(items.get("sort").toString());
					if(sort==1){
						isAdmin=1;
					}
				}
				GetUserInfo gets = new GetUserInfo();
				gets.setIsAdmin(isAdmin);
				JasoUserPO getUser = new JasoUserPO();
				getUser=userlist.get(0);
				gets.setCompanyId(getUser.getCompanyId());
				gets.setJasoUserId(getUser.getJasoUserId());
				gets.setUserEmail(getUser.getUserEmail());
				gets.setUserIcon(getUser.getUserIcon());
				gets.setUserIdCard(getUser.getUserIdCard());
				gets.setUserName(getUser.getUserName());
				gets.setUserRealName(getUser.getUserRealName());
				gets.setUserTel(getUser.getUserTel());
				gets.setIsApp(getUser.getIsApp());
				List<MenuPO> menus = new ArrayList<>();
				menus=roleMapper.selectMenuList(getUser.getJasoUserId());
				if(menus==null){
					gets.setMenuList(new ArrayList<>());
				}else{
					gets.setMenuList(menus);
				}
				if(getUser.getIsApp()!=1){
					if(PasswordUtil.isPasswordValid(getUser.getPassword(), user.getPassword(), PasswordUtil.getSalt())){
						ThreadlocalContext context = new ThreadlocalContext(); 
						JwtUserInfo jui = new JwtUserInfo();
						String key = UUID.randomUUID().toString().replaceAll("-", "");
						jui.setToken(key);
						jui.setType(UserTypeEnum.WEB);
						String token = tokenUtil.generateToken(jui.toJsonString(), tokenUtil.getRandomKey());
						context.setToken(token);
						context.setCurUser(getUser); 
						ThreadlocalManager.setThreadContext(context);
						CacheManager.put(CacheTypeEnum.webTokenCache, key, context);
						result.setToken(token);
						result.setData(gets);
					}else{
						throw new BusinessException(BusinessExceptionErrorEnum.LOGIN_ERROR);
					}
				}else{
					throw new BusinessException(BusinessExceptionErrorEnum.LOGIN_SYSTEM_ERROR);
				}
			}else{
				throw new BusinessException(BusinessExceptionErrorEnum.LOGIN_ERROR);
			}
		}else{
			throw new BusinessException(BusinessExceptionErrorEnum.LOGIN_ERROR);
		}		
		return result;
	}

	public Object selectPages(UserVo user) {
		JasoUserPO cacheUser=(JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		//String key=tokenUtil.;
		//Object object=CacheManager.get(CacheTypeEnum.webTokenCache, key);
		Page<JasoUserPO> page = new Page<JasoUserPO>();
		page.setSize(user.getPageVo().getPageSize());
		page.setCurrent(user.getPageVo().getPageNo());
		JasoUserPO po = new JasoUserPO();
		BeanUtil.copy(user, po);
		Wrapper<JasoUserPO> wrapper = new EntityWrapper<JasoUserPO>();
		wrapper.orderDesc(Arrays.asList("create_time"));
		if(po.getCompanyId()!=null){
			wrapper.eq("company_id", po.getCompanyId());
		}else{
			wrapper.eq("company_id", cacheUser.getCompanyId());
		}
		if(po.getUserName()!=null){
			wrapper.like("user_name", po.getUserName());
		}
		if(po.getUserRealName()!=null){
			wrapper.like("user_real_name",po.getUserRealName() );
		}
		if(user.getDepartmentTree()!=null){
			Wrapper<UserDepartmentPO> wrappers = new EntityWrapper<UserDepartmentPO>();
			List<Long> userDepartmentIdList = new ArrayList<Long>();
			for(Long[] tree:user.getDepartmentTree()){
				userDepartmentIdList.add(tree[tree.length-1]);
			}
			wrappers.in("department_id", userDepartmentIdList);
			List<UserDepartmentPO> dusers=userDepartmentService.selectList(wrappers);
			List<Long> userIdList = new ArrayList<Long>();
			for(UserDepartmentPO item:dusers){
				userIdList.add(item.getJasoUserId());
			}
			wrapper.in("jaso_user_id",userIdList);
		}
		if(po.getUserTel()!=null){
			wrapper.like("user_tel", po.getUserTel());
		}
		page=selectPage(page, wrapper);
        return PageVoUtil.setPage(page);
	}

	public Object deleteList(List<JasoUserPO> users) {
		for(int i=0;i<users.size();i++){
			users.get(i).setIfDelete(1);
		}
		return insertOrUpdateBatch(users);
	}
	public Object add(UserAddVo user) {
		JasoUserPO cacheUser=(JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		boolean result = false; 
		JasoUserPO addItem = new JasoUserPO();
		BeanUtil.copy(user, addItem);
		// TODO Auto-generated method stub
		if(addItem!=null){
			addItem.setCompanyId(cacheUser.getCompanyId());
			if(addItem.getUserIdCard()!=null){
				if(addItem.getUserIdCard().length()==18){
					try {
						Map<String,Integer> infos=IDCardScreen.identityCard18(user.getUserIdCard());
						addItem.setUserAge(infos.get("age"));
						addItem.setUserSex(infos.get("sex"));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if(addItem.getUserIdCard().length()==15){
					try {
						Map<String,Integer> infos=IDCardScreen.identityCard15(user.getUserIdCard());
						addItem.setUserAge(infos.get("age"));
						addItem.setUserSex(infos.get("sex"));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					throw new BusinessException(BusinessExceptionErrorEnum.ID_CARD_MATCH_ERROR);
				}
				if(addItem.getPassword()!=null){
					if(user.getJasoUserId()!=null){
						JasoUserPO userOld = selectById(user.getJasoUserId());
						if(!userOld.getPassword().equals(addItem.getPassword())){
							addItem.setPassword(PasswordUtil.encodePassword(addItem.getPassword(), PasswordUtil.getSalt()));
						}
					}else{
						addItem.setPassword(PasswordUtil.encodePassword(addItem.getPassword(), PasswordUtil.getSalt()));
					}
				}
			}
		}
		if(addItem.getUserIcon()==null){
	        String uuid = UUID.randomUUID().toString();
	        try {
				ChartGraphics.generateImg(addItem.getUserRealName(), "E://jaso-spring-boot//tomcat_8085//webapps//ROOT//uploadFiles//projectfiles", uuid);
				addItem.setUserIcon("/uploadFiles/projectfiles/"+uuid+".jpg");
	        } catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(addItem.getJasoUserId()!=null){
			JasoUserPO oldUser = selectById(addItem.getJasoUserId());
			if(!addItem.getUserRealName().equals(oldUser.getUserRealName())){
				String uuid = UUID.randomUUID().toString();
		        try {
					ChartGraphics.generateImg(addItem.getUserRealName(), "E://jaso-spring-boot//tomcat_8085//webapps//ROOT//uploadFiles//projectfiles", uuid);
					addItem.setUserIcon("/uploadFiles/projectfiles/"+uuid+".jpg");
		        } catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		result=insertOrUpdate(addItem);
        if(result){
        	//用户新增成功;新增项目-用户关系表
        	if(user.getProjectIdList()!=null && user.getProjectIdList().length>0){
        		//新增前，删除以前的记录
        		Wrapper<UserProjectPO> wrapper = new EntityWrapper<>();
        		wrapper.eq("jaso_user_id", addItem.getJasoUserId());
        		if(userProjectService.delete(wrapper)){
        			List<UserProjectPO> uprojects = new ArrayList<>();
            		for(int i=0;i<user.getProjectIdList().length;i++){
            			UserProjectPO userproject = new UserProjectPO();
                		userproject.setCompanyId(cacheUser.getCompanyId());
                		userproject.setJasoUserId(addItem.getJasoUserId());
                		userproject.setProjectId(user.getProjectIdList()[i]);
                		uprojects.add(userproject);
            		}
            		userProjectService.insertOrUpdateBatch(uprojects);
        		}
        	}
        	//新增用户-工种关系数据
        	if(user.getWorkTypeId()!=null && user.getWorkTypeId().length>0){
        		//新增前，删除以前的记录
        		Wrapper<UserWorkTypePO> wrapper = new EntityWrapper<>();
        		wrapper.eq("jaso_user_id", addItem.getJasoUserId());
        		if(userWorkTypeService.delete(wrapper)){
        			List<UserWorkTypePO> uworks = new ArrayList<>();
            		for(int i=0;i<user.getWorkTypeId().length;i++){
            			UserWorkTypePO userWork = new UserWorkTypePO();
            			userWork.setCompanyId(cacheUser.getCompanyId());
            			userWork.setJasoUserId(addItem.getJasoUserId());
            			userWork.setWorkTypeId(user.getWorkTypeId()[i]);
            			uworks.add(userWork);
            		}
            		userWorkTypeService.insertOrUpdateBatch(uworks);
        		}
        	}
             ///pc表新增成功，新增pc用户、部门关系记录表
         	updateOrAddRoleAndDepartment(user,addItem);
        }
		return result;
	}
	public void updateOrAddRoleAndDepartment(UserAddVo user,JasoUserPO addItem){
		///pc表修改成功，新增pc用户、部门关系记录表
        if(user.getDepartmentTree()!=null){
            List<UserDepartmentPO> trees = new ArrayList<UserDepartmentPO>();
            int departmentSize = user.getDepartmentTree().length;
            UserDepartmentPO userDepartment = new UserDepartmentPO();
            userDepartment.setDepartmentId(user.getDepartmentTree()[departmentSize-1]);
            userDepartment.setJasoUserId(addItem.getJasoUserId());
            trees.add(userDepartment);
            ///用户、组织架构关系新增
            Wrapper<UserDepartmentPO> wrapperd = new EntityWrapper<UserDepartmentPO>();
            wrapperd.eq("jaso_user_id", addItem.getJasoUserId());
            if(userDepartmentService.delete(wrapperd)){
                userDepartmentService.insertOrUpdateBatch(trees);
            }else{
                throw new BusinessException(BusinessExceptionErrorEnum.SYSTEM_ERROR);
            }
        }
        ///pc表修改成功，新增pc用户、角色关系记录表
        if(user.getRoleId()!=null){
    		List<UserRolePO> userRoleList = new ArrayList<UserRolePO>();
    	    for(Long roleId:user.getRoleId()){
    	    	UserRolePO userRolePO = new UserRolePO();
    	    	userRolePO.setRoleId(roleId);
    	    	userRolePO.setJasoUserId(addItem.getJasoUserId());
    	    	userRolePO.setCompanyId(addItem.getCompanyId());
    	    	userRoleList.add(userRolePO);
    	    }
    	    Wrapper<UserRolePO> wrapper = new EntityWrapper<UserRolePO>();
	        wrapper.eq("jaso_user_id", addItem.getJasoUserId());
	        if(userRoleService.delete(wrapper)){
	            userRoleService.insertOrUpdateBatch(userRoleList);
	        }else{
	            throw new BusinessException(BusinessExceptionErrorEnum.SYSTEM_ERROR);
	        }
    	}
	}
	/*用户、角色获取*/
	public Object selectDetail(JasoUserPO user) {
		// TODO Auto-generated method stub
		Wrapper<UserRolePO> wrapper = new EntityWrapper<UserRolePO>();
		if(user.getJasoUserId()!=null){
			wrapper.eq("jaso_user_id", user.getJasoUserId());
		}
		return userRoleService.selectList(wrapper);
	}
	/*用户、部门获取*/
	public Object selectDepartmentDetail(JasoUserPO user) {
		// TODO Auto-generated method stub
		Wrapper<UserDepartmentPO> wrapper = new EntityWrapper<UserDepartmentPO>();
		if(user.getJasoUserId()!=null){
			wrapper.eq("jaso_user_id", user.getJasoUserId());
		}
		return userDepartmentService.selectList(wrapper);
	}

	public Object selectDepartmentTree() {
		return departmentService.selectJasoUserTreeLists();
	}
	public Object getListByProjectId(ProjectPO po) {
		// TODO Auto-generated method stub
		JasoUserPO cacheUser=(JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		MulSelect mul = MulSelect.newInstance("${1}.userRealName,${1}.userIcon", new UserProjectPO(), new JasoUserPO());
		mul.where("${user_project}")
			.eq(po.getProjectId()!=null,"projectId", po.getProjectId())
			.eq("companyId", cacheUser.getCompanyId())
			.notEq("jasoUserId", cacheUser.getJasoUserId());
		return serviceMain.mulSelect(mul);
	}
	public Object getUserListByProjectId(MapUserList user) {
		// TODO Auto-generated method stub
		JasoUserPO cacheUser=(JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		MulSelect mul = MulSelect.newInstance("${1}.userRealName", new UserProjectPO(), new JasoUserPO());
		mul.where("${user_project}")
			.eq(user.getProjectId()!=null,"projectId", user.getProjectId())
			.eq("companyId", cacheUser.getCompanyId())
			.notEq("jasoUserId", cacheUser.getJasoUserId());
		return serviceMain.mulSelect(mul);
	}
	public Object getIdentifyingCode(UserInfo user) {
		UserSignResult result = new UserSignResult();
		boolean flag=true;
		PhoneFormatCheckUtils pc = new PhoneFormatCheckUtils();
		if(pc.isPhoneLegal(user.getMobile())){
			Wrapper<JasoUserPO> wrapper = new EntityWrapper<>();
			wrapper.eq("user_tel", user.getMobile());
			JasoUserPO jasouser=selectOne(wrapper);
			if(jasouser!=null){
				String identifyingCode=String.valueOf(new Random().nextInt(899999) + 100000);
				if(JSMSExample.testSendTemplateSMS(user.getMobile(),identifyingCode)){
					SignUserPO newUser = new SignUserPO();
					newUser.setCode(identifyingCode);
					newUser.setMobile(user.getMobile());
					newUser.setMobile(user.getMobile());
					newUser.setJasoUserId(jasouser.getJasoUserId());
					signService.insert(newUser);
					result.setMessage("验证码发送成功");
				}
			}else{
				flag=false;
				result.setMessage("用户不存在");
			}
		}
		result.setFlag(flag);
		return result;
	}
	public Object getIdentifyingInfo(UserInfo user) {
		UserSignResult result = new UserSignResult();
		boolean flag = false;
		if(user.getMobile()!=null && user.getCode()!=null){
			List<SignUserPO> newUser = new ArrayList<>();
			Wrapper<SignUserPO> wrapper = new EntityWrapper<>();
			wrapper.eq("mobile", user.getMobile()).eq("code", user.getCode());
			newUser = signService.selectList(wrapper);
			if(!newUser.isEmpty()){
				Date ss = newUser.get(0).getCreateTime();
				Date end= new Date();
				long between=(end.getTime()-ss.getTime())/1000;//除以1000是为了转换成秒
				if(between>0 && between<297){
					List<JasoUserPO> users = new ArrayList<>();
					Wrapper<JasoUserPO> wrapperUser = new EntityWrapper<>();
					wrapperUser.eq("user_tel", user.getMobile());
					users=selectList(wrapperUser);
					if(!users.isEmpty()){
						JasoUserPO old= users.get(0);
						old.setPassword(PasswordUtil.encodePassword(user.getPassword(), PasswordUtil.getSalt()));
						insertOrUpdate(old);
						result.setMessage("修改成功");
						flag=true;
					}
				}else{
					result.setMessage("验证码失效！");
				}
			}else{
				result.setMessage("验证码错误！");
			}
		}
		result.setFlag(flag);
		return result;
	}
	public Object getPhoneBook(UserVo user) {
		// TODO Auto-generated method stub
		List<PhoneBook> result = new ArrayList<>(); 
		Page<JasoUserPO> page = new Page<JasoUserPO>();
		Page<PhoneBook> resultpage = new Page<PhoneBook>();
		page.setSize(user.getPageVo().getPageSize());
		page.setCurrent(user.getPageVo().getPageNo());
		JasoUserPO cacheUser=(JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		Wrapper<JasoUserPO> userWrapper = new EntityWrapper<>();
		userWrapper.eq("company_id", cacheUser.getCompanyId());
		page=selectPage(page, userWrapper);
		for(int i=0;i<page.getRecords().size();i++){
			PhoneBook phoneBook = new PhoneBook();
			phoneBook.setRealName(page.getRecords().get(i).getUserRealName());
			phoneBook.setTel(page.getRecords().get(i).getUserTel());
			phoneBook.setUserIcon(page.getRecords().get(i).getUserIcon());
			result.add(phoneBook);
		}
		resultpage.setSize(user.getPageVo().getPageSize());
		resultpage.setCurrent(user.getPageVo().getPageNo());
		resultpage.setTotal(page.getTotal());
		resultpage.setRecords(result);
		return resultpage;
	}
	public Object selectList() {
		// TODO Auto-generated method stub
		JasoUserPO cacheUser=(JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		Wrapper<JasoUserPO> userWrapper = new EntityWrapper<>();
		userWrapper.eq("company_id", cacheUser.getCompanyId());
		return selectList(userWrapper);
	}
	public Object getRoleListByUserId(JasoUserPO user) {
		// TODO Auto-generated method stub
		JasoUserPO cacheUser=(JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		Wrapper<UserRolePO> userWrapper = new EntityWrapper<>();
		userWrapper.eq("company_id", cacheUser.getCompanyId())
		.eq("jaso_user_id", cacheUser.getJasoUserId());
		return userRoleService.selectList(userWrapper);
	}
	public Object getWorkTypeListByUserId(JasoUserPO user) {
		// TODO Auto-generated method stub
		JasoUserPO cacheUser=(JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		Wrapper<UserWorkTypePO> userWrapper = new EntityWrapper<>();
		userWrapper.eq("company_id", cacheUser.getCompanyId())
		.eq("jaso_user_id", user.getJasoUserId());
		return userWorkTypeService.selectList(userWrapper);
	}
	public Object getJasoUserMsg(){
		UserInfoDetail detail = new UserInfoDetail();
		JasoUserPO cacheUser=(JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		JasoUserPO user = jasoUserMapper.selectById(cacheUser.getJasoUserId());
		if(user!=null){
			detail.setCompanyId(user.getCompanyId());
			detail.setJasoUserId(user.getJasoUserId());
			detail.setUserIcon(user.getUserIcon());
			detail.setUserName(user.getUserName());
			detail.setUserRealName(user.getUserRealName());
			
		    /////判断是否是管理员
			Integer isAdmin=0;
			MulSelect mul2 = MulSelect.newInstance("${1}.roleName,${1}.sort", new UserRolePO(), new RolePO());
			mul2.where("${user_role}")
				.eq("jasoUserId",user.getJasoUserId())
				.eq("companyId", user.getCompanyId());	
			List<Map<String,Object>> tests=(List<Map<String, Object>>) serviceMain.mulSelect(mul2);
			for(Map<String,Object> items:tests){
				int sort=Integer.valueOf(items.get("sort").toString());
				if(sort==1){
					isAdmin=1;
				}
			}
			detail.setIsAdmin(isAdmin);
			List<MenuPO> menus = new ArrayList<>();
			menus=roleMapper.selectMenuList(user.getJasoUserId());
			List<Long> menuIdList = new ArrayList<>();
			for(MenuPO menu:menus){
				menuIdList.add(menu.getMenuId());
			}
			detail.setMenuList(menuIdList);
			if(isAdmin==1){
				List<ProjectPO> list = new ArrayList<>();
				Wrapper<ProjectPO> wrapperProject = new EntityWrapper<>();
				wrapperProject.eq("company_id", cacheUser.getCompanyId());
				list = projectService.selectList(wrapperProject);
				List<ProjectList> projectList = new ArrayList<>();
				for(ProjectPO item:list){
					ProjectList lista = new ProjectList();
					lista.setProjectId(item.getProjectId());
					lista.setProjectName(item.getProjectName());
					lista.setCityCode(item.getCityCode());
					projectList.add(lista);
				}
				detail.setProjectList(projectList);
			}else{
				MulSelect mul = MulSelect.newInstance("${1}.projectName,${1}.cityCode", new UserProjectPO(), new ProjectPO());
				mul.where("${user_project}")
					.eq("jasoUserId",user.getJasoUserId())
					.eq("companyId", user.getCompanyId());	
				List<Map<String,Object>> test=(List<Map<String, Object>>) serviceMain.mulSelect(mul);
				List<ProjectList> projectList = new ArrayList<>();
				for(Map<String,Object> item:test){
					ProjectList list = new ProjectList();
					list.setProjectId(Long.valueOf(item.get("projectId").toString()));
					list.setProjectName(item.get("projectName").toString());
					list.setCityCode(item.get("cityCode").toString());
					projectList.add(list);
				}
				detail.setProjectList(projectList);
			}
			
		}
		return detail;
	}
	

}
