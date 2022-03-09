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

		Form build();

	}

	public interface InputFormMultiSpec<T>{
		InputFormMultiSpec withValues(List<T> values);
		Builder and();
	}


	public interface InputTextSpec<T>{
		InputTextSpec withMinValue(T value);
		InputTextSpec withMaxValue(T value);
		Builder and();
	}

	public interface InputFormFileSpec {
		InputFormFileSpec withFileExtensions(List<String> fileExtensions);
		Builder and();
	}

	public interface Form {
		List<InputFormBase> getInputForms();
	}

	public interface InputFormMulti<T> extends InputFormBase {
		public List<T> getValues();
	}

	public interface InputFormFile extends InputFormBase {
		public List<String> getFileExtensions();
	}

	public interface InputFormUnique<T> extends InputFormBase {
		T getMin();
		T getMax();
	}

	public interface InputFormBase {
		InputFormType getInputFormType();
		InputValueType getInputValueType();
		String getLabel();
		String getParameter();
		boolean isMandatory();
	}
}
