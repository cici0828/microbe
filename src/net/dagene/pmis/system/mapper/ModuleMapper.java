package net.dagene.pmis.system.mapper;

import java.util.List;

import net.dagene.pmis.system.po.Module;

public interface ModuleMapper {
	public List<Module> getModuleByRoleID(int roleid) throws Exception;

}
