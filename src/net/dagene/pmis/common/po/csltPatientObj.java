package net.dagene.pmis.common.po;

import java.io.Serializable;

public class csltPatientObj implements Serializable {
	private Long origrec;
	/*所属医院*/
	private String samplefrom;
	/*姓名 jhh*/
	private String patientname;
	/*性别 jhh*/
	private String sex;
	/*年龄单位 jhh*/
	private String ageunit;
	/*年龄 jhh*/
	private String age;
	/*年龄+年龄单位 jhh*/
	private String agex;
	/*病理编号 jhh*/
	private String folderno;
	/*取材部位 jhh*/
	private String sendstuff;
	
	public Long getOrigrec() {
		return origrec;
	}
	public void setOrigrec(Long origrec) {
		this.origrec = origrec;
	}
	public String getSamplefrom() {
		return samplefrom;
	}
	public void setSamplefrom(String samplefrom) {
		this.samplefrom = samplefrom;
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
	public String getAgex() {
		return agex;
	}
	public void setAgex(String agex) {
		this.agex = agex;
	}
	public String getFolderno() {
		return folderno;
	}
	public void setFolderno(String folderno) {
		this.folderno = folderno;
	}
	public String getSendstuff() {
		return sendstuff;
	}
	public void setSendstuff(String sendstuff) {
		this.sendstuff = sendstuff;
	}
	
}
