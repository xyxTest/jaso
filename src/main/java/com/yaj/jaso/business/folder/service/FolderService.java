package com.yaj.jaso.business.folder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yaj.common.threadlocal.ThreadlocalManager;
import com.yaj.jaso.business.folder.entity.po.FolderPO;
import com.yaj.jaso.business.folder.entity.vo.FolderVo;
import com.yaj.jaso.business.folder.mapper.FolderMapper;
import com.yaj.jaso.business.jasofile.entity.po.JasoFilePO;
import com.yaj.jaso.business.jasofile.service.JasoFileService;
import com.yaj.jaso.business.jasouser.entity.po.JasoUserPO;
import com.yaj.jaso.global.PageVoUtil;
import com.yaj.xyx.util.MenuRecursion;

/*
 * @Description: 
 * @date: 2019-09-07
 */
@Service
public class FolderService extends ServiceImpl<FolderMapper, FolderPO> {

    @Resource
    FolderMapper folderMapper;
    @Autowired
    JasoFileService fileService;
    private static String filePaths="project/folder/";
	public Object add(FolderPO floder) {
		// TODO Auto-generated method stub
		JasoUserPO cacheUser=(JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		floder.setCompanyId(cacheUser.getCompanyId());
		floder.setJasoUserId(cacheUser.getJasoUserId());
		return insertOrUpdate(floder);
	}

	/*public Object deleteFloder(FolderDelete idList) {
		if()
		for(int i=0;i<idList.size();i++){
			idList.get(i).setIfDelete(1);
		}
		// TODO Auto-generated method stub
		return updateBatchById(idList);
	}*/

	public Object selectLists(FolderPO floder) {
		JasoUserPO cacheUser=(JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		Wrapper<FolderPO> wrapper = new EntityWrapper<>();
		wrapper.eq("company_id", cacheUser.getCompanyId())
		.eq(floder.getProjectId()!=null,"porject_id", floder.getProjectId())
		.eq("file_type", 2)
		.like(floder.getName()!=null,"name", floder.getName());
		return selectList(wrapper);
	}

	public Object selectList(FolderVo floder) {
		Page<FolderPO> page = new Page<FolderPO>();
		page.setCurrent(floder.getPageVo().getPageNo());
		page.setSize(floder.getPageVo().getPageSize());
		Wrapper<FolderPO> wrapper = new EntityWrapper<>();
		wrapper.orderDesc(Arrays.asList("create_time"))
		.eq(floder.getFolderId()!=null,"pid",floder.getFolderId());
		page=selectPage(page, wrapper);
		return PageVoUtil.setPage(page);
	}
	
	public Object getById(Long id) {
		// TODO Auto-generated method stub
		return selectById(id);
	}
	/*@SuppressWarnings("unchecked")
	public Object getFolderList(FolderPO floder) {
		// TODO Auto-generated method stub
		DataWrapper<Object> foldersList = new DataWrapper<Object>();
		List<FolderPO> folders = new ArrayList<FolderPO>();
		List<FolderPO> getfolders = new ArrayList<FolderPO>();
		JasoUserPO cacheUser=(JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		if(cacheUser!=null){
			if(floder.getFolderId()!=null){
				FolderPO folderOld = new FolderPO();
				folderOld=selectById(floder.getFolderId());
				if(folderOld!=null){
					Wrapper<FolderPO> wrapper = new EntityWrapper<>();
					wrapper.eq(floder.getProjectId()!=null,"project_id",floder.getProjectId());
					folders=selectList(wrapper);
					MenuRecursion mr = new MenuRecursion();
					//@SuppressWarnings("static-access")
					List<FolderPO> childrens = mr.treeChildList(folders, floder.getFolderId());
					childrens.add(floder);
					if(!childrens.isEmpty()){
						getfolders = folderDao.getFolderLists(childrens,floder.getProjectId());
						if(!getfolders.isEmpty())
						{
							@SuppressWarnings("rawtypes")
							List dataList = new ArrayList();  
							for(Folder ss:getfolders){
								@SuppressWarnings("rawtypes")
								HashMap dataRecord1 = new HashMap();  
								dataRecord1.put("id", ss.getId().toString());
								dataRecord1.put("name", ss.getName());
								dataRecord1.put("remark", ss.getRemark());
								dataRecord1.put("fileType", ss.getFileType()+"");
								if(ss.getFileId()!=null){
									Files file = fileService.getById(ss.getFileId());
									if(file!=null){
										dataRecord1.put("url", file.getUrl());
									}
								}else{
									dataRecord1.put("url", null);
								}
								if(ss.getParrentId()==0){
									dataRecord1.put("parentId", "");
								}else{
									dataRecord1.put("parentId", ss.getParrentId().toString());
								}
								String str=sdf.format(ss.getCreateDate());
								dataRecord1.put("uploadDate", str);
								if(ss.getUserId()!=null){
									User user = userDao.getById(ss.getUserId());
									if(user!=null){
										String userName=user.getUserName();
										dataRecord1.put("userName", userName);
									}else{
										dataRecord1.put("userName", "");
									}
									
								}
								dataList.add(dataRecord1);
							}
							NodeUtil nodeUtil = new NodeUtil();
							String resultString=nodeUtil.getJasonString(dataList);
							if(resultString!=null){
								foldersList.setData(JSONArray.parse(resultString));
							}
							
						}
					}else{
						if(!folders.isEmpty())
						{
							@SuppressWarnings("rawtypes")
							List dataList = new ArrayList();  
							for(Folder ss:folders){
								@SuppressWarnings("rawtypes")
								HashMap dataRecord1 = new HashMap();  
								dataRecord1.put("id", ss.getId().toString());
								dataRecord1.put("name", ss.getName());
								dataRecord1.put("remark", ss.getRemark());
								dataRecord1.put("fileType", ss.getFileType()+"");
								if(ss.getFileId()!=null){
									Files file = fileService.getById(ss.getFileId());
									if(file!=null){
										dataRecord1.put("url", file.getUrl());
									}
								}else{
									dataRecord1.put("url", null);
								}
								if(ss.getParrentId()==0){
									dataRecord1.put("parentId", "");
								}else{
									dataRecord1.put("parentId", ss.getParrentId().toString());
								}
								String str=sdf.format(ss.getCreateDate());
								dataRecord1.put("uploadDate", str);
								if(ss.getUserId()!=null){
									User user = userDao.getById(ss.getUserId());
									if(user!=null){
										String userName=user.getUserName();
										dataRecord1.put("userName", userName);
									}else{
										dataRecord1.put("userName", "");
									}
									
								}
								dataList.add(dataRecord1);
							}
							NodeUtil nodeUtil = new NodeUtil();
							String resultString=nodeUtil.getJasonString(dataList);
							if(resultString!=null){
								foldersList.setData(JSONArray.parse(resultString));
							}
							
						}
					}
				}else{
					foldersList.setErrorCode(ErrorCodeEnum.Target_Not_Existed);
				}
			}
		}else{
			foldersList.setErrorCode(ErrorCodeEnum.User_Not_Logined);
		}
		
		return foldersList;  
		  
	}
*/
	
	public Object uploadFloder(FolderPO floder, MultipartFile[] fileList,
			HttpServletRequest request) {
		JasoUserPO cacheUser=(JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		if(cacheUser!=null){
			if(fileList!=null){
				List<FolderPO> addList = new ArrayList<FolderPO>();
				floder.setJasoUserId(cacheUser.getJasoUserId());
				floder.setFileType(1);
				for(MultipartFile mf : fileList){
					FolderPO f = new FolderPO();
					floder.setJasoUserId(cacheUser.getJasoUserId());
					JasoFilePO files = fileService.uploadFile(filePaths+floder.getProjectId(), mf, 11, request);
					if(files!=null){
						f.setFileId(files.getJasoFileId());
						f.setName(files.getRealName());
					}
					addList.add(f);
				}
				if(!insertBatch(addList)){
					return true;
				}
			}
		}
		return false;
	}

	public Object deleteFloder(String id) {
		JasoUserPO cacheUser=(JasoUserPO)ThreadlocalManager.getThreadContext().getCurUser();
		if(cacheUser!=null){
			if(id!=null){
				String[] idsp =id.split(",");
				for(String s:idsp){
					FolderPO folderP = selectById(Long.valueOf(s));
					if(folderP!=null){
						if(folderP.getFileType()==1){
							if(!deleteById(Long.valueOf(s))){
								return false;
							}
						}else{
							Wrapper<FolderPO> wrapper = new EntityWrapper<>();
							wrapper.eq("company_id", cacheUser.getCompanyId());
							List<FolderPO> getall = selectList(wrapper);
							MenuRecursion men = new MenuRecursion();
							List<FolderPO> childrens = men.treeChildList(getall, Long.valueOf(s));
							if(!childrens.isEmpty()){
								List<Long> ids =new ArrayList<>();
								for(int i=0;i<childrens.size();i++){
									ids.add(childrens.get(i).getFolderId());
								}
								if(!deleteBatchIds(ids)){
									return false;
								}
							}else{
								if(!deleteById(Long.valueOf(s))){
									return false;
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

	/*public Object batchDownload(String ids,Long projectId ,HttpServletRequest request,
			HttpServletResponse response){
		Wrapper<FolderPO> wrapper = new EntityWrapper<>();
		List<FolderPO> alllists = selectList(wrapper);
		List<Folder> lists = this.selectByIds(ids);
		String rootPath = request.getSession().getServletContext().getRealPath("/");
		String currentTimemils = System.currentTimeMillis()+"";
		String path = rootPath+"/downloadFolderFiles/"+token+currentTimemils+"/";
		File file_path = new File(rootPath+"/downloadFolderFiles/"+token+currentTimemils+"/");
		if(!file_path.exists()){
			file_path.mkdirs();
		}
		//压缩包名字
		String fileName="download.zip";
		List<File> fileLists = new ArrayList<File>();
		String outpaths="";
		if(!lists.isEmpty()){
			DeFiles  defiles = new DeFiles();
			if(defiles.deletefile(rootPath+"/downloadFolderFiles")){
				for(FolderPO folder:lists){
					if(folder.getFileType()==1){
						JasoFilePO files=fileService.selectById(folder.getFileId());
						if(files!=null){
							String[] test = rootPath.split("\\\\");
							String[] test1 = files.getUrl().split("/");
							String pathss="";
							String outpaths2="";
							for(String s:test){
								if(pathss.equals("")){
									pathss=s;
								}else{
									pathss=pathss+"\\"+"\\"+s;
								}
							}
							for(String sb:test1){
								if(pathss.equals("")){
									pathss=sb;
								}else{
									pathss=pathss+"\\"+"\\"+sb;
								}
							}
							File source = new File(pathss);
							
							for(String ss:test){
								if(outpaths2.equals("")){
									outpaths2=ss;
								}else{
									outpaths2=outpaths2+"\\"+"\\"+ss;
								}
							}
							String strname = currentTimemils;
							String downloadUrl =outpaths2+"\\"+"\\"+"downloadFolderFiles\\\\"+strname; 
							outpaths2=outpaths2+"\\"+"\\"+"downloadFolderFiles\\\\"+strname+"\\\\"+folder.getName()+"."+folder.getRemark();
							File downs = new File(downloadUrl);
							downs.mkdirs();
							File dest = new File(outpaths2);
							CopyFilesExample.copyFileUsingFileChannels(source, dest);
							fileLists.add(dest);
						}
					}else{
						/////获取单个文件夹的所有子
						//Folder father = MenuRecursion.treeParrentList(alllists, folder.getParrentId());
						List<Folder> childrens = new ArrayList<Folder>();
						MenuRecursion men = new MenuRecursion();
						childrens=men.treeChildList(alllists, folder.getId(),folder.getName());
						if(!childrens.isEmpty()){
							childrens.add(folder);
							for(int i=0;i<childrens.size();i++){
								File file=null;
								if(childrens.get(i).getFileType()==1){
									Files files=fileService.getById(childrens.get(i).getFileId());
									if(files!=null){
										String[] test = rootPath.split("\\\\");
										String[] test1 = files.getUrl().split("/");
										String pathss="";
										outpaths="";
										for(String s:test){
											if(pathss.equals("")){
												pathss=s;
											}else{
												pathss=pathss+"\\"+"\\"+s;
											}
										}
										for(String sb:test1){
											if(pathss.equals("")){
												pathss=sb;
											}else{
												pathss=pathss+"\\"+"\\"+sb;
											}
										}
										File source = new File(pathss);
									
										for(String ss:test){
											if(outpaths.equals("")){
												outpaths=ss;
											}else{
												outpaths=outpaths+"\\"+"\\"+ss;
											}
										}
										outpaths=outpaths+"\\"+"\\"+"downloadFolderFiles\\\\"+token+currentTimemils+"\\\\";
										String[] out2 = childrens.get(i).getRemark().split("/");
										String outFiles="";
										outFiles=outpaths;
										for(String sb1:out2){
											if(outFiles.equals("")){
												outFiles=sb1;
											}else{
												outFiles=outFiles+"\\"+"\\"+sb1;
											}
										}
										file = new File(outFiles);
										if(file.exists()){
											file.delete();
										}else{
											String[] trys = outFiles.split("\\\\");
							        		String tests="";
							        		for(int p=0;p<trys.length-1;p++){
							        			if(tests.equals("")){
							        				tests=trys[p];
							        			}else{
							        				tests=tests+"\\\\"+trys[p];
							        			}
							        			
							        		}
							        		File stry = new File(tests);
							        		stry.mkdirs();
										}
										CopyFilesExample.copyFileUsingFileChannels(source, file);
										fileLists.add(file);
									}
								}else{
									if(childrens.size()==1 || (childrens.size()>1 && i==(childrens.size()-1))){
										file = new File(path+childrens.get(i).getName());
										if(!file.exists() && !file.isDirectory()) {
										    file.mkdirs();
										}
									}else{
										file = new File(path+childrens.get(i).getRemark());
										if(!file.exists() && !file.isDirectory()) {
										    file.mkdirs();
										}
									}
									
								}
								//如果路径不存在，新建
								fileLists.add(file);
							}
							}
						}
						}
					}
			}
			if(lists.size()==1){
    		   fileName =lists.get(0).getName()+".zip";
			}
			ScatterSampleTest test = new ScatterSampleTest();
			if(outpaths.equals("")){
			String[] test1 = path.split("/");
			String pathss="";
			for(String s:test1){
				if(pathss.equals("")){
					pathss=s;
				}else{
					pathss=pathss+"\\"+"\\"+s;
				}
			}
			outpaths=pathss;
		}
	    File results = new File(outpaths+"\\"+fileName);  
	    test.createZipFile(outpaths, results);  
	    if(CustomFileUtil.downloadFile(results, response, true)){
	    	
	    }
        return result;
	}*/
	public List<File> getFileLists(File file){
		List<File> files = new ArrayList<File>();
		if(file.exists()) {
	           File[] fileArr = file.listFiles();
	           for (File file2 : fileArr) {
	               files.add(file2);
	           }
		}else{
			file.mkdirs();
		}
		return files;
	}

	public Object uploadFloderFiles(String filePath, MultipartFile file, HttpServletRequest request, Long pid,
			Long projectId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getFolderIndexList(FolderPO folder) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object updateFloder(String name, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object takeFolderTo(Long id, Long pid) {
		// TODO Auto-generated method stub
		return null;
	}

}
