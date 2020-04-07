package cts.rabobank.projects.csvxmlvalidator.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.UnmarshalException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cts.rabobank.projects.csvxmlvalidator.entity.Records;
import cts.rabobank.projects.csvxmlvalidator.entity.XmlEntity;
import cts.rabobank.projects.csvxmlvalidator.exception.FileDoesnotExistException;
import cts.rabobank.projects.csvxmlvalidator.exception.UnExpectedFileFormatException;
import cts.rabobank.projects.csvxmlvalidator.util.CsvXmlValidateAndGenerateReport;

@Service("XmlService")
public class XmlService implements CsvXmlInterface {

	Logger logger = LoggerFactory.getLogger(XmlService.class);

	public void validateAndGenerateReport(String fileName) throws IOException, JAXBException {

		try {
			List<Records> reportEntity = uploadFile(fileName);
			Set<Integer> duplicateReferences = CsvXmlValidateAndGenerateReport.findDuplicateReferences(reportEntity);
			Map<Integer, String> invalidEndBalanceRecords = CsvXmlValidateAndGenerateReport
					.findInvalidEndBalanceRecords(reportEntity);
			CsvXmlValidateAndGenerateReport.generateReport(reportEntity, duplicateReferences, invalidEndBalanceRecords);
		} catch (FileDoesnotExistException exception) {
			throw new FileDoesnotExistException("File Not Found");
		} catch (UnExpectedFileFormatException exception) {
			throw new UnExpectedFileFormatException("Invalid. File Doesnot contain required Fields");
		}
	}

	public List<Records> uploadFile(String fileName) throws JAXBException, IOException, UnmarshalException {
		List<Records> allElements = null;
		JAXBContext jaxbContext;
		Path path = Paths.get(fileName);
		if (!Files.exists(path)) {
			throw new FileDoesnotExistException("File Not Found");
		}

		jaxbContext = JAXBContext.newInstance(XmlEntity.class);
		List<XmlEntity> reportEntity = new ArrayList<XmlEntity>();
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		reportEntity.add((XmlEntity) jaxbUnmarshaller.unmarshal(new File(fileName)));
		for (XmlEntity xml : reportEntity)
			allElements = xml.getRecord();
		for (Records record : allElements) {
			if (record.getReference() == null) {
				throw new UnExpectedFileFormatException("Invalid. File Doesnot contain required Fields");
			}

		}
		return allElements;

	}

}
