package com.yufenit.appstore.holder;

import android.text.format.Formatter;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yufenit.appstore.R;
import com.yufenit.appstore.base.BaseHolder;
import com.yufenit.appstore.bean.AppInfoBean;
import com.yufenit.appstore.bean.ItemCategaryBean;
import com.yufenit.appstore.utils.Constants;
import com.yufenit.appstore.utils.UIUtils;

/**
 * @项目名: AppStore
 * @包名: com.yufenit.appstore.holder
 * @类名: HomeHolder
 * @创建者: chniccs
 * @创建时间: 2015-8-23 下午10:04:57
 * @描述: 游戏页面的hodler
 * 
 * @svn版本: $Rev$
 * @更新人: $Author$
 * @更新时间: $Date$
 * @更新描述:
 */
public class CategoryHolder extends BaseHolder<ItemCategaryBean>
{

	@ViewInject(R.id.categary_title)
	private TextView		mTitle;

	@ViewInject(R.id.categary_parts)
	private LinearLayout	mParts;

	@ViewInject(R.id.categary_part1)
	private LinearLayout	mPart1;

	@ViewInject(R.id.categary_part2)
	private LinearLayout	mPart2;

	@ViewInject(R.id.categary_part3)
	private LinearLayout	mPart3;

	@ViewInject(R.id.categary_part1_image1)
	private ImageView		mImage1;

	@ViewInject(R.id.categary_part1_name1)
	private TextView		mName1;

	@ViewInject(R.id.categary_part2_image2)
	private ImageView		mImage2;

	@ViewInject(R.id.categary_part2_name2)
	private TextView		mName2;

	@ViewInject(R.id.categary_part3_image3)
	private ImageView		mImage3;

	@ViewInject(R.id.categary_part3_name3)
	private TextView		mName3;

	private int				mType;

	private BitmapUtils	mBitmapUtils;

	public CategoryHolder(int type) {
		this.mType = type;
	}

	@Override
	protected View initView()
	{
		View view = View.inflate(UIUtils.getContext(), R.layout.item_category, null);
		ViewUtils.inject(this, view);
		
		 mBitmapUtils = new BitmapUtils(UIUtils.getContext());

		return view;
	}

	
	@Override
	protected void Refresh(ItemCategaryBean data)
	{
		
		if (data.type == ItemCategaryBean.TITLE)
		{

			mTitle.setVisibility(View.VISIBLE);

			mParts.setVisibility(View.GONE);

			mTitle.setText(data.title);

		}
		else
		{
			mTitle.setVisibility(View.GONE);

			mParts.setVisibility(View.VISIBLE);
			
			mPart1.setVisibility(setName(mName1, data.name1)?View.VISIBLE:View.INVISIBLE);
			mPart2.setVisibility(setName(mName2, data.name2)?View.VISIBLE:View.INVISIBLE);
			mPart3.setVisibility(setName(mName3, data.name3)?View.VISIBLE:View.INVISIBLE);
			
			setIcon(mImage1, data.url1);
			setIcon(mImage2, data.url2);
			setIcon(mImage3, data.url3);
		

		}
	}
	
	public boolean setName(TextView tv,String name){
		if(!name.isEmpty()){
			tv.setText(name);
			return true;
		}
		return false;
	}
	public void setIcon(ImageView iv,String url){
		if(!url.isEmpty()){
			
			mBitmapUtils.display(iv,Constants.BASE_IMAGE+url);
		}
	}

}