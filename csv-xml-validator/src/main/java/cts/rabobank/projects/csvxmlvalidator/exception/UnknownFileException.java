package cts.rabobank.projects.csvxmlvalidator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UnknownFileException extends RuntimeException {

	public UnknownFileException(String exception) {
		super(exception);
	}
}
