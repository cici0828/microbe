package net.dagene.pmis.pathology.vo;

import java.util.Date;

public class TranslateVo {
	private String[] states;//状态数组
	private String state;
	private Date start;//开始时间
	private Date end;//结束时间
	
	public String[] getStates() {
		return states;
	}
	public void setStates(String[] states) {
		this.states = states;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	

}
