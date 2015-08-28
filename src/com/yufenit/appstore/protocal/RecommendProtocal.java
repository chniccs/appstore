package com.yufenit.appstore.protocal;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yufenit.appstore.base.BaseProtocal;


/**
 * @项目名: 	AppStore
 * @包名:	com.yufenit.appstore.protocal
 * @类名:	RecommendProcotal
 * @创建者:	chniccs
 * @创建时间:	2015-8-27	下午7:08:08 
 * @描述:	推荐页面的网络访问协议类
 * 
 * @svn版本:	$Rev$
 * @更新人:	$Author$
 * @更新时间:	$Date$
 * @更新描述:	
 */
public class RecommendProtocal extends BaseProtocal<List<String>>
{

	@Override
	protected String getInterfacePath()
	{
		return "recommend";
	}

	@Override
	protected List<String> parseJson(String json)
	{
		
		Gson gson=new Gson();
		
		Type type=new TypeToken<List<String>>(){}.getType();
		
		List<String> datas = gson.fromJson(json, type);
		
		return datas;
	}
}
