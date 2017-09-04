package net.dagene.pmis.pathology.vo;

import net.dagene.pmis.pathology.po.CsltResultCustom;

public class CsltResultVo {
	private String cstate_old;
	private CsltResultCustom csltResultCustom;

	public CsltResultCustom getCsltResultCustom() {
		return csltResultCustom;
	}

	public void setCsltResultCustom(CsltResultCustom csltResultCustom) {
		this.csltResultCustom = csltResultCustom;
	}

	public String getCstate_old() {
		return cstate_old;
	}

	public void setCstate_old(String cstate_old) {
		this.cstate_old = cstate_old;
	}


}
