package cts.rabobank.projects.csvxmlvalidator.entity;

import java.math.BigDecimal;

import com.opencsv.bean.CsvBindByName;

public class CSVEntity {

	@CsvBindByName
	private Integer reference;
	@CsvBindByName
	private String description;
	@CsvBindByName
	private String accountNumber;
	@CsvBindByName(column = "Start Balance")
	private BigDecimal startBalance;
	@CsvBindByName
	private BigDecimal mutation;
	@CsvBindByName(column = "End Balance")
	private BigDecimal endBalance;

	public CSVEntity() {

	}

	public CSVEntity(Integer reference, String description, String accountNumber, BigDecimal startBalance,
			BigDecimal mutation, BigDecimal endBalance) {
		super();
		this.reference = reference;
		this.description = description;
		this.accountNumber = accountNumber;
		this.startBalance = startBalance;
		this.mutation = mutation;
		this.endBalance = endBalance;
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

	public BigDecimal getStartBalance() {
		return startBalance;
	}

	public void setStartBalance(BigDecimal startBalance) {
		this.startBalance = startBalance;
	}

	public BigDecimal getMutation() {
		return mutation;
	}

	public void setMutation(BigDecimal mutation) {
		this.mutation = mutation;
	}

	public BigDecimal getEndBalance() {
		return endBalance;
	}

	public void setEndBalance(BigDecimal endBalance) {
		this.endBalance = endBalance;
	}

}
