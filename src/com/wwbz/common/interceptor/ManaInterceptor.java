package com.wwbz.common.interceptor;

import javax.servlet.http.HttpSession;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.kit.PropKit;

public class ManaInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		// TODO Auto-generated method stub
		Controller cont = inv.getController();
		HttpSession session = cont.getSession();
		if (null == session) {
			cont.redirect("/", true);
		} else {
			String admin = (String) session.getAttribute("manager");
			String password = (String) session.getAttribute("mpassword");
			if (null == admin || null == password || !PropKit.get("manager").equals(admin)
					|| !PropKit.get("mpassword").equals(password)) {
				cont.redirect("/mana", true);
			} else {
				inv.invoke();
			}
		}
	}

}
