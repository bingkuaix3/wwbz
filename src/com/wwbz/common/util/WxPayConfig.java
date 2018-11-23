package com.wwbz.common.util;

public class WxPayConfig {
	// 小程序appid
	public static final String appid = "wxf99f86834d80402b";
	// 小程序的 app secret
	public static final String appsecret = "d40f1b7e4f8836f8a20b49f532bc16c6";
	// 填写为 authorization_code
	public static final String grant_type = "authorization_code";
	// 微信支付的商户id
	public static final String mch_id = "1507525981";
	// 微信支付的商户密钥
	public static final String key = "wuweibuzhi2202041990070403112018";
	// 支付成功后的服务器回调url
	public static final String notify_url = "https://www.weishengtai.club/wwbz/wx/pay_notify";
	// 签名方式
	public static final String SIGNTYPE = "MD5";
	// 交易类型
	public static final String TRADETYPE = "JSAPI";
	// 微信统一下单接口地址
	public static final String pay_url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
}
