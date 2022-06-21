package fr.xelians.xdh.plugin.transformer;

/**
 * Throw this exception to print a transformation functional error on the user interface.
 */

/**
 * @author aurelien on 01/06/2022
 */
public class TransformException extends Exception{

	private String code;

	/**
	 * @param message the message to be displayed on the user interface
	 */
	public TransformException(String message) {
		super(message);
	}

	/**
	 * @param message the message to be displayed on the user interface
	 * @param code the code to be displayed on the user interface
	 */
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
