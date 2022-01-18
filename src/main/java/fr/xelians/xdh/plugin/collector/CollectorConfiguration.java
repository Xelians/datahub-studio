package fr.xelians.xdh.plugin.collector;

import fr.xelians.xdh.plugin.PluginConfiguration;

/**
 * @author aurelien on 17/01/2022
 */
public interface CollectorConfiguration extends PluginConfiguration {

	Class<? extends Collector> getCollectorClass();

}
