package com.example.day07.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.view.KeyEvent;

public class ProgressDlgUtil {
	private ProgressDlgUtil() {
	}

	private static ProgressDialog mProgressDlg;

	public static void showProgress(String info, Context context) {
		if (info == null)
			info = "please wait a moment";
		if(context == null){
			return ;
		}
		mProgressDlg = ProgressDialog.show(context, null, info, true, true);
		mProgressDlg.setCancelable(false);
		
		mProgressDlg.setOnDismissListener(new DialogInterface.OnDismissListener() {
			@Override
			public void onDismiss(DialogInterface dialog) {
				mProgressDlg = null;
			}
		});
		
		mProgressDlg.setOnCancelListener(new DialogInterface.OnCancelListener() {
			@Override
			public void onCancel(DialogInterface dialog) {
				mProgressDlg = null;
			}
		});
		
		mProgressDlg.setOnKeyListener(new OnKeyListener() {
			@Override
			public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_BACK) {
					mProgressDlg.dismiss();
					mProgressDlg = null;
				}
				return false;
			}
		});
		
	}

	public static void dismissProgress() {
		if (mProgressDlg != null) {
			mProgressDlg.dismiss();
			mProgressDlg = null;
		}
	}
}
