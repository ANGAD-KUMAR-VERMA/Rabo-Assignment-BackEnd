package cts.rabobank.projects.csvxmlvalidator.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import cts.rabobank.projects.csvxmlvalidator.entity.CSVEntity;
import cts.rabobank.projects.csvxmlvalidator.exception.FileDoesnotExistException;
import cts.rabobank.projects.csvxmlvalidator.exception.UnExpectedFileFormatException;
import cts.rabobank.projects.csvxmlvalidator.util.CsvXmlValidateAndGenerateReport;

@Service("CsvService")
public class CsvService implements CsvXmlInterface {

	Logger logger = LoggerFactory.getLogger(CsvService.class);

	public void validateAndGenerateReport(String fileName) throws IOException {
		try {
			List reportEntity = uploadFile(fileName);
			Set<Integer> duplicateReferences = CsvXmlValidateAndGenerateReport.findDuplicateReferences(reportEntity);
			Map<Integer, String> invalidEndBalanceRecords = CsvXmlValidateAndGenerateReport
					.findInvalidEndBalanceRecords(reportEntity);
			CsvXmlValidateAndGenerateReport.generateReport(reportEntity, duplicateReferences, invalidEndBalanceRecords);
		} catch (FileNotFoundException exception) {
			throw new FileDoesnotExistException("File Not found");
		} catch (UnExpectedFileFormatException exception) {
			throw new UnExpectedFileFormatException("Invalid. File Doesnot contain required Fields");
		}

	}

	public List uploadFile(String fileName) throws IOException, NoSuchFileException {
		List<CSVEntity> reportEntity = null;
		Path path = Paths.get(fileName);

		if (!Files.exists(path)) {
			throw new FileDoesnotExistException("File Not found");
		}

		Reader reader = Files.newBufferedReader(Paths.get(fileName));
		CsvToBean<CSVEntity> csvToBean = new CsvToBeanBuilder<CSVEntity>(reader).withType(CSVEntity.class)
				.withIgnoreLeadingWhiteSpace(true).build();
		reportEntity = csvToBean.parse();
		for (CSVEntity csv : reportEntity) {

			if (csv.getReference() == null) {
				throw new UnExpectedFileFormatException("Invalid. File Doesnot contain required Fields");
			}

		}
		return reportEntity;
	}

}
