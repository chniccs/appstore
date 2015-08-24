package com.yufenit.appstore.manager;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @项目名: AppStore
 * @包名: com.yufenit.appstore.manager
 * @类名: ThreadPoolProxy
 * @创建者: chniccs
 * @创建时间: 2015-8-24 下午7:11:15
 * @描述: 线程池的创建类
 * 
 * @svn版本: $Rev$
 * @更新人: $Author$
 * @更新时间: $Date$
 * @更新描述:
 */
public class ThreadPoolProxy
{

	int					corePoolSize	= 3;
	int					maximumPoolSize	= 5;
	long				keepAliveTime	= 30 * 1000;
	ThreadPoolExecutor	mExecutor		= null;

	/**
	 * 
	 * @param size 核心线程数
	 * @param maxSize 最大线程数
	 * @param time 非核心线程保留时间
	 */
	public ThreadPoolProxy(int size, int maxSize, long time) {
		this.corePoolSize = size;
		this.maximumPoolSize = maxSize;
		this.keepAliveTime = time;
	}

	/**
	 * 执行任务的方法
	 * 
	 * @param task
	 */
	public void execute(Runnable task)
	{
		if (task == null) { return; }
		checkPool();

		mExecutor.execute(task);
	}

	/**
	 * 有返回值的执行方法，可以执行cancel操作
	 * 
	 * @param task
	 * @return
	 */
	public Future<?> submit(Runnable task)
	{
		if (task == null) { return null; }
		checkPool();

		return mExecutor.submit(task);
	}

	/**
	 * 移除任务的操作
	 * 
	 * @param task
	 */
	public void remove(Runnable task)
	{

		if (mExecutor != null)
		{

			mExecutor.getQueue().remove(task);
		}

	}

	/**
	 * 检查状态
	 */
	private void checkPool()
	{
		if (mExecutor == null || mExecutor.isShutdown())
		{
			BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>();

			ThreadFactory threadFactory = Executors.defaultThreadFactory();// 使用默认的线程工厂

			RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();

			mExecutor = new ThreadPoolExecutor(corePoolSize, // 核心线程的个数
												maximumPoolSize, // 线程池的最大值
												keepAliveTime, // 非核心线程的保留时间
												TimeUnit.MILLISECONDS, // 时间单位
												workQueue, // 工作队列
												threadFactory,// 线程工厂
												handler);// 异常处理器
		}
	}

}
