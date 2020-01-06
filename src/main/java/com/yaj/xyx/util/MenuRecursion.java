package com.yaj.xyx.util;

  
import java.util.ArrayList;  
import java.util.List;

import com.yaj.jaso.business.folder.entity.po.FolderPO;
  

public class MenuRecursion {  
	 //子节点  
	public static List<String> childPath = new ArrayList<String>();
    static  List<FolderPO> childFolder=new ArrayList<FolderPO>();  
    static  FolderPO parrentFolder=new FolderPO();  
    static String childPaths="";
    static String parrentNames;
    public MenuRecursion(String parrentName){
    	MenuRecursion.childPaths="";
    	MenuRecursion.childFolder.clear();
    	MenuRecursion.parrentNames=parrentName;
    }
    public MenuRecursion(){
    	MenuRecursion.childPaths="";
    	MenuRecursion.childFolder.clear();
    }
    /** 
     * 获取某个父节点下面的所有子节点 
     * @param menuList 
     * @param pid 
     * @return 
     */  
    public List<FolderPO> treeChildList( List<FolderPO> menuList, Long pid){  
        for(FolderPO mu: menuList){  
            //遍历出父id等于参数的id，add进子节点集合  
            if(mu.getParrentId().equals(pid)){  
                //递归遍历下一级  
            	treeChildList(menuList,mu.getFolderId());  
            	childFolder.add(mu);  
            }  
        }  
    return childFolder;  
    }  
    
    /** 
     * 获取某个父节点下面的所有子节点 ,并返回相应的路径
     * @param menuList 
     * @param pid 
     * @return 
     */  
    public List<FolderPO> treeChildList( List<FolderPO> menuList, Long pid,String parrentName){  
    	if(childPaths.equals("")){
    		childPaths=parrentName;
    	}
        for(int i=0;i<menuList.size();i++){  
            //遍历出父id等于参数的id，add进子节点集合  
            if(menuList.get(i).getParrentId().equals(pid)){  
            	if(menuList.get(i).getFileType()==1){
            		childPaths=childPaths+"/"+menuList.get(i).getName()+"."+menuList.get(i).getRemark();
            	}else{
            		if(childPaths.equals("")){
            			childPaths=parrentNames;
            		}
            		childPaths=childPaths+"/"+menuList.get(i).getName();
            	}
            	menuList.get(i).setRemark(childPaths);
            	treeChildList(menuList,menuList.get(i).getFolderId(),menuList.get(i).getName()); 
                //递归遍历下一级  
            	childFolder.add(menuList.get(i)); 
        		String[] test = childPaths.split("/");
        		String tests="";
        		for(int p=0;p<test.length-1;p++){
        			if(tests.equals("")){
        				tests=test[p];
        			}else{
        				tests=tests+"/"+test[p];
        			}
        			
        		}
        		childPaths=tests;
            }  
        }  
    return childFolder;  
    }  
    /** 
     * 获取某个子节点上面的顶层父节点 
     * @param menuList 
     * @param pid 
     * @return 
     */  
    public static FolderPO treeParrentList( List<FolderPO> menuList, Long pid){  
        for(FolderPO mu: menuList){  
    		 if(mu.getFolderId()==pid){  
    			//遍历出父id等于参数的pid
    			 if(mu.getParrentId().equals(0)){
             		parrentFolder=mu;
             	}else{
             		 //递归遍历上一级  
                 	treeParrentList(menuList,mu.getParrentId());  
             	}
             }  
        }  
        return parrentFolder;  
    }  
}