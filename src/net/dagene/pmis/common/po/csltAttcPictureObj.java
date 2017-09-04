package net.dagene.pmis.common.po;

import java.io.Serializable;

public class csltAttcPictureObj{
	public csltAttcPictureObj(){}
	/*截图ID*/
	private String picid;
	/*图片描述*/
	private String describe;
	/*图片文件名*/
	private String reportpic;
	
	public String getPicid() {
		return picid;
	}
	public void setPicid(String picid) {
		this.picid = picid;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public String getReportpic() {
		return reportpic;
	}
	public void setReportpic(String reportpic) {
		this.reportpic = reportpic;
	}	
}
