package net.dagene.pmis.pathology.po;

import net.dagene.pmis.common.po.PatientinfoCustom;

public class PatientinfoPY extends PatientinfoCustom {
	private TestResultsPY testResultPY;

	private String test1;

	public TestResultsPY getTestResultPY() {
		return testResultPY;
	}

	public void setTestResultPY(TestResultsPY testResultPY) {
		this.testResultPY = testResultPY;
	}

	public String getTest1() {
		return test1;
	}

	public void setTest1(String test1) {
		this.test1 = test1;
	}
}
