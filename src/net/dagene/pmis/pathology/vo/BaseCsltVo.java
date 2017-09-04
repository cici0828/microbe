package net.dagene.pmis.pathology.vo;

public class BaseCsltVo {
	private String barcode;
	private String op_userid;
	private String op_name;
	private String op_tel;
	private String key;
	private String dept;

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getOp_userid() {
		return op_userid;
	}

	public void setOp_userid(String op_userid) {
		this.op_userid = op_userid;
	}

	public String getOp_name() {
		return op_name;
	}

	public void setOp_name(String op_name) {
		this.op_name = op_name;
	}

	public String getOp_tel() {
		return op_tel;
	}

	public void setOp_tel(String op_tel) {
		this.op_tel = op_tel;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}
}
