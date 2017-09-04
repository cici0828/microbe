package net.dagene.pmis.pathology.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import net.dagene.pmis.common.po.PoState;
import net.dagene.pmis.common.po.csltAttcPictureObj;
import net.dagene.pmis.common.po.csltObject;
import net.dagene.pmis.common.po.csltPatientObj;
import net.dagene.pmis.common.po.csltSliceObj;
import net.dagene.pmis.pathology.model.CsltModel;
import net.dagene.pmis.pathology.model.CsltPicModel;
import net.dagene.pmis.pathology.po.AttcPicture;
import net.dagene.pmis.pathology.po.CsltInfotPo;
import net.dagene.pmis.pathology.po.CsltPicPo;
import net.dagene.pmis.pathology.service.CsltService;
import net.dagene.pmis.pathology.service.impl.CsltServiceImpl;
import net.dagene.pmis.pathology.vo.CsltInfoVo;
import net.dagene.pmis.pathology.vo.CsltPicVo;
import net.dagene.pmis.util.HttpGetFileUtil;
import net.dagene.pmis.util.ObjectSerializeUtil;
import net.dagene.pmis.util.PropertiesUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("template")
public class CsltTemplateAction {
	@Autowired
	private CsltService csltService;
	
	private String clipimgURL = PropertiesUtil.getResourceProperty("clipimgURL");

	public CsltTemplateAction() {
		
	}
	
	@RequestMapping("/diagnosis_infopanel")
	public ModelAndView  diagnosis_infopanel() throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/template/diagnosis_infopanel.jsp");
		return modelAndView;
	}
	
	@RequestMapping("/insert_expert")
	public ModelAndView insert_expert() throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/template/insert_expert.jsp");
		return modelAndView;
	}
	
	//专家选择模板
	@RequestMapping("/diagnosis_expert")
	public ModelAndView diagnosis_expert() throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/template/diagnosis_expert.jsp");
		return modelAndView;
	}
	
	@RequestMapping("/getResultReport")
	public ModelAndView getResultReport(String barcode) throws Exception{
	    CsltModel csltModel = csltService.getUnConfirmCsltResult(barcode);
	    ModelAndView modelAndView = new ModelAndView();
	    if (csltModel!=null)
	    {
	       List<AttcPicture> attcPicture = csltService.getCsltPicInfoListByCCID(csltModel.getCcid());
	       modelAndView.addObject("picList", attcPicture);
		   modelAndView.addObject("csltModel", csltModel);		
		   modelAndView.addObject("picURL", clipimgURL);
		   modelAndView.setViewName("/template/resultReport.jsp");
	    }
	    else
	    {
	    	modelAndView.setViewName("/template/getResultReportFailure.jsp");
	    }
				
		return modelAndView;
	}
	
	
}
