package fr.xelians.xdh.studio.form;

import fr.xelians.xdh.studio.translation.Label;

import java.util.List;

/**
 * @author aurelien on 08/03/2022
 *
 * This class contain all the interfaces for builders and form components
 */
public class WorkerForm {

	/**
	 * Get a new {{@link WorkerFormBuilder}}
	 * @return {{@link Builder}}
	 */
	public static Builder builder(){
		return new WorkerFormBuilder();
	}

	/**
	 * Form Builder interface
	 */
	public interface Builder{

		/**
		 * Add an input text for integer values. The parameter should be of type Integer.
		 * @param parameter the name fo the parameter should be equal to the parameter name of the constructor
		 * @param label the {{@link fr.xelians.xdh.studio.translation.Label.Translation}} use for form labels translation
		 * @param mandatory if the form field is mandatory
		 * @return {{@link InputTextSpec<Integer>}} specification for input text with integer values
		 */
		InputTextSpec<Integer> addInputTextInt(String parameter, Label.Translation label, boolean mandatory);

		/**
		 * Add an input text for double precision values. The parameter should be of type Double.
		 * @param parameter the name fo the parameter should be equal to the parameter name of the constructor
		 * @param label the {{@link fr.xelians.xdh.studio.translation.Label.Translation}} use for form labels translation
		 * @param mandatory if the form field is mandatory
		 * @return {{@link InputTextSpec<Double>}} specification for input text with double precision values
		 */
		InputTextSpec<Double> addInputTextDouble(String parameter, Label.Translation label, boolean mandatory);

		/**
		 * Add an input text for float values. The parameter should be of type Float.
		 * @param parameter the name fo the parameter should be equal to the parameter name of the constructor
		 * @param label the {{@link fr.xelians.xdh.studio.translation.Label.Translation}} use for form labels translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.studio.translation.Label.Translation}} instances
		 * @param mandatory if the form field is mandatory
		 * @return {{@link InputTextSpec<Float>}} specification for input text with float values
		 */
		InputTextSpec<Float> addInputTextFloat(String parameter, Label.Translation label, boolean mandatory);

		/**
		 * Add an input text for long values. The parameter should be of type Long.
		 * @param parameter the name fo the parameter should be equal to the parameter name of the constructor
		 * @param label the {{@link fr.xelians.xdh.studio.translation.Label.Translation}} use for form labels translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.studio.translation.Label.Translation}} instances
		 * @param mandatory if the form field is mandatory
		 * @return {{@link InputTextSpec<Long>}} specification for input text with long values
		 */
		InputTextSpec<Long> addInputTextLong(String parameter, Label.Translation label, boolean mandatory);

		/**
		 * Add an input text for Boolean values. The parameter should be of type Boolean.
		 * @param parameter the name fo the parameter should be equal to the parameter name of the constructor
		 * @param label the {{@link fr.xelians.xdh.studio.translation.Label.Translation}} use for form labels translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.studio.translation.Label.Translation}} instances
		 * @param mandatory if the form field is mandatory
		 * @return {{@link InputTextSpec<Boolean>}} specification for input text with long values
		 */
		InputTextSpec<Boolean> addInputTextBoolean(String parameter, Label.Translation label, boolean mandatory);

		/**
		 * Add an input text for text values. The parameter should be of type String.
		 * @param parameter the name fo the parameter should be equal to the parameter name of the constructor
		 * @param label the {{@link fr.xelians.xdh.studio.translation.Label.Translation}} use for form labels translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.studio.translation.Label.Translation}} instances
		 * @param mandatory if the form field is mandatory
		 * @return {{@link InputTextSpec<String>}} specification for input text with text values
		 */
		InputTextSpec<String> addInputTextText(String parameter, Label.Translation label, boolean mandatory);

		/**
		 * Add an input select for integer values. The parameter should be of type Integer.
		 * @param parameter the name fo the parameter should be equal to the parameter name of the constructor
		 * @param label the {{@link fr.xelians.xdh.studio.translation.Label.Translation}} use for form labels translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.studio.translation.Label.Translation}} instances
		 * @param mandatory if the form field is mandatory
		 * @return {{@link InputFormMultiSpec<Integer>}} specification for input multivalued with integer values
		 */
		InputFormMultiSpec<Integer> addInputSelectInt(String parameter, Label.Translation label, boolean mandatory);

		/**
		 * Add an input select for double precision values. The parameter should be of type Double.
		 * @param parameter the name fo the parameter should be equal to the parameter name of the constructor
		 * @param label the {{@link fr.xelians.xdh.studio.translation.Label.Translation}} use for form labels translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.studio.translation.Label.Translation}} instances
		 * @param mandatory if the form field is mandatory
		 * @return {{@link InputFormMultiSpec<Double>}} specification for input multivalued with double precision values
		 */
		InputFormMultiSpec<Double> addInputSelectDouble(String parameter, Label.Translation label, boolean mandatory);

		/**
		 * Add an input select for float values. The parameter should be of type Float.
		 * @param parameter the name fo the parameter should be equal to the parameter name of the constructor
		 * @param label the {{@link fr.xelians.xdh.studio.translation.Label.Translation}} use for form labels translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.studio.translation.Label.Translation}} instances
		 * @param mandatory if the form field is mandatory
		 * @return {{@link InputFormMultiSpec<Float>}} specification for input multivalued with float values
		 */
		InputFormMultiSpec<Float> addInputSelectFloat(String parameter, Label.Translation label, boolean mandatory);

		/**
		 * Add an input select for long values. The parameter should be of type Long.
		 * @param parameter the name fo the parameter should be equal to the parameter name of the constructor
		 * @param label the {{@link fr.xelians.xdh.studio.translation.Label.Translation}} use for form labels translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.studio.translation.Label.Translation}} instances
		 * @param mandatory if the form field is mandatory
		 * @return {{@link InputFormMultiSpec<Long>}} specification for input multivalued with long values
		 */
		InputFormMultiSpec<Long> addInputSelectLong(String parameter, Label.Translation label, boolean mandatory);

		/**
		 * Add an input select for boolean values. The parameter should be of type Boolean.
		 * @param parameter the name fo the parameter should be equal to the parameter name of the constructor
		 * @param label the {{@link fr.xelians.xdh.studio.translation.Label.Translation}} use for form labels translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.studio.translation.Label.Translation}} instances
		 * @param mandatory if the form field is mandatory
		 * @return {{@link InputFormMultiSpec<Boolean>}} specification for input multivalued with boolean values
		 */
		InputFormMultiSpec<Boolean> addInputSelectBoolean(String parameter, Label.Translation label, boolean mandatory);

		/**
		 * Add an input select for text values. The parameter should be of type String.
		 * @param parameter the name fo the parameter should be equal to the parameter name of the constructor
		 * @param label the {{@link fr.xelians.xdh.studio.translation.Label.Translation}} use for form labels translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.studio.translation.Label.Translation}} instances
		 * @param mandatory if the form field is mandatory
		 * @return {{@link InputFormMultiSpec<String>}} specification for input multivalued with text values
		 */
		InputFormMultiSpec<String> addInputSelectText(String parameter, Label.Translation label, boolean mandatory);

		/**
		 * Add an input checkbox for integer values. The parameter should be of type List<Integer>.
		 * @param parameter the name fo the parameter should be equal to the parameter name of the constructor
		 * @param label the {{@link fr.xelians.xdh.studio.translation.Label.Translation}} use for form labels translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.studio.translation.Label.Translation}} instances
		 * @param mandatory if the form field is mandatory
		 * @return {{@link InputFormMultiSpec<Integer>}} specification for input multivalued with integer values
		 */
		InputFormMultiSpec<Integer> addInputCheckboxInt(String parameter, Label.Translation label, boolean mandatory);

		/**
		 * Add an input checkbox for double values. The parameter should be of type List<Double>.
		 * @param parameter the name fo the parameter should be equal to the parameter name of the constructor
		 * @param label the {{@link fr.xelians.xdh.studio.translation.Label.Translation}} use for form labels translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.studio.translation.Label.Translation}} instances
		 * @param mandatory if the form field is mandatory
		 * @return {{@link InputFormMultiSpec<Double>}} specification for input multivalued with double values
		 */
		InputFormMultiSpec<Double> addInputCheckboxDouble(String parameter, Label.Translation label, boolean mandatory);

		/**
		 * Add an input checkbox for float values. The parameter should be of type List<Float>.
		 * @param parameter the name fo the parameter should be equal to the parameter name of the constructor
		 * @param label the {{@link fr.xelians.xdh.studio.translation.Label.Translation}} use for form labels translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.studio.translation.Label.Translation}} instances
		 * @param mandatory if the form field is mandatory
		 * @return {{@link InputFormMultiSpec<Float>}} specification for input multivalued with float values
		 */
		InputFormMultiSpec<Float> addInputCheckboxFloat(String parameter, Label.Translation label, boolean mandatory);

		/**
		 * Add an input checkbox for long values. The parameter should be of type List<Long>.
		 * @param parameter the name fo the parameter should be equal to the parameter name of the constructor
		 * @param label the {{@link fr.xelians.xdh.studio.translation.Label.Translation}} use for form labels translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.studio.translation.Label.Translation}} instances
		 * @param mandatory if the form field is mandatory
		 * @return {{@link InputFormMultiSpec<Long>}} specification for input multivalued with long values
		 */
		InputFormMultiSpec<Long> addInputCheckboxLong(String parameter, Label.Translation label, boolean mandatory);

		/**
		 * Add an input checkbox for boolean values. The parameter should be of type List<Boolean>.
		 * @param parameter the name fo the parameter should be equal to the parameter name of the constructor
		 * @param label the {{@link fr.xelians.xdh.studio.translation.Label.Translation}} use for form labels translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.studio.translation.Label.Translation}} instances
		 * @param mandatory if the form field is mandatory
		 * @return {{@link InputFormMultiSpec<Boolean>}} specification for input multivalued with boolean values
		 */
		InputFormMultiSpec<Boolean> addInputCheckboxBoolean(String parameter, Label.Translation label, boolean mandatory);

		/**
		 * Add an input checkbox for text values. The parameter should be of type List<String>.
		 * @param parameter the name fo the parameter should be equal to the parameter name of the constructor
		 * @param label the {{@link fr.xelians.xdh.studio.translation.Label.Translation}} use for form labels translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.studio.translation.Label.Translation}} instances
		 * @param mandatory if the form field is mandatory
		 * @return {{@link InputFormMultiSpec<String>}} specification for input multivalued with text values
		 */
		InputFormMultiSpec<String> addInputCheckboxText(String parameter, Label.Translation label, boolean mandatory);

		/**
		 * Add an input radio for integer values. The parameter should be of type Integer.
		 * @param parameter the name fo the parameter should be equal to the parameter name of the constructor
		 * @param label the {{@link fr.xelians.xdh.studio.translation.Label.Translation}} use for form labels translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.studio.translation.Label.Translation}} instances
		 * @param mandatory if the form field is mandatory
		 * @return {{@link InputFormMultiSpec<Integer>}} specification for input multivalued with integer values
		 */
		InputFormMultiSpec<Integer> addInputRadioInt(String parameter, Label.Translation label, boolean mandatory);

		/**
		 * Add an input radio for double values. The parameter should be of type Double.
		 * @param parameter the name fo the parameter should be equal to the parameter name of the constructor
		 * @param label the {{@link fr.xelians.xdh.studio.translation.Label.Translation}} use for form labels translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.studio.translation.Label.Translation}} instances
		 * @param mandatory if the form field is mandatory
		 * @return {{@link InputFormMultiSpec<Double>}} specification for input multivalued with double values
		 */
		InputFormMultiSpec<Double> addInputRadioDouble(String parameter, Label.Translation label, boolean mandatory);

		/**
		 * Add an input radio for float values. The parameter should be of type Float.
		 * @param parameter the name fo the parameter should be equal to the parameter name of the constructor
		 * @param label the {{@link fr.xelians.xdh.studio.translation.Label.Translation}} use for form labels translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.studio.translation.Label.Translation}} instances
		 * @param mandatory if the form field is mandatory
		 * @return {{@link InputFormMultiSpec<Float>}} specification for input multivalued with float values
		 */
		InputFormMultiSpec<Float> addInputRadioFloat(String parameter, Label.Translation label, boolean mandatory);

		/**
		 * Add an input radio for long values. The parameter should be of type Long.
		 * @param parameter the name fo the parameter should be equal to the parameter name of the constructor
		 * @param label the {{@link fr.xelians.xdh.studio.translation.Label.Translation}} use for form labels translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.studio.translation.Label.Translation}} instances
		 * @param mandatory if the form field is mandatory
		 * @return {{@link InputFormMultiSpec<Long>}} specification for input multivalued with long values
		 */
		InputFormMultiSpec<Long> addInputRadioLong(String parameter, Label.Translation label, boolean mandatory);

		/**
		 * Add an input radio for boolean values. The parameter should be of type Boolean
		 * @param parameter the name fo the parameter should be equal to the parameter name of the constructor
		 * @param label the {{@link fr.xelians.xdh.studio.translation.Label.Translation}} use for form labels translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.studio.translation.Label.Translation}} instances
		 * @param mandatory if the form field is mandatory
		 * @return {{@link InputFormMultiSpec<Boolean>}} specification for input multivalued with boolean values
		 */
		InputFormMultiSpec<Boolean> addInputRadioBoolean(String parameter, Label.Translation label, boolean mandatory);

		/**
		 * Add an input radio for text values. The parameter should be of type String
		 * @param parameter the name fo the parameter should be equal to the parameter name of the constructor
		 * @param label the {{@link fr.xelians.xdh.studio.translation.Label.Translation}} use for form labels translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.studio.translation.Label.Translation}} instances
		 * @param mandatory if the form field is mandatory
		 * @return {{@link InputFormMultiSpec<String>}} specification for input multivalued with text values
		 */
		InputFormMultiSpec<String> addInputRadioText(String parameter, Label.Translation label, boolean mandatory);

		/**
		 * Add an input file. The parameter should be of type Path.
		 * @param parameter the name fo the parameter should be equal to the parameter name of the constructor
		 * @param label the {{@link fr.xelians.xdh.studio.translation.Label.Translation}} use for form labels translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.studio.translation.Label.Translation}} instances
		 * @param mandatory if the form field is mandatory
		 * @return {{@link InputFormFileSpec}} specification for input file
		 */
		InputFormFileSpec addInputFormFile(String parameter, Label.Translation label, boolean mandatory);

		/**
		 * Add an input table. The parameter should be of type boolean List<Map> (correspond to the list of rows, the key of the Map is the parameter name).
		 * @param parameter the name fo the parameter should be equal to the parameter name of the constructor
		 * @param label the {{@link fr.xelians.xdh.studio.translation.Label.Translation}} use for form labels translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.studio.translation.Label.Translation}} instances
		 * @param mandatory if the form field is mandatory
		 * @return {{@link InputFormTableSpec}} specification for input table
		 */
		InputFormTableSpec addInputFormTable(String parameter, Label.Translation label, boolean mandatory);

		/**
		 * Add an input toggle. The parameter should be of type boolean
		 * @param parameter the name fo the parameter should be equal to the parameter name of the constructor
		 * @param label the {{@link fr.xelians.xdh.studio.translation.Label.Translation}} use for form labels translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.studio.translation.Label.Translation}} instances
		 * @param mandatory if the form field is mandatory
		 * @return {{@link InputFormToggleSpec}} specification for input toggle
		 */
		InputFormToggleSpec addInputFormToggle(String parameter, Label.Translation label, boolean mandatory);

		/**
		 * Add an input textarea. The parameter should be of type string
		 * @param parameter the name fo the parameter should be equal to the parameter name of the constructor
		 * @param label the {{@link fr.xelians.xdh.studio.translation.Label.Translation}} use for form labels translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.studio.translation.Label.Translation}} instances
		 * @param mandatory if the form field is mandatory
		 * @return {{@link InputTextAreaSpec}} specification for input textarea
		 */
		InputTextAreaSpec addInputFormTextArea(String parameter, Label.Translation label, boolean mandatory);

		/**
		 * Build the form and get an instance of type {{@link Form}}
		 * @return the {{@link Form}}
		 */
		Form build();

	}

	/**
	 * Interface for multivalued input specification
	 */
	public interface InputFormMultiSpec<T> extends InputFormBaseSpec{

		/**
		 * Add a list of available values for the field
		 * @param choices the list of values
		 * @return {{@link InputFormMultiSpec}}
		 */
		InputFormMultiSpec withChoices(List<MultiValueChoice.Choice<T>> choices);

		/**
		 * Add an information text for the input field
		 * @param information the {{@link fr.xelians.xdh.studio.translation.Label.Translation}} use for information translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.studio.translation.Label.Translation}} instances
		 * @return {{@link InputFormMultiSpec}}
		 */
		InputFormMultiSpec withInformation(Label.Translation information);


	}

	/**
	 * Interface for input text specification
	 */
	public interface InputTextSpec<T> extends InputFormBaseSpec{

		/**
		 * Add min value constraint
		 * @param value the min value
		 * @return {{@link InputTextSpec}}
		 */
		InputTextSpec withMinValue(T value);

		/**
		 * Add max value constraint
		 * @param value the max value
		 * @return {{@link InputTextSpec}}
		 */
		InputTextSpec withMaxValue(T value);

		/**
		 * Add an information
		 * @param information the {{@link fr.xelians.xdh.studio.translation.Label.Translation}} use for information translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.studio.translation.Label.Translation}} instances
		 * @return {{@link InputTextSpec}}
		 */
		InputTextSpec withInformation(Label.Translation information);

		/**
		 * Add a placeholder
		 * @param placeholder the {{@link fr.xelians.xdh.studio.translation.Label.Translation}} use for placeholder translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.studio.translation.Label.Translation}} instances
		 * @return {{@link InputTextSpec}}
		 */
		InputTextSpec withPlaceholder(Label.Translation placeholder);

		/**
		 * Obfuscate the field
		 * @return {{@link InputTextSpec}}
		 */
		InputTextSpec obfuscate();

	}

	/**
	 * Interface for input textarea specification
	 */
	public interface InputTextAreaSpec extends InputFormBaseSpec{

		/**
		 * Add an information
		 * @param information the {{@link fr.xelians.xdh.studio.translation.Label.Translation}} use for information translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.studio.translation.Label.Translation}} instances
		 * @return {{@link InputTextAreaSpec}}
		 */
		InputTextAreaSpec withInformation(Label.Translation information);

		/**
		 * Add a placeholder
		 * @param placeholder the {{@link fr.xelians.xdh.studio.translation.Label.Translation}} use for placeholder translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.studio.translation.Label.Translation}} instances
		 * @return {{@link InputTextAreaSpec}}
		 */
		InputTextAreaSpec withPlaceholder(Label.Translation placeholder);

		/**
		 * Obfuscate the field
		 * @return {{@link InputTextAreaSpec}}
		 */
		InputTextAreaSpec obfuscate();
	}

	/**
	 * Interface for input file specification
	 */
	public interface InputFormFileSpec extends InputFormBaseSpec{

		/**
		 * Add file extension constraint
		 * @param fileExtensions list of file extensions exemple:".jpg"
		 * @return {{@link InputFormFileSpec}}
		 */
		InputFormFileSpec withFileExtensions(List<String> fileExtensions);

		/**
		 * Add an information
		 * @param information the {{@link fr.xelians.xdh.studio.translation.Label.Translation}} use for information translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.studio.translation.Label.Translation}} instances
		 * @return {{@link InputFormFileSpec}}
		 */
		InputFormFileSpec withInformation(Label.Translation information);

	}

	/**
	 * Interface for input multi (checkboxes, selects, radios) specification for table field
	 */
	public interface InputFormMultiTableSpec{

		/**
		 * Add a list of available values for the field
		 * @param choices the list of values
		 * @return {{@link InputFormMultiTableSpec}}
		 */
		InputFormMultiTableSpec withChoices(List<WorkerForm.MultiValueChoice.Choice<String>> choices);

		/**
		 * Effectively add the field
		 * @return {{@link InputFormTableSpec}}
		 */
		InputFormTableSpec and();
	}

	/**
	 * Interface for input text specification for table field
	 */
	public interface InputTextTableSpec{

		/**
		 * Add min value constraint
		 * @param value the min value
		 * @return {{@link InputTextTableSpec}}
		 */
		InputTextTableSpec withMinValue(String value);

		/**
		 * Add max value constraint
		 * @param value the max value
		 * @return {{@link InputTextTableSpec}}
		 */
		InputTextTableSpec withMaxValue(String value);

		/**
		 * Effectively add the field
		 * @return {{@link InputFormTableSpec}}
		 */
		InputFormTableSpec and();
	}

	/**
	 * Interface for input toggle specification for table field
	 */
	public interface InputFormToggleTableSpec{

		/**
		 * Effectively add the field
		 * @return {{@link InputFormTableSpec}}
		 */
		InputFormTableSpec and();
	}

	/**
	 * Interface for table field specification
	 */
	public interface InputFormTableSpec extends InputFormBaseSpec{

		/**
		 * Add an input text for text values. The value of the corresponding key will be of type String
		 * @param parameter the name fo the parameter should be equal to the parameter name of the constructor
		 * @param label the {{@link fr.xelians.xdh.studio.translation.Label.Translation}} use for form labels translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.studio.translation.Label.Translation}} instances
		 * @param mandatory if the form field is mandatory
		 * @return {{@link InputTextTableSpec<String>}} specification for input text with text values
		 */
		InputTextTableSpec addInputTextText(String parameter, Label.Translation label, boolean mandatory);

		/**
		 * Add an input select for text values. The value of the corresponding key will be of type String
		 * @param parameter the name fo the parameter should be equal to the parameter name of the constructor
		 * @param label the {{@link fr.xelians.xdh.studio.translation.Label.Translation}} use for form labels translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.studio.translation.Label.Translation}} instances
		 * @param mandatory if the form field is mandatory
		 * @return {{@link InputFormMultiTableSpec<String>}} specification for input multivalued
		 */
		InputFormMultiTableSpec addInputSelectText(String parameter, Label.Translation label, boolean mandatory);

		/**
		 * Add an input toggle. The value of the corresponding key will be of type Boolean
		 * @param parameter the name fo the parameter should be equal to the parameter name of the constructor
		 * @param label the {{@link fr.xelians.xdh.studio.translation.Label.Translation}} use for form labels translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.studio.translation.Label.Translation}} instances
		 * @param mandatory if the form field is mandatory
		 * @return {{@link InputTextTableSpec<String>}} specification for input text with text values
		 */
		InputFormToggleTableSpec addInputFormToggle(String parameter, Label.Translation label, boolean mandatory);

		/**
		 * Add an information
		 * @param information the {{@link fr.xelians.xdh.studio.translation.Label.Translation}} use for information translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.studio.translation.Label.Translation}} instances
		 * @return {{@link InputFormTableSpec}}
		 */
		InputFormTableSpec withInformation(Label.Translation information);

		/**
		 * Add a Label for the table field in the open state.
		 * @param tableLabel the {{@link fr.xelians.xdh.studio.translation.Label.Translation}} use for information translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.studio.translation.Label.Translation}} instances
		 * @return {{@link InputFormTableSpec}}
		 */
		InputFormTableSpec withTableLabel(Label.Translation tableLabel);

	}

	/**
	 * Base interface of input form specification
	 */
	public interface InputFormBaseSpec{

		/**
		 * Effectively add the input form
		 * @return the form builder {{@link Builder}}
		 */
		Builder and();

	}

	/**
	 * Interface for input form toggle specification
	 */
	public interface InputFormToggleSpec extends InputFormBaseSpec{

		/**
		 * Add an information
		 * @param information the {{@link fr.xelians.xdh.studio.translation.Label.Translation}} use for information translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.studio.translation.Label.Translation}} instances
		 * @return {{@link InputFormToggleSpec}}
		 */
		InputFormToggleSpec withInformation(Label.Translation information);
	}

	/**
	 * Interface for forms
	 */
	public interface Form {

		/**
		 * Get input forms
		 * @return list of {{@link InputFormBase}}
		 */
		List<InputFormBase> getInputForms();
	}

	/**
	 * Interface for input forms with multiple values constraint
	 */
	public interface InputFormMulti<T> extends InputFormBaseValue {

		/**
		 * Get the list of values available
		 * @return list of values
		 */
		List<WorkerForm.MultiValueChoice.Choice<T>> getChoices();

	}

	/**
	 * Interface for input form file
	 */
	public interface InputFormFile extends InputFormBase {

		/**
		 * Get authorized file extensions
		 * @return List of file extension
		 */
		List<String> getFileExtensions();
	}

	/**
	 * Interface for input form toggle
	 */
	public interface InputFormToggle extends InputFormBase {

	}

	/**
	 * Interface for input form table
	 */
	public interface InputFormTable extends InputFormBase {

		/**
		 * Get the input form columns of the table
		 * @return {{@link InputFormBase}}
		 */
		List<InputFormBase> getInputFormColumns();

		/**
		 * Get the table Label
		 * @return the {{@link fr.xelians.xdh.studio.translation.Label.Translation}} use for form labels translation.
		 */
		Label.Translation getTableLabel();
	}

	/**
	 * Interface for input form field with unique value
	 */
	public interface InputFormUnique<T> extends InputFormBaseValue {

		/**
		 * Get the constraint min value
		 * @return the min value
		 */
		T getMin();

		/**
		 * Get the constraint max value
		 * @return the max value
		 */
		T getMax();

		/**
		 * If the field is obfuscate (like password fields)
		 * @return is obfuscate
		 */
		boolean isObfuscate();

		/**
		 * The placeholder of the input form
		 * @return the {{@link fr.xelians.xdh.studio.translation.Label.Translation}} use for form labels translation.
		 */
		Label.Translation getPlaceholder();
	}

	/**
	 * Interface for input form field of type textArea
	 */
	public interface InputFormTextArea extends InputFormBase {

		/**
		 * The placeholder of the input form
		 * @return the {{@link fr.xelians.xdh.studio.translation.Label.Translation}} use for form labels translation.
		 */
		Label.Translation getPlaceholder();

		/**
		 * If the field is obfuscate (like password fields)
		 * @return is obfuscate
		 */
		boolean isObfuscate();
	}

	/**
	 * Input form base interface for value fields
	 */
	public interface InputFormBaseValue extends InputFormBase{

		/**
		 * Get the input value type
		 * @return {{@link InputValueType}}
		 */
		InputValueType getInputValueType();

	}

	/**
	 * Input form base interface
	 */
	public interface InputFormBase {

		/**
		 * The input form type
		 * @return {{@link InputFormType}}
		 */
		InputFormType getInputFormType();

		/**
		 * The label of the input form
		 * @return the {{@link fr.xelians.xdh.studio.translation.Label.Translation}} use for form labels translation.
		 */
		Label.Translation getLabel();

		/**
		 * get the parameter name
		 * @return the parameter name
		 */
		String getParameter();

		/**
		 * If the field is mandatory
		 * @return is mandatory
		 */
		boolean isMandatory();

		/**
		 * Get the information of the field
		 * @return the {{@link fr.xelians.xdh.studio.translation.Label.Translation}} use for form labels translation.
		 */
		Label.Translation getInformation();
	}

	/**
	 * The class MultiValueChoice is used for multi forms to have the value and her translate label
	 */
	public final class MultiValueChoice {

		private MultiValueChoice (){
		}

		/**
		 * Create a Choice object
		 * @param label the translation label
		 * @param value the value
		 * @return {{@link Choice}}
		 */
		public static <T> Choice of(Label.Translation label, T value) { return new Choice(label, value); }

		/**
		 * Create a Choice object
		 * label Choice's param is created automatically with the value as french label and english label
		 * @param value the value
		 * @return {{@link Choice}}
		 */
		public static <T> Choice of(T value) { return new Choice<T>(value); }

		public static class Choice<T> {
			private Label.Translation label;
			private T value;

			private Choice(Label.Translation label, T value){
				this.label = label;
				this.value = value;
			}

			private Choice(T value){
				this.value = value;
				this.label = Label.of(value.toString(),value.toString());
			}

			public Label.Translation getLabel() { return label; }

			public T getValue() { return value; }
		}
	}

}
