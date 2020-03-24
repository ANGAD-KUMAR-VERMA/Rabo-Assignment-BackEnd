package cts.rabobank.projects.csvxmlvalidator.exception;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionConroller {
	private static final Logger logger = LoggerFactory.getLogger(ExceptionConroller.class);

	@ExceptionHandler(UnknownFileException.class)
	public ResponseEntity<Object> exception(UnknownFileException exception) {
		logger.debug("Unknown File Exception : {}", exception.getMessage());
		return new ResponseEntity<>("Unknown File Type", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionDetails> handleAllExceptions(Exception exception) {
		logger.debug("Exception : {}", exception.getMessage());
		ExceptionDetails exceptionDetails = new ExceptionDetails(new Date(), exception.getMessage());
		return new ResponseEntity<>(exceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
