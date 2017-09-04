package net.dagene.pmis.pathology.po;

import net.dagene.pmis.pathology.model.CsltProgramModel;
import net.dagene.pmis.system.po.UserCustom;

public class CsltProgramCustom extends CsltProgramModel{
	String statemean;
	
	public String getStatemean() {
		return statemean;
	}

	public void setStatemean(String statemean) {
		this.statemean = statemean;
	}

	/*-------------病人信息-------------------*/
	private PatientinfoPY patient;
	
	/*------------联系人信息-------------------*/
	private UserCustom usercustom;

	public PatientinfoPY getPatient() {
		return patient;
	}

	public void setPatient(PatientinfoPY patient) {
		this.patient = patient;
	}

	public UserCustom getUsercustom() {
		return usercustom;
	}

	public void setUsercustom(UserCustom usercustom) {
		this.usercustom = usercustom;
	}

}
