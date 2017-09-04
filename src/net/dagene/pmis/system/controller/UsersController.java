package net.dagene.pmis.system.controller;

//forword页面转发url地址不变，request可以共享 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.dagene.pmis.system.po.UserCustom;
import net.dagene.pmis.system.service.UsersService;
import net.dagene.pmis.system.vo.UserVo;
import net.dagene.pmis.util.MD5Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("system")
public class UsersController {
	public UsersController() {
	}

	@Autowired
	private UsersService usersService;

	@RequestMapping("/getDeptList")
	public @ResponseBody String GetDeptList(String usrnam) throws Exception {
		String deptList = usersService.GetDeptList(usrnam);
		return deptList;
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) throws Exception {
		request.getSession().invalidate();
		return "redirect:/";
		
	}

	@RequestMapping("/login")
	public String Login(HttpServletRequest request, UserVo uservo) throws Exception {
		String returnURL = "redirect:/index.jsp";
		uservo.setPasswd(MD5Util.string2MD5(uservo.getPasswd()));
		UserCustom userCustom = usersService.UserLogin(uservo);
		if (userCustom == null) {
			returnURL = "redirect:/";
		} else {
			request.getSession().setAttribute("userCustom", userCustom);
			returnURL = "redirect:/mainframe.action";
		}

		return returnURL;

	}
	
	@RequestMapping("/login2")
	public @ResponseBody int Login2(HttpSession session, UserVo uservo) throws Exception {
		uservo.setPasswd(MD5Util.string2MD5(uservo.getPasswd()));
		UserCustom userCustom = usersService.UserLogin(uservo);
		if (userCustom == null) {
			return 0;
		} else {
			session.setAttribute("userCustom", userCustom);
			return 1;
		}
	}
	
	
	@RequestMapping("/login3")
	public ModelAndView Login3(HttpServletRequest request, UserVo uservo) throws Exception {
		//String returnURL = "redirect:/index.jsp";
		uservo.setPasswd(MD5Util.string2MD5(uservo.getPasswd()));
		UserCustom userCustom = usersService.UserLogin(uservo);
		//ModelAndView modelAndView = new ModelAndView();
		if (userCustom == null){
			//modelAndView.setViewName("/");
			return null;
			
		}else{
			request.getSession().setAttribute("userCustom", userCustom);
			//modelAndView.setViewName(new RedirectView("/mainFrame.html"));
			return new ModelAndView(new RedirectView("../mainFrame.html"));
		}
		// if (userCustom == null) {
		// returnURL = "redirect:/";
		// } else {
		// request.getSession().setAttribute("userCustom", userCustom);
		// returnURL = "redirect:/mainframe.action";
		// }

		//return modelAndView;

	}


}