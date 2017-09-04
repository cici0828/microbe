package net.dagene.pmis.exception;

public class CustomException extends Exception {
	private static final long serialVersionUID = 11L;
	
	public String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public CustomException(String message)
	{
		super(message);
		this.message = message;
	}

}
