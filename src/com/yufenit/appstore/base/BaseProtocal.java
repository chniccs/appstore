package com.yufenit.appstore.base;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseStream;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yufenit.appstore.bean.HomeBean;
import com.yufenit.appstore.protocal.HomeProtocal;
import com.yufenit.appstore.utils.Constants;
import com.yufenit.appstore.utils.FileUtils;
import com.yufenit.appstore.utils.IOUtils;
import com.yufenit.appstore.utils.LogUtils;
import com.yufenit.appstore.utils.UIUtils;

/**
 * @param <T>
 * @项目名: AppStore
 * @包名: com.yufenit.appstore.protocal
 * @类名: ProtocalManger
 * @创建者: chniccs
 * @创建时间: 2015-8-25 下午2:20:46
 * @描述: 网络连接的基类
 * 
 * @svn版本: $Rev$
 * @更新人: $Author$
 * @更新时间: $Date$
 * @更新描述: 
 */
public abstract class BaseProtocal<T>
{

	public T loadData(int index) throws Exception
	{
		// ---------------------读取缓存-----------------------
		if (getFromLocal(index) != null && getFromLocal(index).length() > 0)
		{
			String json = getFromLocal(index);
			return parseJson(json);
		}
		// ---------------------读取缓存-----------------------
		// 连接网络，此方法是被LoadingUI回调的，本身就处于子线程中，所以不需要开启子线程
		HttpUtils utils = new HttpUtils();

		String url = Constants.BASE_SERVER + getInterfacePath();

		RequestParams params = new RequestParams();
		params.addQueryStringParameter("index", "" + index);

		ResponseStream stream = utils.sendSync(HttpMethod.GET, url, params);

		String json = stream.readString();

		// System.out.println("读取网络");

		writeToLocal(json, index);
		// ---------------------创建缓存-----------------------
		// 让子类去解析json
		return parseJson(json);
	}

	public String getFromLocal(int index) throws Exception
	{

		String dir = FileUtils.getDir("json") + getInterfacePath() + index;
		File file = new File(dir);
//		System.out.println(dir);
		// 读取本地缓存
		if (file.exists())
		{
			// 获得缓存文件目录
			BufferedReader rb = new BufferedReader(new FileReader(file));
			
			// 获得保存时间
			String time = rb.readLine();
			long createTime = Long.valueOf(time);
			
			// 对比当前时间，看是否超时
			if ((System.currentTimeMillis() - createTime) < Constants.TIME_OUT)
			{
				// 不超时
				String json = rb.readLine();
				// 让子类去解析json
				// System.out.println("读取缓存");
				IOUtils.close(rb);
				return json;
			}
		}
		return null;
	}

	public void writeToLocal(String json, int index) throws Exception
	{

//		System.out.println("写入缓存");
		String dir = FileUtils.getDir("json") + getInterfacePath() + index;
		// ---------------------创建缓存-----------------------
		File file = new File(dir);
		if (json != null)
		{
			// 获得缓存文件目录
			BufferedWriter rw = new BufferedWriter(new FileWriter(file));
			// 获得保存时间
			long time = System.currentTimeMillis();
			String createTime = Long.toString(time);
			
			rw.write(createTime);
			rw.newLine();
			
			rw.write(json);
			IOUtils.close(rw);
		}
	}

	/**
	 * 让子类去返回具体的接口
	 * 
	 * @return
	 */
	protected abstract String getInterfacePath();

	/**
	 * 让子类去解析json并返回具体类型
	 * 
	 * @param json
	 * @return
	 */
	protected abstract T parseJson(String json);
}
