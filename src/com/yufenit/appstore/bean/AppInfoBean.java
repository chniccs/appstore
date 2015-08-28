package com.yufenit.appstore.bean;

import java.util.List;


/**
 * @项目名: 	AppStore
 * @包名:	com.yufenit.appstore.bean
 * @类名:	AppInfoBean
 * @创建者:	chniccs
 * @创建时间:	2015-8-23	下午10:17:06 
 * @描述:	应用信息的bean
 * 
 * @svn版本:	$Rev$
 * @更新人:	$Author$
 * @更新时间:	$Date$
 * @更新描述:	
 */
public class AppInfoBean
{
	public String des;
	public String downloadUrl;	
	public String iconUrl;
	public long id;	
	public String name;
	public String packageName;
	public long size;	
	public float stars;	
	
	public String author;
	public String date;
	public String downloadNum;	
	public List<AppInfoSafeBean> safe;	
	public List<String> screen;	
	public String version;	
	
	public class AppInfoSafeBean{
		public String safeDes;	
		public int  safeDesColor;	
		public String safeDesUrl;	
		public String safeUrl;	
	}
}
