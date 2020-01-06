package com.yaj.core.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBHelper {
	public String dbName = null;
	public static final String url = "jdbc:mysql://127.0.0.1/";  
    public static final String name = "com.mysql.jdbc.Driver";  
    public static final String user = "root";  
    public static final String password = "black94.";  

    public Connection conn = null;  
    public PreparedStatement pst = null;  

    public DBHelper() {  
        this.conn();
    }  

    public void conn () {
    	try {  
            Class.forName(name);//指定连接类型  
            String temp = url;
            if (dbName != null)
            	temp = url + dbName;
            conn = DriverManager.getConnection(temp, user, password);//获取连接  
            
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }
    public void close() {  
        try {  
            this.conn.close();  
            this.pst.close();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }  
    
    public void resetDBName(String dbName) {
    	this.dbName = dbName;
    	this.close();
    	this.conn();
    }
    public void excuteSql(String sql) throws Exception {
    	pst = conn.prepareStatement(sql);//准备执行语句  
    	pst.executeUpdate();
    }
}
