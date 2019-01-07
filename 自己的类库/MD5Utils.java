package md5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {

	public static void main(String args[]){
		String password="123456";
		StringBuilder sb = new StringBuilder();
		//银行  6位数字，10-30次MD5
		try {
			//数据摘要器
			//参数：加密方式
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			//将一个数组加密，返回一个加密过的数组，第一次加密，做准备
			//参数：要加密的数组
			byte[] digest = messageDigest.digest(password.getBytes());
			for (int i = 0; i < digest.length; i++) {
				//byte : -128-127
				int result = digest[i] & 0xff;
				//将int类型的值转化成十六进制的字符串
//				String hexString = Integer.toHexString(result)+1;//不规则加密，加盐
				String hexString = Integer.toHexString(result);
				if (hexString.length() < 2) {
//					System.out.print("0");
					sb.append("0");
				}
//				System.out.println(hexString);
				sb.append(hexString);
			}
			System.out.println(sb.toString());
		} catch (NoSuchAlgorithmException e) {
			//找不到加密方式的异常
			e.printStackTrace();
		}
	}
}
