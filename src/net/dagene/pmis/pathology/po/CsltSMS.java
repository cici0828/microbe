package net.dagene.pmis.pathology.po;

import java.util.Date;

public class CsltSMS {
	private Integer expertid;
	private Integer samplenum;
	private Integer smsstate;
	private Date nextsenddate;
	
	public Integer getExpertid() {
		return expertid;
	}
	public void setExpertid(Integer expertid) {
		this.expertid = expertid;
	}
	public Integer getSamplenum() {
		return samplenum;
	}
	public void setSamplenum(Integer samplenum) {
		this.samplenum = samplenum;
	}
	public Integer getSmsstate() {
		return smsstate;
	}
	public void setSmsstate(Integer smsstate) {
		this.smsstate = smsstate;
	}
	public Date getNextsenddate() {
		return nextsenddate;
	}
	public void setNextsenddate(Date nextsenddate) {
		this.nextsenddate = nextsenddate;
	}

}
