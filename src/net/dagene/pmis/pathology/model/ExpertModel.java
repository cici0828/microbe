package net.dagene.pmis.pathology.model;

import java.util.Date;

public class ExpertModel {
	private Integer id;
	private String expertname;
	private Date finishdate;
	private Float viewnumperday;
	private Integer viewnum;
	private String userid;
		
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getExpertname() {
		return expertname;
	}
	public void setExpertname(String expertname) {
		this.expertname = expertname;
	}
	public Date getFinishdate() {
		return finishdate;
	}
	public void setFinishdate(Date finishdate) {
		this.finishdate = finishdate;
	}
	public Float getViewnumperday() {
		return viewnumperday;
	}
	public void setViewnumperday(Float viewnumperday) {
		this.viewnumperday = viewnumperday;
	}
	public Integer getViewnum() {
		return viewnum;
	}
	public void setViewnum(Integer viewnum) {
		this.viewnum = viewnum;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}


}
