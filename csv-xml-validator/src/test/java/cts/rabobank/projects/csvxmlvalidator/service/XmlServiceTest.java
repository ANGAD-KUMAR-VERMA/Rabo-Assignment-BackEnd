package cts.rabobank.projects.csvxmlvalidator.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBException;
import javax.xml.bind.UnmarshalException;

import org.junit.jupiter.api.Test;

import cts.rabobank.projects.csvxmlvalidator.entity.Records;

public class XmlServiceTest {

	@Test
	void testValidateAndGenerateReport() throws IOException, JAXBException {
		XmlService test = new XmlService();
		String actual = test.validateAndGenerateReport("src/main/resources/records.xml");
		String expected = "Success";
		assertEquals(expected, actual);

	}

	@Test
	void testUploadFileFail() {
		XmlService test = new XmlService();
		assertThrows(UnmarshalException.class, () -> {
			test.uploadFile("/src/main/resources/records.xml");
		});

	}

	@Test
	void testfindDuplicateReferencePass() {
		XmlService test = new XmlService();
		List<Records> reportEntity = new ArrayList<Records>();
		reportEntity.add(new Records(194261, "NL91RABO0315273637", "Clothes from Bakke", 21.6f, 41.83f, 20.23f));
		reportEntity.add(new Records(194262, "NL91RABO0315273636", "Clothes from Jan Bakke", 21.6f, 41.83f, 20.23f));
		reportEntity.add(new Records(194263, "NL91RABO0315273635", "Clothes from Jan", 21.6f, 41.83f, 20.23f));

		Set<Integer> actual = test.findDuplicateReference(reportEntity);
		Set<Integer> expected = new HashSet<Integer>();
		assertEquals(expected, actual);
	}

	@Test
	void testfindDuplicateReferenceFail() {
		XmlService test = new XmlService();
		List<Records> reportEntity = new ArrayList<Records>();
		reportEntity.add(new Records(194261, "NL91RABO0315273637", "Clothes from Bakke", 21.6f, 41.83f, 20.23f));
		reportEntity.add(new Records(194262, "NL91RABO0315273636", "Clothes from Jan Bakke", 21.6f, 41.83f, 20.23f));
		reportEntity.add(new Records(194261, "NL91RABO0315273635", "Clothes from Jan", 21.6f, 41.83f, 20.23f));

		Set<Integer> actual = test.findDuplicateReference(reportEntity);
		Set<Integer> expected = new HashSet<Integer>();
		assertEquals(expected, actual);
	}
}
