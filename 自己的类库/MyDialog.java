package com.seuic.hansheng.utils;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by Administrator on 2015/3/13.
 */
public class MyDialog {
	
    public static MyDialog mmyDialog;
    
    private Context mContext;
    ProgressDialog dialog;
    
    private MyDialog(){
    }
    

    public static MyDialog getInstance(){
    	if(mmyDialog==null){
    		mmyDialog = new MyDialog();
    	}
    	return mmyDialog;
    }
    
    public void setContext(Context mContext){
        this.mContext = mContext;
    }

    public void show(){
        initDialog();
        dialog.show();
    }
    
    private void  initDialog(){
    	dialog = new ProgressDialog(mContext);
    	dialog.setCancelable(false);
    }

    public boolean isShow(){
        if(dialog!=null){
            return dialog.isShowing();
        }
        return false;
    }


    public void setDisplay(String display){
        dialog.setMessage(display);
    }

    public void dismiss(){
        dialog.dismiss();
    }
}
