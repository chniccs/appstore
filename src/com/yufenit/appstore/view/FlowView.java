package com.yufenit.appstore.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;

/**
 * @项目名 FlowView
 * @包名 com.yufenit.flowview
 * @创建时间 2015-8-21 上午10:18:31
 * @author chniccs
 * @描述 随机大小的瀑布流展示控件
 * 
 */

public class FlowView extends ViewGroup
{
	// 保存行的集合
	private List<Line>	mLines			= new ArrayList<Line>();
	// 行的对象
	private Line		mCurrentLine	= null;
	// 每行距离左边的宽度
	private int			mLeft;
	// 每行距离顶部的高度
	private int			mTop;
	// 竖直方向上每行之间的距离
	private int			mVerticalSpace	= 10;
	// 每行剩余的空间
	private int			mExtra;

	public FlowView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public FlowView(Context context) {
		super(context);
	}

	// 为其孩子测量的方法
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{

		// 重复调用此方法的话会使得高度叠加，所以在这里清除之前的信息
		mLines.clear();
		mCurrentLine = null;
		mTop = 0;
		mLeft = 0;

		// 获得父类控件的宽度
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);

		System.out.println(widthSize);

		mLeft += getPaddingLeft();

		mTop += getPaddingTop();

		int width = widthSize - getPaddingLeft() - getPaddingRight();
		int space = 10;

		// 获得每个子控件
		int count = getChildCount();
		// 遍历每个子控件，为其设置宽高
		for (int i = 0; i < count; i++)
		{
			if (mCurrentLine == null)
			{
				mCurrentLine = new Line(width, space);
				mLines.add(mCurrentLine);
			}
			View view = getChildAt(i);
			// 测量子控件本身
			// widthMeasureSpec,heightMeasureSpec是两个32位的值，头两位表示的是模式
			// UNSPECIFIED 不确定的值
			// EXACTLY精确的值
			// AT_MOST最大值
			// 这里我们因为需要设置为固定的宽高，所以需要设置其模式为EXACTILY
			// 又因为其值为32位，而我们的所写的值是10进制的值，这里就需要调用其内部的方法去设置值以及模式

			// view.measure(MeasureSpec.makeMeasureSpec(20,
			// MeasureSpec.EXACTLY),
			// MeasureSpec.makeMeasureSpec(20, MeasureSpec.EXACTLY));

			// 测量孩子，使用父容器的期望值，测量之后才会有子控件的宽高值
			measureChild(view, widthMeasureSpec, heightMeasureSpec);

			if (mCurrentLine.canView(view))
			{
				mCurrentLine.addView(view);
			}
			else
			{
				mCurrentLine = new Line(width, space);
				mCurrentLine.addView(view);
				mLines.add(mCurrentLine);
			}

		}
		int parentHeight = 0;
		// 设置父容器的高度
		for (int i = 0; i < mLines.size(); i++)
		{
			parentHeight += mLines.get(i).mHeight;
			// System.out.println("父容器高度"+parentHeight);
		}

		parentHeight += mLines.size() * mVerticalSpace;
		setMeasuredDimension(widthSize, parentHeight);

		// super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	// 为其孩子布局的方法
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b)
	{

		// 遍历每行
		// 此处通过将控件分成一行行展示，所以这里设置的是行的布局
		for (int i = 0; i < mLines.size(); i++)
		{
			Line line = mLines.get(i);
			line.layout(mLeft, mTop);
			mTop += line.mHeight + mVerticalSpace;
			System.out.println("顶部距离" + mTop);
		}

	}

	// 行的类，用来装view封装到行中显示
	class Line
	{

		int	maxWidth;
		int	mHeight;
		int	currentWidth;	// 当前的宽度
		int	mSpace;

		public Line(int width, int space) {

			this.maxWidth = width;
			this.mSpace = space;
		}

		private List<View>	mViews	= new ArrayList<View>();

		// 为其内view布局 TODO
		public void layout(int left, int top)
		{
			//获得剩余的宽度
			mExtra = maxWidth - currentWidth;
			
			int size = mViews.size();
			
			int avgWidht=(int) (mExtra*1f/size+0.5f);
			
			for (int i = 0; i < size; i++)
			{
				View view = mViews.get(i);
				// 获得当前view的宽高
				int width = view.getMeasuredWidth();
				int height = view.getMeasuredHeight();
				//加上均分的宽度后重新测量
				view.measure(MeasureSpec.makeMeasureSpec(width+avgWidht, MeasureSpec.EXACTLY), height);
				
				//重新获得宽高
				width = view.getMeasuredWidth();
				height = view.getMeasuredHeight();
				// 为其重新设置布局参数
				int l = left;
				int t = top;
				int r = left + width;
				int b = top + height;

				view.layout(l, t, r, b);
				// 左边距累加
				left += width + mSpace;

			}
		}

		// 判断是否还能再view的方法 TODO
		public boolean canView(View view)
		{

			if (mViews.size() == 0)
			{
				// 当前没有view直接添加
				return true;

			}
			else
			{
				// 当前有view，计算其已经使用的宽度加上view间的空间及需要添加进去的view的宽度和总宽度对比，若小于则添加，大于就不添加
				int viewWidth = view.getMeasuredWidth();
				if ((currentWidth + viewWidth + mSpace) > maxWidth)
				{
					// 说明添加不了
					return false;
				}
				else
				{
					return true;
				}

			}

		}

		// 添加view的方法 TODO
		public void addView(View view)
		{
			currentWidth += (view.getMeasuredWidth() + mSpace);
			// int viewHeight = view.getMeasuredHeight();

			int height = view.getMeasuredHeight();
			// 获得最大的高度值
			mHeight = mHeight > height ? mHeight : height;
			mViews.add(view);
		}

	}

}
