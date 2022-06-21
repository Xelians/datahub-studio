package fr.xelians.xdh.plugin;


import fr.xelians.xdh.plugin.form.WorkerForm;
import fr.xelians.xdh.plugin.translation.Label;

/**
 * @author aurelien on 17/01/2022
 *
 * Every worker must have a configuration class which implements WorkerConfiguration
 */
public interface WorkerConfiguration {

	/**
	 * Get the name of the worker.
	 * This name will be displayed on the User Interface of the Xelians DataHub.
	 * @return The name of the worker
	 */
	String name();

	/**
	 * Get the worker id.
	 * This id must be unique be careful to give something very specific and avoid spaces
	 * @return the worker id
	 */
	String id();

	/**
	 * Get the description displayed on the User Interface of the Xelians DataHub.
	 * @return the description
	 */
	default String description(){
		return "";
	}

	/**
	 * Get the version of the worker.
	 * The version will be displayed along the worker on the User Interface of the Xelians DataHub.
	 * To create a version of specific worker you must create a worker with the same id of the previous one and a different version.
	 * @return the version of the worker
	 */
	String version();

	/**
	 * The form for the worker configuration. To create the form you can use the {{@link fr.xelians.xdh.plugin.form.WorkerFormBuilder}} by calling {{@link WorkerForm#builder() }}.
	 * The form will be displayed on the User Interface of the Xelians DataHub during the channel creation and update process.
	 * The form should be coherent you need to give a field for every constructor parameter of the worker.
	 * The field parameter attribute correspond exactly to the parameter name.
	 * @return the form
	 */
	WorkerForm.Form form();

}
