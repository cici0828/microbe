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
import net.dagene.pmis.pathology.po.CsltInfotPo;
import net.dagene.pmis.pathology.service.CsltService;
import net.dagene.pmis.pathology.service.impl.CsltServiceImpl;
import net.dagene.pmis.pathology.vo.CsltInfoVo;
import net.dagene.pmis.util.HttpGetFileUtil;
import net.dagene.pmis.util.ObjectSerializeUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("api")
public class CsltWebServer {
	@Autowired
	CsltService csltService;

	public CsltWebServer() {
	}
/*
	@RequestMapping("/getn")
	public @ResponseBody
	void getn(String barcode) throws Exception {	
		// csltService.test("{a:1}");
		// System.out.println(tmp);
		// csltService.s
		csltObject obj = csltService.t_getObj("201200621");
		obj.setState("16");

		List<csltAttcPictureObj> list=	new ArrayList<csltAttcPictureObj>();
		for (int i = 0; i < 3; i++) {
			csltAttcPictureObj pic = new csltAttcPictureObj();
			pic.setDescribe("描述"+i);
			pic.setPicid(""+i);
			pic.setReportpic("http://oa.dazd.cn/images/homepage/noimgdefault.jpg");
			list.add(pic);
		}
		obj.setAttcpicturelist(list);
		String m = csltService.setCslt(ObjectSerializeUtil.getStrFromObj(obj),"name", "id");
	}*/

	@RequestMapping("/get2")
	public @ResponseBody
	void get2() throws Exception {

		/*
		 * Properties pro =
		 * net.dagene.pmis.util.PropertiesUtil.GetProperties("/resource.properties"
		 * ); if(pro!=null){
		 * System.out.println(pro.getProperty("resourceImageDomain"));
		 * System.out.println(pro.getProperty("mongodbDomain"));
		 * System.out.println(pro.getProperty("mongodbPort"));
		 * System.out.println(pro.getProperty("mongodbName")); }
		 * 
		 * Map<String,String> map = new HashMap<String,String>();
		 * map.put("resourceImageDomain", "sina.com.cn");
		 * net.dagene.pmis.util.PropertiesUtil
		 * .WriteProperties("/resource.properties",map);
		 * 
		 * if(pro!=null){
		 * System.out.println(pro.getProperty("resourceImageDomain"));
		 * System.out.println(pro.getProperty("mongodbDomain"));
		 * System.out.println(pro.getProperty("mongodbPort"));
		 * System.out.println(pro.getProperty("mongodbName")); }
		 * 
		 * pro =
		 * net.dagene.pmis.util.PropertiesUtil.GetProperties("/resource.properties"
		 * ); if(pro!=null){
		 * System.out.println(pro.getProperty("resourceImageDomain"));
		 * System.out.println(pro.getProperty("mongodbDomain"));
		 * System.out.println(pro.getProperty("mongodbPort"));
		 * System.out.println(pro.getProperty("mongodbName")); }
		 */

	}

	@RequestMapping("/test")
	public @ResponseBody
	void test() throws Exception {
		// csltService.insertCslt(new CsltModel());
		CsltModel model = new CsltModel();
		model.setBarcode("123456");
		model.setCstate("0");
		model.setExpertcslt("ababab");
		model.setExpertcslt_en("expertcslt_en");
		model.setExpertid("10");
		model.setExpertname("nam");
		model.setFolderno("folderno");
		model.setPid(0);
		model.setRslt_comments("rslt_comments");
		model.setPid(111);

		CsltModel model2 = csltService.insertCslt(model);

		/*
		 * byte[] bytes=GetImageUtil.GetBytes(
		 * "http://img1.cache.netease.com/catchpic/9/9A/9A6245464E6107474D9EEF8E83D6233F.jpg"
		 * );
		 * 
		 * File imageFile = new File("C:/baidu.png"); FileOutputStream outStream
		 * = new FileOutputStream(imageFile); outStream.write(bytes);
		 * outStream.close();
		 */
		return;
	}

	private csltObject getObj() {
		csltObject obj = new csltObject();
		obj.setPid(350);
		obj.setPt_barcode("144396386");
		obj.setState("16");
		obj.setExpertname("T_沈炜");
		obj.setExpertid("181");
		obj.setExpertdate(new Date());
		obj.setExpertfinishdate(new Date());
		obj.setDept("青岛兰信");
		obj.setPicnum(0);
		obj.setLoginname("name");
		obj.setLogindate(new Date());
		obj.setFolderno("PD0000509817");
		obj.setFee((float) 50);
		obj.setPatient(new csltPatientObj());
		obj.getPatient().setOrigrec((long) 59331098);
		obj.getPatient().setSamplefrom("青岛大学师范学院卫生所");
		obj.getPatient().setPatientname("郎征");
		obj.getPatient().setSex("女");
		obj.getPatient().setAgeunit("岁");
		obj.getPatient().setAge("23");
		obj.getPatient().setAgex("23岁");
		obj.getPatient().setFolderno("PD0000509817");
		obj.getPatient().setSendstuff("右颞部肿块");
		obj.setClidata("右颞部肿块切除术");
		obj.setClihistory("发现右颞部肿块2年");
		obj.setGross("右颞部肿块：灰红灰白色组织一个，大小约2.5cm×2.0cm×0.8cm，切开切面灰红灰白色，灶性出血。");
		obj.setFirst_option("（右颞部）考虑为汗腺来源的肿瘤（汗腺癌可能性大）。");
		obj.setLetter("");

		//obj.setSlicelist(new ArrayList<csltSliceObj>());

		obj.setExpertcslt("未见病变456111");
		obj.setBackreason("退回原因");
		obj.setRslt_comments("fengwei1");
		obj.setAttcpicturelist(new ArrayList<csltAttcPictureObj>());

		for (int i = 1; i < 4; i++) {
			csltAttcPictureObj item = new csltAttcPictureObj();
			item.setPicid("123" + i);
			item.setDescribe("描述" + i);
			item.setReportpic("文件地址" + i);
			obj.getAttcpicturelist().add(item);
		}

		return obj;
	}

	@RequestMapping("/setCslt")
	public @ResponseBody
	PoState setCslt(String csltJson) throws Exception {
		PoState state = new PoState();
		String op_userid;
		String op_name;
		// 字符串转成数据对象
		/*
		 * csltObject obj = (csltObject)
		 * net.dagene.pmis.util.ObjectSerializeUtil
		 * .getObjectFromJsonString(csltJson, csltObject.class);
		 */
		csltObject obj = getObj();

		op_userid = obj.getExpertid();
		op_name = obj.getExpertname();
		// 保存返回信息（select * from t_pty_csltinfo_en）
		CsltInfotPo cslt_info_po = csltService.getCsltAuditViewByBarcode(obj
				.getPt_barcode());
		// 无相关记录不处理
		if (cslt_info_po == null || cslt_info_po.getEbid() == 0) {
			state.setRespstate(false);
			return state;
		}
		CsltInfoVo cslt_info = new CsltInfoVo();
		cslt_info.setEbid(cslt_info_po.getEbid());
		cslt_info.setState(obj.getState());// 状态
		cslt_info.setBarcode(obj.getPt_barcode());// 条码
		cslt_info.setLetter(obj.getLetter());// 附言（英文）
		cslt_info.setCliresult_en(obj.getExpertcslt());// 诊断结果

		// obj.setExpertcslt("未见病变456111");
		// 是否退回
		if (obj.getState().equals("0") || obj.getState().equals("17"))
			cslt_info.setRemark(obj.getBackreason());// 退回原因（备注）
		else
			cslt_info.setRemark(obj.getRslt_comments());// 退回原因（备注）
		state = csltService.setCsltInfo(cslt_info, op_userid, op_name);// 保存信息及状态（默认添加日志）

		if (state.getRespstate()) {
			// 添加结果信息
			CsltModel cslt_model = new CsltModel();
			cslt_model.setPid(obj.getPid());
			cslt_model.setExpertcslt(obj.getExpertname());
			cslt_model.setExpertid(obj.getExpertid());
			cslt_model.setCommitdate(obj.getExpertfinishdate());
			cslt_model.setCstate("1");
			cslt_model.setRslt_comments(obj.getLoginname());
			cslt_model.setExpertcslt_en(obj.getExpertcslt());
			cslt_model.setFolderno(obj.getFolderno());
			cslt_model.setBarcode(obj.getPt_barcode());
			// 保存
			csltService.insertCslt(cslt_model);

			// 图片属性添加 （select * from t_pty_csltpic）
			if (obj.getAttcpicturelist() != null) {
				csltService.insertCsltPics(obj);
			}
		}
		return state;
	}
}
