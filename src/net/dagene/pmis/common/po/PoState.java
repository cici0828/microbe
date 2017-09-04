package net.dagene.pmis.common.po;

public class PoState {
	public Boolean respstate;
	public String respmsg;
	public Object vo;

	public Object getVo() {
		return vo;
	}

	public void setVo(Object vo) {
		this.vo = vo;
	}

	public String getRespmsg() {
		return respmsg;
	}

	public void setRespmsg(String respmsg) {
		this.respmsg = respmsg;
	}

	public Boolean getRespstate() {
		return respstate;
	}

	public void setRespstate(Boolean respstate) {
		this.respstate = respstate;
	}

}
