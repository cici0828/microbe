package net.dagene.pmis.pathology.vo;

public class ExecClstCommitExpertVo extends BaseCsltVo {
	
	private Integer vpid;//流程号
	private String vexpertname;//姓名
	private Integer vexperid;// expert表中的id
	private Integer vnum;//暂时不用批量提交，所以传入1
	private String vreporttype;//报告方式，就是界面中的诊断意见或诊断、  报告
	private Integer vstate;//传入提交专家的状态
	private Integer issendsms;//0 表示立即发送 --1表示隔天发送
	private float vfee;//费用
	private String vuserid;//提交人id
	private String vusername;//--提交人姓名
	private String vcommittel;
	private Integer sucflag2;
	private String errInf2;

	public Integer getVpid() {
		return vpid;
	}

	public void setVpid(Integer vpid) {
		this.vpid = vpid;
	}

	public String getVexpertname() {
		return vexpertname;
	}

	public void setVexpertname(String vexpertname) {
		this.vexpertname = vexpertname;
	}

	public Integer getVexperid() {
		return vexperid;
	}

	public void setVexperid(Integer vexperid) {
		this.vexperid = vexperid;
	}

	public Integer getVnum() {
		return vnum;
	}

	public void setVnum(Integer vnum) {
		this.vnum = vnum;
	}

	public String getVreporttype() {
		return vreporttype;
	}

	public void setVreporttype(String vreporttype) {
		this.vreporttype = vreporttype;
	}

	public Integer getVstate() {
		return vstate;
	}

	public void setVstate(Integer vstate) {
		this.vstate = vstate;
	}

	public Integer getIssendsms() {
		return issendsms;
	}

	public void setIssendsms(Integer issendsms) {
		this.issendsms = issendsms;
	}

	public float getVfee() {
		return vfee;
	}

	public void setVfee(float vfee) {
		this.vfee = vfee;
	}

	public String getVuserid() {
		return vuserid;
	}

	public void setVuserid(String vuserid) {
		this.vuserid = vuserid;
	}

	public String getVusername() {
		return vusername;
	}

	public void setVusername(String vusername) {
		this.vusername = vusername;
	}

	public Integer getSucflag2() {
		return sucflag2;
	}

	public void setSucflag2(Integer sucflag2) {
		this.sucflag2 = sucflag2;
	}

	public String getErrInf2() {
		return errInf2;
	}

	public void setErrInf2(String errInf2) {
		this.errInf2 = errInf2;
	}


	public String getVcommittel() {
		return vcommittel;
	}

	public void setVcommittel(String vcommittel) {
		this.vcommittel = vcommittel;
	}

}
