package net.dagene.pmis.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

public class CustomExceptionResolver implements HandlerExceptionResolver {

	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {

		// CustomException customException = null;
		// if (ex instanceof CustomException) {
		// customException = (CustomException) ex;
		// } else {
		// // System.out.println("err_msg:" + ex.getMessage());
		// customException = new CustomException("未知错误");
		//
		// }
		String message = ex.getMessage();
		ModelAndView modelAndView = new ModelAndView();
		MappingJacksonJsonView mv = new MappingJacksonJsonView();
		Map attrs = new HashMap();
		attrs.put("error", message);
		mv.setAttributesMap(attrs);
		response.setStatus(500);
		modelAndView.setView(mv);
		return modelAndView;
	}

}
