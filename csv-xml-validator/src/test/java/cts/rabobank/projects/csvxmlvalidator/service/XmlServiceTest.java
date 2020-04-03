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

	@Test
	void testCsvValidateAndGenerateReport() throws IOException, JAXBException{
		XmlService test = new XmlService();
		test.validateAndGenerateReport("src/main/resources/records.xml");
	}



}
