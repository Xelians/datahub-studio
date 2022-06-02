package fr.xelians.xdh.plugin;


import fr.xelians.xdh.plugin.form.WorkerForm;
import fr.xelians.xdh.plugin.translation.Label;

/**
 * @author aurelien on 17/01/2022
 */
public interface WorkerConfiguration {

	String name();

	String id();

	default String description(){
		return "";
	}

	String version();

	WorkerForm.Form form();

}
