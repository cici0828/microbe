package net.dagene.pmis.system.service.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import net.dagene.pmis.system.mapper.UserMapper;
import net.dagene.pmis.system.po.UserCustom;
import net.dagene.pmis.system.service.UsersService;
import net.dagene.pmis.system.vo.UserVo;

public class UsersServiceImpl implements UsersService {
	@Autowired
	private UserMapper userMapper;
	private List<UserCustom> UserList = null;

	public UsersServiceImpl() {

	}

	public UserCustom GetUser(UserVo userVo) throws Exception {
		// return (UserCustom) userMapper.getUser(userVo);
		return null;
	}

	public String GetDeptList(String userid) throws Exception {
		/*
		 * BeanUtils.copyProperties(source, target);//属性拷贝 import
		 * org.springframework.beans.BeanUtils;
		 */
		if ((userid == null) || (userid == ""))
			return "";
		else {
			UserVo userVo = new UserVo();
			UserCustom userCustom = new UserCustom();
			userCustom.setUsrnam(userid);
			userVo.setUserCustom(userCustom);
			userCustom = (UserCustom) userMapper.getUser(userVo);
			if (userCustom == null)
				return "";
			return userCustom.getDeptlist();
		}
	}

	public UserCustom UserLogin(UserVo userVo) throws Exception {
		UserCustom userCustom = (UserCustom) userMapper.getUser(userVo);
		if (userCustom != null)
			userCustom.setLogindept(userVo.getUserCustom().getLogindept());
		return userCustom;
	}

	@Override
	public List<UserCustom> GetMicUserList() throws Exception {
		return userMapper.getMicrobeUser();
	}

	@Override
	public String GetUserFullName(String userid) throws Exception {
		String name = "";
		if (userid==null) return "";
		if (UserList == null)
			UserList = GetMicUserList();
		for (Iterator<UserCustom> it = UserList.iterator(); it.hasNext();) {
			UserCustom user = it.next();
			if (user.getUsrnam().equals(userid))
			{
				name = user.getFullname();
				break;
			}
		}
		
		if (name.equals("")){
			UserVo userVo = new UserVo();
			UserCustom userCustom = new UserCustom();
			userCustom.setUsrnam(userid);
			userVo.setUserCustom(userCustom);
			userCustom = (UserCustom) userMapper.getUser(userVo);
			if (userCustom!=null){
				name = userCustom.getFullname();
				UserList.add(userCustom);
			}			
		}
		
		return name;
	}

}
