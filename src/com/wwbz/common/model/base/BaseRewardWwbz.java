package com.wwbz.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseRewardWwbz<M extends BaseRewardWwbz<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}

	public java.lang.Integer getId() {
		return get("id");
	}

	public void setGoods(java.lang.Double goods) {
		set("goods", goods);
	}

	public java.lang.Double getGoods() {
		return get("goods");
	}

	public void setArchives(java.lang.Integer archives) {
		set("archives", archives);
	}

	public java.lang.Integer getArchives() {
		return get("archives");
	}

	public void setShare(java.lang.Integer share) {
		set("share", share);
	}

	public java.lang.Integer getShare() {
		return get("share");
	}

	public void setDay(java.lang.Integer day) {
		set("day", day);
	}

	public java.lang.Integer getDay() {
		return get("day");
	}

	public void setOldlow(java.lang.Integer oldlow) {
		set("oldlow", oldlow);
	}

	public java.lang.Integer getOldlow() {
		return get("oldlow");
	}

	public void setOldhigh(java.lang.Integer oldhigh) {
		set("oldhigh", oldhigh);
	}

	public java.lang.Integer getOldhigh() {
		return get("oldhigh");
	}

	public void setNewlow(java.lang.Integer newlow) {
		set("newlow", newlow);
	}

	public java.lang.Integer getNewlow() {
		return get("newlow");
	}

	public void setNewhigh(java.lang.Integer newhigh) {
		set("newhigh", newhigh);
	}

	public java.lang.Integer getNewhigh() {
		return get("newhigh");
	}

	public void setSharetitle(java.lang.String sharetitle) {
		set("sharetitle", sharetitle);
	}

	public java.lang.String getSharetitle() {
		return get("sharetitle");
	}

	public void setShareimg(java.lang.String shareimg) {
		set("shareimg", shareimg);
	}

	public java.lang.String getShareimg() {
		return get("shareimg");
	}

}
