package net.dagene.pmis.pathology.model;

import java.util.Date;

public class CsltProgramModel {
	private Integer pid;
	private String pt_barcode;
	private String state;
	private String expertname;
	private String expertid;
	private Date expertdate;
	private Date expertfinishdate;
	private String dept;
	private int picnum;
	private String loginname;
	private Date logindate;
	private String scandept;
	private float fee;
	


	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getPt_barcode() {
		return pt_barcode;
	}
	public void setPt_barcode(String pt_barcode) {
		this.pt_barcode = pt_barcode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getExpertname() {
		return expertname;
	}
	public void setExpertname(String expertname) {
		this.expertname = expertname;
	}
	public String getExpertid() {
		return expertid;
	}
	public void setExpertid(String expertid) {
		this.expertid = expertid;
	}
	public Date getExpertdate() {
		return expertdate;
	}
	public void setExpertdate(Date expertdate) {
		this.expertdate = expertdate;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public int getPicnum() {
		return picnum;
	}
	public void setPicnum(int picnum) {
		this.picnum = picnum;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public Date getLogindate() {
		return logindate;
	}
	public void setLogindate(Date logindate) {
		this.logindate = logindate;
	}
	public String getScandept() {
		return scandept;
	}
	public void setScandept(String scandept) {
		this.scandept = scandept;
	}

	public float getFee() {
		return fee;
	}
	public void setFee(float fee) {
		this.fee = fee;
	}
	public Date getExpertfinishdate() {
		return expertfinishdate;
	}
	public void setExpertfinishdate(Date expertfinishdate) {
		this.expertfinishdate = expertfinishdate;
	}

	
}
