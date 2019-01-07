/**
	 * 保存图片到 sdcard 下 的 minehandsetmanagersystem 文件夹中，并且压缩到 17kb 左右
	 * @param photoBitmap 原始的图片
	 */
	public void savePhotoToSDCard(String path,String photoName,Bitmap photoBitmap){
		File dir=new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		File photoFile=new File(path, photoName); //在创建的文件夹下创建图片文件
		FileOutputStream out=null;
		try {
			out=new FileOutputStream(photoFile);
//			byte[] buffer = new byte[1024]; // 也不知道为什么，谢了这个就显示不出图片来
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