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
 * @描述:	TODO
 * 
 * @svn版本:	$Rev$
 * @更新人:	$Author$
 * @更新时间:	$Date$
 * @更新描述:	网络访问的类
 */
public class GameProtocal extends BaseProtocal<List<AppInfoBean>>
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
		return "game";
	}

	@Override
	protected List<AppInfoBean> parseJson(String json)
	{
		// 解析JSON
//		Gson gson = new Gson();
//		Type type = new TypeToken<List<AppInfoBean>>() {
//		}.getType();
//
//		return gson.fromJson(json, type);
		//获得解析者
		JsonParser parser=new JsonParser();
		
		//获得根结点元素
		JsonElement element = parser.parse(json);
		
		//观察文档可以发现其为数据类型
		JsonArray jsonArray = element.getAsJsonArray();
		
		//获得下级节点元素
		//因为是数组，所以直接遍历
		List<AppInfoBean> list=new ArrayList<AppInfoBean>();
		for (int i = 0; i < jsonArray.size(); i++)
		{
			//获得节点元素
			JsonElement jsonElement = jsonArray.get(i);
			
//			des	捕鱼达人土豪金是捕鱼达人原班团队（fishingjoy）继捕鱼达人2研发的一款精
//			downloadUrl	app/org.cocos2dx.GoldenFishGame/org.cocos2dx.GoldenFishGame.apk
//			iconUrl	app/org.cocos2dx.GoldenFishGame/icon.jpg
//			id	1642739
//			name	捕鱼达人土豪金
//			packageName	org.cocos2dx.GoldenFishGame
//			size	9815944
//			stars	2.5
			//获得对象类型
			JsonObject object = jsonElement.getAsJsonObject();
			
			
			//因为其为普通的元素类型，所以使用primitive
			//--获得des元素--下同
			JsonPrimitive desJson = object.getAsJsonPrimitive("des");
			String des=desJson.getAsString();
			
			JsonPrimitive downloadUrlJson = object.getAsJsonPrimitive("downloadUrl");
			String downloadUrl=downloadUrlJson.getAsString();
			
			JsonPrimitive iconUrlJson = object.getAsJsonPrimitive("iconUrl");
			String iconUrl=iconUrlJson.getAsString();
			
			JsonPrimitive idJson = object.getAsJsonPrimitive("id");
			long id=idJson.getAsLong();
			
			JsonPrimitive nameJson = object.getAsJsonPrimitive("name");
			String name=nameJson.getAsString();
			
			JsonPrimitive packageNameJson = object.getAsJsonPrimitive("packageName");
			String packageName=packageNameJson.getAsString();
			
			JsonPrimitive sizeJson = object.getAsJsonPrimitive("size");
			long size=sizeJson.getAsLong();
			
			JsonPrimitive starsJson = object.getAsJsonPrimitive("stars");
			float stars=starsJson.getAsFloat();
			
			AppInfoBean bean=new AppInfoBean();
			
			bean.des=des;
			bean.downloadUrl=downloadUrl;
			bean.iconUrl=iconUrl;
			bean.id=id;
			bean.name=name;
			bean.packageName=packageName;
			bean.size=size;
			bean.stars=stars;
			
			list.add(bean);
		}
		
		return list;
	}
}
