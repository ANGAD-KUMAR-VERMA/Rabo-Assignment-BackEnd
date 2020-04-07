package cts.rabobank.projects.csvxmlvalidator.service;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cts.rabobank.projects.csvxmlvalidator.exception.FileDoesnotExistException;
import cts.rabobank.projects.csvxmlvalidator.exception.UnExpectedFileFormatException;

public class XmlServiceTest {

	@Test
	void testCsvValidateAndGenerateReport() throws IOException, JAXBException {
		Assertions.assertDoesNotThrow(() -> {
			XmlService test = new XmlService();
			test.validateAndGenerateReport("src/main/resources/records.xml");
		});
	}

	@Test
	void testFileDoesnotExistexception() {

		Assertions.assertThrows(FileDoesnotExistException.class, () -> {
			XmlService test = new XmlService();
			test.uploadFile("src/main/resources/unknown.xml");
		});
	}

	@Test
	void testUploadFile() {
		Assertions.assertDoesNotThrow(() -> {
			XmlService test = new XmlService();
			test.uploadFile("src/main/resources/records.xml");
		});
	}

	@Test
	void testUnExpectedFileFormatException() {
		Assertions.assertThrows(UnExpectedFileFormatException.class, () -> {
			XmlService test = new XmlService();
			test.uploadFile("src/main/resources/PiratedXmlFile.xml");
		});
	}

}
