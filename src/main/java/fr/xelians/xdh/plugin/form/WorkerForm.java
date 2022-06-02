package fr.xelians.xdh.plugin.form;

import fr.xelians.xdh.plugin.translation.Label;

import java.util.List;

/**
 * @author aurelien on 08/03/2022
 */
public class  WorkerForm {

	public static Builder builder(){
		return new WorkerFormBuilder();
	}

	public interface Builder{

		InputTextSpec<Integer> addInputTextInt(String parameter, Label.Translation label, boolean mandatory);
		InputTextSpec<Double> addInputTextDouble(String parameter, Label.Translation label, boolean mandatory);
		InputTextSpec<Float> addInputTextFloat(String parameter, Label.Translation label, boolean mandatory);
		InputTextSpec<Long> addInputTextLong(String parameter, Label.Translation label, boolean mandatory);
		InputTextSpec<Boolean> addInputTextBoolean(String parameter, Label.Translation label, boolean mandatory);
		InputTextSpec<String> addInputTextText(String parameter, Label.Translation label, boolean mandatory);

		InputFormMultiSpec<Integer> addInputSelectInt(String parameter, Label.Translation label, boolean mandatory);
		InputFormMultiSpec<Double> addInputSelectDouble(String parameter, Label.Translation label, boolean mandatory);
		InputFormMultiSpec<Float> addInputSelectFloat(String parameter, Label.Translation label, boolean mandatory);
		InputFormMultiSpec<Long> addInputSelectLong(String parameter, Label.Translation label, boolean mandatory);
		InputFormMultiSpec<Boolean> addInputSelectBoolean(String parameter, Label.Translation label, boolean mandatory);
		InputFormMultiSpec<String> addInputSelectText(String parameter, Label.Translation label, boolean mandatory);

		InputFormMultiSpec<Integer> addInputCheckboxInt(String parameter, Label.Translation label, boolean mandatory);
		InputFormMultiSpec<Double> addInputCheckboxDouble(String parameter, Label.Translation label, boolean mandatory);
		InputFormMultiSpec<Float> addInputCheckboxFloat(String parameter, Label.Translation label, boolean mandatory);
		InputFormMultiSpec<Long> addInputCheckboxLong(String parameter, Label.Translation label, boolean mandatory);
		InputFormMultiSpec<Boolean> addInputCheckboxBoolean(String parameter, Label.Translation label, boolean mandatory);
		InputFormMultiSpec<String> addInputCheckboxText(String parameter, Label.Translation label, boolean mandatory);

		InputFormMultiSpec<Integer> addInputRadioInt(String parameter, Label.Translation label, boolean mandatory);
		InputFormMultiSpec<Double> addInputRadioDouble(String parameter, Label.Translation label, boolean mandatory);
		InputFormMultiSpec<Float> addInputRadioFloat(String parameter, Label.Translation label, boolean mandatory);
		InputFormMultiSpec<Long> addInputRadioLong(String parameter, Label.Translation label, boolean mandatory);
		InputFormMultiSpec<Boolean> addInputRadioBoolean(String parameter, Label.Translation label, boolean mandatory);
		InputFormMultiSpec<String> addInputRadioText(String parameter, Label.Translation label, boolean mandatory);

		InputFormFileSpec addInputFormFile(String parameter, Label.Translation label, boolean mandatory);
		InputFormTableSpec addInputFormTable(String parameter, Label.Translation label, boolean mandatory);
		InputFormToggleSpec addInputFormToggle(String parameter, Label.Translation label, boolean mandatory);

		Form build();

	}

	public interface InputFormMultiSpec<T> extends InputFormBaseSpec{
		InputFormMultiSpec withValues(List<T> values);
		InputFormMultiSpec withInformation(Label.Translation information);

	}

	public interface InputTextSpec<T> extends InputFormBaseSpec{
		InputTextSpec withMinValue(T value);
		InputTextSpec withMaxValue(T value);
		InputTextSpec withInformation(Label.Translation information);
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
