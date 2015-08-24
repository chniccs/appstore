package com.yufenit.appstore.manager;

/**
 * @项目名: AppStore
 * @包名: com.yufenit.appstore.manager
 * @类名: ThreadPoolManager
 * @创建者: chniccs
 * @创建时间: 2015-8-24 下午7:51:48
 * @描述: 线程池的管理类
 * 
 * @svn版本: $Rev$
 * @更新人: $Author$
 * @更新时间: $Date$
 * @更新描述: 
 */
public class ThreadPoolManager
{

	private static ThreadPoolProxy	mThreadPool;	// 耗时操作的线程池
	private static Object lock=new Object();

	public static ThreadPoolProxy getLongPool()
	{

		if (mThreadPool == null)
		{
			//通过同步锁来防止第一次开启线程池时发生同时创建两个的异常
			synchronized(lock){
				
				if(mThreadPool == null){
					
					mThreadPool = new ThreadPoolProxy(3, 3, 5000);
				}
			}
		}

		return mThreadPool;
	}
}
