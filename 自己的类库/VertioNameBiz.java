package com.autoidchina.changfei.biz;

import com.autoidchina.changfei.utils.LogUtil;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

public class VertioNameBiz {

	/** 获取当前应用程序的版本号 */
	
	public static String getVersionname(Context context) {
		// 包的管理者，获取应用程序清单文件的信息
		PackageManager pm = context.getPackageManager();
		// packageName:包名 (清单文件中的包名)
		// flags:指定获取信息标签 --GET_ACTIVITIES:额外获取activity信息
		// getPackageName():获取当前应用程序的包名
		try {
			PackageInfo info = pm.getPackageInfo(context.getPackageName(),PackageManager.PERMISSION_GRANTED);
			// 获取版本号
			String versionName = info.versionName;
			LogUtil.e("版本号："+versionName);
			return versionName;
		} catch (NameNotFoundException e) {
			// 不执行
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getPackagename(Context context) {
		// 包的管理者，获取应用程序清单文件的信息
		PackageManager pm = context.getPackageManager();
		// packageName:包名 (清单文件中的包名)
		// flags:指定获取信息标签 --GET_ACTIVITIES:额外获取activity信息
		// getPackageName():获取当前应用程序的包名
		try {
			PackageInfo info = pm.getPackageInfo(context.getPackageName(),PackageManager.PERMISSION_GRANTED);
			// 获取版本号
			 ApplicationInfo applicationInfo = info.applicationInfo;
			 String applicationName =  (String) pm.getApplicationLabel(applicationInfo); 
			LogUtil.e("应用名："+applicationName);
			return applicationName;
		} catch (NameNotFoundException e) {
			// 不执行
			e.printStackTrace();
		}
		return null;
	}
	
	
}
