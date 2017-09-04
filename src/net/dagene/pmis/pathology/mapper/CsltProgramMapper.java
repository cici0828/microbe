package net.dagene.pmis.pathology.mapper;

import java.util.List;

import net.dagene.pmis.pathology.model.CsltGroupprogramModel;
import net.dagene.pmis.pathology.model.CsltModel;
import net.dagene.pmis.pathology.model.CsltPatientinformation;
import net.dagene.pmis.pathology.model.CsltPicModel;
import net.dagene.pmis.pathology.model.MicrobeModel;
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
import net.dagene.pmis.pathology.vo.BaseCsltVo;
import net.dagene.pmis.pathology.vo.CsltDiagnosisVo;
import net.dagene.pmis.pathology.vo.CsltExpertVo;
import net.dagene.pmis.pathology.vo.CsltGroupprogramVo;
import net.dagene.pmis.pathology.vo.CsltInfoVo;
import net.dagene.pmis.pathology.vo.CsltPicVo;
import net.dagene.pmis.pathology.vo.CsltProgramVo;
import net.dagene.pmis.pathology.vo.CsltResultVo;
import net.dagene.pmis.pathology.vo.ExceClstExpertCommitVo;
import net.dagene.pmis.pathology.vo.ExecClstCommitExpertVo;
import net.dagene.pmis.pathology.vo.ExpertVo;
import net.dagene.pmis.pathology.vo.GetDataListVo;

public interface CsltProgramMapper {
	public void execPtyClstCommitExpert(ExecClstCommitExpertVo vo) throws Exception;
	
	public void execPtyClstExpertCommit(ExceClstExpertCommitVo vo) throws Exception;
	
	public List<CsltExpertPo> getExpertModel(BaseCsltVo vo) throws Exception;	
	public void deleteCsltExpert(Integer id) throws Exception;
	public void insertCsltExpert(CsltExpertVo vo) throws Exception;	
	public void updateCsltExpert(CsltExpertVo vo) throws Exception;	

	public void updateDiagnosis(CsltDiagnosisVo vo) throws Exception;
	public void deleteDiagnosis(String barcode) throws Exception;
	public CsltPatientinformation getCsltpatinfo(String barcode) throws Exception;	

	public List<CsltSliceInfoCustom> getCsltSliceList(BaseCsltVo vo) throws Exception;
	public CsltSliceInfoCustom getCsltSliceSmallPic(int dsid) throws Exception;
	
	public List<CsltDiagnosisListPo> getCsltDiagnosisList(GetDataListVo vo) throws Exception;
	
	public  CsltGroupprogramModel getCsltGroupprogramByBarcode(String barcode) throws Exception;
	public  void insertCsltGroupprogram(CsltGroupprogramVo object) throws Exception;

	public CsltModel getCsltModel(CsltModel object) throws Exception;
	public void insertCslt(CsltModel object) throws Exception;
	
	public List<CsltPicPo> getCsltPics(CsltPicVo object) throws Exception;
	public void insertCsltPic(CsltPicModel object) throws Exception;
	public void updateCsltPic(CsltPicModel object) throws Exception;

	public List<CsltLog> getCsltLog(String barcode) throws Exception;

	public List<TranslateTotalPo> getTranslateTotal(GetDataListVo vo) throws Exception;

	public List<TranslatePo> getTranslateList(GetDataListVo vo) throws Exception;

	public CsltInfotPo getCsltAuditView(Integer ebid) throws Exception;

	public CsltInfotPo getCsltAuditViewByBarcode(String barcode) throws Exception;

	public CsltInfotPo getCsltAuditCnView(String BARCODE) throws Exception;

	public List<CsltInfotPo> getCsltAuditList(CsltInfoVo object) throws Exception;

	public void updateCsltInfo(CsltInfoVo object) throws Exception;

	public void insertCsltInfo(CsltInfoVo object) throws Exception;

	public void insertCsltBaseInfo(CsltInfoVo object) throws Exception;

	public void updateCsltBaseInfo(CsltInfoVo object) throws Exception;

	// ===================================================================================
	public List<CsltProgramCustom> getCsltProgramList(CsltProgramVo csltProgramVo) throws Exception;

	public void updateCsltProgram(CsltProgramVo csltProgramVo) throws Exception;

	public CsltBaseInfo getCsltBaseInfo(CsltProgramVo csltProgramVo) throws Exception;

	public int getCsltSliceCount(String barcode) throws Exception;
	
	public List<CsltSliceInfoCustom> getCsltSliceInfo(CsltProgramVo csltProgramVo) throws Exception;

	public CsltSliceInfoCustom getSliceShortPicByDSID(Integer dsid) throws Exception;

	public List<CsltLogInfoCustom> getCsltLogInfoCustomByPID(Integer pid) throws Exception;

	public List<CsltResultCustom> getCsltResultList(CsltResultVo csltResultVo) throws Exception;

	public void insertResult(CsltResultVo csltResultVo) throws Exception;

	public void deleteReuslt(CsltResultVo csltResultVo) throws Exception;

	public void updateResult(CsltResultVo csltResultVo) throws Exception;

	public List<ExpertCustom> getExpertList(Integer ID) throws Exception;

	public void updateExpert(ExpertVo expertVo) throws Exception;

	public List<AttcPicture> getCsltPicInfoListByCCID(Integer CCID) throws Exception;

	public AttcPicture getCsltPicByID(String ID) throws Exception;

	public void ptyCsltLog(CsltLogInfoCustom csltLogInfoCustom) throws Exception;
	
	public void ptyCsltLog(BaseCsltVo vo) throws Exception;
	
	public void deleteCsltSliceList(String barcode) throws Exception;
	
	public void insertCsltSlice(CsltSliceInfoCustom csltSliceInfoCustom) throws Exception;
	
	public List<MicrobeModel> getMicrobeResultByBarcode(String barcode) throws Exception;
	
	public void deleteMicrobeResult(MicrobeModel microbeModel) throws Exception;

}
