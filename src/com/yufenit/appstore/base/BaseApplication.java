package com.yufenit.appstore.base;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

/**
 * @项目名: AppStore
 * @包名: com.yufenit.appstore
 * @类名: BaseApplication
 * @创建者: chniccs
 * @创建时间: 2015-8-22 下午6:58:04
 * @描述: Application的基类
 * 
 * @svn版本: $Rev$
 * @更新人: $Author$
 * @更新时间: $Date$
 * @更新描述: TODO
 */
public class BaseApplication extends Application
{
	private static Context	mContext;

	private static Handler	mHandler;

	private static Thread	mMainThread;

	private static Long	mMainThreadID;

	private static Looper	mMainLooper;

	public static Context getContext()
	{
		return mContext;
	}

	public static Handler getHandler()
	{
		return mHandler;
	}

	public static Thread getMainThread()
	{
		return mMainThread;
	}

	public static Long getMainThreadID()
	{
		return mMainThreadID;
	}

	public Looper getMainLooper()
	{
		return mMainLooper;
	}

	@Override
	public void onCreate()
	{
		// 程序的入口
		mContext = this;

		// 全局的Handler
		mHandler = new Handler();

		// 全局的mainThread
		mMainThread = Thread.currentThread();

		// 全局的线程ID
		mMainThreadID = mMainThread.getId();

		// 主线程中的Looper对象，子线程中Looper对象是需要手动的启动的
		mMainLooper = getMainLooper();
	}

}
