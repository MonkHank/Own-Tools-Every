/**
 * 图片变成byte数组
 */
public byte[] imageGetBytes(String path) {
	String path = Environment.getExternalStorageDirectory()+ "/minehandsetmanagersystem/IMG_20160307_171451.jpg";
	byte[] data = null;
	FileInputStream in = null;
	try {
		in = new FileInputStream(new File(path));
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		int numBytesRead = 0;
		while ((numBytesRead = in.read(buf)) != -1) {
			baos.write(buf, 0, numBytesRead);
		}
		data = baos.toByteArray();
		baos.close();
		in.close();
	} catch (FileNotFoundException ex1) {
		ex1.printStackTrace();
	} catch (IOException ex1) {
		ex1.printStackTrace();
	}
	return data;
}