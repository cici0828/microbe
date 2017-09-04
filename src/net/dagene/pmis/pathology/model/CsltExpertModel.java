package net.dagene.pmis.pathology.model;

import java.util.Date;

public class CsltExpertModel {
	private Integer id;
	private String expertname;// 专家姓名
	private String sex;
	private String expertidentity;
	private String tel;// 联系方式
	private String email;
	private String title;
	private String hospital;
	private String area;
	private String introduction;
	private String dept;
	private String groupto;
	private String duty;
	private String hiredate;
	private String hiretype;
	private String nature;// 工作性质
	private String modes;
	private String agreement;
	private String work_countent;
	private String accept;
	private String charge;
	private String specialty;
	private String isdelete;
	private double viewnumperday;
	private String iswork;
	private Integer viewnum;// 在看片数
	private Date finishdate;// 在看片子完成时间
	private String userid;
	private double fee;// 每片收费
	private Date lbegdate;
	private Date lenddate;
	private String history;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getExpertname() {
		return expertname;
	}

	public void setExpertname(String expertname) {
		this.expertname = expertname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getExpertidentity() {
		return expertidentity;
	}

	public void setExpertidentity(String expertidentity) {
		this.expertidentity = expertidentity;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getGroupto() {
		return groupto;
	}

	public void setGroupto(String groupto) {
		this.groupto = groupto;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getHiredate() {
		return hiredate;
	}

	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}

	public String getHiretype() {
		return hiretype;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public void setHiretype(String hiretype) {
		this.hiretype = hiretype;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public String getModes() {
		return modes;
	}

	public void setModes(String modes) {
		this.modes = modes;
	}

	public String getAgreement() {
		return agreement;
	}

	public void setAgreement(String agreement) {
		this.agreement = agreement;
	}

	public String getWork_countent() {
		return work_countent;
	}

	public void setWork_countent(String work_countent) {
		this.work_countent = work_countent;
	}

	public String getAccept() {
		return accept;
	}

	public void setAccept(String accept) {
		this.accept = accept;
	}

	public String getCharge() {
		return charge;
	}

	public void setCharge(String charge) {
		this.charge = charge;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(String isdelete) {
		this.isdelete = isdelete;
	}

	public double getViewnumperday() {
		return viewnumperday;
	}

	public void setViewnumperday(double viewnumperday) {
		this.viewnumperday = viewnumperday;
	}

	public String getIswork() {
		return iswork;
	}

	public void setIswork(String iswork) {
		this.iswork = iswork;
	}

	public Integer getViewnum() {
		return viewnum;
	}

	public void setViewnum(Integer viewnum) {
		this.viewnum = viewnum;
	}

	public Date getFinishdate() {
		return finishdate;
	}

	public void setFinishdate(Date finishdate) {
		this.finishdate = finishdate;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public Date getLbegdate() {
		return lbegdate;
	}

	public void setLbegdate(Date lbegdate) {
		this.lbegdate = lbegdate;
	}

	public Date getLenddate() {
		return lenddate;
	}

	public void setLenddate(Date lenddate) {
		this.lenddate = lenddate;
	}

}
