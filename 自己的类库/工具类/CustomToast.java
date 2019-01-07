package com.example.day04.activity;

import android.widget.TextView;
import android.widget.Toast;

public class CustomToast  {
	/** 当点击ListView时会自动回调此方法 */
	// l:点击的那个listview
	// view:点击的listview中的那个item view
	private Toast toast ;
	private TextView tv ;
	@Override
	protected void onListItemClick(ListView l,View v,int position, long id) {
		// 将来在此方法中做什么，由自己的业务而定
		String text = (String) l.getItemAtPosition(position);

		// 写程序时，一定要自己摸索，尝试
		if (toast == null) {
			toast = new Toast(this);
			LayoutInflater inflate = (LayoutInflater)MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View view = inflate.inflate(R.layout.toast, null);
			tv = (TextView)view.findViewById(R.id.tv_toast_show);
			toast.setView(view);
			toast.setDuration(0);
		}
		tv.setText(text);
        toast.show();
	}
}
