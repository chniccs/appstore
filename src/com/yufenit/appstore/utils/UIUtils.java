package com.yufenit.appstore.utils;


import com.yufenit.appstore.BaseApplication;
import com.yufenit.appstore.R;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.util.DisplayMetrics;

/**
 * @项目名: GooglePlay10
 * @包名: org.itheima.googleplay10.utils
 * @类名: UIUtils
 * @创建者: chniccs
 * @创建时间: 2015-8-22 下午2:07:58
 * @描述: TODO
 * 
 * @svn版本: $Rev: 6 $
 * @更新人: $Author: xq $
 * @更新时间: $Date: 2015-08-22 14:23:07 +0800 (Sat, 22 Aug 2015) $
 * @更新描述: TODO
 */
public class UIUtils
{

	/**
	 * 获得上下文
	 * 
	 * @return
	 */
	public static Context getContext()
	{
		return BaseApplication.getContext();
	}

	/**
	 * 获得资源
	 * 
	 * @return
	 */
	public static Resources getResources()
	{
		return getContext().getResources();
	}

	/**
	 * 获得string类型的数据
	 * 
	 * @param resId
	 * @return
	 */
	public static String getString(int resId)
	{
		return getContext().getResources().getString(resId);
	}

	/**
	 * 获得handler
	 * 
	 * @return
	 */
	public static Handler getMainHandler()
	{
		return BaseApplication.getHandler();
	}

	/**
	 * 在主线程中执行任务
	 * 
	 * @param task
	 */
	public static void post(Runnable task)
	{
		getMainHandler().post(task);
	}

	/**
	 * 像素转dp
	 * 
	 * @param px
	 * @return
	 */
	public static int px2dp(int px)
	{
		// px = dp * (dpi / 160)
		// dp = px * 160 / dpi

		DisplayMetrics metrics = getResources().getDisplayMetrics();
		int dpi = metrics.densityDpi;
		return (int) (px * 160f / dpi + 0.5f);
	}

	/**
	 * dp转px
	 * 
	 * @param dp
	 * @return
	 */
	public static int dp2px(int dp)
	{
		// px = dp * (dpi / 160)
		DisplayMetrics metrics = getResources().getDisplayMetrics();
		int dpi = metrics.densityDpi;

		return (int) (dp * (dpi / 160f) + 0.5f);
	}

	/**
	 * 获得包名
	 * 
	 * @return
	 */
	public static String getPackageName()
	{
		return getContext().getPackageName();
	}
	/**
	 * 从XML文件中通过属性ID获得文本信息
	 * @param id
	 * @return
	 */
	public static String[] getStringArray(int id){
		
		
		String[] strings = getContext().getResources().getStringArray(id);
		return strings;
	}
	
	public static int getColor(boolean isSelected){
		if(isSelected){
			
			return getResources().getColor(R.color.tab_selected);
		}else{
			return getResources().getColor(R.color.tab_normal);
		}
	}
}
