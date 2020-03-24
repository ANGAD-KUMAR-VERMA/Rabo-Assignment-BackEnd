package cts.rabobank.projects.csvxmlvalidator.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "record")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Records {
	private Integer reference;
	private String accountNumber;
	private String description;
	private Float startBalance;
	private Float mutation;
	private Float endBalance;

	public Records() {
		super();
	}

	public Records(Integer reference, String accountNumber, String description, Float startBalance, Float mutation,
			Float endBalance) {
		super();
		this.reference = reference;
		this.accountNumber = accountNumber;
		this.description = description;
		this.startBalance = startBalance;
		this.mutation = mutation;
		this.endBalance = endBalance;
	}

	@XmlAttribute
	public Integer getReference() {
		return reference;
	}

	public void setReference(Integer reference) {
		this.reference = reference;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getStartBalance() {
		return startBalance;
	}

	public void setStartBalance(Float startBalance) {
		this.startBalance = startBalance;
	}

	public Float getMutation() {
		return mutation;
	}

	public void setMutation(Float mutation) {
		this.mutation = mutation;
	}

	public Float getEndBalance() {
		return endBalance;
	}

	public void setEndBalance(Float endBalance) {
		this.endBalance = endBalance;
	}

}
