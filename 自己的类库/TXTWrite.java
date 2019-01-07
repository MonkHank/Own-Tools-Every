package com.seuic.txtdemo;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;


public class TXTWrite {

	static String dir = Environment.getExternalStorageDirectory().getPath() + "/clientserver/txt";
	
	/**
	 * 第一种写入方式
	 * @param context
	 * @param text ：写入内容
	 */
	public static void userWrite(Context context, String text){
		File f = new File(dir);
		if (!f.exists()) {
			f.mkdirs();
		}
		File file = new File(dir, TXTActivity.FILENAME);
		FileWriter writer = null;
		try {
			writer = new FileWriter(file);
			writer.write(text);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Toast.makeText(context, "写入成功", Toast.LENGTH_SHORT).show();
	}
	
	/**
	 * 第二种写入方式，针对中文会乱码，建议用第一种方式
	 * @param context
	 * @param text ：写入内容
	 */
	public static void userWrite2(Context context, String text){
		File f = new File(dir);
		if (!f.exists()) {
			f.mkdirs();
		}
		File file = new File(dir, TXTActivity.FILENAME2);
		FileOutputStream out = null;
		try {
			 out = new FileOutputStream(file);
			 String str = new String(text);
			 for (int i = 0; i < str.length(); i++) {
				 out.write(str.charAt(i));
			}
			 out.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try{
				if (out != null) {
					out.close();
				}
			}catch(IOException e){
			}
		}
		Toast.makeText(context, "写入成功", Toast.LENGTH_SHORT).show();
	}
	
	/**
	 * 第3中写入方式，对中文有影响，讲义写中文用第一种方式
	 * @param context
	 * @param text ：写入内容
	 */
	public static void userWrite3(Context context, String text){
		File f = new File(dir);
		if (!f.exists()) {
			f.mkdirs();
		}
		File file = new File(dir,TXTActivity.FILENAME3);
		BufferedOutputStream out = null;
		try {
			out = new BufferedOutputStream(new FileOutputStream(file));
			String str = new String(text);
			for (int i = 0; i < str.length(); i++) {
				out.write(str.charAt(i));
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		Toast.makeText(context, "写入成功", Toast.LENGTH_SHORT).show();
	}
}
