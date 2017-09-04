package net.dagene.pmis.pathology.mapper;

import java.util.List;

import net.dagene.pmis.pathology.model.AttcPictureModel;
import net.dagene.pmis.pathology.model.DgsinfoModel;
import net.dagene.pmis.pathology.po.AttcPicture;

public interface PathologyMapper {
	public List<AttcPicture> getAttcPicInfoListByFolderno(String folderno) throws Exception;
    
	public AttcPicture getAttcPicByID(String folderno) throws Exception;
	
	public void deleteDgsinfo(String folderno) throws Exception;
	
	public void insertAttcPic(AttcPictureModel attcPictureModel) throws Exception;
	
	public void deleteAttcPic(String folderno) throws Exception;
	
	public void insertDgsinfo(DgsinfoModel dgsinfoModel) throws Exception;
	
	public String getOrdno(String folderno) throws Exception;

	

}
