package net.dagene.microbe.vo;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import net.dagene.microbe.po.MicrobeDetailPo;

public class QueryMicrobeResultVo {
	private Integer origrec;
	private String barcode;
	private String patientname;
	private String age;
	private String ageUnit;
	private String sex;
	private Date collectddate;
	private Date senddate;
	private String doctor;
	private String clinicid;
	private String clinicname;
	private String diagnosis;
	private String testcode; 
	private String testno;
	private Date receiveddate;
	private Date reportdate;
	private String microbecode;
	private String remark;
	private String checkby;
	private Date inputdate;
	private String inputman;
	private String inputmanName;
	private String inputmanduty;
    //private Date checkdate;
	private String checkman;
	private String checkmanName;
	private String checkmanduty;
    //private Date approvaldate; 
	private String approvalman;
	private String approvalmanduty; 
	private String approvalmanName;
	
	@JsonSerialize(using = net.dagene.pmis.util.CustomDateSerializer.class)
    public Date getInputdate() {
		return inputdate;
	}
	
	public void setInputdate(Date inputdate) {
		this.inputdate = inputdate;
	}
	public String getInputman() {
		return inputman;
	}
	public void setInputman(String inputman) {
		this.inputman = inputman;
	}
	public void setInputmanName(String inputmanName) {
		this.inputmanName = inputmanName;
	}
	public void setCheckmanName(String checkmanName) {
		this.checkmanName = checkmanName;
	}
	public void setApprovalmanName(String approvalmanName) {
		this.approvalmanName = approvalmanName;
	}
	public String getInputmanduty() {
		return inputmanduty;
	}
	public void setInputmanduty(String inputmanduty) {
		this.inputmanduty = inputmanduty;
	}
//	public Date getCheckdate() {
//		return checkdate;
//	}
//	public void setCheckdate(Date checkdate) {
//		this.checkdate = checkdate;
//	}
	public String getCheckman() {
		return checkman;
	}
	public void setCheckman(String checkman) {
		this.checkman = checkman;
	}
	public String getCheckmanduty() {
		return checkmanduty;
	}
	public void setCheckmanduty(String checkmanduty) {
		this.checkmanduty = checkmanduty;
	}
//	public Date getApprovaldate() {
//		return approvaldate;
//	}
//	public void setApprovaldate(Date approvaldate) {
//		this.approvaldate = approvaldate;
//	}
	public String getApprovalman() {
		return approvalman;
	}
	public void setApprovalman(String approvalman) {
		this.approvalman = approvalman;
	}
	public String getApprovalmanduty() {
		return approvalmanduty;
	}
	public void setApprovalmanduty(String approvalmanduty) {
		this.approvalmanduty = approvalmanduty;
	}

	public String getCheckby() {
		return checkby;
	}
	public void setCheckby(String checkby) {
		this.checkby = checkby;
	}
	public String getIfmasculine() {
		return ifmasculine;
	}
	public void setIfmasculine(String ifmasculine) {
		this.ifmasculine = ifmasculine;
	}
	private String ifmasculine;
	private String mguid;
	private List<MicrobeDetailPo> detailList;
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getPatientname() {
		return patientname;
	}
	public void setPatientname(String patientname) {
		this.patientname = patientname;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getAgeUnit() {
		return ageUnit;
	}
	public void setAgeUnit(String ageUnit) {
		this.ageUnit = ageUnit;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
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
	public String getDoctor() {
		return doctor;
	}
	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}
	public String getClinicid() {
		return clinicid;
	}
	public void setClinicid(String clinicid) {
		this.clinicid = clinicid;
	}
	public String getClinicname() {
		return clinicname;
	}
	public void setClinicname(String clinicname) {
		this.clinicname = clinicname;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
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
	public Date getReceiveddate() {
		return receiveddate;
	}
	public void setReceiveddate(Date receiveddate) {
		this.receiveddate = receiveddate;
	}
	public Date getReportdate() {
		return reportdate;
	}
	public void setReportdate(Date reportdate) {
		this.reportdate = reportdate;
	}
	public String getMicrobecode() {
		return microbecode;
	}
	public void setMicrobecode(String microbecode) {
		this.microbecode = microbecode;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public List<MicrobeDetailPo> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<MicrobeDetailPo> detailList) {
		this.detailList = detailList;
	}
	public Integer getOrigrec() {
		return origrec;
	}
	public void setOrigrec(Integer origrec) {
		this.origrec = origrec;
	}
	public String getMguid() {
		return mguid;
	}
	public void setMguid(String mguid) {
		this.mguid = mguid;
	}
	public String getInputmanName() {
		return inputmanName;
	}
	public String getCheckmanName() {
		return checkmanName;
	}
	public String getApprovalmanName() {
		return approvalmanName;
	}
	
}