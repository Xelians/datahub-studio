package fr.xelians.xdh.studio.sender;

import fr.xelians.xdh.studio.WorkerConfiguration;

/**
 * Every Sender must have a configuration class. The configuration class must implement this interface.
 */
public interface SenderConfiguration extends WorkerConfiguration {

	/**
	 * Method to get the sender class
	 * @return the class of The sender
	 */
	Class<? extends Sender> getSenderClass();

}
