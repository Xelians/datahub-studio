package fr.xelians.xdh.plugin.sender;

import org.slf4j.Logger;

import java.nio.file.Path;

/**
 * This interface has to be implemented for every sender.
 * Sender parameters are the parameters defined in the constructor of the sender.
 * Every sender should send a file but not be blocking.
 */

/**
 * @author aurelien on 10/01/2022
 */
public interface Sender {

	/**
	 * Send operation
	 * @param fileName the filename to send
	 * @param fromDirectory the path of the directory where is the file to send
	 * @param logger The logger to use. Log files are in the log folder of the channel directory under the base datahub folder.
	 * @return The detail of a successful send operation to be displayed on the user interface
	 * @throws Exception For exception to be displayed as a functional exception you should throw a {{@link SendException}}
	 */
	String send(String fileName, Path fromDirectory, Logger logger) throws Exception;

}
