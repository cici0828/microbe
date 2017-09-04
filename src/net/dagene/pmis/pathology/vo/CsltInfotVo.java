package net.dagene.pmis.pathology.vo;

import net.dagene.pmis.pathology.model.CsltInfoModel;

public class CsltInfotVo extends CsltInfoModel {

	private String state_msg;

	public String getState_msg() {
		try {
			switch (Integer.parseInt(this.getState())) {
			case 11:
				this.state_msg = "新建翻译";
				break;
			case 12:
				this.state_msg = "提交翻译审核";
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
				this.state_msg = "JHH提交诊断结果";// "JHH提交回结果(翻译在此状态翻译)";
				break;
			case 17:
				this.state_msg = "JHH诊断退回";// "JHH退回";
				break;
			case 18:
				this.state_msg = "提交诊断结果翻译审核";
				break;
			case 19:
				this.state_msg = "翻译审核通过";
				break;
			case 21:
				this.state_msg = "发布诊断报告";
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
}
