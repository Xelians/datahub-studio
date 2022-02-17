package fr.xelians.xdh.plugin.collector;

import fr.xelians.xdh.plugin.WorkerConfiguration;

/**
 * @author aurelien on 17/01/2022
 */
public interface CollectorConfiguration extends WorkerConfiguration {

	Class<? extends Collector> getCollectorClass();

}
