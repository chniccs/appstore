package com.yufenit.appstore.activity;

import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

import com.astuetz.PagerSlidingTabStrip;
import com.yufenit.appstore.R;
import com.yufenit.appstore.fragment.FragmentFactory;
import com.yufenit.appstore.utils.UIUtils;

public class MainActivity extends ActionBarActivity
{

	private ActionBar				mActionBar;

	// 抽屉控件
	private DrawerLayout			mDrawerLayout;
	// 抽屉开关
	private ActionBarDrawerToggle	mDrawerToggel;
	// 展示内容的viewpager
	private ViewPager				mViewPager;
	// 顶部的tab
	private PagerSlidingTabStrip	mTab;

	private String[]				mList;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout_home);

		mViewPager = (ViewPager) findViewById(R.id.main_viewpager);

		mTab = (PagerSlidingTabStrip) findViewById(R.id.main_tabstrip);

		// 初始化ActionBar
		initActionBar();

		// 初始化数据
		initData();
	}

	private void initData()
	{
		mList = UIUtils.getStringArray(R.array.pagers);

		mViewPager.setAdapter(new MainAdapter(getSupportFragmentManager()));

		// 为tab设置viewpager
		mTab.setViewPager(mViewPager);
		//通过修改码源添加的重载方法，用于设定颜色
		mTab.setTextColor(UIUtils.getColor(false), UIUtils.getColor(true));
		
		
	}
	//viewpager的适配器
	//这里使用fragmentpagerAdapter--它适用于较少的页面展示，其页面在加载后都不会销毁一直存在于内在中，也会保存浏览信息
	//还有FragmentStatePagerAdapter--它也可以用于展示，不过它在会将当前不需要展示的页面从内在中存储到硬盘上，可以用于展示较多的页面
	private class MainAdapter extends FragmentPagerAdapter
	{

		public MainAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position)
		{

			return FragmentFactory.getFrament(position);
		}

		@Override
		public int getCount()
		{
			if (mList != null) { return mList.length; }
			return 0;
		}

		@Override
		public CharSequence getPageTitle(int position)
		{
			if (mList != null) { return mList[position]; }
			return super.getPageTitle(position);
		}

	}
	//初始化actionBar
	private void initActionBar()
	{
		mActionBar = getSupportActionBar();

		// 设置标题
		mActionBar.setTitle("应用商店");
		// 设置显示标题
		mActionBar.setDisplayShowTitleEnabled(true);
		// 设置显示Home按钮
		mActionBar.setDisplayHomeAsUpEnabled(true);

		// 创建抽屉开关
		mDrawerToggel = new ActionBarDrawerToggle(this, mDrawerLayout,
													R.drawable.ic_drawer_am,
													R.string.open,
													R.string.close);
		// 让开关和抽屉同步
		mDrawerToggel.syncState();

		// 设置抽屉的监听,查看源码，mDrawerToggel是实现了此监听器的
		// 实现开关打开抽屉时的缩进动画
		mDrawerLayout.setDrawerListener(mDrawerToggel);

	}

	// ActionBar中控件被点击的回调方法
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{

		switch (item.getItemId())
		{
		// 此ID是安卓中固定的ID
			case android.R.id.home:
				// 判断开关的状态
				boolean selected = mDrawerToggel.onOptionsItemSelected(item);
				if (selected) { return true; }

				break;

			default:
				break;
		}
		return super.onOptionsItemSelected(item);
	}

}
