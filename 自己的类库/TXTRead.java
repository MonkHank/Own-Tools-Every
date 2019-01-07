package com.seuic.txtdemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@SuppressLint("SdCardPath")
public class TXTRead {

	static String dir = Environment.getExternalStorageDirectory().getPath() + "/clientserver/txt";

	/**
	 * 第一种读取方式
	 * @param context
	 * @param fileName ：文件名字
	 * @return
	 */
	public static String userRead(Context context, String fileName) {
		File file = new File(dir, fileName);
		if (!file.exists()) {
			Toast.makeText(context, "还没写文件", Toast.LENGTH_SHORT).show();
		} else {
			FileReader reader = null;
			try {
				reader = new FileReader(file);
				BufferedReader reader2 = new BufferedReader(reader);
				String vs = null;
				StringBuilder sb = new StringBuilder();
				while ((vs = reader2.readLine()) != null) {
					sb.append(vs);
				}
				return sb.toString();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * 第二种读取方式
	 * @param context
	 * @return
	 */
	public static String userRead2(Context context, String fileName) {
		File file = new File(dir, fileName);
		FileInputStream in = null;

		if (!file.exists()) {
			Toast.makeText(context, "还没写文件", Toast.LENGTH_SHORT).show();
			return "";
		} 
		try {
			in = new FileInputStream(file);
			StringBuilder sb = new StringBuilder();
			// 单字节读取标准格式
			int b;
			while ((b = in.read()) != -1) {
				char c = (char) b;
				sb.append(c);
			}
			return sb.toString(); // 中文会乱码，建议用第一种方式
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return "";
	}
	
	/**
	 * 第三种读取方式
	 * @return
	 */
	public static String userRead3(Context context,String fileName){
		File file = new File(dir, fileName);
		if (!file.exists()) {
			Toast.makeText(context, "还没写文件", Toast.LENGTH_SHORT).show();
			return "";
		} 
		FileInputStream in = null;
		try {
			in = new FileInputStream(file.getPath());
			// 批量读取标准格式
			byte[] buff = new byte[4];
			int n;
			String s = "";
			while ((n = in.read(buff)) != -1) {
				s = new String(buff,0,n);
				Log.i("tag", "n:"+n);
			}
			return s; // 中文会乱码，建议用第一种方式
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "";
	}
}
