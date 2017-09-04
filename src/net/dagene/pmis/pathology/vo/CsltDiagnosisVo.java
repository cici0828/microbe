package net.dagene.pmis.pathology.vo;

import net.dagene.pmis.pathology.model.CsltGroupprogramModel;

public class CsltDiagnosisVo extends BaseCsltVo {
	private CsltGroupprogramModel csltGroupprogramModel;
	private Boolean isupdate;
	private String state_msg;
	private String remark="";

	public String getState_msg() {
		try {
			switch (csltGroupprogramModel.getState()) {
			case 20:
				this.state_msg = "会诊申请";
				break;
			case 21:
				this.state_msg = "会诊退回";
				break;
			case 25:
				this.state_msg = "会诊审核";
				break;
			case 26:
				this.state_msg = "提交专家";
				break;
			case 27:
				this.state_msg = "专家退回";
				break;
			case 28:
				this.state_msg = "解除提交";
				break;
			case 29:
				this.state_msg = "重选专家";
				break;
			case 35:
				this.state_msg = "阅片完成";
				break;
			case 36:
				this.state_msg = "结果确认";
				break;
			case 37:
				this.state_msg = "读片会";
				break;
			default:
				this.state_msg = "未知";
				break;
			}
		} catch (Exception ex) {
			this.state_msg = "未知";
		}
		return state_msg;
	}

	public CsltGroupprogramModel getCsltGroupprogramModel() {
		return csltGroupprogramModel;
	}

	public void setCsltGroupprogramModel(CsltGroupprogramModel csltGroupprogramModel) {
		this.csltGroupprogramModel = csltGroupprogramModel;
	}

	public Boolean getIsupdate() {
		return isupdate;
	}

	public void setIsupdate(Boolean isupdate) {
		this.isupdate = isupdate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
