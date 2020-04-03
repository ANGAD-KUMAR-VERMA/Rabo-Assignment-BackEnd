package cts.rabobank.projects.csvxmlvalidator.controller;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cts.rabobank.projects.csvxmlvalidator.exception.UnknownFileException;
import cts.rabobank.projects.csvxmlvalidator.service.CsvService;
import cts.rabobank.projects.csvxmlvalidator.service.XmlService;

@RestController
@RequestMapping("report")
public class CsvXmlController {

	private static final Logger logger = LoggerFactory.getLogger(CsvXmlController.class);

	@Autowired
	private CsvService csvService;

	@Autowired
	private XmlService xmlService;

	@PostMapping("validate")
	public ResponseEntity<Object> validateAndGenerateReport(@RequestBody String filePath)
			throws IOException, JAXBException {

		String fileType = FilenameUtils.getExtension(filePath);

		if (fileType.equals("csv")) {
			logger.info("CSV file ");
			csvService.validateAndGenerateReport(filePath);
		} else if (fileType.equals("xml")) {
			logger.info("XML file has been loaded");
			xmlService.validateAndGenerateReport(filePath);
		} else {
			throw new UnknownFileException("Unknown File Type");
		}

		return new ResponseEntity<>("Report Generated Successfully", HttpStatus.OK);
	}
}
