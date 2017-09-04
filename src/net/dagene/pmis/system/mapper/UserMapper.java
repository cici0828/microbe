package net.dagene.pmis.system.mapper;

import java.util.List;

import net.dagene.pmis.system.po.UserCustom;
import net.dagene.pmis.system.vo.UserVo;

public interface UserMapper {
	public UserCustom getUser(UserVo userVo) throws Exception;
	public List<UserCustom> getMicrobeUser() throws Exception;
}
