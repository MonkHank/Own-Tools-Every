package com.example.demo.utils;


import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
	private static Toast toast;
	/**
	 * ǿ�����˾���������������������һ����ʧ
	 * @param text
	 */
	public static void showToast(Context context,String text){
		// ����
		if (toast==null) {
			toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
		}
		toast.setText(text);
		toast.show();
	}
}
