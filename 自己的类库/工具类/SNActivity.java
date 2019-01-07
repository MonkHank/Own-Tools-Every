package com.example.testandroid.activity;

import java.lang.reflect.Method;

import com.example.testandroid.R;
import com.seuic.misc.Misc;

import android.app.Activity;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.Log;

public class SNActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sn);
		
		// �豸���к�(SN��)
		Misc misc = new Misc();
		String sn = misc.getSN();
		
		// �ƶ�ͨ�Ź���ʶ����
		String imei = ((TelephonyManager) this.getSystemService(TELEPHONY_SERVICE)).getDeviceId();
		
		String android_id = Secure.getString(this.getContentResolver(), Secure.ANDROID_ID);
		
		// ���к�
		String serialNumber = getSerialNumber();
		
		Log.e("tag", "sn:"+sn+"\n"+"IMEI:"+imei+"\n"+android_id);
		Log.v("tag", "seriaNum:"+serialNumber);
		
	}
	
	public static String getSerialNumber(){
	    String serial = null;
	    try {
	    Class<?> c = Class.forName("android.os.SystemProperties");
	       Method get = c.getMethod("get", String.class);
	       serial = (String) get.invoke(c, "ro.serialno");
	    } catch (Exception e) {
	       e.printStackTrace();
	    }
	    return serial;
	}
}
