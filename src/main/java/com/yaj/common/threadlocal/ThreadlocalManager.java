package com.yaj.common.threadlocal;


/**
* @Description: ThreadLocal管理类
* @author Orange
* @date 2017年8月23日
*
*/
public class ThreadlocalManager {
	
	private static ThreadLocal<ThreadlocalContext> threadlocalContext = new ThreadLocal<ThreadlocalContext>();

	public static ThreadlocalContext getThreadContext() {
		return threadlocalContext.get();
	}
	public static void setThreadContext(ThreadlocalContext token) {
		threadlocalContext.set(token);
	}
	public static void removeThreadContext() {
		threadlocalContext.remove();
	}
}
