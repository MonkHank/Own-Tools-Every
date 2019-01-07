package com.example.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	private boolean isExit;// false
	@Override
	public void onBackPressed() {
		if (!isExit) {
			Toast.makeText(this, "再点一次则退出", 0).show();
			isExit = true;
			Handler h = new Handler(Looper.getMainLooper());
			// h.sendMessageDelayed(msg, delayMillis)
			h.postDelayed(new Runnable() {
				@Override
				public void run() {
					isExit = false;
				}
			}, 2000);
		} else {
			finish();
		}
	}
}
