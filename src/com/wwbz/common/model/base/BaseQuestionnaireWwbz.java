package com.wwbz.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseQuestionnaireWwbz<M extends BaseQuestionnaireWwbz<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}

	public java.lang.Integer getId() {
		return get("id");
	}

	public void setPaperid(java.lang.Integer paperid) {
		set("paperid", paperid);
	}

	public java.lang.Integer getPaperid() {
		return get("paperid");
	}

	public void setParentid(java.lang.Integer parentid) {
		set("parentid", parentid);
	}

	public java.lang.Integer getParentid() {
		return get("parentid");
	}

	public void setOrder(java.lang.Integer order) {
		set("order", order);
	}

	public java.lang.Integer getOrder() {
		return get("order");
	}

	public void setContent(java.lang.String content) {
		set("content", content);
	}

	public java.lang.String getContent() {
		return get("content");
	}

	public void setScore(java.lang.Integer score) {
		set("score", score);
	}

	public java.lang.Integer getScore() {
		return get("score");
	}

	public void setStates(java.lang.Integer states) {
		set("states", states);
	}

	public java.lang.Integer getStates() {
		return get("states");
	}

}
