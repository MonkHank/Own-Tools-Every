
	protected Dialog alertDialog;
	private void showDialogCheck() {
		AlertDialog.Builder build = new Builder(MainActivity.this);
		build.setCancelable(false);
		View view = View.inflate(MainActivity.this, R.layout.dialog_userswitch, null);
		TextView tv_dialog_title = (TextView) view.findViewById(R.id.tv_dialog_title);
		TextView tv_dialog_message = (TextView) view.findViewById(R.id.tv_dialog_message);
		Button btn_ok = (Button) view.findViewById(R.id.btn_ok);
		Button btn_cancel = (Button) view.findViewById(R.id.btn_cancel);
		
		tv_dialog_title.setText("����");
		tv_dialog_message.setText("ScanContainer���hnrk0014���ڳ�NHTP0011���鳵��");
		btn_ok.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				alertDialog.dismiss();
			}
		});
		btn_cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				alertDialog.dismiss();
			}
		});
		build.setView(view);
		alertDialog = build.create();
		alertDialog.show();
    }

