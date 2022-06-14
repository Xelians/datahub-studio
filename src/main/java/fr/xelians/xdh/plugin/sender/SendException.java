package fr.xelians.xdh.plugin.sender;

/**
 * @author aurelien on 01/06/2022
 */
public class SendException extends Exception{

	private String code;
	public SendException(String message) {
		super(message);
	}

	public SendException(String message, String code) {
		super(message);
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
