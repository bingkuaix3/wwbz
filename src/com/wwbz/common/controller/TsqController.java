package com.wwbz.common.controller;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.kit.PropKit;
import com.wwbz.common.interceptor.TsqInterceptor;
import com.wwbz.common.model.CommentWwbz;

public class TsqController extends Controller {
	public void index() {
		System.out.println("tsqindex");
		render("login.jsp");
	}
	public void login() {
		String admin = getPara("name");
		String password = getPara("password");
		if (null == admin || null == password || !PropKit.get("tsqmanager").equals(admin)
				|| !PropKit.get("tsqmpassword").equals(password)) {
			setAttr("msg", "账号密码错误！");
		} else {
			setAttr("msg", 0);
			setSessionAttr("tsqmanager", admin);
			setSessionAttr("tsqmpassword", password);
		}
		renderJson();
	}
	@Before(TsqInterceptor.class)
	public void commentlist() {
		List<CommentWwbz> ctl=new CommentWwbz().dao().find("SELECT * from comment_wwbz where states=-3 and fathercommentid=0");
		setAttr("list",ctl);
		render("commentlist.jsp");
	}
	@Before(TsqInterceptor.class)
	public void dealcommentlist() {
		List<CommentWwbz> ctl=new CommentWwbz().dao().find("SELECT * from comment_wwbz where states=-2 and fathercommentid=0");
		setAttr("list",ctl);
		render("dealcommentlist.jsp");
	}
	@Before(TsqInterceptor.class)
	public void comment() {
		int id =getParaToInt("id");
		CommentWwbz ct=new CommentWwbz().findById(id);
		setAttr("ct",ct);
		JSONArray pic = new JSONArray().parseArray(ct.getPic());
		setAttr("pic",pic);
		render("comment.jsp");
	}
	@Before(TsqInterceptor.class)
	public void passcomment() {
		int id =getParaToInt("id");
		String des=getPara("des");
		CommentWwbz ct=new CommentWwbz().findById(id);
		if(null!=ct) {
			ct.setContent(des);
			ct.setStates(-2);
			ct.setGoodsid(0);
			if(ct.update()) {
				setAttr("result",1);
				redirect("/tsq/commentlist");
			}else {
				setAttr("result",0);
			}
		}else {
			setAttr("result",0);
		}
		
	}
}
