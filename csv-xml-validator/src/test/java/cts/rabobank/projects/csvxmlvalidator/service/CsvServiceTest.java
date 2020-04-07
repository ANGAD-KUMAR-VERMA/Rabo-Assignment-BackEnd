package cts.rabobank.projects.csvxmlvalidator.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cts.rabobank.projects.csvxmlvalidator.exception.FileDoesnotExistException;
import cts.rabobank.projects.csvxmlvalidator.exception.UnExpectedFileFormatException;

public class CsvServiceTest {

	@Test()
	void testCsvValidateAndGenerateReport() {
		Assertions.assertDoesNotThrow(() -> {
			CsvService test = new CsvService();
			test.validateAndGenerateReport("src/main/resources/records.csv");
		});
	}

	@Test
	void testFileDoesnotExistexception() {

		Assertions.assertThrows(FileDoesnotExistException.class, () -> {
			CsvService test = new CsvService();
			test.uploadFile("src/main/resources/unknown.xml");
		});

	}

	@Test
	void testUnExpectedFileFormatException() {
		Assertions.assertThrows(UnExpectedFileFormatException.class, () -> {
			CsvService test = new CsvService();
			test.uploadFile("src/main/resources/PiratedCsvFile.csv");
		});
	}

	@Test
	void testUploadFile() {
		Assertions.assertDoesNotThrow(() -> {
			CsvService test = new CsvService();
			test.uploadFile("src/main/resources/records.csv");
		});
	}

}
