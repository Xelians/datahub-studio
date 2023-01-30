package fr.xelians.xdh.studio.transformer;

import fr.xelians.xdh.studio.WorkerConfiguration;

/**
 * Every Transformer must have a configuration class. The configuration class must implement this interface.
 */

/**
 * @author aurelien on 17/01/2022
 */
public interface TransformerConfiguration extends WorkerConfiguration {

	/**
	 * Method to get the transformer class
	 * @return the class of The transformer
	 */
	Class<? extends Transformer> getTransformerClass();

}
