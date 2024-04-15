package fr.xelians.xdh.studio.form;

import fr.xelians.xdh.studio.translation.Label;

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
	public WorkerForm.InputTextSpec<Integer> addInputTextInt(String parameter, Label.Translation label, boolean mandatory){
		return new InputTextSpec<>(InputValueType.INTEGER, parameter, label, mandatory, this);
	}

	@Override
	public WorkerForm.InputTextSpec<Double> addInputTextDouble(String parameter, Label.Translation label, boolean mandatory) {
		return new InputTextSpec<>(InputValueType.DOUBLE, parameter, label, mandatory, this);
	}

	@Override
	public WorkerForm.InputTextSpec<Float> addInputTextFloat(String parameter, Label.Translation label, boolean mandatory) {
		return new InputTextSpec<>(InputValueType.FLOAT, parameter, label, mandatory, this);
	}

	@Override
	public WorkerForm.InputTextSpec<Long> addInputTextLong(String parameter, Label.Translation label, boolean mandatory) {
		return new InputTextSpec<>(InputValueType.LONG, parameter, label, mandatory, this);
	}

	@Override
	public WorkerForm.InputTextSpec<Boolean> addInputTextBoolean(String parameter, Label.Translation label, boolean mandatory) {
		return new InputTextSpec<>(InputValueType.BOOLEAN, parameter, label, mandatory, this);
	}

	@Override
	public WorkerForm.InputTextSpec<String> addInputTextText(String parameter, Label.Translation label, boolean mandatory) {
		return new InputTextSpec<>(InputValueType.TEXT, parameter, label, mandatory, this);
	}

	@Override
	public WorkerForm.InputFormMultiSpec<Integer> addInputSelectInt(String parameter, Label.Translation label, boolean mandatory) {
		return new InputFormSelectSpec<>(InputValueType.INTEGER, parameter, label, mandatory, this);
	}

	@Override
	public WorkerForm.InputFormMultiSpec<Double> addInputSelectDouble(String parameter, Label.Translation label, boolean mandatory) {
		return new InputFormSelectSpec<>(InputValueType.DOUBLE, parameter, label, mandatory, this);
	}

	@Override
	public WorkerForm.InputFormMultiSpec<Float> addInputSelectFloat(String parameter, Label.Translation label, boolean mandatory) {
		return new InputFormSelectSpec<>(InputValueType.FLOAT, parameter, label, mandatory, this);
	}

	@Override
	public WorkerForm.InputFormMultiSpec<Long> addInputSelectLong(String parameter, Label.Translation label, boolean mandatory) {
		return new InputFormSelectSpec<>(InputValueType.LONG, parameter, label, mandatory, this);
	}

	@Override
	public WorkerForm.InputFormMultiSpec<Boolean> addInputSelectBoolean(String parameter, Label.Translation label, boolean mandatory) {
		return new InputFormSelectSpec<>(InputValueType.BOOLEAN, parameter, label, mandatory, this);
	}

	@Override
	public WorkerForm.InputFormMultiSpec<String> addInputSelectText(String parameter, Label.Translation label, boolean mandatory) {
		return new InputFormSelectSpec<>(InputValueType.TEXT, parameter, label, mandatory, this);
	}

	@Override
	public WorkerForm.InputFormMultiSpec<Integer> addInputCheckboxInt(String parameter, Label.Translation label, boolean mandatory) {
		return new InputFormCheckboxSpec<>(InputValueType.INTEGER, parameter, label, mandatory, this);
	}

	@Override
	public WorkerForm.InputFormMultiSpec<Double> addInputCheckboxDouble(String parameter, Label.Translation label, boolean mandatory) {
		return new InputFormCheckboxSpec<>(InputValueType.DOUBLE, parameter, label, mandatory, this);
	}

	@Override
	public WorkerForm.InputFormMultiSpec<Float> addInputCheckboxFloat(String parameter, Label.Translation label, boolean mandatory) {
		return new InputFormCheckboxSpec<>(InputValueType.FLOAT, parameter, label, mandatory, this);
	}

	@Override
	public WorkerForm.InputFormMultiSpec<Long> addInputCheckboxLong(String parameter, Label.Translation label, boolean mandatory) {
		return new InputFormCheckboxSpec<>(InputValueType.LONG, parameter, label, mandatory, this);
	}

	@Override
	public WorkerForm.InputFormMultiSpec<Boolean> addInputCheckboxBoolean(String parameter, Label.Translation label, boolean mandatory) {
		return new InputFormCheckboxSpec<>(InputValueType.BOOLEAN, parameter, label, mandatory, this);
	}

	@Override
	public WorkerForm.InputFormMultiSpec<String> addInputCheckboxText(String parameter, Label.Translation label, boolean mandatory) {
		return new InputFormCheckboxSpec<>(InputValueType.TEXT, parameter, label, mandatory, this);
	}

	@Override
	public WorkerForm.InputFormMultiSpec<Integer> addInputRadioInt(String parameter, Label.Translation label, boolean mandatory) {
		return new InputFormRadioSpec<>(InputValueType.INTEGER, parameter, label, mandatory, this);
	}

	@Override
	public WorkerForm.InputFormMultiSpec<Double> addInputRadioDouble(String parameter, Label.Translation label, boolean mandatory) {
		return new InputFormRadioSpec<>(InputValueType.DOUBLE, parameter, label, mandatory, this);
	}

	@Override
	public WorkerForm.InputFormMultiSpec<Float> addInputRadioFloat(String parameter, Label.Translation label, boolean mandatory) {
		return new InputFormRadioSpec<>(InputValueType.FLOAT, parameter, label, mandatory, this);
	}

	@Override
	public WorkerForm.InputFormMultiSpec<Long> addInputRadioLong(String parameter, Label.Translation label, boolean mandatory) {
		return new InputFormRadioSpec<>(InputValueType.LONG, parameter, label, mandatory, this);
	}

	@Override
	public WorkerForm.InputFormMultiSpec<Boolean> addInputRadioBoolean(String parameter, Label.Translation label, boolean mandatory) {
		return new InputFormRadioSpec<>(InputValueType.BOOLEAN, parameter, label, mandatory, this);
	}

	@Override
	public WorkerForm.InputFormMultiSpec<String> addInputRadioText(String parameter, Label.Translation label, boolean mandatory) {
		return new InputFormRadioSpec<>(InputValueType.TEXT, parameter, label, mandatory, this);
	}

	@Override
	public WorkerForm.InputFormFileSpec addInputFormFile(String parameter, Label.Translation label, boolean mandatory){
		return new InputFormFileSpec(parameter, label, mandatory, this);
	}

	@Override
	public WorkerForm.InputFormTableSpec addInputFormTable(String parameter, Label.Translation label, boolean mandatory) {
		return new InputFormTableSpec(parameter, label, mandatory, this);
	}

	@Override
	public WorkerForm.InputFormToggleSpec addInputFormToggle(String parameter, Label.Translation label, boolean mandatory) {
		return new InputFormToggleSpec(parameter, label, mandatory, this);
	}

	@Override
	public WorkerForm.InputTextAreaSpec addInputFormTextArea(String parameter, Label.Translation label, boolean mandatory) {
		return new InputFormTextAreaSpec(parameter, label, mandatory, this);
	}

	private WorkerFormBuilder addInput(WorkerForm.InputFormBase inputFormBase){
		inputForms.add(inputFormBase);
		return this;
	}

	private class InputTextSpec<T> implements WorkerForm.InputTextSpec<T>{

		private InputFormUnique<T> inputFormUnique;

		private WorkerFormBuilder builder;

		private InputTextSpec(InputValueType inputValueType, String parameter, Label.Translation label, boolean mandatory, WorkerFormBuilder builder){
			this.inputFormUnique = new InputFormUnique<>();
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
		public InputTextSpec<T> withInformation(Label.Translation information) {
			inputFormUnique.setInformation(information);
			return this;
		}

		@Override
		public WorkerForm.InputTextSpec<T> withPlaceholder(Label.Translation placeholder) {
			inputFormUnique.setPlaceholder(placeholder);
			return this;
		}

		@Override
		public WorkerForm.InputTextSpec<T> obfuscate() {
			inputFormUnique.setObfuscate(true);
			return this;
		}

		@Override
		public WorkerForm.InputTextSpec<T> withDisplayDependency(ConditionalDisplay<T> conditionalDisplay) {
			inputFormUnique.setConditionalDisplay(conditionalDisplay);
			return this;
		}
	}

	private class InputFormTextAreaSpec implements WorkerForm.InputTextAreaSpec {

		private InputFormTextArea inputFormTextArea;

		private WorkerFormBuilder builder;

		private InputFormTextAreaSpec(String parameter, Label.Translation label, boolean mandatory, WorkerFormBuilder builder){
			this.inputFormTextArea = new InputFormTextArea();
			this.inputFormTextArea.setInputFormType(InputFormType.TEXTAREA);
			this.inputFormTextArea.setParameter(parameter);
			this.inputFormTextArea.setLabel(label);
			this.inputFormTextArea.setMandatory(mandatory);
			this.builder = builder;
		}

		public WorkerFormBuilder and() {
			return builder.addInput(inputFormTextArea);
		}

		@Override
		public InputFormTextAreaSpec withDisplayDependency(ConditionalDisplay<?> conditionalDisplay) {
			this.inputFormTextArea.setConditionalDisplay(conditionalDisplay);
			return this;
		}

		@Override
		public WorkerForm.InputTextAreaSpec withInformation(Label.Translation information) {
			inputFormTextArea.setInformation(information);
			return this;
		}

		@Override
		public WorkerForm.InputTextAreaSpec withPlaceholder(Label.Translation placeholder) {
			inputFormTextArea.setPlaceholder(placeholder);
			return this;
		}

		@Override
		public WorkerForm.InputTextAreaSpec obfuscate() {
			inputFormTextArea.setObfuscate(true);
			return this;
		}
	}

	private class InputFormCheckboxSpec<T> extends InputFormMultiSpec<T> {

		private InputFormCheckboxSpec(InputValueType inputValueType, String parameter, Label.Translation label, boolean mandatory, WorkerFormBuilder builder) {
			super(inputValueType, parameter, label, mandatory, builder);
			this.inputFormMulti.setInputFormType(InputFormType.CHECKBOX);
		}

	}

	private class InputFormSelectSpec<T> extends InputFormMultiSpec<T>{

		private InputFormSelectSpec(InputValueType inputValueType, String parameter, Label.Translation label, boolean mandatory, WorkerFormBuilder builder) {
			super(inputValueType, parameter, label, mandatory, builder);
			this.inputFormMulti.setInputFormType(InputFormType.SELECT);
		}

	}

	private class InputFormRadioSpec<T> extends InputFormMultiSpec<T>{

		private InputFormRadioSpec(InputValueType inputValueType, String parameter, Label.Translation label, boolean mandatory, WorkerFormBuilder builder) {
			super(inputValueType, parameter, label, mandatory, builder);
			this.inputFormMulti.setInputFormType(InputFormType.RADIO);
		}

	}

	private class InputFormToggleSpec implements WorkerForm.InputFormToggleSpec{

		private InputFormToggle inputFormToggle;
		private WorkerFormBuilder builder;

		private InputFormToggleSpec(String parameter, Label.Translation label, boolean mandatory, WorkerFormBuilder builder) {
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
		public WorkerForm.InputFormToggleSpec withInformation(Label.Translation information) {
			inputFormToggle.setInformation(information);
			return this;
		}

		@Override
		public WorkerForm.InputFormToggleSpec withDisplayDependency(ConditionalDisplay<?> conditionalDisplay) {
			this.inputFormToggle.setConditionalDisplay(conditionalDisplay);
			return this;
		}
	}

	private class InputFormTableSpec implements WorkerForm.InputFormTableSpec{

		private InputFormTable inputFormTable;
		private WorkerFormBuilder builder;

		private InputFormTableSpec(String parameter, Label.Translation label, boolean mandatory, WorkerFormBuilder builder){
			this.inputFormTable = new InputFormTable();
			this.inputFormTable.setInputFormType(InputFormType.TABLE);
			this.inputFormTable.setParameter(parameter);
			this.inputFormTable.setLabel(label);
			this.inputFormTable.setMandatory(mandatory);
			this.builder = builder;
		}

		@Override
		public WorkerForm.InputFormTableSpec withFields(WorkerForm.Form form) {
			this.inputFormTable.inputFormColumns = form.getInputForms();
			return this;
		}

		@Override
		public WorkerForm.InputFormTableSpec withInformation(Label.Translation information) {
			inputFormTable.setInformation(information);
			return this;
		}

		@Override
		public WorkerForm.InputFormTableSpec withTableLabel(Label.Translation tableLabel) {
			this.inputFormTable.tableLabel = tableLabel;
			return this;
		}

		@Override
		public WorkerForm.Builder and() {
			return builder.addInput(inputFormTable);
		}


	}

	private class InputFormFileSpec implements WorkerForm.InputFormFileSpec {

		private InputFormFile inputFormFile;
		private WorkerFormBuilder builder;

		private InputFormFileSpec(String parameter, Label.Translation label, boolean mandatory, WorkerFormBuilder builder){
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
		public WorkerForm.InputFormFileSpec withInformation(Label.Translation information) {
			inputFormFile.setInformation(information);
			return this;
		}

		public WorkerFormBuilder and() {
			return builder.addInput(inputFormFile);
		}


		public WorkerForm.InputFormFileSpec withDisplayDependency(ConditionalDisplay<?> conditionalDisplay) {
			inputFormFile.setConditionalDisplay(conditionalDisplay);
			return this;
		}

	}

	private abstract class InputFormMultiSpec<T> implements WorkerForm.InputFormMultiSpec<T>{

		protected InputFormMulti<T> inputFormMulti;

		private WorkerFormBuilder builder;

		private InputFormMultiSpec(InputValueType inputValueType, String parameter, Label.Translation label, boolean mandatory, WorkerFormBuilder builder){
			this.inputFormMulti = new InputFormMulti<>();
			this.inputFormMulti.setInputValueType(inputValueType);
			this.inputFormMulti.setParameter(parameter);
			this.inputFormMulti.setLabel(label);
			this.inputFormMulti.setMandatory(mandatory);
			this.builder = builder;
		}

		public InputFormMultiSpec<T> withChoices(List<WorkerForm.MultiValueChoice.Choice<T>> choices){
			inputFormMulti.setChoices(choices);
			return this;
		}

		public WorkerFormBuilder and() {
			return builder.addInput(inputFormMulti);
		}

		public InputFormMultiSpec<T> withInformation(Label.Translation information){
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

		private InputValueType inputValueType;

		private InputFormMulti() {}

		private void setInputValueType(InputValueType inputValueType) {
			this.inputValueType = inputValueType;
		}

		@Override
		public InputValueType getInputValueType() {
			return inputValueType;
		}

		private void setChoices(List<WorkerForm.MultiValueChoice.Choice<T>> choices) {
			this.choices = choices;
		}

		private List<WorkerForm.MultiValueChoice.Choice<T>> choices;

		@Override
		public List<WorkerForm.MultiValueChoice.Choice<T>> getChoices() {
			return choices;
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
		private Label.Translation tableLabel;

		private void addInputFormColumn(WorkerForm.InputFormBase inputFormBase){
			inputFormColumns.add(inputFormBase);
		}

		@Override
		public List<WorkerForm.InputFormBase> getInputFormColumns() {
			return inputFormColumns;
		}

		@Override
		public Label.Translation getTableLabel() {
			return tableLabel;
		}

	}

	private class InputFormToggle extends InputFormBase implements WorkerForm.InputFormToggle{

	}

	private class InputFormUnique<T> extends InputFormBase implements WorkerForm.InputFormUnique<T>{

		private T min;
		private T max;
		private Label.Translation placeholder;
		private InputValueType inputValueType;
		private boolean obfuscate;


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

		private void setObfuscate(boolean obfuscate){
			this.obfuscate = true;
		}

		private void setPlaceholder(Label.Translation placeholder){
			this.placeholder = placeholder;
		}

		public T getMin() {
			return min;
		}

		public T getMax() {
			return max;
		}

		public Label.Translation getPlaceholder(){
			return this.placeholder;
		}

		@Override
		public boolean isObfuscate() {
			return obfuscate;
		}
	}

	private class InputFormTextArea extends InputFormBase implements WorkerForm.InputFormTextArea {

		private Label.Translation placeholder;
		private boolean obfuscate;

		private InputFormTextArea() {}

		private void setPlaceholder(Label.Translation placeholder){
			this.placeholder = placeholder;
		}

		public Label.Translation getPlaceholder(){
			return this.placeholder;
		}

		private void setObfuscate(boolean obfuscate){
			this.obfuscate = true;
		}

		@Override
		public boolean isObfuscate() {
			return obfuscate;
		}
	}

	private abstract class InputFormBase {

		private InputFormType inputFormType;

		private Label.Translation label;

		private String parameter;

		private boolean mandatory;

		private Label.Translation information;

		private ConditionalDisplay<?> conditionalDisplay;

		public InputFormType getInputFormType() {
			return inputFormType;
		}

		public Label.Translation getLabel() {
			return label;
		}

		public String getParameter() {
			return parameter;
		}

		public boolean isMandatory() {
			return mandatory;
		}

		public ConditionalDisplay<?> getConditionalDisplay() {
			return conditionalDisplay;
		}

		protected void setInputFormType(InputFormType inputFormType) {
			this.inputFormType = inputFormType;
		}

		protected void setLabel(Label.Translation label) {
			this.label = label;
		}

		protected void setParameter(String parameter) {
			this.parameter = parameter;
		}

		protected void setMandatory(boolean mandatory) {
			this.mandatory = mandatory;
		}

		public Label.Translation getInformation() {
			return information;
		}

		protected void setInformation(Label.Translation information) {
			this.information = information;
		}

		protected void setConditionalDisplay(ConditionalDisplay<?> conditionalDisplay) {
			this.conditionalDisplay = conditionalDisplay;
		}
	}

}
