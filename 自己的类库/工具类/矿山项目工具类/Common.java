package com.chinaautoid.minehandsetmanagersystem.util;

import com.chinaautoid.minehandsetmanagersystem.R;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

public class Common {
	static PopupWindow popupWindow;
	public static View popview;
	
	@SuppressLint("InflateParams")
	public static void ShowPopWindow(View paramView, LayoutInflater paramLayoutInflater, String paramString)
	  {
	    popview = paramLayoutInflater.inflate(R.layout.popwindows, null);
	    ((TextView)popview.findViewById(R.id.poptext)).setText(paramString);
	    PopupWindow localPopupWindow = new PopupWindow(popview, 200, 150);
	    popupWindow = localPopupWindow;
	    popupWindow.setOutsideTouchable(false);
	    popupWindow.setFocusable(true);
	    popupWindow.setAnimationStyle(-1);
	    popupWindow.showAtLocation(paramView, 16, 0, 0);
	  }
	
	public static void cLosePopwindow()
	  {
	    popupWindow.dismiss();
	  }

}
