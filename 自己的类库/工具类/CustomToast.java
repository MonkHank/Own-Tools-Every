package com.example.day04.activity;

import android.widget.TextView;
import android.widget.Toast;

public class CustomToast  {
	/** �����ListViewʱ���Զ��ص��˷��� */
	// l:������Ǹ�listview
	// view:�����listview�е��Ǹ�item view
	private Toast toast ;
	private TextView tv ;
	@Override
	protected void onListItemClick(ListView l,View v,int position, long id) {
		// �����ڴ˷�������ʲô�����Լ���ҵ�����
		String text = (String) l.getItemAtPosition(position);

		// д����ʱ��һ��Ҫ�Լ�����������
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
