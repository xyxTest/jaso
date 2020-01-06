package com.yaj.common.cache;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yaj.common.cache.CacheConfig.CacheTypeEnum;

import javax.annotation.PostConstruct;

/**
* @Description: 缓存管理
* @author Peach
* @date 2017年5月9日
*
*/
@Component  
public class CacheManager{
	
	private static CacheManager cacheManager;
	
	@Autowired
	private org.springframework.cache.CacheManager caffeineCacheManager;
	
	@PostConstruct  
    public void  init(){  
		cacheManager=this;
		cacheManager.caffeineCacheManager=this.caffeineCacheManager;
    }  
	
	public static Object get(CacheTypeEnum cacheTypeEnum,Object key){
		return cacheManager.caffeineCacheManager.getCache(cacheTypeEnum.name()).get(key);
	}

	public static <T> T get(CacheTypeEnum cacheTypeEnum,Object key,Class clazz){
		return  (T) cacheManager.caffeineCacheManager.getCache(cacheTypeEnum.name()).get(key,clazz);
	}

	public static void put(CacheTypeEnum cacheTypeEnum,Object key,Object value){
		cacheManager.caffeineCacheManager.getCache(cacheTypeEnum.name()).put(key, value);
	}

	public static void del(CacheTypeEnum cacheTypeEnum,Object key){
		cacheManager.caffeineCacheManager.getCache(cacheTypeEnum.name()).evict(key);
	}

	public static void clear(CacheTypeEnum cacheTypeEnum){
		cacheManager.caffeineCacheManager.getCache(cacheTypeEnum.name()).clear();
	}

	public static boolean exist(CacheTypeEnum cacheTypeEnum,Object key){
		if(cacheManager.caffeineCacheManager.getCache(cacheTypeEnum.name()).get(key)!=null) {
			return true;
		}else {
			return false;
		}
	}
	
	
}


/**
 * @ClassName: CacheManager
 * @Description: 
 * @author: Aaron
 * @date: 2017/6/28 0028
 * @company 北京帮众信息技术有限公司
 */
//public class CacheManager {
//    private static CacheManager cacheManager = null;
//    private static CacheSystem cs = null;
//    private static CacheManager getInstance(Properties p) throws Exception {
//        if( cacheManager == null ){
//            synchronized (CacheManager.class) {
//                if(cacheManager == null){//二次检查
//                    cacheManager = new CacheManager(p);
//                }
//            }
//        }
//        return cacheManager;
//    }
//    private CacheManager(Properties p) throws Exception {
//        cs = new CacheSystem(p);
//    }
//    public static CacheSystem getCacheSystem() throws Exception {
//        Properties tag = new Properties();
//        Properties p = PropertiesLoading.getProperties("application.22.properties");
//        tag.setProperty("address",p.getProperty("redis.address"));
//        tag.setProperty("port",p.getProperty("redis.port"));
//        tag.setProperty("auth",p.getProperty("redis.auth"));
//        tag.setProperty("cacheClient",p.getProperty("redis.cacheClient"));
//        tag.setProperty("cashServer",p.getProperty("redis.cashServer"));
//        getInstance(tag);
//        return cs;
//    }
//
//
//}
