package cts.rabobank.projects.csvxmlvalidator.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBException;
import javax.xml.bind.UnmarshalException;

import org.junit.jupiter.api.Test;

import cts.rabobank.projects.csvxmlvalidator.entity.CSVEntity;
import cts.rabobank.projects.csvxmlvalidator.entity.Records;
import cts.rabobank.projects.csvxmlvalidator.util.CsvXmlValidateAndGenerateReport;

public class XmlServiceTest {

//	@Test
//	void testValidateAndGenerateReport() throws IOException, JAXBException {
//		XmlService test = new XmlService();
//		String actual = test.validateAndGenerateReport("src/main/resources/records.xml");
//		String expected = "Success";
//		assertEquals(expected, actual);
//
//	}

//	@Test
//	void testUploadFileFail() {
//		XmlService test = new XmlService();
//		assertThrows(UnmarshalException.class, () -> {
//			test.uploadFile("/src/main/resources/records.xml");
//		});
//
//	}

//	@Test
//	void testfindDuplicateReferencePass() {
//		XmlService test = new XmlService();
//		List<Records> reportEntity = new ArrayList<Records>();
//		reportEntity.add(new Records(194261, "NL91RABO0315273637", "Clothes from Bakke", 21.6f, 41.83f, 20.23f));
//		reportEntity.add(new Records(194262, "NL91RABO0315273636", "Clothes from Jan Bakke", 21.6f, 41.83f, 20.23f));
//		reportEntity.add(new Records(194263, "NL91RABO0315273635", "Clothes from Jan", 21.6f, 41.83f, 20.23f));
//
//		Set<Integer> actual = test.findDuplicateReference(reportEntity);
//		Set<Integer> expected = new HashSet<Integer>();
//		assertEquals(expected, actual);
//	}
//
//
//	
//	@Test
//	void testfindInvalidEndBalanceRecords() {
//		CsvXmlValidateAndGenerateReport testgenerate = new CsvXmlValidateAndGenerateReport();
//		List<CSVEntity> reportEntity = new ArrayList<CSVEntity>();
//		reportEntity.add(new CSVEntity(194261, "Clothes from Bakke", "NL91RABO0315273637",new BigDecimal("21.60"),new BigDecimal("20.23"), new BigDecimal("41.83")));
//		reportEntity.add(new CSVEntity(194262, "Clothes from Jan Bakke", "NL91RABO0315273636",new BigDecimal("21.60"),new BigDecimal("41.83"), new BigDecimal("85.23")));
//		reportEntity.add(new CSVEntity(194263, "Clothes from Jan","NL91RABO0315273635" ,new BigDecimal("21.60"),new BigDecimal("90.83"), new BigDecimal("20.23")));
//		Map<Integer,String> actual = new HashMap<Integer,String>(); 
//		Map<Integer,String> expected = new HashMap<Integer,String>(); 
//		actual=	testgenerate.findInvalidEndBalanceRecords(reportEntity);
//		expected.put(194262, "Clothes from Jan Bakke");
//		expected.put(194263,"Clothes from Jan" );
//		System.out.println(actual);
//		System.out.println(expected);
//		assertEquals(expected, actual);
//	}


}
