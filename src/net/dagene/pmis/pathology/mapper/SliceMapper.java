package net.dagene.pmis.pathology.mapper;

import java.util.List;

import net.dagene.pmis.pathology.model.SliceFileModel;

public interface SliceMapper {
	public List<SliceFileModel> getUploadSliceList() throws Exception;
	
	public List<SliceFileModel> getSliceListByBarcode(String barcode) throws Exception;

}
