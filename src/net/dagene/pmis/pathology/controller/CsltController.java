package net.dagene.pmis.pathology.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.dagene.pmis.common.po.PoState;
import net.dagene.pmis.common.po.csltObject;
import net.dagene.pmis.pathology.model.CsltExpertModel;
import net.dagene.pmis.pathology.model.CsltPicModel;
import net.dagene.pmis.pathology.model.MicrobeModel;
import net.dagene.pmis.pathology.model.SliceFileModel;
import net.dagene.pmis.pathology.po.AttcPicture;
import net.dagene.pmis.pathology.po.CsltBaseInfo;
import net.dagene.pmis.pathology.po.CsltDiagnosisListPo;
import net.dagene.pmis.pathology.po.CsltExpertPo;
import net.dagene.pmis.pathology.po.CsltInfotPo;
import net.dagene.pmis.pathology.po.CsltLog;
import net.dagene.pmis.pathology.po.CsltLogInfoCustom;
import net.dagene.pmis.pathology.po.CsltPicPo;
import net.dagene.pmis.pathology.po.CsltProgramCustom;
import net.dagene.pmis.pathology.po.CsltResultCustom;
import net.dagene.pmis.pathology.po.CsltSliceInfoCustom;
import net.dagene.pmis.pathology.po.TranslatePo;
import net.dagene.pmis.pathology.po.TranslateTotalPo;
import net.dagene.pmis.pathology.service.CsltService;
import net.dagene.pmis.pathology.vo.BaseCsltVo;
import net.dagene.pmis.pathology.vo.CsltDiagnosisVo;
import net.dagene.pmis.pathology.vo.CsltExpertVo;
import net.dagene.pmis.pathology.vo.CsltInfoVo;
import net.dagene.pmis.pathology.vo.CsltPicVo;
import net.dagene.pmis.pathology.vo.ExceClstExpertCommitVo;
import net.dagene.pmis.pathology.vo.ExecClstCommitExpertVo;
import net.dagene.pmis.pathology.vo.GetDataListVo;
import net.dagene.pmis.system.po.UserCustom;
import net.dagene.pmis.util.PropertiesUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;

@Controller
@RequestMapping("cslt")
public class CsltController {
	@Autowired
	CsltService csltService;

	public CsltController() {
	}

	// 专家 列表页面
	@RequestMapping("/csltDiagnosisExpert")
	public ModelAndView csltDiagnosisExpert() throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("authCode", "1");// 1.翻译员 2.分中心 3.总部
		// modelAndView.addObject("resourceImageDomain",PropertiesUtil.getResourceProperty("resourceImageDomain"));
		modelAndView.setViewName("/csltDiagnosisExpert");
		return modelAndView;
	}

	@RequestMapping("delExpert")
	public @ResponseBody
	PoState delExpert(int id) throws Exception {
		return csltService.deleteCsltExpert(id);
		/*
		 * PoState ps=new PoState(); ps.setRespstate(true); return ps;
		 */
	}

	@RequestMapping("addExpert")
	public @ResponseBody
	PoState addExpert(CsltExpertVo vo) throws Exception {
		/*
		 * CsltExpertVo vo=new CsltExpertVo(); CsltExpertModel model=new
		 * CsltExpertModel(); model.setExpertname("m_武林海");
		 * model.setTel("15900000000"); model.setNature("内部");
		 * model.setFinishdate(new Date()); model.setFee(1.11);
		 * model.setIswork("是");
		 */
		return csltService.insertCsltExpert(vo);
	}

	@RequestMapping("setExpert")
	public @ResponseBody
	PoState setExpert(CsltExpertVo vo) throws Exception {
		/*
		 * CsltExpertVo vo=new CsltExpertVo(); CsltExpertModel model=new
		 * CsltExpertModel(); model.setExpertname("m_武林海"); model.setSex("男");
		 * model.setTel("15900000000"); model.setNature("内部");
		 * model.setFinishdate(new Date()); model.setFee(3.11);
		 * model.setIswork("否"); model.setId(201); vo.setExpert(model);
		 */
		if (vo.getExpert().getId() != null && vo.getExpert().getId() > 0) {
			return csltService.updateCsltExpert(vo);
		} else {
			return csltService.insertCsltExpert(vo);
		}
	}

	@RequestMapping("execPtyClstCommitExpert")
	public @ResponseBody
	PoState execPtyClstCommitExpert(HttpServletRequest request,
			ExecClstCommitExpertVo vo) throws Exception {

		Object o = request.getSession().getAttribute("userCustom");
		if (o != null) {
			UserCustom userCustom = (UserCustom) request.getSession()
					.getAttribute("userCustom");

			vo.setOp_name(userCustom.getFullname());
			vo.setOp_tel(userCustom.getUsertel());
			vo.setOp_userid(userCustom.getUsrnam());
		}

		PoState ps = csltService.execPtyClstCommitExpert(vo);
		return ps;
	}

	@RequestMapping("execPtyClstExpertCommit")
	public @ResponseBody
	PoState execPtyClstExpertCommit(HttpServletRequest request,
			ExceClstExpertCommitVo vo) throws Exception {
		Object o = request.getSession().getAttribute("userCustom");
		if (o != null) {
			UserCustom userCustom = (UserCustom) request.getSession()
					.getAttribute("userCustom");

			vo.setOp_name(userCustom.getFullname());
			vo.setOp_tel(userCustom.getUsertel());
			vo.setOp_userid(userCustom.getUsrnam());
		}

		PoState ps = csltService.execPtyClstExpertCommit(vo);
		return ps;
	}

	@RequestMapping("getExpertList")
	public @ResponseBody
	List<CsltExpertPo> getExpertList(BaseCsltVo vo) throws Exception {
		List<CsltExpertPo> list = csltService.getExpertList(vo);
		return list;
	}

	// 会诊页面
	@RequestMapping("/menu/diagnosisRequest")
	public ModelAndView diagnosisRequest(int authCode) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("authCode", authCode);// 1.翻译员 2.分中心 3.总部

		modelAndView.addObject("resourceImageDomain",
				PropertiesUtil.getResourceProperty("resourceImageDomain"));
		modelAndView.setViewName("/diagnosisRequest.jsp");
		return modelAndView;
	}

	@RequestMapping("getDiagnosisList")
	public @ResponseBody
	List<CsltDiagnosisListPo> getDiagnosisList(HttpServletRequest request,
			GetDataListVo vo) throws Exception {
		if (vo.getState() == null || vo.getState().equals("")) {
			return null;
		} else {
			vo.setStates(vo.getState().split(","));
		}
		Object o = request.getSession().getAttribute("userCustom");
		if (o != null) {
			UserCustom userCustom = (UserCustom) request.getSession()
					.getAttribute("userCustom");
			vo.setDept(userCustom.getLogindept());
		}
		List<CsltDiagnosisListPo> list = csltService.getCsltDiagnosisList(vo);
		return list;
	}

	@RequestMapping("/sendDiagnosis")
	public @ResponseBody
	PoState sendDiagnosis(HttpServletRequest request, BaseCsltVo vo)
			throws Exception {
		Object o = request.getSession().getAttribute("userCustom");
		if (o != null) {
			UserCustom userCustom = (UserCustom) request.getSession()
					.getAttribute("userCustom");

			vo.setOp_name(userCustom.getFullname());
			vo.setOp_tel(userCustom.getUsertel());
			vo.setOp_userid(userCustom.getUsrnam());
			vo.setDept(userCustom.getLogindept());

		}
		return csltService.sendDiagnosis(vo);
	}

	@RequestMapping("/deleteDiagnosis")
	public @ResponseBody
	PoState deleteDiagnosis(HttpServletRequest request, BaseCsltVo vo)
			throws Exception {
		PoState state = new PoState();
		Object o = request.getSession().getAttribute("userCustom");
		if (o != null) {
			UserCustom userCustom = (UserCustom) request.getSession()
					.getAttribute("userCustom");

			vo.setOp_name(userCustom.getFullname());
			vo.setOp_tel(userCustom.getUsertel());
			vo.setOp_userid(userCustom.getUsrnam());
		}
		csltService.deleteDiagnosis(vo);
		return state;
	}

	// 修改信息
	@RequestMapping("/setDiagnosis")
	public @ResponseBody
	PoState setDiagnosis(HttpServletRequest request, CsltDiagnosisVo vo)
			throws Exception {
		Object o = request.getSession().getAttribute("userCustom");
		if (o != null) {
			UserCustom userCustom = (UserCustom) request.getSession()
					.getAttribute("userCustom");

			vo.setOp_name(userCustom.getFullname());
			vo.setOp_tel(userCustom.getUsertel());
			vo.setOp_userid(userCustom.getUsrnam());
		}

		return csltService.updateDiagnosis(vo);
	}

	// 获取切片列表
	@RequestMapping("/getCsltSliceList")
	public @ResponseBody
	List<CsltSliceInfoCustom> getCsltSliceList(HttpServletRequest request,
			BaseCsltVo vo) throws Exception {
		Object o = request.getSession().getAttribute("userCustom");
		if (o != null) {
			UserCustom userCustom = (UserCustom) request.getSession()
					.getAttribute("userCustom");

			vo.setOp_name(userCustom.getFullname());
			vo.setOp_tel(userCustom.getUsertel());
			vo.setOp_userid(userCustom.getUsrnam());
		}
		return csltService.getCsltSliceList(vo);
	}

	@RequestMapping("/getCsltSliceListFromMySQL")
	public @ResponseBody
	List<SliceFileModel> getCsltSliceListFromMySQL(String barcode)
			throws Exception {
		return csltService.getSliceListByBarcodeFromMySQL(barcode);
	}

	// 从mysql获取切片列表test
	@RequestMapping("/getCsltSliceList_mysql")
	public @ResponseBody
	List<SliceFileModel> getSliceList() throws Exception {
		csltService.createProgramFromMySQL();
		return csltService.getUploadSliceList();
	}

	// 获取小图
	@RequestMapping("/getCsltSliceSmallPic")
	public void getCsltSliceSmallPic(int dsid, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		byte[] img = csltService.getCsltSliceSmallPic(dsid);

		if (img != null) {
			OutputStream stream = response.getOutputStream();
			try {
				stream.write(img);
				stream.flush();
				response.setContentType("image/jpeg");
			} catch (IOException e) {
				throw e;
			} finally {
				if (null != stream) {
					try {
						stream.close();
					} catch (IOException e) {
						throw e;
					}
				}
			}
		}
	}

	// ========================
	@RequestMapping("/getCsltPicsByBarcode")
	public @ResponseBody
	List<CsltPicPo> getCsltPicsByBarcode(String barcode) throws Exception {
		CsltPicVo object = new CsltPicVo();
		object.setBarcode(barcode);
		object.setDisplay(-1);
		return csltService.getCsltPics(object);
	}

	@RequestMapping("/setCsltPics")
	public @ResponseBody
	void setCsltPics(String json) throws Exception {
		String[] jsonArr = json.split("\\|:\\|");
		List<CsltPicVo> list = new ArrayList<CsltPicVo>();
		for (int i = 0; i < jsonArr.length; i++) {
			CsltPicVo voModel = (CsltPicVo) net.dagene.pmis.util.ObjectSerializeUtil
					.getObjectFromJsonString(jsonArr[i], CsltPicVo.class);
			list.add(voModel);
		}
		csltService.updateCsltPics(list);
	}

	// 获取日志信息
	@RequestMapping("/getCsltLogList")
	public @ResponseBody
	List<CsltLog> getCsltLog(String barcode) throws Exception {
		List<CsltLog> po = csltService.getCsltLog(barcode);
		return po;
	}

	// 操作展示翻译页面统计数据
	@RequestMapping("/getTranslateTotal")
	public @ResponseBody
	List<TranslateTotalPo> getTranslateTotal(GetDataListVo vo) throws Exception {
		List<TranslateTotalPo> po = csltService.getTranslateTotal(vo);
		return po;
	}

	// 操作翻译页面数据
	@RequestMapping("/getTranslateList")
	public @ResponseBody
	List<TranslatePo> getTranslateList(GetDataListVo vo) throws Exception {
		if (vo.getState() == null || vo.getState().equals("")) {
			return null;
		} else {
			vo.setStates(vo.getState().split(","));
		}
		List<TranslatePo> po = csltService.getTranslateList(vo);
		return po;
	}

	// 操作翻译展示页面
	@RequestMapping("/jhhViews")
	public ModelAndView mainViews(CsltInfoVo object) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("authCode", "1");// 1.翻译员 2.分中心 3.总部
		modelAndView.addObject("resourceImageDomain",
				PropertiesUtil.getResourceProperty("resourceImageDomain"));
		modelAndView.setViewName("/jhhViews.jsp");
		return modelAndView;
	}

	// 补充信息保存
	@RequestMapping("/setCsltBaseinfo")
	public @ResponseBody
	PoState setCsltBaseinfo(HttpServletRequest request, CsltInfoVo vo)
			throws Exception {
		Object o = request.getSession().getAttribute("userCustom");
		String op_userid = null;
		String op_name = null;
		if (o != null) {
			UserCustom userCustom = (UserCustom) request.getSession()
					.getAttribute("userCustom");

			op_userid = userCustom.getUsrnam();
			op_name = userCustom.getFullname();

		}
		PoState state_obj = new PoState();
		state_obj = csltService.setCsltBaseInfo(vo, op_userid, op_name);

		return state_obj;
	}

	/*
	 * // 详细信息
	 * 
	 * @RequestMapping("/auditDetail") public ModelAndView
	 * auditDetail(CsltInfotVo object) throws Exception { ModelAndView
	 * modelAndView = new ModelAndView(); modelAndView.addObject("ebid",
	 * object.ebid); modelAndView.addObject("barcode", object.barcode);
	 * modelAndView.setViewName("/csltAuditDetail"); return modelAndView; }
	 */
	// 翻译审核页面
	@RequestMapping("/jhhAuditView")
	public ModelAndView auditView(CsltInfoVo object) throws Exception {
		// System.out.println("-----------------------------------------------------------");
		ModelAndView modelAndView = new ModelAndView();
		// modelAndView.addObject("pid", object.pid);
		modelAndView.addObject("resourceImageDomain",
				PropertiesUtil.getResourceProperty("resourceImageDomain"));
		modelAndView.setViewName("/jhhCsltAuditView.jsp");
		return modelAndView;
	}

	// 获取翻译中文信息
	@RequestMapping("/getAuditCnView")
	public @ResponseBody
	CsltInfotPo getAuditCnView(String BARCODE) throws Exception {
		CsltInfotPo po = csltService.getCsltAuditCnView(BARCODE);
		return po;
	}

	// 获取翻译英文信息
	@RequestMapping("/getAuditView")
	public @ResponseBody
	CsltInfotPo getAuditView(Integer EBID) throws Exception {
		CsltInfotPo po = csltService.getCsltAuditView(EBID);
		return po;
	}

	// 获取需要审核的翻译列表
	@RequestMapping("/getAuditList")
	public @ResponseBody
	List<CsltInfotPo> getAuditList(CsltInfoVo object) throws Exception {
		List<CsltInfotPo> list = csltService.getCsltAuditList(object);
		return list;
	}

	// 保存翻译信息
	@RequestMapping("/setCsltInfo")
	public @ResponseBody
	PoState setCsltInfo(HttpServletRequest request, CsltInfoVo object)
			throws Exception {
		Object o = request.getSession().getAttribute("userCustom");
		String op_userid = null;
		String op_name = null;
		if (o != null) {
			UserCustom userCustom = (UserCustom) request.getSession()
					.getAttribute("userCustom");
			op_userid = userCustom.getUsrnam();
			op_name = userCustom.getFullname();
		}
		PoState state_obj = new PoState();
		state_obj = csltService.setCsltInfo(object, op_userid, op_name);
		return state_obj;
	}

	// ======================================================================
	@RequestMapping("/getCsltlistByExpertid")
	public @ResponseBody
	List<CsltProgramCustom> getCsltlistByExpertid(String expertID)
			throws Exception {
		expertID = "181";
		List<CsltProgramCustom> list = csltService
				.getCsltProgramListByExperID(expertID);
		return list;
	}

	@RequestMapping("/getCsltDetailForm")
	public ModelAndView getCsltDetailForm(Integer pid) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("pid", pid);
		modelAndView.setViewName("/csltDetailForm");
		return modelAndView;
	}

	@RequestMapping("/getCsltBaseInfoByPID")
	public @ResponseBody
	CsltBaseInfo getCsltBaseInfoByPID(Integer pid) throws Exception {
		CsltBaseInfo csltBaseInfo = csltService.getCsltBaseInfoByPID(pid);
		return csltBaseInfo;
	}

	@RequestMapping("/getCsltSliceInfoByBarcode")
	public @ResponseBody
	List<CsltSliceInfoCustom> getCsltSliceInfoByBarcode(String barcode)
			throws Exception {
		return csltService.getCsltSliceInfoByBarcode(barcode);
	}

	@RequestMapping("/getCsltLogInfoCustomByPID")
	// cslt/getCsltLogInfoCustomByPID?action=350
	public @ResponseBody
	List<CsltLogInfoCustom> getCsltLogInfoCustomByPID(Integer pid)
			throws Exception {
		return csltService.getCsltLogInfoCustomByPID(pid);
	}

	@RequestMapping("/getSliceListFromMDB")
	public @ResponseBody
	int getSliceListFromMDB(HttpServletRequest request) throws Exception {
		Object o = request.getSession().getAttribute("userCustom");
		String dept = null;
		if (o != null) {
			UserCustom userCustom = (UserCustom) request.getSession()
					.getAttribute("userCustom");
			dept = userCustom.getLogindept();
		}
		csltService.getSliceListFromMDB(dept);
		return 1;
	}

	@RequestMapping("/getSliceUploadState")
	public @ResponseBody
	String getSliceUploadState(String barcode) throws Exception {
		return csltService.getSliceUploadState(barcode);

	}

	@RequestMapping("/getSliceShortPicByLi")
	public void getSliceShortPicByDSID(HttpServletResponse response,
			Integer dsno, String folder) throws Exception {
		// http://192.168.0.217:8888/slice/slicefile/SLice2016-03-010001/preview.jpg

	}

	@RequestMapping("/getSliceShortPicByDSID")
	public void getSliceShortPicByDSID(HttpServletResponse response,
			Integer dsid) throws Exception {
		if ((dsid != null) && (!"".equals(dsid))) {
			response.setContentType("image/jpeg");
			CsltSliceInfoCustom csltSliceInfoCustom = csltService
					.getSliceShortPicByDSID(dsid);
			if (csltSliceInfoCustom.getShortpic() != null) {
				OutputStream os = null;
				try {
					os = response.getOutputStream();
					os.write(csltSliceInfoCustom.getShortpic());
					os.flush();
				} catch (IOException e) {

					throw e;
				} finally {
					if (null != os) {
						try {
							os.close();
						} catch (IOException e) {
							throw e;
						}
					}
				}
			}
		}
	}

	@RequestMapping("/GetUnCommitResult")
	public @ResponseBody
	CsltResultCustom GetUnCommitResult(Integer pid) throws Exception {
		// TODO 传入专家ID
		return csltService.GetUnCommitResult(pid, null);
	}

	@RequestMapping("/resultConfirm")
	public @ResponseBody
	int resultConfirm(String pt_barcode, Integer pid, String reporttype)
			throws Exception {
		return csltService.resultConfirm(pt_barcode, pid, reporttype);

	}

	@RequestMapping("/getCsltPicByID")
	public void getCsltPicByID(HttpServletResponse response, String id)
			throws Exception {
		if ((id != null) && (!"".equals(id))) {
			AttcPicture attcPicture = csltService.getCsltPicByID(id);
			if (attcPicture.getPicture() != null) {
				OutputStream os = null;
				try {
					os = response.getOutputStream();
					os.write(attcPicture.getPicture());
					os.flush();
					response.setContentType("image/jpeg");
				} catch (IOException e) {

					throw e;
				} finally {
					if (null != os) {
						try {
							os.close();
						} catch (IOException e) {
							throw e;
						}
					}
				}
			}
		}
	}

	@RequestMapping("/getMicrobeResultByBarcode")
	public @ResponseBody
	List<MicrobeModel> getMicrobeResultByBarcode(String barcode)
			throws Exception {
		return csltService.getMicrobeResultByBarcode(barcode);
	}

	// http://localhost:8080/pmis/cslt/delMicrobeResult.action?barcode=088915347&microbecode=201609260002
	// http://localhost:8080/pmis/cslt/getMicrobeResultByBarcode.action?barcode=088344867
	@RequestMapping("/delMicrobeResult")
	public @ResponseBody
	Integer deleteMicrobeResult(String barcode, String microbecode, String test)
			throws Exception {
		if (test.equals("a87654321")) {
			MicrobeModel microbeModel = new MicrobeModel();
			microbeModel.setMic_barcode(barcode);
			microbeModel.setMicrobecode(microbecode);
			csltService.deleteMicrobeResult(microbeModel);
			return 0;
		} else
			return -2;
	}

}
