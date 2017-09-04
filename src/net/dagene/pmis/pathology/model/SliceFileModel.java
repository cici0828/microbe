package net.dagene.pmis.pathology.model;

public class SliceFileModel {
	private int id;
	private String barcode;
	private String slicenumber;
	private String uploadfilepath;
	private String filestatus;
	private String diagnosecomment;
	private int offset;
	private int counts;
	private int sliecefileid;
	private String slicepath;
	private int maxzoom;
	private int width;
	private int height;
	
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getSlicenumber() {
		return slicenumber;
	}
	public void setSlicenumber(String slicenumber) {
		this.slicenumber = slicenumber;
	}
	public String getUploadfilepath() {
		return uploadfilepath;
	}
	public void setUploadfilepath(String uploadfilepath) {
		this.uploadfilepath = uploadfilepath;
	}
	public String getFilestatus() {
		return filestatus;
	}
	public void setFilestatus(String filestatus) {
		this.filestatus = filestatus;
	}
	public String getDiagnosecomment() {
		return diagnosecomment;
	}
	public void setDiagnosecomment(String diagnosecomment) {
		this.diagnosecomment = diagnosecomment;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getCounts() {
		return counts;
	}
	public void setCounts(int counts) {
		this.counts = counts;
	}
	public int getSliecefileid() {
		return sliecefileid;
	}
	public void setSliecefileid(int sliecefileid) {
		this.sliecefileid = sliecefileid;
	}
	public String getSlicepath() {
		return slicepath;
	}
	public void setSlicepath(String slicepath) {
		this.slicepath = slicepath;
	}
	public int getMaxzoom() {
		return maxzoom;
	}
	public void setMaxzoom(int maxzoom) {
		this.maxzoom = maxzoom;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}


}
