package fr.xelians.xdh.plugin.form;

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

	private static final String PARAM_1 = "PARAM_1";
	private static final String PARAM_2 = "PARAM_2";
	private static final String PARAM_3 = "PARAM_3";
	private static final String PARAM_4 = "PARAM_4";
	private static final String PARAM_5 = "PARAM_5";

	private static final Float MIN_VALUE = 2.6f;
	private static final Float MAX_VALUE = 10.f;

	private static final Integer VALUE_1 = 1;
	private static final Integer VALUE_2 = 2;

	private static final Double VALUE_3 = 1.0;
	private static final Double VALUE_4 = 2.2;

	@Test
	public void testWorkerForm(){

		WorkerForm.Form form = WorkerForm.builder()
				.addInputTextFloat(PARAM_1, LABEL_1, true)
				.withMinValue(MIN_VALUE)
				.withMaxValue(MAX_VALUE)
				.and()
				.addInputCheckboxBoolean(PARAM_2, LABEL_2, false)
				.withValues(List.of(true, false))
				.and()
				.addInputRadioInt(PARAM_3, LABEL_3, false)
				.withValues(List.of(VALUE_1, VALUE_2))
				.and()
				.addInputSelectDouble(PARAM_4, LABEL_4, true)
				.withValues(List.of(VALUE_3, VALUE_4))
				.and()
				.addInputFormFile(PARAM_5, LABEL_5, false)
				.and()
				.build();

		List<WorkerForm.InputFormBase> inputFormBases = form.getInputForms();
		assertEquals(5, inputFormBases.size());
		WorkerForm.InputFormBase inputFormBase1 = inputFormBases.get(0);
		WorkerForm.InputFormBase inputFormBase2 = inputFormBases.get(1);
		WorkerForm.InputFormBase inputFormBase3 = inputFormBases.get(2);
		WorkerForm.InputFormBase inputFormBase4 = inputFormBases.get(3);
		WorkerForm.InputFormBase inputFormBase5 = inputFormBases.get(4);

		assertTrue(inputFormBase1 instanceof WorkerForm.InputFormUnique);
		WorkerForm.InputFormUnique<Float> inputFormUnique1 = (WorkerForm.InputFormUnique) inputFormBase1;
		assertEquals(PARAM_1, inputFormUnique1.getParameter());
		assertEquals(LABEL_1, inputFormUnique1.getLabel());
		assertTrue(inputFormUnique1.isMandatory());
		assertEquals(InputFormType.TEXT, inputFormUnique1.getInputFormType());
		assertEquals(InputValueType.FLOAT, inputFormUnique1.getInputValueType());
		assertEquals(MAX_VALUE, inputFormUnique1.getMax());
		assertEquals(MIN_VALUE, inputFormUnique1.getMin());

		assertTrue(inputFormBase2 instanceof WorkerForm.InputFormMulti);
		WorkerForm.InputFormMulti<Boolean> inputFormMulti2 = (WorkerForm.InputFormMulti<Boolean>) inputFormBase2;
		assertEquals(PARAM_2, inputFormMulti2.getParameter());
		assertEquals(LABEL_2, inputFormMulti2.getLabel());
		assertFalse(inputFormMulti2.isMandatory());
		assertEquals(InputFormType.CHECKBOX, inputFormMulti2.getInputFormType());
		assertEquals(InputValueType.BOOLEAN, inputFormMulti2.getInputValueType());
		assertEquals(List.of(true, false), inputFormMulti2.getValues());

		assertTrue(inputFormBase3 instanceof WorkerForm.InputFormMulti);
		WorkerForm.InputFormMulti<Integer> inputFormMulti3 = (WorkerForm.InputFormMulti<Integer>) inputFormBase3;
		assertEquals(PARAM_3, inputFormMulti3.getParameter());
		assertEquals(LABEL_3, inputFormMulti3.getLabel());
		assertFalse(inputFormMulti3.isMandatory());
		assertEquals(InputFormType.RADIO, inputFormMulti3.getInputFormType());
		assertEquals(InputValueType.INTEGER, inputFormMulti3.getInputValueType());
		assertEquals(List.of(VALUE_1, VALUE_2), inputFormMulti3.getValues());

		assertTrue(inputFormBase4 instanceof WorkerForm.InputFormMulti);
		WorkerForm.InputFormMulti<Double> inputFormMulti4 = (WorkerForm.InputFormMulti<Double>) inputFormBase4;
		assertEquals(PARAM_4, inputFormMulti4.getParameter());
		assertEquals(LABEL_4, inputFormMulti4.getLabel());
		assertTrue(inputFormMulti4.isMandatory());
		assertEquals(InputFormType.SELECT, inputFormMulti4.getInputFormType());
		assertEquals(InputValueType.DOUBLE, inputFormMulti4.getInputValueType());
		assertEquals(List.of(VALUE_3, VALUE_4), inputFormMulti4.getValues());

		assertTrue(inputFormBase5 instanceof WorkerForm.InputFormFile);
		WorkerForm.InputFormFile inputFormFile = (WorkerForm.InputFormFile) inputFormBase5;
		assertEquals(PARAM_5, inputFormFile.getParameter());
		assertEquals(LABEL_5, inputFormFile.getLabel());
		assertFalse(inputFormFile.isMandatory());
		assertEquals(InputFormType.FILE, inputFormFile.getInputFormType());
		assertEquals(InputValueType.TEXT, inputFormFile.getInputValueType());


	}

}