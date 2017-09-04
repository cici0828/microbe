package net.dagene.pmis.common.po;

import java.util.List;

public class PatientinfoCustom extends Patientinfo {
	private String agex;

	private List<Patienttests> patienttests;

	public List<Patienttests> getPatienttests() {
		return patienttests;
	}

	public void setPatienttests(List<Patienttests> patienttests) {
		this.patienttests = patienttests;
	}

	public String getAgex() {
		if (super.getAge() == null)
			return super.getAgeunit();
		else
		    return super.getAge() + super.getAgeunit();
	}

	public void setAgex(String agex) {
		this.agex = agex;
	}

}
