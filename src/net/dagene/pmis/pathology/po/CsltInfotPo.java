package net.dagene.pmis.pathology.po;


import net.dagene.pmis.pathology.model.CsltInfoModel;

public class CsltInfotPo extends CsltInfoModel {
	private String state_msg;
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
}
