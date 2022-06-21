package fr.xelians.xdh.plugin.form;

import fr.xelians.xdh.plugin.translation.Label;

import java.util.List;

/**
 * @author aurelien on 08/03/2022
 *
 * This class contain all the interfaces for builders and form components
 */
public class  WorkerForm {

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
		 * Add an input text for integer values
		 * @param parameter the name fo the parameter should be equal to the parameter name of the constructor
		 * @param label the {{@link fr.xelians.xdh.plugin.translation.Label.Translation}} use for form labels translation
		 * @param mandatory if the form field is mandatory
		 * @return {{@link InputTextSpec<Integer>}} specification for input text with integer values
		 */
		InputTextSpec<Integer> addInputTextInt(String parameter, Label.Translation label, boolean mandatory);

		/**
		 * Add an input text for double precision values
		 * @param parameter the name fo the parameter should be equal to the parameter name of the constructor
		 * @param label the {{@link fr.xelians.xdh.plugin.translation.Label.Translation}} use for form labels translation
		 * @param mandatory if the form field is mandatory
		 * @return {{@link InputTextSpec<Double>}} specification for input text with double precision values
		 */
		InputTextSpec<Double> addInputTextDouble(String parameter, Label.Translation label, boolean mandatory);

		/**
		 * Add an input text for float values
		 * @param parameter the name fo the parameter should be equal to the parameter name of the constructor
		 * @param label the {{@link fr.xelians.xdh.plugin.translation.Label.Translation}} use for form labels translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.plugin.translation.Label.Translation}} instances
		 * @param mandatory if the form field is mandatory
		 * @return {{@link InputTextSpec<Float>}} specification for input text with float values
		 */
		InputTextSpec<Float> addInputTextFloat(String parameter, Label.Translation label, boolean mandatory);

		/**
		 * Add an input text for long values
		 * @param parameter the name fo the parameter should be equal to the parameter name of the constructor
		 * @param label the {{@link fr.xelians.xdh.plugin.translation.Label.Translation}} use for form labels translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.plugin.translation.Label.Translation}} instances
		 * @param mandatory if the form field is mandatory
		 * @return {{@link InputTextSpec<Long>}} specification for input text with long values
		 */
		InputTextSpec<Long> addInputTextLong(String parameter, Label.Translation label, boolean mandatory);

		/**
		 * Add an input text for Boolean values
		 * @param parameter the name fo the parameter should be equal to the parameter name of the constructor
		 * @param label the {{@link fr.xelians.xdh.plugin.translation.Label.Translation}} use for form labels translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.plugin.translation.Label.Translation}} instances
		 * @param mandatory if the form field is mandatory
		 * @return {{@link InputTextSpec<Boolean>}} specification for input text with long values
		 */
		InputTextSpec<Boolean> addInputTextBoolean(String parameter, Label.Translation label, boolean mandatory);

		/**
		 * Add an input text for text values
		 * @param parameter the name fo the parameter should be equal to the parameter name of the constructor
		 * @param label the {{@link fr.xelians.xdh.plugin.translation.Label.Translation}} use for form labels translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.plugin.translation.Label.Translation}} instances
		 * @param mandatory if the form field is mandatory
		 * @return {{@link InputTextSpec<String>}} specification for input text with text values
		 */
		InputTextSpec<String> addInputTextText(String parameter, Label.Translation label, boolean mandatory);

		/**
		 * Add an input select for integer values
		 * @param parameter the name fo the parameter should be equal to the parameter name of the constructor
		 * @param label the {{@link fr.xelians.xdh.plugin.translation.Label.Translation}} use for form labels translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.plugin.translation.Label.Translation}} instances
		 * @param mandatory if the form field is mandatory
		 * @return {{@link InputFormMultiSpec<Integer>}} specification for input multivalued with integer values
		 */
		InputFormMultiSpec<Integer> addInputSelectInt(String parameter, Label.Translation label, boolean mandatory);

		/**
		 * Add an input select for double precision values
		 * @param parameter the name fo the parameter should be equal to the parameter name of the constructor
		 * @param label the {{@link fr.xelians.xdh.plugin.translation.Label.Translation}} use for form labels translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.plugin.translation.Label.Translation}} instances
		 * @param mandatory if the form field is mandatory
		 * @return {{@link InputFormMultiSpec<Double>}} specification for input multivalued with double precision values
		 */
		InputFormMultiSpec<Double> addInputSelectDouble(String parameter, Label.Translation label, boolean mandatory);

		/**
		 * Add an input select for float values
		 * @param parameter the name fo the parameter should be equal to the parameter name of the constructor
		 * @param label the {{@link fr.xelians.xdh.plugin.translation.Label.Translation}} use for form labels translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.plugin.translation.Label.Translation}} instances
		 * @param mandatory if the form field is mandatory
		 * @return {{@link InputFormMultiSpec<Float>}} specification for input multivalued with float values
		 */
		InputFormMultiSpec<Float> addInputSelectFloat(String parameter, Label.Translation label, boolean mandatory);

		/**
		 * Add an input select for long values
		 * @param parameter the name fo the parameter should be equal to the parameter name of the constructor
		 * @param label the {{@link fr.xelians.xdh.plugin.translation.Label.Translation}} use for form labels translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.plugin.translation.Label.Translation}} instances
		 * @param mandatory if the form field is mandatory
		 * @return {{@link InputFormMultiSpec<Long>}} specification for input multivalued with long values
		 */
		InputFormMultiSpec<Long> addInputSelectLong(String parameter, Label.Translation label, boolean mandatory);

		/**
		 * Add an input select for boolean values
		 * @param parameter the name fo the parameter should be equal to the parameter name of the constructor
		 * @param label the {{@link fr.xelians.xdh.plugin.translation.Label.Translation}} use for form labels translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.plugin.translation.Label.Translation}} instances
		 * @param mandatory if the form field is mandatory
		 * @return {{@link InputFormMultiSpec<Boolean>}} specification for input multivalued with boolean values
		 */
		InputFormMultiSpec<Boolean> addInputSelectBoolean(String parameter, Label.Translation label, boolean mandatory);

		/**
		 * Add an input select for text values
		 * @param parameter the name fo the parameter should be equal to the parameter name of the constructor
		 * @param label the {{@link fr.xelians.xdh.plugin.translation.Label.Translation}} use for form labels translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.plugin.translation.Label.Translation}} instances
		 * @param mandatory if the form field is mandatory
		 * @return {{@link InputFormMultiSpec<String>}} specification for input multivalued with text values
		 */
		InputFormMultiSpec<String> addInputSelectText(String parameter, Label.Translation label, boolean mandatory);

		/**
		 * Add an input checkbox for integer values
		 * @param parameter the name fo the parameter should be equal to the parameter name of the constructor
		 * @param label the {{@link fr.xelians.xdh.plugin.translation.Label.Translation}} use for form labels translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.plugin.translation.Label.Translation}} instances
		 * @param mandatory if the form field is mandatory
		 * @return {{@link InputFormMultiSpec<Integer>}} specification for input multivalued with integer values
		 */
		InputFormMultiSpec<Integer> addInputCheckboxInt(String parameter, Label.Translation label, boolean mandatory);

		/**
		 * Add an input checkbox for double values
		 * @param parameter the name fo the parameter should be equal to the parameter name of the constructor
		 * @param label the {{@link fr.xelians.xdh.plugin.translation.Label.Translation}} use for form labels translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.plugin.translation.Label.Translation}} instances
		 * @param mandatory if the form field is mandatory
		 * @return {{@link InputFormMultiSpec<Double>}} specification for input multivalued with double values
		 */
		InputFormMultiSpec<Double> addInputCheckboxDouble(String parameter, Label.Translation label, boolean mandatory);

		/**
		 * Add an input checkbox for float values
		 * @param parameter the name fo the parameter should be equal to the parameter name of the constructor
		 * @param label the {{@link fr.xelians.xdh.plugin.translation.Label.Translation}} use for form labels translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.plugin.translation.Label.Translation}} instances
		 * @param mandatory if the form field is mandatory
		 * @return {{@link InputFormMultiSpec<Float>}} specification for input multivalued with float values
		 */
		InputFormMultiSpec<Float> addInputCheckboxFloat(String parameter, Label.Translation label, boolean mandatory);

		/**
		 * Add an input checkbox for long values
		 * @param parameter the name fo the parameter should be equal to the parameter name of the constructor
		 * @param label the {{@link fr.xelians.xdh.plugin.translation.Label.Translation}} use for form labels translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.plugin.translation.Label.Translation}} instances
		 * @param mandatory if the form field is mandatory
		 * @return {{@link InputFormMultiSpec<Long>}} specification for input multivalued with long values
		 */
		InputFormMultiSpec<Long> addInputCheckboxLong(String parameter, Label.Translation label, boolean mandatory);

		/**
		 * Add an input checkbox for boolean values
		 * @param parameter the name fo the parameter should be equal to the parameter name of the constructor
		 * @param label the {{@link fr.xelians.xdh.plugin.translation.Label.Translation}} use for form labels translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.plugin.translation.Label.Translation}} instances
		 * @param mandatory if the form field is mandatory
		 * @return {{@link InputFormMultiSpec<Boolean>}} specification for input multivalued with boolean values
		 */
		InputFormMultiSpec<Boolean> addInputCheckboxBoolean(String parameter, Label.Translation label, boolean mandatory);

		/**
		 * Add an input checkbox for text values
		 * @param parameter the name fo the parameter should be equal to the parameter name of the constructor
		 * @param label the {{@link fr.xelians.xdh.plugin.translation.Label.Translation}} use for form labels translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.plugin.translation.Label.Translation}} instances
		 * @param mandatory if the form field is mandatory
		 * @return {{@link InputFormMultiSpec<String>}} specification for input multivalued with text values
		 */
		InputFormMultiSpec<String> addInputCheckboxText(String parameter, Label.Translation label, boolean mandatory);

		/**
		 * Add an input radio for integer values
		 * @param parameter the name fo the parameter should be equal to the parameter name of the constructor
		 * @param label the {{@link fr.xelians.xdh.plugin.translation.Label.Translation}} use for form labels translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.plugin.translation.Label.Translation}} instances
		 * @param mandatory if the form field is mandatory
		 * @return {{@link InputFormMultiSpec<Integer>}} specification for input multivalued with integer values
		 */
		InputFormMultiSpec<Integer> addInputRadioInt(String parameter, Label.Translation label, boolean mandatory);

		/**
		 * Add an input radio for double values
		 * @param parameter the name fo the parameter should be equal to the parameter name of the constructor
		 * @param label the {{@link fr.xelians.xdh.plugin.translation.Label.Translation}} use for form labels translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.plugin.translation.Label.Translation}} instances
		 * @param mandatory if the form field is mandatory
		 * @return {{@link InputFormMultiSpec<Double>}} specification for input multivalued with double values
		 */
		InputFormMultiSpec<Double> addInputRadioDouble(String parameter, Label.Translation label, boolean mandatory);

		/**
		 * Add an input radio for float values
		 * @param parameter the name fo the parameter should be equal to the parameter name of the constructor
		 * @param label the {{@link fr.xelians.xdh.plugin.translation.Label.Translation}} use for form labels translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.plugin.translation.Label.Translation}} instances
		 * @param mandatory if the form field is mandatory
		 * @return {{@link InputFormMultiSpec<Float>}} specification for input multivalued with float values
		 */
		InputFormMultiSpec<Float> addInputRadioFloat(String parameter, Label.Translation label, boolean mandatory);

		/**
		 * Add an input radio for long values
		 * @param parameter the name fo the parameter should be equal to the parameter name of the constructor
		 * @param label the {{@link fr.xelians.xdh.plugin.translation.Label.Translation}} use for form labels translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.plugin.translation.Label.Translation}} instances
		 * @param mandatory if the form field is mandatory
		 * @return {{@link InputFormMultiSpec<Long>}} specification for input multivalued with long values
		 */
		InputFormMultiSpec<Long> addInputRadioLong(String parameter, Label.Translation label, boolean mandatory);

		/**
		 * Add an input radio for boolean values
		 * @param parameter the name fo the parameter should be equal to the parameter name of the constructor
		 * @param label the {{@link fr.xelians.xdh.plugin.translation.Label.Translation}} use for form labels translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.plugin.translation.Label.Translation}} instances
		 * @param mandatory if the form field is mandatory
		 * @return {{@link InputFormMultiSpec<Boolean>}} specification for input multivalued with boolean values
		 */
		InputFormMultiSpec<Boolean> addInputRadioBoolean(String parameter, Label.Translation label, boolean mandatory);

		/**
		 * Add an input radio for text values
		 * @param parameter the name fo the parameter should be equal to the parameter name of the constructor
		 * @param label the {{@link fr.xelians.xdh.plugin.translation.Label.Translation}} use for form labels translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.plugin.translation.Label.Translation}} instances
		 * @param mandatory if the form field is mandatory
		 * @return {{@link InputFormMultiSpec<String>}} specification for input multivalued with text values
		 */
		InputFormMultiSpec<String> addInputRadioText(String parameter, Label.Translation label, boolean mandatory);

		/**
		 * Add an input file
		 * @param parameter the name fo the parameter should be equal to the parameter name of the constructor
		 * @param label the {{@link fr.xelians.xdh.plugin.translation.Label.Translation}} use for form labels translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.plugin.translation.Label.Translation}} instances
		 * @param mandatory if the form field is mandatory
		 * @return {{@link InputFormFileSpec}} specification for input file
		 */
		InputFormFileSpec addInputFormFile(String parameter, Label.Translation label, boolean mandatory);

		/**
		 * Add an input table
		 * @param parameter the name fo the parameter should be equal to the parameter name of the constructor
		 * @param label the {{@link fr.xelians.xdh.plugin.translation.Label.Translation}} use for form labels translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.plugin.translation.Label.Translation}} instances
		 * @param mandatory if the form field is mandatory
		 * @return {{@link InputFormTableSpec}} specification for input table
		 */
		InputFormTableSpec addInputFormTable(String parameter, Label.Translation label, boolean mandatory);

		/**
		 * Add an input toggle the parameter should be of type boolean
		 * @param parameter the name fo the parameter should be equal to the parameter name of the constructor
		 * @param label the {{@link fr.xelians.xdh.plugin.translation.Label.Translation}} use for form labels translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.plugin.translation.Label.Translation}} instances
		 * @param mandatory if the form field is mandatory
		 * @return {{@link InputFormToggleSpec}} specification for input toggle
		 */
		InputFormToggleSpec addInputFormToggle(String parameter, Label.Translation label, boolean mandatory);

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
		 * @param values the list of values
		 * @return {{@link InputFormMultiSpec}}
		 */
		InputFormMultiSpec withValues(List<T> values);

		/**
		 * Add an information text for the input field
		 * @param information the {{@link fr.xelians.xdh.plugin.translation.Label.Translation}} use for information translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.plugin.translation.Label.Translation}} instances
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
		 * @param information the {{@link fr.xelians.xdh.plugin.translation.Label.Translation}} use for information translation. Use {{@link Label#of(String, String)}} to get a {{@link fr.xelians.xdh.plugin.translation.Label.Translation}} instances
		 * @return {{@link InputTextSpec}}
		 */
		InputTextSpec withInformation(Label.Translation information);

		/**
		 * Obfuscate the field
		 * @return {{@link InputTextSpec}}
		 */
		InputTextSpec obfuscate();

	}

	public interface InputFormFileSpec extends InputFormBaseSpec{
		InputFormFileSpec withFileExtensions(List<String> fileExtensions);
		InputFormFileSpec withInformation(Label.Translation information);

	}

	public interface InputFormMultiTableSpec{
		InputFormMultiTableSpec withValues(List<String> values);
		InputFormTableSpec and();
	}

	public interface InputTextTableSpec{
		InputTextTableSpec withMinValue(String value);
		InputTextTableSpec withMaxValue(String value);
		InputFormTableSpec and();
	}

	public interface InputFormToggleTableSpec{
		InputFormTableSpec and();
	}

	public interface InputFormTableSpec extends InputFormBaseSpec{
		InputTextTableSpec addInputTextText(String parameter, Label.Translation label, boolean mandatory);
		InputFormMultiTableSpec addInputSelectText(String parameter, Label.Translation label, boolean mandatory);
		InputFormToggleTableSpec addInputFormToggle(String parameter, Label.Translation label, boolean mandatory);
		InputFormTableSpec withInformation(Label.Translation information);
		InputFormTableSpec withTableLabel(Label.Translation tableLabel);

	}

	public interface InputFormBaseSpec{

		Builder and();

	}

	public interface InputFormToggleSpec extends InputFormBaseSpec{
		InputFormToggleSpec withInformation(Label.Translation information);
	}

	public interface Form {
		List<InputFormBase> getInputForms();
	}

	public interface InputFormMulti<T> extends InputFormBaseValue {
		List<T> getValues();
	}

	public interface InputFormFile extends InputFormBase {
		List<String> getFileExtensions();
	}

	public interface InputFormToggle extends InputFormBase {

	}

	public interface InputFormTable extends InputFormBase {
		List<InputFormBase> getInputFormColumns();
		Label.Translation getTableLabel();
	}

	public interface InputFormUnique<T> extends InputFormBaseValue {
		T getMin();
		T getMax();
		boolean isObfuscate();
	}

	public interface InputFormBaseValue extends InputFormBase{
		InputValueType getInputValueType();
	}

	public interface InputFormBase {
		InputFormType getInputFormType();
		Label.Translation getLabel();
		String getParameter();
		boolean isMandatory();
		Label.Translation getInformation();
	}

}
