package com.yaj.jaso.business.department.mapper;
import java.util.List;
import com.yaj.common.mybatisplus.SuperMapper;
import com.yaj.jaso.business.department.entity.po.DepartmentPO;
import com.yaj.jaso.business.department.entity.vo.DepartmentTree;

/*
 * @Description: 
 * @date: 2019-07-22
 */
public interface DepartmentMapper extends SuperMapper<DepartmentPO> {
	List<DepartmentTree> selectTreeLists();

	List<DepartmentTree> selectProjectTreeList();
}
