package com.wwbz.common.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wwbz.common.interceptor.ManaInterceptor;
import com.jfinal.aop.Before;
import com.jfinal.aop.Duang;
import com.jfinal.core.Controller;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.upload.UploadFile;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.kit.PaymentKit;
import com.jfinal.weixin.sdk.utils.HttpUtils;
import com.jfinal.weixin.sdk.utils.IOUtils;
import com.jfinal.wxaapp.WxaConfig;
import com.jfinal.wxaapp.WxaConfigKit;
import com.jfinal.wxaapp.api.WxaAccessTokenApi;
import com.wwbz.common.model.AllotWwbz;
import com.wwbz.common.model.ArchivesWwbz;
import com.wwbz.common.model.ArticleWwbz;
import com.wwbz.common.model.ArticleWwbzgl;
import com.wwbz.common.model.ArticlekindWwbz;
import com.wwbz.common.model.BannerWwbz;
import com.wwbz.common.model.BannerWwbzgl;
import com.wwbz.common.model.BargaingoodsWwbz;
import com.wwbz.common.model.BargainorderWwbz;
import com.wwbz.common.model.BasicWwbz;
import com.wwbz.common.model.BasicWwbzgl;
import com.wwbz.common.model.CategoryWwbz;
import com.wwbz.common.model.CommentWwbz;
import com.wwbz.common.model.CooperateWwbz;
import com.wwbz.common.model.CustomerWwbz;
import com.wwbz.common.model.DealerWwbz;
import com.wwbz.common.model.DivideWwbz;
import com.wwbz.common.model.FeedWwbz;
import com.wwbz.common.model.GlucometerWwbz;
import com.wwbz.common.model.GoodsWwbz;
import com.wwbz.common.model.InvitationWwbz;
import com.wwbz.common.model.MoneyWwbz;
import com.wwbz.common.model.OrderWwbz;
import com.wwbz.common.model.QuestionnaireWwbz;
import com.wwbz.common.model.RewardWwbz;
import com.wwbz.common.model.RewardcontactWwbz;
import com.wwbz.common.model.StoreWwbz;
import com.wwbz.common.model.UserWwbz;
import com.wwbz.common.util.WxPayConfig;
import com.wwbz.common.util.WxaQrcodeApi;
import com.wwbz.common.util.message;

public class ManaController extends Controller {
	public void index() {
		render("login.jsp");
	}
	public void login() {
		String admin = getPara("name");
		String password = getPara("password");
		if (null == admin || null == password || !PropKit.get("manager").equals(admin)
				|| !PropKit.get("mpassword").equals(password)) {
			setAttr("msg", "账号密码错误！");
		} else {
			setAttr("msg", 0);
			setSessionAttr("manager", admin);
			setSessionAttr("mpassword", password);
		}
		renderJson();
	}
	@Before(ManaInterceptor.class)
	public void mission() {
//		List<GoodsWwbz> gl=new GoodsWwbz().dao().find("SELECT * FROM goods_wwbz where states = 0 and kind = 0");
//		for (GoodsWwbz goodsWwbz : gl) {
//			if((goodsWwbz.getStime().getTime()-new Date().getTime())<1000*60*60*24*3&&(goodsWwbz.getStime().getTime()-new Date().getTime())>0){
//				goodsWwbz.setState(1);
//				goodsWwbz.update();
//			}
//				
//		}
//		setAttr("list", gl);
		render("missionlist.jsp");
	}
	@Before(ManaInterceptor.class)
	public void addmission() {
		int id = getParaToInt("id");
		List<GoodsWwbz> gl=new GoodsWwbz().dao().find("SELECT * from goods_wwbz where state<3 and states=0  and kind = 0");
		System.out.println(gl.size());
		if(id==0) {
			if(gl.size()>0) {
				redirect("/mana/mission");
				return;
			}else {
				GoodsWwbz goods=new GoodsWwbz();
				goods.setId(0);
				setAttr("goods", goods);
			}
			
			
		}else {
			GoodsWwbz goods=new GoodsWwbz().dao().findById(id);
			setAttr("goods", goods);
		}
		render("addmission.jsp");
	}
	@Before(ManaInterceptor.class)
	public void delmission() {
		int id = getParaToInt("id");
		GoodsWwbz goods=new GoodsWwbz().dao().findById(id);
		if(null!=goods) {
			goods.setStates(-1);
			if(goods.update()) {
				setAttr("result", 1);
			}else {
				setAttr("result", 0);
			}
		}else {
			setAttr("result", 1);
		}
		renderJson();
	}
	@Before(ManaInterceptor.class)
	public void savemission() throws ParseException {
		UploadFile uploadFile = getFile("mission_pic");// 在磁盘上保存文件
		UploadFile uploadFile1 = getFile("mission_diagram");
		String name= getPara("mission_name");
		int id=getParaToInt("mission_id");
		int number= getParaToInt("mission_number");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String stime=getPara("mission_stime");
		String des=getPara("mission_des");
		String etime=getPara("mission_etime");
		if(stime.length()>16){
			stime=stime.replace("/", "-");
		}else {
			stime=stime+":00";
		}
		if(etime.length()>16){
			etime=etime.replace("/", "-");
		}else {
			etime=etime+":00";
		}

		Date st= sdf.parse(stime.replace("T", " "));
		Date et= sdf.parse(etime.replace("T", " "));
		String price=getPara("mission_price");
		String sale=getPara("mission_sale");
		System.out.println("name="+name);
		System.out.println("id="+id);
		System.out.println("number="+number);
		System.out.println("stime="+stime);
		System.out.println("etime="+etime);
		System.out.println("price="+price);
		System.out.println("sale="+sale);
		GoodsWwbz goods=new GoodsWwbz();
		goods.setEtime(et);
		goods.setStime(st);
		goods.setPrice(price);
		goods.setSale(sale);
		goods.setName(name);
		goods.setNumber(number);
		goods.setState(0);
		goods.setStates(0);
		goods.setKind(0);
		goods.setDes(des);
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
					goods.setPic("icon/"+img.getName());
		}else {
			
		}
		if(null != uploadFile1) {
			boolean flag = false;
			String fileName = "";
			String fileNamePath = "";
					fileName = uploadFile1.getFileName();// 获取保存文件的文件名
					fileNamePath = uploadFile1.getUploadPath();
					String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length());
					fileName = (System.currentTimeMillis()+10) + fileType;
					File img = new File(fileNamePath + "/" + fileName);
					if (img.exists()) {
						img.delete();
					}
					flag = uploadFile1.getFile().renameTo(img);
					System.out.println(fileNamePath);
					System.out.println("icon/"+img.getName());
					goods.setDiagram("icon/"+img.getName());
		}else {
			
		}
		if(id==0) {
			if(goods.save()) {
				redirect("/mana/mission");
			}else {
				
			}
		}else {
			goods.setId(id);
			if(goods.update()) {
				redirect("/mana/mission");
			}else {
				
			}
		}
		
	}
	@Before(ManaInterceptor.class)
	public void goods() {
		List<GoodsWwbz> gl=new GoodsWwbz().dao().find("SELECT a.*,b.name categroy from goods_wwbz a LEFT JOIN category_wwbz b on a.categroyid=b.id  where a.states=0");
		setAttr("list",gl);
		render("goodslist.jsp");
	}
	@Before(ManaInterceptor.class)
	public void addgoods() {
		int id = getParaToInt("id");
		int kind = getParaToInt("kind");
		List<CategoryWwbz> cl=new CategoryWwbz().dao().find("SELECT * FROM category_wwbz where states=0");
		setAttr("cl",cl);
		if(id==0) {
			GoodsWwbz goods=new GoodsWwbz();
			goods.setId(0);
			goods.setKind(kind);
			setAttr("goods", goods);
		}else {
			GoodsWwbz goods=new GoodsWwbz().dao().findById(id);
			setAttr("goods", goods);
		}
		render("addgoods.jsp");
	}
	@Before(ManaInterceptor.class)
	public void delgoods() {
		int id = getParaToInt("id");
		GoodsWwbz goods=new GoodsWwbz().dao().findById(id);
		if(null!=goods) {
			goods.setStates(-1);
			if(goods.update()) {
				setAttr("result", 1);
			}else {
				setAttr("result", 0);
			}
		}else {
			setAttr("result", 1);
		}
		renderJson();
	}
	@Before(ManaInterceptor.class)
	public void savegoods(){
		UploadFile uploadFile = getFile("mission_pic");// 在磁盘上保存文件
		UploadFile uploadFile1 = getFile("mission_diagram");
		UploadFile uploadFile2 = getFile("mission_imgf");
		UploadFile uploadFile3 = getFile("mission_imgs");
		UploadFile uploadFile4 = getFile("mission_imgt");
		String name= getPara("mission_name");
		String des= getPara("mission_des");
		int id=getParaToInt("mission_id");
		int offset=getParaToInt("offset");
		int kind=getParaToInt("mission_kind");
		int categroy=getParaToInt("categroy");
		String price=getPara("mission_price");
		String standard=getPara("mission_standard");
		System.out.println("name="+name);
		System.out.println("id="+id);
		System.out.println("price="+price);
		GoodsWwbz goods=new GoodsWwbz();
		goods.setPrice(price);
		goods.setName(name);
		goods.setState(0);
		goods.setStates(0);
		goods.setKind(kind);
		goods.setDes(des);
		goods.setCategroyid(categroy);
		goods.setStandard(standard);
		goods.setOffset(offset);
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
					goods.setPic("icon/"+img.getName());
		}else {
			
		}
		if(null != uploadFile2) {
			boolean flag = false;
			String fileName = "";
			String fileNamePath = "";
					fileName = uploadFile2.getFileName();// 获取保存文件的文件名
					fileNamePath = uploadFile2.getUploadPath();
					String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length());
					fileName = (System.currentTimeMillis()+90) + fileType;
					File img = new File(fileNamePath + "/" + fileName);
					if (img.exists()) {
						img.delete();
					}
					flag = uploadFile2.getFile().renameTo(img);
					System.out.println(fileNamePath);
					System.out.println("icon/"+img.getName());
					goods.setImgf("icon/"+img.getName());
		}else {
			
		}
		if(null != uploadFile3) {
			boolean flag = false;
			String fileName = "";
			String fileNamePath = "";
					fileName = uploadFile3.getFileName();// 获取保存文件的文件名
					fileNamePath = uploadFile3.getUploadPath();
					String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length());
					fileName = (System.currentTimeMillis()+70) + fileType;
					File img = new File(fileNamePath + "/" + fileName);
					if (img.exists()) {
						img.delete();
					}
					flag = uploadFile3.getFile().renameTo(img);
					System.out.println(fileNamePath);
					System.out.println("icon/"+img.getName());
					goods.setImgs("icon/"+img.getName());
		}else {
			
		}
		if(null != uploadFile4) {
			boolean flag = false;
			String fileName = "";
			String fileNamePath = "";
					fileName = uploadFile4.getFileName();// 获取保存文件的文件名
					fileNamePath = uploadFile4.getUploadPath();
					String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length());
					fileName = (System.currentTimeMillis()+50) + fileType;
					File img = new File(fileNamePath + "/" + fileName);
					if (img.exists()) {
						img.delete();
					}
					flag = uploadFile4.getFile().renameTo(img);
					System.out.println(fileNamePath);
					System.out.println("icon/"+img.getName());
					goods.setImgt("icon/"+img.getName());
		}else {
			
		}
		if(null != uploadFile1) {
			boolean flag = false;
			String fileName = "";
			String fileNamePath = "";
					fileName = uploadFile1.getFileName();// 获取保存文件的文件名
					fileNamePath = uploadFile1.getUploadPath();
					String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length());
					fileName = (System.currentTimeMillis()+20) + fileType;
					File img = new File(fileNamePath + "/" + fileName);
					if (img.exists()) {
						img.delete();
					}
					flag = uploadFile1.getFile().renameTo(img);
					System.out.println(fileNamePath);
					System.out.println("icon/"+img.getName());
					goods.setDiagram("icon/"+img.getName());
		}else {
			
		}
		if(id==0) {
			if(goods.save()) {
				redirect("/mana/goods");
			}else {
				
			}
		}else {
			goods.setId(id);
			if(goods.update()) {
				redirect("/mana/goods");
			}else {
				
			}
		}
		
	}
	@Before(ManaInterceptor.class)
	public void articlelist() {
		List<ArticleWwbz> al=new ArticleWwbz().dao().find("SELECT a.*,b.name kind FROM article_wwbz a LEFT JOIN articlekind_wwbz b on a.kindid=b.id where a.states =0");
		setAttr("al",al);
		render("articlelist.jsp");
		
	}
	@Before(ManaInterceptor.class)
	public void addarticle() {
		int id=getParaToInt("id");
		if(id==0) {
			ArticleWwbz aw=new ArticleWwbz();
			aw.setId(0);
			setAttr("aw",aw);
		}else {
			ArticleWwbz aw=new ArticleWwbz().dao().findById(id);
			setAttr("aw",aw);
		}
		List<ArticlekindWwbz> akl=new ArticlekindWwbz().dao().find("SELECT * FROM articlekind_wwbz where states =0");
		setAttr("akl",akl);
		render("addarticle.jsp");
		
	}
	@Before(ManaInterceptor.class)
	public void delarticle() {
		int id=getParaToInt("id");
		ArticleWwbz aw=new ArticleWwbz().dao().findById(id);
		if(null!=aw) {
			aw.setStates(-1);
			if(aw.update()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}else {
			setAttr("result",1);
		}
		renderJson();
	}
	@Before(ManaInterceptor.class)
	public void savearticle() {
		int id=getParaToInt("id");
		String name=getPara("name");
		String src=getPara("src");
		int kindid=getParaToInt("articlekind");
		//System.out.println("id="+id);
		System.out.println("name="+name);
		System.out.println("src="+src);
		if(id==0) {
			ArticleWwbz aw=new ArticleWwbz();
			aw.setName(name);
			aw.setSrc(src);
			aw.setStates(0);
			aw.setKindid(kindid);
			if(aw.save()) {
				redirect("/mana/articlelist");
			}else {
				
			}
			
		}else {
			ArticleWwbz aw=new ArticleWwbz().dao().findById(id);
			aw.setName(name);
			aw.setSrc(src);
			aw.setStates(0);
			aw.setKindid(kindid);
			if(aw.update()) {
				redirect("/mana/articlelist");
			}else {
				
			}
		}
		
	}
	@Before(ManaInterceptor.class)
	public void customerlist() {
		List<CustomerWwbz> cl=new CustomerWwbz().dao().find("SELECT * FROM customer_wwbz where states=0");
		setAttr("list",cl);
		render("customerlist.jsp");
		
	}
	@Before(ManaInterceptor.class)
	public void addcustomer() {
		int id=getParaToInt("id");
		if(id==0) {
			CustomerWwbz ct=new CustomerWwbz();
			ct.setId(0);
			setAttr("ct",ct);
		}else {
			CustomerWwbz ct=new CustomerWwbz().dao().findById(id);
			setAttr("ct",ct);
		}
		render("addcustomer.jsp");
		
	}
	@Before(ManaInterceptor.class)
	public void delcustomer() {
		int id=getParaToInt("id");
		CustomerWwbz ct=new CustomerWwbz().dao().findById(id);
		if(null!=ct) {
			ct.setStates(-1);
			if(ct.update()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}else {
			setAttr("result",1);
		}
		renderJson();
	}
	@Before(ManaInterceptor.class)
	public void savecustomer() {
		UploadFile uploadFile = getFile("pic");
		int id=getParaToInt("id");
		String name=getPara("name");
		String des=getPara("des");
		CustomerWwbz ct=new CustomerWwbz();
		ct.setDes(des);
		ct.setName(name);
		ct.setStates(0);
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
					ct.setPic("icon/"+img.getName());
		}else {
			
		}
		if(id==0) {
			if(ct.save()) {
				redirect("/mana/customerlist");
			}else {
				
			}
		}else {
			ct.setId(id);
			if(ct.update()) {
				redirect("/mana/customerlist");
			}else {
				
			}
		}
		
	}
	
	@Before(ManaInterceptor.class)
	public void orderlist() {
		List<Record> list=Db.find("SELECT a.* FROM order_wwbz a LEFT JOIN goods_wwbz b on a.goodsid=b.id where a.state=1 and a.direction=0");
		setAttr("list",list);
		System.out.println("list="+list);
		render("orderlist.jsp");
	}
	@Before(ManaInterceptor.class)
	public void deallist() {
		List<Record> list=Db.find("SELECT a.* FROM order_wwbz a LEFT JOIN goods_wwbz b on a.goodsid=b.id where (a.state=2 or a.state=3) and a.direction=0 and a.goodsid!=0");
		setAttr("list",list);
		System.out.println("list="+list);
		render("deallist.jsp");
	}
	@Before(ManaInterceptor.class)
	public void dealistajax() {
		List<Record> list=Db.find("SELECT a.*,b.`name` goodsname FROM order_wwbz a LEFT JOIN goods_wwbz b on a.goodsid=b.id where a.state=1");
		System.out.println("list="+list);
		setAttr("data",list);
		renderJson();
	}
	@Before(ManaInterceptor.class)
	public void orderlistajax() {
		List<Record> list=Db.find("SELECT a.*,b.`name` goodsname FROM order_wwbz a LEFT JOIN goods_wwbz b on a.goodsid=b.id where a.state=0");
		System.out.println("list="+list);
		setAttr("data",list);
		renderJson();
	}
	@Before(ManaInterceptor.class)
	public void order() {
		int id=getParaToInt("id");
		Record record=Db.findFirst("SELECT a.*,b.`name` goodsname FROM order_wwbz a LEFT JOIN goods_wwbz b on a.goodsid=b.id where a.id= ?",id);
		if(null!=record.getStr("fatheropenid")) {
			DealerWwbz fdl=new DealerWwbz().dao().findFirst("SELECT * from dealer_wwbz where states=0 and state=1 and openid=?",record.getStr("fatheropenid"));
			setAttr("father",fdl.getName());
		}else {
			setAttr("father","无");
		}
		if(null!=record.getStr("headopenid")) {
			DealerWwbz pdl=new DealerWwbz().dao().findFirst("SELECT * from dealer_wwbz where states=0 and state=1 and openid=?",record.getStr("headopenid"));
			setAttr("head",pdl.getName());
		}else {
			setAttr("head","无");
		}
		
		
		setAttr("record",record);
		render("order.jsp");
	}
	@Before(ManaInterceptor.class)
	public void changeorder() {
		int id=getParaToInt("record_id");
		String com=getPara("com");
		String num=getPara("num");
		OrderWwbz od=new OrderWwbz().dao().findById(id);
		if(null!=od) {
			od.setState(2);
			od.setCom(com);
			od.setNum(num);
			if(od.update()) {
				redirect("/mana/orderlist");
				String name="";
				if(od.getCom().equals("shunfeng")) {
					name="顺丰速运";
				}else if(od.getCom().equals("debangwuliu")) {
					name="德邦快递";
				}else if(od.getCom().equals("yuantong")) {
					name="圆通快递";
				}else if(od.getCom().equals("zhongtong")) {
					name="中通快递";
				}else if(od.getCom().equals("shentong")) {
					name="申通快递";
				}else if(od.getCom().equals("yunda")) {
					name="韵达快递";
				}else if(od.getCom().equals("youzhengguonei")) {
					name="中国邮政";
				}
				message.message(od.getTel(), "您的"+od.getGoodsname()+"商品已经发货 快递公司为"+name+" 单号为"+od.getNum()+"请注意查收，您可以在小程序中查询物流信息。");
			}else {
				
			}
		}else {
			redirect("/mana/orderlist");
		}
	}
//	public void start() {
//		System.out.println("start");
//		 WebSocketImpl.DEBUG = false;
//	        WsServer s;
//	        s = new WsServer(8887);
//	        s.start();
//		renderJson();
//	}
//	public void stop() {
//		System.out.println("stop");
//		 WebSocketImpl.DEBUG = false;
//	        WsServer s;
//	        s = new WsServer(8887);
//	        s.start();
//		renderJson();
//	}
	@Before(ManaInterceptor.class)
	public void storelist() {
		List<StoreWwbz> sl=new StoreWwbz().dao().find("SELECT * FROM store_wwbz where states=0");
		setAttr("list",sl);
		render("storelist.jsp");
	}
	@Before(ManaInterceptor.class)
	public void delstore() {
		int id=getParaToInt("id");
		StoreWwbz st=new StoreWwbz().dao().findById(id);
		if(null!=st){
			st.setStates(-1);
			if(st.update()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}else {
			setAttr("result",1);
		}
		renderJson();
	}
	@Before(ManaInterceptor.class)
	public void addstore() {
		int id=getParaToInt("id");
		if(id==0) {
			StoreWwbz st=new StoreWwbz();
			st.setId(0);
			setAttr("st",st);
		}else {
			StoreWwbz st=new StoreWwbz().dao().findById(id);
			setAttr("st",st);
		}
		render("addstore.jsp");
	}
	@Before(ManaInterceptor.class)
	public void savestore() {
		int id=getParaToInt("id");
		String name=getPara("name");
		String city=getPara("city");
		String address=getPara("address");
		String tel=getPara("tel");
		String longitude=getPara("longitude");
		String latitude=getPara("latitude");
		System.out.println("longitude="+longitude);
		System.out.println("latitude="+latitude);
//		double pi = 3.141592653589793 * 3000.0 / 180.0;
//		double x = Double.parseDouble(longitude) - 0.0065, y = Double.parseDouble(latitude) - 0.006;
//		double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * pi);
//		double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * pi);
//		double gg_lon = z * Math.cos(theta);
//		double gg_lat = z * Math.sin(theta);
//		System.out.println("gg_lon="+gg_lon);
//		System.out.println("gg_lon="+gg_lon);
//		
		
		
		if(id==0) {
			StoreWwbz st=new StoreWwbz();
			st.setId(id);
			st.setAddress(address);
			st.setCity(city);
			st.setTel(tel);
			st.setName(name);
			st.setLongitude(longitude);
			st.setLatitude(latitude);
			st.setStates(0);
			if(st.save()) {
				redirect("/mana/storelist");
			}else {
				
			}
			
		}else {
			StoreWwbz st=new StoreWwbz().dao().findById(id);
			st.setId(id);
			st.setAddress(address);
			st.setCity(city);
			st.setTel(tel);
			st.setName(name);
			st.setLongitude(longitude);
			st.setLatitude(latitude);

			if(st.update()) {
				redirect("/mana/storelist");
			}else {
				
			}
		}
	}
	@Before(ManaInterceptor.class)
	public void applylist() {
		List<DealerWwbz> ul=new DealerWwbz().dao().find("SELECT * from dealer_wwbz where state=0 and states=0");
		setAttr("list",ul);
		render("applylist.jsp");
	}
	@Before(ManaInterceptor.class)
	public void dealapply() {
		List<Record> ul=Db.find("select * FROM dealer_wwbz  where state=1 and states=0 ORDER BY number desc");
		setAttr("list",ul);
		render("dealapply.jsp");
	}
	@Before(ManaInterceptor.class)
	public void pass() {
		int id=getParaToInt("id");
		UserWwbz user=new UserWwbz().dao().findById(id);
		if(null!=user&&user.getState()==0) {
			user.setJoin(2);
			user.setQrcodetime(new Date());
			if(user.update()) {
				WxaConfig wc = new WxaConfig();
				wc.setAppId(WxPayConfig.appid);
				wc.setAppSecret(WxPayConfig.appsecret);
				WxaConfigKit.setWxaConfig(wc);
				WxaAccessTokenApi.getAccessTokenStr();
				WxaQrcodeApi wxaQrcodeApi = Duang.duang(WxaQrcodeApi.class);
				String scene=user.getId()+"";
				InputStream inputStream = wxaQrcodeApi.getUnLimit(scene, "pages/index/index", 2000);
				long name=System.currentTimeMillis();
				try {
					IOUtils.toFile(inputStream, new File("C:\\Program Files\\Apache Software Foundation\\Tomcat 8.0\\webapps\\wwbz\\icon\\"+name+".png"));
					System.out.println("icon/"+name+".png");
					user.setQrcode("icon/"+name+".png");
					if(user.update()) {
						setAttr("result",1);
					}else {
						setAttr("result",0);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				setAttr("result",0);
			}
		}else {
			setAttr("result",0);
		}
		redirect("/mana/applylist");
	}
	@Before(ManaInterceptor.class)
	public void delapply() {
		int id=getParaToInt("id");
		UserWwbz user=new UserWwbz().dao().findById(id);
		if(null!=user&&user.getState()==0) {
			user.setJoin(0);
			user.setQrcodetime(null);
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
	@Before(ManaInterceptor.class)
	public void archiveslist() {
		List<ArchivesWwbz> al=new ArchivesWwbz().dao().find("SELECT * FROM archives_wwbz where states=0 and state=2 ORDER BY `order`,time DESC");
		setAttr("list",al);
		render("archiveslist.jsp");
	}
	@Before(ManaInterceptor.class)
	public void archivesdeal() {
		List<ArchivesWwbz> al=new ArchivesWwbz().dao().find("SELECT * FROM archives_wwbz where states=0 and (state=3 or state=4) ORDER BY `order`,time DESC");
		setAttr("list",al);
		render("archivesdeal.jsp");
	}
	@Before(ManaInterceptor.class)
	public void archives() {
		int id=getParaToInt("id");
		ArchivesWwbz ac=new ArchivesWwbz().dao().findById(id);
		setAttr("archives",ac);
		String list=ac.getNewill();
		String med=ac.getMedicine();
		String pic=ac.getPic();
		JSONArray illlist = new JSONArray().parseArray(list);
		JSONArray medlist = new JSONArray().parseArray(med);
		JSONArray piclist = new JSONArray().parseArray(pic);
		List<FeedWwbz> fl=new FeedWwbz().dao().find("SELECT * FROM feed_wwbz where openid=? ORDER BY time DESC",ac.getOpenid());
		for (FeedWwbz feedWwbz : fl) {
			if(feedWwbz.getPic().length()==2) {
				feedWwbz.setPic("");
			}else {
				feedWwbz.setPic(feedWwbz.getPic().substring(1, feedWwbz.getPic().length() - 1).replace("\"", ""));
			}
		}
		setAttr("illlist",illlist);
		setAttr("medlist",medlist);
		setAttr("piclist",piclist);
		System.out.println("fl="+fl);
		setAttr("list",fl);
		render("archivesdetail.jsp");
	}
	@Before(ManaInterceptor.class)
	public void passarchives() {
		int id=getParaToInt("id");
		int check=getParaToInt("check");
		int order=getParaToInt("order");
		System.out.println("check="+check);
		ArchivesWwbz ac=new ArchivesWwbz().dao().findById(id);
		if(null!=ac) {
			ac.setState(check);
			ac.setOrder(order);
			if(ac.update()) {
				redirect("/mana/archivesdeal");
				if(-1!=check) {
					message.message(ac.getTel(), "您的健康档案分享审核已通过，感谢您的支持！");
				}else {
					message.message(ac.getTel(), "抱歉您的健康档案审核未通过，请检查是否有推广/敏感信息。");
				}
				
			}
		}
	}
	@Before(ManaInterceptor.class)
	public void bannerlist() {
		List<BannerWwbz> al=new BannerWwbz().dao().find("SELECT * from banner_wwbz where states=0 ORDER BY banner_wwbz.index");
		setAttr("list",al);
		render("bannerlist.jsp");
	}
	@Before(ManaInterceptor.class)
	public void delbanner() {
		int id=getParaToInt("id");
		BannerWwbz bn=new BannerWwbz().dao().findById(id);
		if(null!=bn) {
			bn.setStates(-1);
			if(bn.update()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}else {
			setAttr("result",0);
		}
		renderJson();
	}
	@Before(ManaInterceptor.class)
	public void addbanner() {
		int id=getParaToInt("id");
		if(id==0) {
			BannerWwbz bn=new BannerWwbz();
			bn.setId(0);
			setAttr("bn",bn);
		}else {
			BannerWwbz bn=new BannerWwbz().dao().findById(id);
			setAttr("bn",bn);
		}
		render("addbanner.jsp");
	}
	@Before(ManaInterceptor.class)
	public void savebanner() {
		UploadFile url = getFile("url");
		UploadFile lpic = getFile("lpic");
		int id=getParaToInt("id");
		int index=getParaToInt("index");
		String name=getPara("name");
		int kind=getParaToInt("kind");
		String address=getPara("address");
		String page=getPara("page");
		BannerWwbz bn=new BannerWwbz();
		bn.setName(name);
		bn.setKind(kind);
		bn.setStates(0);
		bn.setIndex(index);
		if(kind==0) {
			bn.setContent(page);
		}else if(kind==1) {
			bn.setContent(address);
		}else if(kind==2) {
			if(null != lpic) {
				boolean flag = false;
				String fileName = "";
				String fileNamePath = "";
						fileName = lpic.getFileName();// 获取保存文件的文件名
						fileNamePath = lpic.getUploadPath();
						String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length());
						fileName = (System.currentTimeMillis()+90) + fileType;
						File img = new File(fileNamePath + "/" + fileName);
						if (img.exists()) {
							img.delete();
						}
						flag = lpic.getFile().renameTo(img);
						System.out.println(fileNamePath);
						System.out.println("icon/"+img.getName());
						bn.setContent("https://www.weishengtai.club/wwbz/icon/"+img.getName());
			}else {
				
			}
		}
		if(null != url) {
			boolean flag = false;
			String fileName = "";
			String fileNamePath = "";
					fileName = url.getFileName();// 获取保存文件的文件名
					fileNamePath = url.getUploadPath();
					String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length());
					fileName = System.currentTimeMillis() + fileType;
					File img = new File(fileNamePath + "/" + fileName);
					if (img.exists()) {
						img.delete();
					}
					flag = url.getFile().renameTo(img);
					System.out.println(fileNamePath);
					System.out.println("icon/"+img.getName());
					bn.setUrl("https://www.weishengtai.club/wwbz/icon/"+img.getName());
		}else {
			
		}
		if(id==0) {
			if(bn.save()) {
				redirect("/mana/bannerlist");
			}else {
				
			}
		}else {
			bn.setId(id);
			if(bn.update()) {
				redirect("/mana/bannerlist");
			}else {
				
			}
		}
		

	}
	@Before(ManaInterceptor.class)
	public void reward() {
		RewardWwbz re=new RewardWwbz().dao().findById(1);
		setAttr("re",re);
		render("reward.jsp");
	}
	@Before(ManaInterceptor.class)
	public void rewardlist() {
		List<UserWwbz> list=new UserWwbz().dao().find("SELECT * from user_wwbz where state=0 and nickname is not null");
		setAttr("list",list);
		render("rewardlist.jsp");
	}
	@Before(ManaInterceptor.class)
	public void savereward() {
		UploadFile shareimg = getFile("shareimg");
		String goods=getPara("goods");
		String sharetitle=getPara("sharetitle");
		int archives=getParaToInt("archives");
		int share=getParaToInt("share");
		int day=getParaToInt("day");
		int oldlow=getParaToInt("oldlow");
		int oldhigh=getParaToInt("oldhigh");
		int newlow=getParaToInt("newlow");
		int newhigh=getParaToInt("newhigh");
		RewardWwbz re=new RewardWwbz().dao().findById(1);
		
		
		
		
		if(oldhigh<=oldlow||newhigh<=newlow) {
			setAttr("result",0);
		}else {
			re.setGoods(Double.parseDouble(goods));
			re.setSharetitle(sharetitle);
			re.setArchives(archives);
			re.setShare(share);
			re.setDay(day);
			re.setOldlow(oldlow);
			re.setOldhigh(oldhigh);
			re.setNewlow(newlow);
			re.setNewhigh(newhigh);

			if(null != shareimg) {
				boolean flag = false;
				String fileName = "";
				String fileNamePath = "";
						fileName = shareimg.getFileName();// 获取保存文件的文件名
						fileNamePath = shareimg.getUploadPath();
						String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length());
						fileName = System.currentTimeMillis() + fileType;
						File img = new File(fileNamePath + "/" + fileName);
						if (img.exists()) {
							img.delete();
						}
						flag = shareimg.getFile().renameTo(img);
						System.out.println(fileNamePath);
						System.out.println("icon/"+img.getName());
						re.setShareimg("https://www.weishengtai.club/wwbz/icon/"+img.getName());
			}
			
			
			if(re.update()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}
		
		RewardWwbz nre=new RewardWwbz().dao().findById(1);
		setAttr("re",nre);
		render("reward.jsp");
	}
	@Before(ManaInterceptor.class)
	public void category() {
		List<CategoryWwbz> cl=new CategoryWwbz().dao().find("SELECT * FROM category_wwbz where states=0");
		setAttr("cl",cl);
		render("category.jsp");
	}
	@Before(ManaInterceptor.class)
	public void addcategory() {
		UploadFile icon = getFile("icon");
		String name=getPara("name");
		int id=getParaToInt("id");
	
		CategoryWwbz cy=new CategoryWwbz();
		if(null != icon) {
			boolean flag = false;
			String fileName = "";
			String fileNamePath = "";
					fileName = icon.getFileName();// 获取保存文件的文件名
					fileNamePath = icon.getUploadPath();
					String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length());
					fileName = System.currentTimeMillis() + fileType;
					File img = new File(fileNamePath + "/" + fileName);
					if (img.exists()) {
						img.delete();
					}
					flag = icon.getFile().renameTo(img);
					System.out.println(fileNamePath);
					System.out.println("icon/"+img.getName());
					cy.setIcon("icon/"+img.getName());
		}else {
			
		}
		cy.setName(name);
		cy.setStates(0);
		if(id==0) {
			if(cy.save()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}else {
			cy.setId(id);
			if(cy.update()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}
		
		redirect("/mana/category");
	}
	@Before(ManaInterceptor.class)
	public void changecategory() {
		String name=getPara("name");
		int id=getParaToInt("id");
		CategoryWwbz cy=new CategoryWwbz().findById(id);
		if(null!=cy) {
			cy.setName(name);
			if(cy.update()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}else {
			setAttr("result",0);
		}
		renderJson();
	}
	@Before(ManaInterceptor.class)
	public void delcategory() {
		int id=getParaToInt("id");
		CategoryWwbz cy=new CategoryWwbz().findById(id);
		List<GoodsWwbz> gl=new GoodsWwbz().dao().find("SELECT * from goods_wwbz where states=0 and categroyid = ?",id);
		if(gl.size()!=0) {
			System.out.println(gl.size());
			setAttr("result",2);
		}else {
			if(null!=cy) {
				cy.setStates(-1);
				if(cy.update()) {
					setAttr("result",1);
				}else {
					setAttr("result",0);
				}
			}else {
				setAttr("result",0);
			}
		}
		
		renderJson();
	}
	@Before(ManaInterceptor.class)
	public void articlekind() {
		List<ArticlekindWwbz> akl=new ArticlekindWwbz().dao().find("SELECT * FROM articlekind_wwbz where states=0");
		setAttr("akl",akl);
		render("articlekind.jsp");
	}
	@Before(ManaInterceptor.class)
	public void addarticlekind() {
		UploadFile icon = getFile("icon");
		String name=getPara("name");
		int id=getParaToInt("id");
	
		ArticlekindWwbz ak=new ArticlekindWwbz();
		if(null != icon) {
			boolean flag = false;
			String fileName = "";
			String fileNamePath = "";
					fileName = icon.getFileName();// 获取保存文件的文件名
					fileNamePath = icon.getUploadPath();
					String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length());
					fileName = System.currentTimeMillis() + fileType;
					File img = new File(fileNamePath + "/" + fileName);
					if (img.exists()) {
						img.delete();
					}
					flag = icon.getFile().renameTo(img);
					System.out.println(fileNamePath);
					System.out.println("icon/"+img.getName());
					ak.setIcon("icon/"+img.getName());
		}else {
			
		}
		ak.setName(name);
		ak.setStates(0);
		if(id==0) {
			if(ak.save()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}else {
			ak.setId(id);
			if(ak.update()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}
		
		redirect("/mana/articlekind");
	}
	@Before(ManaInterceptor.class)
	public void changearticlekind() {
		String name=getPara("name");
		int id=getParaToInt("id");
		ArticlekindWwbz ak=new ArticlekindWwbz().findById(id);
		if(null!=ak) {
			ak.setName(name);
			if(ak.update()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}else {
			setAttr("result",0);
		}
		renderJson();
	}
	@Before(ManaInterceptor.class)
	public void delarticlekind() {
		int id=getParaToInt("id");
		ArticlekindWwbz akl=new ArticlekindWwbz().findById(id);
		List<ArticleWwbz> al=new ArticleWwbz().dao().find("SELECT * from article_wwbz where states=0 and kindid = ?",id);
		if(al.size()!=0) {
			System.out.println(al.size());
			setAttr("result",2);
		}else {
			if(null!=akl) {
				akl.setStates(-1);
				if(akl.update()) {
					setAttr("result",1);
				}else {
					setAttr("result",0);
				}
			}else {
				setAttr("result",0);
			}
		}
		
		renderJson();
	}
	@Before(ManaInterceptor.class)
	public void applyquestion() {
		List<QuestionnaireWwbz> qnl=new QuestionnaireWwbz().dao().find("SELECT * FROM questionnaire_wwbz where states=0 and paperid=1 and parentid=0 ORDER BY `order`");
		List<QuestionnaireWwbz> anl=new QuestionnaireWwbz().dao().find("SELECT * FROM questionnaire_wwbz where states=0 and paperid=1");
		setAttr("qlist",qnl);
		setAttr("list",anl);
		render("applyquestion.jsp");
	}
	@Before(ManaInterceptor.class)
	public void addapplyquestion() {
		int number=getParaToInt("number");
		String content=getPara("content");
		int id=getParaToInt("id");
		QuestionnaireWwbz qn=new QuestionnaireWwbz();
		qn.setContent(content);
		qn.setOrder(number);
		qn.setPaperid(1);
		qn.setParentid(0);
		qn.setStates(0);
		qn.setScore(0);
		if(id==0) {
			if(qn.save()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}else {
			qn.setId(id);
			if(qn.update()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}
		
		renderJson();
	}
	@Before(ManaInterceptor.class)
	public void delapplyquestion() {
		int id=getParaToInt("id");
		QuestionnaireWwbz qn=new QuestionnaireWwbz().findById(id);
		if(null!=qn) {
			qn.setStates(-1);
			if(qn.update()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}else {
			setAttr("result",0);
		}
		
		renderJson();
	}
	@Before(ManaInterceptor.class)
	public void addapplyquestionson() {
		int fatherid=getParaToInt("fatherid");
		int id=getParaToInt("id");
		String content=getPara("content");
		QuestionnaireWwbz qn=new QuestionnaireWwbz();
		qn.setContent(content);
		qn.setOrder(0);
		qn.setPaperid(1);
		qn.setStates(0);
		qn.setScore(0);
		if(id==0) {
			qn.setParentid(fatherid);
			if(qn.save()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}else {
			qn.setId(id);
			if(qn.update()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}
		
		renderJson();
	}
	@Before(ManaInterceptor.class)
	public void applycontrol() {
		List<CooperateWwbz> crl=new CooperateWwbz().dao().find("SELECT * from cooperate_wwbz WHERE states=0");
		List<AllotWwbz> atl=new AllotWwbz().dao().find("SELECT * from allot_wwbz WHERE states=0");
		setAttr("list",crl);
		setAttr("atl",atl);
		render("applycontrol.jsp");
	}
	@Before(ManaInterceptor.class)
	public void addcooperate() {
		int id=getParaToInt("id");
		String name=getPara("name");
		int money=getParaToInt("money");
		String des=getPara("des");
		boolean pay=getParaToBoolean("pay");
		boolean hospital=getParaToBoolean("hospital");
		boolean head=getParaToBoolean("head");
		String percent=getPara("percent");
 		CooperateWwbz cr=new CooperateWwbz();
		cr.setStates(0);
		cr.setName(name);
		cr.setMoney(money);
		cr.setDes(des);
		cr.setPercent(Double.parseDouble(percent));
		if(pay) {
			cr.setIspay(1);
		}else {
			cr.setIspay(0);
		}
		if(hospital) {
			cr.setIshospital(1);
		}else {
			cr.setIshospital(0);
		}
		if(head) {
			cr.setIshead(1);
		}else {
			cr.setIshead(0);
		}
		if(id==0) {
			if(cr.save()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}else {
			cr.setId(id);
			if(cr.update()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}
		renderJson();
	}
	@Before(ManaInterceptor.class)
	private Double getParaToDouble(String string) {
		// TODO Auto-generated method stub
		return null;
	}
	@Before(ManaInterceptor.class)
	public void delcooperate() {
		int id=getParaToInt("id");
		CooperateWwbz cr=new CooperateWwbz().dao().findById(id);
		if(null!=cr) {
			cr.setStates(-1);
			if(cr.update()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}else {
			setAttr("result",0);
		}
		renderJson();
	}
	@Before(ManaInterceptor.class)
	public void addallot() {
		int id=getParaToInt("id");
		int start=getParaToInt("start");
		int end=getParaToInt("end");
		int percent=getParaToInt("percent");
		List<AllotWwbz> atl=new AllotWwbz().dao().find("select * from allot_wwbz where states=0");
		AllotWwbz at=new AllotWwbz();
		if(id==0) {
			if(atl.size()==0) {
				at.setStates(0);
				at.setStart(start);
				at.setEnd(end);
				at.setPercent(percent);
				if(at.save()) {
					setAttr("result",1);
				}else {
					setAttr("result",0);
				}
			}else {
				AllotWwbz pat=atl.get(atl.size()-1);
				System.out.println("end="+pat.getEnd());
				System.out.println(start==pat.getEnd());
				if(start==pat.getEnd()) {
					at.setStates(0);
					at.setStart(start);
					at.setEnd(end);
					at.setPercent(percent);
					if(at.save()) {
						setAttr("result",1);
					}else {
						setAttr("result",0);
					}
				}else {
					setAttr("result",0);
					setAttr("msg","区间不连贯");
				}
			}
		}else {
			at.setStart(start);
			at.setEnd(end);
			at.setPercent(percent);
			at.setId(id);
			if(at.update()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}
		renderJson();
	}
	@Before(ManaInterceptor.class)
	public void delallot() {
		List<AllotWwbz> atl=new AllotWwbz().dao().find("select * from allot_wwbz where states=0");
		for (AllotWwbz allotWwbz : atl) {
			allotWwbz.setStates(-1);
			allotWwbz.update();
		}
		setAttr("result",1);
		renderJson();
	}
	@Before(ManaInterceptor.class)
	public void delapplyx() {
		int id=getParaToInt("id");
		DealerWwbz dl=new DealerWwbz().findById(id);
		if(null!=dl) {
			dl.setStates(-1);
			if(dl.update()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}else {
			setAttr("result",1);
		}
		renderJson();
	}
	@Before(ManaInterceptor.class)
	public void applycontent() {
		int id=getParaToInt("id");
		int state=getParaToInt("state");
		DealerWwbz dl=new DealerWwbz().findById(id);
		
		if(null!=dl) {
			setAttr("dl",dl);
			JSONArray answer = new JSONArray().parseArray(dl.getAnswer());
			JSONArray pic = new JSONArray().parseArray(dl.getPic());
			setAttr("answer",answer);
			setAttr("pic",pic);
			setAttr("state",state);
		}
		render("applycontent.jsp");
	}
	@Before(ManaInterceptor.class)
	public void applycontentdel() {
		int id=getParaToInt("id");
		DealerWwbz dl=new DealerWwbz().findById(id);
		if(null!=dl) {
			dl.setStates(-1);
			if(dl.update()) {
				setAttr("result",1);
				message.message(dl.getTel(), "抱歉您的经销商申请未能通过，如有交付定金，请联系客服退款。");
			}else {
				setAttr("result",0);
			}
		}else {
			setAttr("result",1);
		}
		redirect("/mana/applylist");
	}
	
	public void update(String openid) {
		//String openid=getPara("openid");
		List<RewardcontactWwbz> rcl=new RewardcontactWwbz().dao().find("SELECT * from rewardcontact_wwbz where fatheropenid=?",openid);
		long time = System.currentTimeMillis();
        Date nowDate = new Date(time);
        Date day = new Date(time - 2592000000L);
        System.out.println("day="+day.toLocaleString());
		for (RewardcontactWwbz rewardcontactWwbz : rcl) {
			UserWwbz user=new UserWwbz().dao().findFirst("SELECT * from user_wwbz where state=0 and openid=?",rewardcontactWwbz.getOpenid());
			if(rewardcontactWwbz.getTime().after(day)) {
				if(null!=user&&null==user.getFatheropenid()) {
					user.setFatheropenid(rewardcontactWwbz.getFatheropenid());
					user.update();
				}
			}else {
				user.setLock(0);
				user.update();
			}
		}
	}
	
	
	
	@Before(ManaInterceptor.class)
	public void applycontentpass() {
		int id=getParaToInt("id");
		String latitude=getPara("latitude");
		String longitude=getPara("longitude");
		DealerWwbz dl=new DealerWwbz().findById(id);
		List<DealerWwbz> drl=new DealerWwbz().dao().find("SELECT * FROM dealer_wwbz where number !=0");
		if(null!=dl) {
			
			dl.setIsshow(0);
			if(dl.getState()==1) {
				dl.setLatitude(latitude);
				dl.setLongitude(longitude);
			}else {
				dl.setState(1);
				dl.setTime(new Date());
				dl.setLatitude(latitude);
				dl.setLongitude(longitude);
				dl.setNumber(drl.size()+1);
				UserWwbz user=new UserWwbz().dao().findFirst("SELECT * FROM user_wwbz where openid= ? and state=0",dl.getOpenid());
				WxaConfig wc = new WxaConfig();
				wc.setAppId(WxPayConfig.appid);
				wc.setAppSecret(WxPayConfig.appsecret);
				WxaConfigKit.setWxaConfig(wc);
				WxaAccessTokenApi.getAccessTokenStr();
				WxaQrcodeApi wxaQrcodeApi = Duang.duang(WxaQrcodeApi.class);
				if(null!=user) {
					String scene=user.getId()+"";
					InputStream inputStream = wxaQrcodeApi.getUnLimit(scene, "pages/index/index", 2000);
					long name=System.currentTimeMillis();
					try {
						IOUtils.toFile(inputStream, new File("C:\\Program Files\\Apache Software Foundation\\Tomcat 8.0\\webapps\\wwbz\\icon\\"+name+".png"));
						System.out.println("icon/"+name+".png");
						dl.setQrcode("icon/"+name+".png");
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
			
			
			if(dl.update()) {
				update(dl.getOpenid());
				message.message(dl.getTel(), "您的经销商申请已通过且不展示服务中心，感谢您的支持！");
			}else {
				
			}
			
		}
		redirect("/mana/applylist");
		
		
	}
	@Before(ManaInterceptor.class)
	public void applycontentrealpass() {
		int id=getParaToInt("id");
		String latitude=getPara("latitude");
		String longitude=getPara("longitude");
		DealerWwbz dl=new DealerWwbz().findById(id);
		List<DealerWwbz> drl=new DealerWwbz().dao().find("SELECT * FROM dealer_wwbz where number !=0");
		if(null!=dl) {
			dl.setIsshow(1);
			if(dl.getState()==1) {
				dl.setLatitude(latitude);
				dl.setLongitude(longitude);
			}else {
				dl.setState(1);
				dl.setTime(new Date());
				dl.setNumber(drl.size()+1);
				dl.setLatitude(latitude);
				dl.setLongitude(longitude);
				UserWwbz user=new UserWwbz().dao().findFirst("SELECT * FROM user_wwbz where openid= ? and state=0",dl.getOpenid());
				WxaConfig wc = new WxaConfig();
				wc.setAppId(WxPayConfig.appid);
				wc.setAppSecret(WxPayConfig.appsecret);
				WxaConfigKit.setWxaConfig(wc);
				WxaAccessTokenApi.getAccessTokenStr();
				WxaQrcodeApi wxaQrcodeApi = Duang.duang(WxaQrcodeApi.class);
				if(null!=user) {
					String scene=user.getId()+"";
					InputStream inputStream = wxaQrcodeApi.getUnLimit(scene, "pages/index/index", 2000);
					long name=System.currentTimeMillis();
					try {
						IOUtils.toFile(inputStream, new File("C:\\Program Files\\Apache Software Foundation\\Tomcat 8.0\\webapps\\wwbz\\icon\\"+name+".png"));
						System.out.println("icon/"+name+".png");
						dl.setQrcode("icon/"+name+".png");
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				List<DealerWwbz> dll=new DealerWwbz().dao().find("SELECT * from dealer_wwbz where city = ? and state=1 and states=0",dl.getCity());
				if(dll.size()<10) {
					dl.setStorename("无微不治"+dl.getCity()+"服务中心No.00"+(dll.size()+1));
				}else if(dll.size()>=10&&dll.size()<100) {
					dl.setStorename("无微不治"+dl.getCity()+"服务中心No.0"+(dll.size()+1));
				}else if(dll.size()>=100&&dll.size()<1000) {
					dl.setStorename("无微不治"+dl.getCity()+"服务中心No."+(dll.size()+1));
				}
			}
			
			
			
			
		
			if(dl.update()) {
				update(dl.getOpenid());
				message.message(dl.getTel(), "您的经销商申请已通过并展示服务中心，感谢您的支持！");
			}else {
				
			}
		}
		redirect("/mana/applylist");
		
		
	}
	@Before(ManaInterceptor.class)
	public void dealerallot() {
		int id=getParaToInt("id");
		List<AllotWwbz> atl=new AllotWwbz().dao().find("select * from allot_wwbz where states=0");
		DealerWwbz dl=new DealerWwbz().dao().findById(id);
		Record headrecord=Db.findFirst("SELECT SUM(money) sum,a.* from order_wwbz a where state!=0 and kind =0 and direction=0 and headopenid =?",dl.getOpenid());
		Record totalrecord=Db.findFirst("SELECT SUM(money) sum,a.* from order_wwbz a where state!=0 and kind =0 and direction=0 and fatheropenid =?",dl.getOpenid());		
		if(null!=totalrecord.getStr("sum")) {
			Double totalmoney=Double.parseDouble(totalrecord.getStr("sum"));
			setAttr("totalmoney",totalmoney);
			List<DivideWwbz> dil=new DivideWwbz().dao().find("SELECT * from divide_wwbz where openid=?",dl.getOpenid());
			Date time = null;
			if(dil.size()==0) {
				Record record=Db.findFirst("SELECT SUM(money) sum,a.* from order_wwbz a where state!=0 and kind =0 and direction=0 and fatheropenid =?",dl.getOpenid());
				int percent=0;
				for (AllotWwbz allotWwbz : atl) {
					System.out.println("计算="+totalmoney);
					System.out.println(allotWwbz.getEnd());
					System.out.println("档位="+allotWwbz.getEnd()*10000);
					
					if(totalmoney<allotWwbz.getEnd()*10000) {
						percent=allotWwbz.getPercent();
						break;
					}
				}
				setAttr("unliquidated",totalmoney);
				setAttr("money",totalmoney*percent/100);
				
				if(null!=headrecord.getStr("sum")) {
					setAttr("htotal",headrecord.getStr("sum"));
					setAttr("hunliquidated",headrecord.getStr("sum"));
					setAttr("hmoney",Double.parseDouble(headrecord.getStr("sum"))*1/100);
					setAttr("hpercent",1);
				}else {
					setAttr("htotal",0);
					setAttr("hunliquidated",0);
					setAttr("hmoney",0);
					setAttr("hpercent",1);
				}
				
			}else {
				for (DivideWwbz divideWwbz : dil) {
					time=divideWwbz.getTime();
				}
				Record record=Db.findFirst("SELECT SUM(money) sum,a.* from order_wwbz a where state!=0 and kind =0 and direction=0 and fatheropenid =? and buytime>?",dl.getOpenid(),time);
				if(null!=record.getStr("sum")) {
					Double tmoney=Double.parseDouble(record.getStr("sum"));
					int percent=0;
					for (AllotWwbz allotWwbz : atl) {
						System.out.println("计算="+tmoney);
						System.out.println(allotWwbz.getEnd());
						System.out.println("档位="+allotWwbz.getEnd()*10000);
						
						if(tmoney<allotWwbz.getEnd()*10000) {
							percent=allotWwbz.getPercent();
							break;
						}
					}
					setAttr("unliquidated",tmoney);
					setAttr("money",tmoney*percent/100);
				}else {
					setAttr("unliquidated",0);
					setAttr("money",0);
				}
				
				Record hrecord=Db.findFirst("SELECT SUM(money) sum,a.* from order_wwbz a where state!=0 and kind =0 and direction=0 and headopenid =? and buytime>?",dl.getOpenid(),time);
				if(null!=hrecord.getStr("sum")) {
					setAttr("htotal",headrecord.getStr("sum"));
					setAttr("hunliquidated",hrecord.getStr("sum"));
					setAttr("hmoney",Double.parseDouble(hrecord.getStr("sum"))*1/100);
					setAttr("hpercent",1);
				}else {
					setAttr("htotal",0);
					setAttr("hunliquidated",0);
					setAttr("hmoney",0);
					setAttr("hpercent",1);
				}
			}
			
		}
		
		
		
		List<DivideWwbz> dill=new DivideWwbz().dao().find("SELECT * from divide_wwbz where openid = ?",dl.getOpenid());
		
		
		setAttr("allot",atl);
		setAttr("dealer",dl);
		setAttr("dl",dill);
		render("dealerallot.jsp");
	}
	@Before(ManaInterceptor.class)
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
		
		render("deallist.jsp");
	}
	@Before(ManaInterceptor.class)
	public void divide() {
		System.out.println("divide");
		int id=getParaToInt("id");
		String name=getPara("name");
		String bank=getPara("bank");		
		String bankname=getPara("bankname");			
		String branch=getPara("branch");
		String account=getPara("account");
		String total=getPara("total");
		String money=getPara("money");
		String htotal=getPara("htotal");
		String hmoeny=getPara("hmoeny");
		List<AllotWwbz> atl=new AllotWwbz().dao().find("select * from allot_wwbz where states=0");
		DealerWwbz dl=new DealerWwbz().dao().findById(id);
		int percent=0;
		String start="";
		String end="";
		for (AllotWwbz allotWwbz : atl) {
			System.out.println("计算="+total);
			System.out.println(allotWwbz.getEnd());
			System.out.println("档位="+allotWwbz.getEnd()*10000);
			
			if(Double.parseDouble(total)<allotWwbz.getEnd()*10000) {
				percent=allotWwbz.getPercent();
				start=allotWwbz.getStart()+"";
				end=allotWwbz.getEnd()+"";
				break;
			}
		}
		DivideWwbz dv=new DivideWwbz();
		dv.setAllot(start+"万~"+end+"万 分配比例"+percent+"%");
		dv.setMoney(Double.parseDouble(total));
		dv.setName(name);
		dv.setOut(Double.parseDouble(money));
		dv.setOpenid(dl.getOpenid());
		dv.setTime(new Date());
		dv.setAccount(account);
		dv.setBank(bank);
		dv.setBankname(bankname);
		dv.setBranch(branch);
		dv.setHeadmoney(Double.parseDouble(htotal));
		dv.setHeadout(Double.parseDouble(hmoeny));
		if(dv.save()) {
			dl.setUnliquidated(0.0);
			dl.setHeadunliquidated(0.0);
			dl.update();
			setAttr("result",1);
		}else {
			setAttr("result",0);
		}
		renderJson();
	}
	@Before(ManaInterceptor.class)
	public void commentlist() {
		int goodsid=getParaToInt("id");
		String name=getPara("name");
		List<CommentWwbz> ctl=new CommentWwbz().dao().find("SELECT * FROM comment_wwbz where goodsid=? and fathercommentid = 0 and states>-2",goodsid);
		List<CommentWwbz> all=new CommentWwbz().dao().find("SELECT * FROM comment_wwbz where goodsid=? and states=0",goodsid);
		setAttr("list",ctl);
		setAttr("all",all);
		setAttr("name",name);
		render("commentlist.jsp");
	}
	@Before(ManaInterceptor.class)
	public void commentstates() {
		int id=getParaToInt("id");
		boolean check=getParaToBoolean("check");
		CommentWwbz ct=new CommentWwbz().findById(id);
		if(null!=ct) {
			if(check) {
				ct.setStates(-1);
				if(ct.update()) {
					setAttr("result",1);
				}else {
					setAttr("result",0);
				}
			}else {
				ct.setStates(0);
				if(ct.update()) {
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
	@Before(ManaInterceptor.class)
	public void sellerreply() {
		int id=getParaToInt("id");
		String content=getPara("content");
		CommentWwbz ct=new CommentWwbz().findById(id);
		CommentWwbz ne=new CommentWwbz();
		ne.setTime(new Date());
		ne.setContent(content);
		ne.setOpenid("0");
		ne.setGoodsid(ct.getGoodsid());
		ne.setStates(0);
		ne.setNickname("商家");
		ne.setTonickname(ct.getNickname());
		ne.setFathercommentid(id);
		if(ne.save()) {
			ct.setIsnew(1);
			ct.update();
			setAttr("result",1);
		}else {
			setAttr("result",0);
		}
		renderJson();
	}
	@Before(ManaInterceptor.class)
	public void basic() {
		BasicWwbzgl bc=new BasicWwbzgl().findById(1);
		setAttr("basic",bc);
		render("basic.jsp");
	}
	@Before(ManaInterceptor.class)
	public void changebasic() {
		UploadFile drrb = getFile("drrb");
		UploadFile drzb = getFile("drzb");
		UploadFile dryb = getFile("dryb");
		UploadFile drnb = getFile("drnb");
		UploadFile dqrb = getFile("dqrb");
		UploadFile dqzb = getFile("dqzb");
		UploadFile dqyb = getFile("dqyb");
		UploadFile dqnb = getFile("dqnb");
		String protocol=getPara("protocol");
		BasicWwbzgl bc=new BasicWwbzgl().findById(1);
		bc.setProtocol(protocol);
		if(null != drrb) {
			boolean flag = false;
			String fileName = "";
			String fileNamePath = "";
					fileName = drrb.getFileName();// 获取保存文件的文件名
					fileNamePath = drrb.getUploadPath();
					String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length());
					fileName = (System.currentTimeMillis()+0) + fileType;
					File img = new File(fileNamePath + "/" + fileName);
					if (img.exists()) {
						img.delete();
					}
					flag = drrb.getFile().renameTo(img);
					System.out.println(fileNamePath);
					System.out.println("icon/"+img.getName());
					bc.setDrrb("https://www.weishengtai.club/wwbz/icon/"+img.getName());
		}
		if(null != drzb) {
			boolean flag = false;
			String fileName = "";
			String fileNamePath = "";
					fileName = drzb.getFileName();// 获取保存文件的文件名
					fileNamePath = drzb.getUploadPath();
					String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length());
					fileName = (System.currentTimeMillis()+20) + fileType;
					File img = new File(fileNamePath + "/" + fileName);
					if (img.exists()) {
						img.delete();
					}
					flag = drzb.getFile().renameTo(img);
					System.out.println(fileNamePath);
					System.out.println("icon/"+img.getName());
					bc.setDrzb("https://www.weishengtai.club/wwbz/icon/"+img.getName());
		}
		if(null != dryb) {
			boolean flag = false;
			String fileName = "";
			String fileNamePath = "";
					fileName = dryb.getFileName();// 获取保存文件的文件名
					fileNamePath = dryb.getUploadPath();
					String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length());
					fileName = (System.currentTimeMillis()+40) + fileType;
					File img = new File(fileNamePath + "/" + fileName);
					if (img.exists()) {
						img.delete();
					}
					flag = dryb.getFile().renameTo(img);
					System.out.println(fileNamePath);
					System.out.println("icon/"+img.getName());
					bc.setDryb("https://www.weishengtai.club/wwbz/icon/"+img.getName());
		}
		if(null != drnb) {
			boolean flag = false;
			String fileName = "";
			String fileNamePath = "";
					fileName = drnb.getFileName();// 获取保存文件的文件名
					fileNamePath = drnb.getUploadPath();
					String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length());
					fileName = (System.currentTimeMillis()+60) + fileType;
					File img = new File(fileNamePath + "/" + fileName);
					if (img.exists()) {
						img.delete();
					}
					flag = drnb.getFile().renameTo(img);
					System.out.println(fileNamePath);
					System.out.println("icon/"+img.getName());
					bc.setDrnb("https://www.weishengtai.club/wwbz/icon/"+img.getName());
		}
		if(null != dqrb) {
			boolean flag = false;
			String fileName = "";
			String fileNamePath = "";
					fileName = dqrb.getFileName();// 获取保存文件的文件名
					fileNamePath = dqrb.getUploadPath();
					String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length());
					fileName = (System.currentTimeMillis()+100) + fileType;
					File img = new File(fileNamePath + "/" + fileName);
					if (img.exists()) {
						img.delete();
					}
					flag = dqrb.getFile().renameTo(img);
					System.out.println(fileNamePath);
					System.out.println("icon/"+img.getName());
					bc.setDqrb("https://www.weishengtai.club/wwbz/icon/"+img.getName());
		}
		if(null != dqzb) {
			boolean flag = false;
			String fileName = "";
			String fileNamePath = "";
					fileName = dqzb.getFileName();// 获取保存文件的文件名
					fileNamePath = dqzb.getUploadPath();
					String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length());
					fileName = (System.currentTimeMillis()+120) + fileType;
					File img = new File(fileNamePath + "/" + fileName);
					if (img.exists()) {
						img.delete();
					}
					flag = dqzb.getFile().renameTo(img);
					System.out.println(fileNamePath);
					System.out.println("icon/"+img.getName());
					bc.setDqzb("https://www.weishengtai.club/wwbz/icon/"+img.getName());
		}
		if(null != dqyb) {
			boolean flag = false;
			String fileName = "";
			String fileNamePath = "";
					fileName = dqyb.getFileName();// 获取保存文件的文件名
					fileNamePath = dqyb.getUploadPath();
					String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length());
					fileName = (System.currentTimeMillis()+140) + fileType;
					File img = new File(fileNamePath + "/" + fileName);
					if (img.exists()) {
						img.delete();
					}
					flag = dqyb.getFile().renameTo(img);
					System.out.println(fileNamePath);
					System.out.println("icon/"+img.getName());
					bc.setDqyb("https://www.weishengtai.club/wwbz/icon/"+img.getName());
		}
		if(null != dqnb) {
			boolean flag = false;
			String fileName = "";
			String fileNamePath = "";
					fileName = dqnb.getFileName();// 获取保存文件的文件名
					fileNamePath = dqnb.getUploadPath();
					String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length());
					fileName = (System.currentTimeMillis()+160) + fileType;
					File img = new File(fileNamePath + "/" + fileName);
					if (img.exists()) {
						img.delete();
					}
					flag = dqnb.getFile().renameTo(img);
					System.out.println(fileNamePath);
					System.out.println("icon/"+img.getName());
					bc.setDqnb("https://www.weishengtai.club/wwbz/icon/"+img.getName());
		}
		if(bc.update()) {
			
		}else {
			
		}
		redirect("/mana/basic");
	}
	@Before(ManaInterceptor.class)
	public void bannerlistgl() {
		List<BannerWwbzgl> al=new BannerWwbzgl().dao().find("SELECT * FROM banner_wwbzgl where states=0");
		setAttr("list",al);
		render("bannerlistgl.jsp");
	}
	@Before(ManaInterceptor.class)
	public void delbannergl() {
		int id=getParaToInt("id");
		BannerWwbzgl bn=new BannerWwbzgl().dao().findById(id);
		if(null!=bn) {
			bn.setStates(-1);
			if(bn.update()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}else {
			setAttr("result",0);
		}
		renderJson();
	}
	@Before(ManaInterceptor.class)
	public void addbannergl() {
		int id=getParaToInt("id");
		if(id==0) {
			BannerWwbzgl bn=new BannerWwbzgl();
			bn.setId(0);
			setAttr("bn",bn);
		}else {
			BannerWwbzgl bn=new BannerWwbzgl().dao().findById(id);
			setAttr("bn",bn);
		}
		render("addbannergl.jsp");
	}
	@Before(ManaInterceptor.class)
	public void savebannergl() {
		UploadFile url = getFile("url");
		UploadFile lpic = getFile("lpic");
		int id=getParaToInt("id");
		String name=getPara("name");
		int kind=getParaToInt("kind");
		String address=getPara("address");
		String page=getPara("page");
		BannerWwbzgl bn=new BannerWwbzgl();
		bn.setName(name);
		bn.setKind(kind);
		bn.setStates(0);
		if(kind==0) {
			bn.setContent(page);
		}else if(kind==1) {
			bn.setContent(address);
		}else if(kind==2) {
			if(null != lpic) {
				boolean flag = false;
				String fileName = "";
				String fileNamePath = "";
						fileName = lpic.getFileName();// 获取保存文件的文件名
						fileNamePath = lpic.getUploadPath();
						String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length());
						fileName = (System.currentTimeMillis()+90) + fileType;
						File img = new File(fileNamePath + "/" + fileName);
						if (img.exists()) {
							img.delete();
						}
						flag = lpic.getFile().renameTo(img);
						System.out.println(fileNamePath);
						System.out.println("icon/"+img.getName());
						bn.setContent("https://www.weishengtai.club/wwbz/icon/"+img.getName());
			}else {
				
			}
		}
		if(null != url) {
			boolean flag = false;
			String fileName = "";
			String fileNamePath = "";
					fileName = url.getFileName();// 获取保存文件的文件名
					fileNamePath = url.getUploadPath();
					String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length());
					fileName = System.currentTimeMillis() + fileType;
					File img = new File(fileNamePath + "/" + fileName);
					if (img.exists()) {
						img.delete();
					}
					flag = url.getFile().renameTo(img);
					System.out.println(fileNamePath);
					System.out.println("icon/"+img.getName());
					bn.setUrl("https://www.weishengtai.club/wwbz/icon/"+img.getName());
		}else {
			
		}
		if(id==0) {
			if(bn.save()) {
				redirect("/mana/bannerlistgl");
			}else {
				
			}
		}else {
			bn.setId(id);
			if(bn.update()) {
				redirect("/mana/bannerlistgl");
			}else {
				
			}
		}
		

	}
	@Before(ManaInterceptor.class)
	public void articlelistgl() {
		List<ArticleWwbzgl> al=new ArticleWwbzgl().dao().find("SELECT * from article_wwbzgl where states=0");
		setAttr("al",al);
		render("articlelistgl.jsp");
		
	}
	@Before(ManaInterceptor.class)
	public void addarticlegl() {
		int id=getParaToInt("id");
		if(id==0) {
			ArticleWwbzgl aw=new ArticleWwbzgl();
			aw.setId(0);
			setAttr("aw",aw);
		}else {
			ArticleWwbzgl aw=new ArticleWwbzgl().dao().findById(id);
			setAttr("aw",aw);
		}
		
		render("addarticlegl.jsp");
		
	}
	@Before(ManaInterceptor.class)
	public void delarticlegl() {
		int id=getParaToInt("id");
		ArticleWwbzgl aw=new ArticleWwbzgl().dao().findById(id);
		if(null!=aw) {
			aw.setStates(-1);
			if(aw.update()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}else {
			setAttr("result",1);
		}
		renderJson();
	}
	@Before(ManaInterceptor.class)
	public void savearticlegl() {
		UploadFile imgs = getFile("img");
		int id=getParaToInt("id");
		String name=getPara("name");
		String src=getPara("src");
		String day=getPara("isday");
		//System.out.println("id="+id);
		System.out.println("day="+day);
		System.out.println("name="+name);
		System.out.println("src="+src);
		ArticleWwbzgl aw=new ArticleWwbzgl();
		if(null==day) {
			
			aw.setIsday(0);
		}else {
			aw.setIsday(1);
			List<ArticleWwbzgl> awl=new ArticleWwbzgl().dao().find("SELECT * from article_wwbzgl where states=0 and isday=1");
			for (ArticleWwbzgl articleWwbzgl : awl) {
				articleWwbzgl.setIsday(0);
				articleWwbzgl.update();
			}
		}
		aw.setName(name);
		aw.setStates(0);
		aw.setUrl(src);
		
		if(null != imgs) {
			boolean flag = false;
			String fileName = "";
			String fileNamePath = "";
					fileName = imgs.getFileName();// 获取保存文件的文件名
					fileNamePath = imgs.getUploadPath();
					String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length());
					fileName = (System.currentTimeMillis()+90) + fileType;
					File img = new File(fileNamePath + "/" + fileName);
					if (img.exists()) {
						img.delete();
					}
					flag = imgs.getFile().renameTo(img);
					System.out.println(fileNamePath);
					System.out.println("icon/"+img.getName());
					aw.setImg("https://www.weishengtai.club/wwbz/icon/"+img.getName());
		}else {
			
		}
		if(id==0) {
			if(aw.save()) {
				redirect("/mana/articlelistgl");
			}else {
				
			}
		}else {
			aw.setId(id);
			if(aw.update()) {
				redirect("/mana/articlelistgl");
			}else {
				
			}
		}
		
	}
	@Before(ManaInterceptor.class)
	public void basicwwbz() {
		BasicWwbz bs=new BasicWwbz().findById(1);
		setAttr("basic",bs);
		render("basicwwbz.jsp");
	}
	@Before(ManaInterceptor.class)
	public void changebasicwwbz() {
		UploadFile url = getFile("posterurl");
		UploadFile dealerimg = getFile("dealerimg");
		String dealerword=getPara("dealerword");
		String reword=getPara("reword");
		String aboutus=getPara("aboutus");
		String tel=getPara("tel");
		String pay=getPara("pay");
		String posterword=getPara("posterword");
		BasicWwbz bs=new BasicWwbz().findById(1);
		if(null != url) {
			boolean flag = false;
			String fileName = "";
			String fileNamePath = "";
					fileName = url.getFileName();// 获取保存文件的文件名
					fileNamePath = url.getUploadPath();
					String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length());
					fileName = System.currentTimeMillis() + fileType;
					File img = new File(fileNamePath + "/" + fileName);
					if (img.exists()) {
						img.delete();
					}
					flag = url.getFile().renameTo(img);
					System.out.println(fileNamePath);
					System.out.println("icon/"+img.getName());
					bs.setPosterurl("https://www.weishengtai.club/wwbz/icon/"+img.getName());
					
		}
		if(null != dealerimg) {
			boolean flag = false;
			String fileName = "";
			String fileNamePath = "";
					fileName = dealerimg.getFileName();// 获取保存文件的文件名
					fileNamePath = dealerimg.getUploadPath();
					String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length());
					fileName = System.currentTimeMillis() + fileType;
					File img = new File(fileNamePath + "/" + fileName);
					if (img.exists()) {
						img.delete();
					}
					flag = dealerimg.getFile().renameTo(img);
					System.out.println(fileNamePath);
					System.out.println("icon/"+img.getName());
					bs.setDealerimg("https://www.weishengtai.club/wwbz/icon/"+img.getName());
					
		}
		bs.setDealerword(dealerword);
		bs.setAboutus(aboutus);
		bs.setReword(reword);
		bs.setTel(tel);
		bs.setPay(pay);
		bs.setPosterword(posterword);
		bs.update();
		setAttr("basic",bs);
		render("basicwwbz.jsp");
	}
	@Before(ManaInterceptor.class)
	public void addgoodsrecycle() {
		List<GoodsWwbz> gdl=new GoodsWwbz().dao().find("SELECT a.*,b.name categroy from goods_wwbz a LEFT JOIN category_wwbz b on a.categroyid=b.id  where a.states=-1");
		setAttr("list",gdl);
		render("goodslistrecycle.jsp");
	}
	@Before(ManaInterceptor.class)
	public void recyclegoods() {
		int id=getParaToInt("id");
		GoodsWwbz gd=new GoodsWwbz().dao().findById(id);
		if(null!=gd) {
			gd.setStates(0);
			if(gd.update()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}else {
			setAttr("result",1);
		}
		renderJson();
	}
	@Before(ManaInterceptor.class)
	public void invite() {
		List<Record> list=Db.find("SELECT c.*,d.name fname from(SELECT b.nickname,b.wxicon,b.id,a.`name`,a.openid,b.fatheropenid from dealer_wwbz a LEFT JOIN user_wwbz b on a.openid=b.openid where a.states=0 and a.state=1) c LEFT JOIN dealer_wwbz d on c.fatheropenid=d.openid ORDER BY c.id");
		setAttr("list",list);
		List<DealerWwbz> dl=new DealerWwbz().dao().find("SELECT * from dealer_wwbz where states=0 and state=1");
		setAttr("dealerlist",dl);
		render("invite.jsp");
	}
	@Before(ManaInterceptor.class)
	public void changeinvite() {
		String openid=getPara("openid");
		String fopenid=getPara("fopenid");
		UserWwbz user=new UserWwbz().dao().findFirst("SELECT * FROM user_wwbz where openid= ? and state=0",openid);
		UserWwbz father=new UserWwbz().dao().findFirst("SELECT * FROM user_wwbz where openid= ? and state=0",fopenid);
		if(null!=user&&null!=father) {
			user.setFatheropenid(fopenid);
			if(user.update()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}
		
		renderJson();
	}
	@Before(ManaInterceptor.class)
	public void diabetes() {
		List<QuestionnaireWwbz> qnl=new QuestionnaireWwbz().dao().find("SELECT * FROM questionnaire_wwbz where states=0 and paperid=2 and parentid=0 ORDER BY `order`");
		List<QuestionnaireWwbz> anl=new QuestionnaireWwbz().dao().find("SELECT * FROM questionnaire_wwbz where states=0 and paperid=2");
		setAttr("qlist",qnl);
		setAttr("list",anl);
		render("diabetesquestion.jsp");
	}
	@Before(ManaInterceptor.class)
	public void adddiabetesquestion() {
		int number=getParaToInt("number");
		String content=getPara("content");
		int id=getParaToInt("id");
		QuestionnaireWwbz qn=new QuestionnaireWwbz();
		qn.setContent(content);
		qn.setOrder(number);
		qn.setPaperid(2);
		qn.setParentid(0);
		qn.setStates(0);
		qn.setScore(0);
		if(id==0) {
			if(qn.save()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}else {
			qn.setId(id);
			if(qn.update()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}
		
		renderJson();
	}
	@Before(ManaInterceptor.class)
	public void deldiabetesquestion() {
		int id=getParaToInt("id");
		QuestionnaireWwbz qn=new QuestionnaireWwbz().findById(id);
		if(null!=qn) {
			qn.setStates(-1);
			if(qn.update()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}else {
			setAttr("result",0);
		}
		
		renderJson();
	}
	@Before(ManaInterceptor.class)
	public void adddiabetesquestionson() {
		int fatherid=getParaToInt("fatherid");
		int id=getParaToInt("id");
		String content=getPara("content");
		int score=getParaToInt("right");
		QuestionnaireWwbz qn=new QuestionnaireWwbz();
		qn.setContent(content);
		qn.setOrder(0);
		qn.setPaperid(2);
		qn.setStates(0);
		
		if(1==score) {
			qn.setScore(5);
			List<QuestionnaireWwbz> ql=new QuestionnaireWwbz().dao().find("SELECT * from questionnaire_wwbz where states=0 and parentid=?",fatherid);
			for (QuestionnaireWwbz questionnaireWwbz : ql) {
				System.out.println(questionnaireWwbz.getId());
				questionnaireWwbz.setScore(0);
				questionnaireWwbz.update();
			}
			
		}else {
			qn.setScore(0);
		}
		if(id==0) {
			qn.setParentid(fatherid);
			if(qn.save()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}else {
			qn.setId(id);
			if(qn.update()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}
		
		renderJson();
	}
	@Before(ManaInterceptor.class)
	public void diabetesprelist() {
		List<GlucometerWwbz> count=new GlucometerWwbz().dao().find("SELECT * from glucometer_wwbz where state=3");
		setAttr("list",count);
		render("diabetesprelist.jsp");
	}
	@Before(ManaInterceptor.class)
	public void diabetesdeallist() {
		List<GlucometerWwbz> count=new GlucometerWwbz().dao().find("SELECT * from glucometer_wwbz where state=4");
		setAttr("list",count);
		render("diabetesdeallist.jsp");
	}
	@Before(ManaInterceptor.class)
	public void dealdiabetes() {
		int id=getParaToInt("id");
		GlucometerWwbz gl=new GlucometerWwbz().findById(id);
		if(null!=gl) {
			gl.setState(4);
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
	@Before(ManaInterceptor.class)
	public void oldcommentlist() {
		List<CommentWwbz> ctl=new CommentWwbz().dao().find("SELECT * from comment_wwbz where states=-2 and fathercommentid=0 and goodsid=0");
		setAttr("list",ctl);
		render("oldcommentlist.jsp");
	}
	@Before(ManaInterceptor.class)
	public void dealoldcommentlist() {
		List<CommentWwbz> ctl=new CommentWwbz().dao().find("SELECT * from comment_wwbz where states=-2 and fathercommentid=0 and goodsid!=0");
		setAttr("list",ctl);
		render("dealoldcommentlist.jsp");
	}
	@Before(ManaInterceptor.class)
	public void oldcomment() {
		int id =getParaToInt("id");
		CommentWwbz ct=new CommentWwbz().findById(id);
		setAttr("ct",ct);
		JSONArray pic = new JSONArray().parseArray(ct.getPic());
		setAttr("pic",pic);
		render("oldcomment.jsp");
	}
	@Before(ManaInterceptor.class)
	public void passoldcomment() {
		int id =getParaToInt("id");
		String des=getPara("des");
		int goodsid=getParaToInt("goodsid");
		String show=getPara("show");
		System.out.println("show="+show);
		CommentWwbz ct=new CommentWwbz().findById(id);
		if(null!=ct) {
			ct.setContent(des);
			if(null==show) {
				ct.setStates(-2);
			}else {
				ct.setStates(0);
				ct.setPstar(5);
				ct.setBstar(5);
				ct.setTstar(5);
				ct.setIsnew(0);
			}
			
			ct.setGoodsid(goodsid);
			
			if(ct.update()) {
				setAttr("result",1);
				
				redirect("/mana/oldcommentlist");
			}else {
				setAttr("result",0);
			}
		}else {
			setAttr("result",0);
		}
		
	}
	@Before(ManaInterceptor.class)
	public void invitationlist() {
		List<InvitationWwbz> itl=new InvitationWwbz().dao.find("SELECT * from invitation_wwbz where states=0");
		setAttr("list",itl);
		render("invitationlist.jsp");
	}
	@Before(ManaInterceptor.class)
	public void  dealerupdatelist() {
		List<DealerWwbz> dl=new DealerWwbz().dao.find("SELECT * from dealer_wwbz where isapply=1 and states=0 and state=1");
		setAttr("list",dl);
		render("dealerupdatelist.jsp");
	}
	@Before(ManaInterceptor.class)
	public void updatepass() {
		int id=getParaToInt("id");
		DealerWwbz dl=new DealerWwbz().findById(id);
		if(null!=dl) {
			dl.setIsapply(0);
			dl.setIshead(1);
			dl.setHeadpercent(1.0);
			if(dl.update()) {
				
			}else {
				
			}
		}else {
			
		}
		redirect("/mana/dealerupdatelist");
	}
	@Before(ManaInterceptor.class)
	public void updatedel() {
		int id=getParaToInt("id");
		DealerWwbz dl=new DealerWwbz().findById(id);
		if(null!=dl) {
			dl.setIsapply(0);
			if(dl.update()) {
				
			}else {
				
			}
		}else {
			
		}
		redirect("/mana/dealerupdatelist");
	}
	@Before(ManaInterceptor.class)
	public void changereward() {
		int id=getParaToInt("id");
		int reward=getParaToInt("reward");
		String reason=getPara("reason");
		UserWwbz user=new UserWwbz().findById(id);
		if(null!=user) {
			user.setReward((Integer.parseInt(user.getReward())+reward)+"");
			if(user.update()) {
				setAttr("result",1);
				OrderWwbz order =new OrderWwbz();
				order.setMoney(Math.abs(reward)+"");
				order.setBuytime(new Date());
				order.setOpenid(user.getOpenid());
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
				if(reward>0) {
					order.setDirection(1);
				}else {
					order.setDirection(0);
				}
				order.setGoodsname(reason);
				order.save();
			}else {
				setAttr("result",0);
			}
		}else {
			setAttr("result",0);
		}
		renderJson();
	}	
	@Before(ManaInterceptor.class)
	public void bargainlist() {
		List<BargaingoodsWwbz> lbg=new BargaingoodsWwbz().find("SELECT a.*,b.`name` goodsname from bargaingoods_wwbz a LEFT JOIN goods_wwbz b on a.goodsid=b.id where a.states =0");
		setAttr("list",lbg);
		render("bargainlist.jsp");
	}
	@Before(ManaInterceptor.class)
	public void addbargain() {
		int id=getParaToInt("id");
		if(id==0) {
			BargaingoodsWwbz bg=new BargaingoodsWwbz();
			bg.setId(0);
			setAttr("bg",bg);
		}else {
			BargaingoodsWwbz bg=new BargaingoodsWwbz().findById(id);
			setAttr("bg",bg);
		}
		List<GoodsWwbz> gl=new GoodsWwbz().dao().find("SELECT * from goods_wwbz where states=0");
		setAttr("goodslist",gl);
		render("addbargain.jsp");
	}
	@Before(ManaInterceptor.class)
	public void delbargain() {
		int id=getParaToInt("id");
		BargaingoodsWwbz bg=new BargaingoodsWwbz().findById(id);
		if(null!=bg) {
			bg.setStates(-1);
			if(bg.update()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}else {
			setAttr("result",0);
		}
		renderJson();
	}
	@Before(ManaInterceptor.class)
	public void savebargain() throws ParseException {
		UploadFile url = getFile("url");
		int id=getParaToInt("id");
		String name=getPara("name");
		int goodsid=getParaToInt("goods");
		String endtime=getPara("endtime");
		int percent=getParaToInt("percent");
		int high=getParaToInt("high");
		int low=getParaToInt("low");
		int ohigh=getParaToInt("ohigh");
		int olow=getParaToInt("olow");
		String word=getPara("word");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date et= sdf.parse(endtime.replace("T", " "));
		if(id==0) {
			BargaingoodsWwbz bg=new BargaingoodsWwbz();
			bg.setEndtime(et);
			bg.setStates(0);
			bg.setName(name);
			bg.setPercent(percent+"");
			bg.setGoodsid(goodsid);
			bg.setHigh(high);
			bg.setLow(low);
			bg.setWord(word);
			bg.setOhigh(ohigh);
			bg.setOlow(olow);
			if(null != url) {
				boolean flag = false;
				String fileName = "";
				String fileNamePath = "";
						fileName = url.getFileName();// 获取保存文件的文件名
						fileNamePath = url.getUploadPath();
						String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length());
						fileName = System.currentTimeMillis() + fileType;
						File img = new File(fileNamePath + "/" + fileName);
						if (img.exists()) {
							img.delete();
						}
						flag = url.getFile().renameTo(img);
						System.out.println(fileNamePath);
						System.out.println("icon/"+img.getName());
						bg.setImg("https://www.weishengtai.club/wwbz/icon/"+img.getName());
						
			}
			bg.save();
		}else {
			BargaingoodsWwbz bg=new BargaingoodsWwbz().findById(id);
			bg.setEndtime(et);
			bg.setStates(0);
			bg.setName(name);
			bg.setPercent(percent+"");
			bg.setGoodsid(goodsid);
			bg.setHigh(high);
			bg.setLow(low);
			bg.setWord(word);
			bg.setOhigh(ohigh);
			bg.setOlow(olow);
			if(null != url) {
				boolean flag = false;
				String fileName = "";
				String fileNamePath = "";
						fileName = url.getFileName();// 获取保存文件的文件名
						fileNamePath = url.getUploadPath();
						String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length());
						fileName = System.currentTimeMillis() + fileType;
						File img = new File(fileNamePath + "/" + fileName);
						if (img.exists()) {
							img.delete();
						}
						flag = url.getFile().renameTo(img);
						System.out.println(fileNamePath);
						System.out.println("icon/"+img.getName());
						bg.setImg("https://www.weishengtai.club/wwbz/icon/"+img.getName());
						
			}
			bg.update();
		}
		redirect("/mana/bargainlist");
	}
	@Before(ManaInterceptor.class)
	public void bargainapply() {
		List<BargainorderWwbz> bol=new BargainorderWwbz().dao().find("SELECT * from bargainorder_wwbz where states=0 and fid=0 and state=2");
		setAttr("list",bol);
		render("bargainapply.jsp");
	}
	@Before(ManaInterceptor.class)
	public void bargaindeal() {
		List<BargainorderWwbz> bol=new BargainorderWwbz().dao().find("SELECT * from bargainorder_wwbz where states=0 and fid=0 and state=3");
		setAttr("list",bol);
		render("bargaindeal.jsp");
	}
	@Before(ManaInterceptor.class)
	public void dealbargain() {
		int id=getParaToInt("id");
		BargainorderWwbz bg=new BargainorderWwbz().findById(id);
		if(null!=bg) {
			bg.setState(3);
			if(bg.update()) {
				setAttr("result",1);
			}else {
				setAttr("result",0);
			}
		}else {
			setAttr("result",0);
		}
		renderJson();
	}
}
