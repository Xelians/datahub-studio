package fr.xelians.xdh.plugin.transformer;

import fr.xelians.xdh.plugin.WorkerConfiguration;

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
