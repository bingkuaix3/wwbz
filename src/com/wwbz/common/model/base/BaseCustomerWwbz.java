package com.wwbz.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseCustomerWwbz<M extends BaseCustomerWwbz<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}

	public java.lang.Integer getId() {
		return get("id");
	}

	public void setName(java.lang.String name) {
		set("name", name);
	}

	public java.lang.String getName() {
		return get("name");
	}

	public void setDes(java.lang.String des) {
		set("des", des);
	}

	public java.lang.String getDes() {
		return get("des");
	}

	public void setPic(java.lang.String pic) {
		set("pic", pic);
	}

	public java.lang.String getPic() {
		return get("pic");
	}

	public void setStates(java.lang.Integer states) {
		set("states", states);
	}

	public java.lang.Integer getStates() {
		return get("states");
	}

}
