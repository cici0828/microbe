package net.dagene.pmis.pathology.service.impl;

import net.dagene.pmis.util.ObjectSerializeUtil;
import net.dagene.pmis.util.MongoDBUtil;
import net.dagene.pmis.util.PropertiesUtil;

import java.io.DataInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.cxf.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import net.dagene.pmis.common.mapper.PatientInfoMapper;
import net.dagene.pmis.common.po.PatientinfoCustom;
import net.dagene.pmis.common.po.PoState;
import net.dagene.pmis.common.po.csltAttcPictureObj;
import net.dagene.pmis.common.po.csltObject;
import net.dagene.pmis.common.po.csltPatientObj;
import net.dagene.pmis.common.po.csltSliceObj;
import net.dagene.pmis.common.po.csltUsercustom;
import net.dagene.pmis.pathology.mapper.CsltProgramMapper;
import net.dagene.pmis.pathology.mapper.PathologyMapper;
import net.dagene.pmis.pathology.mapper.SliceMapper;
//import net.dagene.pmis.pathology.mapper.SliceMapper;
import net.dagene.pmis.pathology.model.AttcPictureModel;
import net.dagene.pmis.pathology.model.CsltExpertModel;
import net.dagene.pmis.pathology.model.CsltGroupprogramModel;
import net.dagene.pmis.pathology.model.CsltModel;
import net.dagene.pmis.pathology.model.CsltPatientinformation;
import net.dagene.pmis.pathology.model.CsltPicModel;
import net.dagene.pmis.pathology.model.DgsinfoModel;
import net.dagene.pmis.pathology.model.MicrobeModel;
import net.dagene.pmis.pathology.model.SliceFileModel;
import net.dagene.pmis.pathology.po.AttcPicture;
import net.dagene.pmis.pathology.po.CsltDiagnosisListPo;
import net.dagene.pmis.pathology.po.CsltExpertPo;
import net.dagene.pmis.pathology.po.CsltInfotPo;
import net.dagene.pmis.pathology.po.CsltBaseInfo;
import net.dagene.pmis.pathology.po.CsltLog;
import net.dagene.pmis.pathology.po.CsltLogInfoCustom;
import net.dagene.pmis.pathology.po.CsltPicPo;
import net.dagene.pmis.pathology.po.CsltProgramCustom;
import net.dagene.pmis.pathology.po.CsltResultCustom;
import net.dagene.pmis.pathology.po.CsltSliceInfoCustom;
import net.dagene.pmis.pathology.po.ExpertCustom;
import net.dagene.pmis.pathology.po.TranslatePo;
import net.dagene.pmis.pathology.po.TranslateTotalPo;
import net.dagene.pmis.pathology.service.CsltService;
import net.dagene.pmis.pathology.vo.BaseCsltVo;
import net.dagene.pmis.pathology.vo.CsltDiagnosisVo;
import net.dagene.pmis.pathology.vo.CsltExpertVo;
import net.dagene.pmis.pathology.vo.CsltGroupprogramVo;
import net.dagene.pmis.pathology.vo.CsltInfoVo;
import net.dagene.pmis.pathology.vo.CsltPicVo;
import net.dagene.pmis.pathology.vo.CsltProgramVo;
import net.dagene.pmis.pathology.vo.CsltResultVo;
import net.dagene.pmis.pathology.vo.CsltSliceVo;
import net.dagene.pmis.pathology.vo.ExceClstExpertCommitVo;
import net.dagene.pmis.pathology.vo.ExecClstCommitExpertVo;
import net.dagene.pmis.pathology.vo.ExpertVo;
import net.dagene.pmis.pathology.vo.GetDataListVo;
import net.dagene.pmis.util.HttpGetFileUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class CsltServiceImpl implements CsltService {
	@Autowired
	private CsltProgramMapper csltProgramMapper;

	@Autowired
	private PatientInfoMapper patientInfoMapper;

	@Autowired
	private PathologyMapper pathologyMapper;
	
	@Autowired
	private SliceMapper sliceMapper;

	private String clipimgURL = PropertiesUtil
			.getResourceProperty("clipimgURL");

	private CsltProgramVo getCsltProgramVo() {
		CsltProgramVo csltProgramVo = new CsltProgramVo();
		CsltProgramCustom csltProgramCustom = new CsltProgramCustom();
		csltProgramVo.setCsltProgramCustom(csltProgramCustom);
		return csltProgramVo;
	}

	private CsltResultVo getCsltResultVo() {
		CsltResultVo csltResultVo = new CsltResultVo();
		CsltResultCustom csltResultCustom = new CsltResultCustom();
		csltResultVo.setCsltResultCustom(csltResultCustom);
		return csltResultVo;
	}

	// 发送mongodb
	// 初始化发送对象
	private csltObject resultCsltFormat(CsltInfotPo ciPo,
			CsltGroupprogramModel cgModel, PatientinfoCustom pcVo)
			throws Exception {
		csltObject object = new csltObject();
		object.setPid(cgModel.getPid());// 流程ID
		object.setPt_barcode(cgModel.getPt_barcode());// 条码
		// object.setState(ciPo.getState());// 状态
		object.setState("0");// 状态
		object.setExpertdate(ciPo.getCommitdate());// 提交时间
		object.setExpertname(cgModel.getExpertname());// 专家名称
		// object.setExpertname("JHH");
		object.setExpertid(cgModel.getExpertid());// 专家ID
		// object.setExpertid("9999");
		object.setPicnum(cgModel.getPicnum());// 图片数量
		// object.setDept(cgModel.getDept());
		object.setDept(pcVo.getDept());
		object.setFolderno(pcVo.getFolderno());
		object.setLoginname(cgModel.getLoginname());
		object.setLogindate(cgModel.getLogindate());
		object.setFolderno(ciPo.getFolderno());
		object.setFee(cgModel.getFee());
		object.setClidata(ciPo.getClidata());
		object.setClihistory(ciPo.getClihistory());
		object.setGross(ciPo.getGross());
		object.setFirst_option(ciPo.getFirst_option());
		object.setLetter(ciPo.getLetter());
		object.setRslt_comments(ciPo.getRemark());

		object.setUsercustom(new csltUsercustom());
		object.getUsercustom().setUsertel("");

		// csltPatientObj
		object.setPatient(new csltPatientObj());
		object.getPatient().setOrigrec(pcVo.getOrigrec());// 主ID
		object.getPatient().setSamplefrom(pcVo.getSamplefrom());// 所属医院
		object.getPatient().setPatientname(ciPo.getPatientname());
		object.getPatient().setSex(ciPo.getSex());
		if (ciPo.getAge() != null)
			object.getPatient().setAge(Integer.toString(ciPo.getAge()));
		object.getPatient().setAgex(pcVo.getAgex());
		object.getPatient().setAgeunit(pcVo.getAgeunit());
		object.getPatient().setFolderno(ciPo.getFolderno());
		object.getPatient().setSendstuff(ciPo.getSendstuff());

		return object;
	}

	// 发送至mongodb
	private void saveCsltToMongodb(String barcode) throws Exception {
		CsltInfotPo ciPo = csltProgramMapper.getCsltAuditViewByBarcode(barcode);
		CsltGroupprogramModel cgModel = csltProgramMapper
				.getCsltGroupprogramByBarcode(barcode);
		PatientinfoCustom pcVo = patientInfoMapper
				.getPatientinfoCustomByBarcode(barcode);
		BaseCsltVo baseCsltVo = new BaseCsltVo();
		baseCsltVo.setBarcode(barcode);
		
		List<CsltSliceInfoCustom> sliceList = csltProgramMapper
				.getCsltSliceList(baseCsltVo);
				
		csltObject object = resultCsltFormat(ciPo, cgModel, pcVo);
		object.setSlicelist(sliceList);
		object.setExpertdate(new Date());
		// =================================
		// --把数据保存到mongodb数据库
		// =================================
		String json = ObjectSerializeUtil.getStrFromObj(object);
		MongoDBUtil.addOne("cslt", MongoDBUtil.getDocumentByJSON(json));
		return;
	}

	// 发送至mongodb
	private void saveCsltCnToMongodb(String barcode) throws Exception {
		CsltInfotPo ciPo = csltProgramMapper.getCsltAuditCnView(barcode);
		CsltGroupprogramModel cgModel = csltProgramMapper
				.getCsltGroupprogramByBarcode(barcode);
		PatientinfoCustom pcVo = patientInfoMapper
				.getPatientinfoCustomByBarcode(barcode);
		BaseCsltVo baseCsltVo = new BaseCsltVo();
		baseCsltVo.setBarcode(barcode);
		//List<CsltSliceInfoCustom> sliceList = csltProgramMapper
		//		.getCsltSliceList(baseCsltVo);
		List<CsltSliceInfoCustom> sliceList = getCsltSliceList(barcode, true);
		csltObject object = resultCsltFormat(ciPo, cgModel, pcVo);
		object.setSlicelist(sliceList);
		object.setExpertdate(new Date());
		// =================================
		// --把数据保存到mongodb数据库
		// =================================
		String json = ObjectSerializeUtil.getStrFromObj(object);
		MongoDBUtil.addOne("cslt", MongoDBUtil.getDocumentByJSON(json));
		return;
	}

	// private csltObject t_getObj(String barcode) throws Exception {
	// CsltInfotPo ciPo = csltProgramMapper.getCsltAuditViewByBarcode(barcode);
	// CsltGroupprogramModel cgModel =
	// csltProgramMapper.getCsltGroupprogramByBarcode(barcode);
	// //System.out.print(b)
	// PatientinfoCustom pcVo =
	// patientInfoMapper.getPatientinfoCustomByBarcode(barcode);
	// csltObject object = resultCsltFormat(ciPo, cgModel, pcVo);
	// return object;
	// }

	class ThreadGetAndSaveImages implements Runnable {
		Map<String, Object> images_map;

		// public ThreadGetAndSaveImages() {
		// }
		// private int ticket = 5;
		public ThreadGetAndSaveImages(Map<String, Object> _map) {
			images_map = _map;
		}

		public void run() {
			// try {
			// Thread.sleep(20 * 60 * 1000);
			// } catch (InterruptedException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			if (!images_map.isEmpty()) {
				for (Map.Entry<String, Object> entry : images_map.entrySet()) {
					try {
						// Thread.currentThread().getName()
						CsltPicModel insert_model = new CsltPicModel();
						insert_model.setPicture(HttpGetFileUtil
								.GetFileBytes(entry.getValue().toString()));
						insert_model.setId(entry.getKey());// ID
						insert_model.setDisplay(1);// 图片状态（-1代表未保存图片二进制数据）
						csltProgramMapper.updateCsltPic(insert_model);// 修改保存图片
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	// 删除专家
	public PoState deleteCsltExpert(Integer id) throws Exception {
		csltProgramMapper.deleteCsltExpert(id);
		PoState ps = new PoState();
		ps.setRespstate(true);
		return ps;
	}

	// 添加专家
	public PoState insertCsltExpert(CsltExpertVo vo) throws Exception {
		// 默认
		vo.getExpert().setIsdelete("0");
		vo.getExpert().setHistory("1");
		vo.getExpert().setViewnum(0);
		vo.getExpert().setIswork("是");
		csltProgramMapper.insertCsltExpert(vo);
		PoState ps = new PoState();
		ps.setRespstate(true);
		return ps;
	}

	// 修改专家信息
	public PoState updateCsltExpert(CsltExpertVo vo) throws Exception {
		csltProgramMapper.updateCsltExpert(vo);
		PoState ps = new PoState();
		ps.setRespstate(true);
		return ps;
	}

	// 提交专家会诊
	public PoState execPtyClstCommitExpert(ExecClstCommitExpertVo vo)
			throws Exception {
		PoState ps = new PoState();
		CsltGroupprogramModel cgModel = csltProgramMapper
				.getCsltGroupprogramByBarcode(vo.getBarcode());
		int old_state = cgModel.getState();
		if (old_state == 25 || old_state == 28 || old_state == 27 || old_state == 29) {
			//int picnum = csltProgramMapper.getCsltSliceCount(vo.getBarcode());
			int picnum = 1;
			if (picnum > 0) {
				vo.setVuserid(vo.getOp_userid());
				vo.setVusername(vo.getOp_name());
				vo.setVcommittel(vo.getOp_tel());
				vo.setVnum(1);
				vo.setIssendsms(0);
				csltProgramMapper.execPtyClstCommitExpert(vo);
				ps.setRespstate(vo.getSucflag2() == 0 ? false : true);
				ps.setRespmsg(vo.getErrInf2());
				if (ps.getRespstate()) {
					saveCsltCnToMongodb(vo.getBarcode());
				}
			} else {
				ps.setRespstate(false);
				ps.setRespmsg("没有切片，不能提交给专家！");
			}
		}
		
		
		return ps;
	}

	// 撤销会诊
	public PoState execPtyClstExpertCommit(ExceClstExpertCommitVo vo)
			throws Exception {
		PoState ps = new PoState();
		CsltGroupprogramModel cgModel = csltProgramMapper
				.getCsltGroupprogramByBarcode(vo.getBarcode());
		// 在会诊状态下才处理
		if (cgModel != null && cgModel.getState() == 26) {
			cgModel.setState(vo.getVstate());
			if (vo.getBz() == null || vo.getBz().equals("")) {
				vo.setBz(cgModel.getState_msg() + "[" + cgModel.getExpertname()
						+ "]");
			}
			vo.setVuserid(vo.getOp_userid());
			vo.setVusername(vo.getOp_name());
			vo.setVnum(1);
			vo.setVpid(cgModel.getPid());
			vo.setVstatemean(cgModel.getState_msg());
			vo.setVexperid(Integer.parseInt(cgModel.getExpertid()));
			vo.setExpertname(cgModel.getExpertname());

			csltProgramMapper.execPtyClstExpertCommit(vo);
			ps.setRespstate(vo.getSucflag2() == 0 ? false : true);
			ps.setRespmsg(vo.getErrinf2());
			if (ps.getRespstate()) {
				MongoDBUtil.delete(
						"cslt",
						MongoDBUtil.getDocumentByJSON("{pt_barcode:\""
								+ vo.getBarcode() + "\"}"));
			}
		} else {
			ps.setRespstate(false);
			ps.setRespmsg("参数错误！");
		}
		return ps;
	}

	// 诊断专家列表
	public List<CsltExpertPo> getExpertList(BaseCsltVo vo) throws Exception {
		return csltProgramMapper.getExpertModel(vo);
	}

	// 修改流程信息
	public PoState updateDiagnosis(CsltDiagnosisVo vo) throws Exception {
		PoState po = new PoState();

		CsltGroupprogramModel model = csltProgramMapper
				.getCsltGroupprogramByBarcode(vo.getBarcode());
		if (model == null) {
			po.setRespstate(false);
			po.setRespmsg("参数错误");
		} else {
			/*
			 * if(
			 * StringUtils.isEmpty(vo.getCsltGroupprogramModel().getPt_barcode
			 * ())){
			 * vo.getCsltGroupprogramModel().setPt_barcode(vo.getBarcode()); }
			 */
			Integer old_state = model.getState();
			Integer new_state = vo.getCsltGroupprogramModel().getState();
			switch (new_state) {
			case 20:
				vo.setIsupdate(false);
				break;
			case 21:
				if (old_state == 25 || old_state == 28 || old_state == 27 || old_state == 29) {
					vo.setIsupdate(true);
					//CsltGroupprogramModel csltGroupprogramModel=vo.getCsltGroupprogramModel();
					//csltGroupprogramModel.setCommitid(vo.getOp_userid());
					//csltGroupprogramModel.setCommitname(vo.getOp_name());
					//csltGroupprogramModel.setCommittel(vo.getOp_tel());
					//csltGroupprogramModel.setCommitdate(new Date());	
				} else {
					vo.setIsupdate(false);
				}
				break;
			case 25:
				if (old_state == 20 || old_state == 21) {
					vo.setIsupdate(true);
					CsltGroupprogramModel csltGroupprogramModel=vo.getCsltGroupprogramModel();
					csltGroupprogramModel.setLoginid(vo.getOp_userid());
					csltGroupprogramModel.setLoginname(vo.getOp_name());
					csltGroupprogramModel.setLogintel(vo.getOp_tel());
					csltGroupprogramModel.setLogindate(new Date());					
				} 
				else {
					vo.setIsupdate(false);
				}
				break;
			case 26:
				if (old_state == 25 || old_state == 28 || old_state == 27 || old_state == 29) {
					int picnum = csltProgramMapper.getCsltSliceCount(vo
							.getBarcode());
					if (picnum > 0) {
						vo.setIsupdate(true);
						CsltGroupprogramModel csltGroupprogramModel=vo.getCsltGroupprogramModel();
						csltGroupprogramModel.setCommitid(vo.getOp_userid());
						csltGroupprogramModel.setCommitname(vo.getOp_name());
						csltGroupprogramModel.setCommittel(vo.getOp_tel());
						csltGroupprogramModel.setCommitdate(new Date());	
					} else {
						vo.setIsupdate(false);
						po.setRespmsg("没有切片，不能提交给专家！");
					}
				} else {
					vo.setIsupdate(false);
				}
				break;
			case 27:
				if (old_state == 26) {
					vo.setIsupdate(true);
				} else {
					vo.setIsupdate(false);
				}
				break;
			case 28:
				if (old_state == 26) {
					vo.setIsupdate(true);
				} else {
					vo.setIsupdate(false);
				}
				break;
			case 29:
				if (old_state == 26 || old_state == 35) {
					vo.setIsupdate(true);
				} else {
					vo.setIsupdate(false);
				}
				break;
			default:
				vo.setIsupdate(false);
				break;
			}

			if (vo.getIsupdate()) {
				csltProgramMapper.updateDiagnosis(vo);

				CsltLogInfoCustom csltLogInfoCustom = new CsltLogInfoCustom();
				// csltLogInfoCustom.setLogid(765210);//主键
				csltLogInfoCustom.setCsltid(0);// 诊断结果ID
				csltLogInfoCustom.setLogpid(0);// 流程ID
				// 操作内容，
				// 1.退回的记录退回原因，
				// 2.有结果的记录结果
				// 3.不是退回和提交结果的，有备注的记录备注
				csltLogInfoCustom.setOp_bz(vo.getRemark());// 日志的类型
				csltLogInfoCustom.setOp_state(new_state.toString());// 状态
				csltLogInfoCustom.setOp_state_mean(vo.getState_msg());// 状态说明
				csltLogInfoCustom.setOp_userid(vo.getOp_userid());// 用户ID [登陆用户
																	// ID]
				csltLogInfoCustom.setOp_username(vo.getOp_name());// 用户名 [登陆用户
																	// 名]
				csltLogInfoCustom.setBarcode(vo.getBarcode());
				csltProgramMapper.ptyCsltLog(csltLogInfoCustom);
				po.setRespstate(true);
			} else {
				po.setRespstate(false);
				if (po.getRespmsg() == null || po.getRespmsg().equals("")) {
					po.setRespmsg("不允许的操作！");
				}
			}
		}
		return po;
	}

	public void deleteDiagnosis(BaseCsltVo vo) throws Exception {
		// String barcode, String op_userid, String op_name,String op_tel
		csltProgramMapper.deleteDiagnosis(vo.getBarcode());

		CsltLogInfoCustom csltLogInfoCustom = new CsltLogInfoCustom();
		// csltLogInfoCustom.setLogid(765210);//主键
		csltLogInfoCustom.setCsltid(0);// 诊断结果ID
		csltLogInfoCustom.setLogpid(0);// 流程ID
		// 操作内容，
		// 1.退回的记录退回原因，
		// 2.有结果的记录结果
		// 3.不是退回和提交结果的，有备注的记录备注
		csltLogInfoCustom.setOp_bz("");// 日志的类型
		csltLogInfoCustom.setOp_state("0");// 状态
		csltLogInfoCustom.setOp_state_mean("删除会诊");// 状态说明
		csltLogInfoCustom.setOp_userid(vo.getOp_userid());// 用户ID [登陆用户 ID]
		csltLogInfoCustom.setOp_username(vo.getOp_name());// 用户名 [登陆用户 名]
		csltLogInfoCustom.setBarcode(vo.getBarcode());
		csltProgramMapper.ptyCsltLog(csltLogInfoCustom);
	}

	public PoState sendDiagnosis(BaseCsltVo vo) throws Exception {
		// String barcode, String op_userid, String op_name,String op_tel
		PoState ps = new PoState();
		CsltGroupprogramModel cgModel = csltProgramMapper
				.getCsltGroupprogramByBarcode(vo.getBarcode());
		if (cgModel != null) {
			ps.setRespstate(false);
			ps.setRespmsg("信息已经存在！");
		} else {
			CsltPatientinformation cpInfo = csltProgramMapper.getCsltpatinfo(vo
					.getBarcode());
			if (cpInfo != null) {
				// 保存流程
				CsltGroupprogramVo cgVo = new CsltGroupprogramVo();
				cgVo.setPt_barcode(vo.getBarcode());
				cgVo.setState(20);
				cgVo.setLoginname(vo.getOp_name());
				cgVo.setLoginid(vo.getOp_userid());
				cgVo.setLogintel(vo.getOp_tel());
				cgVo.setDept(vo.getDept());
				cgVo.setPicnum(csltProgramMapper.getCsltSliceCount(vo
						.getBarcode()));
				csltProgramMapper.insertCsltGroupprogram(cgVo);

				CsltLogInfoCustom csltLogInfoCustom = new CsltLogInfoCustom();
				// csltLogInfoCustom.setLogid(765210);//主键
				csltLogInfoCustom.setCsltid(0);// 诊断结果ID
				csltLogInfoCustom.setLogpid(0);// 流程ID
				// 操作内容，
				// 1.退回的记录退回原因，
				// 2.有结果的记录结果
				// 3.不是退回和提交结果的，有备注的记录备注
				csltLogInfoCustom.setOp_bz("");// 日志的类型
				csltLogInfoCustom.setOp_state(cgVo.getState().toString());// 状态
				csltLogInfoCustom.setOp_state_mean("会诊申请");// 状态说明
				csltLogInfoCustom.setOp_userid(vo.getOp_userid());// 用户ID [登陆用户
																	// ID]
				csltLogInfoCustom.setOp_username(vo.getOp_name());// 用户名 [登陆用户
																	// 名]
				csltLogInfoCustom.setBarcode(vo.getBarcode());
				csltProgramMapper.ptyCsltLog(csltLogInfoCustom);
				ps.setRespstate(true);
			} else {
				ps.setRespstate(false);
				ps.setRespmsg("条码不存在！");
			}
		}
		return ps;
	}

	public List<CsltDiagnosisListPo> getCsltDiagnosisList(GetDataListVo vo)
			throws Exception {
		try {
			List<CsltDiagnosisListPo> list = csltProgramMapper
					.getCsltDiagnosisList(vo);

			return list;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public List<CsltSliceInfoCustom> getCsltSliceList(BaseCsltVo vo)
			throws Exception {
		return csltProgramMapper.getCsltSliceList(vo);
	}
	
	public List<CsltSliceInfoCustom> getCsltSliceList(String barcode, Boolean isMysql)
			throws Exception {

		List<CsltSliceInfoCustom> sliceList = new ArrayList<CsltSliceInfoCustom>();
		
	    List<SliceFileModel> slices = sliceMapper.getSliceListByBarcode(barcode);
	    for (int i =0; i < slices.size(); i++){
	    	CsltSliceInfoCustom sliceCustom = new CsltSliceInfoCustom();
	    	sliceList.add(sliceCustom);
	    	SliceFileModel sliceFileModel = slices.get(i);
	    	sliceCustom.setDiancode(barcode);
	    	sliceCustom.setDsid(sliceFileModel.getId());
	    	sliceCustom.setDsno(sliceFileModel.getSlicenumber());
	    	sliceCustom.setSlicedesc(sliceFileModel.getDiagnosecomment());
	    	sliceCustom.setShortpicurl(sliceFileModel.getSlicepath());
	    	sliceCustom.setMaxzoom(sliceFileModel.getMaxzoom());
	    	sliceCustom.setHeight(sliceFileModel.getHeight());
	    	sliceCustom.setWidth(sliceFileModel.getWidth());	    	
	    }
	    
	    return sliceList;
	}

	public byte[] getCsltSliceSmallPic(int dsid) throws Exception {
		CsltSliceInfoCustom po = csltProgramMapper.getCsltSliceSmallPic(dsid);
		return po.getShortpic();
	}

	//
	public String setCslt(String json, String op_userid, String op_name)
			throws Exception {
		JSONObject js = JSONObject.fromObject(json);

		String barcode = js.getString("pt_barcode");
		int pid = js.getInt("pid");
		String expertid = js.getString("expertid");
		String expertname = js.getString("expertname");
		Boolean isJHH = (expertid.equals("9999"));
		String state = js.getString("state");
		System.out.println(js.getString("backreason"));
		Boolean isCommit = state.equals("16");
		Boolean isBack = state.equals("17");
		CsltModel csltModel = new CsltModel();

		if (isCommit) {
			// 诊断结果
			String expertcslt = js.getString("expertcslt");
			String folderno = js.getJSONObject("patient").getString("folderno");

			csltModel.setPid(pid);
			csltModel.setBarcode(barcode);
			csltModel.setCstate("0");
			if (isJHH)
				csltModel.setExpertcslt_en(expertcslt);
			else
				csltModel.setExpertcslt(expertcslt);
			csltModel.setExpertid(expertid);
			csltModel.setExpertname(expertname);
			csltModel.setFolderno(folderno);
			csltProgramMapper.insertCslt(csltModel);
			csltModel = csltProgramMapper.getCsltModel(csltModel);

			// 获取截图信息
			JSONArray screenshots = js.getJSONArray("screenshot");
			for (int i = 0; i < screenshots.size(); i++) {
				JSONObject s = screenshots.getJSONObject(i);
				CsltPicModel csltPicModel = new CsltPicModel();
				csltPicModel.setLocaldir(s.getString("imgpath"));
				csltPicModel.setDisplay(s.getBoolean("issave") ? 1 : -1);
				csltPicModel.setOrderno("0");
				csltPicModel.setFolderno(folderno);
				csltPicModel.setCcid(csltModel.getCcid());
				csltPicModel.setId(java.util.UUID.randomUUID().toString());
				csltPicModel.setType("2");
				if (isJHH) {
					csltPicModel.setDescribe_en(s.getString("remark"));
				} else {
					csltPicModel.setDescribe(s.getString("remark"));
				}
				csltProgramMapper.insertCsltPic(csltPicModel);
			}

			// 调存储过程更新其他信息
			ExceClstExpertCommitVo cvo = new ExceClstExpertCommitVo();
			cvo.setBarcode(barcode);
			cvo.setFolderno(folderno);
			cvo.setVexperid(Integer.parseInt(expertid));
			cvo.setExpertname(expertname);
			cvo.setVuserid(expertid);
			cvo.setVusername(expertname);
			cvo.setVnum(1);
			cvo.setBz(expertname + ":" + expertcslt);
			cvo.setExpertcslt(expertcslt);
			cvo.setVstate(35);
			cvo.setVstatemean("阅片完成");
			cvo.setVpid(pid);
			csltProgramMapper.execPtyClstExpertCommit(cvo);
			if (cvo.getSucflag2() == 0)
				throw new Exception("更新专家提交失败" + barcode);

		} else if (isBack) {
			ExceClstExpertCommitVo cvo = new ExceClstExpertCommitVo();
			cvo.setBarcode(barcode);
			cvo.setVexperid(Integer.parseInt(expertid));
			cvo.setExpertname(expertname);
			cvo.setVuserid(expertid);
			cvo.setVusername(expertname);
			cvo.setVnum(1);
			cvo.setBz(expertname + ":" + js.getString("backreason"));
			cvo.setExpertcslt("");
			cvo.setVstate(27);
			cvo.setVstatemean("专家退回");
			cvo.setVpid(pid);
			csltProgramMapper.execPtyClstExpertCommit(cvo);
			if (cvo.getSucflag2() == 0)
				throw new Exception("更新专家退回失败" + barcode);
		} else {

		}

		// PoState state = new PoState();
		// Map<String, Class> classMap = new HashMap<String, Class>();
		// // classMap.put("attcpicturelist", csltAttcPictureObj.class);
		// //classMap.put("screenshot", csltAttcPictureObj.class);
		// classMap.put("slicelist", csltSliceObj.class);
		//
		// csltObject obj = (csltObject)
		// ObjectSerializeUtil.getObjectFromJsonString(json, csltObject.class,
		// classMap);
		// op_userid = obj.getExpertid();
		// op_name = obj.getExpertname();
		// Boolean isJHH = (obj.getExpertid().equals("9999"));
		//
		// if (!isJHH) {
		// Integer pid = obj.getPid();
		// String barcode = obj.getPt_barcode();
		// System.out.println(barcode);
		// //添加结果表
		// CsltModel csltModel = new CsltModel();
		// csltModel.setPid(pid);
		// csltModel.setBarcode(barcode);
		//
		// }
		// else
		// {
		// CsltInfotPo cslt_info_po = getCsltAuditViewByBarcode(obj
		// .getPt_barcode());
		// if (cslt_info_po == null || cslt_info_po.getEbid() == 0) {
		// // state.setRespstate(false);
		// return "0";
		// }
		// CsltInfoVo cslt_info = new CsltInfoVo();
		// cslt_info.setEbid(cslt_info_po.getEbid());
		// cslt_info.setState(obj.getState());// 状态
		// cslt_info.setBarcode(obj.getPt_barcode());// 条码
		// cslt_info.setLetter(obj.getLetter());// 附言（英文）
		// cslt_info.setCliresult_en(obj.getExpertcslt());// 诊断结果
		// // obj.setExpertcslt("未见病变456111");
		// // 是否退回
		// if (obj.getState().equals("0") || obj.getState().equals("17"))
		// cslt_info.setRemark(obj.getBackreason());// 退回原因（备注）
		// else
		// cslt_info.setRemark(obj.getRslt_comments());// 退回原因（备注）
		// state = setCsltInfo(cslt_info, op_userid, op_name);// 保存信息及状态（默认添加日志）
		// if (state.getRespstate()) {
		// // 添加结果信息
		// CsltModel cslt_model = new CsltModel();
		// cslt_model.setPid(obj.getPid());
		// cslt_model.setExpertcslt(obj.getExpertname());
		// cslt_model.setExpertid(obj.getExpertid());
		// cslt_model.setCommitdate(obj.getExpertfinishdate());
		// cslt_model.setCstate("1");
		// cslt_model.setRslt_comments(obj.getLoginname());
		// cslt_model.setExpertcslt_en(obj.getExpertcslt());
		// cslt_model.setFolderno(obj.getFolderno());
		// cslt_model.setBarcode(obj.getPt_barcode());
		// // 保存
		// insertCslt(cslt_model);
		//
		// // 图片属性添加 （select * from t_pty_csltpic）
		// if (obj.getAttcpicturelist() != null) {
		// insertCsltPics(obj);
		// }
		// }
		// }

		return "1";
	}

	// 保存发布数据
	public CsltModel insertCslt(CsltModel object) throws Exception {
		CsltModel model = new CsltModel();
		csltProgramMapper.insertCslt(object);
		model = csltProgramMapper.getCsltModel(object);
		return model;
	}

	public List<CsltPicPo> getCsltPics(CsltPicVo object) throws Exception {
		return csltProgramMapper.getCsltPics(object);
	}

	public void insertCsltPics(csltObject object) throws Exception {
		// Queue<String> queue = new LinkedList<String>();
		Map<String, Object> map = new HashMap<String, Object>();
		CsltModel model = new CsltModel();
		model.setBarcode(object.getPt_barcode());
		model.setCstate("1");//
		model = csltProgramMapper.getCsltModel(model);
		if (model != null && model.getPid() > 0)
			// 图片属性添加 （select * from t_pty_csltpic）
			if (object.getAttcpicturelist() != null) {
				List<csltAttcPictureObj> list = object.getAttcpicturelist();
				for (int i = 0; i < list.size(); i++) {
					// String tmp=list.get(i).getDescribe();
					csltAttcPictureObj pic = list.get(i);
					CsltPicModel insert_model = new CsltPicModel();
					String uuid = java.util.UUID.randomUUID().toString();
					insert_model.setCcid(model.getCcid());
					insert_model.setFolderno(object.getFolderno());
					insert_model.setOrderno("0");
					insert_model.setType("2");
					insert_model.setSortorder(0);
					insert_model.setDisplay(-1);// 图片状态（-1代表未保存图片二进制数据）
					insert_model.setLocaldir(pic.getReportpic());// 文件名
					insert_model.setDescribe_en(pic.getDescribe());// 英文说明d
					insert_model.setId(uuid);// ID
					csltProgramMapper.insertCsltPic(insert_model);// 保存图片基本信息
					map.put(uuid, pic.getReportpic());
				}
				// for (csltAttcPictureObj item : object.getAttcpicturelist()) {
				// CsltPicModel insert_model = new CsltPicModel();
				// String uuid = java.util.UUID.randomUUID().toString();
				// insert_model.setCcid(model.getCcid());
				// insert_model.setFolderno(object.getFolderno());
				// insert_model.setOrderno("0");
				// insert_model.setType("2");
				// insert_model.setSortorder(0);
				// insert_model.setDisplay(-1);// 图片状态（-1代表未保存图片二进制数据）
				// insert_model.setLocaldir(item.getReportpic());// 文件名
				// insert_model.setDescribe_en(item.getDescribe());// 英文说明d
				// insert_model.setId(uuid);// ID
				// csltProgramMapper.insertCsltPic(insert_model);// 保存图片基本信息
				// map.put(uuid, item.getReportpic());
				// }
			}

		if (!map.isEmpty()) {
			ThreadGetAndSaveImages my = new ThreadGetAndSaveImages(map);
			new Thread(my, "threadGetImages").start(); //
			// for (Map.Entry<String, Object> entry :map.entrySet()) { //
			// 逐个获取图片二进制数据，并保存数据【】
			// System.out.println(entry.getKey() + "--->" + //
			// entry.getValue()); //
			// }
		}

	}

	public void insertCsltPic(CsltPicModel object) throws Exception {
		csltProgramMapper.insertCsltPic(object);
	}

	public void updateCsltPics(List<CsltPicVo> list) throws Exception {
		for (CsltPicModel item : list) {
			csltProgramMapper.updateCsltPic(item);
		}
	}

	public void updateCsltPic(CsltPicModel object) throws Exception {
		csltProgramMapper.updateCsltPic(object);
	}

	public List<CsltLog> getCsltLog(String barcode) throws Exception {
		List<CsltLog> po = csltProgramMapper.getCsltLog(barcode);
		return po;
	}

	public List<TranslateTotalPo> getTranslateTotal(GetDataListVo vo)
			throws Exception {
		List<TranslateTotalPo> po = csltProgramMapper.getTranslateTotal(vo);
		return po;
	}

	public List<TranslatePo> getTranslateList(GetDataListVo vo)
			throws Exception {
		List<TranslatePo> po = csltProgramMapper.getTranslateList(vo);
		return po;
	}

	public CsltInfotPo getCsltAuditCnView(String BARCODE) throws Exception {
		CsltInfotPo po = csltProgramMapper.getCsltAuditCnView(BARCODE);
		return po;
	}

	public CsltInfotPo getCsltAuditView(Integer EBID) throws Exception {
		CsltInfotPo po = csltProgramMapper.getCsltAuditView(EBID);
		return po;
	}

	public List<CsltInfotPo> getCsltAuditList(CsltInfoVo object)
			throws Exception {
		List<CsltInfotPo> csltProgramCustomList = csltProgramMapper
				.getCsltAuditList(object);
		return csltProgramCustomList;
	}

	public CsltInfotPo getCsltAuditViewByBarcode(String barcode)
			throws Exception {
		return csltProgramMapper.getCsltAuditViewByBarcode(barcode);
	}

	public PoState setCsltInfo(CsltInfoVo object, String op_userid,
			String op_name) throws Exception {
		int newState = 0;
		int oldState = 0;
		PoState postate = new PoState();
		postate.respstate = true;
		/* try { */
		CsltInfotPo po = csltProgramMapper.getCsltAuditView(object.getEbid());
		// CsltInfotPo po =
		// csltProgramMapper.getCsltAuditViewByBarcode(object.barcode);
		if (po != null && po.getEbid() > 0) {
			newState = Integer.parseInt(object.getState());
			oldState = Integer.parseInt(po.getState());
			switch (newState) {
			case 10:
			case 0:
				object.setState(po.getState());
				break;
			case 11:
				if (oldState != 10 && oldState != 11 && oldState != 12
						&& oldState != 17) {
					postate.respstate = false;
				}
				break;
			case 12:
				if (oldState != 11 && oldState != 17 && oldState != 12) {
					postate.respstate = false;
				}
				break;
			case 13:
				if (oldState != 12 && oldState != 13) {
					postate.respstate = false;
				}
				break;
			case 15:
				if (oldState != 13 && oldState != 15 && oldState != 17) {
					postate.respstate = false;
				} else {
					if (csltProgramMapper
							.getCsltSliceCount(object.getBarcode()) == 0) {
						postate.respstate = false;
						throw new Exception("没有切片，不能提交给专家！");
					}
				}
				break;
			case 16:
				if (oldState != 15 && oldState != 16) {
					postate.respstate = false;
				}
				break;
			case 17:
				if (oldState != 15 && oldState != 17) {
					postate.respstate = false;
				}
				break;
			case 18:
				if (oldState != 16 && oldState != 18) {
					postate.respstate = false;
				}
				break;
			case 19:
				if (oldState != 18 && oldState != 19) {
					postate.respstate = false;
				}
				break;
			case 21:
				if (oldState != 19 && oldState != 21) {
					postate.respstate = false;
				}
				break;
			}
			if (postate.respstate) {
				csltProgramMapper.updateCsltInfo(object);
				if (object.getState().equals("15")) {
					saveCsltToMongodb(object.getBarcode());
					// postate.setVo(t_getObj(object.getBarcode()));
				}
			} else {
				postate.respmsg = "error state!";
			}
		} else {
			object.setState(object.getState().equals("10")
					|| object.getState().equals("0") ? "11" : object.getState());
			newState = Integer.parseInt(object.getState());
			csltProgramMapper.insertCsltInfo(object);
			postate.vo = csltProgramMapper.getCsltAuditViewByBarcode(object
					.getBarcode());

			// 保存流程
			CsltGroupprogramVo cgVo = new CsltGroupprogramVo();
			cgVo.setPt_barcode(object.getBarcode());
			cgVo.setState(1);
			cgVo.setJhh("1");
			cgVo.setLoginname(op_name);
			cgVo.setLoginid(op_userid);
			cgVo.setPicnum(csltProgramMapper.getCsltSliceCount(object
					.getBarcode()));
			csltProgramMapper.insertCsltGroupprogram(cgVo);
		}
		/*
		 * } catch (Exception e) { postate.respstate = false;
		 * postate.respmsg+=e.getMessage(); postate.vo=object; }
		 */
		if (postate.respstate && newState != oldState) {
			CsltLogInfoCustom csltLogInfoCustom = new CsltLogInfoCustom();
			// csltLogInfoCustom.setLogid(765210);//主键
			csltLogInfoCustom.setCsltid(0);// 诊断结果ID
			csltLogInfoCustom.setLogpid(0);// 流程ID
			// 操作内容，
			// 1.退回的记录退回原因，
			// 2.有结果的记录结果
			// 3.不是退回和提交结果的，有备注的记录备注
			csltLogInfoCustom.setOp_bz(object.getRemark());// 日志的类型
			csltLogInfoCustom.setOp_state(object.getState());// 状态
			csltLogInfoCustom.setOp_state_mean(object.getState_msg());// 状态说明
			csltLogInfoCustom.setOp_userid(op_userid);// 用户ID [登陆用户 ID]
			csltLogInfoCustom.setOp_username(op_name);// 用户名 [登陆用户 名]
			csltLogInfoCustom.setBarcode(object.getBarcode());
			csltProgramMapper.ptyCsltLog(csltLogInfoCustom);
		}
		return postate;
	}

	// 保存流程
	public void insertCsltGroupprogram(CsltGroupprogramVo cgVo)
			throws Exception {
		csltProgramMapper.insertCsltGroupprogram(cgVo);
	}

	// 英文信息保存
	public void updateCsltInfo(CsltInfoVo object) throws Exception {
		csltProgramMapper.updateCsltInfo(object);
	}

	// 英文信息添加
	public void insertCsltInfo(CsltInfoVo object) throws Exception {
		csltProgramMapper.insertCsltInfo(object);
	}

	// 基础信息保存
	public PoState setCsltBaseInfo(CsltInfoVo object, String op_userid,
			String op_name) throws Exception {
		PoState postate = new PoState();
		postate.respstate = true;
		// try {
		CsltInfotPo po = csltProgramMapper.getCsltAuditCnView(object
				.getBarcode());
		if (po != null && po.getBid() != null && po.getBid() > 0) {
			object.setBid(po.getBid());
			updateCsltBaseInfo(object);
		} else {
			insertCsltBaseInfo(object);
		}
		// } catch (Exception e) {
		// postate.respstate = false;
		// postate.respmsg += e.getMessage();
		// postate.vo = object;
		// }
		return postate;
	}

	public void updateCsltBaseInfo(CsltInfoVo object) throws Exception {
		csltProgramMapper.updateCsltBaseInfo(object);
	}

	public void insertCsltBaseInfo(CsltInfoVo object) throws Exception {
		csltProgramMapper.insertCsltBaseInfo(object);
	}

	public void ptyCsltLog(CsltLogInfoCustom csltLogInfoCustom)
			throws Exception {
		csltProgramMapper.ptyCsltLog(csltLogInfoCustom);
	}

	// ===========================================================================
	public List<CsltProgramCustom> getCsltProgramListByExperID(String expertid)
			throws Exception {
		CsltProgramVo csltProgramVo = getCsltProgramVo();
		csltProgramVo.getCsltProgramCustom().setExpertid(expertid);
		List<CsltProgramCustom> csltProgramCustomList = csltProgramMapper
				.getCsltProgramList(csltProgramVo);
		return csltProgramCustomList;
	}

	public CsltBaseInfo getCsltBaseInfoByPID(Integer pid) throws Exception {
		if (pid == null)
			return null;
		else {
			CsltProgramVo csltProgramVo = getCsltProgramVo();
			csltProgramVo.getCsltProgramCustom().setPid(pid);
			CsltBaseInfo csltBaseInfo = csltProgramMapper
					.getCsltBaseInfo(csltProgramVo);
			return csltBaseInfo;
		}
	}

	public List<CsltSliceInfoCustom> getCsltSliceInfoByBarcode(String barcode)
			throws Exception {
		if (barcode == null)
			return null;
		else {
			CsltProgramVo csltProgramVo = getCsltProgramVo();
			csltProgramVo.getCsltProgramCustom().setPt_barcode(barcode);
			List<CsltSliceInfoCustom> csltSliceInfoList = csltProgramMapper
					.getCsltSliceInfo(csltProgramVo);
			return csltSliceInfoList;
		}
	}

	public CsltSliceInfoCustom getSliceShortPicByDSID(Integer dsid)
			throws Exception {
		if (dsid == null)
			return null;
		else {
			CsltSliceInfoCustom csltSliceInfoCustom = csltProgramMapper
					.getSliceShortPicByDSID(dsid);
			return csltSliceInfoCustom;
		}

	}

	public List<CsltLogInfoCustom> getCsltLogInfoCustomByPID(Integer pid)
			throws Exception {
		if (pid == null)
			return null;
		else {
			List<CsltLogInfoCustom> list = csltProgramMapper
					.getCsltLogInfoCustomByPID(pid);
			return list;
		}
	}

	public void updateResult(CsltResultVo csltResultVo) throws Exception {
		CsltResultVo vo = new CsltResultVo();
		CsltResultCustom rslt = new CsltResultCustom();
		vo.setCsltResultCustom(rslt);
		rslt.setPid(csltResultVo.getCsltResultCustom().getPid());
		rslt.setExpertid(csltResultVo.getCsltResultCustom().getExpertid());
		vo.setCstate_old("0");

		List<CsltResultCustom> list = csltProgramMapper.getCsltResultList(vo);

		if (list.isEmpty()) {
			csltProgramMapper.insertResult(csltResultVo);
		} else {
			rslt.setExpertcslt(csltResultVo.getCsltResultCustom()
					.getExpertcslt());
			rslt.setRslt_comments(csltResultVo.getCsltResultCustom()
					.getRslt_comments());
			csltProgramMapper.updateResult(vo);
		}
	}

	public void CommitResult(CsltResultVo csltResultVo) throws Exception {
		// 更新专家信息
		List<ExpertCustom> expertlist = csltProgramMapper.getExpertList(Integer
				.parseInt(csltResultVo.getCsltResultCustom().getExpertid()));
		ExpertCustom expert = expertlist.get(0);
		if (expert == null)
			throw new Exception("无法获取专家，提交失败！");
		expert.setViewnum(expert.getViewnum() - 1);
		Calendar now = Calendar.getInstance();
		if ((expert.getViewnumperday() == 0) || (expert.getViewnum() == 0))
			expert.setFinishdate(null);
		else
			now.add(Calendar.DAY_OF_YEAR, Math.round(expert.getViewnum()));
		ExpertVo expertVo = new ExpertVo();
		expertVo.setExpertCustom(expert);
		csltProgramMapper.updateExpert(expertVo);
		// 更新会诊流程
		// csltProgramMapper.updateCsltProgram()

	}

	public AttcPicture getCsltPicByID(String ID) throws Exception {
		return csltProgramMapper.getCsltPicByID(ID);
	}

	public CsltResultCustom GetUnCommitResult(Integer pid, Integer expertID)
			throws Exception {
		CsltResultVo csltResultVo = getCsltResultVo();
		CsltResultCustom csltResultCustom = csltResultVo.getCsltResultCustom();
		csltResultVo.setCstate_old("0");
		csltResultCustom.setPid(pid);
		csltResultCustom.setExpertid(expertID.toString());
		csltResultVo.setCsltResultCustom(csltResultCustom);
		List<CsltResultCustom> list = csltProgramMapper
				.getCsltResultList(csltResultVo);

		if (list.size() > 0) {
			csltResultCustom = list.get(0);
			Integer ccid = csltResultCustom.getCcid();
			csltResultCustom.setAttcPictureList(csltProgramMapper
					.getCsltPicInfoListByCCID(ccid));
			return csltResultCustom;
		} else
			return null;
	}

	public List<AttcPicture> getCsltPicInfoListByCCID(Integer ccid)
			throws Exception {
		return csltProgramMapper.getCsltPicInfoListByCCID(ccid);
	}

	public void getSliceListFromMDB(String dept) throws Exception {
		// 获取M上的新修改的切片列表
		ArrayList<String> list = MongoDBUtil.findJSON("slice", "sliceinfo",
				"{update: 0}");
		ArrayList<CsltSliceVo> sliceList = new ArrayList<CsltSliceVo>();
		Iterator<String> it = list.iterator();
		while (it.hasNext()) {
			String str = it.next();
			JSONObject js = JSONObject.fromObject(str);
			CsltSliceVo csltSliceVo = new CsltSliceVo();

			csltSliceVo.setBarcode(js.getString("barcode"));
			csltSliceVo.setSlicelist(new ArrayList<CsltSliceInfoCustom>());

			JSONArray slices = js.getJSONArray("slicelist");
			for (int i = 0; i < slices.size(); i++) {
				JSONObject slice = slices.getJSONObject(i);
				CsltSliceInfoCustom csltSliceInfoCustom = new CsltSliceInfoCustom();
				csltSliceInfoCustom.setDiancode(csltSliceVo.getBarcode());
				csltSliceInfoCustom.setDsno(slice.getString("sliceid"));
				csltSliceInfoCustom.setShortpicurl(slice
						.getString("slice_shot"));
				csltSliceInfoCustom.setSlicedesc(slice.getString("slicedesc"));
				csltSliceVo.getSlicelist().add(csltSliceInfoCustom);
			}
			sliceList.add(csltSliceVo);
		}

		for (int i = 0; i < sliceList.size(); i++) {
			CsltSliceVo csltSliceVo = (CsltSliceVo) sliceList.get(i);
			String barcode = csltSliceVo.getBarcode();
			ArrayList<CsltSliceInfoCustom> slices = csltSliceVo.getSlicelist();
			if (csltProgramMapper.getCsltGroupprogramByBarcode(barcode) == null) {
				CsltGroupprogramVo cgVo = new CsltGroupprogramVo();
				cgVo.setPt_barcode(barcode);
				cgVo.setState(20);
				cgVo.setDept(dept);
				csltProgramMapper.insertCsltGroupprogram(cgVo);
			}
			csltProgramMapper.deleteCsltSliceList(barcode);
			for (int j = 0; j < slices.size(); j++) {
				CsltSliceInfoCustom csltSliceInfoCustom = slices.get(j);
				csltSliceInfoCustom.setProvider("bs_li");
				csltProgramMapper
						.insertCsltSlice((CsltSliceInfoCustom) (csltSliceInfoCustom));
			}
			MongoDBUtil.update("sliceinfo", MongoDBUtil
					.getDocumentByJSON("{barcode:'" + barcode + "'}"),
					MongoDBUtil.getDocumentByJSON("{$set: {update: 1}}"),
					"slice");
		}
	}

	public String getSliceUploadState(String barcode) throws Exception {
		ArrayList<String> list = MongoDBUtil.findJSON("slice", "sliceinfo",
				"{barcode:'" + barcode + "'}");
		if (list.size() > 0) {
			return JSONObject.fromObject(list.get(0)).getString("slicelist");
			// return list.get(0);
		} else
			return "[]";
	}

	public int resultConfirm(String barcode, Integer pid, String reporttype)
			throws Exception {
		// 获取结果
		CsltModel csltModel = new CsltModel();
		csltModel.setBarcode(barcode);
		csltModel.setCstate("2");
		csltModel = csltProgramMapper.getCsltModel(csltModel);

		
		// 获取截图列表
		if (csltModel != null) {
			boolean isbg = reporttype.equals("bg");
			Integer ccid = csltModel.getCcid();
			String ordno = null;
			List<AttcPicture> attcPictures = csltProgramMapper
					.getCsltPicInfoListByCCID(ccid);

			// 清除原有报告结果合同截图
			if (isbg) {
				ordno = pathologyMapper.getOrdno(csltModel.getFolderno());
				pathologyMapper.deleteAttcPic(csltModel.getFolderno());
				pathologyMapper.deleteDgsinfo(csltModel.getFolderno());

				// 添加结果表 t_pathology_dgsinfo
				// TODO d.windowid,d.winidname qualitydoctor diagnosedoctor
				DgsinfoModel dgsinfoModel = new DgsinfoModel();
				dgsinfoModel.setFolderno(csltModel.getFolderno());
				dgsinfoModel.setOrdno(ordno);
				dgsinfoModel.setBarcode(barcode);
				dgsinfoModel.setId(UUID.randomUUID().toString());
				dgsinfoModel.setRepidea(csltModel.getExpertcslt());
				pathologyMapper.insertDgsinfo(dgsinfoModel);
			}

			// 获取截图 t_attc_picture t_pty_csltpic
			for (int i = 0; i < attcPictures.size(); i++) {
				AttcPicture attcPicture = attcPictures.get(i);
				String describe = attcPicture.getDescribe();
				String localDir = attcPicture.getLocaldir();
				String csltID = attcPicture.getId();
				Boolean display = (attcPicture.getDisplay() == 1);
				URL url = new URL(clipimgURL + localDir);
				DataInputStream dataInputStream = new DataInputStream(
						url.openStream());
				byte[] picture = HttpGetFileUtil
						.readInputStream(dataInputStream);

				// 更新t_pty_csltpic
				CsltPicModel csltPicModel = new CsltPicModel();
				csltPicModel.setId(csltID);
				csltPicModel.setPicture(picture);
				csltProgramMapper.updateCsltPic(csltPicModel);

				// 更新t_attc_picture
				if (isbg) {
					if (display) {
						AttcPictureModel attcPictureModel = new AttcPictureModel();
						attcPictureModel.setId(csltID);
						attcPictureModel.setFolderno(csltModel.getFolderno());
						attcPictureModel.setDescribe(describe);
						attcPictureModel.setPicture(picture);
						pathologyMapper.insertAttcPic(attcPictureModel);

					}
				}

				dataInputStream.close();

			}

			// 修改流程状态
			CsltDiagnosisVo csltDiagnosisVo = new CsltDiagnosisVo();
			CsltGroupprogramModel csltGroupprogramModel = new CsltGroupprogramModel();
			csltDiagnosisVo.setBarcode(barcode);
			csltGroupprogramModel.setState(36);
			csltDiagnosisVo.setCsltGroupprogramModel(csltGroupprogramModel);
			csltProgramMapper.updateDiagnosis(csltDiagnosisVo);

			// 修改结果状态
			CsltResultVo csltResultVo = new CsltResultVo();
			CsltResultCustom csltResultCustom = new CsltResultCustom();
			csltResultCustom.setCstate("1");
			csltResultCustom.setBarcode(barcode);
			csltResultVo.setCsltResultCustom(csltResultCustom);
			csltProgramMapper.updateResult(csltResultVo);

			// 记录日志
			CsltLogInfoCustom csltLogInfoCustom = new CsltLogInfoCustom();
			csltLogInfoCustom.setBarcode(barcode);
			csltLogInfoCustom.setOp_state("36");
			csltLogInfoCustom.setOp_state_mean("报告提交");
			csltLogInfoCustom.setLogpid(csltModel.getPid());
			csltLogInfoCustom.setOp_userid("op_userid");
			csltLogInfoCustom.setOp_username("op_username");
			csltLogInfoCustom.setOp_bz("报告提交");
			csltProgramMapper.ptyCsltLog(csltLogInfoCustom);
		}
		return 1;
	}

	public CsltModel getUnConfirmCsltResult(String barcode) throws Exception {
		CsltModel csltModel = new CsltModel();
		csltModel.setBarcode(barcode);
		csltModel.setCstate("2");
		return csltProgramMapper.getCsltModel(csltModel);
	}

	public List<SliceFileModel> getUploadSliceList() throws Exception {
		return sliceMapper.getUploadSliceList();
	}

	//通过MySQL中的切片和条码关联数据建立流程
	public void createProgramFromMySQL() throws Exception {
		List<SliceFileModel> slices = sliceMapper.getUploadSliceList();
		
		for(int i=0; i < slices.size(); i++){
			SliceFileModel slice = slices.get(i);
			String barcode = slice.getBarcode();
			
			PatientinfoCustom patientinfoCustom=patientInfoMapper.getPatientinfoCustomByBarcode(barcode);
			if (patientinfoCustom!=null)
			{
				if (csltProgramMapper.getCsltGroupprogramByBarcode(barcode) == null) {
					CsltGroupprogramVo cgVo = new CsltGroupprogramVo();
					cgVo.setPt_barcode(barcode);
					cgVo.setDept(patientinfoCustom.getDept());
					cgVo.setState(20);
					csltProgramMapper.insertCsltGroupprogram(cgVo);
				}
				
			}			
		}				
	}

	public List<SliceFileModel> getSliceListByBarcodeFromMySQL(String barcode)
			throws Exception {
		return sliceMapper.getSliceListByBarcode(barcode);
	}

	public List<MicrobeModel> getMicrobeResultByBarcode(String barcode)
			throws Exception {
		// TODO Auto-generated method stub
		return csltProgramMapper.getMicrobeResultByBarcode(barcode);
	}

	public void deleteMicrobeResult(MicrobeModel microbeModel) throws Exception {
		csltProgramMapper.deleteMicrobeResult(microbeModel);
		
	}
}
