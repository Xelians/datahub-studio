package fr.xelians.xdh.plugin.sender;

import fr.xelians.xdh.plugin.WorkerConfiguration;

/**
 * @author aurelien on 17/01/2022
 */
public interface SenderConfiguration extends WorkerConfiguration {

	Class<? extends Sender> getSenderClass();

}
