package com.wwbz.common.util;

import com.jfinal.weixin.sdk.utils.HttpUtils;

public class message {
	public static void message(String tel,String content) {
		String str="http://118.190.211.36:7862/sms?";
		String data="action=send&account=901036&password=neC2QJ&mobile="+tel+"&content="+content+"【北京无微不治】&extno=106901036&rt=json";
		System.out.println("result="+HttpUtils.post(str, data));
	}
}
