package cts.rabobank.projects.csvxmlvalidator.exception;

import java.util.Date;

public class ExceptionDetails {

	private Date timestamp;
	private String message;

	public ExceptionDetails(Date timestamp, String message) {
		super();
		this.timestamp = timestamp;
		this.message = message;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
