package fr.xelians.xdh.studio.transformer;

import fr.xelians.xdh.studio.ChannelStore;
import fr.xelians.xdh.studio.ResourceHandler;
import fr.xelians.xdh.studio.logging.XDHProcessLogger;

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
public interface Transformer extends ResourceHandler {

	/**
	 * The transform method to be implemented for transformation
	 * @param fileNames the list of filenames to transform
	 * @param fromDirectory the directory where the files are
	 * @param toDirectory the directory where the new file has to be created
	 * @param logger The logger to use. Log files are in the log folder of the channel directory under the base datahub folder.
	 * @return The result containing success message and the name of the produced file.
	 * @throws Exception For exception to be displayed as a functional exception you should throw a {{@link TransformException}}
	 */
	TransformResult transform(List<String> fileNames, Path fromDirectory, Path toDirectory, XDHProcessLogger logger) throws Exception;

	/**
	 * The transform method to be implemented for transformation
	 * @param fileNames the list of filenames to transform
	 * @param fromDirectory the directory where the files are
	 * @param toDirectory the directory where the new file has to be created
	 * @param logger The logger to use. Log files are in the log folder of the channel directory under the base datahub folder.
	 * @param channelStore storage for persisting key-value data
	 * @return The result containing success message and the name of the produced file.
	 * @throws Exception For exception to be displayed as a functional exception you should throw a {{@link TransformException}}
	 */
	default TransformResult transform(List<String> fileNames, Path fromDirectory, Path toDirectory, XDHProcessLogger logger, ChannelStore channelStore) throws Exception{
		return  transform(fileNames, fromDirectory, toDirectory, logger);
	}

}
