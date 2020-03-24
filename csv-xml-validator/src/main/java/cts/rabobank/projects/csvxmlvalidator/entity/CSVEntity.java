package cts.rabobank.projects.csvxmlvalidator.entity;

import com.opencsv.bean.CsvBindByName;

public class CSVEntity {

	@CsvBindByName
	private Integer reference;
	@CsvBindByName
	private String description;
	@CsvBindByName
	private String accountNumber;
	@CsvBindByName(column = "Start Balance")
	private Float startBalance;
	@CsvBindByName
	private Float mutation;
	@CsvBindByName(column = "End Balance")
	private Float endBalance;

	public CSVEntity() {

	}

	public CSVEntity(Integer reference, String description, String accountNumber, Float startBalance, Float endBalance,
			Float mutation) {
		super();
		this.reference = reference;
		this.description = description;
		this.accountNumber = accountNumber;
		this.startBalance = startBalance;
		this.endBalance = endBalance;
		this.mutation = mutation;
	}

	public Integer getReference() {
		return reference;
	}

	public void setReference(Integer reference) {
		this.reference = reference;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Float getStartBalance() {
		return startBalance;
	}

	public void setStartBalance(Float startBalance) {
		this.startBalance = startBalance;
	}

	public Float getEndBalance() {
		return endBalance;
	}

	public void setEndBalance(Float endBalance) {
		this.endBalance = endBalance;
	}

	public Float getMutation() {
		return mutation;
	}

	public void setMutation(Float mutation) {
		this.mutation = mutation;
	}

}
