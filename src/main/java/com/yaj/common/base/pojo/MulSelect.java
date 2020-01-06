package com.yaj.common.base.pojo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map; 

public class MulSelect {
	
	//select all columns what you want
	private String columns;
	//the from table name
	private String masterTable;
	// order by columns
	private String orderBy;
	
	private String groupBy;
	//left join table names 
	private List<String> join;
	//where sql segment
	private String sqlSegment;
	//TableEntity array
	private TableEntity[] tes;
	//user custom where sql segments
	private List<WhereCustomSegment> whereCustomSegments = null;
	//avoid sql injection parameters
	private Map<String, Object> parameter = null;
	
	private boolean addCustomFlag = true;
	
	private Integer start;

	private Integer end;
	public static MulSelect newInstance(String otherColumns, Collection<?> entities) {
		return  MulFactory.makeSelect(otherColumns, entities);
	}
	public static MulSelect newInstance(String otherColumns, Object ...entities) {
//		StringBuffer sb = new StringBuffer();
//		for (Object o : entities) {
//			sb.append(o.getClass().getSimpleName());
//		}
//		sb.append(Integer.toHexString(otherColumns.hashCode())); 
//		MulSelect ms = SimpleCache.get(sb.toString());
//		if (ms != null) {
//			return  ms;
//		} else { 
		return  MulFactory.makeSelect(otherColumns, entities);
//			SimpleCache.put(sb.toString(), ms);
//			return ms;
//		}
	}
	
	public Map<String, Object> getParameter() {
		return parameter;
	}
	public void setParameter(Map<String, Object> parameter) {
		this.parameter = parameter;
	}
	public void addParameter(Map<String, Object> parameter) {
		if (this.parameter == null) {
			this.parameter = new HashMap<>();
		}
		this.parameter.putAll(parameter);
	}
	public String getColumns() {
		return columns;
	}
	public void setColumns(String columns) {
		this.columns = columns;
	}
	public String getMasterTable() {
		return masterTable;
	}
	public void setMasterTable(String masterTable) {
		this.masterTable = masterTable;
	} 
	
	public List<String> getJoin() {
		return join;
	}
	public void setJoin(List<String> join) {
		this.join = join;
	}
	public MulSelect clearJoin() {
		this.join.clear();
		return this;
	}
	//TODO left : ${0}.companyId, right : ${3}.companyId
	public MulSelect addJoin(String left, String right) {
		getTNCname(left);
		return this;
	}
	//TODO
	//get table,nick, column name by custom grammar ${0}.columnName
	private void getTNCname(String batch) {
		this.join.add("question.question_id = answer.question_id");
	}
	public String getSqlSegment() {
		//reject myBatis visit three times
		if (addCustomFlag) {
			addCustomFlag = false;
			this.setCustomWhere();
		}
		return sqlSegment;
	}
	public void setSqlSegment(String sqlSegment) {
		this.sqlSegment = sqlSegment;
	}
	private void setCustomWhere() {
		
		if (whereCustomSegments != null) {
			StringBuffer sb = new StringBuffer(sqlSegment);
			for (WhereCustomSegment i : whereCustomSegments) {
				if (parameter != null && i.getParameter() != null)
					addParameter(i.getParameter());
				
				for (String segment : i.getSegmentSql()) {
					sb.append(" ").append(segment);
				}
			} 
			for (TableEntity te : this.tes) {
				sb.append(" ")
				  .append("AND (")
				  .append(te.getNickName())
				  .append(".")
				  .append("if_delete is null or ")
				  .append(te.getNickName())
				  .append(".")
				  .append("if_delete")
				  .append("=")
				  .append("0)");
			}
			sqlSegment = sb.toString();
		}
	}

	public String getOrderBy() {
		return orderBy;
	}
	
	public void setOrderBy(String columns) {
		
//		StringBuilder sb = new StringBuilder();
//		String[] arr = columns.split(",");
//		for (String column : arr) {
//			String[] t$2 = column.split(" ");
//			String ob = MulFactory.getOtherColumnName(t$2[0], tes);
//			if (ob != null) {
//				sb.append(ob.split(" ")[0]);
//				if (t$2.length == 2) {
//					sb.append(" ").append(t$2[1]);
//				}
//				sb.append(",");
//			}
//		}
//		//delete last character ','
//		if (sb.length() != 0)
//			sb.deleteCharAt(sb.length() - 1); 
//		
//		this.orderBy = sb.toString();
		this.orderBy = columns;
	}
	public TableEntity[] getTes() {
		return tes;
	}
	public void setTes(TableEntity[] tes) {
		this.tes = tes;
	}
	public WhereCustomSegment where(String table) {
		
		String tableDeputyName = table.replaceAll("\\$\\{|}", "");
		TableEntity tableEntity = null;
		try {
			int tableIndex = Integer.parseInt(tableDeputyName);
			
			if (tableIndex < tes.length) 
				tableEntity = tes[tableIndex]; 
		
		} catch (Exception e) {  
			for (TableEntity te : tes) {
				String column = tableDeputyName.toLowerCase();
				if (column.equals(te.getNickName().toLowerCase()) || column.equals(te.getTableName().toLowerCase())) {
					tableEntity = te;
					break;
				}
			} 
		}
		
		if (tableEntity == null) {
			(new Exception("no table '" + table + "' be found,use default table : '0'")).printStackTrace();
			tableEntity = tes[0];
		}
		
		WhereCustomSegment whereCustomSegment = new WhereCustomSegment(tableEntity);
		if (whereCustomSegments == null) {
			whereCustomSegments = new ArrayList<>();
		}
		whereCustomSegments.add(whereCustomSegment);
		return whereCustomSegment; 
				
	}
	 
	public Integer getStart() {
		return start;
	}
 
	public Integer getEnd() {
		return end;
	}
 
	public void setPage(Integer pageNo, Integer pageSize) {
		
		if (pageSize == null || pageSize == 0 || pageNo == null) {
			start = null; end = null;
		} else {
			start = (pageNo - 1) * pageSize;
			end = pageSize;
		}
			
	}
	public String getGroupBy() {
		return groupBy;
	}
	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	} 
	 
}
