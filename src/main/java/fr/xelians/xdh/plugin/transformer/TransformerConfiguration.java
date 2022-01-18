package fr.xelians.xdh.plugin.transformer;

import fr.xelians.xdh.plugin.PluginConfiguration;

/**
 * @author aurelien on 17/01/2022
 */
public interface TransformerConfiguration extends PluginConfiguration {

	Class<? extends Transformer> getTransformerClass();

}
