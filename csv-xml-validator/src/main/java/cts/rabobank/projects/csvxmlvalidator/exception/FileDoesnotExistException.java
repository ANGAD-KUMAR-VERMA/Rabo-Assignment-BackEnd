package cts.rabobank.projects.csvxmlvalidator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class FileDoesnotExistException extends RuntimeException {

	public FileDoesnotExistException(String exception) {
		super(exception);
	}
}
