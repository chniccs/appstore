package com.yufenit.appstore.view;

import com.yufenit.appstore.R;
import com.yufenit.appstore.utils.UIUtils;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

/**
 * @项目名: 	AppStore
 * @包名:	com.yufenit.appstore.view
 * @类名:	RatinView
 * @创建者:	chniccs
 * @创建时间:	2015-8-25	下午6:52:22 
 * @描述:	TODO
 * 
 * @svn版本:	$Rev$
 * @更新人:	$Author$
 * @更新时间:	$Date$
 * @更新描述:	TODO
 */
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * @描述: 宽高比布局
 * 
 * @svn版本: $Rev: 36 $
 * @更新人: $Author: xq $
 * @更新时间: $Date: 2015-08-24 17:06:41 +0800 (Mon, 24 Aug 2015) $
 * @更新描述: TODO
 */
public class RatioLayout extends FrameLayout
{
	private final static int	RELATIVE_WIDTH	= 0;
	private final static int	RELATIVE_HEIGHT	= 1;

	private float				mRatio;
	private int					mRelative		= RELATIVE_WIDTH;

	public RatioLayout(Context context) {
		this(context, null);
	}

	public RatioLayout(Context context, AttributeSet set) {
		super(context, set);

		TypedArray ta = context.obtainStyledAttributes(set, R.styleable.RatioLayout);
		mRatio = ta.getFloat(R.styleable.RatioLayout_ratio, 0);
		mRelative = ta.getInt(R.styleable.RatioLayout_relative, RELATIVE_WIDTH);
		ta.recycle();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{

		// 获得宽度值
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);

		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		int heightsMode = MeasureSpec.getMode(heightMeasureSpec);
		
		
		// 测量孩子
		// 1.已知宽高比，宽度，计算高度
		if (widthMode == MeasureSpec.EXACTLY && mRatio != 0 && mRelative == RELATIVE_WIDTH)
		{
			
			// 获得孩子的宽度
			int width = widthSize - getPaddingLeft() - getPaddingRight();

			// 通过宽度计算高度
			int height = (int) (width / mRatio + 0.5f);
//			Log.d("ratiolayout:自身高度",width+"" );
			// 期望孩子的宽度和高度
			measureChildren(MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY),
							MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));

			// 设置自己的高度
			setMeasuredDimension(widthSize, height + getPaddingTop() + getPaddingBottom());
		}
		else if (heightsMode == MeasureSpec.EXACTLY && mRatio != 0 && mRelative == RELATIVE_HEIGHT)
		{
			// 2.已知宽高比，高度，计算宽度
			int height = heightSize - getPaddingTop() - getPaddingBottom();

			// 根据高度算宽度
			int width = (int) (height * mRatio + 0.5f);

			// 期望孩子的宽度和高度
			measureChildren(MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY),
							MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));

			// 设置自己的高度
			setMeasuredDimension(width + getPaddingLeft() + getPaddingRight(), heightSize);
			int i=width + getPaddingLeft() + getPaddingRight();
		}
		else
		{
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		}
	}
}
