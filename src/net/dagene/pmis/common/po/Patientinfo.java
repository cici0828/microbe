package net.dagene.pmis.common.po;

import java.util.Date;
import java.util.List;

public class Patientinfo {
	private long origrec;
	private String samplefrom;
	private String sampleto;
	private String bussinesstype;
	private String barcode;
	private Date collectddate;
	private Date senddate;
	private String samplestatus;
	private String patientname;
	private String sex;
	private String ageunit;
	private String age;
	private String folderno;
	private String sendstuff;
	private String dept;
	
	public String getBussinesstype() {
		return bussinesstype;
	}
	public void setBussinesstype(String bussinesstype) {
		this.bussinesstype = bussinesstype;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public Date getCollectddate() {
		return collectddate;
	}
	public void setCollectddate(Date collectddate) {
		this.collectddate = collectddate;
	}
	public Date getSenddate() {
		return senddate;
	}
	public void setSenddate(Date senddate) {
		this.senddate = senddate;
	}
	public String getSamplestatus() {
		return samplestatus;
	}
	public void setSamplestatus(String samplestatus) {
		this.samplestatus = samplestatus;
	}
	public String getPatientname() {
		return patientname;
	}
	public void setPatientname(String patientname) {
		this.patientname = patientname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAgeunit() {
		return ageunit;
	}
	public void setAgeunit(String ageunit) {
		this.ageunit = ageunit;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}

	public String getFolderno() {
		return folderno;
	}
	public void setFolderno(String folderno) {
		this.folderno = folderno;
	}
	public List<Patienttests> getPatienttests() {
		return patienttests;
	}
	public void setPatienttests(List<Patienttests> patienttests) {
		this.patienttests = patienttests;
	}

	private List<Patienttests> patienttests;
	public long getOrigrec() {
		return origrec;
	}
	public void setOrigrec(long origrec) {
		this.origrec = origrec;
	}
	public String getSamplefrom() {
		return samplefrom;
	}
	public void setSamplefrom(String samplefrom) {
		this.samplefrom = samplefrom;
	}
	public String getSampleto() {
		return sampleto;
	}
	public void setSampleto(String sampleto) {
		this.sampleto = sampleto;
	}
	public String getSendstuff() {
		return sendstuff;
	}
	public void setSendstuff(String sendstuff) {
		this.sendstuff = sendstuff;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
}
