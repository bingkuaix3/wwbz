package com.wwbz.common.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.wwbz.common.model.AllotWwbz;
import com.wwbz.common.model.ArticleWwbzgl;
import com.wwbz.common.model.BannerWwbz;
import com.wwbz.common.model.BannerWwbzgl;
import com.wwbz.common.model.BasicWwbzgl;
import com.wwbz.common.model.DealerWwbz;
import com.wwbz.common.model.DivideWwbz;
import com.wwbz.common.model.OrderWwbz;
import com.wwbz.common.model.UserWwbz;

public class GlController extends Controller {
	public void index() {
		System.out.println("gl");
	}
	public void getbasic() {
		BasicWwbzgl bc=new BasicWwbzgl().findById(1);
		setAttr("basic",bc.getProtocol());
		setAttr("bc",bc);
		renderJson();
	}
	public void logingl() {
		String account=getPara("account");
		String password=getPara("password");
		DealerWwbz dl=new DealerWwbz().dao().findFirst("SELECT * from dealer_wwbz where tel =? and states =0",account);
		if(null!=dl) {
			if(dl.getState()==0) {
				setAttr("result",4);
			}else {
				System.out.println(dl.getPassword());
				System.out.println(password.equals(dl.getPassword()));
				if(password.equals(dl.getPassword())) {

					setAttr("result",1);
					UserWwbz user=new UserWwbz().dao().findFirst("SELECT * from user_wwbz where state=0 and openid= ?",dl.getOpenid());
					setAttr("user",user);
				}else {
					setAttr("result",3);
				}
			}

		}else {
			setAttr("result",2);
		}
		renderJson();
	}
	public void getinfogl() {
		String openid=getPara("openid");
		DealerWwbz dl=new DealerWwbz().dao().findFirst("SELECT * from dealer_wwbz where openid=? and states=0 and state=1",openid);
		setAttr("info",dl);
		List<AllotWwbz> atl=new AllotWwbz().dao().find("select * from allot_wwbz where states=0");
		Calendar calendar=Calendar.getInstance();    
		calendar.setTime(new Date());    
		System.out.println("现在时间是："+new Date());    
		String year=String.valueOf(calendar.get(Calendar.YEAR));    
		String month=String.valueOf(calendar.get(Calendar.MONTH)+1);    
		String day=String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));    
		String week=String.valueOf(calendar.get(Calendar.DAY_OF_WEEK)-1);    
		System.out.println("现在时间是："+year+"年"+month+"月"+day+"日，星期"+week);
		if(month.length()==1) {
			month="0"+month;
		}
		
		String start=year+"-"+month;
		String endmonth=String.valueOf(Integer.parseInt(month)+1);
		String endyear=year;
		if(endmonth.length()==1) {
			endmonth="0"+endmonth;
		}
		if("12".equals(month)) {
			endmonth="01";
			endyear=String.valueOf(Integer.parseInt(year)+1);
		}
		String end=endyear+"-"+endmonth;
		System.out.println("start="+start);
		System.out.println("end="+end);
		Record record=Db.findFirst("SELECT SUM(money) sum,a.* from order_wwbz a where state!=0 and kind =0 and direction=0 and fatheropenid =? and buytime>? and buytime<? ",openid,start,end);
		Record totalrecord=Db.findFirst("SELECT SUM(money) sum,a.* from order_wwbz a where state!=0 and kind =0 and direction=0 and fatheropenid =?",openid);
		int percent=0;
		System.out.println("record="+record);
		if(null!=record.getStr("sum")) {
			for (AllotWwbz allotWwbz : atl) {
				System.out.println("计算="+record.getStr("sum"));
				System.out.println(allotWwbz.getEnd());
				System.out.println("档位="+allotWwbz.getEnd()*10000);
				
				if(Double.parseDouble(record.getStr("sum"))<allotWwbz.getEnd()*10000) {
					percent=allotWwbz.getPercent();
					break;
				}
			}
			System.out.println(Double.parseDouble(record.getStr("sum")));
			System.out.println(Double.parseDouble(record.getStr("sum"))*percent/100);
			setAttr("totalmoney",Double.parseDouble(totalrecord.getStr("sum")));
			setAttr("money",Double.parseDouble(record.getStr("sum"))*percent/100);
			setAttr("month",Double.parseDouble(record.getStr("sum")));
			setAttr("percent",percent);
		}else {
			for (AllotWwbz allotWwbz : atl) {
				percent=allotWwbz.getPercent();
				break;
			}
			setAttr("money",0);
			setAttr("month",0);
			setAttr("percent",percent);
		}
		
		Record headrecord=Db.findFirst("SELECT SUM(money) sum,a.* from order_wwbz a where state!=0 and kind =0 and direction=0 and headopenid =? and buytime>? and buytime<? ",openid,start,end);
		if(null!=headrecord.getStr("sum")) {
			setAttr("hmonth",headrecord.getStr("sum"));
			setAttr("hmoney",Double.parseDouble(headrecord.getStr("sum"))*1/100);
			setAttr("hpercent",1);
		}else {
			setAttr("hmonth",0);
			setAttr("hmoney",0);
			setAttr("hpercent",1);
		}
		renderJson();
	}
	public void banner() {
		List<BannerWwbzgl> bl=new BannerWwbzgl().dao().find("SELECT * FROM banner_wwbzgl where  states=0");
		setAttr("list",bl);
		renderJson();
	} 
	public void article() {
		List<ArticleWwbzgl> atl=new ArticleWwbzgl().dao().find("SELECT * from article_wwbzgl where states=0 and isday=0");
		ArticleWwbzgl at=new ArticleWwbzgl().dao().findFirst("SELECT * from article_wwbzgl where states=0 and isday=1");
		setAttr("list",atl);
		if(null==at) {
			setAttr("day",0);
		}else {
			setAttr("day",at);
		}
		
		renderJson();
	}
	public void changepassword() {
		String openid=getPara("openid");
		String odp=getPara("odp");
		String nwp=getPara("nwp");
		String nwa=getPara("nwa");
		DealerWwbz dl=new DealerWwbz().dao().findFirst("SELECT * from dealer_wwbz where states=0 and state=1 and openid =?",openid);
		if(null!=dl) {
			if(dl.getPassword().equals(odp)) {
				dl.setPassword(nwp);
				if(dl.update()) {
					setAttr("result",1);
				}else {
					setAttr("result",0);
				}
			}else {
				setAttr("result",3);
			}
		}else {
			setAttr("result",3);
		}
		renderJson();
	}
	public void checkcard() {
		String openid=getPara("openid");
		DealerWwbz dl=new DealerWwbz().dao().findFirst("SELECT * from dealer_wwbz where states=0 and state=1 and openid =?",openid);		
		if(null!=dl) {
			if("0".equals(dl.getAccount())) {
				setAttr("result",0);
			}else {
				setAttr("result",1);
			}
		}else {
			
		}
		renderJson();
	}
	public void updatecard() {
		String number=getPara("number");
		String bank=getPara("bank");
		String branch=getPara("branch");
		String name=getPara("name");
		String openid=getPara("openid");
		DealerWwbz dl=new DealerWwbz().dao().findFirst("SELECT * from dealer_wwbz where states=0 and state=1 and openid =?",openid);	
		if(null!=dl) {
			dl.setAccount(number);
			dl.setBank(bank);
			dl.setBranch(branch);
			dl.setBankname(name);
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
	public void cardinfo() {
		String openid=getPara("openid");
		DealerWwbz dl=new DealerWwbz().dao().findFirst("SELECT * from dealer_wwbz where states=0 and state=1 and openid =?",openid);	
		if(null!=dl) {
			setAttr("dl",dl);
		}else {
			
		}
		renderJson();
	}
	public void moneylist() {
		String openid=getPara("openid");
		int kind=getParaToInt("kind");
		String start=getPara("start");
		String end=getPara("end");
//		Calendar calendar=Calendar.getInstance();    
//		calendar.setTime(new Date());    
//		System.out.println("现在时间是："+new Date());    
//		String year=String.valueOf(calendar.get(Calendar.YEAR));    
//		String month=String.valueOf(calendar.get(Calendar.MONTH)+1);    
//		String day=String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));    
//		String week=String.valueOf(calendar.get(Calendar.DAY_OF_WEEK)-1);    
//		System.out.println("现在时间是："+year+"年"+month+"月"+day+"日，星期"+week);
//		if(month.length()==1) {
//			month="0"+month;
//		}
//		
//		String start=year+"-"+month;
//		String endmonth=String.valueOf(Integer.parseInt(month)+1);
//		String endyear=year;
//		if(endmonth.length()==1) {
//			endmonth="0"+endmonth;
//		}
//		if("12".equals(month)) {
//			endmonth="01";
//			endyear=String.valueOf(Integer.parseInt(year)+1);
//		}
//		String end=endyear+"-"+endmonth;
		System.out.println("start="+start);
		System.out.println("end="+end);
		List<Record> list=null;
		if(kind==0) {
			 list=Db.find("SELECT c.*,d.wxicon from (SELECT SUM(a.money) sum,b.name,b.openid from order_wwbz a LEFT JOIN dealer_wwbz b ON a.fatheropenid = b.openid where a.kind=0 and a.direction=0 and a.buytime>? and a.buytime<? and a.state!=0 and a.fatheropenid!=\"null\" GROUP BY a.fatheropenid) c LEFT JOIN user_wwbz d on c.openid=d.openid  order by sum desc",start,end);
		}else {
			 list=Db.find("SELECT c.*,d.wxicon from (SELECT SUM(a.money) sum,b.name,b.openid from order_wwbz a LEFT JOIN dealer_wwbz b ON a.headopenid = b.openid where a.kind=0 and a.direction=0 and a.buytime>? and a.buytime<? and a.state!=0 and a.headopenid!=\"null\" GROUP BY a.headopenid) c LEFT JOIN user_wwbz d on c.openid=d.openid  order by sum desc",start,end);
		}
		
		int i=1;
		double money=0;
		String hding="";
		for (Record record : list) {
			if(openid.equals(record.get("openid"))) {
				money=record.get("sum");
				hding=record.get("wxicon");
				break;
			}
			i++;
		}
		setAttr("number",i);
		setAttr("money",money);
		setAttr("list",list);
		setAttr("hdimg",hding);
		renderJson();
	}

	public void ordersearch() {
		String openid=getPara("openid");
		Date start=getParaToDate("start");
		Date end=getParaToDate("end");	
		List<OrderWwbz> odl=new OrderWwbz().dao().find("SELECT * from order_wwbz where state!=0 and kind=0 and direction=0 and fatheropenid=? and buytime>? and buytime<?",openid,start,end);
		setAttr("list",odl);
		renderJson();
	}
	
	public void headordersearch() {
		String openid=getPara("openid");
		Date start=getParaToDate("start");
		Date end=getParaToDate("end");	
		List<OrderWwbz> odl=new OrderWwbz().dao().find("SELECT * from order_wwbz where state!=0 and kind=0 and direction=0 and headopenid=? and buytime>? and buytime<?",openid,start,end);
		setAttr("list",odl);
		renderJson();
	}
	public void chart() {
		String openid=getPara("openid");
		Date start=getParaToDate("start");
		Date end=getParaToDate("end");	
		List<OrderWwbz> odl=new OrderWwbz().dao().find("SELECT  DATE_FORMAT(buytime,'%Y-%m-%d') time,SUM(money) sum,a.* from order_wwbz a where state!=0 and kind=0 and direction=0 and fatheropenid=? and buytime>? and buytime<? GROUP BY time",openid,start,end);
		setAttr("list",odl);
		renderJson();
	}
	public void belowchart() {
		String openid=getPara("openid");
		Date start=getParaToDate("start");
		Date end=getParaToDate("end");	
		List<OrderWwbz> odl=new OrderWwbz().dao().find("SELECT  DATE_FORMAT(buytime,'%Y-%m-%d') time,SUM(money) sum,a.* from order_wwbz a where state!=0 and kind=0 and direction=0 and headopenid=? and buytime>? and buytime<? GROUP BY time",openid,start,end);
		setAttr("list",odl);
		renderJson();
	}
	public void getdivide() {
		String openid=getPara("openid");
		List<DivideWwbz> dil=new DivideWwbz().find("SELECT * from divide_wwbz where openid = ?",openid);
		setAttr("list",dil);
		renderJson();
	}
	public void getbelow() {
		String openid=getPara("openid");
		Date start=getParaToDate("start");
		Date end=getParaToDate("end");	
		List<Record> rl=Db.find("SELECT * from (SELECT SUM(money) sum ,fatheropenid openid from order_wwbz where state!=0 and kind=0 and direction=0 and headopenid=? and buytime>? and buytime<? GROUP BY fatheropenid ) c RIGHT JOIN(SELECT b.name,a.openid FROM user_wwbz a  LEFT JOIN dealer_wwbz b on a.openid = b.openid where a.fatheropenid=? and a.state=0 and b.states=0 and b.state=1) d on c.openid =d.openid ORDER BY sum desc",openid,start,end,openid);
		System.out.println(rl.size());
		setAttr("list",rl);
		setAttr("size",rl.size());
		renderJson();
	}
	public void getorder() {
		String openid=getPara("openid");
		int kind=getParaToInt("kind");
		Date start=getParaToDate("start");
		Date end=getParaToDate("end");	
		if(0==kind) {
			List<Record> dl=Db.find("SELECT a.money,a.buytime,a.headpercent,a.fatheropenid openid,b.name from order_wwbz a LEFT JOIN dealer_wwbz b on a.fatheropenid=b.openid where a.state!=0 and  a.kind=0 and  a.direction=0 and  a.fatheropenid=? and  a.buytime>? and  a.buytime<? and b.states=0 and b.state=1",openid,start,end);
			for (Record record : dl) {
				record.set("headpercent", "20");
			}
			setAttr("list",dl);
			System.out.println("kind="+kind);
			System.out.println("start="+start);
			System.out.println("end="+end);
			
		}else if(1==kind) {
			List<Record> dl=Db.find("SELECT a.money,a.buytime,a.headpercent,a.fatheropenid openid,b.name from order_wwbz a LEFT JOIN dealer_wwbz b on a.fatheropenid=b.openid where a.state!=0 and  a.kind=0 and  a.direction=0 and  a.headopenid=? and  a.buytime>? and  a.buytime<? and b.states=0 and b.state=1 ",openid,start,end);
			setAttr("list",dl);
			System.out.println("kind="+kind);
			System.out.println("start="+start);
			System.out.println("end="+end);
		}else if(2==kind) {
			List<Record> dl=Db.find("SELECT a.money,a.buytime,a.headpercent,a.fatheropenid openid,b.name from order_wwbz a LEFT JOIN dealer_wwbz b on a.fatheropenid=b.openid where a.state!=0 and  a.kind=0 and  a.direction=0 and  (a.fatheropenid=? or a.headopenid=?) and  a.buytime>? and  a.buytime<? and b.states=0 and b.state=1",openid,openid,start,end);
			for (Record record : dl) {
				if(record.get("openid").equals(openid)) {
					record.set("headpercent", "20");
				}
			
			}
			setAttr("list",dl);
			System.out.println("kind="+kind);
			System.out.println("start="+start);
			System.out.println("end="+end);
		}
		renderJson();
	}
}
