package com.yufenit.appstore.bean;

import java.util.List;


/**
 * @项目名: 	AppStore
 * @包名:	com.yufenit.appstore.bean
 * @类名:	CategaryBean
 * @创建者:	chniccs
 * @创建时间:	2015-8-26	下午10:16:30 
 * @描述:	分类的bean
 * 
 * @svn版本:	$Rev$
 * @更新人:	$Author$
 * @更新时间:	$Date$
 * @更新描述:	TODO
 */
public class CategaryBean
{
	public List<RowBean> infos;
	public String title;
	
	public class RowBean{
		
//		name1	休闲
//		name2	棋牌
//		name3	益智
//		url1	image/category_game_0.jpg
//		url2	image/category_game_1.jpg
//		url3	image/category_game_2.jpg
		
		public String name1;
		public String name2;
		public String name3;
		
		public String url1;
		public String url2;
		public String url3;
		
		
	}
}
