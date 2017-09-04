package net.dagene.pmis.pathology.service;

import java.util.List;

import net.dagene.pmis.common.po.PoState;
import net.dagene.pmis.common.po.csltObject;
import net.dagene.pmis.pathology.model.CsltModel;
import net.dagene.pmis.pathology.model.CsltPicModel;
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
import net.dagene.pmis.pathology.po.TranslatePo;
import net.dagene.pmis.pathology.po.TranslateTotalPo;
import net.dagene.pmis.pathology.vo.BaseCsltVo;
import net.dagene.pmis.pathology.vo.CsltDiagnosisVo;
import net.dagene.pmis.pathology.vo.CsltExpertVo;
import net.dagene.pmis.pathology.vo.CsltInfoVo;
import net.dagene.pmis.pathology.vo.CsltPicVo;
import net.dagene.pmis.pathology.vo.CsltResultVo;
import net.dagene.pmis.pathology.vo.ExceClstExpertCommitVo;
import net.dagene.pmis.pathology.vo.ExecClstCommitExpertVo;
import net.dagene.pmis.pathology.vo.GetDataListVo;

public interface CsltService {
	
	public PoState deleteCsltExpert(Integer id) throws Exception;
	public PoState insertCsltExpert(CsltExpertVo vo) throws Exception;
	public PoState updateCsltExpert(CsltExpertVo vo) throws Exception;	

	public PoState execPtyClstExpertCommit(ExceClstExpertCommitVo vo) throws Exception;
	public PoState execPtyClstCommitExpert(ExecClstCommitExpertVo vo) throws Exception;

	public List<CsltExpertPo> getExpertList(BaseCsltVo vo) throws Exception;

	public PoState updateDiagnosis(CsltDiagnosisVo vo) throws Exception;
	public void deleteDiagnosis(BaseCsltVo vo) throws Exception;
	public PoState sendDiagnosis(BaseCsltVo vo) throws Exception;

	public List<CsltDiagnosisListPo> getCsltDiagnosisList(GetDataListVo vo) throws Exception;
	public List<CsltSliceInfoCustom> getCsltSliceList(BaseCsltVo vo) throws Exception;
	public List<CsltSliceInfoCustom> getCsltSliceList(String barcode, Boolean isMysql) throws Exception;
	public byte[] getCsltSliceSmallPic(int dsid) throws Exception;
	
	public String setCslt(String json, String op_userid, String op_name) throws Exception;

	public CsltModel insertCslt(CsltModel object) throws Exception;

	// 图片查添加修改
	public List<CsltPicPo> getCsltPics(CsltPicVo object) throws Exception;
	public void updateCsltPics(List<CsltPicVo> list) throws Exception;
	public void insertCsltPics(csltObject object) throws Exception;
	public void insertCsltPic(CsltPicModel object) throws Exception;
	public void updateCsltPic(CsltPicModel object) throws Exception;

	// 获取日志列表
	public List<CsltLog> getCsltLog(String barcode) throws Exception;

	// 获取需要翻译信息统计
	public List<TranslateTotalPo> getTranslateTotal(GetDataListVo vo)
			throws Exception;

	// 获取需要翻译信息的列表
	public List<TranslatePo> getTranslateList(GetDataListVo vo) throws Exception;

	// 获取中文审核信息
	public CsltInfotPo getCsltAuditCnView(String BARCODE) throws Exception;

	// 获取审核英文信息
	public CsltInfotPo getCsltAuditView(Integer EBID) throws Exception;

	public CsltInfotPo getCsltAuditViewByBarcode(String barcode) throws Exception;

	// 获取需要审核的信息列表
	public List<CsltInfotPo> getCsltAuditList(CsltInfoVo object) throws Exception;

	// 审核日志添加
	public void ptyCsltLog(CsltLogInfoCustom csltLogInfoCustom) throws Exception;

	/*
	 * // 翻译通过认证 public PoState commitAudit(CsltInfoVo object, String op_userid,
	 * String op_name) throws Exception;
	 */

	public PoState setCsltInfo(CsltInfoVo object, String op_userid, String op_name) throws Exception;

	// 翻译数据修改
	public void updateCsltInfo(CsltInfoVo object) throws Exception;

	// 翻译数据添加
	public void insertCsltInfo(CsltInfoVo object) throws Exception;

	public PoState setCsltBaseInfo(CsltInfoVo object, String op_userid, String op_name) throws Exception;

	// 中文补充信息修改
	public void updateCsltBaseInfo(CsltInfoVo object) throws Exception;

	// 中文补充信息添加
	public void insertCsltBaseInfo(CsltInfoVo object) throws Exception;

	// =========================================================================
	public List<CsltProgramCustom> getCsltProgramListByExperID(String expertid) throws Exception;

	public CsltBaseInfo getCsltBaseInfoByPID(Integer pid) throws Exception;

	public List<CsltSliceInfoCustom> getCsltSliceInfoByBarcode(String barcode) throws Exception;

	public CsltSliceInfoCustom getSliceShortPicByDSID(Integer dsid) throws Exception;

	public List<CsltLogInfoCustom> getCsltLogInfoCustomByPID(Integer pid) throws Exception;

	public List<AttcPicture> getCsltPicInfoListByCCID(Integer ccid) throws Exception;

	public AttcPicture getCsltPicByID(String ID) throws Exception;

	public CsltResultCustom GetUnCommitResult(Integer pid, Integer expertID) throws Exception;

	public void updateResult(CsltResultVo csltResultVo) throws Exception;

	public void CommitResult(CsltResultVo csltResultVo) throws Exception;
	
	//=========================================================================
	public void getSliceListFromMDB(String dept) throws Exception;
	
	public String getSliceUploadState(String barcode) throws Exception;
	
	public int resultConfirm(String barcode, Integer pid, String reporttype) throws Exception;
	
	public CsltModel getUnConfirmCsltResult(String barcode) throws Exception;
		
    public List<SliceFileModel> getUploadSliceList() throws Exception;
    
    public void createProgramFromMySQL() throws Exception;
    
    public List<SliceFileModel> getSliceListByBarcodeFromMySQL(String barcode) throws Exception;
    
	public List<MicrobeModel> getMicrobeResultByBarcode(String barcode) throws Exception;
	
	public void deleteMicrobeResult(MicrobeModel microbeModel) throws Exception;
}
