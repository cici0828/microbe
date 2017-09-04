package net.dagene.pmis.webservice.impl;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import net.dagene.pmis.common.po.PoState;
import net.dagene.pmis.common.po.csltObject;
import net.dagene.pmis.pathology.model.CsltModel;
import net.dagene.pmis.pathology.po.CsltInfotPo;
import net.dagene.pmis.pathology.vo.CsltInfoVo;
import net.dagene.pmis.webservice.PMISWebService;
import net.dagene.pmis.pathology.service.CsltService;

@WebService
public class PMISWebServiceImpl implements PMISWebService {
	@Autowired
	CsltService csltService;
	public String recResult(String text) {
		try{			
			return csltService.setCslt(text, "op_userid", "op_name");
		}catch(Exception e){
			return "0";
		}
	}
}
