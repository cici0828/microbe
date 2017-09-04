package net.dagene.pmis.pathology.model;

import java.util.Date;

public class CsltModel {
	private Integer pid;
	private String expertname;
	private String expertid;
	private Date commitdate;
	private byte[] attachment;
	private String cstate;
	private String rslt_comments;
	private Integer ccid;
	private String expertcslt;
	private String folderno;
	private String barcode;
	private String expertcslt_en;

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
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

	public Date getCommitdate() {
		return commitdate;
	}

	public void setCommitdate(Date commitdate) {
		this.commitdate = commitdate;
	}

	public byte[] getAttachment() {
		return attachment;
	}

	public void setAttachment(byte[] attachment) {
		this.attachment = attachment;
	}

	public String getCstate() {
		return cstate;
	}

	public void setCstate(String cstate) {
		this.cstate = cstate;
	}

	public String getRslt_comments() {
		return rslt_comments;
	}

	public void setRslt_comments(String rslt_comments) {
		this.rslt_comments = rslt_comments;
	}

	public Integer getCcid() {
		return ccid;
	}

	public void setCcid(Integer ccid) {
		this.ccid = ccid;
	}

	public String getExpertcslt() {
		return expertcslt;
	}

	public void setExpertcslt(String expertcslt) {
		this.expertcslt = expertcslt;
	}

	public String getFolderno() {
		return folderno;
	}

	public void setFolderno(String folderno) {
		this.folderno = folderno;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getExpertcslt_en() {
		return expertcslt_en;
	}

	public void setExpertcslt_en(String expertcslt_en) {
		this.expertcslt_en = expertcslt_en;
	}
}
