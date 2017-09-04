package net.dagene.pmis.pathology.po;

import net.dagene.pmis.pathology.model.CsltSliceModel;

public class CsltSliceInfoCustom extends CsltSliceModel {
	@Override
	public void setShortpicurl(String shortpicurl) {
		super.setShortpicurl(""+shortpicurl);
	}
}
