package com.yufenit.appstore.utils;

import java.util.List;

/**
 * @项目名: AppStore
 * @包名: com.yufenit.appstore.utils
 * @类名: Constants
 * @创建者: chniccs
 * @创建时间: 2015-8-23 下午10:10:07
 * @描述: TODO
 * 
 * @svn版本: $Rev$
 * @更新人: $Author$
 * @更新时间: $Date$
 * @更新描述: TODO
 */
public class Constants
{
	public static final long	TIME_OUT		= 30 * 1000;

	public static final int		PAGE_SIZE		= 20;

	public static final String		APPICON_BASEURL	= "appicon_baseurl";
	
//	public static final String		BASE_SERVER		= "http://192.168.191.1:8080/GooglePlayServer/";
//	public static final String		BASE_SERVER		= "http://192.168.1.103:8080/GooglePlayServer/";
	public static final String		BASE_SERVER		= "http://10.0.2.2:8080/GooglePlayServer/";
	public static final String	BASE_IMAGE		= BASE_SERVER + "image?name=";							;

//	public static final String		BASE_URL		= "http://192.168.191.1:8080/GooglePlayServer/home";
//	public static final String		BASE_URL		= "http://192.168.1.103:8080/GooglePlayServer/home";
	public static final String		BASE_URL		= "http://10.0.2.2:8080/GooglePlayServer/home";

//	public static final String		BASE_ICON_URL	= "http://192.168.191.1:8080/GooglePlayServer/image?name=";
//	public static final String		BASE_ICON_URL	= "http://192.168.1.103:8080/GooglePlayServer/image?name=";
	public static final String		BASE_ICON_URL	= "http://10.0.2.2:8080/GooglePlayServer/image?name=";
}
