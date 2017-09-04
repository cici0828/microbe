package net.dagene.pmis.pathology.po;

import java.util.Date;

import net.dagene.pmis.pathology.model.CsltInfoModel;

public class TranslatePo extends CsltInfoModel {
	private String state_msg;
	private String samplefrom;
	private String sampleto;
	private Date senddate;
	private int picnum;

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

	public Date getSenddate() {
		return senddate;
	}

	public void setSenddate(Date senddate) {
		this.senddate = senddate;
	}

	public String getState_msg() {
		try {
			switch (Integer.parseInt(this.getState())) {
			case 10:
				this.state_msg = "翻译未建";
				break;
			case 11:
				this.state_msg = "翻译新建";
				break;
			case 12:
				this.state_msg = "信息翻译审核";
				break;
			/*
			 * case 13: this.state_msg="翻译退回"; break;
			 */
			case 13:
				this.state_msg = "翻译审核通过";
				break;
			case 15:
				this.state_msg = "提交JHH";
				break;
			case 16:
				this.state_msg = "JHH返回结果";
				break;
			case 17:
				this.state_msg = "JHH退回";
				break;
			case 18:
				this.state_msg = "结果翻译审核";
				break;
			case 19:
				this.state_msg = "审核翻译";
				break;
			case 21:
				this.state_msg = "已发布";
				break;
			default:
				this.state_msg = "未知";
				break;
			}
		} catch (Exception ex) {
			this.state_msg = "未知";
		}
		return this.state_msg;
	}

	public int getPicnum() {
		return picnum;
	}

	public void setPicnum(int picnum) {
		this.picnum = picnum;
	}

	/*
	 * public Integer ebid; public String clidata;//临床资料 public String
	 * clihistory;//病史 public String gross;//大体所见 public String
	 * first_option;//初诊意见 public Integer gid;//流程编号 public String barcode;//条码号
	 * public String patientname;//姓名 public String sex;//性别 public Integer
	 * age;//年龄 public String remark;//备注 public String cliresult_en;//诊断结果英文
	 * public String backreason;//退回原因
	 * 
	 * 10-翻译未建 11-翻译新建 12-提交翻译审核 13-翻译退回 14-翻译审核通过 15-提交JHH
	 * 16-JHH提交回结果(翻译在此状态翻译) 17-JHH退回 18-翻译结果审核 19-审核翻译 21-结果发布 public String
	 * state; //public AuditStateEnum state_msg; public String state_msg; public
	 * String doctor;//诊断医生 public String doctorid;//诊断医生ID public String
	 * rsltfrom;//20=JHH public String folderno;//病理编号 public String
	 * sendstuff;//取材部位 public Date commitdate;//JHH提交或退回时间 public String
	 * cliresult_cn;//诊断结果中文 public String letter;//给JHH，可以填些类似莱卡的COVER
	 * LETTER内容，这里是因为翻译
	 * 
	 * 
	 * public Integer getEbid() { return ebid; } public void setEbid(Integer
	 * ebid) { this.ebid = ebid; } public String getClidata() { return clidata;
	 * } public void setClidata(String clidata) { this.clidata = clidata; }
	 * public String getClihistory() { return clihistory; } public void
	 * setClihistory(String clihistory) { this.clihistory = clihistory; } public
	 * String getGross() { return gross; } public void setGross(String gross) {
	 * this.gross = gross; } public String getFirst_option() { return
	 * first_option; } public void setFirst_option(String first_option) {
	 * this.first_option = first_option; } public Integer getGid() { return gid;
	 * } public void setGid(Integer gid) { this.gid = gid; } public String
	 * getBarcode() { return barcode; } public void setBarcode(String barcode) {
	 * this.barcode = barcode; } public String getPatientname() { return
	 * patientname; } public void setPatientname(String patientname) {
	 * this.patientname = patientname; } public String getSex() { return sex; }
	 * public void setSex(String sex) { this.sex = sex; } public Integer
	 * getAge() { return age; } public void setAge(Integer age) { this.age =
	 * age; } public String getRemark() { return remark; } public void
	 * setRemark(String remark) { this.remark = remark; } public String
	 * getCliresult_en() { return cliresult_en; } public void
	 * setCliresult_en(String cliresult_en) { this.cliresult_en = cliresult_en;
	 * } public String getBackreason() { return backreason; } public void
	 * setBackreason(String backreason) { this.backreason = backreason; } public
	 * String getState() { return state; } public void setState(String state) {
	 * this.state =state; try{ switch(Integer.parseInt(this.state)){ case 10:
	 * this.state_msg="翻译未建"; break; case 11: this.state_msg="翻译新建"; break; case
	 * 12: this.state_msg="提交翻译审核"; break; case 13: this.state_msg="翻译退回";
	 * break; case 14: this.state_msg="翻译审核通过"; break; case 15:
	 * this.state_msg="提交JHH"; break; case 16:
	 * this.state_msg="JHH提交回结果(翻译在此状态翻译)"; break; case 17:
	 * this.state_msg="JHH退回"; break; case 18: this.state_msg="翻译结果审核"; break;
	 * case 19: this.state_msg="审核翻译"; break; default: this.state_msg="未知";
	 * break; } }catch(Exception ex){ this.state_msg="未知"; } }
	 * 
	 * public String getState_msg() { return this.state_msg; }
	 * 
	 * public String getDoctor() { return doctor; } public void setDoctor(String
	 * doctor) { this.doctor = doctor; } public String getDoctorid() { return
	 * doctorid; } public void setDoctorid(String doctorid) { this.doctorid =
	 * doctorid; } public String getRsltfrom() { return rsltfrom; } public void
	 * setRsltfrom(String rsltfrom) { this.rsltfrom = rsltfrom; } public String
	 * getFolderno() { return folderno; } public void setFolderno(String
	 * folderno) { this.folderno = folderno; } public String getSendstuff() {
	 * return sendstuff; } public void setSendstuff(String sendstuff) {
	 * this.sendstuff = sendstuff; } public Date getCommitdate() { return
	 * commitdate; } public void setCommitdate(Date commitdate) {
	 * this.commitdate = commitdate; } public String getCliresult_cn() { return
	 * cliresult_cn; } public void setCliresult_cn(String cliresult_cn) {
	 * this.cliresult_cn = cliresult_cn; } public String getLetter() { return
	 * letter; } public void setLetter(String letter) { this.letter = letter; }
	 */
}
