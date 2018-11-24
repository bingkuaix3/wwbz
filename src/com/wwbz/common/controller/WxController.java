package com.wwbz.common.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.InvalidAlgorithmParameterException;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.catalina.User;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Duang;
import com.jfinal.core.Controller;
import com.jfinal.kit.HttpKit;
import com.jfinal.kit.PropKit;
import com.jfinal.kit.StrKit;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.upload.UploadFile;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.kit.IpKit;
import com.jfinal.weixin.sdk.kit.PaymentKit;
import com.jfinal.weixin.sdk.utils.HttpUtils;
import com.jfinal.weixin.sdk.utils.IOUtils;
import com.jfinal.weixin.sdk.utils.JsonUtils;
import com.jfinal.wxaapp.WxaConfig;
import com.jfinal.wxaapp.WxaConfigKit;
import com.jfinal.wxaapp.api.WxaAccessTokenApi;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import com.wwbz.common.model.AddressWwbz;
import com.wwbz.common.model.AllotWwbz;
import com.wwbz.common.model.ArchivesWwbz;
import com.wwbz.common.model.ArticleWwbz;
import com.wwbz.common.model.ArticlekindWwbz;
import com.wwbz.common.model.BannerWwbz;
import com.wwbz.common.model.BargaingoodsWwbz;
import com.wwbz.common.model.BargainorderWwbz;
import com.wwbz.common.model.BasicWwbz;
import com.wwbz.common.model.BasicWwbzgl;
import com.wwbz.common.model.CartWwbz;
import com.wwbz.common.model.CategoryWwbz;
import com.wwbz.common.model.CommentWwbz;
import com.wwbz.common.model.CooperateWwbz;
import com.wwbz.common.model.CustomerWwbz;
import com.wwbz.common.model.DealerWwbz;
import com.wwbz.common.model.DivideWwbz;
import com.wwbz.common.model.DonateWwbz;
import com.wwbz.common.model.FeedWwbz;
import com.wwbz.common.model.GlucometerWwbz;
import com.wwbz.common.model.GoodsWwbz;
import com.wwbz.common.model.InvitationWwbz;
import com.wwbz.common.model.MoneyWwbz;
import com.wwbz.common.model.OfferWwbz;
import com.wwbz.common.model.OrderWwbz;
import com.wwbz.common.model.QuestionnaireWwbz;
import com.wwbz.common.model.RewardWwbz;
import com.wwbz.common.model.RewardcontactWwbz;
import com.wwbz.common.model.StoreWwbz;
import com.wwbz.common.model.TransactionWwbz;
import com.wwbz.common.model.UserWwbz;
import com.wwbz.common.util.AES;
import com.wwbz.common.util.AjaxResult;
import com.wwbz.common.util.CertHttpUtil;
import com.wwbz.common.util.PayUtil;
import com.wwbz.common.util.StringUtils;
import com.wwbz.common.util.WxPayConfig;
import com.wwbz.common.util.WxaQrcodeApi;
import com.wwbz.common.util.express;
import com.wwbz.common.util.message;
import com.wwbz.common.util.random;

public class WxController extends Controller {
	private AjaxResult ajax = new AjaxResult();
	public void index() {
		System.out.println("index");
		renderJson();
	}
	public void login() {
		String code = getPara("code");
		// 接口地址
		String getOpenIdurl = "https://api.weixin.qq.com/sns/jscode2session";
		Map<String, String> params = new HashMap<String, String>();
		params.put("appid", WxPayConfig.appid);
		params.put("secret", WxPayConfig.appsecret);
		params.put("js_code", code);
		params.put("grant_type", WxPayConfig.grant_type);
		String para = PaymentKit.packageSign(params, false);
		String url = getOpenIdurl + "?" + para;
		ApiResult apiResult = new ApiResult(HttpUtils.get(url));
		System.out.println("apiResult="+apiResult);
		Map<String, String> map = new HashMap<String, String>();
		map.put("openid", apiResult.get("openid"));
		map.put("unionid", apiResult.get("unionid"));
		map.put("session_key", apiResult.get("session_key"));
		map.put("state", saveUser(apiResult.get("openid")));
		map.put("id", getid(apiResult.get("openid")));
		JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(map));
		// setSessionAttr("wxid", apiResult.get("openid"));
		// setSessionAttr("unionid", apiResult.get("unionid"));

		renderJson(jsonObject);
	}
	public void getlogin() {
		String code = getPara("code");
		// 接口地址
		String getOpenIdurl = "https://api.weixin.qq.com/sns/jscode2session";
		Map<String, String> params = new HashMap<String, String>();
		params.put("appid", WxPayConfig.appid);
		params.put("secret", WxPayConfig.appsecret);
		params.put("js_code", code);
		params.put("grant_type", WxPayConfig.grant_type);
		String para = PaymentKit.packageSign(params, false);
		String url = getOpenIdurl + "?" + para;
		ApiResult apiResult = new ApiResult(HttpUtils.get(url));
		System.out.println("apiResult="+apiResult);
		Map<String, String> map = new HashMap<String, String>();
		map.put("openid", apiResult.get("openid"));
		JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(map));
		// setSessionAttr("wxid", apiResult.get("openid"));
		// setSessionAttr("unionid", apiResult.get("unionid"));

		renderJson(jsonObject);
	}
	public String saveUser(String openid) {
		UserWwbz user=new UserWwbz().dao().findFirst("select * from user_wwbz where openid = ? and state=0",openid);
		if(null==user) {
			UserWwbz nu=new UserWwbz();
			nu.setJointime(new Date());
			nu.setOpenid(openid);
			nu.setState(0);
			nu.save();
			return "0";
		}else {
			if(null==user.getWxicon()) {
				return "0";
			}else {
				return "1";
			}
		}

	}
	public String getid(String openid) {
		UserWwbz user=new UserWwbz().dao().findFirst("select * from user_wwbz where openid = ? and state=0",openid);
		if(null==user) {
			return "0";
		}else {
			return user.getId()+"";
		}
	}
	public void updateUser() throws Base64DecodingException {
		String session_key = getPara("session_key");
		String encryptedData = getPara("encryptedData");
		String iv = getPara("iv");
		String nickName = getPara("nickName");
		String avatarUrl = getPara("avatarUrl");
		String openid =getPara("openid");
		System.out.println("openid="+openid);
		System.out.println("session_key="+session_key);
		System.out.println("encryptedData="+encryptedData);
		System.out.println("iv="+iv);
		System.out.println("nickName="+nickName);
		System.out.println("avatarUrl="+avatarUrl);
		
		UserWwbz user=new UserWwbz().dao().findFirst("select * from user_wwbz where openid = ? and state=0",openid);
		if(null!=user) {
			
			user.setNickname(nickName);
			user.setWxicon(avatarUrl);
			if(user.update()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}

		}else {
			setAttr("result",0);
		}
		renderJson();
//		Map map = new HashMap();    
//		try {    
//			byte[] dataByte = Base64.decode(encryptedData);
//			// 加密秘钥
//			byte[] keyByte = Base64.decode(session_key);
//			// 偏移量
//			byte[] ivByte = Base64.decode(iv);
//			byte[] resultByte  = AES.decrypt(dataByte,keyByte,ivByte);    
//			if(null != resultByte && resultByte.length > 0){    
//				String userInfo = new String(resultByte, "UTF-8");                   
//				map.put("status", "1");    
//				map.put("msg", "解密成功");                   
//				map.put("userInfo", userInfo);    
//			}else{    
//				map.put("status", "0");    
//				map.put("msg", "解密失败");    
//			}    
//		}catch (InvalidAlgorithmParameterException e) {    
//			e.printStackTrace();    
//		} catch (UnsupportedEncodingException e) {    
//			e.printStackTrace();    
//		}                  
//		//JSONObject jsonObject = JSONObject.parseObject(map.get("userInfo")+"");
//		JSONObject jsonObject = JSONObject.parseObject((String) map.get("userInfo"));
//		System.out.println(jsonObject.get("unionId"));
//		System.out.println(jsonObject.get("openId"));
//		UserWwbz user=new UserWwbz().dao().findFirst("select * from user_wwbz where openid = ? and state=0",jsonObject.get("openId"));
//		if(null!=user) {
//			user.setUnionid((String) jsonObject.get("unionId"));
//			user.setNickname(nickName);
//			user.setWxicon(avatarUrl);
//			if(user.update()) {
//				setAttr("result",1);
//			}else {
//				setAttr("result",0);
//			}
//
//		}else {
//			setAttr("result",0);
//		}
//		renderJson();

	}
	public void down() {
		System.out.println("down");
		GoodsWwbz goods=new GoodsWwbz().dao().findFirst("SELECT * from goods_wwbz where state<3 and states=0 and kind=0");
		List<OrderWwbz> oln=new OrderWwbz().dao().find("SELECT * FROM order_wwbz");
			setAttr("c",10000+oln.size());
		List<CustomerWwbz> ctl=new CustomerWwbz().dao().find("SELECT * FROM customer_wwbz where states=0");
			setAttr("list",ctl);
		if(null==goods) {
			setAttr("result",3);
		}else {
			Date now=new Date();
			//			System.out.println("now="+now);
			//			System.out.println("now="+now.getTime());
			//			System.out.println("goodsstime="+goods.getStime());
			//			System.out.println("goodsstime="+goods.getStime().getTime());
			//			System.out.println("goodsetime="+goods.getEtime());
			//			System.out.println("goodsetime="+goods.getEtime().getTime());
			//			System.out.println("stime-now="+(goods.getStime().getTime()-now.getTime()));
			//			System.out.println("etime-now="+(goods.getEtime().getTime()-now.getTime()));
			if(now.before(goods.getStime())) {
				if((goods.getStime().getTime()-now.getTime())>1000*60*60*24*3) {
					setAttr("result",0);
				}else {
					goods.setState(1);
					goods.update();
					setAttr("result",1);
					setAttr("count",goods.getStime().getTime()-now.getTime());
					setAttr("goods",goods);
				}

			}else if(now.after(goods.getStime())&&now.before(goods.getEtime())) {
				goods.setState(2);
				goods.update();
				setAttr("result",2);
				setAttr("count",goods.getEtime().getTime()-now.getTime());
				setAttr("goods",goods);
				List<OrderWwbz> ol=new OrderWwbz().dao().find("SELECT * FROM order_wwbz where goodsid = ?",goods.getId());
				int i=0;
				i=goods.getNumber()-ol.size();
				if(i<0) {
					i=0;
				}
				setAttr("number",i);
			}else if(now.after(goods.getEtime())){
				goods.setState(3);
				goods.update();
				setAttr("result",3);
			}
		}
		renderJson();
	}
	public void start() {
		System.out.println("start");
		int id = getParaToInt("id");
		GoodsWwbz goods=new GoodsWwbz().findById(id);
		goods.setState(2);
		if(goods.update()) {
			setAttr("result",1);
		}else {
			setAttr("result",0);
		}
		renderJson();
	}
	public void end() {
		System.out.println("end");
		int id = getParaToInt("id");
		GoodsWwbz goods=new GoodsWwbz().findById(id);
		goods.setState(3);
		if(goods.update()) {
			setAttr("result",1);
		}else {
			setAttr("result",0);
		}
		renderJson();
	}
	public void shoplist() {
		System.out.println("shoplist");
		List<GoodsWwbz> gl=new GoodsWwbz().dao().find("SELECT * from goods_wwbz where  states=0");
		setAttr("list",gl);
		renderJson();
	}
	public void goods() {
		System.out.println("goods");
		int id = getParaToInt("id");
		GoodsWwbz goods=new GoodsWwbz().dao().findById(id);
		setAttr("goods",goods);
		//List<CommentWwbz> ctm=new CommentWwbz().dao().find("SELECT * from comment_wwbz where states=0 and goodsid= ? and fathercommentid=0 ORDER BY time desc",id);
		Page<CommentWwbz> al=new CommentWwbz().dao().paginate(1, 5, "SELECT *", "from comment_wwbz where states=0 and goodsid= ? and fathercommentid=0 ORDER BY time desc",id);
		List<CommentWwbz> cta=new CommentWwbz().dao().find("SELECT * from comment_wwbz where states=0 and goodsid= ? ORDER BY time desc",id);
		//List<UserWwbz> userlist=new UserWwbz().dao().find("select * from user_wwbz");
		//setAttr("user",userlist);
		setAttr("commentm",al);
		setAttr("commenta",cta);
		renderJson();
	}
	public void pay() {
		String openid = getPara("openid");
		int id = getParaToInt("id");
		System.out.println("openid="+openid);
		System.out.println("id="+openid);
		int number = getParaToInt("number");
		GoodsWwbz goods=new GoodsWwbz().dao().findById(id);
		if(null!=goods) {
			
				try {

					// 统一下单文档地址：https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_1
					Map<String, String> params = new HashMap<String, String>();
					// 生成的随机字符串
					String nonce_str = StringUtils.getRandomStringByLength(32);
					// 商品名称
					String body = goods.getName();
					// 获取本机的ip地址
					String ip = IpKit.getRealIp(getRequest());
					// 部分安卓手机获取的ip地址是2个,字符串格式为"111.26.34.33, 123.151.76.158",
					// 此处进行判断和修正,出现此问题的手机品牌为华为，安卓版本6.0,EMUI4.1
					if (StrKit.isBlank(ip) || !StringUtils.Isipv4(ip)) {
						if (ip.indexOf(',') > 0) {
							ip = ip.substring(0, ip.indexOf(','));
						}
					}
					if (StrKit.isBlank(ip) || !StringUtils.Isipv4(ip)) {
						ip = "127.0.0.1";
					}
					// System.out.println(ip);
					// 商户订单号
					String out_trade_no = System.currentTimeMillis() + "";
					String total_fee="";
					total_fee = goods.getPrice();
					// 支付金额单位是分，不能带小数
					int price = ((int) (Float.valueOf(total_fee) * 100*number));
					total_fee = price + "";
					params.put("appid", WxPayConfig.appid);
					params.put("mch_id", WxPayConfig.mch_id);
					params.put("nonce_str", nonce_str);
					params.put("body", body);
					params.put("out_trade_no", out_trade_no);// 商户订单号
					params.put("total_fee", total_fee);// 支付金额，这边需要转成字符串类型，否则后面的签名会失败
					params.put("spbill_create_ip", ip);
					params.put("notify_url", WxPayConfig.notify_url);
					params.put("trade_type", WxPayConfig.TRADETYPE);
					params.put("openid", openid);
					// 除去数组中的空值和签名参数
					params = PayUtil.paraFilter(params);
					// 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
					String prestr = PayUtil.createLinkString(params);
					// MD5运算生成签名，这里是第一次签名，用于调用统一下单接口
					String sign = PayUtil.sign(prestr, WxPayConfig.key, "utf-8").toUpperCase();
					System.out.println("=======================第一次签名：" + sign + "=====================");
					// 拼接统一下单接口使用的xml数据，要将上一步生成的签名一起拼接进去
					String xml = "<xml>" + "<appid>" + WxPayConfig.appid + "</appid>" + "<body><![CDATA[" + body + "]]></body>"
							+ "<mch_id>" + WxPayConfig.mch_id + "</mch_id>" + "<nonce_str>" + nonce_str + "</nonce_str>"
							+ "<notify_url>" + WxPayConfig.notify_url + "</notify_url>" + "<openid>" + openid + "</openid>"
							+ "<out_trade_no>" + out_trade_no + "</out_trade_no>" + "<spbill_create_ip>" + ip
							+ "</spbill_create_ip>" + "<total_fee>" + total_fee + "</total_fee>" + "<trade_type>"
							+ WxPayConfig.TRADETYPE + "</trade_type>" + "<sign>" + sign + "</sign>" + "</xml>";
					System.out.println("调试模式_统一下单接口 请求XML数据：" + xml);
					// 调用统一下单接口，并接受返回的结果
					String result = PayUtil.httpRequest(WxPayConfig.pay_url, "POST", xml);
					System.out.println("调试模式_统一下单接口 返回XML数据：" + result);
					// 将解析结果存储在HashMap中
					@SuppressWarnings("rawtypes")
					Map map = PayUtil.doXMLParse(result);
					String return_code = (String) map.get("return_code");// 返回状态码
					String return_msg = (String) map.get("return_msg");
					if (StrKit.isBlank(return_code) || !"SUCCESS".equals(return_code)) {
						ajax.addError(return_msg);
						renderJson(ajax);
						return;
					}
					String result_code = (String) map.get("result_code");
					if (StrKit.isBlank(result_code) || !"SUCCESS".equals(result_code)) {
						ajax.addError(return_msg);
						renderJson(ajax);
						return;
					}
					// 返回给移动端需要的参数
					Map<String, Object> response = new HashMap<String, Object>();
					// 业务结果
					String prepay_id = (String) map.get("prepay_id");// 返回的预付单信息
					response.put("nonceStr", nonce_str);
					response.put("package", "prepay_id=" + prepay_id);
					Long timeStamp = System.currentTimeMillis() / 1000;
					response.put("timeStamp", timeStamp + "");// 这边要将返回的时间戳转化成字符串，不然小程序端调用wx.requestPayment方法会报签名错误
					String stringSignTemp = "appId=" + WxPayConfig.appid + "&nonceStr=" + nonce_str + "&package=prepay_id="
							+ prepay_id + "&signType=" + WxPayConfig.SIGNTYPE + "&timeStamp=" + timeStamp;
					// 再次签名，这个签名用于小程序端调用wx.requesetPayment方法
					String paySign = PayUtil.sign(stringSignTemp, WxPayConfig.key, "utf-8").toUpperCase();
					System.out.println("=======================第二次签名：" + paySign + "=====================");
					response.put("paySign", paySign);
					response.put("out_trade_no", out_trade_no);
					String jsonStr = JsonUtils.toJson(response);
					System.out.println(JsonUtils.toJson(response));
					ajax.success(jsonStr);
				} catch (Exception e) {
					e.printStackTrace();
					ajax.addError("发起失败！");
				}
				renderJson(ajax);
		
		}else {
			setAttr("result",0);
			renderJson();
		}

	}
	public void pay_notify() {
		// 获取所有的参数
		StringBuffer sbf = new StringBuffer();
		Enumeration<String> en = getParaNames();
		while (en.hasMoreElements()) {
			Object o = en.nextElement();
			sbf.append(o.toString() + "=" + getPara(o.toString()));
		}
		System.out.println("支付通知参数：" + sbf.toString());
		// 支付结果通用通知文档: https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_7
		String xmlMsg = HttpKit.readData(getRequest());
		System.out.println("支付通知=" + xmlMsg);
		Map<String, String> params = PaymentKit.xmlToMap(xmlMsg);
		// String returnCode = params.get("return_code");
		// String return_msg = params.get("return_msg");
		// String appid = params.get("appid");
		// 商户号
		// String mch_id = params.get("mch_id");
		String result_code = params.get("result_code");
		String openId = params.get("openid");
		// 交易类型
		// String trade_type = params.get("trade_type");
		// 付款银行
		// String bank_type = params.get("bank_type");
		// 总金额
		String total_fee = params.get("total_fee");
		// 现金支付金额
		// String cash_fee = params.get("cash_fee");
		// 微信支付订单号
		String transaction_id = params.get("transaction_id");
		// 商户订单号
		String out_trade_no = params.get("out_trade_no");
		// 支付完成时间，格式为yyyyMMddHHmmss
		String time_end = params.get("time_end");
		///////////////////////////// 以下是附加参数///////////////////////////////////
		// String attach = params.get("attach");
		// String fee_type = params.get("fee_type");
		// String is_subscribe = params.get("is_subscribe");
		// String err_code = params.get("err_code");
		// String err_code_des = params.get("err_code_des");
		// 注意重复通知的情况，同一订单号可能收到多次通知，请注意一定先判断订单状态
		// 避免已经成功、关闭、退款的订单被再次更新
		
		renderText("");
	}
	public void order() {
		int gid =getParaToInt("gid"); 
		int number =getParaToInt("number");
		int orderid=getParaToInt("orderid");
		int offset=getParaToInt("offset");
		String openid=getPara("openid"); 
        String out_trade_no=getPara("onumber"); 
        String lw=getPara("lw");
        int aid=getParaToInt("aid");
        System.out.println("openid="+openid);
        System.out.println("out_trade_no="+out_trade_no);
        System.out.println("lw="+lw);
        System.out.println("aid="+aid);
        OrderWwbz od=new OrderWwbz();
        AddressWwbz ad=new AddressWwbz().dao().findById(aid);
        GoodsWwbz gd=new GoodsWwbz().dao().findById(gid);
        RewardWwbz re=new RewardWwbz().dao().findById(1);
        if(orderid!=0) {
        	OrderWwbz uod=new OrderWwbz().dao().findById(orderid);
        	uod.setState(1);
        	uod.setBuytime(new Date());
        	if(uod.update()) {
        		if(gd.getKind()==1) {
        			UserWwbz user=new UserWwbz().dao().findFirst("SELECT * FROM user_wwbz where openid= ? and state=0",openid);
        			if(null!=user) {
        				user.setReward((Integer.parseInt(user.getReward())-(Integer.parseInt(gd.getPrice())*number))+"");
        				user.update();
        			}
        			setAttr("result",1);
        			gd.setCount(gd.getCount()+number);
            		gd.update();
        		}else if(gd.getKind()==0) {
        			setAttr("result",1);
        			
        			
        			
        			
        			
        			
        			
            		gd.setCount(gd.getCount()+number);
            		gd.update();
            		if(null!=uod.getFatheropenid()) {
            			DealerWwbz dl=new DealerWwbz().dao().findFirst("SELECT * from dealer_wwbz where states=0 and state=1 and openid=?",uod.getFatheropenid());
            			//DealerWwbz pdl=new DealerWwbz().dao().findFirst("SELECT * from dealer_wwbz where states=0 and state=1 and openid=?",uod.getHeadopenid());
            			if(null!=dl) {
            				dl.setTotal(dl.getTotal()+Double.parseDouble(uod.getMoney()));
            				dl.setUnliquidated(dl.getUnliquidated()+Double.parseDouble(uod.getMoney()));
            				dl.update();
            				message.message(dl.getTel(), "小美恭喜您新增一笔"+uod.getMoney()+"元的销售额！详情请查看微信小程序！");
            			}
            		}
            		
            		if(null!=uod.getHeadopenid()) {
            			DealerWwbz pdl=new DealerWwbz().dao().findFirst("SELECT * from dealer_wwbz where states=0 and state=1 and openid=?",uod.getHeadopenid());
            			if(null!=pdl) {
            				pdl.setHeadtotal(pdl.getHeadtotal()+Double.parseDouble(uod.getMoney()));
            				pdl.setHeadunliquidated(pdl.getHeadunliquidated()+Double.parseDouble(uod.getMoney()));
            				pdl.update();
            				message.message(pdl.getTel(), "小美恭喜您的大区新增一笔"+uod.getMoney()+"元的销售额！详情请查看微信小程序！");
            			}
            		}
            		
            		
            		UserWwbz user=new UserWwbz().dao().findFirst("SELECT * FROM user_wwbz where openid= ? and state=0",openid);
            		if(null!=user&&uod.getOffset()!=0) {
//            			int money=Integer.parseInt(new java.text.DecimalFormat("0").format((Double.parseDouble(gd.getPrice())*number*re.getGoods())));
//            			System.out.println("money="+money);
//            			user.setReward((Integer.parseInt(user.getReward())+money)+"");
//            			
//            			if(user.update()) {
//            				OrderWwbz jfod=new OrderWwbz();
//            				jfod.setMoney(money+"");
//            				jfod.setBuytime(new Date());
//            				jfod.setKind(1);
//            				jfod.setDirection(1);
//            				jfod.setOpenid(openid);
//            				jfod.setState(3);
//            				jfod.setOutTradeNo(out_trade_no);
//            				jfod.setGoodsid(0);
//            				jfod.setNumber(1);
//            				jfod.setSheng(ad.getSheng());
//            				jfod.setShi(ad.getShi());
//            				jfod.setQu(ad.getQu());
//            				jfod.setAddress(ad.getAddress());
//            				jfod.setTel(ad.getTel());
//            				jfod.setName(ad.getName());
//            				jfod.setGoodsname("购买"+gd.getName());
//            				jfod.save();
//            			}
            			int money=uod.getOffset()*number;
            			System.out.println("money="+money);
            			user.setReward((Integer.parseInt(user.getReward())-money)+"");
            			
            			if(user.update()&&offset!=0) {
            				OrderWwbz jfod=new OrderWwbz();
            				jfod.setMoney(money+"");
            				jfod.setBuytime(new Date());
            				jfod.setKind(1);
            				jfod.setDirection(0);
            				jfod.setOpenid(openid);
            				jfod.setState(3);
            				jfod.setOutTradeNo(out_trade_no);
            				jfod.setGoodsid(0);
            				jfod.setNumber(1);
            				jfod.setSheng(ad.getSheng());
            				jfod.setShi(ad.getShi());
            				jfod.setQu(ad.getQu());
            				jfod.setAddress(ad.getAddress());
            				jfod.setTel(ad.getTel());
            				jfod.setName(ad.getName());
            				jfod.setGoodsname("购买"+gd.getName()+"消费");
            				jfod.save();
            			}
            		}
            	}
        		
        	}else {
        		setAttr("result",0);
        	}
        }else {
        	if(offset==0) {
        		od.setMoney((Double.parseDouble(gd.getPrice())*number)+"");
        	}else {
        		od.setMoney(String.format("%.2f", ((Double.parseDouble(gd.getPrice())-(offset/100.0))*number)));
        	}
        	od.setOffset(offset);
            od.setBuytime(new Date());
            od.setOpenid(openid);
            od.setState(1);
            od.setOutTradeNo(out_trade_no);
            od.setGoodsid(gid);
            od.setNumber(number);
            od.setSheng(ad.getSheng());
            od.setShi(ad.getShi());
            od.setQu(ad.getQu());
            od.setAddress(ad.getAddress());
            od.setTel(ad.getTel());
            od.setName(ad.getName());
            od.setKind(gd.getKind());
            od.setLw(lw);
            od.setGoodsname(gd.getName());
            UserWwbz use=new UserWwbz().dao().findFirst("SELECT * FROM user_wwbz where openid = ?",openid);
        	if(null!=use&&null!=use.getFatheropenid()) {
        		od.setFatheropenid(use.getFatheropenid());
        	}
        	
        	UserWwbz father=new UserWwbz().dao().findFirst("SELECT * FROM user_wwbz where openid = ? and state=0",use.getFatheropenid());
        	if(null!=father&&null!=father.getFatheropenid()) {
        		DealerWwbz fdl=new DealerWwbz().dao().findFirst("SELECT * from dealer_wwbz where openid= ? and states=0 and state=1",father.getOpenid());
        		DealerWwbz gdl=new DealerWwbz().dao().findFirst("SELECT * from dealer_wwbz where openid= ? and states=0 and state=1",father.getFatheropenid());
        		if(null!=fdl&&null!=gdl) {
        			if(fdl.getIshospital()==1) {
        				od.setHeadopenid(father.getFatheropenid());
        				od.setHeadpercent(fdl.getHeadpercent());
        			}
        		}
        		
        		
        		if(null!=gdl) {
        			if(gdl.getIshead()==1) {
            			od.setHeadopenid(father.getFatheropenid());
            			od.setHeadpercent(gdl.getHeadpercent());
            		}
        		}
        		
        	}
        	
        	
            if(od.save()) {
            	
            	DealerWwbz dl=new DealerWwbz().dao().findFirst("SELECT * from dealer_wwbz where states=0 and state=1 and openid=?",use.getFatheropenid());
    			if(null!=dl) {
    				dl.setTotal(dl.getTotal()+(Double.parseDouble(gd.getPrice())*number));
    				dl.setUnliquidated(dl.getUnliquidated()+(Double.parseDouble(gd.getPrice())*number));
    				dl.update();
    				message.message(dl.getTel(), "小美恭喜您新增一笔"+od.getMoney()+"元的销售额！详情请查看微信小程序！");
    			}

    			if(null!=od.getHeadopenid()) {
        			DealerWwbz pdl=new DealerWwbz().dao().findFirst("SELECT * from dealer_wwbz where states=0 and state=1 and openid=?",od.getHeadopenid());
        			if(null!=pdl) {
        				pdl.setHeadtotal(pdl.getHeadtotal()+(Double.parseDouble(gd.getPrice())*number));
        				pdl.setHeadunliquidated(pdl.getHeadunliquidated()+(Double.parseDouble(gd.getPrice())*number));
        				pdl.update();
        				message.message(pdl.getTel(), "小美恭喜您的大区新增一笔"+od.getMoney()+"元的销售额！详情请查看微信小程序！");
        			}
        		}
    			
            	
            	setAttr("result",1);
            	gd.setCount(gd.getCount()+number);
            	gd.update();
            	if(gd.getKind()==1) {
            		UserWwbz user=new UserWwbz().dao().findFirst("SELECT * FROM user_wwbz where openid= ? and state=0",openid);
            		if(null!=user) {
            			user.setReward((Integer.parseInt(user.getReward())-(Integer.parseInt(gd.getPrice())*number))+"");
            			user.update();
            		}
            	}else if(gd.getKind()==0) {
            		
            		UserWwbz user=new UserWwbz().dao().findFirst("SELECT * FROM user_wwbz where openid= ? and state=0",openid);
            		
            		if(null!=user) {
            			int money=offset*number;
            			System.out.println("money="+money);
            			user.setReward((Integer.parseInt(user.getReward())-money)+"");
            			if(user.update()&&offset!=0) {
            				OrderWwbz jfod=new OrderWwbz();
            				jfod.setMoney(money+"");
            				jfod.setBuytime(new Date());
            				jfod.setKind(1);
            				jfod.setDirection(0);
            				jfod.setOpenid(openid);
            				jfod.setState(3);
            				jfod.setOutTradeNo(out_trade_no);
            				jfod.setGoodsid(0);
            				jfod.setNumber(1);
            				jfod.setSheng(ad.getSheng());
            				jfod.setShi(ad.getShi());
            				jfod.setQu(ad.getQu());
            				jfod.setAddress(ad.getAddress());
            				jfod.setTel(ad.getTel());
            				jfod.setName(ad.getName());
            				jfod.setGoodsname("购买"+gd.getName()+"消费");
            				jfod.save();
            			}
            		}
            	}
            }else {
            	setAttr("result",0);
            }
        }
        renderJson();
	}
	public void failorder() {
		int gid =getParaToInt("gid"); 
		int number =getParaToInt("number"); 
		int orderid=getParaToInt("orderid");
		int offset=getParaToInt("offset");
		String openid=getPara("openid"); 
        String out_trade_no=getPara("onumber"); 
        String lw=getPara("lw");
        int aid=getParaToInt("aid");
        System.out.println("openid="+openid);
        System.out.println("out_trade_no="+out_trade_no);
        System.out.println("lw="+lw);
        System.out.println("aid="+aid);
        OrderWwbz od=new OrderWwbz();
    	AddressWwbz ad=new AddressWwbz().dao().findById(aid);
    	GoodsWwbz gd=new GoodsWwbz().dao().findById(gid);
        if(orderid!=0) {

        }else {
        	if(offset==0) {
        		od.setMoney((Double.parseDouble(gd.getPrice())*number)+"");
        	}else {
        		System.out.println("price="+Double.parseDouble(gd.getPrice()));
        		System.out.println("offset="+offset/100.0);
        		System.out.println("money="+((Double.parseDouble(gd.getPrice())-(offset/100.0))*number));
        		od.setMoney(String.format("%.2f", ((Double.parseDouble(gd.getPrice())-(offset/100.0))*number)));
        	}
        	od.setOffset(offset);
        	od.setBuytime(new Date());
        	od.setOpenid(openid);
        	od.setState(0);
        	od.setOutTradeNo(out_trade_no);
        	od.setGoodsid(gid);
        	od.setNumber(number);
        	od.setSheng(ad.getSheng());
        	od.setShi(ad.getShi());
        	od.setQu(ad.getQu());
        	od.setAddress(ad.getAddress());
        	od.setTel(ad.getTel());
        	od.setName(ad.getName());
        	od.setKind(gd.getKind());
        	od.setLw(lw);
        	od.setGoodsname(gd.getName());
        	UserWwbz user=new UserWwbz().dao().findFirst("SELECT * FROM user_wwbz where openid = ? and state=0",openid);
        	if(null!=user&&null!=user.getFatheropenid()) {
        		od.setFatheropenid(user.getFatheropenid());
        	}
        	UserWwbz father=new UserWwbz().dao().findFirst("SELECT * FROM user_wwbz where openid = ? and state=0",user.getFatheropenid());
        	if(null!=father&&null!=father.getFatheropenid()) {
        		DealerWwbz fdl=new DealerWwbz().dao().findFirst("SELECT * from dealer_wwbz where openid= ? and states=0 and state=1",father.getOpenid());
        		DealerWwbz gdl=new DealerWwbz().dao().findFirst("SELECT * from dealer_wwbz where openid= ? and states=0 and state=1",father.getFatheropenid());
        		if(null!=fdl&&null!=gdl) {
        			if(fdl.getIshospital()==1) {
        				od.setHeadopenid(father.getFatheropenid());
        				od.setHeadpercent(fdl.getHeadpercent());
        			}
        		}
        		
        		
        		if(null!=gdl) {
        			if(gdl.getIshead()==1) {
            			od.setHeadopenid(father.getFatheropenid());
            			od.setHeadpercent(gdl.getHeadpercent());
            		}
        		}
        		
        	}
        	
        	if(od.save()) {
        		setAttr("result",1);
        	}else {
        		setAttr("result",0);
        	}
        }
        renderJson();
	}
	public void article() {
		List<ArticleWwbz> al=new ArticleWwbz().dao().find("SELECT * FROM article_wwbz where states =0");
		setAttr("al",al);
		List<ArticlekindWwbz> akl=new ArticlekindWwbz().dao().find("SELECT * FROM articlekind_wwbz where states =0");
		setAttr("akl",akl);
		renderJson();
	}

	public void uploadpic() {
		UploadFile uploadFile = getFile();
		if(null != uploadFile) {
			boolean flag = false;
			String fileName = "";
			String fileNamePath = "";
					fileName = uploadFile.getFileName();// 获取保存文件的文件名
					fileNamePath = uploadFile.getUploadPath();
					String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length());
					fileName = System.currentTimeMillis() + fileType;
					File img = new File(fileNamePath + "/" + fileName);
					if (img.exists()) {
						img.delete();
					}
					flag = uploadFile.getFile().renameTo(img);
					System.out.println(fileNamePath);
					System.out.println("icon/"+img.getName());
					setAttr("url","icon/"+img.getName());
		}
		renderJson();
	}
	
	public void userinfo() {
		String openid=getPara("openid");
		UserWwbz user=new UserWwbz().dao().findFirst("SELECT * FROM user_wwbz where openid =? and state=0",openid);
		List<Record> list=Db.find("SELECT a.money money, a.buytime buytime,a.number number,b.pic pic ,b.name name FROM order_wwbz a  LEFT JOIN goods_wwbz b on a.goodsid=b.id where a.openid= ?",openid);
		List<FeedWwbz> fd=new FeedWwbz().dao().find("SELECT * FROM feed_wwbz where openid = ?",openid); 
		setAttr("list",list);
		setAttr("fdl",fd);
		if(null!=user) {
			setAttr("user",user);
			setAttr("result",1);
		}else {
			UserWwbz newuser=new UserWwbz();
			setAttr("result",1);
			setAttr("user",newuser);
		}
		renderJson();
	}
	public void store() {
		String city =getPara("city");
		List<DealerWwbz> citylist=new DealerWwbz().dao().find("SELECT * FROM dealer_wwbz where states=0 and isshow=1 and state=1 GROUP BY city");
		if("".equals(city)) {
			
			String c="";
			for (DealerWwbz dealerWwbz : citylist) {
				c=dealerWwbz.getCity();
				break;
			}
			List<DealerWwbz> dl=new DealerWwbz().dao().find("SELECT * FROM dealer_wwbz where states=0 and isshow=1 and state=1 and city=? ORDER BY storename",c);
			for (DealerWwbz dealerWwbz : dl) {
				dealerWwbz.setStorename(dealerWwbz.getStorename().replaceFirst("市", ""));
				dealerWwbz.setName(dealerWwbz.getName().substring(0,1));
			}
			setAttr("list",dl);
			setAttr("citylist",citylist);
		}else {
			List<DealerWwbz> dl=new DealerWwbz().dao().find("SELECT * FROM dealer_wwbz where states=0 and isshow=1 and state=1 and city=? ORDER BY storename",city);
			for (DealerWwbz dealerWwbz : dl) {
				dealerWwbz.setStorename(dealerWwbz.getStorename().replaceFirst("市", ""));
				dealerWwbz.setName(dealerWwbz.getName().substring(0,1));
			}
			setAttr("list",dl);
			setAttr("citylist",citylist);
		}
		renderJson();
	}
	public void storelist() {
		
		List<StoreWwbz> cl=new StoreWwbz().dao().find("SELECT * FROM store_wwbz where states=0 GROUP BY city");
		String city="";
		for (StoreWwbz storeWwbz : cl) {
			city=storeWwbz.getCity();
			break;
		}
		List<StoreWwbz> sl=new StoreWwbz().dao().find("SELECT * FROM store_wwbz where states=0 and city=?",city);
		setAttr("list",sl);
		setAttr("citylist",cl);
		renderJson();
	}
	public void storechange() {
		String city =getPara("city");
		List<StoreWwbz> sl=new StoreWwbz().dao().find("SELECT * FROM store_wwbz where states=0 and city=?",city);
		List<StoreWwbz> cl=new StoreWwbz().dao().find("SELECT * FROM store_wwbz where states=0 GROUP BY city");
		
		setAttr("list",sl);
		setAttr("citylist",cl);
		renderJson();
	}
	public void address() {
		String openid =getPara("openid");
		List<AddressWwbz> ad=new AddressWwbz().dao.find("select * from address_wwbz where openid = ?",openid);
		if(ad.size()==0) {
			setAttr("result",0);
		}else {
			setAttr("result",1);
			for (AddressWwbz addressWwbz : ad) {
				if(addressWwbz.getState()==1) {
					setAttr("result",2);
					setAttr("address",addressWwbz);
					break;
				}
			}
			
		}
		renderJson();
	}
	public void newaddress() {
		int id=getParaToInt("id");
		String name=getPara("name");
		String tel=getPara("tel");
		String sheng=getPara("sheng");
		String shi=getPara("shi");
		String qu=getPara("qu");
		String address=getPara("address");
		boolean df=getParaToBoolean("default");
		String openid=getPara("openid");
		AddressWwbz ad=new AddressWwbz();
		ad.setName(name);
		ad.setTel(tel);
		ad.setSheng(sheng);
		ad.setShi(shi);
		ad.setQu(qu);
		ad.setAddress(address);
		ad.setOpenid(openid);
		ad.setStates(0);
		if(df==true) {
			ad.setState(1);
			List<AddressWwbz> adl=new AddressWwbz().dao().find("SELECT * from address_wwbz where states=0 and openid= ?",openid);
			for (AddressWwbz addressWwbz : adl) {
				addressWwbz.setState(0);
				addressWwbz.update();
			}
		}else {
			ad.setState(0);
		}
		if(id==0) {
			if(ad.save()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}else {
			ad.setId(id);
			if(ad.update()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}
		renderJson();
	}
	public void addresslist() {
		String openid=getPara("openid");
		List<AddressWwbz> adl=new AddressWwbz().dao().find("SELECT * from address_wwbz where states=0 and openid= ?",openid);
		setAttr("list",adl);
		renderJson();
	}
	public void selectaddress() {
		int id=getParaToInt("id");
		AddressWwbz ad=new AddressWwbz().dao().findFirst("select * from address_wwbz where id = ?",id);
		System.out.println(ad);
		if(null!=ad) {
			setAttr("address",ad);
		}else {
			AddressWwbz ad1=new AddressWwbz();
			setAttr("address",ad1);
		}
		
		renderJson();
	}
	public void deladdress() {
		int id=getParaToInt("id");
		AddressWwbz ad=new AddressWwbz().dao().findFirst("select * from address_wwbz where id = ?",id);
		System.out.println(ad);
		if(null!=ad) {
			ad.setStates(-1);
			ad.setState(0);
			if(ad.update()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}else {
			setAttr("result",1);
		}
		
		renderJson();
	}
	public void reward() {
		String openid=getPara("openid");
		UserWwbz user=new UserWwbz().dao().findFirst("SELECT * FROM user_wwbz where openid= ? and state=0",openid);
		if(null!=user) {
			setAttr("reward",user.getReward());
			setAttr("money",user.getMoney());
		}else {
			setAttr("reward",0);
		}
		renderJson();
	}
	public void rewardpay() {
		String openid = getPara("openid");
		int id = getParaToInt("id");
		System.out.println("openid="+openid);
		System.out.println("id="+openid);
		int number = getParaToInt("number");
		GoodsWwbz goods=new GoodsWwbz().dao().findById(id);
		UserWwbz user=new UserWwbz().dao().findFirst("SELECT * FROM user_wwbz where openid= ? and state=0",openid);
		if(null!=goods&&null!=user) {
			if(Double.parseDouble(user.getReward())>=Double.parseDouble(goods.getPrice())*number){
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}else {
			setAttr("result",0);
		}
		renderJson();
	}
	public void addcart() {
		String openid=getPara("openid");
		int id=getParaToInt("id");
		CartWwbz cr=new CartWwbz().dao().findFirst("SELECT * from cart_wwbz where openid=? and gid = ?",openid,id);
		if(null!=cr) {
			cr.setNumber(cr.getNumber()+1);
			if(cr.update()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}else {
			CartWwbz ncr=new CartWwbz();
			ncr.setGid(id);
			ncr.setNumber(1);
			ncr.setOpenid(openid);
			if(ncr.save()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}	
		}
		renderJson();
	}
	public void cartcount() {
		String openid=getPara("openid");
		List<CartWwbz> ctl=new CartWwbz().dao().find("SELECT * FROM cart_wwbz WHERE openid=?",openid);
		int number=0;
		for (CartWwbz cartWwbz : ctl) {
			number+=cartWwbz.getNumber();
		}
		setAttr("count",number);
		renderJson();
	}
	public void cart() {
		String openid =getPara("openid");
		List<CartWwbz> ctl=new CartWwbz().dao().find("SELECT * FROM cart_wwbz WHERE openid=?",openid);		
		int number=0;
		Double total=0.00;
		for (CartWwbz cartWwbz : ctl) {
			number+=cartWwbz.getNumber();
			GoodsWwbz gd=new GoodsWwbz().dao().findById(cartWwbz.getGid());
			total+=Double.parseDouble(gd.getPrice())*cartWwbz.getNumber();
		}
		List<Record> list=Db.find("SELECT a.number number,b.name name,b.pic pic,b.standard standard,b.price price,b.id id,b.kind kind,b.offset offset FROM cart_wwbz a LEFT JOIN goods_wwbz b on a.gid=b.id where a.openid=?",openid);
		setAttr("number",number);
		setAttr("total",(double)Math.round(total*100)/100);
		setAttr("list",list);
		renderJson();
	}
	public void delcart() {
		String dl=getPara("dl");
		String[] list=dl.substring(1, dl.length()-1).split(",");
		String openid=getPara("openid");
		int result=1;
		for (String string : list) {
			CartWwbz ct=new CartWwbz().dao().findFirst("SELECT * FROM cart_wwbz where gid= ? and openid =?",string,openid);
			if(null!=ct) {
				if(ct.delete()) {
					
				}else {
					result=0;
				}
			}
			
		}
		
		System.out.println("openid="+openid);
		setAttr("result",result);
		renderJson();
	}
	public void jiacart() {
		String openid=getPara("openid");
		int id=getParaToInt("id");
		CartWwbz ct=new CartWwbz().dao().findFirst("SELECT * FROM cart_wwbz where gid= ? and openid =?",id,openid);
		if(null!=ct) {
			ct.setNumber(ct.getNumber()+1);
			if(ct.update()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}else {
			setAttr("result",0);
		}
		renderJson();
	}
	public void jiancart() {
		String openid=getPara("openid");
		int id=getParaToInt("id");
		CartWwbz ct=new CartWwbz().dao().findFirst("SELECT * FROM cart_wwbz where gid= ? and openid =?",id,openid);
		if(null!=ct&&ct.getNumber()>1) {
			ct.setNumber(ct.getNumber()-1);
			if(ct.update()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}else {
			setAttr("result",0);
		}
		renderJson();
	}
	public void applypay() {
		String openid = getPara("openid");
		String money=getPara("money");
		System.out.println("openid="+openid);
		System.out.println("money="+money);


		try {

			// 统一下单文档地址：https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_1
			Map<String, String> params = new HashMap<String, String>();
			// 生成的随机字符串
			String nonce_str = StringUtils.getRandomStringByLength(32);
			// 商品名称
			String body = "合作定金";
			// 获取本机的ip地址
			String ip = IpKit.getRealIp(getRequest());
			// 部分安卓手机获取的ip地址是2个,字符串格式为"111.26.34.33, 123.151.76.158",
			// 此处进行判断和修正,出现此问题的手机品牌为华为，安卓版本6.0,EMUI4.1
			if (StrKit.isBlank(ip) || !StringUtils.Isipv4(ip)) {
				if (ip.indexOf(',') > 0) {
					ip = ip.substring(0, ip.indexOf(','));
				}
			}
			if (StrKit.isBlank(ip) || !StringUtils.Isipv4(ip)) {
				ip = "127.0.0.1";
			}
			// System.out.println(ip);
			// 商户订单号
			String out_trade_no = System.currentTimeMillis() + "";
			String total_fee="";
			total_fee = money;
			// 支付金额单位是分，不能带小数
			int price = ((int) (Float.valueOf(total_fee) * 100));
			total_fee = price + "";
			params.put("appid", WxPayConfig.appid);
			params.put("mch_id", WxPayConfig.mch_id);
			params.put("nonce_str", nonce_str);
			params.put("body", body);
			params.put("out_trade_no", out_trade_no);// 商户订单号
			params.put("total_fee", total_fee);// 支付金额，这边需要转成字符串类型，否则后面的签名会失败
			params.put("spbill_create_ip", ip);
			params.put("notify_url", WxPayConfig.notify_url);
			params.put("trade_type", WxPayConfig.TRADETYPE);
			params.put("openid", openid);
			// 除去数组中的空值和签名参数
			params = PayUtil.paraFilter(params);
			// 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
			String prestr = PayUtil.createLinkString(params);
			// MD5运算生成签名，这里是第一次签名，用于调用统一下单接口
			String sign = PayUtil.sign(prestr, WxPayConfig.key, "utf-8").toUpperCase();
			System.out.println("=======================第一次签名：" + sign + "=====================");
			// 拼接统一下单接口使用的xml数据，要将上一步生成的签名一起拼接进去
			String xml = "<xml>" + "<appid>" + WxPayConfig.appid + "</appid>" + "<body><![CDATA[" + body + "]]></body>"
					+ "<mch_id>" + WxPayConfig.mch_id + "</mch_id>" + "<nonce_str>" + nonce_str + "</nonce_str>"
					+ "<notify_url>" + WxPayConfig.notify_url + "</notify_url>" + "<openid>" + openid + "</openid>"
					+ "<out_trade_no>" + out_trade_no + "</out_trade_no>" + "<spbill_create_ip>" + ip
					+ "</spbill_create_ip>" + "<total_fee>" + total_fee + "</total_fee>" + "<trade_type>"
					+ WxPayConfig.TRADETYPE + "</trade_type>" + "<sign>" + sign + "</sign>" + "</xml>";
			System.out.println("调试模式_统一下单接口 请求XML数据：" + xml);
			// 调用统一下单接口，并接受返回的结果
			String result = PayUtil.httpRequest(WxPayConfig.pay_url, "POST", xml);
			System.out.println("调试模式_统一下单接口 返回XML数据：" + result);
			// 将解析结果存储在HashMap中
			@SuppressWarnings("rawtypes")
			Map map = PayUtil.doXMLParse(result);
			String return_code = (String) map.get("return_code");// 返回状态码
			String return_msg = (String) map.get("return_msg");
			if (StrKit.isBlank(return_code) || !"SUCCESS".equals(return_code)) {
				ajax.addError(return_msg);
				renderJson(ajax);
				return;
			}
			String result_code = (String) map.get("result_code");
			if (StrKit.isBlank(result_code) || !"SUCCESS".equals(result_code)) {
				ajax.addError(return_msg);
				renderJson(ajax);
				return;
			}
			// 返回给移动端需要的参数
			Map<String, Object> response = new HashMap<String, Object>();
			// 业务结果
			String prepay_id = (String) map.get("prepay_id");// 返回的预付单信息
			response.put("nonceStr", nonce_str);
			response.put("package", "prepay_id=" + prepay_id);
			Long timeStamp = System.currentTimeMillis() / 1000;
			response.put("timeStamp", timeStamp + "");// 这边要将返回的时间戳转化成字符串，不然小程序端调用wx.requestPayment方法会报签名错误
			String stringSignTemp = "appId=" + WxPayConfig.appid + "&nonceStr=" + nonce_str + "&package=prepay_id="
					+ prepay_id + "&signType=" + WxPayConfig.SIGNTYPE + "&timeStamp=" + timeStamp;
			// 再次签名，这个签名用于小程序端调用wx.requesetPayment方法
			String paySign = PayUtil.sign(stringSignTemp, WxPayConfig.key, "utf-8").toUpperCase();
			System.out.println("=======================第二次签名：" + paySign + "=====================");
			response.put("paySign", paySign);
			response.put("out_trade_no", out_trade_no);
			String jsonStr = JsonUtils.toJson(response);
			System.out.println(JsonUtils.toJson(response));
			ajax.success(jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
			ajax.addError("发起失败！");
		}
		renderJson(ajax);


	}
	public void account() {
		String openid = getPara("openid");
		String money=getPara("money");
		System.out.println("openid="+openid);
		System.out.println("money="+money);


		try {

			// 统一下单文档地址：https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_1
			Map<String, String> params = new HashMap<String, String>();
			// 生成的随机字符串
			String nonce_str = StringUtils.getRandomStringByLength(32);
			// 商品名称
			String body = "结算购物";
			// 获取本机的ip地址
			String ip = IpKit.getRealIp(getRequest());
			// 部分安卓手机获取的ip地址是2个,字符串格式为"111.26.34.33, 123.151.76.158",
			// 此处进行判断和修正,出现此问题的手机品牌为华为，安卓版本6.0,EMUI4.1
			if (StrKit.isBlank(ip) || !StringUtils.Isipv4(ip)) {
				if (ip.indexOf(',') > 0) {
					ip = ip.substring(0, ip.indexOf(','));
				}
			}
			if (StrKit.isBlank(ip) || !StringUtils.Isipv4(ip)) {
				ip = "127.0.0.1";
			}
			// System.out.println(ip);
			// 商户订单号
			String out_trade_no = System.currentTimeMillis() + "";
			String total_fee="";
			total_fee = money;
			// 支付金额单位是分，不能带小数
			int price = ((int) (Float.valueOf(total_fee) * 100));
			total_fee = price + "";
			params.put("appid", WxPayConfig.appid);
			params.put("mch_id", WxPayConfig.mch_id);
			params.put("nonce_str", nonce_str);
			params.put("body", body);
			params.put("out_trade_no", out_trade_no);// 商户订单号
			params.put("total_fee", total_fee);// 支付金额，这边需要转成字符串类型，否则后面的签名会失败
			params.put("spbill_create_ip", ip);
			params.put("notify_url", WxPayConfig.notify_url);
			params.put("trade_type", WxPayConfig.TRADETYPE);
			params.put("openid", openid);
			// 除去数组中的空值和签名参数
			params = PayUtil.paraFilter(params);
			// 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
			String prestr = PayUtil.createLinkString(params);
			// MD5运算生成签名，这里是第一次签名，用于调用统一下单接口
			String sign = PayUtil.sign(prestr, WxPayConfig.key, "utf-8").toUpperCase();
			System.out.println("=======================第一次签名：" + sign + "=====================");
			// 拼接统一下单接口使用的xml数据，要将上一步生成的签名一起拼接进去
			String xml = "<xml>" + "<appid>" + WxPayConfig.appid + "</appid>" + "<body><![CDATA[" + body + "]]></body>"
					+ "<mch_id>" + WxPayConfig.mch_id + "</mch_id>" + "<nonce_str>" + nonce_str + "</nonce_str>"
					+ "<notify_url>" + WxPayConfig.notify_url + "</notify_url>" + "<openid>" + openid + "</openid>"
					+ "<out_trade_no>" + out_trade_no + "</out_trade_no>" + "<spbill_create_ip>" + ip
					+ "</spbill_create_ip>" + "<total_fee>" + total_fee + "</total_fee>" + "<trade_type>"
					+ WxPayConfig.TRADETYPE + "</trade_type>" + "<sign>" + sign + "</sign>" + "</xml>";
			System.out.println("调试模式_统一下单接口 请求XML数据：" + xml);
			// 调用统一下单接口，并接受返回的结果
			String result = PayUtil.httpRequest(WxPayConfig.pay_url, "POST", xml);
			System.out.println("调试模式_统一下单接口 返回XML数据：" + result);
			// 将解析结果存储在HashMap中
			@SuppressWarnings("rawtypes")
			Map map = PayUtil.doXMLParse(result);
			String return_code = (String) map.get("return_code");// 返回状态码
			String return_msg = (String) map.get("return_msg");
			if (StrKit.isBlank(return_code) || !"SUCCESS".equals(return_code)) {
				ajax.addError(return_msg);
				renderJson(ajax);
				return;
			}
			String result_code = (String) map.get("result_code");
			if (StrKit.isBlank(result_code) || !"SUCCESS".equals(result_code)) {
				ajax.addError(return_msg);
				renderJson(ajax);
				return;
			}
			// 返回给移动端需要的参数
			Map<String, Object> response = new HashMap<String, Object>();
			// 业务结果
			String prepay_id = (String) map.get("prepay_id");// 返回的预付单信息
			response.put("nonceStr", nonce_str);
			response.put("package", "prepay_id=" + prepay_id);
			Long timeStamp = System.currentTimeMillis() / 1000;
			response.put("timeStamp", timeStamp + "");// 这边要将返回的时间戳转化成字符串，不然小程序端调用wx.requestPayment方法会报签名错误
			String stringSignTemp = "appId=" + WxPayConfig.appid + "&nonceStr=" + nonce_str + "&package=prepay_id="
					+ prepay_id + "&signType=" + WxPayConfig.SIGNTYPE + "&timeStamp=" + timeStamp;
			// 再次签名，这个签名用于小程序端调用wx.requesetPayment方法
			String paySign = PayUtil.sign(stringSignTemp, WxPayConfig.key, "utf-8").toUpperCase();
			System.out.println("=======================第二次签名：" + paySign + "=====================");
			response.put("paySign", paySign);
			response.put("out_trade_no", out_trade_no);
			String jsonStr = JsonUtils.toJson(response);
			System.out.println(JsonUtils.toJson(response));
			ajax.success(jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
			ajax.addError("发起失败！");
		}
		renderJson(ajax);


	}
	public void rewardlist() {
		String openid=getPara("openid");
		List<OrderWwbz> ol=new OrderWwbz().dao().find("SELECT * FROM order_wwbz where kind=1 and openid=? order by buytime desc",openid);
		setAttr("list",ol);
		UserWwbz user=new UserWwbz().dao().findFirst("SELECT * FROM user_wwbz where openid = ? and state=0",openid);
		setAttr("total",user.getReward());
		renderJson();
	}
	public void test() {
		System.out.println("test");
		//message.message("18602227456","您的经销商申请已通过，感谢您的支持！");
		renderJson();
	}
	public void orderlist() {
		String openid=getPara("openid");
		List<Record> list=Db.find("SELECT a.id orderid,a.offset offset,a.money money,a.state state,a.out_trade_no out_trade_no,a.number number,a.kind kind,a.iscomment iscomment,b.name name,b.pic pic,b.standard standard,b.price price,b.id id from order_wwbz a LEFT JOIN goods_wwbz b on a.goodsid=b.id where a.direction=0 and openid=? and b.states=0",openid);
		setAttr("list",list);
		renderJson();
	}
	public void express() {
		String openid=getPara("openid");
		List<OrderWwbz> ol=new OrderWwbz().dao().find("SELECT * FROM order_wwbz where state=2 and openid=?",openid);
		for (OrderWwbz od : ol) {
			if(null!=od.getCom()) {
				String result=express.search(od.getCom(),od.getNum());
				JSONObject jsStr = JSONObject.parseObject(result);
				if(null!=jsStr.getBoolean("result")) {
					
				}else {
					od.setExpress(result);
					od.update();
				}

				if("3".equals(jsStr.get("state")) ||"4".equals(jsStr.get("state"))||"6".equals(jsStr.get("state"))) {
					od.setState(3);
					od.update();
				}
			}
		}
		renderJson();
	}
	public void getorder() {
		int id=getParaToInt("id");
		OrderWwbz od=new OrderWwbz().dao().findById(id);
		setAttr("express",od.getExpress());
		renderJson();
	}
	public void apply() {
		String openid=getPara("openid");
		UserWwbz user=new UserWwbz().dao().findFirst("SELECT * FROM user_wwbz where openid = ? and state=0",openid);
		if(null!=user&&user.getJoin()!=2) {
			user.setJoin(1);
			if(user.update()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}else {
			setAttr("result",0);
		}
		renderJson();
	}
	public void applystate() {
		String openid=getPara("openid");
		UserWwbz user=new UserWwbz().dao().findFirst("SELECT * FROM user_wwbz where openid = ? and state=0",openid);
		if(null!=user) {
			setAttr("state",user.getJoin());
			if(user.getJoin()==2) {
				setAttr("img",user.getQrcode());
				List<UserWwbz> ul=new UserWwbz().dao().find("SELECT * FROM user_wwbz where `join` =2 ORDER BY qrcodetime");
				int number=1;
				for (UserWwbz userWwbz : ul) {
					if(userWwbz.getOpenid().equals(openid)) {
						break;
					}
					number++;
				}
				setAttr("number",number);
			}
		}else {
			setAttr("state",0);
		}
		renderJson();
	}
	public void invite() {
		String openid=getPara("openid");
		int fatherid=getParaToInt("fatherid");
		int addtimes=getParaToInt("addtimes");
		UserWwbz my=new UserWwbz().dao().findFirst("SELECT * FROM user_wwbz where openid = ?",openid);
		UserWwbz father=new UserWwbz().dao().findById(fatherid);
		DealerWwbz dr=new DealerWwbz().dao().findFirst("SELECT * FROM dealer_wwbz where states=0 and state=1 and openid=?",father.getOpenid());
		if(null!=my&&null!=father&&null==my.getFatheropenid()&&null!=dr) {
			if(openid.equals(father.getOpenid())) {
				setAttr("result",0);
				System.out.println("openid相同");
			}else {
				if("oayw34wTAXTFs--UM9LhVthzT31M".equals(father.getOpenid())) {
					setAttr("result",0);
				}else {
					if(my.getIsnew()==0) {
						setAttr("result",0);
						System.out.println("没有绑定资格");
					}else {
						my.setFatheropenid(father.getOpenid());
						if(my.update()) {
							setAttr("result",1);
						}else {
							setAttr("result",0);
							System.out.println("更新不成功");
						}
					}
					
				}
				
			}
			
		}else {
			setAttr("result",0);
			System.out.println("条件不满足");
			System.out.println(null!=my);
			System.out.println(null!=father);
			System.out.println(null==my.getFatheropenid());
			System.out.println(null!=dr);
		}
		if(addtimes==1) {
			if(null!=my&&null!=father) {
				if(openid.equals(father.getOpenid())) {
					
				}else {
					if(my.getIsnewreward()==1) {
						father.setRewardtimes(father.getRewardtimes()+1);
						if(father.update()) {
							RewardcontactWwbz rc=new RewardcontactWwbz();
							rc.setOpenid(openid);
							rc.setFatheropenid(father.getOpenid());
							rc.setTime(new Date());
							rc.save();
							my.setIsnewreward(0);
							my.update();
						}
					}
				}
				
				
			}
		}
		
		renderJson();
	}
	public void archives() {
		String openid=getPara("openid");
		ArchivesWwbz ac=new ArchivesWwbz().dao().findFirst("SELECT * FROM archives_wwbz where openid =? and states=0",openid);
		
		if(null!=ac) {
			String list=ac.getNewill();
			String med=ac.getMedicine();
			String pic=ac.getPic();
			JSONArray illlist = new JSONArray().parseArray(list);
			JSONArray medlist = new JSONArray().parseArray(med);
			JSONArray piclist = new JSONArray().parseArray(pic);
			setAttr("illlist",illlist);
			setAttr("medlist",medlist);
			setAttr("piclist",piclist);
			setAttr("result",1);
			setAttr("archives",ac);
		}else {
			setAttr("result",0);
		}
		renderJson();
	}public void uploadIcon() {
		System.out.println("uploadIcon");
		UploadFile uploadFile = getFile();// 在磁盘上保存文件
		if (null != uploadFile) {
			String fileName = uploadFile.getFileName();// 获取保存文件的文件名
			String fileNamePath = uploadFile.getUploadPath();
			String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length());
			fileName = System.currentTimeMillis() + fileType;
			File img = new File(fileNamePath + "/" + fileName);
			if (img.exists()) {
				img.delete();
			}
			System.out.println("****"+fileNamePath);
			System.out.println("****"+fileName);
			boolean flag = uploadFile.getFile().renameTo(img);
			if (flag) {
				setAttr("msg", "0");
				setAttr("path", "icon/" + fileName);
			} else {
				setAttr("msg", "上传失败！");
			}
		} else {
			setAttr("msg", "没有获取到上传文件！");
		}
		renderJson();
	}
	public void savearchives() {
		String openid=getPara("openid");
		String wxicon=getPara("wxicon");
		String name=getPara("name");
		int age=getParaToInt("age");
		int sex=getParaToInt("sex");
		String weight=getPara("weight");
		String low=getPara("low");
		String high=getPara("high");
		String tel=getPara("tel");
		int share=getParaToInt("share");
		int id=getParaToInt("id");
		if(id==0) {
			ArchivesWwbz nac=new ArchivesWwbz();
			nac.setOpenid(openid);
			nac.setWxicon(wxicon);
			nac.setName(name);
			nac.setAge(age);
			nac.setSex(sex);
			nac.setWeight(weight);
			nac.setLow(low);
			nac.setHigh(high);
			nac.setTel(tel);
			nac.setShare(share);
			nac.setTime(new Date());
			nac.setState(0);
			nac.setStates(0);
			List<ArchivesWwbz> al=new ArchivesWwbz().dao().find("SELECT * from archives_wwbz");
			nac.setOrder(21);
			if(nac.save()) {
				setAttr("result",1);
				UserWwbz user=new UserWwbz().dao().findFirst("SELECT * from user_wwbz where openid =? and state=0",openid);
				RewardWwbz re=new RewardWwbz().dao().findById(1);
				if(null!=user) {
					if(share==1) {
						user.setReward(Integer.parseInt(user.getReward())+re.getArchives()+re.getShare()+"");
					}else {
						user.setReward(Integer.parseInt(user.getReward())+re.getArchives()+"");
					}
					
					if(user.update()) {
						OrderWwbz jfod=new OrderWwbz();
	    				jfod.setMoney(re.getArchives()+"");
	    				jfod.setBuytime(new Date());
	    				jfod.setDirection(1);
	    				jfod.setKind(1);
	    				jfod.setOpenid(openid);
	    				jfod.setState(3);
	    				jfod.setOutTradeNo(new Date().getTime()+"");
	    				jfod.setGoodsid(0);
	    				jfod.setNumber(1);
	    				jfod.setSheng("");
	    				jfod.setShi("");
	    				jfod.setQu("");
	    				jfod.setAddress("");
	    				jfod.setTel("");
	    				jfod.setName("");
	    				jfod.setGoodsname("建立档案奖励积分");
	    				jfod.save();
	    				if(share==1) {
	    					OrderWwbz order=new OrderWwbz();
	    					order.setMoney(re.getShare()+"");
	    					order.setBuytime(new Date());
	    					order.setDirection(1);
	    					order.setKind(1);
	    					order.setOpenid(openid);
	    					order.setState(3);
	    					order.setOutTradeNo(new Date().getTime()+"");
	    					order.setGoodsid(0);
	    					order.setNumber(1);
	    					order.setSheng("");
	    					order.setShi("");
	    					order.setQu("");
	    					order.setAddress("");
	    					order.setTel("");
	    					order.setName("");
	    					order.setGoodsname("分享档案奖励积分");
	    					order.save();
	    				}
	    				
					}
					
					
				}
			}else {
				setAttr("result",0);
			}
		}else {
			ArchivesWwbz ac=new ArchivesWwbz().findById(id);
			ac.setOpenid(openid);
			ac.setWxicon(wxicon);
			ac.setName(name);
			ac.setAge(age);
			ac.setSex(sex);
			ac.setWeight(weight);
			ac.setLow(low);
			ac.setHigh(high);
			ac.setTel(tel);
			ac.setShare(share);
			ac.setTime(new Date());
			ac.setState(0);
			if(ac.update()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}
		renderJson();
	}
	public void changeshare() {
		String openid=getPara("openid");
		int share=getParaToInt("share");
		ArchivesWwbz ac=new ArchivesWwbz().dao().findFirst("SELECT * FROM archives_wwbz where openid =? and states=0",openid);
		if(null!=ac) {
			UserWwbz user=new UserWwbz().dao().findFirst("SELECT * from user_wwbz where openid =? and state=0",openid);
			RewardWwbz re=new RewardWwbz().dao().findById(1);
			if(null!=user) {
				if(share==1) {
					user.setReward(Integer.parseInt(user.getReward())+re.getShare()+"");
				}else {
					user.setReward(Integer.parseInt(user.getReward())-re.getShare()+"");
				}
				if(user.update()) {
					OrderWwbz jfod=new OrderWwbz();
    				jfod.setMoney(re.getShare()+"");
    				jfod.setBuytime(new Date());
    				if(share==1) {
    					jfod.setDirection(1);
    				}else {
    					jfod.setDirection(0);
    				}
    				jfod.setKind(1);
    				jfod.setOpenid(openid);
    				jfod.setState(3);
    				jfod.setOutTradeNo(new Date().getTime()+"");
    				jfod.setGoodsid(0);
    				jfod.setNumber(1);
    				jfod.setSheng("");
    				jfod.setShi("");
    				jfod.setQu("");
    				jfod.setAddress("");
    				jfod.setTel("");
    				jfod.setName("");
    				if(share==1) {
    					jfod.setGoodsname("健康档案分享积分");
    				}else {
    					jfod.setGoodsname("移除健康档案分享积分");
    				}
    				jfod.save();
				}
				
				
			}
			ac.setShare(share);
			if(ac.getState()==2&&share==0) {
				ac.setState(1);
			}else if(ac.getState()==1&&share==1) {
				ac.setState(2);
				BasicWwbz bc=new BasicWwbz().findById(1);
				message.message(bc.getTel(), "有新的健康档案需要审核！");
			}else if(ac.getState()==3&&share==0) {
				ac.setState(1);
			}else if(ac.getState()==4&&share==0) {
				ac.setState(1);
			}
//			if(ac.getState()!=0&&ac.getState()!=-1&&ac.getState()!=2) {
//				if(share==1) {
//					ac.setState(4);
//				}else {
//					ac.setState(1);
//				}
//			}
			if(ac.update()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}else {
			setAttr("result",0);
		}
		renderJson();
	}
	public void saveill() {
		String list=getPara("list");
		String openid=getPara("openid");
		System.out.println("list="+list);
		ArchivesWwbz ac=new ArchivesWwbz().dao().findFirst("SELECT * FROM archives_wwbz where openid =? and states=0",openid);
		if(null!=ac) {
			ac.setIllness(list);
			if(ac.update()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}else {
			setAttr("result",0);
		}
		renderJson();
	}
	public void savemed() {
		String list=getPara("list");
		String openid=getPara("openid");
		System.out.println("list="+list);
		ArchivesWwbz ac=new ArchivesWwbz().dao().findFirst("SELECT * FROM archives_wwbz where openid =? and states=0",openid);
		if(null!=ac) {
			ac.setMedicine(list);
			if(ac.update()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}else {
			setAttr("result",0);
		}
		renderJson();
	}
	public void savext() {
		String fpg=getPara("fpg");
		String vpg=getPara("vpg");
		String hbaic=getPara("hbaic");
		String openid=getPara("openid");;
		ArchivesWwbz ac=new ArchivesWwbz().dao().findFirst("SELECT * FROM archives_wwbz where openid =? and states=0",openid);
		if(null!=ac) {
			ac.setFpg(fpg);
			ac.setVpg(vpg);
			ac.setHbaic(hbaic);
			if(ac.update()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}else {
			setAttr("result",0);
		}
		renderJson();
	}
	public void savepic() {
		String list=getPara("list");
		String openid=getPara("openid");
		System.out.println("list="+list);
		ArchivesWwbz ac=new ArchivesWwbz().dao().findFirst("SELECT * FROM archives_wwbz where openid =? and states=0",openid);
		if(null!=ac) {
			ac.setPic(list);
			if(ac.getState()==-1) {
				
			}else {
				ac.setState(1);
			}
			if(ac.update()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}else {
			setAttr("result",0);
		}
		renderJson();
	}
	public void addfeed() {
		String openid=getPara("openid");
		String fpg=getPara("fpg");
		String vpg=getPara("vpg");
		String hbaic=getPara("hbaic");
		String pic=getPara("pic");
		String des=getPara("des");
		String low=getPara("low");
		String high=getPara("high");
		String start=getPara("start");
		String end=getPara("end");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		FeedWwbz fd=new FeedWwbz().dao().findFirst("SELECT * FROM feed_wwbz WHERE openid =? and time =?",openid,format.format(new Date()));
		if(null!=fd) {
			FeedWwbz fd2=new FeedWwbz();
			fd2.setOpenid(openid);
			fd2.setFpg(fpg);
			fd2.setVpg(vpg);
			fd2.setHbaic(hbaic);
			fd2.setPic(pic);
			fd2.setDes(des);
			fd2.setLow(low);
			fd2.setHigh(high);
			fd2.setTime(new Date());
			fd2.setStates(0);
			fd2.setStart(start);
			fd2.setEnd(end);
			if(fd2.save()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
			
		}else {
			FeedWwbz fd1=new FeedWwbz();
			fd1.setOpenid(openid);
			fd1.setFpg(fpg);
			fd1.setVpg(vpg);
			fd1.setHbaic(hbaic);
			fd1.setPic(pic);
			fd1.setDes(des);
			fd1.setLow(low);
			fd1.setHigh(high);
			fd1.setTime(new Date());
			fd1.setStates(0);
			fd1.setStart(start);
			fd1.setEnd(end);
			if(fd1.save()) {
				setAttr("result",1);
				setAttr("new",1);
				UserWwbz user=new UserWwbz().dao().findFirst("SELECT * from user_wwbz where openid =? and state=0",openid);
				RewardWwbz re=new RewardWwbz().dao().findById(1);
				if(null!=user) {
					user.setReward(Integer.parseInt(user.getReward())+re.getDay()+"");
					if(user.update()) {
						OrderWwbz jfod=new OrderWwbz();
	    				jfod.setMoney(re.getDay()+"");
	    				jfod.setBuytime(new Date());
	    				jfod.setDirection(1);
	    				jfod.setKind(1);
	    				jfod.setOpenid(openid);
	    				jfod.setState(3);
	    				jfod.setOutTradeNo(new Date().getTime()+"");
	    				jfod.setGoodsid(0);
	    				jfod.setNumber(1);
	    				jfod.setSheng("");
	    				jfod.setShi("");
	    				jfod.setQu("");
	    				jfod.setAddress("");
	    				jfod.setTel("");
	    				jfod.setName("");
	    				jfod.setGoodsname("每日血糖奖励");
	    				jfod.save();
	    				
	    				
					}
					
					
				}
				
			}else {
				setAttr("result",0);
			}
		}
		renderJson();
	}
	
	public void feed() {
		String openid=getPara("openid");
		List<FeedWwbz> fl=new FeedWwbz().dao().find("SELECT * FROM feed_wwbz where openid=? ORDER BY time DESC",openid);
		List<Record> list=Db.find("SELECT count(*) count,max(vpg) maxv,max(fpg) maxf,a.time from feed_wwbz a  where openid=? GROUP BY time ORDER BY time DESC",openid);
		setAttr("list",fl);
		setAttr("count",list);
		renderJson();
		
	}
	public void changestate() {
		String openid=getPara("openid");
		ArchivesWwbz ac=new ArchivesWwbz().dao().findFirst("SELECT * FROM archives_wwbz where openid= ? and states=0",openid);
		if(null!=ac) {
			ac.setState(ac.getShare()+1);
//			System.out.println(ac.getState());
//			System.out.println(ac.getState()!=-1);
//			if(ac.getState()==-1) {
//				ac.setState(2);
//			}else {
//				ac.setState(4);
//			}
			if(ac.update()) {
				setAttr("result",1);
				if(ac.getState()==2) {
					BasicWwbz bc=new BasicWwbz().findById(1);
					message.message(bc.getTel(), "有新的健康档案需要审核！");
				}
			}else {
				setAttr("result",0);
			}
		}else {
			setAttr("result",0);
		}
		renderJson();
	}
	public void indexlist() {
		List<ArchivesWwbz> al=new ArchivesWwbz().dao().find("SELECT * FROM archives_wwbz where state=4 and states=0 ORDER BY `order`,time DESC");
		setAttr("list",al);
		renderJson();
	}
	public void sharelist() {
		List<ArchivesWwbz> al=new ArchivesWwbz().dao().find("SELECT * FROM archives_wwbz where state>2 and states=0 ORDER BY `order`,time DESC");
		setAttr("list",al);
		renderJson();
	}
	public void banner() {
		List<BannerWwbz> bl=new BannerWwbz().dao().find("SELECT * FROM banner_wwbz where  states=0");
		setAttr("list",bl);
		renderJson();
	} 
	public void indexlistp() {
		int pagesize=getParaToInt("pagesize");
		int pageindex=getParaToInt("pageindex");
		//List<ArchivesWwbz> al=new ArchivesWwbz().dao().find("SELECT * FROM archives_wwbz where state=4 and states=0 ORDER BY `order`,time DESC");
		Page<ArchivesWwbz> al=new ArchivesWwbz().dao().paginate(pageindex, pagesize, "SELECT *", "FROM archives_wwbz where state=4 and states=0 ORDER BY `order`,time DESC");
		setAttr("list",al);
		renderJson();
	}
	public void sharelistp() {
		int pagesize=getParaToInt("pagesize");
		int pageindex=getParaToInt("pageindex");
		//List<ArchivesWwbz> al=new ArchivesWwbz().dao().find("SELECT * FROM archives_wwbz where state=4 and states=0 ORDER BY `order`,time DESC");
		Page<ArchivesWwbz> al=new ArchivesWwbz().dao().paginate(pageindex, pagesize, "SELECT *", "FROM archives_wwbz where state>2 and states=0 ORDER BY `order`,time DESC");
		setAttr("list",al);
		renderJson();
	}
	public void categroylist() {
		List<CategoryWwbz> cl=new CategoryWwbz().dao().find("select * from category_wwbz where states=0");
		setAttr("list",cl);
		renderJson();
	}
	public void savearchivesnew() {
		String openid=getPara("openid");
		String wxicon=getPara("wxicon");
		String name=getPara("name");
		int height=getParaToInt("height");
		int sex=getParaToInt("sex");
		String weight=getPara("weight");
		String nation=getPara("nation");
		Date birthday=getParaToDate("birthday");
		String tel=getPara("tel");
		int share=getParaToInt("share");
		int id=getParaToInt("id");
		if(id==0) {
			ArchivesWwbz nac=new ArchivesWwbz();
			nac.setOpenid(openid);
			nac.setWxicon(wxicon);
			nac.setName(name);
			nac.setNation(nation);
			nac.setSex(sex);
			nac.setWeight(weight);
			nac.setHeight(height);
			nac.setBirthday(birthday);
			nac.setTel(tel);
			nac.setShare(share);
			nac.setTime(new Date());
			nac.setState(0);
			nac.setStates(0);
			//List<ArchivesWwbz> al=new ArchivesWwbz().dao().find("SELECT * from archives_wwbz");
			nac.setOrder(21);
			if(nac.save()) {
				setAttr("result",1);
				UserWwbz user=new UserWwbz().dao().findFirst("SELECT * from user_wwbz where openid =? and state=0",openid);
				RewardWwbz re=new RewardWwbz().dao().findById(1);
				if(null!=user) {
					if(share==1) {
						user.setReward(Integer.parseInt(user.getReward())+re.getArchives()+re.getShare()+"");
					}else {
						user.setReward(Integer.parseInt(user.getReward())+re.getArchives()+"");
					}
					
					if(user.update()) {
						OrderWwbz jfod=new OrderWwbz();
	    				jfod.setMoney(re.getArchives()+"");
	    				jfod.setBuytime(new Date());
	    				jfod.setDirection(1);
	    				jfod.setKind(1);
	    				jfod.setOpenid(openid);
	    				jfod.setState(3);
	    				jfod.setOutTradeNo(new Date().getTime()+"");
	    				jfod.setGoodsid(0);
	    				jfod.setNumber(1);
	    				jfod.setSheng("");
	    				jfod.setShi("");
	    				jfod.setQu("");
	    				jfod.setAddress("");
	    				jfod.setTel("");
	    				jfod.setName("");
	    				jfod.setGoodsname("建立档案奖励积分");
	    				jfod.save();
	    				if(share==1) {
	    					OrderWwbz order=new OrderWwbz();
	    					order.setMoney(re.getShare()+"");
	    					order.setBuytime(new Date());
	    					order.setDirection(1);
	    					order.setKind(1);
	    					order.setOpenid(openid);
	    					order.setState(3);
	    					order.setOutTradeNo(new Date().getTime()+"");
	    					order.setGoodsid(0);
	    					order.setNumber(1);
	    					order.setSheng("");
	    					order.setShi("");
	    					order.setQu("");
	    					order.setAddress("");
	    					order.setTel("");
	    					order.setName("");
	    					order.setGoodsname("分享档案奖励积分");
	    					order.save();
	    				}
	    				
					}
					
					
				}
			}else {
				setAttr("result",0);
			}
		}else {
			ArchivesWwbz ac=new ArchivesWwbz().findById(id);
			ac.setOpenid(openid);
			ac.setWxicon(wxicon);
			ac.setName(name);
			ac.setNation(nation);
			ac.setSex(sex);
			ac.setWeight(weight);
			ac.setHeight(height);
			ac.setBirthday(birthday);
			ac.setTel(tel);
			ac.setShare(share);
			ac.setTime(new Date());
			if(ac.getState()==-1) {
				ac.setState(-1);
			}else {
				ac.setState(0);
			}
			
			if(ac.update()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}
		renderJson();
	}
	public void saveillnew() {
		String list=getPara("list");
		String openid=getPara("openid");
		System.out.println("list="+list);
		ArchivesWwbz ac=new ArchivesWwbz().dao().findFirst("SELECT * FROM archives_wwbz where openid =? and states=0",openid);
		if(null!=ac) {
			ac.setNewill(list);
			if(ac.update()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}else {
			setAttr("result",0);
		}
		renderJson();
	}
	public void savextnew() {
		String fpg=getPara("fpg");
		String vpg=getPara("vpg");
		String low=getPara("low");
		String high=getPara("high");
		String openid=getPara("openid");;
		ArchivesWwbz ac=new ArchivesWwbz().dao().findFirst("SELECT * FROM archives_wwbz where openid =? and states=0",openid);
		if(null!=ac) {
			ac.setFpg(fpg);
			ac.setVpg(vpg);
			ac.setLow(low);
			ac.setHigh(high);
			if(ac.update()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}else {
			setAttr("result",0);
		}
		renderJson();
	}
	public void savedes() {
		String des=getPara("des");
		String openid=getPara("openid");
		ArchivesWwbz ac=new ArchivesWwbz().dao().findFirst("SELECT * FROM archives_wwbz where openid =? and states=0",openid);
		if(null!=ac) {
			ac.setDescribe(des);
			if(ac.update()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}else {
			setAttr("result",0);
		}
		renderJson();
	}
	public void rewardcheck() {
		String openid=getPara("openid");
		int total=getParaToInt("total");
		UserWwbz user=new UserWwbz().dao().findFirst("select * from user_wwbz where openid=? and state=0",openid);
		if(null!=user) {
			if(Integer.parseInt(user.getReward())>=total) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}else {
			setAttr("result",0);
		}
		renderJson();
	}
	public void dealer() {
		String openid=getPara("openid");
		DealerWwbz dl=new DealerWwbz().dao().findFirst("SELECT * FROM dealer_wwbz where states=0 and openid= ?",openid);
		List<CooperateWwbz> crl=new CooperateWwbz().dao().find("SELECT * from cooperate_wwbz where states=0");
		setAttr("list",crl);
		if(null!=dl) {
			if(dl.getState()==0) {
				setAttr("result",1);
				setAttr("dl",dl);
			}else if(dl.getState()==3){
				setAttr("result",0);
			}else {
				List<DealerWwbz> drl=new DealerWwbz().dao().find("SELECT * from dealer_wwbz where states=0 and state=1 ORDER BY time");
				int number=1;
				for (DealerWwbz dealerWwbz : drl) {
					if(dealerWwbz.getOpenid().equals(openid)) {
						break;
					}
					number++;
				}
				setAttr("number",number);
				setAttr("dl",dl);
				JSONArray piclist = new JSONArray().parseArray(dl.getPic());
				setAttr("piclist",piclist);
				if(dl.getAccount().equals("0")) {
					
					setAttr("result",2);
					
				}else {
					List<AllotWwbz> atl=new AllotWwbz().dao().find("select * from allot_wwbz where states=0");
					int percent=0;
					for (AllotWwbz allotWwbz : atl) {
						System.out.println("计算="+dl.getUnliquidated());
						System.out.println(allotWwbz.getEnd());
						System.out.println("档位="+allotWwbz.getEnd()*10000);
						
						if(dl.getUnliquidated()<allotWwbz.getEnd()*10000) {
							percent=allotWwbz.getPercent();
							break;
						}
					}
					setAttr("money",dl.getUnliquidated()*percent/100);
					setAttr("result",3);
				}
				
			}
		}else {
			setAttr("result",0);
		}
		renderJson();
	}
	public void applycontent() {
		List<QuestionnaireWwbz> ql=new QuestionnaireWwbz().dao().find("SELECT * from questionnaire_wwbz where states=0 and parentid=0 and paperid=1 ORDER BY `order`");
		List<QuestionnaireWwbz> al=new QuestionnaireWwbz().dao().find("SELECT * from questionnaire_wwbz where states=0 and paperid=1 ORDER BY `order`");
		List<CooperateWwbz> crl=new CooperateWwbz().dao().find("SELECT * from cooperate_wwbz where states=0");
		setAttr("ql",ql);
		setAttr("al",al);
		setAttr("crl",crl);
		renderJson();
	}
	public void dealerapply() {
		String kind=getPara("kind");
		String openid=getPara("openid");
		String policy=getPara("policy");
		int money=getParaToInt("money");
		String name=getPara("name");
		int sex=getParaToInt("sex");
		int age=getParaToInt("age");
		int hospital=getParaToInt("hospital");
		int head=getParaToInt("head");
		String percent=getPara("percent");
		String tel=getPara("tel");
		String wechat=getPara("wechat");
		String city=getPara("city");
		String province=getPara("province");
		int forecast=getParaToInt("forecast");
		String address=getPara("address");
		String latitude=getPara("latitude");
		String longitude=getPara("longitude");
		String des=getPara("des");
		String pic=getPara("pic");
		String answer=getPara("answer");
		DealerWwbz dl=new DealerWwbz();
		List<DealerWwbz> dll=new DealerWwbz().dao().find("SELECT * from dealer_wwbz where state=1 and states=0");
		UserWwbz user=new UserWwbz().dao().findFirst("SELECT * from user_wwbz WHERE openid = ? and state=0",openid);
		DealerWwbz dls=new DealerWwbz().dao().findFirst("SELECT * from dealer_wwbz where openid = ?  and states=0",openid);
		List<DealerWwbz> dtel=new DealerWwbz().dao().find("SELECT * FROM dealer_wwbz where tel= ? and state!=3",tel);
		if(dtel.size()==0) {
			if(null==dls) {
				dl.setOpenid(openid);
				if(null!=user) {
					dl.setUnionid(user.getUnionid());
				}
				dl.setPassword(tel.substring(5));
				dl.setStates(0);
				if(0==money) {
					dl.setState(0);
				}else {
					dl.setState(3);
				}
				dl.setIshospital(hospital);
				dl.setIshead(head);
				dl.setHeadpercent(Double.parseDouble(percent));
				dl.setKind(kind);
				dl.setPolicy(policy);
				dl.setPay(money);
				dl.setAnswer(answer);
				dl.setName(name);
				dl.setAge(age);
				dl.setSex(sex);
				dl.setTel(tel);
				dl.setWechat(wechat);
				dl.setCity(city);
				dl.setProvince(province);
				dl.setForecast(forecast);
				dl.setDes(des);
				dl.setPic(pic);
				dl.setStoretel(tel);
				dl.setStart("08:00");
				dl.setEnd("17:00");
				/*if(dll.size()<10) {
					dl.setStorename("无微不治"+city+"服务中心No.00"+(dll.size()+1));
				}else if(dll.size()>=10&&dll.size()<100) {
					dl.setStorename("无微不治"+city+"服务中心No.0"+(dll.size()+1));
				}else if(dll.size()>=100&&dll.size()<1000) {
					dl.setStorename("无微不治"+city+"服务中心No."+(dll.size()+1));
				}
				*/
				dl.setStoreaddress(address);
				dl.setLongitude(longitude);
				dl.setLatitude(latitude);
				dl.setIsshow(0);
				if(dl.save()) {
					setAttr("result",1);
					setAttr("id",dl.getId());
				}else {
					setAttr("result",0);
				}
			}else {
				dls.setOpenid(openid);
				if(null!=user) {
					dls.setUnionid(user.getUnionid());
				}
				dls.setPassword(tel.substring(5));
				dls.setStates(0);
				if(0==money) {
					dls.setState(0);
				}else {
					dls.setState(3);
				}
				dls.setIshospital(hospital);
				dls.setIshead(head);
				dls.setHeadpercent(Double.parseDouble(percent));
				dls.setKind(kind);
				dls.setPolicy(policy);
				dls.setPay(money);
				dls.setAnswer(answer);
				dls.setName(name);
				dls.setAge(age);
				dls.setSex(sex);
				dls.setTel(tel);
				dls.setWechat(wechat);
				dls.setCity(city);
				dls.setProvince(province);
				dls.setForecast(forecast);
				dls.setDes(des);
				dls.setPic(pic);
				dls.setStoretel(tel);
				dls.setStart("08:00");
				dls.setEnd("17:00");
//				if(dll.size()<10) {
//					dls.setStorename("无微不治"+city+"服务中心No.00"+(dll.size()+1));
//				}else if(dll.size()>=10&&dll.size()<100) {
//					dls.setStorename("无微不治"+city+"服务中心No.0"+(dll.size()+1));
//				}else if(dll.size()>=100&&dll.size()<1000) {
//					dls.setStorename("无微不治"+city+"服务中心No."+(dll.size()+1));
//				}
				
				dls.setStoreaddress(address);
				dls.setLongitude(longitude);
				dls.setLatitude(latitude);
				dls.setIsshow(0);
				if(dls.update()) {
					setAttr("result",1);
					setAttr("id",dl.getId());
				}else {
					setAttr("result",0);
				}
			}
		}else {
			setAttr("result",3);
		}
		
		
		
		renderJson();
	}
	
	public void bank() {
		int id=getParaToInt("id");
		String account=getPara("account");
		String bank=getPara("bank");
		String branch=getPara("branch");
		String name=getPara("name");
		DealerWwbz dl=new DealerWwbz().findById(id);
		if(null!=dl) {
			dl.setAccount(account);
			dl.setBank(bank);
			dl.setBankname(name);
			dl.setBranch(branch);
			if(dl.update()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}else {
			setAttr("result",0);
		}
		renderJson();
	}
	public void allotdetail() {
		String openid=getPara("openid");
		List<DivideWwbz> dil=new DivideWwbz().dao().find("SELECT * from divide_wwbz where openid=?",openid);
		Date time = null;
		if(dil.size()==0) {
			List<Record> list=Db.find("SELECT a.*,b.`name` goodsname FROM order_wwbz a LEFT JOIN goods_wwbz b on a.goodsid=b.id where a.state>0  and a.direction=0 and a.fatheropenid= ?",openid);
			setAttr("list",list);
			System.out.println("list="+list);
		}else {
			for (DivideWwbz divideWwbz : dil) {
				time=divideWwbz.getTime();
			}
			List<Record> list=Db.find("SELECT a.*,b.`name` goodsname FROM order_wwbz a LEFT JOIN goods_wwbz b on a.goodsid=b.id where a.state>0  and a.direction=0 and a.fatheropenid= ? and a.buytime> ?",openid,time);
			setAttr("list",list);
			System.out.println("list="+list);
		}
		
		renderJson();
	}
	public void divide() {
		String openid=getPara("openid");
		List<DivideWwbz> dil=new DivideWwbz().dao().find("SELECT * from divide_wwbz where openid = ?",openid);
		setAttr("list",dil);
		renderJson();
	}
	public void comment() {
		String des=getPara("des");
		String openid=getPara("openid");
		int goodsid=getParaToInt("goodsid");
		int pstar=getParaToInt("pstar");
		int bstar=getParaToInt("bstar");
		int tstar=getParaToInt("tstar");
		int orderid=getParaToInt("orderid");
		
		
			CommentWwbz ct=new CommentWwbz();
			ct.setTime(new Date());
			ct.setOpenid(openid);
			ct.setContent(des);
			ct.setPstar(pstar);
			ct.setBstar(bstar);
			ct.setTstar(tstar);
			ct.setIsnew(0);
			ct.setGoodsid(goodsid);
			ct.setStates(0);
			ct.setFathercommentid(0);
			UserWwbz user=new UserWwbz().dao().findFirst("SELECT * FROM user_wwbz where openid= ? and  state=0",openid);
			if(null!=user&&null!=user.getUnionid()) {
				ct.setNickname(user.getNickname());
				ct.setWxicon(user.getWxicon());
				if(ct.save()) {
					setAttr("result",1);
					OrderWwbz od=new OrderWwbz().findById(orderid);
					if(null!=od) {
						od.setIscomment(1);
						od.update();
					}
				}else {
					setAttr("result",0);
				}
			}else {
				setAttr("result",0);
			}
		
		renderJson();
	}
	public void commentnew() {
		String openid=getPara("openid");
		
		List<CommentWwbz> ctl=new CommentWwbz().dao().find("SELECT * FROM comment_wwbz where openid = ? and isnew=1 and states=0",openid);
		if(ctl.size()==0) {
			setAttr("new",0);
		}else {
			setAttr("new",1);
		}
		renderJson();
	}
	public void changenew() {
		String openid=getPara("openid");
		List<CommentWwbz> ctl=new CommentWwbz().dao().find("SELECT * FROM comment_wwbz where openid = ? and isnew=1 and states=0",openid);
		for (CommentWwbz commentWwbz : ctl) {
			commentWwbz.setIsnew(0);
			commentWwbz.update();
		}
		renderJson();
	}
	public void commentlist() {
		String openid=getPara("openid");
		List<Record> commentm=Db.find("SELECT a.*,b.name FROM comment_wwbz a LEFT JOIN goods_wwbz b on a.goodsid=b.id where a.openid = ? and a.fathercommentid=0 and a.states=0",openid);
		List<CommentWwbz> commenta=new CommentWwbz().dao().find("SELECT * FROM comment_wwbz where states=0");
		setAttr("commentm",commentm);
		setAttr("commenta",commenta);
		renderJson();
	}
	public void replycomment() {
		String openid=getPara("openid");
		String tonickname=getPara("tonickname");
		int fathercommentid=getParaToInt("fathercommentid");
		int goodsid=getParaToInt("goodsid");
		String comment=getPara("comment");
		CommentWwbz ct=new CommentWwbz();
		ct.setTime(new Date());
		ct.setContent(comment);
		ct.setOpenid(openid);
		ct.setGoodsid(goodsid);
		ct.setStates(0);
		UserWwbz user=new UserWwbz().dao().findFirst("select * from user_wwbz where openid = ? and state=0",openid);
		if(null!=user) {
			ct.setNickname(user.getNickname());
			ct.setWxicon(user.getWxicon());
		}
		ct.setTonickname(tonickname);
		ct.setFathercommentid(fathercommentid);
		if(ct.save()) {
			setAttr("result",1);
		}else {
			setAttr("result",0);
		}
		renderJson();
	}
	public void replycontent() {
		int commentid=getParaToInt("commentid");
		Record ct=Db.findFirst("SELECT a.*,b.name FROM comment_wwbz a LEFT JOIN goods_wwbz b on a.goodsid=b.id where a.id = ? and a.states=0",commentid);
		setAttr("comment",ct);
		List<CommentWwbz> ctl=new CommentWwbz().dao().find("select * from comment_wwbz where fathercommentid=? and states=0",commentid);
		setAttr("list",ctl);
		renderJson();
	}
	public void basic() {
		BasicWwbz basic=new BasicWwbz().findById(1);
		setAttr("basic",basic);
		renderJson();
	}
	public void checktel() {
		String tel=getPara("tel");
		List<DealerWwbz> dl=new DealerWwbz().dao().find("SELECT * FROM dealer_wwbz where tel= ? and state!=3",tel);
		if(dl.size()==0) {
			setAttr("result",1);
		}else {
			setAttr("result",0);
		}
		renderJson();
	}
	public void dealerlocal() {
		List<DealerWwbz> plist=new DealerWwbz().dao().find("SELECT count(*),a.* from dealer_wwbz a where states=0 and state=1 GROUP BY province ");
		String province="";
		for (DealerWwbz dealerWwbz : plist) {
			province=dealerWwbz.getProvince();
			break;
		}
		List<DealerWwbz> clist=new DealerWwbz().dao().find("SELECT * from dealer_wwbz where states=0 and state=1  and province = ?",province);
		setAttr("sheng",plist);
		setAttr("shi",clist);
		renderJson();
	}
	public void getcity() {
		String province=getPara("province");
		List<DealerWwbz> clist=new DealerWwbz().dao().find("SELECT * from dealer_wwbz where states=0 and state=1  and province = ? GROUP BY city",province);
		setAttr("shi",clist);
		renderJson();
	}
	public void paypass() {
		String openid=getPara("openid");
		DealerWwbz dl=new DealerWwbz().dao().findFirst("select * from dealer_wwbz where openid= ? and state=3",openid);
		if(null!=dl) {
			dl.setState(0);
			if(dl.update()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}else {
			setAttr("result",0);
		}
		renderJson();
	}
	public void paycancel() {
		String openid=getPara("openid");
		DealerWwbz dl=new DealerWwbz().dao().findFirst("select * from dealer_wwbz where openid= ? and state=3",openid);
		if(null!=dl) {
			if(dl.delete()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}else {
			setAttr("result",1);
		}
		renderJson();
	}
	public void getsotreid() {
		String openid=getPara("openid");
		DealerWwbz dl=new DealerWwbz().dao().findFirst("select * from dealer_wwbz where openid= ? and states=0 and state=1",openid);
		if(null!=dl) {
			setAttr("id",dl.getId());
		}else {
			setAttr("id",0);
		}
		renderJson();
	}
	public void updatedealer() {
		int id=getParaToInt("id");
		String logo=getPara("logo");
		String tel=getPara("tel");
		String start=getPara("start");
		String end=getPara("end");
		String latitude=getPara("latitude");
		String longitude=getPara("longitude");
		String address=getPara("address");
		String des=getPara("des");
		String pic=getPara("pic");
		String proclaim=getPara("proclaim");
		DealerWwbz dl=new DealerWwbz().findById(id);
		if(null!=dl) {
			dl.setLogo(logo);
			dl.setStoretel(tel);
			dl.setStart(start);
			dl.setEnd(end);
			dl.setLatitude(latitude);
			dl.setLongitude(longitude);
			dl.setStoreaddress(address);
			dl.setDes(des);
			dl.setPic(pic);
			dl.setProclaim(proclaim);
			if(dl.update()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}else {
			setAttr("result",0);
		}
		renderJson();
	}
	public void getdealer() {
		int id=getParaToInt("id");
		DealerWwbz dl=new DealerWwbz().findById(id);
		BasicWwbz ba=new BasicWwbz().findById(1);
		UserWwbz user=new UserWwbz().dao().findFirst("SELECT * from user_wwbz where state=0 and openid =?",dl.getOpenid());
		List<Record> dnl=Db.find("SELECT a.*,b.wxicon from donate_wwbz a LEFT JOIN user_wwbz b on a.openid=b.openid where a.fatheropenid =? ORDER BY a.time desc",dl.getOpenid());
		int t=0;
		for (Record record : dnl) {
			t+=record.getInt("share");
		}
		if(null!=dl) {
			List<OrderWwbz> ol=new OrderWwbz().find("SELECT * from order_wwbz where fatheropenid=? and state!=0 and kind=0",dl.getOpenid());
			Double total=0.0;
			for (OrderWwbz orderWwbz : ol) {
				total+=Double.parseDouble(orderWwbz.getMoney());
			}
			setAttr("total",total);
			setAttr("number",ol.size());
			setAttr("dl",dl);
			JSONArray pic = new JSONArray().parseArray(dl.getPic());
			setAttr("pic",pic);
			setAttr("img",ba.getDealerimg());
			setAttr("word",ba.getDealerword());
			setAttr("headimg",user.getWxicon());
			setAttr("nickname",user.getNickname());
			setAttr("dnl",dnl);
			setAttr("pn",dnl.size());
			setAttr("tw",t);
		}else {
			
		}
		renderJson();
	}
	public void getdealerlist() {
		int id=getParaToInt("id");
		BasicWwbz ba=new BasicWwbz().findById(1);
		UserWwbz my=new UserWwbz().findById(id);
		List<DealerWwbz> dl=new DealerWwbz().dao().find("SELECT * from dealer_wwbz where states=0 and state=1 order by rand()");
		//List<DealerWwbz> dl=new DealerWwbz().dao().find("SELECT * from dealer_wwbz where states=0 and state=1");
		setAttr("myicon",my.getWxicon());
		for (DealerWwbz dealerWwbz : dl) {
			List<OrderWwbz> ol=new OrderWwbz().find("SELECT * from order_wwbz where fatheropenid=? and state!=0 and kind=0",dealerWwbz.getOpenid());
			UserWwbz father=new UserWwbz().dao().findFirst("SELECT * from user_wwbz where state=0 and openid =?",dealerWwbz.getOpenid());
			List<Record> dnl=Db.find("SELECT a.*,b.wxicon from donate_wwbz a LEFT JOIN user_wwbz b on a.openid=b.openid where a.fatheropenid =? ORDER BY a.time desc",dealerWwbz.getOpenid());
			List<Record> dnlx=Db.find("SELECT b.wxicon from donate_wwbz a LEFT JOIN user_wwbz b on a.openid=b.openid where a.fatheropenid =? ORDER BY a.time desc",dealerWwbz.getOpenid());
			int t=0;
			for (Record record : dnl) {
				t+=record.getInt("share");
			}
			Double total=0.0;
			for (OrderWwbz orderWwbz : ol) {
				total+=Double.parseDouble(orderWwbz.getMoney());
			}
			dealerWwbz.setAnswer(total+"");
			dealerWwbz.setAge(ol.size());
			dealerWwbz.setBank(ba.getDealerimg());
			dealerWwbz.setBankname(ba.getDealerword());
			dealerWwbz.setBranch(father.getWxicon());
			dealerWwbz.setCity(father.getNickname());
			dealerWwbz.setDes(dnlx+"");
			dealerWwbz.setEnd(dnl.size()+"");
			dealerWwbz.setForecast(t);
			
		}
		if(null!=my&&null!=my.getFatheropenid()) {
			UserWwbz father=new UserWwbz().dao().findFirst("SELECT * from user_wwbz where state=0 and openid =?",my.getFatheropenid());
			DealerWwbz dealer=new DealerWwbz().findFirst("SELECT * from dealer_wwbz where states=0 and state=1 and openid=?",my.getFatheropenid());
			if(null!=dealer) {
				setAttr("result",1);
				List<Record> dnl=Db.find("SELECT a.*,b.wxicon from donate_wwbz a LEFT JOIN user_wwbz b on a.openid=b.openid where a.fatheropenid =? ORDER BY a.time desc",my.getFatheropenid());
				int t=0;
				for (Record record : dnl) {
					t+=record.getInt("share");
				}
				List<OrderWwbz> ol=new OrderWwbz().find("SELECT * from order_wwbz where fatheropenid=? and state!=0 and kind=0",my.getFatheropenid());
				Double total=0.0;
				for (OrderWwbz orderWwbz : ol) {
					total+=Double.parseDouble(orderWwbz.getMoney());
				}
				setAttr("total",total);
				setAttr("number",ol.size());
				setAttr("dl",dealer);
				JSONArray pic = new JSONArray().parseArray(dealer.getPic());
				setAttr("pic",pic);
				setAttr("img",ba.getDealerimg());
				setAttr("word",ba.getDealerword());
				setAttr("headimg",father.getWxicon());
				setAttr("nickname",father.getNickname());
				setAttr("dnl",dnl);
				setAttr("pn",dnl.size());
				setAttr("tw",t);
				setAttr("dllist",dl);
			}else {
				setAttr("result",0);
				setAttr("dllist",dl);
			}
		}else {
			setAttr("result",0);
			setAttr("dllist",dl);
		}
		renderJson();
	}
	public void diabetesask() {
		List<QuestionnaireWwbz> ql=new QuestionnaireWwbz().dao().find("SELECT * from questionnaire_wwbz where states=0 and parentid=0 and paperid=2 ORDER BY `order`");
		List<QuestionnaireWwbz> al=new QuestionnaireWwbz().dao().find("SELECT * from questionnaire_wwbz where states=0 and paperid=2 ORDER BY `order`");
		setAttr("ql",ql);
		setAttr("al",al);
		renderJson();
	}
	public void diabetesfound() {
		String openid= getPara("openid");
		String name= getPara("name");
		String tel= getPara("tel");
		String age= getPara("age");
		Date date= getParaToDate("date");
		GlucometerWwbz gl=new GlucometerWwbz().dao().findFirst("SELECT * from glucometer_wwbz where openid=? and fatheropenid=''",openid);
		if(null==gl) {
			GlucometerWwbz gln=new GlucometerWwbz();
			gln.setOpenid(openid);
			gln.setName(name);
			gln.setTel(tel);
			gln.setAge(age);
			gln.setIll(date);
			gln.setFatheropenid("");
			if(gln.save()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}else {
			gl.setOpenid(openid);
			gl.setName(name);
			gl.setTel(tel);
			gl.setAge(age);
			gl.setIll(date);
			if(gl.update()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}
		renderJson();
	}
	public void diabetesanswer() {
		String openid=getPara("openid");
		int score=getParaToInt("score");
		GlucometerWwbz gl=new GlucometerWwbz().dao().findFirst("SELECT * from glucometer_wwbz where openid=? and fatheropenid=''",openid);
		if(null!=gl) {
			gl.setScore(score);
			gl.setState(1);
			if(gl.update()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}else {
			setAttr("result",0);
		}
		renderJson();
	}
	public void getdiabetes() {
		String openid=getPara("openid");
		GlucometerWwbz gl=new GlucometerWwbz().dao().findFirst("SELECT * from glucometer_wwbz where openid=? and fatheropenid=''",openid);
		if(null!=gl) {
			setAttr("score",gl.getScore());
			setAttr("myid",gl.getId());
			setAttr("state",gl.getState());
		}else {
			setAttr("score",0);
			setAttr("myid",0);
			setAttr("state",0);
		}
		List<GlucometerWwbz> gll=new GlucometerWwbz().dao().find("SELECT * from glucometer_wwbz where fatheropenid=?",openid);
		setAttr("list",gll);
		
		List<GlucometerWwbz> count=new GlucometerWwbz().dao().find("SELECT * from glucometer_wwbz where state>2");
		setAttr("number",100-count.size());
		renderJson();
	}
	public void bf() {
		String openid=getPara("openid");
		String nickname=getPara("nickname");
		String wxicon=getPara("wxicon");
		int score=getParaToInt("score");
		int fatherid=getParaToInt("fatherid");
		GlucometerWwbz gl=new GlucometerWwbz().findById(fatherid);
		if(null!=gl) {
			GlucometerWwbz glm=new GlucometerWwbz().dao().findFirst("SELECT * from glucometer_wwbz where openid=? and fatheropenid!=''",openid);
			if(null!=glm) {
				setAttr("result",2);
			}else {
				GlucometerWwbz gll=new GlucometerWwbz();
				gll.setOpenid(openid);
				gll.setScore(score);
				gll.setWxicon(wxicon);
				gll.setNickname(nickname);
				gll.setFatheropenid(gl.getOpenid());
				if(gll.save()) {
					gl.setScore(gl.getScore()+score);
					if(gl.getScore()>=100) {
						if(gl.getState()==1) {
							message.message(gl.getTel(), "您参加的活动已满足要求，请前往查看！");
						}
						gl.setState(2);
					}
					
					if(gl.update()) {
						setAttr("result",1);
					}else {
						setAttr("result",3);
					}
				}else {
					setAttr("result",3);
				}
			}
		}else {
			setAttr("result",3);
		}
		renderJson();
	}
	public void getdiabetesopenid() {
		int id=getParaToInt("id");
		GlucometerWwbz gl=new GlucometerWwbz().findById(id);
		if(null!=gl) {
			setAttr("openid",gl.getOpenid());
		}else {
			setAttr("openid",0);
		}
		renderJson();
	}
	public void getmyid() {
		String openid=getPara("openid");
		GlucometerWwbz gl=new GlucometerWwbz().dao().findFirst("SELECT * from glucometer_wwbz where openid=? and fatheropenid=''",openid);
		if(null!=gl) {
			setAttr("myid",gl.getId());
		}else {
			setAttr("myid",0);
		}
		renderJson();
	}
	public void buydiabetes() {
		String openid=getPara("openid");
		String name=getPara("name");
		String tel=getPara("tel");
		String area=getPara("city");
		String address=getPara("address");
		String money=getPara("money");
		GlucometerWwbz gl=new GlucometerWwbz().dao().findFirst("SELECT * from glucometer_wwbz where openid=? and fatheropenid=''",openid);
		List<GlucometerWwbz> count=new GlucometerWwbz().dao().find("SELECT * from glucometer_wwbz where state>2");
		if(null!=gl) {
			if(count.size()>=100) {
				setAttr("result",2);
			}else {
				gl.setName(name);
				gl.setTel(tel);
				gl.setArea(area);
				gl.setAddress(address);
				gl.setState(3);
				if(Double.parseDouble(money)==0) {
					
				}else {
					gl.setPaper(1);
				}
				if(gl.update()) {
					setAttr("result",1);
				}else {
					setAttr("result",0);
				}
			}
			
		}else {
			setAttr("result",0);
		}
		renderJson();
	}
	public void oldconmment() {
		String name=getPara("name");
		String tel=getPara("tel");
		String city=getPara("city");
		String ill=getPara("ill");
		int cl=getParaToInt("cl");
		int jt=getParaToInt("jt");
		int dw=getParaToInt("dw");
		int nd=getParaToInt("nd");
		String time=getPara("time");
		String pj=getPara("pj");
		String pic=getPara("pic");
		String headimg=getPara("headimg");
		String openid=getPara("openid");
		CommentWwbz ct=new CommentWwbz().dao().findFirst("SELECT * from comment_wwbz where fathercommentid=0 and states<-1 and openid= ?",openid);
		if(null==ct) {
			CommentWwbz ctn=new CommentWwbz();
			ctn.setNickname(name);
			ctn.setTel(tel);
			ctn.setCity(city);
			ctn.setIll(ill);
			ctn.setCl(cl);
			ctn.setJt(jt);
			ctn.setDw(dw);
			ctn.setNd(nd);
			ctn.setHistory(time);
			ctn.setContent(pj);
			ctn.setPic(pic);
			ctn.setWxicon(headimg);
			ctn.setOpenid(openid);
			ctn.setStates(-3);
			ctn.setFathercommentid(0);
			ctn.setTime(new Date());
			if(ctn.save()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}else {
			setAttr("result",2);
		}
		renderJson();
	}
	public void getoldconmment() {
		String openid=getPara("openid");
		CommentWwbz ct=new CommentWwbz().dao().findFirst("SELECT * from comment_wwbz where fathercommentid=0 and states<-1 and openid= ?",openid);
		if(null!=ct) {
			setAttr("comment",ct);
			setAttr("result",1);
			JSONArray pic = new JSONArray().parseArray(ct.getPic());
			setAttr("pic",pic);
		}else {
			setAttr("result",0);
		}
		renderJson();
	}
	public void commentlistp() {
		int pagesize=getParaToInt("pagesize");
		int pageindex=getParaToInt("pageindex");
		int id=getParaToInt("id");
		//List<ArchivesWwbz> al=new ArchivesWwbz().dao().find("SELECT * FROM archives_wwbz where state=4 and states=0 ORDER BY `order`,time DESC");
		Page<CommentWwbz> al=new CommentWwbz().dao().paginate(pageindex, pagesize, "SELECT *", "from comment_wwbz where states=0 and goodsid= ? and fathercommentid=0 ORDER BY time desc",id);
		
		List<CommentWwbz> cta=new CommentWwbz().dao().find("SELECT * from comment_wwbz where states=0 and goodsid= ? ORDER BY time desc",id);
		//List<UserWwbz> userlist=new UserWwbz().dao().find("select * from user_wwbz");
		//setAttr("user",userlist);
		setAttr("list",al);
		setAttr("commenta",cta);
		renderJson();
	}
	public void invitation() {
		String openid=getPara("openid");
		String name=getPara("name");
		String tel=getPara("tel");
		String company=getPara("company");
		String post=getPara("post");
		InvitationWwbz it=new InvitationWwbz().dao().findFirst("SELECT * from invitation_wwbz where states=0 and openid=?",openid);
		if(null==it) {
			InvitationWwbz itn=new InvitationWwbz();
			itn.setOpenid(openid);
			itn.setName(name);
			itn.setTel(tel);
			itn.setCompany(company);
			itn.setActivity("郑州创建中原");
			itn.setStates(0);
			itn.setPost(post);
			itn.setTime(new Date());
			itn.setPay(200+"");
			if(itn.save()) {
				setAttr("result",1);
			}else {
				
			}
		}else {
			setAttr("result",0);
		}
		renderJson();
	}
	public void invitationnumber() {
		String openid=getPara("openid");
		InvitationWwbz it=new InvitationWwbz().dao().findFirst("SELECT * from invitation_wwbz where states=0 and openid=?",openid);
		setAttr("it",it);
		renderJson();
	}
	public void getinvitation() {
		String openid=getPara("openid");
		InvitationWwbz it=new InvitationWwbz().dao().findFirst("SELECT * from invitation_wwbz where states=0 and openid=?",openid);
		if(null!=it) {
			setAttr("result",1);
		}else {
			setAttr("result",0);
		}
		renderJson();
	}
	public void dealerupdate() {
		int id=getParaToInt("id");
		DealerWwbz dl=new DealerWwbz().findById(id);
		if(null!=dl) {
			dl.setIsapply(1);
			if(dl.update()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}else {
			setAttr("result",0);
		}
		renderJson();
	}
	public void tree() {
		String openid=getPara("openid");
		UserWwbz user=new UserWwbz().dao().findFirst("SELECT * from user_wwbz where state=0 and openid =?",openid);
		List<UserWwbz> pl=new UserWwbz().dao().find("SELECT * from user_wwbz where state=0 and isreward=1");
		List<OrderWwbz> od=new OrderWwbz().dao().find("SELECT * from order_wwbz where goodsid= -1");
		int total=0;
		for (OrderWwbz orderWwbz : od) {
			total+=Integer.parseInt(orderWwbz.getMoney());
		}
		RewardWwbz re=new RewardWwbz().dao().findById(1);
		setAttr("reward",re);
		setAttr("people",pl.size());
		setAttr("total",total);
		setAttr("times",user.getRewardtimes());
		if(null==user.getWxicon()) {
			setAttr("identity",0);
		}else {
			setAttr("identity",1);
		}
		
		renderJson();
	}
	public void shark() {
		String openid=getPara("openid");
		UserWwbz user=new UserWwbz().dao().findFirst("SELECT * from user_wwbz where state=0 and openid =?",openid);
		RewardWwbz reward=new RewardWwbz().findById(1);
		if(user.getRewardtimes()>0) {	
			int r=0;
			if(user.getIsreward()==0) {
				r=(int)(reward.getNewlow()+Math.random()*(reward.getNewhigh()-reward.getNewlow()+1));
				user.setIsreward(1);
			}else {
				r=(int)(reward.getOldlow()+Math.random()*(reward.getOldhigh()-reward.getOldlow()+1));
			}
			user.setReward((Integer.parseInt(user.getReward())+r)+"");
			user.setRewardtimes(user.getRewardtimes()-1);
			if(user.update()) {
				setAttr("result",1);
				setAttr("reward",r);
				setAttr("tt",user.getReward());
				OrderWwbz order =new OrderWwbz();
				order.setMoney(r+"");
				order.setBuytime(new Date());
				order.setOpenid(openid);
				order.setState(3);
				order.setOutTradeNo(new Date().getTime()+"");
				order.setGoodsid(-1);
				order.setNumber(1);
				order.setSheng("");
				order.setShi("");
				order.setQu("");
				order.setAddress("");
				order.setTel("");
				order.setName("");
				order.setKind(1);
				order.setDirection(1);
				order.setGoodsname("五福豆摇一摇");
				order.save();
				
			}else {
				setAttr("result",0);
			}
		}else {
			setAttr("result",0);
		}
		renderJson();
	}
	public void isnew() {
		String openid=getPara("openid");
		UserWwbz user=new UserWwbz().dao().findFirst("SELECT * from user_wwbz where state=0 and openid=?",openid);
		if(null!=user) {
			if(user.getLock()==1) {
				user.setIsnew(0);
				if(user.update()) {
					setAttr("result",1);
				}else {
					setAttr("result",0);
				}
			}else {
				user.setIsnew(1);
				if(user.update()) {
					setAttr("result",1);
				}else {
					setAttr("result",0);
				}
			}
			
		}else {
			setAttr("result",0);
		}
		renderJson();
	}
	public void futian() {
		String openid=getPara("openid");
		List<Record> list=Db.find("SELECT c.*,d.kind,d.state from (SELECT a.*,b.wxicon,b.nickname,b.reward from rewardcontact_wwbz a LEFT JOIN user_wwbz b on a.openid=b.openid where a.fatheropenid=? and b.wxicon is not null ) c LEFT JOIN dealer_wwbz d on c.openid=d.openid ORDER BY c.time DESC",openid);
		setAttr("list",list);
		renderJson();
	}
	public void rewardactivate() {
		String openid=getPara("openid");
		UserWwbz user=new UserWwbz().dao().findFirst("SELECT * from user_wwbz where state=0 and openid=?",openid);
		if(null!=user) {
			if(null==user.getTel()) {
				setAttr("result",2);
			}else {
				setAttr("result",1);
			}
		}else {
			setAttr("result",0);
		}
		renderJson();
	}
	public void code() {
		String tel=getPara("tel");
		String rm=random.get6Radom();
		message.message(tel, rm+"请妥善保存您的验证码");
		setAttr("ryz",rm);
		renderJson();
	}
	public void updatetel() {
		String openid=getPara("openid");
		String tel=getPara("tel");
		UserWwbz user=new UserWwbz().dao().findFirst("SELECT * from user_wwbz where state=0 and openid=?",openid);
		if(null!=user) {
			user.setTel(tel);
			if(user.update()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}else {
			setAttr("result",0);
		}
		renderJson();
	}
	public void rewardshare() {
		String openid=getPara("openid");
		int share=getParaToInt("share");
		OfferWwbz offer=new OfferWwbz();
		offer.setOpenid(openid);
		offer.setShare(share);
		offer.setTime(new Date());
		
		
		
		if(offer.save()) {
			setAttr("result",1);
			UserWwbz user=new UserWwbz().dao().findFirst("SELECT * from user_wwbz where state=0 and openid=?",openid);
			user.setReward((Integer.parseInt(user.getReward())-share)+"");
			user.update();
			OrderWwbz order=new OrderWwbz();
			order.setMoney(share+"");
			order.setBuytime(new Date());
			order.setOpenid(openid);
			order.setState(3);
			order.setOutTradeNo(new Date().getTime()+"");
			order.setGoodsid(0);
			order.setNumber(1);
			order.setSheng("");
			order.setShi("");
			order.setQu("");
			order.setAddress("");
			order.setTel("");
			order.setName("");
			order.setKind(1);
			order.setDirection(0);
			order.setGoodsname("爱心捐助");
			order.save();
		}else {
			setAttr("result",0);
		}
		renderJson();
	}
	public void helplist() {
		List<Record> list=Db.find("SELECT a.* ,b.wxicon from offer_wwbz a LEFT JOIN user_wwbz b on a.openid=b.openid where b.state=0 ORDER BY time DESC");
		setAttr("list",list);
		setAttr("number",list.size());
		int total=0;
		for (Record record : list) {
			total+=record.getInt("share");
		}
		setAttr("total",total);
		renderJson();
	}
	public void sharetotal() {
		String openid=getPara("openid");
		int total=0;
		List<OfferWwbz> ol=new OfferWwbz().dao().find("SELECT * from offer_wwbz where openid=?",openid);
		for (OfferWwbz offerWwbz : ol) {
			total+=offerWwbz.getShare();
		}
		setAttr("total",total);
		renderJson();
	}
	public void donate() {
		String openid=getPara("openid");
		String fatheropenid=getPara("fatheropenid");
		int sharenumber=getParaToInt("sharenumber");
		UserWwbz user=new UserWwbz().dao().findFirst("SELECT * from user_wwbz where state=0 and openid=?",openid);
		UserWwbz father=new UserWwbz().dao().findFirst("SELECT * from user_wwbz where state=0 and openid=?",fatheropenid);
		if(null!=user&&null!=father) {
			if(openid.endsWith(fatheropenid)) {
				setAttr("result",0);
			}else {
				if(Integer.parseInt(user.getReward())>=sharenumber) {
					user.setReward((Integer.parseInt(user.getReward())-sharenumber)+"");
					if(user.update()) {
						father.setReward((Integer.parseInt(father.getReward())+sharenumber)+"");
						father.update();
						OrderWwbz om=new OrderWwbz();
						om.setMoney(sharenumber+"");
						om.setBuytime(new Date());
						om.setOpenid(openid);
						om.setState(3);
						om.setOutTradeNo(new Date().getTime()+"");
						om.setGoodsid(0);
						om.setNumber(1);
						om.setSheng("");
						om.setShi("");
						om.setQu("");
						om.setAddress("");
						om.setTel("");
						om.setName("");
						om.setKind(1);
						om.setDirection(0);
						om.setGoodsname("捐赠经销商");
						OrderWwbz of=new OrderWwbz();
						of.setMoney(sharenumber+"");
						of.setBuytime(new Date());
						of.setOpenid(fatheropenid);
						of.setState(3);
						of.setOutTradeNo(new Date().getTime()+"");
						of.setGoodsid(0);
						of.setNumber(1);
						of.setSheng("");
						of.setShi("");
						of.setQu("");
						of.setAddress("");
						of.setTel("");
						of.setName("");
						of.setKind(1);
						of.setDirection(1);
						of.setGoodsname("获得捐赠");
						if(om.save()&&of.save()) {
							setAttr("result",1);
							DonateWwbz da=new DonateWwbz();
							da.setOpenid(openid);
							da.setFatheropenid(fatheropenid);
							da.setShare(sharenumber);
							da.setTime(new Date());
							da.save();
						}else {
							setAttr("result",0);
						}
					}else {
						setAttr("result",0);
					}
					
				}else {
					setAttr("result",2);
				}
			}
			
		}else {
			setAttr("result",0);
		}
		renderJson();
	}
	public void dealercheck() {
		String openid=getPara("openid");
		List<OrderWwbz> ol=new OrderWwbz().dao().find("SELECT * from order_wwbz where openid=? and state!=0 and kind=0 and direction=0",openid);
		Double total=0.0;
		for (OrderWwbz orderWwbz : ol) {
			total+=Double.parseDouble(orderWwbz.getMoney());
		}
		System.out.println("total="+total);
		if(total>=2000) {
			setAttr("result",1);
			setAttr("count",total);
		}else {
			setAttr("result",0);
			setAttr("count",String.format("%.2f",total));
		}
		renderJson();
	}
	public void dealerlist() {
		String openid=getPara("openid");
		List<Record> list=Db.find("SELECT a.kind,a.name,a.city,a.id,b.wxicon from dealer_wwbz a LEFT JOIN user_wwbz b on a.openid=b.openid where a.states=0 and a.state=1");
		setAttr("list",list);
		UserWwbz user=new UserWwbz().dao().findFirst("SELECT * from user_wwbz where state=0 and openid=?",openid);
		if(null!=user.getFatheropenid()) {
			Record dl=Db.findFirst("SELECT a.kind,a.name,a.city,a.id,b.wxicon from dealer_wwbz a LEFT JOIN user_wwbz b on a.openid=b.openid where a.states=0 and a.state=1 and a.openid=?",user.getFatheropenid());
			setAttr("result",1);
			setAttr("dl",dl);
		}else {
			setAttr("result",0);
		}
		renderJson();
	}
	public void rewardinit() {
		String openid=getPara("openid");
		UserWwbz user=new UserWwbz().dao().findFirst("SELECT * from user_wwbz where state=0 and openid=?",openid);
		setAttr("total",user.getReward());
		List<TransactionWwbz> tl=new TransactionWwbz().find("SELECT * from transaction_wwbz where openid=? and states=0 and state =2",openid);
		Double total=0.0;
		for (TransactionWwbz transactionWwbz : tl) {
			total+=Double.parseDouble(transactionWwbz.getMoney());
		}
		setAttr("get",total);
		List<TransactionWwbz> ulist=new TransactionWwbz().find("SELECT * from transaction_wwbz where openid=? and states=0 and state =0",openid);
		List<TransactionWwbz> slist=new TransactionWwbz().find("SELECT * from transaction_wwbz where openid=? and states=0 and state =2",openid);
		setAttr("ulist",ulist); 
		setAttr("slist",slist);
		renderJson();
	}
	public void rewardup() {
		String openid=getPara("openid");
		int total=getParaToInt("total");
		String money=getPara("money");
		UserWwbz user=new UserWwbz().dao().findFirst("SELECT * from user_wwbz where state=0 and openid=?",openid);
		int reward=Integer.parseInt(user.getReward());
		if(reward>=total) {
			user.setReward((reward-total)+"");
			if(user.update()) {
				setAttr("result",1);
				OrderWwbz om=new OrderWwbz();
				om.setMoney(total+"");
				om.setBuytime(new Date());
				om.setOpenid(openid);
				om.setState(3);
				om.setOutTradeNo(new Date().getTime()+"");
				om.setGoodsid(0);
				om.setNumber(1);
				om.setSheng("");
				om.setShi("");
				om.setQu("");
				om.setAddress("");
				om.setTel("");
				om.setName("");
				om.setKind(1);
				om.setDirection(0);
				om.setGoodsname("五福豆上架");
				om.save();
				TransactionWwbz ts=new TransactionWwbz();
				ts.setOpenid(openid);
				ts.setState(0);
				ts.setMoney(money);
				ts.setNumber(total+"");
				ts.setStates(0);
				ts.setUptime(new Date());
				ts.save();
			}else {
				setAttr("result",0);
			}
		}else {
			setAttr("result",0);
		}
		renderJson();
	}
	public void rewardcx() {
		int id=getParaToInt("id");
		TransactionWwbz ts=new TransactionWwbz().findById(id);
		ts.setStates(-1);
		if(ts.update()) {
			setAttr("result",1);
		}else {
			setAttr("result",0);
		}
		renderJson();
	}
	public void  salelist() {
		List<TransactionWwbz> list=new TransactionWwbz().find("SELECT * from transaction_wwbz  where states=0 and state =0 ORDER BY CAST(number AS SIGNED),CAST(money AS SIGNED)");
		setAttr("list",list);
		renderJson();
	}
	public void getreward() {
		int id=getParaToInt("id");
		String openid=getPara("openid");
		TransactionWwbz ts=new TransactionWwbz().findById(id);
		if(ts.getOpenid().equals(openid)) {
			setAttr("result",2);
		}else {
			if(ts.getState()!=0) {
				setAttr("result",3);
			}else {
				ts.setState(1);
				if(ts.update()) {
					setAttr("result",1);
				}else {
					setAttr("result",0);
				}
			}
			
		}
		
		renderJson();
	}
	public void givereward() {
		int id=getParaToInt("id");
		TransactionWwbz ts=new TransactionWwbz().findById(id);
		ts.setState(0);
		if(ts.update()) {
			setAttr("result",1);
		}else {
			setAttr("result",0);
		}
		renderJson();
	}
	public void rewardsuccess() {
		String openid=getPara("openid");
		int id=getParaToInt("id");
		TransactionWwbz ts=new TransactionWwbz().findById(id);
		ts.setBuyopenid(openid);
		ts.setState(2);
		ts.setBuytime(new Date());
		if(ts.update()) {
			UserWwbz user=new UserWwbz().dao().findFirst("SELECT * from user_wwbz where state=0 and openid=?",openid);
			user.setReward((Integer.parseInt(user.getReward())+Integer.parseInt(ts.getNumber()))+"");
			UserWwbz fuser=new UserWwbz().dao().findFirst("SELECT * from user_wwbz where state=0 and openid=?",ts.getOpenid());
			fuser.setMoney((Double.parseDouble(fuser.getMoney())+Double.parseDouble(ts.getMoney()))+"");
			if(user.update()&&fuser.update()) {
				MoneyWwbz money=new MoneyWwbz();
				money.setOpenid(ts.getOpenid());
				money.setMoney(ts.getMoney());
				money.setTime(new Date());
				money.setStates(0);
				money.setContent("出售微金豆");
				money.setDirection(0);
				OrderWwbz om=new OrderWwbz();
				om.setMoney(ts.getNumber()+"");
				om.setBuytime(new Date());
				om.setOpenid(openid);
				om.setState(3);
				om.setOutTradeNo(new Date().getTime()+"");
				om.setGoodsid(0);
				om.setNumber(1);
				om.setSheng("");
				om.setShi("");
				om.setQu("");
				om.setAddress("");
				om.setTel("");
				om.setName("");
				om.setKind(1);
				om.setDirection(1);
				om.setGoodsname("购买五福豆");
				om.save();
				if(money.save()) {
					setAttr("result",1);
				}else{
					setAttr("result",0);
				}
			}else {
				setAttr("result",0);
			}
			
		}else {
			setAttr("result",0);
		}
		renderJson();
	}
	public void ml() {
		String openid=getPara("openid");
		List<MoneyWwbz> ml=new MoneyWwbz().dao().find("SELECT * from money_wwbz where states=0 and openid=? ORDER BY time desc",openid);
		UserWwbz user=new UserWwbz().dao().findFirst("SELECT * from user_wwbz where state=0 and openid=?",openid);
		setAttr("money",user.getMoney());
		setAttr("list",ml);
		renderJson();
	}
	public void yecheck() {
		String openid=getPara("openid");
		UserWwbz user=new UserWwbz().dao().findFirst("SELECT * from user_wwbz where state=0 and openid=?",openid);
		if(Double.parseDouble(user.getMoney())!=0) {
			setAttr("result",1);
			setAttr("money",Double.parseDouble(user.getMoney()));
		}else {
			setAttr("result",0);
			setAttr("money",0);
		}
		renderJson();
	}
	public void yepay() {
		String openid=getPara("openid");
		String ye=getPara("ye");
		UserWwbz user=new UserWwbz().dao().findFirst("SELECT * from user_wwbz where state=0 and openid=?",openid);
		if(Double.parseDouble(user.getMoney())>=Double.parseDouble(ye)) {
			user.setMoney((Double.parseDouble(user.getMoney())-Double.parseDouble(ye))+"");
			if(user.update()) {
				setAttr("result",1);
				MoneyWwbz my=new MoneyWwbz();
				my.setContent("余额抵现");
				my.setDirection(1);
				my.setMoney(ye);
				my.setOpenid(openid);
				my.setStates(0);
				my.setTime(new Date());
				my.save();
			}else {
				setAttr("result",0);
			}
		}else {
			setAttr("result",0);
		}
		renderJson();
	}
	public void bargainlist() {
		List<Record> rl=Db.find("SELECT a.*,b.`name` goodsname,b.price,b.imgf from bargaingoods_wwbz a LEFT JOIN goods_wwbz b on a.goodsid=b.id where a.states =0");
		setAttr("list",rl);
		renderJson();
	}
	public void bargaincontent() {
		int id=getParaToInt("id");
		BargaingoodsWwbz bg=new BargaingoodsWwbz().findById(id);
		if(bg.getEndtime().after(new Date())&&bg.getStates()==0) {
			setAttr("result",1);
			Record re=Db.findFirst("SELECT a.*,b.imgf,b.diagram,b.standard,b.`name` goodsname,b.price from bargaingoods_wwbz a LEFT JOIN goods_wwbz b on a.goodsid=b.id where a.id=? and a.states=0",id);
			setAttr("content",re);
		}else {
			setAttr("result",0);
		}
		renderJson();
	}
	public void mybargain() {
		String openid=getPara("openid");
		int id=getParaToInt("id");
		List<BargainorderWwbz> bgl=new BargainorderWwbz().dao().find("SELECT * from bargainorder_wwbz where openid=? and fid=0 and bargainid=? and states=0 ORDER BY time DESC",openid,id);
		if(bgl.size()==0) {
			setAttr("result",1);
		}else {
			if(bgl.get(0).getState()==0) {
				setAttr("fid",bgl.get(0).getId());
				setAttr("result",2);
				setAttr("price",bgl.get(0).getPrice());
			}else if(bgl.get(0).getState()==1) {
				setAttr("fid",bgl.get(0).getId());
				setAttr("result",3);
			}else if(bgl.get(0).getState()==2) {
				setAttr("fid",bgl.get(0).getId());
				setAttr("result",4);
			}
		}
		renderJson();
	}
	public void newbargain() {
		String openid=getPara("openid");
		String money=getPara("money");
        String goodsname=getPara("goodsname");
        String tel=getPara("tel");
        String address=getPara("address");
        String area=getPara("area");
        String name=getPara("name");
        int id =getParaToInt("id");
        String price=getPara("price");
        BargainorderWwbz bg=new BargainorderWwbz();
        bg.setOpenid(openid);
        bg.setFid(0);
        bg.setMoney(money);
        bg.setTime(new Date());
        bg.setArea(area);
        bg.setAddress(address);
        bg.setName(name);
        bg.setTel(tel);
        bg.setStates(0);
        bg.setState(0);
        bg.setGoodsname(goodsname);
        bg.setBargainid(id);
        bg.setPrice(price);
        if(bg.save()) {
        	setAttr("result",1);
        }else {
        	setAttr("result",0);
        }
		renderJson();
	}
	public void getopenid() {
		int fid=getParaToInt("fid");
		BargainorderWwbz bg=new BargainorderWwbz().findById(fid);
		setAttr("openid",bg.getOpenid());
		renderJson();
	}
	public void otherbargain() {
		String openid=getPara("openid");
		int fid=getParaToInt("fid");
		BargainorderWwbz bg=new BargainorderWwbz().findById(fid);
		List<BargainorderWwbz> bgl=new BargainorderWwbz().dao().find("SELECT * from bargainorder_wwbz where openid=? and fid=? and states=0 ",openid,fid);
		if(bgl.size()==0) {
			if(bg.getState()!=0) {
				setAttr("result",2);
				setAttr("price",bg.getPrice());
			}else {
				setAttr("result",0);
				setAttr("price",bg.getPrice());
			}
		}else {
			setAttr("result",1);
			setAttr("price",bg.getPrice());
		}
		renderJson();
	}
	public void paycheck() {
		int fid=getParaToInt("fid");
		BargainorderWwbz bg=new BargainorderWwbz().findById(fid);
		if(bg.getState()==1) {
			setAttr("result",1);
		}else {
			setAttr("result",0);
		}
		renderJson();
	}
	public void bargain() {
		String openid=getPara("openid");
		int fid=getParaToInt("fid");
		BargainorderWwbz bo=new BargainorderWwbz().findById(fid);
		BargaingoodsWwbz bg=new BargaingoodsWwbz().findById(bo.getBargainid());
		int money=(int)(bg.getLow()+Math.random()*(bg.getHigh()-bg.getLow()+1));
		int om=(int)(bg.getOlow()+Math.random()*(bg.getOhigh()-bg.getOlow()+1));
		BargainorderWwbz nbo=new BargainorderWwbz();
		List<Record> bol=Db.find("SELECT a.*,b.openid fopenid from bargainorder_wwbz a LEFT JOIN bargainorder_wwbz b on a.fid=b.id where a.states=0 and a.fid!=0 and a.openid=? and b.openid=?",openid,bo.getOpenid());
		System.out.println("bol.size()="+bol.size());
		nbo.setOpenid(openid);
		nbo.setFid(fid);
		nbo.setMoney(0+"");
		nbo.setTime(new Date());
		nbo.setStates(0);
		nbo.setState(3);
		nbo.setGoodsname(bo.getGoodsname());
		nbo.setBargainid(bo.getBargainid());
		if(bol.size()==0) {
			nbo.setPrice(money+"");
			System.out.println("money="+money);
		}else {
			nbo.setPrice(om+"");
			System.out.println("om="+om);
		}
		
		if(nbo.save()) {
			setAttr("result",1);
			if(bo.getState()==0) {
				if(bol.size()==0) {
					bo.setPrice((Double.parseDouble(bo.getPrice())-money)+"");
				}else {
					bo.setPrice((Double.parseDouble(bo.getPrice())-om)+"");
				}
				if(Double.parseDouble(bo.getPrice())<=Double.parseDouble(bo.getMoney())) {
					bo.setState(1);
					message.message(bo.getTel(), "您想要的"+bo.getGoodsname()+"已被砍到底价，马上去付款吧！");
				}else {
					//bo.setState(0);
				}
				bo.update();
			}else {
				
			}
			
		}else {
			setAttr("result",0);
		}
		renderJson();
	}
	public void bargainpay() {
		int fid=getParaToInt("fid");
		BargainorderWwbz bo=new BargainorderWwbz().findById(fid);
		bo.setState(2);
		bo.setTime(new Date());
		if(bo.update()) {
			BargaingoodsWwbz bg=new BargaingoodsWwbz().findById(bo.getBargainid());
			bg.setSale(bg.getSale()+1);
			bg.update();
		}else {
			
		}
		renderJson();
	}
	public void getbargainlist() {
		String openid=getPara("openid");
		List<BargainorderWwbz> bol=new BargainorderWwbz().dao().find("SELECT * from bargainorder_wwbz where fid=0 and states=0 and openid=?",openid);
		setAttr("list",bol);
		renderJson();
	}
	public void changebargain() {
    	String tel=getPara("tel");
    	String address=getPara("address");
    	String area=getPara("area");
    	String name=getPara("name");
    	int id =getParaToInt("id");
    	BargainorderWwbz bo=new BargainorderWwbz().findById(id);
    	bo.setTel(tel);
    	bo.setAddress(address);
    	bo.setArea(area);
    	bo.setName(name);
    	if(bo.update()) {
    		setAttr("result",1);
    	}else {
    		setAttr("result",0);
    	}
    	renderJson();
	}
	public void cashwithdrawal() {
		String openid=getPara("openid");
		int total=getParaToInt("total");
		System.out.println("openid="+openid);
		System.out.println("total="+total);
		
		String appid = PropKit.get("appid");  //微信公众号的appid
        String mch_id = PropKit.get("mch_id");; //商户号
        String nonce_str =  StringUtils.getRandomStringByLength(32); //生成随机数
        String partner_trade_no =   StringUtils.getRandomStringByLength(32); //生成商户订单号
        //String openid = openid; // 支付给用户openid
        String check_name = "NO_CHECK"; //是否验证真实姓名呢
        String re_user_name = "zyy";   //收款用户姓名
        String amount = "100";              //企业付款金额，单位为分
        String desc = "测试开发";   //企业付款操作说明信息。必填。
        String spbill_create_ip = IpKit.getRealIp(getRequest());
		// 部分安卓手机获取的ip地址是2个,字符串格式为"111.26.34.33, 123.151.76.158",
		// 此处进行判断和修正,出现此问题的手机品牌为华为，安卓版本6.0,EMUI4.1
		if (StrKit.isBlank(spbill_create_ip) || !StringUtils.Isipv4(spbill_create_ip)) {
			if (spbill_create_ip.indexOf(',') > 0) {
				spbill_create_ip = spbill_create_ip.substring(0, spbill_create_ip.indexOf(','));
			}
		}
		if (StrKit.isBlank(spbill_create_ip) || !StringUtils.Isipv4(spbill_create_ip)) {
			spbill_create_ip = "127.0.0.1";
		}
		Map<String, String> params = new HashMap<String, String>();
		params.put("mch_appid", appid);         //微信公众号的appid
		params.put("mchid", mch_id);       //商务号
		params.put("nonce_str",nonce_str);  //随机生成后数字，保证安全性
		params.put("partner_trade_no",partner_trade_no); //生成商户订单号
		params.put("openid",openid);            // 支付给用户openid
		params.put("check_name",check_name);    //是否验证真实姓名呢
		params.put("re_user_name",re_user_name);//收款用户姓名
		params.put("amount",amount);            //企业付款金额，单位为分
		params.put("desc",desc);                //企业付款操作说明信息。必填。
		params.put("spbill_create_ip",spbill_create_ip); //调用接口的机器Ip地址

		params = PayUtil.paraFilter(params);
		// 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
		String prestr = PayUtil.createLinkString(params);
		// MD5运算生成签名，这里是第一次签名，用于调用统一下单接口
		String sign = PayUtil.sign(prestr, WxPayConfig.key, "utf-8").toUpperCase();
		System.out.println("=======================第一次签名：" + sign + "=====================");
		// 拼接统一下单接口使用的xml数据，要将上一步生成的签名一起拼接进去
		String xml = "<xml>" + "<mch_appid>" + appid + "</mch_appid>" + "<mchid>" + mch_id + "</mchid>"
				+ "<nonce_str>" + nonce_str + "</nonce_str>"
				+ "<partner_trade_no>" + partner_trade_no + "</partner_trade_no>" + "<openid>" + openid + "</openid>"
				+ "<check_name>" + check_name + "</check_name>" + "<re_user_name>" + re_user_name
				+ "</re_user_name>" + "<amount>" + amount + "</amount>" + "<desc>"
				+ desc + "</desc>"+ "<spbill_create_ip>"+ spbill_create_ip + "</spbill_create_ip>" + "<sign>" + sign + "</sign>" + "</xml>";
		System.out.println("调试模式_统一下单接口 请求XML数据：" + xml);
		// 调用统一下单接口，并接受返回的结果
		//String result = PayUtil.httpRequest(PropKit.get("wxUrl"), "POST", xml);
		String result =CertHttpUtil.ssl(PropKit.get("wxUrl"), xml);
		System.out.println("调试模式_统一下单接口 返回XML数据：" + result);
		renderJson();
	}
}
