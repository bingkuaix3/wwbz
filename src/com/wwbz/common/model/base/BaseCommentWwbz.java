package com.wwbz.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseCommentWwbz<M extends BaseCommentWwbz<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}

	public java.lang.Integer getId() {
		return get("id");
	}

	public void setTime(java.util.Date time) {
		set("time", time);
	}

	public java.util.Date getTime() {
		return get("time");
	}

	public void setContent(java.lang.String content) {
		set("content", content);
	}

	public java.lang.String getContent() {
		return get("content");
	}

	public void setOpenid(java.lang.String openid) {
		set("openid", openid);
	}

	public java.lang.String getOpenid() {
		return get("openid");
	}

	public void setGoodsid(java.lang.Integer goodsid) {
		set("goodsid", goodsid);
	}

	public java.lang.Integer getGoodsid() {
		return get("goodsid");
	}

	public void setStates(java.lang.Integer states) {
		set("states", states);
	}

	public java.lang.Integer getStates() {
		return get("states");
	}

	public void setWxicon(java.lang.String wxicon) {
		set("wxicon", wxicon);
	}

	public java.lang.String getWxicon() {
		return get("wxicon");
	}

	public void setNickname(java.lang.String nickname) {
		set("nickname", nickname);
	}

	public java.lang.String getNickname() {
		return get("nickname");
	}

	public void setTonickname(java.lang.String tonickname) {
		set("tonickname", tonickname);
	}

	public java.lang.String getTonickname() {
		return get("tonickname");
	}

	public void setPstar(java.lang.Integer pstar) {
		set("pstar", pstar);
	}

	public java.lang.Integer getPstar() {
		return get("pstar");
	}

	public void setTstar(java.lang.Integer tstar) {
		set("tstar", tstar);
	}

	public java.lang.Integer getTstar() {
		return get("tstar");
	}

	public void setBstar(java.lang.Integer bstar) {
		set("bstar", bstar);
	}

	public java.lang.Integer getBstar() {
		return get("bstar");
	}

	public void setIsnew(java.lang.Integer isnew) {
		set("isnew", isnew);
	}

	public java.lang.Integer getIsnew() {
		return get("isnew");
	}

	public void setFathercommentid(java.lang.Integer fathercommentid) {
		set("fathercommentid", fathercommentid);
	}

	public java.lang.Integer getFathercommentid() {
		return get("fathercommentid");
	}

	public void setTel(java.lang.String tel) {
		set("tel", tel);
	}

	public java.lang.String getTel() {
		return get("tel");
	}

	public void setCity(java.lang.String city) {
		set("city", city);
	}

	public java.lang.String getCity() {
		return get("city");
	}

	public void setIll(java.lang.String ill) {
		set("ill", ill);
	}

	public java.lang.String getIll() {
		return get("ill");
	}

	public void setCl(java.lang.Integer cl) {
		set("cl", cl);
	}

	public java.lang.Integer getCl() {
		return get("cl");
	}

	public void setJt(java.lang.Integer jt) {
		set("jt", jt);
	}

	public java.lang.Integer getJt() {
		return get("jt");
	}

	public void setDw(java.lang.Integer dw) {
		set("dw", dw);
	}

	public java.lang.Integer getDw() {
		return get("dw");
	}

	public void setNd(java.lang.Integer nd) {
		set("nd", nd);
	}

	public java.lang.Integer getNd() {
		return get("nd");
	}

	public void setHistory(java.lang.String history) {
		set("history", history);
	}

	public java.lang.String getHistory() {
		return get("history");
	}

	public void setPic(java.lang.String pic) {
		set("pic", pic);
	}

	public java.lang.String getPic() {
		return get("pic");
	}

}
