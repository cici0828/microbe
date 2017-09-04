package net.dagene.pmis.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.dagene.pmis.system.mapper.ModuleMapper;
import net.dagene.pmis.system.po.Module;
import net.dagene.pmis.system.po.UserCustom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainFrmController {
	@Autowired
	private ModuleMapper moduleMapper;
	
	public MainFrmController() {

	}

	@RequestMapping("/mainframe")
	public ModelAndView getMainFrm(HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		
		Object o = request.getSession().getAttribute("userCustom");
		if (o != null) {
			UserCustom userCustom = (UserCustom) request.getSession()
					.getAttribute("userCustom");
			modelAndView.addObject("logindept", userCustom.getLogindept());
			modelAndView.addObject("username", userCustom.getUsrnam());
			modelAndView.addObject("fullname", userCustom.getFullname());
			
			Integer roleid2 = userCustom.getRoleid2();
			if (roleid2 != null)
			{
				List<Module> modules = moduleMapper.getModuleByRoleID(roleid2);
				userCustom.setModules(modules);
				modelAndView.addObject("modules", modules);
			}
			
			//getModuleByRoleID
		}
		
		modelAndView.setViewName("/mainFrame.jsp");
		return modelAndView;
	}

	@RequestMapping("/menufrm")
	public ModelAndView GetMenuFrm(HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/menufrm");
		return modelAndView;
	}

}
