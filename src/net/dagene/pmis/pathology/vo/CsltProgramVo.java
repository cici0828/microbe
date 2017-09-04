package net.dagene.pmis.pathology.vo;

import net.dagene.pmis.pathology.po.CsltProgramCustom;

public class CsltProgramVo {
	private String state_old;
	private CsltProgramCustom csltProgramCustom;

	public CsltProgramCustom getCsltProgramCustom() {
		return csltProgramCustom;
	}

	public void setCsltProgramCustom(CsltProgramCustom csltProgramCustom) {
		this.csltProgramCustom = csltProgramCustom;
	}

	public String getState_old() {
		return state_old;
	}

	public void setState_old(String state_old) {
		this.state_old = state_old;
	}
}
