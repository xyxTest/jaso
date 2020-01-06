package com.yaj.jaso.business.department.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Resource;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yaj.common.threadlocal.ThreadlocalManager;
import com.yaj.jaso.business.department.entity.po.DepartmentPO;
import com.yaj.jaso.business.department.entity.vo.DepartmentTree;
import com.yaj.jaso.business.department.entity.vo.DepartmentVo;
import com.yaj.jaso.business.department.mapper.DepartmentMapper;
import com.yaj.jaso.business.jasouser.entity.po.JasoUserPO;
import com.yaj.jaso.business.jasouser.entity.vo.UserDepartmentTree;
import com.yaj.jaso.global.PageVoUtil;
import com.yaj.xyx.util.DepartmentNodeUtil;
import com.yaj.xyx.util.NodeUtil;

/*
 * @Description: 
 * @date: 2019-07-22
 */
@Service
public class DepartmentService extends ServiceImpl<DepartmentMapper, DepartmentPO> {

    @Resource
    DepartmentMapper departmentMapper;

	public Object selectLists(DepartmentVo dpo) {
		// TODO Auto-generated method stub
		Page<DepartmentPO> page = new Page<DepartmentPO>();
		page.setCurrent(dpo.getPageVo().getPageNo());
		page.setSize(dpo.getPageVo().getPageSize());
		Wrapper<DepartmentPO> wrapper = new EntityWrapper<DepartmentPO>();
		wrapper.orderDesc(Arrays.asList("create_time"));
		if(dpo.getDepartmentName()!=null){
			wrapper.like("department_name", dpo.getDepartmentName());
		}
		if(dpo.getCompanyId()!=null){
			wrapper.eq("company_id", dpo.getCompanyId());
		}
		if(dpo.getPid()!=null){
			wrapper.eq("pid", dpo.getPid());
		}	
		page=selectPage(page, wrapper);
        return PageVoUtil.setPage(page);
	}

	public Object selectListByCompanyId(DepartmentPO dpo) {
		// TODO Auto-generated method stub
		Wrapper<DepartmentPO> wrapper = new EntityWrapper<DepartmentPO>();
		wrapper.eq("company_id", dpo.getCompanyId());
        return selectList(wrapper);
	}

	public Object add(DepartmentPO dpo) {
		// TODO Auto-generated method stub
		JasoUserPO cacheUser=(JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		dpo.setCreateUser(cacheUser.getJasoUserId());
		dpo.setCompanyId(cacheUser.getCompanyId());
		return insertOrUpdate(dpo);
	}

	public Object deleteDepartmentList(List<DepartmentPO> dpo) {
		for(int i=0;i<dpo.size();i++){
			dpo.get(i).setIfDelete(1);
		}
		// TODO Auto-generated method stub
		return updateBatchById(dpo);
	}
	public Object selectTreeLists(){
		List<DepartmentTree> lists = departmentMapper.selectTreeLists();//所有数据未转化成树结构
		List<DepartmentTree> getLists = new ArrayList<DepartmentTree>();
		for(int i=0;i<lists.size();i++){
			if(lists.get(i).getIfDelete()!=1){
				getLists.add(lists.get(i));
			}
		}
		NodeUtil nodeUtil = new NodeUtil();
	    String resultString=nodeUtil.getJasonString(getLists);
	    if(resultString!=null){
	        return JSONArray.parse(resultString);
	    }
		return null;
	}
	public Object selectJasoUserTreeLists(){
		List<DepartmentTree> lists = departmentMapper.selectTreeLists();//所有数据未转化成树结构
		if(!lists.isEmpty()){
			List<UserDepartmentTree> treelists = new ArrayList<UserDepartmentTree>();
			for(DepartmentTree tree:lists){
				if(tree.getIfDelete()==0){
					UserDepartmentTree udTree = new UserDepartmentTree();
					udTree.setId(tree.getValue());
					udTree.setLabel(tree.getLabel());
					udTree.setPid(tree.getPid());
					treelists.add(udTree);
				}
			}
			DepartmentNodeUtil nodeUtil = new DepartmentNodeUtil();
		    String resultString=nodeUtil.getJasonString(treelists);
		    if(resultString!=null){
		        return JSONArray.parse(resultString);
		    }
		}
		return null;
	}

	public Object adds(DepartmentPO dpo) {
		JasoUserPO cacheUser=(JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		dpo.setCreateUser(cacheUser.getJasoUserId());
		dpo.setCompanyId(cacheUser.getCompanyId());
		if(insert(dpo)){
			return dpo; 
		}
		return false;
	}

	public Object selectProjectTree() {
		List<DepartmentTree> lists = departmentMapper.selectProjectTreeList();//所有数据未转化成树结构
		if(!lists.isEmpty()){
			List<UserDepartmentTree> treelists = new ArrayList<UserDepartmentTree>();
			for(DepartmentTree tree:lists){
				if(tree.getIfDelete()==0){
					UserDepartmentTree udTree = new UserDepartmentTree();
					udTree.setId(tree.getValue());
					udTree.setLabel(tree.getLabel());
					udTree.setPid(tree.getPid());
					treelists.add(udTree);
				}
			}
			DepartmentNodeUtil nodeUtil = new DepartmentNodeUtil();
		    String resultString=nodeUtil.getJasonString(treelists);
		    if(resultString!=null){
		        return JSONArray.parse(resultString);
		    }
		}
		return null;
	}

	public Object selectProjectByProjectId(DepartmentPO dpo) {
		// TODO Auto-generated method stub
		JasoUserPO cacheUser=(JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		Wrapper<DepartmentPO> wrapper = new EntityWrapper<>();
		wrapper.eq("company_id", cacheUser.getCompanyId())
		.orderBy("sort_id");
		List<DepartmentPO> gets = new ArrayList<>();
		List<DepartmentPO> result = new ArrayList<>();
		gets = selectList(wrapper);
		for(int i=0;i<gets.size();i++){
			if(i==0){
				result.add(gets.get(i));
			}else{
				if(gets.get(i).getPid().equals(gets.get(0).getDepartmentId())){
					result.add(gets.get(i));
				}
			}
		}
		return result;
	}

}
