package com.wwbz.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseUserWwbz<M extends BaseUserWwbz<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}

	public java.lang.Integer getId() {
		return get("id");
	}

	public void setNickname(java.lang.String nickname) {
		set("nickname", nickname);
	}

	public java.lang.String getNickname() {
		return get("nickname");
	}

	public void setWxicon(java.lang.String wxicon) {
		set("wxicon", wxicon);
	}

	public java.lang.String getWxicon() {
		return get("wxicon");
	}

	public void setState(java.lang.Integer state) {
		set("state", state);
	}

	public java.lang.Integer getState() {
		return get("state");
	}

	public void setUnionid(java.lang.String unionid) {
		set("unionid", unionid);
	}

	public java.lang.String getUnionid() {
		return get("unionid");
	}

	public void setOpenid(java.lang.String openid) {
		set("openid", openid);
	}

	public java.lang.String getOpenid() {
		return get("openid");
	}

	public void setJointime(java.util.Date jointime) {
		set("jointime", jointime);
	}

	public java.util.Date getJointime() {
		return get("jointime");
	}

	public void setReward(java.lang.String reward) {
		set("reward", reward);
	}

	public java.lang.String getReward() {
		return get("reward");
	}

	public void setJoin(int i) {
		set("join", i);
	}

	public java.lang.Long getJoin() {
		return get("join");
	}

	public void setQrcode(java.lang.String qrcode) {
		set("qrcode", qrcode);
	}

	public java.lang.String getQrcode() {
		return get("qrcode");
	}

	public void setQrcodetime(java.util.Date qrcodetime) {
		set("qrcodetime", qrcodetime);
	}

	public java.util.Date getQrcodetime() {
		return get("qrcodetime");
	}

	public void setFatheropenid(java.lang.String fatheropenid) {
		set("fatheropenid", fatheropenid);
	}

	public java.lang.String getFatheropenid() {
		return get("fatheropenid");
	}

	public void setIsreward(java.lang.Integer isreward) {
		set("isreward", isreward);
	}

	public java.lang.Integer getIsreward() {
		return get("isreward");
	}

	public void setTel(java.lang.String tel) {
		set("tel", tel);
	}

	public java.lang.String getTel() {
		return get("tel");
	}

	public void setRewardtimes(java.lang.Integer rewardtimes) {
		set("rewardtimes", rewardtimes);
	}

	public java.lang.Integer getRewardtimes() {
		return get("rewardtimes");
	}

	public void setIsnewreward(java.lang.Integer isnewreward) {
		set("isnewreward", isnewreward);
	}

	public java.lang.Integer getIsnewreward() {
		return get("isnewreward");
	}

	public void setIsnew(java.lang.Integer isnew) {
		set("isnew", isnew);
	}

	public java.lang.Integer getIsnew() {
		return get("isnew");
	}

	public void setLock(java.lang.Integer lock) {
		set("lock", lock);
	}

	public java.lang.Integer getLock() {
		return get("lock");
	}

	public void setMoney(java.lang.String money) {
		set("money", money);
	}

	public java.lang.String getMoney() {
		return get("money");
	}

}
