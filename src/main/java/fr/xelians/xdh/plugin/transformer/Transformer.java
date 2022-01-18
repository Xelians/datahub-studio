package fr.xelians.xdh.plugin.transformer;

import org.slf4j.Logger;

import java.nio.file.Path;
import java.util.List;

/**
 * @author aurelien on 10/01/2022
 */
public interface Transformer {

	String transform(List<String> fileNames, Path fromDirectory, Path toDirectory, Logger logger) throws Exception;

}
