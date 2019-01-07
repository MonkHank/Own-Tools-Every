package com.chinaautoid.minehandsetmanagersystem.util;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.chinaautoid.minehandsetmanagersystem.bean.GPS;
import com.chinaautoid.minehandsetmanagersystem.bean.Operator;

public class JsonUtil {
	/**
	 * 解析服务器返回的操作员信息，获取名字
	 */
	public static ArrayList<String> parseJasonUserName(String json){
		ArrayList<String> userName =new ArrayList<String>(); 
		try {
			JSONObject obj = new JSONObject(json);
			JSONObject reurndata = obj.getJSONObject("reurndata");
			JSONObject tables0 = reurndata.getJSONObject("tables0");
			JSONArray rows = tables0.getJSONArray("rows");
			
			for (int i = 0; i < rows.length(); i++) {
				String string = rows.getJSONObject(i).getString("F_MC"); // 操作员名称
				userName.add(string);
			}
			return userName;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 对服务器返回的操作员信息装起来
	 */
	public static ArrayList<Operator> parseJasonOperator(String json){
		ArrayList<Operator> operator = new ArrayList<Operator>();
		try {
			JSONObject obj = new JSONObject(json);
			JSONObject reurndata = obj.getJSONObject("reurndata");
			JSONObject tables0 = reurndata.getJSONObject("tables0");
			JSONArray rows = tables0.getJSONArray("rows");
			
			for (int i = 0; i < rows.length(); i++) {
				Operator op = new Operator();
				String number = rows.getJSONObject(i).getString("F_BH");   // 操作员编号
				String name = rows.getJSONObject(i).getString("F_MC");   // 操作员名称
				String password = rows.getJSONObject(i).getString("F_PASS"); // 操作员密码
				op.setOperatorNumber(number);
				op.setOperatorName(name);
				op.setOperatorPassword(password);
				operator.add(op);
			}
			return operator;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 解析服务器返回的 GPS 全部信息
	 */
	public static ArrayList<GPS> parseJasonGPS(String json){
		ArrayList<GPS> GPS =new ArrayList<GPS>(); 
		
		try {
			JSONObject obj = new JSONObject(json);
			JSONObject reurndata = obj.getJSONObject("reurndata");
			JSONObject tables0 = reurndata.getJSONObject("tables0");
			JSONArray rows = tables0.getJSONArray("rows");
			for (int i = 0; i < rows.length(); i++) {
				GPS gps = new GPS(); // 放在上面就只有一个对象，放在这里有 i 个对象，一个小错误耽误不少时间；
				String locationNum = rows.getJSONObject(i).getString("F_BH"); // 站点编号
				String locationName = rows.getJSONObject(i).getString("F_MC"); // 站点名称
				String locationCoord = rows.getJSONObject(i).getString("F_ZB"); // 坐标值
				String locationOffset = rows.getJSONObject(i).getString("F_PYL"); // 偏移量
				gps.setLocationName(locationName);
				gps.setLocationNum(locationNum);
				gps.setLocationCoord(locationCoord);
				gps.setLocationOffset(locationOffset);
				GPS.add(gps);
			}
			return GPS;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
}
