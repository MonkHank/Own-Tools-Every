package com.chinaautoid.minehandsetmanagersystem.util;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;

import android.text.TextUtils;
import android.util.Xml;

public class XmlOperate {
	/**
	 * 从服务器返回的 xml 要是以字符串形式显示，就用这种方式
	 * @param result
	 * @return
	 */
	public static Map<String, String> parserLoginResult(String result) {
		Map<String, String> map = null;
		XmlPullParser xmlParser = Xml.newPullParser();
		
		try {
			StringReader reader = new StringReader(result);
			xmlParser.setInput(reader);
			int evtType = xmlParser.getEventType();
			while (evtType != XmlPullParser.END_DOCUMENT) {
				String tag = xmlParser.getName();
				switch (evtType) {
				case XmlPullParser.START_DOCUMENT:
					break;
					
				case XmlPullParser.START_TAG:
					if (tag.equalsIgnoreCase("LoginDataSet")) {
						map = new HashMap<String, String>();
					}
					if (tag.equalsIgnoreCase("USER_NAME") && map != null) {
						String name = xmlParser.nextText();
						map.put("USER_NAME", name);
					}
					if (tag.equalsIgnoreCase("ORG_ID") && map != null) {
						String id = xmlParser.nextText();
						map.put("ORG_ID", id);
					}
					break;
				}
				evtType = xmlParser.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	public static Map<String, String> parserMedCodeResult(String result) {
		Map<String, String> map = null;
		XmlPullParser xmlParser = Xml.newPullParser();
		try {
			xmlParser.setInput(new StringReader(result));
			int evtType = xmlParser.getEventType();
			while (evtType != XmlPullParser.END_DOCUMENT) {
				String tag = xmlParser.getName();
				switch (evtType) {
				case XmlPullParser.START_DOCUMENT:
					break;
					
				case XmlPullParser.START_TAG:
					if (tag.equalsIgnoreCase("MedLableDataSet")) {
						map = new HashMap<String, String>();
					}
					if (tag.equalsIgnoreCase("MED_CODE") && map != null) {
						String MED_CODE = xmlParser.nextText();
						map.put("MED_CODE", MED_CODE);
					}
					if (tag.equalsIgnoreCase("MAIL_NUM") && map != null) {
						String MAIL_NUM = xmlParser.nextText();
						map.put("MAIL_NUM", MAIL_NUM);
					}
					if (tag.equalsIgnoreCase("RCV_NAME") && map != null) {
						String RCV_NAME = xmlParser.nextText();
						map.put("RCV_NAME", RCV_NAME);
					}
					if (tag.equalsIgnoreCase("RCV_PHONE") && map != null) {
						String RCV_PHONE = xmlParser.nextText();
						map.put("RCV_PHONE", RCV_PHONE);
					}
					if (tag.equalsIgnoreCase("RCV_ADDR") && map != null) {
						String RCV_ADDR = xmlParser.nextText();
						map.put("RCV_ADDR", RCV_ADDR);
					}
					if (tag.equalsIgnoreCase("CASE_CNT") && map != null) {
						String CASE_CNT = xmlParser.nextText();
						map.put("CASE_CNT", CASE_CNT);
					}
					if (tag.equalsIgnoreCase("CASE_NUM") && map != null) {
						String CASE_NUM = xmlParser.nextText();
						map.put("CASE_NUM", CASE_NUM);
					}
					if (tag.equalsIgnoreCase("ORG_NAME") && map != null) {
						String ORG_NAME = xmlParser.nextText();
						map.put("ORG_NAME", ORG_NAME);
					}
					if (tag.equalsIgnoreCase("LAB_ID") && map != null) {
						String LAB_ID = xmlParser.nextText();
						map.put("LAB_ID", LAB_ID);
					}
					if (tag.equalsIgnoreCase("SHT_BAG_ID") && map != null) {
						String SHT_BAG_ID = xmlParser.nextText();
						map.put("SHT_BAG_ID", SHT_BAG_ID);
					}
					if (tag.equalsIgnoreCase("BAG_ID") && map != null) {
						String BAG_ID = xmlParser.nextText();
						map.put("BAG_ID", BAG_ID);
					}
					break;
				}
				evtType = xmlParser.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	public static List<Map<String, String>> parserMailNumResult(String result) {
		Map<String, String> map = null;
		List<Map<String, String>> listMaps = null;
		XmlPullParser xmlParser = Xml.newPullParser();
		try {
			xmlParser.setInput(new StringReader(result));
			int evtType = xmlParser.getEventType();
			while (evtType != XmlPullParser.END_DOCUMENT) {
				String tag = xmlParser.getName();
				switch (evtType) {
				case XmlPullParser.START_DOCUMENT:
					break;
					
				case XmlPullParser.START_TAG:
					if (tag.equalsIgnoreCase("Lable_Info")) {
						listMaps = new ArrayList<Map<String, String>>();
						if (map != null) {
							listMaps.add(map);
							map = null;
						}
					}
					if (tag.equalsIgnoreCase("MailLableDataSet")) {
						if (map == null)
							map = new HashMap<String, String>();
					}
					if (tag.equalsIgnoreCase("MED_CODE") && map != null) {
						String MED_CODE = xmlParser.nextText();
						map.put("MED_CODE", MED_CODE);
					}
					if (tag.equalsIgnoreCase("MAIL_NUM") && map != null) {
						String MAIL_NUM = xmlParser.nextText();
						map.put("MAIL_NUM", MAIL_NUM);
					}
					if (tag.equalsIgnoreCase("RCV_NAME") && map != null) {
						String RCV_NAME = xmlParser.nextText();
						map.put("RCV_NAME", RCV_NAME);
					}
					if (tag.equalsIgnoreCase("RCV_PHONE") && map != null) {
						String RCV_PHONE = xmlParser.nextText();
						map.put("RCV_PHONE", RCV_PHONE);
					}
					if (tag.equalsIgnoreCase("RCV_ADDR") && map != null) {
						String RCV_ADDR = xmlParser.nextText();
						map.put("RCV_ADDR", RCV_ADDR);
					}
					if (tag.equalsIgnoreCase("CASE_CNT") && map != null) {
						String CASE_CNT = xmlParser.nextText();
						map.put("CASE_CNT", CASE_CNT);
					}
					if (tag.equalsIgnoreCase("CASE_NUM") && map != null) {
						String CASE_NUM = xmlParser.nextText();
						map.put("CASE_NUM", CASE_NUM);
					}
					if (tag.equalsIgnoreCase("ORG_NAME") && map != null) {
						String ORG_NAME = xmlParser.nextText();
						map.put("ORG_NAME", ORG_NAME);
					}
					if (tag.equalsIgnoreCase("LAB_ID") && map != null) {
						String LAB_ID = xmlParser.nextText();
						map.put("LAB_ID", LAB_ID);
					}
					if (tag.equalsIgnoreCase("SHT_BAG_ID") && map != null) {
						String SHT_BAG_ID = xmlParser.nextText();
						map.put("SHT_BAG_ID", SHT_BAG_ID);
					}
					if (tag.equalsIgnoreCase("BAG_ID") && map != null) {
						String BAG_ID = xmlParser.nextText();
						map.put("BAG_ID", BAG_ID);
					}
					break;
				}
				evtType = xmlParser.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listMaps;
	}

	public static List<String> parserStringXml(String result) {
		List<String> dataList = null;
		XmlPullParser xmlParser = Xml.newPullParser();
		try {
			xmlParser.setInput(new StringReader(result));
			int evtType = xmlParser.getEventType();
			while (evtType != XmlPullParser.END_DOCUMENT) {
				String tag = xmlParser.getName();
				switch (evtType) {
				case XmlPullParser.START_DOCUMENT:
					break;
					
				case XmlPullParser.START_TAG:
					if (tag.equalsIgnoreCase("Lable_Info")) {
						evtType = xmlParser.next();
						tag = xmlParser.getName();
						dataList = new ArrayList<String>();
						while (evtType != XmlPullParser.END_TAG) {
							tag = xmlParser.getName();
							String str = xmlParser.nextText();
							if (!TextUtils.isEmpty(str.trim())) {
								String line = tag + ":" + str;
								dataList.add(line);
							}
							evtType = xmlParser.next();
						}
					}
					break;
				}
				evtType = xmlParser.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataList;
	}

	public static Map<String, String> parserByMed(String result) {
		Map<String, String> map = null;
		XmlPullParser xmlParser = Xml.newPullParser();
		try {
			xmlParser.setInput(new StringReader(result));
			int evtType = xmlParser.getEventType();
			while (evtType != XmlPullParser.END_DOCUMENT) {
				String tag = xmlParser.getName();
				switch (evtType) {
				case XmlPullParser.START_DOCUMENT:
					break;
					
				case XmlPullParser.START_TAG:
					if (tag.equalsIgnoreCase("MedLableDataSet")) {
						map = new HashMap<String, String>();
					}
					if (tag.equalsIgnoreCase("邮件号码") && map != null) {
						String MED_CODE = xmlParser.nextText();
						map.put("邮件号码", MED_CODE);
					}
					if (tag.equalsIgnoreCase("部门名称") && map != null) {
						String MAIL_NUM = xmlParser.nextText();
						map.put("部门名称", MAIL_NUM);
					}
					if (tag.equalsIgnoreCase("件数") && map != null) {
						String RCV_NAME = xmlParser.nextText();
						map.put("件数", RCV_NAME);
					}
					if (tag.equalsIgnoreCase("收件人") && map != null) {
						String RCV_PHONE = xmlParser.nextText();
						map.put("收件人", RCV_PHONE);
					}
					if (tag.equalsIgnoreCase("电话") && map != null) {
						String RCV_ADDR = xmlParser.nextText();
						map.put("电话", RCV_ADDR);
					}
					if (tag.equalsIgnoreCase("到货地点") && map != null) {
						String CASE_CNT = xmlParser.nextText();
						map.put("到货地点", CASE_CNT);
					}
					if (tag.equalsIgnoreCase("箱数") && map != null) {
						String CASE_NUM = xmlParser.nextText();
						map.put("箱数", CASE_NUM);
					}
					if (tag.equalsIgnoreCase("箱号") && map != null) {
						String ORG_NAME = xmlParser.nextText();
						map.put("箱号", ORG_NAME);
					}
					if (tag.equalsIgnoreCase("总包号") && map != null) {
						String LAB_ID = xmlParser.nextText();
						map.put("总包号", LAB_ID);
					}
					break;
				}
				evtType = xmlParser.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	public static Map<String, String> parserByMail(String result) {
		Map<String, String> map = null;
		XmlPullParser xmlParser = Xml.newPullParser();
		try {
			xmlParser.setInput(new StringReader(result));
			int evtType = xmlParser.getEventType();
			while (evtType != XmlPullParser.END_DOCUMENT) {
				String tag = xmlParser.getName();
				switch (evtType) {
				case XmlPullParser.START_DOCUMENT:
					break;
					
				case XmlPullParser.START_TAG:
					if (tag.equalsIgnoreCase("MedLableDataSet")) {
						map = new HashMap<String, String>();
					}
					if (tag.equalsIgnoreCase("处理结果") && map != null)
						map.put("处理结果", xmlParser.nextText());
					if (tag.equalsIgnoreCase("已打印") && map != null)
						map.put("已打印", xmlParser.nextText());
					if (tag.equalsIgnoreCase("总包数") && map != null)
						map.put("总包数", xmlParser.nextText());
					if (tag.equalsIgnoreCase("药监码数") && map != null)
						map.put("药监码数", xmlParser.nextText());
					if (tag.equalsIgnoreCase("制单日期") && map != null)
						map.put("制单日期", xmlParser.nextText());
					if (tag.equalsIgnoreCase("订单编号") && map != null)
						map.put("订单编号", xmlParser.nextText());
					if (tag.equalsIgnoreCase("提单编号") && map != null)
						map.put("提单编号", xmlParser.nextText());
					if (tag.equalsIgnoreCase("部门编号") && map != null)
						map.put("部门编号", xmlParser.nextText());
					if (tag.equalsIgnoreCase("部门名称") && map != null)
						map.put("部门名称", xmlParser.nextText());
					if (tag.equalsIgnoreCase("客户编号") && map != null)
						map.put("客户编号", xmlParser.nextText());
					if (tag.equalsIgnoreCase("客户名称") && map != null)
						map.put("客户名称", xmlParser.nextText());
					if (tag.equalsIgnoreCase("数量") && map != null)
						map.put("数量", xmlParser.nextText());
					if (tag.equalsIgnoreCase("计量单位") && map != null)
						map.put("计量单位", xmlParser.nextText());
					if (tag.equalsIgnoreCase("辅助数量") && map != null)
						map.put("辅助数量", xmlParser.nextText());
					if (tag.equalsIgnoreCase("辅助计量单位") && map != null)
						map.put("辅助计量单位", xmlParser.nextText());
					if (tag.equalsIgnoreCase("含税单价") && map != null)
						map.put("含税单价", xmlParser.nextText());
					if (tag.equalsIgnoreCase("含税金额") && map != null)
						map.put("含税金额", xmlParser.nextText());
					if (tag.equalsIgnoreCase("批次号") && map != null)
						map.put("批次号", xmlParser.nextText());
					if (tag.equalsIgnoreCase("运输方式") && map != null)
						map.put("运输方式", xmlParser.nextText());
					if (tag.equalsIgnoreCase("品名简称") && map != null)
						map.put("品名简称", xmlParser.nextText());
					if (tag.equalsIgnoreCase("重量") && map != null)
						map.put("重量", xmlParser.nextText());
					if (tag.equalsIgnoreCase("到货地点") && map != null)
						map.put("到货地点", xmlParser.nextText());
					if (tag.equalsIgnoreCase("收货人") && map != null)
						map.put("收货人", xmlParser.nextText());
					if (tag.equalsIgnoreCase("销售邮编") && map != null)
						map.put("销售邮编", xmlParser.nextText());
					if (tag.equalsIgnoreCase("收货人电话") && map != null)
						map.put("收货人电话", xmlParser.nextText());
					if (tag.equalsIgnoreCase("冷藏药品") && map != null)
						map.put("冷藏药品", xmlParser.nextText());
					if (tag.equalsIgnoreCase("导入日期") && map != null)
						map.put("导入日期", xmlParser.nextText());

					break;
				}
				evtType = xmlParser.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

}
