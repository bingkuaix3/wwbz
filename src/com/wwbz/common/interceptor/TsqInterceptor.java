package com.wwbz.common.interceptor;

import javax.servlet.http.HttpSession;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.kit.PropKit;

public class TsqInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		// TODO Auto-generated method stub
		Controller cont = inv.getController();
		HttpSession session = cont.getSession();
		if (null == session) {
			cont.redirect("/", true);
		} else {
			String admin = (String) session.getAttribute("tsqmanager");
			String password = (String) session.getAttribute("tsqmpassword");
			if (null == admin || null == password || !PropKit.get("tsqmanager").equals(admin)
					|| !PropKit.get("tsqmpassword").equals(password)) {
				cont.redirect("/tsq", true);
			} else {
				inv.invoke();
			}
		}
	}

}
