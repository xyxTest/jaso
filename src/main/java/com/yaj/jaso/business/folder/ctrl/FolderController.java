package com.yaj.jaso.business.folder.ctrl;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.yaj.jaso.business.folder.entity.po.FolderPO;
import com.yaj.jaso.business.folder.service.FolderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="云盘协同接口")
@EnableAutoConfiguration
@RestController
@RequestMapping(value="Folder")
public class FolderController {
	@Autowired
	FolderService fs;
	/*
	  * 新建文件夹
	  * 
	  * */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@ApiOperation(value="新建文件夹",notes="新建文件夹")
	@RequestMapping(value="/add", method = RequestMethod.POST)
    public Object addFloder(
    		@RequestBody FolderPO folder) {
        return fs.add(folder);
    }
	 
	 /*
	  * 
	  * 上传文件
	  * */
	@ApiOperation(value="上传文件",notes="上传文件")
	@RequestMapping(value="/uploadFiles", method = RequestMethod.POST)
    public Object addFiles(
    		@RequestBody FolderPO folder,
   		    @RequestParam(value = "fileList", required = true) MultipartFile[] fileList,
            HttpServletRequest request){
       return fs.uploadFloder(folder,fileList,request);
    }
	 
	 /*
	  * 
	  * 上传文件夹
	  * */
    @ApiOperation(value="上传文件夹",notes="上传文件夹")
	@RequestMapping(value="/uploadFolders", method = RequestMethod.POST)
    public Object uploadFloders(
   		   @RequestParam(value = "file", required = true) MultipartFile file,
           HttpServletRequest request,
           @RequestParam(value="filePath",required=true) String filePath,
           @RequestParam(value="projectId",required=true) Long projectId,
           @RequestParam(value="pid",required=true) Long pid){
       return fs.uploadFloderFiles(filePath,file,request,pid,projectId);
    }
	 /*
	  * 获取当前节点下面的所有子节点
	  * */
    @ApiOperation(value="获取当前节点下面的所有子节点",notes="获取当前节点下面的所有子节点")
    @RequestMapping(value="/getFolderIndexList",method=RequestMethod.GET)
    public Object getFolderIndexList(
    		@RequestBody FolderPO folder){
       return fs.getFolderIndexList(folder);
    }
    
    /*
     * 文件或者文件夹重命名
     * 
     * **/
    @ApiOperation(value="文件或者文件夹重命名",notes="文件或者文件夹重命名")
    @RequestMapping(value="/updateFolder",method=RequestMethod.POST)
    public Object updateFloder(
   		 @RequestParam(value="id",required=true) Long id,
   		 @RequestParam(value="name",required=true) String name){
       return fs.updateFloder(name,id);
    }
    
    /*
     * 
     * 移动文件到
     * **/
    @ApiOperation(value="移动文件到",notes="移动文件到")
    @RequestMapping(value="/takeFolderTo",method=RequestMethod.POST)
    public Object tokenFolderTo(
   		 @RequestParam(value="id",required=true) Long id,
   		 @RequestParam(value="pid",required=true) Long pid){
   	 return fs.takeFolderTo(id,pid);
    }
    /*
     * 
     * 删除文件或者文件夹
     * 
     * **/
   /* @ApiOperation(value="删除文件或者文件夹",notes="删除文件或者文件夹")
    @RequestMapping(value="/deleteFolder",method=RequestMethod.GET)
    public Object deleteFloder(@RequestBody FolderDelete idList){
       return fs.deleteFloder(idList);
    }   */
    
    /*
     * 
     * 搜索文件名接口
     * **/
    /*@ApiOperation(value="移动文件到",notes="移动文件到")
    @RequestMapping(value="/findFileLists",method=RequestMethod.GET)
    public Object findFileLists(
   		 @RequestParam(value="name",required=true) String name,
   		 @RequestParam(value="projectId",required=true) Long projectId){
       return fs.findFileLists(name,projectId);
    }   */
    
    /*
     * 文件夹或者文件下载
     * 
     * */
    
    /*@RequestMapping(value ="/batchDownload", method = RequestMethod.GET)
    public Object BatchDownload(
        @RequestParam(value = "ids", required = true) String ids,
        @RequestParam(value = "projectId", required = true) Long projectId,
        @RequestParam(value = "pid", required = true) Long pid,
        HttpServletRequest request, HttpServletResponse response) throws Exception{
   	 return fs.batchDownload(token,ids,pid,projectId,request,response);
    }*/
	
}
