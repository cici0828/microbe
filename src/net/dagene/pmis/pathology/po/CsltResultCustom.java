package net.dagene.pmis.pathology.po;

import java.util.List;

import net.dagene.pmis.pathology.model.CsltModel;

public class CsltResultCustom extends CsltModel{
	private List<AttcPicture> attcPictureList;

	public List<AttcPicture> getAttcPictureList() {
		return attcPictureList;
	}

	public void setAttcPictureList(List<AttcPicture> attcPictureList) {
		this.attcPictureList = attcPictureList;
	}	
}
