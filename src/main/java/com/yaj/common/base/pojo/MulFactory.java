package com.yaj.common.base.pojo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

public class MulFactory { 
	private static MulSelect make(String otherColumns,Collection<?> entities) {

		MulSelect mulSelect = new MulSelect(); 
		
		//create tableEntity
		TableEntity[] tes = new TableEntity[entities.size()];
		int k = 0; 
		for (Object o : entities) { 
			TableEntity te = new TableEntity();
				te.setEntity(o);
				te.setTableName(getTableName(o));
				te.setAllEntityColumns(getAllEntityColumns(o));
				te.setAllTableColumns(entityColumnsToTableColumns(te.getAllEntityColumns()));
			tes[k ++] = te;
		}
		
		//set select columns 
		String[] others = otherColumns.replaceAll(" ", "").split(",");
		StringBuilder otherColumnSb = new StringBuilder();
		for (String column : others) {
			String back = getOtherColumnName(column, tes);
			if (back != null)
				otherColumnSb.append(",").append(back);
		}
		otherColumnSb.insert(0, tes[0].getSelectSegment());
		mulSelect.setColumns(otherColumnSb.toString());
		mulSelect.setMasterTable(tes[0].getTableName() + " as " + tes[0].getNickName());
		
		//create left join segment
		List<String> join = new ArrayList<>();
		for (int i = 1; i < tes.length; i ++) {
			StringBuilder sb = new StringBuilder();
			
			Map<String, String> map = findSameColumn(tes[i], tes, i);
			if (map == null) 
				continue;
			
			TableEntity te = tes[i];
			
			sb.append(te.getTableName())
			  .append(" as ")
			  .append(te.getNickName())
			  .append(" on ")
			  .append(te.getNickName())
			  .append(".")
			  .append(map.get("column"))
			  .append(" = ") 
			  .append(map.get("table"))
			  .append(".")
			  .append(map.get("column"));
			
			join.add(sb.toString());
		}
		mulSelect.setJoin(join);
		//create sqlSegment
		StringBuilder sb = new StringBuilder();
		Map<String, Object> parameter = new HashMap<>();
		for (TableEntity te : tes) {
			for (int i = 0; i < te.getAllEntityColumns().size(); i ++) {
				Object value;
				try {
					value = getFieldValue(te.getEntity(), te.getAllEntityColumns().get(i));
					if (value != null) {
						String parameterName = "entity_" + te.getNickName() + "_" + te.getAllEntityColumns().get(i);
						sb.append(" and ")
						  .append(te.getNickName())
						  .append(".")
						  .append(te.getAllTableColumns().get(i))
						  .append(" = ")
						  .append(getStringValueByObject(parameterName, value, parameter));
					}
				} catch (Exception e) {
					e.printStackTrace();
				} 
				
			}
		} 
		mulSelect.addParameter(parameter);
		
		
		//strip sql injection
		String sql = sb.toString();
		sql = sql == null ? null : sql.replaceAll("('.+--)|(--)|(\\|)|(%7C)", "");
		mulSelect.setSqlSegment(sql);
		
		mulSelect.setTes(tes);
		return mulSelect;
	}
	public static MulSelect makeSelect(String otherColumns,Collection<?> entities) {
		return make(otherColumns, entities);
	}
	//otherColumns such as "${1}.createTime,${jobinfo}.createTime,${job_info}.jobInfoId"
	public static MulSelect makeSelect(String otherColumns,Object ...entities) {
		return make(otherColumns, Arrays.asList(entities));
		
	}
	
	//get table nick name addition '.' and table column by entity column 'tempColumn'
	public static String getOtherColumnName(String tempColumn, TableEntity[] tes) {
		String[] split$2 = tempColumn.split("\\.");
		if (split$2.length == 1) {
			return getOtherAllColumnName(split$2[0].replaceAll("\\$\\{|}", ""), tes);
		} else if (split$2.length != 2) {
			return null;
		}
		String tableDeputyName = split$2[0].replaceAll("\\$\\{|}", "");
		String tableName = null;
		String tableColumn = null;
		StringBuilder sb = new StringBuilder();
		
		try {
			int tableIndex = Integer.parseInt(tableDeputyName);
			
			if (tableIndex >= tes.length)
				return null;
			
			tableName = tes[tableIndex].getNickName();
			
			if (!tes[tableIndex].getAllEntityColumns().contains(split$2[1])) {
				return null;
			} else {
				tableColumn = tes[tableIndex].getAllTableColumns().get(tes[tableIndex].getAllEntityColumns().indexOf(split$2[1]));
			}
			
		} catch (Exception e) { 
			
			for (TableEntity te : tes) {
				String column = tableDeputyName.toLowerCase();
				if (column.equals(te.getNickName().toLowerCase()) || column.equals(te.getTableName().toLowerCase())) {
					tableName = te.getNickName();
					if (!te.getAllEntityColumns().contains(split$2[1])) {
						return null;
					} else {
						tableColumn = te.getAllTableColumns().get(te.getAllEntityColumns().indexOf(split$2[1]));
					}
				}
			}
			
		}
		if (tableName == null || tableColumn == null)
			return null;
		sb.append(tableName).append(".").append(tableColumn).append(" ").append(split$2[1]);

		return sb.toString();
	}
	
	private static String getOtherAllColumnName(String string, TableEntity[] tes) {
 
		StringBuffer sb = new StringBuffer();
		TableEntity te = null;
		try {
			int tableIndex = Integer.parseInt(string);
			
			if (tableIndex >= tes.length)
				return null;
			 
			te = tes[tableIndex];
			
		} catch (Exception e) { 
			for (TableEntity t : tes) {
				String column = string.toLowerCase();
				if (column.equals(t.getNickName().toLowerCase()) || column.equals(t.getTableName().toLowerCase())) {
					te = t;
					break;
				}
			}
			
		}
		for (int i = 0; i < te.getAllEntityColumns().size(); i ++) {
			sb.append(te.getNickName())
			  .append(".")
			  .append(te.getAllTableColumns().get(i))
			  .append(" ")
			  .append(te.getAllEntityColumns().get(i))
			  .append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	//strip injection
	private static String getStringValueByObject(String prefix, Object value, Map<String, Object> parameter) {
		
		StringBuilder sb = new StringBuilder();
		sb.append("#{parameter.").append(prefix).append("}");
		parameter.put(prefix, value);
		return sb.toString();
	}
	
	//find two table same column,from during index 'i' to first
	private static Map<String, String> findSameColumn(TableEntity entity, TableEntity[] tes, int i) {
		Map<String, String> map = new HashMap<>();
		String tryId = getIdAnnotation(entity.getEntity().getClass());
		if (tryId != null) 
			for (int j = 0; j < i; j ++) {
				String curId = getIdAnnotation(tes[j].getEntity().getClass());
				if (curId == null) return null;
				if (tes[j].getAllEntityColumns().contains(tryId)) {
					map.put("column", getTableColumn(tryId));
					map.put("table", tes[j].getNickName());
					return map;
				} else if (entity.getAllEntityColumns().contains(curId)) {
					map.put("column", getTableColumn(curId));
					map.put("table", tes[j].getNickName());
					return map;
				}
			}
		
		return null;
	}
	//get entity has @TableId annotation field name
	public static String getIdAnnotation(Class<?> clazz) {
		Field[] fields = clazz.getDeclaredFields(); 
		for (Field field : fields) {
			if (field.isAnnotationPresent(TableId.class)) {
				return field.getName();
			}
		}
		return null;
	}

	//entity column to table column
	public static String getTableColumn(String attribute) { 
		
		return attribute.replaceAll("[A-Z]", "_$0").toLowerCase();
		
	}
	
	//get table name by entity annotation
	public static String getTableName(Object entity) {
		if (entity.getClass().isAnnotationPresent(TableName.class)) {
			return entity.getClass().getAnnotation(TableName.class).value(); 
		}
		(new Exception("the entity " + entity.getClass().getSimpleName() + "is not use @tableName annotation")).printStackTrace();
		return null;
		
	}
		
	//all of entity columns convert to table columns
	private static List<String> entityColumnsToTableColumns(List<String> columns) {
		
		List<String> tableColumns = new ArrayList<>();
		
		columns.forEach(i -> {
			tableColumns.add(getTableColumn(i));
		});
		
		return tableColumns;
		
	}
	
	//get all columns by entity
	private static List<String> getAllEntityColumns(Object entity) {
		
		Field[] fields = entity.getClass().getDeclaredFields();  
		List<String> names = new ArrayList<>(fields.length); 
        for (Field field: fields) {
        	names.add(field.getName());
        } 
        return names;
		
	}
	private static Object getFieldValue(Object entity, String fieldName) {  
        try {    
            String firstLetter = fieldName.substring(0, 1).toUpperCase();    
            String getter = "get" + firstLetter + fieldName.substring(1);    
            Method method = entity.getClass().getMethod(getter, new Class[] {});    
            Object value = method.invoke(entity, new Object[] {});    
            return value;    
        } catch (Exception e) {    
            return null;    
        }    
    }

}