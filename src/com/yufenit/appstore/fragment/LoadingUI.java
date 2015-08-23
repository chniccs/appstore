package com.yufenit.appstore.fragment;

import com.yufenit.appstore.R;
import com.yufenit.appstore.utils.UIUtils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

/**
 * @项目名: AppStore
 * @包名: com.yufenit.appstore.fragment
 * @类名: LoadingUI
 * @创建者: chniccs
 * @创建时间: 2015-8-23 下午6:57:43
 * @描述: 控制UI显示的基类
 * 
 * @svn版本: $Rev$
 * @更新人: $Author$
 * @更新时间: $Date$
 * @更新描述: TODO
 */
public abstract class LoadingUI extends FrameLayout
{
	private static final int	STATE_NONE		= 0;	// 没有状态
	private static final int	STATE_LOADING	= 1;
	private static final int	STATE_ERROR		= 2;
	private static final int	STATE_EMPTY		= 3;
	private static final int	STATE_SUCCESS	= 4;

	private View				mLoading;
	private View				mError;
	private View				mEmptry;
	private View				mSuccess;

	private Button				mReLoad;

	private int					mCurrentState;

	public LoadingUI(Context context) {
		this(context, null);
	}

	public LoadingUI(Context context, AttributeSet attrs) {
		super(context, attrs);

		// 首先进行判断，并且将三种状态的view都创建，后面通过对应类的数据状态来显示对应页面
		if (mLoading == null)
		{

			mLoading = View.inflate(UIUtils.getContext(), R.layout.pager_loading, null);
			addView(mLoading);
		}
		if (mError == null)
		{

			mError = View.inflate(UIUtils.getContext(), R.layout.pager_error, null);

			mReLoad = (Button) mError.findViewById(R.id.error_btn_retry);

			mReLoad.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v)
				{
					// 点击重新加载
					loadData();

				}
			});

			addView(mError);
		}
		if (mEmptry == null)
		{

			mEmptry = View.inflate(UIUtils.getContext(), R.layout.pager_empty, null);
			addView(mEmptry);
		}
		// 更新UI
		updateUI();

	}

	private void updateUI()
	{

		// 获得加载数据的状态
		// 加载中的是否显示

		mLoading.setVisibility((mCurrentState == STATE_LOADING || mCurrentState == STATE_NONE) ? View.VISIBLE : View.GONE);

		// 加载为空的是否显示
		mEmptry.setVisibility(mCurrentState == STATE_EMPTY ? View.VISIBLE : View.GONE);

		// 加载失败的是否显示

		mError.setVisibility(mCurrentState == STATE_ERROR ? View.VISIBLE : View.GONE);
		
		// 加载成功时显示数据
		if(mSuccess==null&&mCurrentState==STATE_SUCCESS){
			mSuccess=onLoadSuccessView();
			addView(mSuccess);
		}
		//是否显示加载成功
		if(mSuccess!=null){
			mSuccess.setVisibility(mCurrentState==STATE_SUCCESS?View.VISIBLE:View.GONE);
		}

	}

	// 加载数据的方法

	public void loadData()
	{

		// 判断状态，如果已经加载成功或正在加载中就不加载
		if (mCurrentState == STATE_SUCCESS || mCurrentState == STATE_LOADING) { return; }

		// 否则就更新UI显示
		mCurrentState=STATE_LOADING;
		updateUI();

		// 开启子线程去加载数据
		new Thread(new Runnable() {

			@Override
			public void run()
			{
				// 因为数据是不同的，所以让实现者去处理加载的数据
				ResultState state = onLoadData();

				mCurrentState = state.getState();

				// 加载完成后再更新UI
				// 因为此时是在子线程中，所以需要通过handler去实现在主线程中更新UI的方法
				SafeUpdateUI();

			}

		}).start();

	}

	// 在主线程中更新UI
	private void SafeUpdateUI()
	{
		UIUtils.post(new Runnable() {

			@Override
			public void run()
			{
				updateUI();
			}
		});

	}

	// 创建一个枚举类来控制返回的数值范围
	public enum ResultState
	{

		EMPTRY(STATE_EMPTY),
		ERROR(STATE_ERROR),
		SUCCESS(STATE_SUCCESS);

		private int	state;

		private ResultState(int state) {
			this.state = state;
		}

		public int getState()
		{
			return state;
		}
	}

	/**
	 * 加载数据
	 */
	public abstract ResultState onLoadData();

	/**
	 * 返回成功时显示的view
	 * 
	 * @return
	 */
	public abstract View onLoadSuccessView();

}
