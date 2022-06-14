package fr.xelians.xdh.plugin.transformer;

/**
 * @author aurelien on 01/06/2022
 */
public class TransformException extends Exception{

	private String code;
	public TransformException(String message) {
		super(message);
	}

	public TransformException(String message, String code) {
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
