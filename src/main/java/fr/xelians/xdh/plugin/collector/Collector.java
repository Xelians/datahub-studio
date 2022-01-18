package fr.xelians.xdh.plugin.collector;

import org.slf4j.Logger;

import java.nio.file.Path;
import java.util.List;

/**
 * @author aurelien on 10/01/2022
 */
public interface Collector {

	List<String> collect(Path toDirectory, Logger logger) throws Exception;

}
