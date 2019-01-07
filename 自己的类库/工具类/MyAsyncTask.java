package com.itheima.mobiesafe64.utils;

import android.annotation.SuppressLint;
import android.os.Handler;
/**
 * 模板设计模式
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
	 * 在子线程之前执行
	 */
	public abstract void preTask();
	/**
	 * 在子线程之中执行
	 */
	public abstract void doInBack();
	/**
	 * 在子线程之后执行
	 */
	public abstract void postTask();
	/**
	 * 执行
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
