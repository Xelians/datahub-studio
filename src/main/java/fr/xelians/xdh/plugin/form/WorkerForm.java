package fr.xelians.xdh.plugin.form;

import java.util.List;

/**
 * @author aurelien on 08/03/2022
 */
public class WorkerForm {

	public static Builder builder(){
		return new WorkerFormBuilder();
	}

	public interface Builder{

		InputTextSpec<Integer> addInputTextInt(String parameter, String label, boolean mandatory);
		InputTextSpec<Double> addInputTextDouble(String parameter, String label, boolean mandatory);
		InputTextSpec<Float> addInputTextFloat(String parameter, String label, boolean mandatory);
		InputTextSpec<Long> addInputTextLong(String parameter, String label, boolean mandatory);
		InputTextSpec<Boolean> addInputTextBoolean(String parameter, String label, boolean mandatory);
		InputTextSpec<String> addInputTextText(String parameter, String label, boolean mandatory);

		InputFormMultiSpec<Integer> addInputSelectInt(String parameter, String label, boolean mandatory);
		InputFormMultiSpec<Double> addInputSelectDouble(String parameter, String label, boolean mandatory);
		InputFormMultiSpec<Float> addInputSelectFloat(String parameter, String label, boolean mandatory);
		InputFormMultiSpec<Long> addInputSelectLong(String parameter, String label, boolean mandatory);
		InputFormMultiSpec<Boolean> addInputSelectBoolean(String parameter, String label, boolean mandatory);
		InputFormMultiSpec<String> addInputSelectText(String parameter, String label, boolean mandatory);

		InputFormMultiSpec<Integer> addInputCheckboxInt(String parameter, String label, boolean mandatory);
		InputFormMultiSpec<Double> addInputCheckboxDouble(String parameter, String label, boolean mandatory);
		InputFormMultiSpec<Float> addInputCheckboxFloat(String parameter, String label, boolean mandatory);
		InputFormMultiSpec<Long> addInputCheckboxLong(String parameter, String label, boolean mandatory);
		InputFormMultiSpec<Boolean> addInputCheckboxBoolean(String parameter, String label, boolean mandatory);
		InputFormMultiSpec<String> addInputCheckboxText(String parameter, String label, boolean mandatory);

		InputFormMultiSpec<Integer> addInputRadioInt(String parameter, String label, boolean mandatory);
		InputFormMultiSpec<Double> addInputRadioDouble(String parameter, String label, boolean mandatory);
		InputFormMultiSpec<Float> addInputRadioFloat(String parameter, String label, boolean mandatory);
		InputFormMultiSpec<Long> addInputRadioLong(String parameter, String label, boolean mandatory);
		InputFormMultiSpec<Boolean> addInputRadioBoolean(String parameter, String label, boolean mandatory);
		InputFormMultiSpec<String> addInputRadioText(String parameter, String label, boolean mandatory);

		InputFormFileSpec addInputFormFile(String parameter, String label, boolean mandatory);
		InputFormTableSpec addInputFormTable(String parameter, String label, boolean mandatory);
		InputFormToggleSpec addInputFormToggle(String parameter, String label, boolean mandatory);

		Form build();

	}

	public interface InputFormMultiSpec<T> extends InputFormBaseSpec{
		InputFormMultiSpec withValues(List<T> values);
		InputFormMultiSpec withInformation(String information);

	}

	public interface InputTextSpec<T> extends InputFormBaseSpec{
		InputTextSpec withMinValue(T value);
		InputTextSpec withMaxValue(T value);
		InputTextSpec withInformation(String information);
		InputTextSpec obfuscate();

	}

	public interface InputFormFileSpec extends InputFormBaseSpec{
		InputFormFileSpec withFileExtensions(List<String> fileExtensions);
		InputFormFileSpec withInformation(String information);

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
		InputTextTableSpec addInputTextText(String parameter, String label, boolean mandatory);
		InputFormMultiTableSpec addInputSelectText(String parameter, String label, boolean mandatory);
		InputFormToggleTableSpec addInputFormToggle(String parameter, String label, boolean mandatory);
		InputFormTableSpec withInformation(String information);
		InputFormTableSpec withTableLabel(String tableLabel);

	}

	public interface InputFormBaseSpec{

		Builder and();

	}

	public interface InputFormToggleSpec extends InputFormBaseSpec{
		InputFormToggleSpec withInformation(String information);
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
		String getTableLabel();
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
		String getLabel();
		String getParameter();
		boolean isMandatory();
		String getInformation();
	}

}
