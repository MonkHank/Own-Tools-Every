
	<uses-permission android:name="android.permission.INTERNET"/>
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>  

	ConnectivityManager connectivityManager = (ConnectivityManager)activity.getSystemService(Context.CONNECTIVITY_SERVICE);  
	NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();  
	if(networkInfo == null || !networkInfo.isAvailable())  {  
		    //��ǰ�޿�������  
			ToastUtil.showToast(activity, "���ȴ�WiFi ");
			Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
			startActivity(intent);
			//ֱ�Ӵ� WiFi ����
//			WifiManager wifiManager = (WifiManager) activity.getSystemService(Context.WIFI_SERVICE);  
//	        wifiManager.setWifiEnabled(true); 
		}  
		else {  
		    //��ǰ�п�������  
		}