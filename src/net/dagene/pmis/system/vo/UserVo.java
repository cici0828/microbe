package net.dagene.pmis.system.vo;

import net.dagene.pmis.system.po.UserCustom;

public class UserVo {
	private UserCustom userCustom;
	private String passwd;

	public UserCustom getUserCustom() {
		return userCustom;
	}

	public void setUserCustom(UserCustom userCustom) {
		this.userCustom = userCustom;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

}
