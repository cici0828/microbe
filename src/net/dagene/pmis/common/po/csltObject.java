package net.dagene.pmis.common.po;

import java.util.Date;
import java.util.List;

import net.dagene.pmis.pathology.po.CsltSliceInfoCustom;

public class csltObject{
	public csltObject(){		
	}
	/* 流水号 */
	private Integer pid;
	/* 条码号 */
	private String pt_barcode;
	/* 状态 0-未处理 1-已有结果 16-提交 17-退回，初始状态为0 jhh */
	/* 考虑以后兼容国内专家 8提交 -3退回 */
	private String state;
	/* 专家名称 */
	private String expertname;
	/* 专家ID */
	private String expertid;
	/* 提交专家时间，下面的时间都用double jhh */
	private Date expertdate;
	/*
	 * 专家反馈时间，如果退回就是退回时间， 如果提交就提交时间****TODO jhh
	 */
	private Date expertfinishdate;
	/* 提交中心 */
	private String dept;
	/* 片子数量 jhh */
	private Integer picnum;
	/* 申请人 */
	private String loginname;
	/* 申请人时间 */
	private Date logindate;
	/* 病理编号 jhh */
	private String folderno;
	/* 价格 */
	private Float fee;
	/* 病人信息 */
	private csltPatientObj patient;
	/* 发起人联系电话 */
	private csltUsercustom usercustom;
	/* 临床资料 jhh */
	private String clidata;
	/* 病史 jhh */
	private String clihistory;
	/* 大体所见 jhh */
	private String gross;
	/* 初诊断意见 jhh */
	private String first_option;
	/* 附言 jhh */
	private String letter;
	/* slicelist为切片列表 jhh，把切片结果列表完全映射 */
	private List<CsltSliceInfoCustom> slicelist;
	/* 诊断结果， 上传不用考虑 JHH */
	private String expertcslt;
	/* 退回原因 上传不用考虑 JHH */
	private String backreason;
	/* 备注 */
	private String rslt_comments;
	/* 诊断结果截图 */
	private List<csltAttcPictureObj> attcpicturelist;

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

	public Date getExpertfinishdate() {
		return expertfinishdate;
	}

	public void setExpertfinishdate(Date expertfinishdate) {
		this.expertfinishdate = expertfinishdate;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public Integer getPicnum() {
		return picnum;
	}

	public void setPicnum(Integer picnum) {
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

	public String getFolderno() {
		return folderno;
	}

	public void setFolderno(String folderno) {
		this.folderno = folderno;
	}

	public Float getFee() {
		return fee;
	}

	public void setFee(Float fee) {
		this.fee = fee;
	}

	public csltPatientObj getPatient() {
		return patient;
	}

	public void setPatient(csltPatientObj patient) {
		this.patient = patient;
	}

	public csltUsercustom getUsercustom() {
		return usercustom;
	}

	public void setUsercustom(csltUsercustom usercustom) {
		this.usercustom = usercustom;
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

	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}

	public List<CsltSliceInfoCustom> getSlicelist() {
		return slicelist;
	}

	public void setSlicelist(List<CsltSliceInfoCustom> slicelist) {
		this.slicelist = slicelist;
	}

	public String getExpertcslt() {
		return expertcslt;
	}

	public void setExpertcslt(String expertcslt) {
		this.expertcslt = expertcslt;
	}

	public String getBackreason() {
		return backreason;
	}

	public void setBackreason(String backreason) {
		this.backreason = backreason;
	}

	public String getRslt_comments() {
		return rslt_comments;
	}

	public void setRslt_comments(String rslt_comments) {
		this.rslt_comments = rslt_comments;
	}

	public List<csltAttcPictureObj> getAttcpicturelist() {
		return attcpicturelist;
	}

	public void setAttcpicturelist(List<csltAttcPictureObj> attcpicturelist) {
		this.attcpicturelist = attcpicturelist;
	}
}
