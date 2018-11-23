package com.wwbz.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseTransactionWwbz<M extends BaseTransactionWwbz<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}

	public java.lang.Integer getId() {
		return get("id");
	}

	public void setOpenid(java.lang.String openid) {
		set("openid", openid);
	}

	public java.lang.String getOpenid() {
		return get("openid");
	}

	public void setState(java.lang.Integer state) {
		set("state", state);
	}

	public java.lang.Integer getState() {
		return get("state");
	}

	public void setBuyopenid(java.lang.String buyopenid) {
		set("buyopenid", buyopenid);
	}

	public java.lang.String getBuyopenid() {
		return get("buyopenid");
	}

	public void setNumber(java.lang.String number) {
		set("number", number);
	}

	public java.lang.String getNumber() {
		return get("number");
	}

	public void setMoney(java.lang.String money) {
		set("money", money);
	}

	public java.lang.String getMoney() {
		return get("money");
	}

	public void setUptime(java.util.Date uptime) {
		set("uptime", uptime);
	}

	public java.util.Date getUptime() {
		return get("uptime");
	}

	public void setBuytime(java.util.Date buytime) {
		set("buytime", buytime);
	}

	public java.util.Date getBuytime() {
		return get("buytime");
	}

	public void setStates(java.lang.Integer states) {
		set("states", states);
	}

	public java.lang.Integer getStates() {
		return get("states");
	}

}