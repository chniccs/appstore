package com.yufenit.appstore.fragment;


import android.support.v4.app.Fragment;
import android.support.v4.util.SparseArrayCompat;


/**
 * @项目名: 	AppStore
 * @包名:	com.yufenit.appstore.utils
 * @类名:	FragmentUtils
 * @创建者:	chniccs
 * @创建时间:	2015-8-22	下午9:36:03 
 * @描述:	Fragment的工具类
 * 
 * @svn版本:	$Rev$
 * @更新人:	$Author$
 * @更新时间:	$Date$
 * @更新描述:	
 */
public class FragmentFactory
{
	
	//缓存Fragment
	//使用的是安卓针对java中hashmap集合做的优化算法的集合--使用稀疏array
	//它内部只能存储int类型的key所以只需要指定value的类型就可以
	private static SparseArrayCompat<Fragment> mCache=new SparseArrayCompat<Fragment>();
	

	public static Fragment getFrament(int position){
		
		//这里通过先判断是否存在于缓存中再执行下一次
		//如果存在缓存中就不需要再重新创建，从缓存中取出数据返回即可，可以优化资源使用，提高执行效率
		Fragment fragment = mCache.get(position);
		if(fragment!=null){
			return fragment;
		}
		
//		Fragment fragment=null;
		
		switch (position)
		{
			case 0:
				fragment=new HomeFragment();
				break;
			case 1:
				fragment=new AppFragment();
				break;
			case 2:
				fragment=new GameFragment();
				break;
			case 3:
				fragment=new SubjectFragment();
				break;
			case 4:
				fragment=new HomeFragment();
				break;
			case 5:
				fragment=new HomeFragment();
				break;
			case 6:
				fragment=new HomeFragment();
				break;

			default:
				break;
		}
		//存入缓存中
		mCache.put(position, fragment);
		
		return fragment;
	}
}
