package com.itheima.mobiesafe64.utils;

import android.annotation.SuppressLint;
import android.os.Handler;
/**
 * ģ�����ģʽ
 * @author Administrator
 *
 */
@SuppressLint("HandlerLeak")
public abstract class MyAsyncTask {
	private  Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			postTask();
		}
	};
	/**
	 * �����߳�֮ǰִ��
	 */
	public abstract void preTask();
	/**
	 * �����߳�֮��ִ��
	 */
	public abstract void doInBack();
	/**
	 * �����߳�֮��ִ��
	 */
	public abstract void postTask();
	/**
	 * ִ��
	 */
	public void excuted() {
		preTask();
		new Thread() {
			public void run() {
				doInBack();
				handler.sendEmptyMessage(0);
			}
		}.start();
	}
}
