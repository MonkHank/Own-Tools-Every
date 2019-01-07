package com.chinaautoid.minehandsetmanagersystem.util;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.chinaautoid.minehandsetmanagersystem.bean.GPS;
import com.chinaautoid.minehandsetmanagersystem.bean.Operator;

public class JsonUtil {
	/**
	 * �������������صĲ���Ա��Ϣ����ȡ����
	 */
	public static ArrayList<String> parseJasonUserName(String json){
		ArrayList<String> userName =new ArrayList<String>(); 
		try {
			JSONObject obj = new JSONObject(json);
			JSONObject reurndata = obj.getJSONObject("reurndata");
			JSONObject tables0 = reurndata.getJSONObject("tables0");
			JSONArray rows = tables0.getJSONArray("rows");
			
			for (int i = 0; i < rows.length(); i++) {
				String string = rows.getJSONObject(i).getString("F_MC"); // ����Ա����
				userName.add(string);
			}
			return userName;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * �Է��������صĲ���Ա��Ϣװ����
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
				String number = rows.getJSONObject(i).getString("F_BH");   // ����Ա���
				String name = rows.getJSONObject(i).getString("F_MC");   // ����Ա����
				String password = rows.getJSONObject(i).getString("F_PASS"); // ����Ա����
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
	 * �������������ص� GPS ȫ����Ϣ
	 */
	public static ArrayList<GPS> parseJasonGPS(String json){
		ArrayList<GPS> GPS =new ArrayList<GPS>(); 
		
		try {
			JSONObject obj = new JSONObject(json);
			JSONObject reurndata = obj.getJSONObject("reurndata");
			JSONObject tables0 = reurndata.getJSONObject("tables0");
			JSONArray rows = tables0.getJSONArray("rows");
			for (int i = 0; i < rows.length(); i++) {
				GPS gps = new GPS(); // ���������ֻ��һ�����󣬷��������� i ������һ��С��������ʱ�䣻
				String locationNum = rows.getJSONObject(i).getString("F_BH"); // վ����
				String locationName = rows.getJSONObject(i).getString("F_MC"); // վ������
				String locationCoord = rows.getJSONObject(i).getString("F_ZB"); // ����ֵ
				String locationOffset = rows.getJSONObject(i).getString("F_PYL"); // ƫ����
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
