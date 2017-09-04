package net.dagene.pmis.common.mapper;

import net.dagene.pmis.common.po.PatientinfoCustom;
import net.dagene.pmis.pathology.vo.CsltGroupprogramVo;

public interface PatientInfoMapper {
	public PatientinfoCustom getPatientinfoCustomByBarcode(String barcode)
			throws Exception;
}
