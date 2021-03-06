package net.dagene.pmis.pathology.po;

public class CsltSliceInfo {
	private Integer dsid;
	private String dsno;
	private String diancode;
	private String scandate;
	private String inputdate;
	private String inputuserid;
	private String inputname;
	private String provider;
	private String viewmode;
	private Integer ds_pid;
	private String inputdept;
	private byte[] shortpic;
	private String shortpicurl;
	private String slice_url;

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

	public String getInputname() {
		return inputname;
	}

	public void setInputname(String inputname) {
		this.inputname = inputname;
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

	public Integer getDs_pid() {
		return ds_pid;
	}

	public void setDs_pid(Integer ds_pid) {
		this.ds_pid = ds_pid;
	}

	public String getInputdept() {
		return inputdept;
	}

	public void setInputdept(String inputdept) {
		this.inputdept = inputdept;
	}

	public String getSlice_url() {
		return slice_url;
	}

	public void setSlice_url(String slice_url) {
		this.slice_url = slice_url;
	}

	public String getShortpicurl() {
		return shortpicurl;
	}

	public void setShortpicurl(String shortpicurl) {
		this.shortpicurl = shortpicurl;
	}

	public byte[] getShortpic() {
		return shortpic;
	}

	public void setShortpic(byte[] shortpic) {
		this.shortpic = shortpic;
	}

}
