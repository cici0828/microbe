package net.dagene.pmis.pathology.model;

public class CsltBaseInfoModel {
	private int bid;
	private String clidata;
	private String clihistory;
	private String gross;
	private String first_option;
	private String gid;
	private String barcode;
	private String letter;
	

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getClihistory() {
		return clihistory;
	}

	public void setClihistory(String clihistory) {
		this.clihistory = clihistory;
	}

	public String getGross() {
		return gross;
	}

	public void setGross(String gross) {
		this.gross = gross;
	}

	public String getFirst_option() {
		return first_option;
	}

	public void setFirst_option(String first_option) {
		this.first_option = first_option;
	}

	public String getClidata() {
		return clidata;
	}

	public void setClidata(String clidata) {
		this.clidata = clidata;
	}

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}

}
