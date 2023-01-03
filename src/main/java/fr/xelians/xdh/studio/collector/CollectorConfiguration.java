package fr.xelians.xdh.studio.collector;

import fr.xelians.xdh.studio.WorkerConfiguration;

/**
 * Every Collector must have a configuration class. The configuration class must implement this interface.
 */

/**
 *  @author aurelien on 17/01/2022
 */
public interface CollectorConfiguration extends WorkerConfiguration {

	/**
	 * Method to get the collector class
	 * @return the class of The collector
	 */
	Class<? extends Collector> getCollectorClass();

}
