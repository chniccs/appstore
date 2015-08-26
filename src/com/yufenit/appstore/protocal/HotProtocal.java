package com.yufenit.appstore.protocal;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.yufenit.appstore.base.BaseProtocal;
import com.yufenit.appstore.bean.AppInfoBean;


/**
 * @项目名: 	AppStore
 * @包名:	com.yufenit.appstore.protocal
 * @类名:	Protocal
 * @创建者:	chniccs
 * @创建时间:	2015-8-25	下午2:17:00 
 * @描述:	排行页面的protocal
 * 
 * @svn版本:	$Rev$
 * @更新人:	$Author$
 * @更新时间:	$Date$
 * @更新描述:	网络访问的类
 */
public class HotProtocal extends BaseProtocal<List<String>>
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
		return "hot";
	}

	@Override
	protected List<String> parseJson(String json)
	{

		JsonParser parser=new JsonParser();
		
		//获得根结点元素
		JsonElement element = parser.parse(json);
		
		//观察文档可以发现其为数据类型
		JsonArray jsonArray = element.getAsJsonArray();
		
		//获得下级节点元素
		//因为是数组，所以直接遍历
		List<String> list=new ArrayList<String>();
		for (int i = 0; i < jsonArray.size(); i++)
		{
			//获得节点元素
			JsonElement jsonElement = jsonArray.get(i);
			
			//获得对象类型
			String  name = jsonElement.getAsString();
			
			
			
			
			list.add(name);
		}
		
		return list;
	}
}
