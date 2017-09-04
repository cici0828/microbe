package net.dagene.pmis.system.po;

public class User {
	private String origrec;
	private String usrnam;
	private String fullname;
	private String deptlist;
	private String roleid;
	private String passwd;
	private String usertel;
	private Integer roleid2;

	public String getOrigrec() {
		return origrec;
	}

	public void setOrigrec(String origrec) {
		this.origrec = origrec;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getDeptlist() {
		return deptlist;
	}

	public void setDeptlist(String deptlist) {
		this.deptlist = deptlist;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getUsrnam() {
		return usrnam;
	}

	public void setUsrnam(String usrnam) {
		this.usrnam = usrnam;
	}

	@Override
	public String toString() {
		return usrnam + ":" + fullname + ":" + roleid + ":" + deptlist + ":"
				+ usertel;

	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getUsertel() {
		return usertel;
	}

	public void setUsertel(String usertel) {
		this.usertel = usertel;
	}

	public Integer getRoleid2() {
		return roleid2;
	}

	public void setRoleid2(Integer roleid2) {
		this.roleid2 = roleid2;
	}

}
