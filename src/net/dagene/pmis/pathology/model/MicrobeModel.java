package net.dagene.pmis.pathology.model;

public class MicrobeModel {
	private String mguid;
	private String mic_barcode;
	private String inputman;
	private String microbecode;
	private String tankfarm;
	public String getMguid() {
		return mguid;
	}
	public void setMguid(String mguid) {
		this.mguid = mguid;
	}
	public String getMic_barcode() {
		return mic_barcode;
	}
	public void setMic_barcode(String mic_barcode) {
		this.mic_barcode = mic_barcode;
	}
	public String getInputman() {
		return inputman;
	}
	public void setInputman(String inputman) {
		this.inputman = inputman;
	}
	public String getMicrobecode() {
		return microbecode;
	}
	public void setMicrobecode(String microbecode) {
		this.microbecode = microbecode;
	}
	public String getTankfarm() {
		return tankfarm;
	}
	public void setTankfarm(String tankfarm) {
		this.tankfarm = tankfarm;
	}

}
