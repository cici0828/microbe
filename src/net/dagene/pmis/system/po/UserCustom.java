package net.dagene.pmis.system.po;

import java.util.List;

public class UserCustom extends User {
	private String logindept;
	private List<Module> Modules;

	public String getLogindept() {
		return logindept;
	}

	public void setLogindept(String logindept) {
		this.logindept = logindept;
	}

	public void setUsrnam(String usrnam) {
		super.setUsrnam(usrnam.toUpperCase());
	}

	@Override
	public String toString() {
		return super.toString()+","+logindept;

	}

	public List<Module> getModules() {
		return Modules;
	}

	public void setModules(List<Module> modules) {
		Modules = modules;
	}
}
