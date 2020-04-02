package cts.rabobank.projects.csvxmlvalidator.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import cts.rabobank.projects.csvxmlvalidator.entity.CSVEntity;
import cts.rabobank.projects.csvxmlvalidator.util.CsvXmlValidateAndGenerateReport;

public class CsvXmlValidateAndGenerateReportTest {

	@Test
	void testfindDuplicateReferencePass() {
		CsvXmlValidateAndGenerateReport test = new CsvXmlValidateAndGenerateReport();
		List<CSVEntity> reportEntity = new ArrayList<CSVEntity>();
		reportEntity.add(new CSVEntity(194261, "NL91RABO0315273637", "Clothes from Bakke", new BigDecimal("21.60"),
				new BigDecimal("41.83"), new BigDecimal("20.23")));
		reportEntity.add(new CSVEntity(194262, "NL91RABO0315273636", "Clothes from Jan Bakke", new BigDecimal("21.60"),
				new BigDecimal("41.83"), new BigDecimal("20.23")));
		reportEntity.add(new CSVEntity(194261, "NL91RABO0315273635", "Clothes from Jan", new BigDecimal("21.60"),
				new BigDecimal("41.83"), new BigDecimal("20.23")));

		Set<Integer> actual = test.findDuplicateReferences(reportEntity);
		Set<Integer> expected = new HashSet<Integer>();
		expected.add(194261);
		assertEquals(expected, actual);
	}

	@Test
	void testfindInvalidEndBalanceRecords() {
		CsvXmlValidateAndGenerateReport test = new CsvXmlValidateAndGenerateReport();
		List<CSVEntity> reportEntity = new ArrayList<CSVEntity>();
		reportEntity.add(new CSVEntity(194261, "Clothes from Bakke", "NL91RABO0315273637", new BigDecimal("21.60"),
				new BigDecimal("20.23"), new BigDecimal("41.83")));
		reportEntity.add(new CSVEntity(194262, "Clothes from Jan Bakke", "NL91RABO0315273636", new BigDecimal("21.60"),
				new BigDecimal("41.83"), new BigDecimal("85.23")));
		reportEntity.add(new CSVEntity(194263, "Clothes from Jan", "NL91RABO0315273635", new BigDecimal("21.60"),
				new BigDecimal("90.83"), new BigDecimal("20.23")));
		Map<Integer, String> actual = new HashMap<Integer, String>();
		Map<Integer, String> expected = new HashMap<Integer, String>();
		actual = test.findInvalidEndBalanceRecords(reportEntity);
		expected.put(194262, "Clothes from Jan Bakke");
		expected.put(194263, "Clothes from Jan");
		System.out.println(actual);
		System.out.println(expected);
		assertEquals(expected, actual);
	}

}
