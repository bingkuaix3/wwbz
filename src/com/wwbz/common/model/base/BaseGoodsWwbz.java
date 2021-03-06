package com.wwbz.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseGoodsWwbz<M extends BaseGoodsWwbz<M>> extends Model<M> implements IBean {

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

	public void setPic(java.lang.String pic) {
		set("pic", pic);
	}

	public java.lang.String getPic() {
		return get("pic");
	}

	public void setPrice(java.lang.String price) {
		set("price", price);
	}

	public java.lang.String getPrice() {
		return get("price");
	}

	public void setState(java.lang.Integer state) {
		set("state", state);
	}

	public java.lang.Integer getState() {
		return get("state");
	}

	public void setNumber(java.lang.Integer number) {
		set("number", number);
	}

	public java.lang.Integer getNumber() {
		return get("number");
	}

	public void setStime(java.util.Date stime) {
		set("stime", stime);
	}

	public java.util.Date getStime() {
		return get("stime");
	}

	public void setSale(java.lang.String sale) {
		set("sale", sale);
	}

	public java.lang.String getSale() {
		return get("sale");
	}

	public void setEtime(java.util.Date etime) {
		set("etime", etime);
	}

	public java.util.Date getEtime() {
		return get("etime");
	}

	public void setStates(java.lang.Integer states) {
		set("states", states);
	}

	public java.lang.Integer getStates() {
		return get("states");
	}

	public void setDiagram(java.lang.String diagram) {
		set("diagram", diagram);
	}

	public java.lang.String getDiagram() {
		return get("diagram");
	}

	public void setKind(java.lang.Integer kind) {
		set("kind", kind);
	}

	public java.lang.Integer getKind() {
		return get("kind");
	}

	public void setDes(java.lang.String des) {
		set("des", des);
	}

	public java.lang.String getDes() {
		return get("des");
	}

	public void setCount(java.lang.Integer count) {
		set("count", count);
	}

	public java.lang.Integer getCount() {
		return get("count");
	}

	public void setStandard(java.lang.String standard) {
		set("standard", standard);
	}

	public java.lang.String getStandard() {
		return get("standard");
	}

	public void setImgf(java.lang.String imgf) {
		set("imgf", imgf);
	}

	public java.lang.String getImgf() {
		return get("imgf");
	}

	public void setImgs(java.lang.String imgs) {
		set("imgs", imgs);
	}

	public java.lang.String getImgs() {
		return get("imgs");
	}

	public void setImgt(java.lang.String imgt) {
		set("imgt", imgt);
	}

	public java.lang.String getImgt() {
		return get("imgt");
	}

	public void setCategroyid(java.lang.Integer categroyid) {
		set("categroyid", categroyid);
	}

	public java.lang.Integer getCategroyid() {
		return get("categroyid");
	}

	public void setOffset(java.lang.Integer offset) {
		set("offset", offset);
	}

	public java.lang.Integer getOffset() {
		return get("offset");
	}

}
