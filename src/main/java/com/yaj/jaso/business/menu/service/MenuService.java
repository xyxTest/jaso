package com.yaj.jaso.business.menu.service;

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
import com.yaj.core.util.BeanUtil;
import com.yaj.jaso.business.menu.entity.po.MenuPO;
import com.yaj.jaso.business.menu.entity.vo.MenuVo;
import com.yaj.jaso.business.menu.mapper.MenuMapper;
import com.yaj.jaso.business.jasouser.entity.po.JasoUserPO;
import com.yaj.jaso.global.PageVoUtil;
import com.yaj.xyx.util.MenuNodeUtil;
import com.yaj.xyx.util.MenuTree;

/*
 * @Description: 
 * @date: 2019-07-22
 */
@Service
public class MenuService extends ServiceImpl<MenuMapper, MenuPO> {

    @Resource
    MenuMapper menuMapper;

	public Object selectListByOptions(MenuVo mpo) {
		// TODO Auto-generated method stub
		Page<MenuPO> page = new Page<MenuPO>();
		page.setSize(mpo.getPageVo().getPageSize());
		page.setCurrent(mpo.getPageVo().getPageNo());
		MenuPO po = new MenuPO();
		BeanUtil.copy(mpo, po);
		Wrapper<MenuPO> wrapper = new EntityWrapper<MenuPO>();
		if(mpo.getMenuName()!=null){
			wrapper.like("menu_name", mpo.getMenuName());
		}
		wrapper.orderDesc(Arrays.asList("create_time"));
		page=selectPage(page, wrapper);
        return PageVoUtil.setPage(page);
	}

	public Object deleteList(List<MenuPO> mpo) {
		for(int i=0;i<mpo.size();i++){
			mpo.get(i).setIfDelete(1);
		}
		// TODO Auto-generated method stub
		return updateBatchById(mpo);
	}

	public Object add(MenuPO mpo) {
		// TODO Auto-generated method stub
		return insertOrUpdate(mpo);
	}

	public Object selectListByRole() {
		// TODO Auto-generated method stub
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		
		return menuMapper.selectListByRole(userInCache.getJasoUserId(),userInCache.getCompanyId());
	}

	public Object selectTreeByRole() {
		// TODO Auto-generated method stub
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		List<MenuPO> menuList = menuMapper.selectListByRole(userInCache.getJasoUserId(),userInCache.getCompanyId());
		List<MenuTree> getLists = new ArrayList<MenuTree>();
		for(int i=0;i<menuList.size();i++){
			if(menuList.get(i).getIfDelete()!=1){
				MenuTree tree = new MenuTree();
				tree.setValue(menuList.get(i).getMenuId());
				tree.setLabel(menuList.get(i).getMenuName());
				tree.setPid(Long.valueOf(0));
				getLists.add(tree);
			}
		}
		MenuNodeUtil nodeUtil = new MenuNodeUtil();
	    String resultString=nodeUtil.getJasonString(getLists);
	    if(resultString!=null){
	        return JSONArray.parse(resultString);
	    }
		return null;
	}

	public Object selectAllMenuList() {
		JasoUserPO userInCache = (JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		Wrapper<MenuPO> wrapper = new EntityWrapper<MenuPO>();
		wrapper.eq("company_id", userInCache.getCompanyId());
		return selectList(wrapper);
	}

}
