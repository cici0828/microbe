package net.dagene.pmis.pathology.model;

public class CsltSliceModel {
	private Integer dsid;
	private String dsno;
	private String diancode;
	private String scandate;
	private String inputdate;
	private String inputuserid;
	private String inputusername;
	private String provider;
	private String viewmode;
	private String ds_pid;
	private String inputudept;
	private byte[] shortpic;
	private String shortpicurl;
	private String slice_url;
	private String slicedesc;
	private Double slicestate;
	private int maxzoom;
	private int width;
	private int height;

	public Integer getDsid() {
		return dsid;
	}

	public void setDsid(Integer dsid) {
		this.dsid = dsid;
	}

	public String getDsno() {
		return dsno;
	}

	public void setDsno(String dsno) {
		this.dsno = dsno;
	}

	public String getDiancode() {
		return diancode;
	}

	public void setDiancode(String diancode) {
		this.diancode = diancode;
	}

	public String getScandate() {
		return scandate;
	}

	public void setScandate(String scandate) {
		this.scandate = scandate;
	}

	public String getInputdate() {
		return inputdate;
	}

	public void setInputdate(String inputdate) {
		this.inputdate = inputdate;
	}

	public String getInputuserid() {
		return inputuserid;
	}

	public void setInputuserid(String inputuserid) {
		this.inputuserid = inputuserid;
	}

	public String getInputusername() {
		return inputusername;
	}

	public void setInputusername(String inputusername) {
		this.inputusername = inputusername;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getViewmode() {
		return viewmode;
	}

	public void setViewmode(String viewmode) {
		this.viewmode = viewmode;
	}

	public String getDs_pid() {
		return ds_pid;
	}

	public void setDs_pid(String ds_pid) {
		this.ds_pid = ds_pid;
	}

	public String getInputudept() {
		return inputudept;
	}

	public void setInputudept(String inputudept) {
		this.inputudept = inputudept;
	}

	public byte[] getShortpic() {
		return shortpic;
	}

	public void setShortpic(byte[] shortpic) {
		this.shortpic = shortpic;
	}

	public String getShortpicurl() {
		return shortpicurl;
	}

	public void setShortpicurl(String shortpicurl) {
		this.shortpicurl = shortpicurl;
	}

	public String getSlice_url() {
		return slice_url;
	}

	public void setSlice_url(String slice_url) {
		this.slice_url = slice_url;
	}

	public String getSlicedesc() {
		return slicedesc;
	}

	public void setSlicedesc(String slicedesc) {
		this.slicedesc = slicedesc;
	}

	public Double getSlicestate() {
		return slicestate;
	}

	public void setSlicestate(Double slicestate) {
		this.slicestate = slicestate;
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
