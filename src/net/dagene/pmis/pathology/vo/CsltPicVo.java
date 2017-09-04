package net.dagene.pmis.pathology.vo;

import net.dagene.pmis.pathology.model.CsltPicModel;

public class CsltPicVo extends CsltPicModel {
	private String barcode;
	private Boolean isnew;

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public Boolean getIsnew() {
		return isnew;
	}

	public void setIsnew(Boolean isnew) {
		this.isnew = isnew;
	}

}
