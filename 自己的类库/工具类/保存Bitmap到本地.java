/**
	 * ����ͼƬ�� sdcard �� �� minehandsetmanagersystem �ļ����У�����ѹ���� 17kb ����
	 * @param photoBitmap ԭʼ��ͼƬ
	 */
	public void savePhotoToSDCard(String path,String photoName,Bitmap photoBitmap){
		File dir=new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		File photoFile=new File(path, photoName); //�ڴ������ļ����´���ͼƬ�ļ�
		FileOutputStream out=null;
		try {
			out=new FileOutputStream(photoFile);
//			byte[] buffer = new byte[1024]; // Ҳ��֪��Ϊʲô��л���������ʾ����ͼƬ��
//			out.write(buffer);
			if (photoBitmap != null) {
				if (photoBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)) {
					out.flush();
					out.close();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}