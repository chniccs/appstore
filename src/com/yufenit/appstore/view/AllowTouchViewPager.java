package com.yufenit.appstore.view;

import android.content.Context;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;


/**
 * @项目名: 	AppStore
 * @包名:	com.yufenit.appstore.view
 * @类名:	AllowTouchViewPager
 * @创建者:	chniccs
 * @创建时间:	2015-8-26	下午7:49:36 
 * @描述:	适配3.0以下版本中首页viewpager不能滑动的问题
 * 
 * @svn版本:	$Rev$
 * @更新人:	$Author$
 * @更新时间:	$Date$
 * @更新描述:	
 */
public class AllowTouchViewPager extends ViewPager
{

	public AllowTouchViewPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public AllowTouchViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	//重写分发事件方法
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev)
	{
		
		//判断版本小于3.0就执行
		if(Build.VERSION.SDK_INT<Build.VERSION_CODES.HONEYCOMB){
			//请求父控件中断触摸事件
			getParent().requestDisallowInterceptTouchEvent(true);
		}
		
		
		return super.dispatchTouchEvent(ev);
	}
	
	

}
