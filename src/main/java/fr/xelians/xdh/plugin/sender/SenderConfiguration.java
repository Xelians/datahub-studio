package fr.xelians.xdh.plugin.sender;

import fr.xelians.xdh.plugin.PluginConfiguration;

/**
 * @author aurelien on 17/01/2022
 */
public interface SenderConfiguration extends PluginConfiguration {

	Class<? extends Sender> getSenderClass();

}
