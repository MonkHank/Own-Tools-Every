package com.chinaautoid.minehandsetmanagersystem.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class HttpUtil {
	/**
	 * 发送HttpPost请求 获取数据 
	 */
	public static void loadFlightsByPost() throws IOException {
		String uri = "http://192.168.188.53:8080/android_day02_jsp/loadFlights.jsp";
		HttpPost post = new HttpPost(uri);
		
		post.setHeader("Content-Type", "application/x-www-form-urlencoded"); // 设置Header
		
		List<NameValuePair> list = new ArrayList<NameValuePair>(); // 设置参数
		list.add(new BasicNameValuePair("time", "2015-11-11"));
		HttpEntity reqEntity = new UrlEncodedFormEntity(list, "utf-8");
		post.setEntity(reqEntity);
		
		HttpClient client = new DefaultHttpClient();
		HttpResponse resp = client.execute(post); // 发送请求
		if (resp.getStatusLine().getStatusCode() == 200) {
			HttpEntity entity = resp.getEntity(); // 正常返回
			
			Log.i("info", EntityUtils.toString(entity)); // 把entity转成字符串
			// 使用xml解析的方式 解析xml
			// 获取所有的航班对象
			// 发消息给handler更新ListView
		} else {
			// 异常返回
		}
	}

	/**
	 * 发送HttpGet请求 获取数据 get
	 */
	public static  void httpGet(String uri) throws IOException {
		HttpGet get = new HttpGet(uri);
		HttpClient client = new DefaultHttpClient();
		HttpResponse resp = client.execute(get);
		
		if (resp.getStatusLine().getStatusCode() == 200) { // 解析响应
			HttpEntity entity = resp.getEntity(); // 正常返回
			Log.i("info", EntityUtils.toString(entity)); // 把entity转成字符串
			// 使用xml解析的方式 解析xml
			// 获取所有的航班对象
			// 发消息给handler更新ListView
		} else {
			// 异常返回
		}
	}
	
	/**
	 * Android 调用 WCF
	 */
	public static String callWCF(String uri) {
		HttpGet get = new HttpGet(uri);
		get.setHeader("Accept-Encoding", "grip");
		get.setHeader("Content-type", "txt/xml");
		
		HttpClient client = new DefaultHttpClient();
		HttpResponse response;
		HttpEntity entity = null;
		try {
			response = client.execute(get);
			entity = response.getEntity();
			String entityString = EntityUtils.toString(entity);
			Log.d("tag", "entity:"+entityString); // entity里面的内容
			Log.d("tag", "entity:"+entity.toString()); // entity对象
			
			return entityString;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
