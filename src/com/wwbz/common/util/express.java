package com.wwbz.common.util;

import java.security.MessageDigest;
import java.util.HashMap;

import org.weixin4j.util.MD5;

import com.jfinal.kit.HttpKit;
import com.jfinal.weixin.sdk.utils.HttpUtils;

public class express {
	public static String search(String com,String num) {
		String param ="{\"com\":\""+com+"\",\"num\":\""+num+"\"}";
//		System.err.println("param="+param);
//		System.err.println("param="+"{\"com\":\"shentong\",\"num\":\"3364574314954\"}");
		String customer ="BC91E71432C49E22A9347C1FD4C8FB19";
		String key = "OdeyWOjL2831";
		String sign = toMD5(param+key+customer);
		System.out.println("sign="+sign.toUpperCase());
//		String sign = MD5.encode(param+key+customer);
		HashMap params = new HashMap();
		params.put("param",param);
		params.put("sign",sign.toUpperCase());
		params.put("customer",customer);
		String resp="";
		try {
//			resp = new HttpRequest().postData("http://poll.kuaidi100.com/poll/query.do", params, "utf-8").toString();
			resp= HttpKit.post("http://poll.kuaidi100.com/poll/query.do", params, null);
			System.out.println(resp);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return resp;
	}
	public static String toMD5(String plainText) {
	     try {       
	        MessageDigest md = MessageDigest.getInstance("MD5");     
	        md.update(plainText.getBytes());       
	        byte b[] = md.digest();
	      
	        int i;
	        StringBuffer buf = new StringBuffer("");
	        for (int offset = 0; offset < b.length; offset++) {
	          i = b[offset];
	          if (i < 0)
	            i += 256;
	          if (i < 16)
	            buf.append("0");
	          buf.append(Integer.toHexString(i));
	        }
	        return buf.toString();
	       //  System.out.println("32λ: " + buf.toString());// 32位
	       // System.out.println("16λ: " + buf.toString().substring(8, 24));// 16位
	     } 
	     catch (Exception e) {
	       e.printStackTrace();
	     }
	     return null;

	}
}
