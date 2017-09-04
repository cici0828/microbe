package net.dagene.pmis.pathology.po;

import net.dagene.pmis.pathology.model.CsltGroupprogramModel;
import net.dagene.pmis.pathology.model.CsltPatientinformation;

public class CsltDiagnosisListPo {
	private CsltGroupprogramModel groupprogram;//会诊流程信息
	private CsltPatientinformation patinfo;//会诊用户基本信息
	private CsltBaseInfo baseinfo;//补充信息
	private Integer pid;
	private String state_msg;

	public CsltGroupprogramModel getGroupprogram() {
		return groupprogram;
	}

	public void setGroupprogram(CsltGroupprogramModel groupprogram) {
		this.groupprogram = groupprogram;
	}

	public CsltPatientinformation getPatinfo() {
		return patinfo;
	}

	public void setPatinfo(CsltPatientinformation patinfo) {
		this.patinfo = patinfo;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public CsltBaseInfo getBaseinfo() {
		return baseinfo;
	}

	public void setBaseinfo(CsltBaseInfo baseinfo) {
		this.baseinfo = baseinfo;
	}

}
