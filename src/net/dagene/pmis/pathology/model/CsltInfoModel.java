package net.dagene.pmis.pathology.model;

import java.util.Date;

public class CsltInfoModel {
	private Integer ebid;
	private String clidata;//临床资料
	private String clihistory;//病史
	private String gross;//大体所见
	private String first_option;//初诊意见
	private Integer gid;//流程编号
	private String barcode;//条码号
	private String patientname;//姓名
	private String sex;//性别
	private Integer age;//年龄
	private String remark;//备注
	private String cliresult_en;//诊断结果英文
	private String backreason;//退回原因
	/*	11-翻译新建
	12-提交翻译审核
	13-翻译退回
	14-翻译审核通过
	15-提交JHH
	16-JHH提交回结果(翻译在此状态翻译)
	17-JHH退回
	18-翻译结果审核
	19-审核翻译
	21-结果发布*/
	private String state;
	private String doctor;//诊断医生
	private String doctorid;//诊断医生ID
	private String rsltfrom="20";//20=JHH
	private String folderno;//病理编号
	private String sendstuff;//取材部位
	private Date commitdate;//JHH提交或退回时间
	private String cliresult_cn;//诊断结果中文
	private String letter;//给JHH，可以填些类似莱卡的COVER LETTER内容，这里是因为翻译
	private Integer bid=0;//中文补充信息ID
	
	public Integer getEbid() {
		return ebid;
	}
	public void setEbid(Integer ebid) {
		this.ebid = ebid;
	}
	public String getClidata() {
		return clidata;
	}
	public void setClidata(String clidata) {
		this.clidata = clidata;
	}
	public String getClihistory() {
		return clihistory;
	}
	public void setClihistory(String clihistory) {
		this.clihistory = clihistory;
	}
	public String getGross() {
		return gross;
	}
	public void setGross(String gross) {
		this.gross = gross;
	}
	public String getFirst_option() {
		return first_option;
	}
	public void setFirst_option(String first_option) {
		this.first_option = first_option;
	}
	public Integer getGid() {
		return gid;
	}
	public void setGid(Integer gid) {
		this.gid = gid;
	}
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCliresult_en() {
		return cliresult_en;
	}
	public void setCliresult_en(String cliresult_en) {
		this.cliresult_en = cliresult_en;
	}
	public String getBackreason() {
		return backreason;
	}
	public void setBackreason(String backreason) {
		this.backreason = backreason;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;		
	}
	public String getDoctor() {
		return doctor;
	}
	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}
	public String getDoctorid() {
		return doctorid;
	}
	public void setDoctorid(String doctorid) {
		this.doctorid = doctorid;
	}
	public String getRsltfrom() {
		return rsltfrom;
	}
	public void setRsltfrom(String rsltfrom) {
		this.rsltfrom = rsltfrom;
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
	public Date getCommitdate() {
		return commitdate;
	}
	public void setCommitdate(Date commitdate) {
		this.commitdate = commitdate;
	}
	public String getCliresult_cn() {
		return cliresult_cn;
	}
	public void setCliresult_cn(String cliresult_cn) {
		this.cliresult_cn = cliresult_cn;
	}
	public String getLetter() {
		return letter;
	}
	public void setLetter(String letter) {
		this.letter = letter;
	}
	public Integer getBid() {
		return bid;
	}
	public void setBid(Integer bid) {
		this.bid = bid;
	}	
}
