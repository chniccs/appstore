package com.yufenit.appstore.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.yufenit.appstore.fragment.LoadingUI;
import com.yufenit.appstore.fragment.LoadingUI.ResultState;
import com.yufenit.appstore.utils.UIUtils;

/**
 * @项目名: AppStore
 * @包名: com.yufenit.appstore.fragment
 * @类名: BaseFragment
 * @创建者: chniccs
 * @创建时间: 2015-8-22 下午6:57:15
 * @描述: Fragment的基类
 * 
 * @svn版本: $Rev$
 * @更新人: $Author$
 * @更新时间: $Date$
 * @更新描述:
 */
public abstract class BaseFragment extends Fragment
{
	private LoadingUI	mLoadingUI;

	// 加载中
	// 加载成功 -->显示数据
	// 加载失败-->数据为空、连接失败

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{

		// 显示页面

		if (mLoadingUI == null)
		{
			mLoadingUI = new LoadingUI(UIUtils.getContext()) {

				@Override
				public ResultState onLoadData()
				{
					//声明一个抽象方法让子类去实现
					return onStartLoadData();
				}

				@Override
				public View onLoadSuccessView()
				{
					//声明一个抽象方法让子类去实现加载数据成功时的view
					return onStartSuccessView();
				}
			};
		}
		else
		{
			// 因为Fragment在ViewPager页面被销毁时走了生命周期方法，但没有在内在中被销毁，当viewpager页面再次创建一个新的显示页面时
			// 此时它并不是之前的Fragment的宿主所以不能以之前的方法将Fragment依附上去，所以这里就获得Fragment之前保存的父控件来清除绑定
			// 就可以重新绑定上了
			// 移除view
			ViewParent parent = mLoadingUI.getParent();

			if (parent instanceof ViewGroup)
			{

				((ViewGroup) parent).removeView(mLoadingUI);
			}

		}

		return mLoadingUI;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		// 加载数据
		
		if(mLoadingUI!=null){
			
			mLoadingUI.loadData();
		}
		super.onActivityCreated(savedInstanceState);
	}

	/**
	 * 让子类去返回加载后的状态
	 * 
	 * @return
	 */
	public abstract ResultState onStartLoadData();
	/**
	 * 让子类去实现返回加载数据成功时view
	 * @return
	 */
	public abstract View onStartSuccessView();
}
