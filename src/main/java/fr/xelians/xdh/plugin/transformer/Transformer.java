package fr.xelians.xdh.plugin.transformer;

import fr.xelians.xdh.plugin.sender.SendException;
import org.slf4j.Logger;

import java.nio.file.Path;
import java.util.List;

/**
 * This interface has to be implemented for every transformer.
 * Transformer parameters are the parameters defined in the constructor of the transformer.
 * Every transformer should transform 1..n files to a new file.
 */

/**
 * @author aurelien on 10/01/2022
 */
public interface Transformer {

	/**
	 * The transform method to be implemented for transformation
	 * @param fileNames the list of filenames to transform
	 * @param fromDirectory the directory where the files are
	 * @param toDirectory the directory where the new file has to be created
	 * @param logger The logger to use. Log files are in the log folder of the channel directory under the base datahub folder.
	 * @return The filename of the new file.
	 * @throws Exception For exception to be displayed as a functional exception you should throw a {{@link TransformException}}
	 */
	String transform(List<String> fileNames, Path fromDirectory, Path toDirectory, Logger logger) throws Exception;

}
