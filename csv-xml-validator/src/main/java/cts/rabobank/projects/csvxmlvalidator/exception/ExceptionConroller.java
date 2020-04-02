package cts.rabobank.projects.csvxmlvalidator.exception;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import cts.rabobank.projects.csvxmlvalidator.entity.CustomMessage;

@ControllerAdvice
public class ExceptionConroller {
	private static final Logger logger = LoggerFactory.getLogger(ExceptionConroller.class);

	@ExceptionHandler(value = { FileDoesnotExistException.class, UnExpectedFileFormatException.class })
	public ResponseEntity<CustomMessage> handleFileDoesNotExistException(Exception exception) {
		logger.error("Exception : {}", exception.getMessage());
		CustomMessage apiCustomMessage = new CustomMessage(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
		return new ResponseEntity<>(apiCustomMessage, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { UnknownFileException.class })
	public ResponseEntity<CustomMessage> handleUnknownFileException(Exception exception) {
		logger.error("Exception : {}", exception.getMessage());
		CustomMessage apiCustomMessage = new CustomMessage(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
		return new ResponseEntity<>(apiCustomMessage, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<CustomMessage> handleAllExceptions(Exception exception) {
		logger.error("Exception : {}", exception.getMessage());
		CustomMessage apiCustomMessage = new CustomMessage(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
		return new ResponseEntity<>(apiCustomMessage, HttpStatus.BAD_REQUEST);
	}

}
