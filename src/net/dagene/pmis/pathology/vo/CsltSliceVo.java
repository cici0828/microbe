package net.dagene.pmis.pathology.vo;

import java.util.ArrayList;

import net.dagene.pmis.pathology.po.CsltSliceInfoCustom;

public class CsltSliceVo {	
	public CsltSliceVo(){
				
	}
	
	private String barcode;
	
	private ArrayList<CsltSliceInfoCustom> slicelist;


	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public ArrayList<CsltSliceInfoCustom> getSlicelist() {
		return slicelist;
	}

	public void setSlicelist(ArrayList<CsltSliceInfoCustom> slicelist) {
		this.slicelist = slicelist;
	}
}
