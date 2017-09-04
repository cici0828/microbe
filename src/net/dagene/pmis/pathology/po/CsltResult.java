package net.dagene.pmis.pathology.po;

import java.util.Date;

public class CsltResult {
	private Integer ccid;
	private Integer pid;
	private Integer expertid;
	private String expertname;
	private Date commitdate;
	private String cstate;
	private String expertcslt;
	private String folderno;
	private String barcode;
	private String rslt_comments;

	public Integer getCcid() {
		return ccid;
	}

	public void setCcid(Integer ccid) {
		this.ccid = ccid;
	}

	public Integer getExpertid() {
		return expertid;
	}

	public void setExpertid(Integer expertid) {
		this.expertid = expertid;
	}

	public String getExpertname() {
		return expertname;
	}

	public void setExpertname(String expertname) {
		this.expertname = expertname;
	}

	public Date getCommitdate() {
		return commitdate;
	}

	public void setCommitdate(Date commitdate) {
		this.commitdate = commitdate;
	}

	public String getCstate() {
		return cstate;
	}

	public void setCstate(String cstate) {
		this.cstate = cstate;
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

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getExpertcslt() {
		return expertcslt;
	}

	public void setExpertcslt(String expertcslt) {
		this.expertcslt = expertcslt;
	}

	public String getRslt_comments() {
		return rslt_comments;
	}

	public void setRslt_comments(String rslt_comments) {
		this.rslt_comments = rslt_comments;
	}

	

}
