package com.yufenit.appstore.protocal;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yufenit.appstore.base.BaseProtocal;
import com.yufenit.appstore.bean.AppInfoBean;
import com.yufenit.appstore.bean.HomeBean;


/**
 * @项目名: 	AppStore
 * @包名:	com.yufenit.appstore.protocal
 * @类名:	Protocal
 * @创建者:	chniccs
 * @创建时间:	2015-8-25	下午2:17:00 
 * @描述:	TODO
 * 
 * @svn版本:	$Rev$
 * @更新人:	$Author$
 * @更新时间:	$Date$
 * @更新描述:	网络访问的类
 */
public class AppProtocal extends BaseProtocal<List<AppInfoBean>>
{
	/**
	 * 加载数据
	 * @param index
	 * @return
	 * @throws Exception
	 */


	@Override
	protected String getInterfacePath()
	{
		return "app";
	}

	@Override
	protected List<AppInfoBean> parseJson(String json)
	{
		// 解析JSON
		Gson gson = new Gson();
		Type type = new TypeToken<List<AppInfoBean>>() {
		}.getType();

		return gson.fromJson(json, type);
	}
}
