package net.dagene.pmis.common.po;

import java.util.Date;

public class Patienttests {
	private long origrec;
	private long patientorigrec;
	private String servgrp;
	private String testgroup;
	private String testcode;
	private String testno;
	private String sampletype;
	private String status;
	private String dept;
	private String microbecode;
	private Date receiveddate;
	
	public long getPatientorigrec() {
		return patientorigrec;
	}
	public void setPatientorigrec(long patientorigrec) {
		this.patientorigrec = patientorigrec;
	}
	public String getServgrp() {
		return servgrp;
	}
	public void setServgrp(String servgrp) {
		this.servgrp = servgrp;
	}
	public String getTestgroup() {
		return testgroup;
	}
	public void setTestgroup(String testgroup) {
		this.testgroup = testgroup;
	}
	public String getTestcode() {
		return testcode;
	}
	public void setTestcode(String testcode) {
		this.testcode = testcode;
	}
	public String getTestno() {
		return testno;
	}
	public void setTestno(String testno) {
		this.testno = testno;
	}
	public String getSampletype() {
		return sampletype;
	}
	public void setSampletype(String sampletype) {
		this.sampletype = sampletype;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getMicrobecode() {
		return microbecode;
	}
	public void setMicrobecode(String microbecode) {
		this.microbecode = microbecode;
	}
	public Date getReceiveddate() {
		return receiveddate;
	}
	public void setReceiveddate(Date receiveddate) {
		this.receiveddate = receiveddate;
	}

	public long getOrigrec() {
		return origrec;
	}
	public void setOrigrec(long origrec) {
		this.origrec = origrec;
	}
	
}
