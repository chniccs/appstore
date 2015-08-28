package com.yufenit.appstore.protocal;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yufenit.appstore.base.BaseProtocal;
import com.yufenit.appstore.bean.AppInfoBean;
import com.yufenit.appstore.bean.CategaryBean;
import com.yufenit.appstore.bean.CategaryBean.RowBean;
import com.yufenit.appstore.bean.ItemCategaryBean;

/**
 * @项目名: AppStore
 * @包名: com.yufenit.appstore.protocal
 * @类名: Protocal
 * @创建者: chniccs
 * @创建时间: 2015-8-25 下午2:17:00
 * @描述: TODO
 * 
 * @svn版本: $Rev$
 * @更新人: $Author$
 * @更新时间: $Date$
 * @更新描述: 网络访问的类
 */
public class CategoryProtocal extends BaseProtocal<List<ItemCategaryBean>>
{
	private ItemCategaryBean	mBean;

	/**
	 * 加载数据
	 * 
	 * @param index
	 * @return
	 * @throws Exception
	 */

	@Override
	protected String getInterfacePath()
	{
		return "category";
	}

	@Override
	protected List<ItemCategaryBean> parseJson(String json)
	{

		// TODO 转换数据格式
		// 解析JSON
//		Gson gson = new Gson();

		List<ItemCategaryBean> mList = new ArrayList<ItemCategaryBean>();

		Gson gson = new Gson();
		Type type = new TypeToken<List<CategaryBean>>() {
		}.getType();

		List<CategaryBean> list = gson.fromJson(json,  type);

		for (int i = 0; i < list.size(); i++)
		{
			CategaryBean categaryBean = list.get(i);

			mBean = new ItemCategaryBean();

			mBean.title = categaryBean.title;
			
			mBean.type = ItemCategaryBean.TITLE;
			// 添加标题的item到集合中
			mList.add(mBean);
		
			List<RowBean> rList = categaryBean.infos;
			for (int j = 0; j < rList.size(); j++)
			{
				RowBean rowBean = rList.get(j);

				mBean = new ItemCategaryBean();
				mBean.name1 = rowBean.name1;
//				Log.d("categaryProtocal", "name:"+mBean.name1);
				mBean.name2 = rowBean.name2;
				mBean.name3 = rowBean.name3;

				mBean.url1 = rowBean.url1;
				mBean.url2 = rowBean.url2;
				mBean.url3 = rowBean.url3;

				mBean.type = ItemCategaryBean.CATEGARY;
				mList.add(mBean);

			}

		}

		return mList;
	}
}
