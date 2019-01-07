
	<uses-permission android:name="android.permission.INTERNET"/>
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>  

	ConnectivityManager connectivityManager = (ConnectivityManager)activity.getSystemService(Context.CONNECTIVITY_SERVICE);  
	NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();  
	if(networkInfo == null || !networkInfo.isAvailable())  {  
		    //当前无可用网络  
			ToastUtil.showToast(activity, "请先打开WiFi ");
			Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
			startActivity(intent);
			//直接打开 WiFi 服务
//			WifiManager wifiManager = (WifiManager) activity.getSystemService(Context.WIFI_SERVICE);  
//	        wifiManager.setWifiEnabled(true); 
		}  
		else {  
		    //当前有可用网络  
		}