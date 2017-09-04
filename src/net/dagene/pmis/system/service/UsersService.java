package net.dagene.pmis.system.service;

import java.util.List;

import net.dagene.pmis.system.po.UserCustom;
import net.dagene.pmis.system.vo.UserVo;

public interface UsersService {
	public UserCustom GetUser(UserVo usrVo) throws Exception;

	public String GetDeptList(String userid) throws Exception;
	
	public String GetUserFullName(String userid) throws Exception;
	
	public UserCustom UserLogin(UserVo userVo) throws Exception;
	
	public List<UserCustom> GetMicUserList() throws Exception;
}
