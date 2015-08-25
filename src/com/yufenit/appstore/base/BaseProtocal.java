package com.yufenit.appstore.base;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseStream;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yufenit.appstore.bean.HomeBean;
import com.yufenit.appstore.protocal.HomeProtocal;
import com.yufenit.appstore.utils.Constants;


/**
 * @param <T>
 * @项目名: 	AppStore
 * @包名:	com.yufenit.appstore.protocal
 * @类名:	ProtocalManger
 * @创建者:	chniccs
 * @创建时间:	2015-8-25	下午2:20:46 
 * @描述:	TODO
 * 
 * @svn版本:	$Rev$
 * @更新人:	$Author$
 * @更新时间:	$Date$
 * @更新描述:	TODO
 */
public abstract class BaseProtocal<T>
{

	
	public T loadData(int index) throws Exception{
		// 连接网络，此方法是被LoadingUI回调的，本身就处于子线程中，所以不需要开启子线程
		HttpUtils utils = new HttpUtils();

		String url = Constants.BASE_SERVER+getInterfacePath();

		RequestParams params = new RequestParams();
		params.addQueryStringParameter("index", "" + index);

		ResponseStream stream = utils.sendSync(HttpMethod.GET, url, params);

		String json = stream.readString();

		//让子类去解析json
		return parseJson(json);
	}
	/**
	 * 让子类去返回具体的接口
	 * @return
	 */
	protected abstract String getInterfacePath();
	
	/**
	 * 让子类去解析json并返回具体类型
	 * @param json
	 * @return
	 */
	protected abstract T parseJson(String json);
}
