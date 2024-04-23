package fr.xelians.xdh.studio.form;

import fr.xelians.xdh.studio.translation.Label;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author aurelien on 08/03/2022
 */

public class WorkerFormTest {

	private static final String LABEL_1 = "LABEL_1";
	private static final String LABEL_2 = "LABEL_2";
	private static final String LABEL_3 = "LABEL_3";
	private static final String LABEL_4 = "LABEL_4";
	private static final String LABEL_5 = "LABEL_5";
	private static final String LABEL_6 = "LABEL_6";

	private static final String PARAM_1 = "PARAM_1";
	private static final String PARAM_2 = "PARAM_2";
	public static final ConditionalDisplay<Float> CONDITIONAL_DISPLAY = ConditionalDisplay.of(List.of(PARAM_2),
			ConditionalDisplay.DisplayBehavior.HIDDEN,
			ConditionalDisplay.ComparisonOperator.EQUAL,
			5.f);
	private static final String PARAM_3 = "PARAM_3";
	private static final String PARAM_4 = "PARAM_4";
	private static final String PARAM_5 = "PARAM_5";
	private static final String PARAM_6 = "PARAM_6";

	private static final Float MIN_VALUE = 2.6f;
	private static final Float MAX_VALUE = 10.f;

	private static final Integer VALUE_1 = 1;
	private static final Integer VALUE_2 = 2;

	private static final Double VALUE_3 = 1.0;
	private static final Double VALUE_4 = 2.2;
	private static final String VALUE_5 = "VALUE_5";
	private static final String VALUE_6 = "VALUE_6";

	@Test
	void testWorkerForm(){

		WorkerForm.Form form = WorkerForm.builder()
				.addInputTextFloat(PARAM_1, Label.of(LABEL_1, LABEL_1), true)
				.withPlaceholder(Label.of(LABEL_2, LABEL_2))
				.withMinValue(MIN_VALUE)
				.withMaxValue(MAX_VALUE)
				.withDisplayDependency(CONDITIONAL_DISPLAY)
				.and()
				.addInputCheckboxBoolean(PARAM_2, Label.of(LABEL_2, LABEL_2), false)
				.withChoices(List.of(WorkerForm.MultiValueChoice.of(true), WorkerForm.MultiValueChoice.of(false)))
				.and()
				.addInputRadioInt(PARAM_3, Label.of(LABEL_3, LABEL_3), false)
				.withChoices(List.of(WorkerForm.MultiValueChoice.of(VALUE_1), WorkerForm.MultiValueChoice.of(VALUE_2)))
				.and()
				.addInputSelectDouble(PARAM_4, Label.of(LABEL_4, LABEL_4), true)
				.withChoices(List.of(WorkerForm.MultiValueChoice.of(VALUE_3), WorkerForm.MultiValueChoice.of(VALUE_4)))
				.and()
				.addInputFormFile(PARAM_5, Label.of(LABEL_5, LABEL_5), false)
				.and()
				.addInputFormTable(PARAM_6, Label.of(LABEL_6, LABEL_6), true)
				.withFields(WorkerForm.builder().addInputTextText(PARAM_1, Label.of(LABEL_1, LABEL_1), false).and()
						.addInputSelectText(PARAM_2, Label.of(LABEL_2, LABEL_2), true)
						.withChoices(List.of(WorkerForm.MultiValueChoice.of(VALUE_5), WorkerForm.MultiValueChoice.of(VALUE_6))).and().build())
				.and()
				.build();

		List<WorkerForm.InputFormBase> inputFormBases = form.inputForms();
		assertEquals(6, inputFormBases.size());
		WorkerForm.InputFormBase inputFormBase1 = inputFormBases.get(0);
		WorkerForm.InputFormBase inputFormBase2 = inputFormBases.get(1);
		WorkerForm.InputFormBase inputFormBase3 = inputFormBases.get(2);
		WorkerForm.InputFormBase inputFormBase4 = inputFormBases.get(3);
		WorkerForm.InputFormBase inputFormBase5 = inputFormBases.get(4);
		WorkerForm.InputFormBase inputFormBase6 = inputFormBases.get(5);

		assertTrue(inputFormBase1 instanceof WorkerForm.InputFormUnique);
		WorkerForm.InputFormUnique<Float> inputFormUnique1 = (WorkerForm.InputFormUnique) inputFormBase1;
		assertEquals(PARAM_1, inputFormUnique1.getParameter());
		assertEquals(LABEL_1, inputFormUnique1.getLabel().getFr());
		assertEquals(LABEL_2, inputFormUnique1.getPlaceholder().getFr());
		assertEquals(LABEL_2, inputFormUnique1.getPlaceholder().getEn());
		assertTrue(inputFormUnique1.isMandatory());
		assertEquals(InputFormType.TEXT, inputFormUnique1.getInputFormType());
		assertEquals(InputValueType.FLOAT, inputFormUnique1.getInputValueType());
		assertEquals(CONDITIONAL_DISPLAY, inputFormUnique1.getConditionalDisplay());
		assertEquals(MAX_VALUE, inputFormUnique1.getMax());
		assertEquals(MIN_VALUE, inputFormUnique1.getMin());

		assertTrue(inputFormBase2 instanceof WorkerForm.InputFormMulti);
		WorkerForm.InputFormMulti<Boolean> inputFormMulti2 = (WorkerForm.InputFormMulti<Boolean>) inputFormBase2;
		assertEquals(PARAM_2, inputFormMulti2.getParameter());
		assertEquals(LABEL_2, inputFormMulti2.getLabel().getEn());
		assertFalse(inputFormMulti2.isMandatory());
		assertEquals(InputFormType.CHECKBOX, inputFormMulti2.getInputFormType());
		assertEquals(InputValueType.BOOLEAN, inputFormMulti2.getInputValueType());
		assertEquals(true, inputFormMulti2.getChoices().get(0).getValue());
		assertEquals(false, inputFormMulti2.getChoices().get(1).getValue());

		assertTrue(inputFormBase3 instanceof WorkerForm.InputFormMulti);
		WorkerForm.InputFormMulti<Integer> inputFormMulti3 = (WorkerForm.InputFormMulti<Integer>) inputFormBase3;
		assertEquals(PARAM_3, inputFormMulti3.getParameter());
		assertEquals(LABEL_3, inputFormMulti3.getLabel().getFr());
		assertFalse(inputFormMulti3.isMandatory());
		assertEquals(InputFormType.RADIO, inputFormMulti3.getInputFormType());
		assertEquals(InputValueType.INTEGER, inputFormMulti3.getInputValueType());
		assertEquals(VALUE_1, inputFormMulti3.getChoices().get(0).getValue());
		assertEquals(VALUE_2, inputFormMulti3.getChoices().get(1).getValue());

		assertTrue(inputFormBase4 instanceof WorkerForm.InputFormMulti);
		WorkerForm.InputFormMulti<Double> inputFormMulti4 = (WorkerForm.InputFormMulti<Double>) inputFormBase4;
		assertEquals(PARAM_4, inputFormMulti4.getParameter());
		assertEquals(LABEL_4, inputFormMulti4.getLabel().getEn());
		assertTrue(inputFormMulti4.isMandatory());
		assertEquals(InputFormType.SELECT, inputFormMulti4.getInputFormType());
		assertEquals(InputValueType.DOUBLE, inputFormMulti4.getInputValueType());
		assertEquals(VALUE_3, inputFormMulti4.getChoices().get(0).getValue());
		assertEquals(VALUE_4, inputFormMulti4.getChoices().get(1).getValue());

		assertTrue(inputFormBase5 instanceof WorkerForm.InputFormFile);
		WorkerForm.InputFormFile inputFormFile = (WorkerForm.InputFormFile) inputFormBase5;
		assertEquals(PARAM_5, inputFormFile.getParameter());
		assertEquals(LABEL_5, inputFormFile.getLabel().getFr());
		assertFalse(inputFormFile.isMandatory());
		assertEquals(InputFormType.FILE, inputFormFile.getInputFormType());

		assertTrue(inputFormBase6 instanceof WorkerForm.InputFormTable);
		WorkerForm.InputFormTable inputFormTable = (WorkerForm.InputFormTable) inputFormBase6;
		assertEquals(PARAM_6, inputFormTable.getParameter());
		assertEquals(LABEL_6, inputFormTable.getLabel().getFr());
		assertTrue(inputFormTable.isMandatory());
		assertEquals(InputFormType.TABLE, inputFormTable.getInputFormType());
		List<WorkerForm.InputFormBase> inputFormColumns = inputFormTable.getInputFormColumns();

		WorkerForm.InputFormBase inputFormColum1 = inputFormColumns.get(0);
		WorkerForm.InputFormBase inputFormColum2 = inputFormColumns.get(1);

		assertTrue(inputFormColum1 instanceof WorkerForm.InputFormUnique);
		WorkerForm.InputFormUnique inputFormUniqueColumn1 = (WorkerForm.InputFormUnique) inputFormColum1;
		assertEquals(PARAM_1, inputFormUniqueColumn1.getParameter());
		assertEquals(LABEL_1, inputFormUniqueColumn1.getLabel().getFr());
		assertEquals(InputFormType.TEXT, inputFormUniqueColumn1.getInputFormType());
		assertEquals(InputValueType.TEXT, inputFormUniqueColumn1.getInputValueType());
		assertFalse(inputFormUniqueColumn1.isMandatory());

		assertTrue(inputFormColum2 instanceof WorkerForm.InputFormMulti);
		WorkerForm.InputFormMulti<String> inputFormMultiColum2 = (WorkerForm.InputFormMulti<String>) inputFormColum2;
		assertEquals(PARAM_2, inputFormMultiColum2.getParameter());
		assertEquals(LABEL_2, inputFormMultiColum2.getLabel().getFr());
		assertTrue(inputFormMultiColum2.isMandatory());
		assertEquals(InputFormType.SELECT, inputFormMultiColum2.getInputFormType());
		assertEquals(InputValueType.TEXT, inputFormMultiColum2.getInputValueType());
		assertEquals(VALUE_5, inputFormMultiColum2.getChoices().get(0).getValue());
		assertEquals(VALUE_6, inputFormMultiColum2.getChoices().get(1).getValue());
	}

}