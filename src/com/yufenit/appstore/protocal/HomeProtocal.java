package com.yufenit.appstore.protocal;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseStream;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yufenit.appstore.base.BaseProtocal;
import com.yufenit.appstore.bean.HomeBean;
import com.yufenit.appstore.utils.Constants;


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
public class HomeProtocal extends BaseProtocal<HomeBean>
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
		return "home";
	}

	@Override
	protected HomeBean parseJson(String json)
	{
		// 解析JSON
		Gson gson = new Gson();
		
		HomeBean bean = gson.fromJson(json, HomeBean.class);
		return bean;
	}
}
