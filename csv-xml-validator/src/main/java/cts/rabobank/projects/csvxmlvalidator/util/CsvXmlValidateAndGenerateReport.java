package cts.rabobank.projects.csvxmlvalidator.util;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.CSVWriter;

import cts.rabobank.projects.csvxmlvalidator.entity.CSVEntity;
import cts.rabobank.projects.csvxmlvalidator.entity.Records;
import cts.rabobank.projects.csvxmlvalidator.repo.ParserInterface;
import cts.rabobank.projects.csvxmlvalidator.service.XmlService;

public class CsvXmlValidateAndGenerateReport  {
	private static Logger logger = LoggerFactory.getLogger(CsvXmlValidateAndGenerateReport.class);

	public static Set<Integer> findDuplicateReferences(List reportEntity) {
		Set<Integer> reportTemp = new HashSet<Integer>();
		Set<Integer> duplicateReferences = new HashSet<Integer>();
		for (Object element : reportEntity) {
			if (element instanceof CSVEntity) {
				if (!reportTemp.add(((CSVEntity) element).getReference())) {
					duplicateReferences.add(((CSVEntity) element).getReference());
				}
			} else if (element instanceof Records) {
				if (!reportTemp.add(((Records) element).getReference())) {
					duplicateReferences.add(((Records) element).getReference());
				}
			}

		}
		return duplicateReferences;
	}

	public static Map<Integer, String> findInvalidEndBalanceRecords(List reportEntity) {

		Map<Integer, String> invalidEndBalanceRecords = new HashMap<Integer, String>();
		for (Object element : reportEntity) {
			if (element instanceof CSVEntity) {
				int res = ((CSVEntity) element).getEndBalance()
						.compareTo(((CSVEntity) element).getStartBalance().add(((CSVEntity) element).getMutation()));
				if (res != 0) {
					invalidEndBalanceRecords.put(((CSVEntity) element).getReference(),
							((CSVEntity) element).getDescription());
				}
			} else if (element instanceof Records) {
				int res = ((Records) element).getEndBalance()
						.compareTo(((Records) element).getStartBalance().add(((Records) element).getMutation()));
				if (res != 0) {
					invalidEndBalanceRecords.put(((Records) element).getReference(),
							((Records) element).getDescription());
				}
			}

		}

		return invalidEndBalanceRecords;
	}

	public static void generateReport(List allElements, Set<Integer> duplicateReferences,
			Map<Integer, String> misMatchedEndBalanceReferences) throws IOException {

		Writer writer = null;
		CSVWriter csvWriter = null;
		try {

			for (Object element : allElements) {
				if (element instanceof CSVEntity) {
					writer = Files.newBufferedWriter(Paths.get("./ReportCSV.csv"));
				} else if (element instanceof Records) {
					writer = Files.newBufferedWriter(Paths.get("./ReportXML.csv"));
				}
			}

			csvWriter = new CSVWriter(writer, CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER,
					csvWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
			csvWriter.writeNext(new String[] { "Duplicate References" });
			csvWriter.writeNext(new String[] { "Reference", "Description" });
			for (Integer duplicateReference : duplicateReferences) {
				for (Object element : allElements) {
					if (element instanceof CSVEntity) {
						if (((CSVEntity) element).getReference().equals(duplicateReference)) {
							logger.info("Duplicate Reference : {}, Description : {}",
									((CSVEntity) element).getReference(), ((CSVEntity) element).getDescription());
							csvWriter.writeNext(new String[] { Integer.toString(((CSVEntity) element).getReference()),
									((CSVEntity) element).getDescription() });

						}
					} else if (element instanceof Records) {
						if (((Records) element).getReference().equals(duplicateReference)) {
							logger.info("Duplicate  Reference :{} Description : {}", ((Records) element).getReference(),
									((Records) element).getDescription());
							csvWriter.writeNext(new String[] { Integer.toString(((Records) element).getReference()),
									((Records) element).getDescription() });

						}
					}

				}
			}
			csvWriter.writeNext(new String[] { "Mismatched End Balance", "" });
			csvWriter.writeNext(new String[] { "Reference", "Description" });

			for (Map.Entry<Integer, String> m : misMatchedEndBalanceReferences.entrySet()) {
				logger.info("Mismatched End Balance, Reference : {}, Decsription : {}", m.getKey(), m.getValue());
				csvWriter.writeNext(new String[] { Integer.toString(m.getKey()), m.getValue() });

			}
		} catch (IOException e) {
			// handle the Exception
		} finally {
			csvWriter.close();
		}

	}

}
