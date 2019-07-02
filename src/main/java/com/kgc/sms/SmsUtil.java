package com.kgc.sms;

import java.util.HashMap;
import java.util.Map;

/**  
 * @Title: http://www.smschinese.cn/api.shtml
 * @date 2011-3-22
 * @version V1.2  
 */
public class SmsUtil {
	
	//用户名
	private static String Uid = "xlj1695";
	
	//接口安全秘钥
	private static String Key = "d41d8cd98f00b204e980";
	
	//手机号码，多个号码如13800000000,13800000001,13800000002
	//private static String smsMob = "13387277008";
	
	//短信内容
	//private static String smsText = "睡醒了没?";


	/**
	 * 发送短信的方法
	 * @param smsMob  手机号
	 * @param smsText  发送的文本
	 * @return 表示发送成功的条数 >0 就是成功
	 */
	public static int sendMsm(String smsMob,String smsText) {
		
		HttpClientUtil client = HttpClientUtil.getInstance();
		
		//UTF发送
		int result = client.sendMsgUtf8(Uid, Key, smsText, smsMob);
		return result;
	}
}
