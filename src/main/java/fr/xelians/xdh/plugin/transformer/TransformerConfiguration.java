package fr.xelians.xdh.plugin.transformer;

import fr.xelians.xdh.plugin.WorkerConfiguration;

/**
 * @author aurelien on 17/01/2022
 */
public interface TransformerConfiguration extends WorkerConfiguration {

	Class<? extends Transformer> getTransformerClass();

}
