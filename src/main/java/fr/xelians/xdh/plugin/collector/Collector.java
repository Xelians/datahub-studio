package fr.xelians.xdh.plugin.collector;

import fr.xelians.xdh.plugin.logging.XDHProcessLogger;
import reactor.core.publisher.Mono;

import java.nio.file.Path;
import java.util.List;

/**
 * This interface has to be implemented for every collector.
 * Collector parameters are the parameters defined in the constructor of the collector.
 * Every collectors should collect 1...n files but not be blocking, you must keep n as a small number.
 */

/**
 * @author aurelien on 10/01/2022
 */
public interface Collector {

	/**
	 * Collect operation
	 * @param toDirectory The directory path where the files should be collected
	 * @param webhook The value is set if webhook is available for the channel
	 * @param logger The logger to use. Log files are in the log folder of the channel directory under the base datahub folder.
	 * @return The list of filenames collected, be careful to give only the filename not the path.
	 * @throws Exception For exception to be displayed as a functional exception you should throw a {{@link CollectException}}
	 */
	Mono<List<String>> collect(Path toDirectory, String webhook, XDHProcessLogger logger) throws Exception;

}
