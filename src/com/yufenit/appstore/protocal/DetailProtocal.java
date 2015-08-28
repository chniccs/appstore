package com.yufenit.appstore.protocal;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * @描述:	详情页面的网络访问协议类
 * 
 * @svn版本:	$Rev$
 * @更新人:	$Author$
 * @更新时间:	$Date$
 * @更新描述:	网络访问的类
 */
public class DetailProtocal extends BaseProtocal<AppInfoBean>
{
	/**
	 * 加载数据
	 * @param index
	 * @return
	 * @throws Exception
	 */
	
	private String mPackageName;

	public DetailProtocal(String PackageName){
		this.mPackageName=PackageName;
	}

	@Override
	protected String getInterfacePath()
	{
		return "detail";
	}

	@Override
	protected AppInfoBean parseJson(String json)
	{
		// 解析JSON
		Gson gson = new Gson();

		return gson.fromJson(json, AppInfoBean.class);
	}
	//重写此方法，将访问参数传给父类
	@Override
	protected Map<String, String> getParamters()
	{
		Map<String,String> map=new HashMap<String,String>();
		
		map.put("packageName", mPackageName);
//		System.out.println("protocal中包名"+mPackageName);
		return map;
	}
}
