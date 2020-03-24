package cts.rabobank.projects.csvxmlvalidator.service;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import cts.rabobank.projects.csvxmlvalidator.entity.CSVEntity;
import cts.rabobank.projects.csvxmlvalidator.exception.FileDoesnotExistException;
import cts.rabobank.projects.csvxmlvalidator.exception.UnExpectedFileFormatException;

@Service("CsvService")
public class CsvService {

	private static DecimalFormat ROUND_OFF = new DecimalFormat("0.00");

	Logger logger = LoggerFactory.getLogger(CsvService.class);

	public String csvValidateAndGenerateReport(String fileName) throws IOException {
		List<CSVEntity> reportEntity = uploadFile(fileName);
		Set<Integer> duplicateReferences = findDuplicateReferences(reportEntity);
		generateReport(reportEntity, duplicateReferences);
		return "Success";
	}

	public List<CSVEntity> uploadFile(String fileName) throws IOException, NoSuchFileException {
		List<CSVEntity> reportEntity = null;
		Path path = Paths.get(fileName);
		try {
			if (!Files.exists(path)) {
				logger.error("Filename doesnot found in the given location");
				throw new FileDoesnotExistException("Filename doesnot found in the given location");
			}
		} catch (FileDoesnotExistException exception) {
			System.out.println(exception.getMessage());
		}

		Reader reader = Files.newBufferedReader(Paths.get(fileName));
		CsvToBean<CSVEntity> csvToBean = new CsvToBeanBuilder<CSVEntity>(reader).withType(CSVEntity.class)
				.withIgnoreLeadingWhiteSpace(true).build();
		reportEntity = csvToBean.parse();
		for (CSVEntity csv : reportEntity) {
			try {
				if (csv.getReference() == null) {
					logger.error("CSV file has been corrupted");
					throw new UnExpectedFileFormatException("Unexpected Input Type");
				}
			} catch (UnExpectedFileFormatException exception) {
				System.out.println(exception.getMessage());
			}

		}
		return reportEntity;
	}

	public Set<Integer> findDuplicateReferences(List<CSVEntity> reportEntity) {
		Set<Integer> reportTemp = new HashSet<Integer>();
		Set<Integer> duplicateReferences = new HashSet<Integer>();
		for (CSVEntity csvEntity : reportEntity) {
			if (!reportTemp.add(csvEntity.getReference())) {
				duplicateReferences.add(csvEntity.getReference());
			}
		}
		return duplicateReferences;
	}

	public void generateReport(List<CSVEntity> allElements, Set<Integer> duplicateReferences) throws IOException {
		Writer writer = null;
		CSVWriter csvWriter = null;
		writer = Files.newBufferedWriter(Paths.get("./CSVReport.csv"));
		csvWriter = new CSVWriter(writer, CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER,
				csvWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
		csvWriter.writeNext(new String[] { "Duplicate References" });
		csvWriter.writeNext(new String[] { "Reference", "Description" });
		for (Integer duplicateReference : duplicateReferences) {
			for (CSVEntity csvEntity : allElements) {
				if (csvEntity.getReference().equals(duplicateReference)) {
					logger.info("Duplicate Reference : {}, Description : {}", csvEntity.getReference(),
							csvEntity.getDescription());
					csvWriter.writeNext(
							new String[] { Integer.toString(csvEntity.getReference()), csvEntity.getDescription() });

				}
			}
		}
		csvWriter.writeNext(new String[] { "Mismatched End Balance", "" });
		csvWriter.writeNext(new String[] { "Reference", "Description" });

		for (CSVEntity csvEntity : allElements) {
			if (Float.parseFloat(ROUND_OFF.format(csvEntity.getStartBalance() + csvEntity.getMutation())) != csvEntity
					.getEndBalance()) {
				logger.info("Mismatched End Balance, Reference : {}, Decsription : {}", csvEntity.getReference(),
						csvEntity.getDescription());
				csvWriter.writeNext(
						new String[] { Integer.toString(csvEntity.getReference()), csvEntity.getDescription() });
			}
		}

		csvWriter.close();
	}
}
