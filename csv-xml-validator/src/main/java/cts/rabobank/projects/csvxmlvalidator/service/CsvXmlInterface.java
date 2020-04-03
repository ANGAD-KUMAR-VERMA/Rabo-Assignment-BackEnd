package cts.rabobank.projects.csvxmlvalidator.service;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.List;

import javax.xml.bind.JAXBException;

public interface CsvXmlInterface {

	
	public void validateAndGenerateReport(String filename) throws IOException, JAXBException;
	
	public List uploadFile(String filename) throws  NoSuchFileException, IOException, JAXBException;
}
