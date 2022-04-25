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

	@Override
	public WorkerForm.InputFormTableSpec addInputFormTable(String parameter, String label, boolean mandatory) {
		return new InputFormTableSpec(parameter, label, mandatory, this);
	}

	@Override
	public WorkerForm.InputFormToggleSpec addInputFormToggle(String parameter, String label, boolean mandatory) {
		return new InputFormToggleSpec(parameter, label, mandatory, this);
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

		@Override
		public InputTextSpec withInformation(String information) {
			inputFormUnique.setInformation(information);
			return this;
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

	private class InputFormToggleSpec implements WorkerForm.InputFormToggleSpec{

		private InputFormToggle inputFormToggle;
		private WorkerFormBuilder builder;

		private InputFormToggleSpec(String parameter, String label, boolean mandatory, WorkerFormBuilder builder) {
			this.inputFormToggle = new InputFormToggle();
			this.inputFormToggle.setInputFormType(InputFormType.TOGGLE);
			this.inputFormToggle.setParameter(parameter);
			this.inputFormToggle.setLabel(label);
			this.inputFormToggle.setMandatory(mandatory);
			this.builder = builder;
		}

		@Override
		public WorkerForm.Builder and() {
			return builder.addInput(inputFormToggle);
		}

		@Override
		public WorkerForm.InputFormToggleSpec withInformation(String information) {
			inputFormToggle.setInformation(information);
			return this;
		}
	}

	private class InputFormTableSpec implements WorkerForm.InputFormTableSpec{

		private InputFormTable inputFormTable;
		private WorkerFormBuilder builder;

		private InputFormTableSpec(String parameter, String label, boolean mandatory, WorkerFormBuilder builder){
			this.inputFormTable = new InputFormTable();
			this.inputFormTable.setInputFormType(InputFormType.TABLE);
			this.inputFormTable.setParameter(parameter);
			this.inputFormTable.setLabel(label);
			this.inputFormTable.setMandatory(mandatory);
			this.builder = builder;
		}

		@Override
		public WorkerForm.InputTextTableSpec addInputTextText(String parameter, String label, boolean mandatory) {
			return new InputTextTableSpec(InputValueType.TEXT, InputFormType.TEXT, parameter, label, mandatory, this);
		}

		@Override
		public WorkerForm.InputFormMultiTableSpec addInputSelectText(String parameter, String label, boolean mandatory) {
			return new InputFormMultiTableSpec(InputValueType.TEXT, InputFormType.SELECT, parameter, label, mandatory, this);
		}

		@Override
		public WorkerForm.InputFormToggleTableSpec addInputFormToggle(String parameter, String label, boolean mandatory) {
			return new InputFormToggleTableSpec(parameter, label, mandatory, this);
		}

		@Override
		public WorkerForm.InputFormTableSpec withInformation(String information) {
			inputFormTable.setInformation(information);
			return this;
		}

		@Override
		public WorkerForm.Builder and() {
			return builder.addInput(inputFormTable);
		}

		private void addInputForm(WorkerForm.InputFormBase inputFormBase){
			inputFormTable.addInputFormColumn(inputFormBase);
		}

		private class InputFormMultiTableSpec implements WorkerForm.InputFormMultiTableSpec{

			protected InputFormMulti<String> inputFormMulti;

			private InputFormTableSpec inputFormTableSpec;

			private InputFormMultiTableSpec(InputValueType inputValueType, InputFormType inputFormType, String parameter, String label, boolean mandatory, InputFormTableSpec inputFormTableSpec){
				this.inputFormMulti = new InputFormMulti();
				this.inputFormMulti.setInputValueType(inputValueType);
				this.inputFormMulti.setParameter(parameter);
				this.inputFormMulti.setLabel(label);
				this.inputFormMulti.setMandatory(mandatory);
				this.inputFormMulti.setInputFormType(inputFormType);
				this.inputFormTableSpec = inputFormTableSpec;
			}

			public InputFormMultiTableSpec withValues(List<String> values){
				inputFormMulti.setValues(values);
				return this;
			}

			public InputFormTableSpec and(){
				inputFormTableSpec.addInputForm(inputFormMulti);
				return inputFormTableSpec;
			}

		}

		private class InputTextTableSpec implements WorkerForm.InputTextTableSpec{

			private InputFormUnique<String> inputFormUnique;

			private InputFormTableSpec inputFormTableSpec;

			private InputTextTableSpec(InputValueType inputValueType, InputFormType inputFormType, String parameter, String label, boolean mandatory, InputFormTableSpec inputFormTableSpec){
				this.inputFormUnique = new InputFormUnique();
				this.inputFormUnique.setInputFormType(InputFormType.TEXT);
				this.inputFormUnique.setInputValueType(inputValueType);
				this.inputFormUnique.setParameter(parameter);
				this.inputFormUnique.setLabel(label);
				this.inputFormUnique.setMandatory(mandatory);
				this.inputFormUnique.setInputFormType(inputFormType);
				this.inputFormTableSpec = inputFormTableSpec;
			}

			@Override
			public WorkerForm.InputTextTableSpec withMinValue(String value) {
				inputFormUnique.setMin(value);
				return this;
			}

			@Override
			public WorkerForm.InputTextTableSpec withMaxValue(String value) {
				inputFormUnique.setMax(value);
				return this;
			}

			@Override
			public WorkerForm.InputFormTableSpec and() {
				inputFormTableSpec.addInputForm(inputFormUnique);
				return inputFormTableSpec;
			}
		}

		private class InputFormToggleTableSpec implements WorkerForm.InputFormToggleTableSpec{

			private InputFormToggle inputFormToggle;
			private InputFormTableSpec inputFormTableSpec;

			private InputFormToggleTableSpec(String parameter, String label, boolean mandatory, InputFormTableSpec inputFormTableSpec) {
				this.inputFormToggle = new InputFormToggle();
				this.inputFormToggle.setInputFormType(InputFormType.TOGGLE);
				this.inputFormToggle.setParameter(parameter);
				this.inputFormToggle.setLabel(label);
				this.inputFormToggle.setMandatory(mandatory);
				this.inputFormTableSpec = inputFormTableSpec;
			}


			@Override
			public WorkerForm.InputFormTableSpec and() {
				inputFormTableSpec.addInputForm(inputFormToggle);
				return inputFormTableSpec;
			}
		}
	}

	private class InputFormFileSpec implements WorkerForm.InputFormFileSpec {

		private InputFormFile inputFormFile;
		private WorkerFormBuilder builder;

		private InputFormFileSpec(String parameter, String label, boolean mandatory, WorkerFormBuilder builder){
			this.inputFormFile = new InputFormFile();
			this.inputFormFile.setInputFormType(InputFormType.FILE);
			this.inputFormFile.setParameter(parameter);
			this.inputFormFile.setLabel(label);
			this.inputFormFile.setMandatory(mandatory);
			this.builder = builder;
		}

		public InputFormFileSpec withFileExtensions(List<String> fileExtensions){
			inputFormFile.setFileExtensions(fileExtensions);
			return this;
		}

		@Override
		public WorkerForm.InputFormFileSpec withInformation(String information) {
			inputFormFile.setInformation(information);
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

		public InputFormMultiSpec withInformation(String information){
			inputFormMulti.setInformation(information);
			return this;
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

		private InputValueType inputValueType;

		private void setInputValueType(InputValueType inputValueType) {
			this.inputValueType = inputValueType;
		}
		public InputValueType getInputValueType() {
			return inputValueType;
		}

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

	private class InputFormTable extends InputFormBase implements WorkerForm.InputFormTable{

		private List<WorkerForm.InputFormBase> inputFormColumns = new ArrayList<>();

		private void addInputFormColumn(WorkerForm.InputFormBase inputFormBase){
			inputFormColumns.add(inputFormBase);
		}

		@Override
		public List<WorkerForm.InputFormBase> getInputFormColumns() {
			return inputFormColumns;
		}
	}

	private class InputFormToggle extends InputFormBase implements WorkerForm.InputFormToggle{

	}

	private class InputFormUnique<T> extends InputFormBase implements WorkerForm.InputFormUnique<T>{

		private T min;

		private T max;

		private InputValueType inputValueType;

		private InputFormUnique() {}

		private void setInputValueType(InputValueType inputValueType) {
			this.inputValueType = inputValueType;
		}
		public InputValueType getInputValueType() {
			return inputValueType;
		}

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

		private String label;

		private String parameter;

		private boolean mandatory;

		private String information;

		public InputFormType getInputFormType() {
			return inputFormType;
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

		protected void setLabel(String label) {
			this.label = label;
		}

		protected void setParameter(String parameter) {
			this.parameter = parameter;
		}

		protected void setMandatory(boolean mandatory) {
			this.mandatory = mandatory;
		}

		public String getInformation() {
			return information;
		}

		protected void setInformation(String information) {
			this.information = information;
		}
	}

}
