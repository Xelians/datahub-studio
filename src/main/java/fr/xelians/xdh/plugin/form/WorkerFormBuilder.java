package fr.xelians.xdh.plugin.form;

import java.util.ArrayList;
import java.util.List;

/**
 * @author aurelien on 04/03/2022
 */
public final class WorkerFormBuilder implements WorkerForm.Builder{

	private List<WorkerForm.InputFormBase> inputForms = new ArrayList<>();

	protected WorkerFormBuilder(){}

	public WorkerForm.Form build(){
		return new Form(inputForms);
	}

	@Override
	public WorkerForm.InputTextSpec<Integer> addInputTextInt(String parameter, String label, boolean mandatory){
		return new InputTextSpec(InputValueType.INTEGER, parameter, label, mandatory, this);
	}

	@Override
	public WorkerForm.InputTextSpec<Double> addInputTextDouble(String parameter, String label, boolean mandatory) {
		return new InputTextSpec(InputValueType.DOUBLE, parameter, label, mandatory, this);
	}

	@Override
	public WorkerForm.InputTextSpec<Float> addInputTextFloat(String parameter, String label, boolean mandatory) {
		return new InputTextSpec(InputValueType.FLOAT, parameter, label, mandatory, this);
	}

	@Override
	public WorkerForm.InputTextSpec<Long> addInputTextLong(String parameter, String label, boolean mandatory) {
		return new InputTextSpec(InputValueType.LONG, parameter, label, mandatory, this);
	}

	@Override
	public WorkerForm.InputTextSpec<Boolean> addInputTextBoolean(String parameter, String label, boolean mandatory) {
		return new InputTextSpec(InputValueType.BOOLEAN, parameter, label, mandatory, this);
	}

	@Override
	public WorkerForm.InputTextSpec<String> addInputTextText(String parameter, String label, boolean mandatory) {
		return new InputTextSpec(InputValueType.TEXT, parameter, label, mandatory, this);
	}

	@Override
	public WorkerForm.InputFormMultiSpec<Integer> addInputSelectInt(String parameter, String label, boolean mandatory) {
		return new InputFormSelectSpec(InputValueType.INTEGER, parameter, label, mandatory, this);
	}

	@Override
	public WorkerForm.InputFormMultiSpec<Double> addInputSelectDouble(String parameter, String label, boolean mandatory) {
		return new InputFormSelectSpec(InputValueType.DOUBLE, parameter, label, mandatory, this);
	}

	@Override
	public WorkerForm.InputFormMultiSpec<Float> addInputSelectFloat(String parameter, String label, boolean mandatory) {
		return new InputFormSelectSpec(InputValueType.FLOAT, parameter, label, mandatory, this);
	}

	@Override
	public WorkerForm.InputFormMultiSpec<Long> addInputSelectLong(String parameter, String label, boolean mandatory) {
		return new InputFormSelectSpec(InputValueType.LONG, parameter, label, mandatory, this);
	}

	@Override
	public WorkerForm.InputFormMultiSpec<Boolean> addInputSelectBoolean(String parameter, String label, boolean mandatory) {
		return new InputFormSelectSpec(InputValueType.BOOLEAN, parameter, label, mandatory, this);
	}

	@Override
	public WorkerForm.InputFormMultiSpec<String> addInputSelectText(String parameter, String label, boolean mandatory) {
		return new InputFormSelectSpec(InputValueType.TEXT, parameter, label, mandatory, this);
	}

	@Override
	public WorkerForm.InputFormMultiSpec<Integer> addInputCheckboxInt(String parameter, String label, boolean mandatory) {
		return new InputFormCheckboxSpec(InputValueType.INTEGER, parameter, label, mandatory, this);
	}

	@Override
	public WorkerForm.InputFormMultiSpec<Double> addInputCheckboxDouble(String parameter, String label, boolean mandatory) {
		return new InputFormCheckboxSpec(InputValueType.DOUBLE, parameter, label, mandatory, this);
	}

	@Override
	public WorkerForm.InputFormMultiSpec<Float> addInputCheckboxFloat(String parameter, String label, boolean mandatory) {
		return new InputFormCheckboxSpec(InputValueType.FLOAT, parameter, label, mandatory, this);
	}

	@Override
	public WorkerForm.InputFormMultiSpec<Long> addInputCheckboxLong(String parameter, String label, boolean mandatory) {
		return new InputFormCheckboxSpec(InputValueType.LONG, parameter, label, mandatory, this);
	}

	@Override
	public WorkerForm.InputFormMultiSpec<Boolean> addInputCheckboxBoolean(String parameter, String label, boolean mandatory) {
		return new InputFormCheckboxSpec(InputValueType.BOOLEAN, parameter, label, mandatory, this);
	}

	@Override
	public WorkerForm.InputFormMultiSpec<String> addInputCheckboxText(String parameter, String label, boolean mandatory) {
		return new InputFormCheckboxSpec(InputValueType.TEXT, parameter, label, mandatory, this);
	}

	@Override
	public WorkerForm.InputFormMultiSpec addInputRadioInt(String parameter, String label, boolean mandatory) {
		return new InputFormRadioSpec(InputValueType.INTEGER, parameter, label, mandatory, this);
	}

	@Override
	public WorkerForm.InputFormMultiSpec addInputRadioDouble(String parameter, String label, boolean mandatory) {
		return new InputFormRadioSpec(InputValueType.DOUBLE, parameter, label, mandatory, this);
	}

	@Override
	public WorkerForm.InputFormMultiSpec addInputRadioFloat(String parameter, String label, boolean mandatory) {
		return new InputFormRadioSpec(InputValueType.FLOAT, parameter, label, mandatory, this);
	}

	@Override
	public WorkerForm.InputFormMultiSpec addInputRadioLong(String parameter, String label, boolean mandatory) {
		return new InputFormRadioSpec(InputValueType.LONG, parameter, label, mandatory, this);
	}

	@Override
	public WorkerForm.InputFormMultiSpec addInputRadioBoolean(String parameter, String label, boolean mandatory) {
		return new InputFormRadioSpec(InputValueType.BOOLEAN, parameter, label, mandatory, this);
	}

	@Override
	public WorkerForm.InputFormMultiSpec addInputRadioText(String parameter, String label, boolean mandatory) {
		return new InputFormRadioSpec(InputValueType.TEXT, parameter, label, mandatory, this);
	}

	@Override
	public WorkerForm.InputFormFileSpec addInputFormFile(String parameter, String label, boolean mandatory){
		return new InputFormFileSpec(parameter, label, mandatory, this);
	}

	private WorkerFormBuilder addInput(WorkerForm.InputFormBase inputFormBase){
		inputForms.add(inputFormBase);
		return this;
	}

	private class InputTextSpec<T> implements WorkerForm.InputTextSpec<T>{

		private InputFormUnique<T> inputFormUnique;

		private WorkerFormBuilder builder;

		private InputTextSpec(InputValueType inputValueType, String parameter, String label, boolean mandatory, WorkerFormBuilder builder){
			this.inputFormUnique = new InputFormUnique();
			this.inputFormUnique.setInputFormType(InputFormType.TEXT);
			this.inputFormUnique.setInputValueType(inputValueType);
			this.inputFormUnique.setParameter(parameter);
			this.inputFormUnique.setLabel(label);
			this.inputFormUnique.setMandatory(mandatory);
			this.builder = builder;
		}

		public InputTextSpec<T> withMinValue(T value){
			inputFormUnique.setMin(value);
			return this;
		}

		public InputTextSpec<T> withMaxValue(T value){
			inputFormUnique.setMax(value);
			return this;
		}

		public WorkerFormBuilder and() {
			return builder.addInput(inputFormUnique);
		}
	}

	private class InputFormCheckboxSpec<T> extends InputFormMultiSpec<T> {

		private InputFormCheckboxSpec(InputValueType inputValueType, String parameter, String label, boolean mandatory, WorkerFormBuilder builder) {
			super(inputValueType, parameter, label, mandatory, builder);
			this.inputFormMulti.setInputFormType(InputFormType.CHECKBOX);
		}

	}

	private class InputFormSelectSpec<T> extends InputFormMultiSpec<T>{

		private InputFormSelectSpec(InputValueType inputValueType, String parameter, String label, boolean mandatory, WorkerFormBuilder builder) {
			super(inputValueType, parameter, label, mandatory, builder);
			this.inputFormMulti.setInputFormType(InputFormType.SELECT);
		}

	}

	private class InputFormRadioSpec<T> extends InputFormMultiSpec<T>{

		private InputFormRadioSpec(InputValueType inputValueType, String parameter, String label, boolean mandatory, WorkerFormBuilder builder) {
			super(inputValueType, parameter, label, mandatory, builder);
			this.inputFormMulti.setInputFormType(InputFormType.RADIO);
		}

	}

	private class InputFormFileSpec implements WorkerForm.InputFormFileSpec {

		private InputFormFile inputFormFile;

		private WorkerFormBuilder builder;

		private InputFormFileSpec(String parameter, String label, boolean mandatory, WorkerFormBuilder builder){
			this.inputFormFile = new InputFormFile();
			this.inputFormFile.setInputFormType(InputFormType.FILE);
			this.inputFormFile.setInputValueType(InputValueType.TEXT);
			this.inputFormFile.setParameter(parameter);
			this.inputFormFile.setLabel(label);
			this.inputFormFile.setMandatory(mandatory);
			this.builder = builder;
		}

		public InputFormFileSpec withFileExtensions(List<String> fileExtensions){
			inputFormFile.setFileExtensions(fileExtensions);
			return this;
		}

		public WorkerFormBuilder and() {
			return builder.addInput(inputFormFile);
		}

	}

	private abstract class InputFormMultiSpec<T> implements WorkerForm.InputFormMultiSpec<T>{

		protected InputFormMulti<T> inputFormMulti;

		private WorkerFormBuilder builder;

		private InputFormMultiSpec(InputValueType inputValueType, String parameter, String label, boolean mandatory, WorkerFormBuilder builder){
			this.inputFormMulti = new InputFormMulti();
			this.inputFormMulti.setInputValueType(inputValueType);
			this.inputFormMulti.setParameter(parameter);
			this.inputFormMulti.setLabel(label);
			this.inputFormMulti.setMandatory(mandatory);
			this.builder = builder;
		}

		public InputFormMultiSpec<T> withValues(List<T> values){
			inputFormMulti.setValues(values);
			return this;
		}

		public WorkerFormBuilder and() {
			return builder.addInput(inputFormMulti);
		}
	}


	// Worker form
	private class Form implements WorkerForm.Form{

		private List<WorkerForm.InputFormBase> inputForms;

		private Form(List<WorkerForm.InputFormBase> inputForms) {
			this.inputForms = inputForms;
		}

		public List<WorkerForm.InputFormBase> getInputForms() {
			return inputForms;
		}
	}

	private class InputFormMulti<T> extends InputFormBase implements WorkerForm.InputFormMulti<T>{

		private InputFormMulti() {}

		private void setValues(List<T> values) {
			this.values = values;
		}

		private List<T> values;

		public List<T> getValues() {
			return values;
		}
	}

	private class InputFormFile extends InputFormBase implements WorkerForm.InputFormFile{

		private List<String> fileExtensions;

		public List<String> getFileExtensions() {
			return fileExtensions;
		}

		private void setFileExtensions(List<String> fileExtensions) {
			this.fileExtensions = fileExtensions;
		}
	}

	private class InputFormUnique<T> extends InputFormBase implements WorkerForm.InputFormUnique<T>{

		private T min;

		private T max;

		private InputFormUnique() {}

		private void setMin(T min) {
			this.min = min;
		}

		private void setMax(T max) {
			this.max = max;
		}

		public T getMin() {
			return min;
		}

		public T getMax() {
			return max;
		}
	}

	private abstract class InputFormBase {

		private InputFormType inputFormType;

		private InputValueType inputValueType;

		private String label;

		private String parameter;

		private boolean mandatory;

		public InputFormType getInputFormType() {
			return inputFormType;
		}

		public InputValueType getInputValueType() {
			return inputValueType;
		}

		public String getLabel() {
			return label;
		}

		public String getParameter() {
			return parameter;
		}

		public boolean isMandatory() {
			return mandatory;
		}

		protected void setInputFormType(InputFormType inputFormType) {
			this.inputFormType = inputFormType;
		}

		protected void setInputValueType(InputValueType inputValueType) {
			this.inputValueType = inputValueType;
		}

		protected void setLabel(String label) {
			this.label = label;
		}

		protected void setParameter(String parameter) {
			this.parameter = parameter;
		}

		protected void setMandatory(boolean mandatory) {
			this.mandatory = mandatory;
		}
	}

}
