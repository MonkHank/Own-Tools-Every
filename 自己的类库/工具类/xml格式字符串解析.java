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
	 * �ӷ��������ص� xml Ҫ�����ַ�����ʽ��ʾ���������ַ�ʽ
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
					if (tag.equalsIgnoreCase("�ʼ�����") && map != null) {
						String MED_CODE = xmlParser.nextText();
						map.put("�ʼ�����", MED_CODE);
					}
					if (tag.equalsIgnoreCase("��������") && map != null) {
						String MAIL_NUM = xmlParser.nextText();
						map.put("��������", MAIL_NUM);
					}
					if (tag.equalsIgnoreCase("����") && map != null) {
						String RCV_NAME = xmlParser.nextText();
						map.put("����", RCV_NAME);
					}
					if (tag.equalsIgnoreCase("�ռ���") && map != null) {
						String RCV_PHONE = xmlParser.nextText();
						map.put("�ռ���", RCV_PHONE);
					}
					if (tag.equalsIgnoreCase("�绰") && map != null) {
						String RCV_ADDR = xmlParser.nextText();
						map.put("�绰", RCV_ADDR);
					}
					if (tag.equalsIgnoreCase("�����ص�") && map != null) {
						String CASE_CNT = xmlParser.nextText();
						map.put("�����ص�", CASE_CNT);
					}
					if (tag.equalsIgnoreCase("����") && map != null) {
						String CASE_NUM = xmlParser.nextText();
						map.put("����", CASE_NUM);
					}
					if (tag.equalsIgnoreCase("���") && map != null) {
						String ORG_NAME = xmlParser.nextText();
						map.put("���", ORG_NAME);
					}
					if (tag.equalsIgnoreCase("�ܰ���") && map != null) {
						String LAB_ID = xmlParser.nextText();
						map.put("�ܰ���", LAB_ID);
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
					if (tag.equalsIgnoreCase("������") && map != null)
						map.put("������", xmlParser.nextText());
					if (tag.equalsIgnoreCase("�Ѵ�ӡ") && map != null)
						map.put("�Ѵ�ӡ", xmlParser.nextText());
					if (tag.equalsIgnoreCase("�ܰ���") && map != null)
						map.put("�ܰ���", xmlParser.nextText());
					if (tag.equalsIgnoreCase("ҩ������") && map != null)
						map.put("ҩ������", xmlParser.nextText());
					if (tag.equalsIgnoreCase("�Ƶ�����") && map != null)
						map.put("�Ƶ�����", xmlParser.nextText());
					if (tag.equalsIgnoreCase("�������") && map != null)
						map.put("�������", xmlParser.nextText());
					if (tag.equalsIgnoreCase("�ᵥ���") && map != null)
						map.put("�ᵥ���", xmlParser.nextText());
					if (tag.equalsIgnoreCase("���ű��") && map != null)
						map.put("���ű��", xmlParser.nextText());
					if (tag.equalsIgnoreCase("��������") && map != null)
						map.put("��������", xmlParser.nextText());
					if (tag.equalsIgnoreCase("�ͻ����") && map != null)
						map.put("�ͻ����", xmlParser.nextText());
					if (tag.equalsIgnoreCase("�ͻ�����") && map != null)
						map.put("�ͻ�����", xmlParser.nextText());
					if (tag.equalsIgnoreCase("����") && map != null)
						map.put("����", xmlParser.nextText());
					if (tag.equalsIgnoreCase("������λ") && map != null)
						map.put("������λ", xmlParser.nextText());
					if (tag.equalsIgnoreCase("��������") && map != null)
						map.put("��������", xmlParser.nextText());
					if (tag.equalsIgnoreCase("����������λ") && map != null)
						map.put("����������λ", xmlParser.nextText());
					if (tag.equalsIgnoreCase("��˰����") && map != null)
						map.put("��˰����", xmlParser.nextText());
					if (tag.equalsIgnoreCase("��˰���") && map != null)
						map.put("��˰���", xmlParser.nextText());
					if (tag.equalsIgnoreCase("���κ�") && map != null)
						map.put("���κ�", xmlParser.nextText());
					if (tag.equalsIgnoreCase("���䷽ʽ") && map != null)
						map.put("���䷽ʽ", xmlParser.nextText());
					if (tag.equalsIgnoreCase("Ʒ�����") && map != null)
						map.put("Ʒ�����", xmlParser.nextText());
					if (tag.equalsIgnoreCase("����") && map != null)
						map.put("����", xmlParser.nextText());
					if (tag.equalsIgnoreCase("�����ص�") && map != null)
						map.put("�����ص�", xmlParser.nextText());
					if (tag.equalsIgnoreCase("�ջ���") && map != null)
						map.put("�ջ���", xmlParser.nextText());
					if (tag.equalsIgnoreCase("�����ʱ�") && map != null)
						map.put("�����ʱ�", xmlParser.nextText());
					if (tag.equalsIgnoreCase("�ջ��˵绰") && map != null)
						map.put("�ջ��˵绰", xmlParser.nextText());
					if (tag.equalsIgnoreCase("���ҩƷ") && map != null)
						map.put("���ҩƷ", xmlParser.nextText());
					if (tag.equalsIgnoreCase("��������") && map != null)
						map.put("��������", xmlParser.nextText());

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
