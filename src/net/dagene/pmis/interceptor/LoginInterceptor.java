package net.dagene.pmis.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.dagene.pmis.system.po.Menu;
import net.dagene.pmis.system.po.Module;
import net.dagene.pmis.system.po.UserCustom;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {

	public LoginInterceptor() {

	}

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		if (true)
		return  true;		
		
		String url = request.getRequestURI();
		if ((url.indexOf("login.action") > 0)||
				(url.indexOf("login2.action") > 0)
				|| (url.indexOf("getDeptList.action") > 0)
				|| (url.indexOf("logout.action") > 0)) {
			return true;
		}
		

		HttpSession session = request.getSession();
		UserCustom userCustom = (UserCustom) session.getAttribute("userCustom");
		if (userCustom != null) {
			if (url.indexOf("/menu") > 0)
			{
				List<Module> modules = userCustom.getModules();
				int i=0;
				for(i=0; i<modules.size(); i++){
					Module module = modules.get(i);
					List<Menu> menus = module.getMenuList();
					for(int j=0; j<menus.size(); j++){
						Menu menu = menus.get(j);
						if ((url+"?"+request.getQueryString()).indexOf(menu.getUrl()) > 0) return true;						
					}
				}
				
				if (i ==  modules.size()){
					request.getRequestDispatcher("/error_menu.jsp").forward(request, response);
					return false;
				}
 			}
			return true;
		}

		request.getRequestDispatcher("/index.jsp").forward(request, response);
		return false;
	}


	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}


	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
