package net.dagene.pmis.pathology.model;

import java.util.Date;

public class CsltLogInfoModel {
	private Integer logid;
	private Integer logpid;
	private String op_userid;
	private String op_username;
	private Date op_date;
	private String op_bz;
	private String op_state_mean;
	private String op_state;
	private Integer csltid;

	public Integer getLogid() {
		return logid;
	}

	public void setLogid(Integer logid) {
		this.logid = logid;
	}

	public Integer getLogpid() {
		return logpid;
	}

	public void setLogpid(Integer logpid) {
		this.logpid = logpid;
	}

	public String getOp_userid() {
		return op_userid;
	}

	public void setOp_userid(String op_userid) {
		this.op_userid = op_userid;
	}

	public String getOp_username() {
		return op_username;
	}

	public void setOp_username(String op_username) {
		this.op_username = op_username;
	}

	public Date getOp_date() {
		return op_date;
	}

	public void setOp_date(Date op_date) {
		this.op_date = op_date;
	}

	public String getOp_bz() {
		return op_bz;
	}

	public void setOp_bz(String op_bz) {
		this.op_bz = op_bz;
	}

	public String getOp_state_mean() {
		return op_state_mean;
	}

	public void setOp_state_mean(String op_state_mean) {
		this.op_state_mean = op_state_mean;
	}

	public String getOp_state() {
		return op_state;
	}

	public void setOp_state(String op_state) {
		this.op_state = op_state;
	}

	public Integer getCsltid() {
		return csltid;
	}

	public void setCsltid(Integer csltid) {
		this.csltid = csltid;
	}

}
