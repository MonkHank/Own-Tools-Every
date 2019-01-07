
	/**
	 * ���գ�����ԭͼƬ���浽����
	 */
	private String format;
	private void takaCamera() {
		bt_bitmap_photo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				File localFile1 = new File(Environment.getExternalStorageDirectory(), "����");
				if (!localFile1.exists()){
					localFile1.mkdirs();
				}
				Date localDate = new Date();
				SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("'IMG'_yyyyMMddHHmmss", Locale.CHINA);
				String str = localSimpleDateFormat.format(localDate);
				format = String.format("%s.jpg", new Object[] { str });
				File localFile2 = new File(localFile1.getPath()+ File.separator+ format);

				Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				Uri imageUri = Uri.fromFile(localFile2);
				openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
				startActivityForResult(openCameraIntent, 0);
			}
		});
	}

	/**
	 * �ѱ��浽������ͼƬ��ʾ��imageview��
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
			String path = Environment.getExternalStorageDirectory().getPath()+"/����";
			File pathName = new File(path, format);
			Bitmap bitmap = BitmapFactory.decodeFile(pathName.getPath());
			if (bitmap != null) {
				iv_bitmap_show.setImageBitmap(bitmap);
			}else {
				iv_bitmap_show.setImageResource(R.drawable.ic_launcher);
			}
	}

