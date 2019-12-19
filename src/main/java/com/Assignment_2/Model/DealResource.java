package com.Assignment_2.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import java.util.Date;
import java.util.List;


@Entity
@Table(name = "dealresource")
public class DealResource {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private long id;
	

	private String dealName;
	

	private Date  startOn;
	
	private Date closedOn;
	
	@NotBlank
	private String description;
	
	private Double amount;
	
	@NotBlank
	private String company;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDealName() {
		return dealName;
	}
	public void setDealName(String dealName) {
		this.dealName = dealName;
	}
	public Date getStartOn() {
		return startOn;
	}
	public void setStartOn(Date startOn) {
		this.startOn = startOn;
	}
	public Date getClosedOn() {
		return closedOn;
	}
	public void setClosedOn(Date closedOn) {
		this.closedOn = closedOn;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	

	
}
