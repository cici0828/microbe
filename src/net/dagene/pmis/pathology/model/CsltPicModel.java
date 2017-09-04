package net.dagene.pmis.pathology.model;

import java.util.Date;

public class CsltPicModel {
	private String id;
	private Integer ccid;//t_pty_cslt表ID
	private byte[] picture;
	private String folderno;
	private String orderno;
	private String type;
	private Integer sortorder;
	private Integer display;
	private String localdir;
	private String describe;
	private Date syn;
	private String describe_en;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getCcid() {
		return ccid;
	}

	public void setCcid(Integer ccid) {
		this.ccid = ccid;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public String getFolderno() {
		return folderno;
	}

	public void setFolderno(String folderno) {
		this.folderno = folderno;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getSortorder() {
		return sortorder;
	}

	public void setSortorder(Integer sortorder) {
		this.sortorder = sortorder;
	}

	public Integer getDisplay() {
		return display;
	}

	public void setDisplay(Integer display) {
		this.display = display;
	}

	public String getLocaldir() {
		return localdir;
	}

	public void setLocaldir(String localdir) {
		this.localdir = localdir;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public Date getSyn() {
		return syn;
	}

	public void setSyn(Date syn) {
		this.syn = syn;
	}

	public String getDescribe_en() {
		return describe_en;
	}

	public void setDescribe_en(String describe_en) {
		this.describe_en = describe_en;
	}
}
