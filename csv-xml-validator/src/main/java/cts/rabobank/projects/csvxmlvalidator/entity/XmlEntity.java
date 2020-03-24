package cts.rabobank.projects.csvxmlvalidator.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "records")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class XmlEntity {
	List<Records> record;

	public XmlEntity() {
		super();
	}

	public XmlEntity(List<Records> record) {
		super();
		this.record = record;
	}

	public List<Records> getRecord() {
		return record;
	}

	public void setRecord(List<Records> record) {
		this.record = record;
	}

}
