package fr.xelians.xdh.plugin.collector;

/**
 * Throw this exception to print a collect functional error on the user interface.
 */
/**
 * @author aurelien on 01/06/2022
 */
public class CollectException extends Exception{

	/**
	 * @param message the message to be displayed on the user interface
	 */
	public CollectException(String message) {
		super(message);
	}
}
