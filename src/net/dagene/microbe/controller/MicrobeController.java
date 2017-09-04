package net.dagene.microbe.controller;

import net.dagene.microbe.service.MicrobeService;
import net.dagene.microbe.vo.QueryMicrobeParamVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("mic")
public class MicrobeController {
	@Autowired
	private MicrobeService ms;
	
	public MicrobeController(){
	}
	
	@RequestMapping("/micdata1")
	public @ResponseBody
	String GetTest(String begdate, String enddate, String clientid) throws Exception {
		//Thread.sleep(5*60*1000);
		QueryMicrobeParamVo p = new QueryMicrobeParamVo();
		clientid = new String(clientid.getBytes("ISO-8859-1"), "UTF-8");
		//System.out.println(begdate);
		//System.out.println(enddate);
		System.out.println(clientid);
		
		p.setBeg_rptdate(begdate);
		p.setEnd_rptdate(enddate);
		p.setRasclientid(clientid);
		return ms.getResult(p);
	}
}
