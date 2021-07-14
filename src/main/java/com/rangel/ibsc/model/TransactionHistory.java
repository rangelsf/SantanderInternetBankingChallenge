package com.rangel.ibsc.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.CreationTimestamp;

@ApiModel(description = "Info about the transaction")
@Entity
@Table(name = "transactions")
@SequenceGenerator(name = "seq_transaction", sequenceName = "seq_transaction", initialValue = 1, allocationSize = 1)
public class TransactionHistory {

	@ApiModelProperty(notes = "Unique ID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ApiModelProperty(notes = "Client ID that made the transaction")
	@Column(name = "client_id")
	@NotNull(message = "Client ID cannot be null")
	private long clientId;

	@ApiModelProperty(notes = "Balance before the transaction")
	@Column(name = "old_balance")
	@NotNull(message = "Old balance cannot be null")
	@Digits(integer = 100 ,fraction = 2, message = "Old balance must be only 2 decimal characters")
	private Double oldBalance;

	@ApiModelProperty(notes = "Balance after the transaction")
	@Column(name = "new_balance")
	@NotNull(message = "New balance cannot be null")
	@Digits(integer = 100 ,fraction = 2, message = "New balance must be only 2 decimal characters")
	private Double newBalance;

	@ApiModelProperty(notes = "Amount that was used")
	@Column(name = "amount")
	@NotNull(message = "Amount cannot be null")
	@Digits(integer = 100 ,fraction = 2, message = "Amount must be only 2 decimal characters")
	private Double amount;

	@ApiModelProperty(notes = "Value of the tax if has it")
	@Column(name = "tax")
	private Double tax;

	@ApiModelProperty(notes = "Type of transaction - W = withdrawal -- D = Deposit")
	@Column(name = "type")
	@Pattern(regexp = "[dDwW]", message = "transaction must be W or D")
	private String type;

	@ApiModelProperty(notes = "Date Time Stamp of the transaction")
	@CreationTimestamp
	@Column(name = "updated_at")
	private Date updatedAt;

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getClientId() {
		return clientId;
	}

	public void setClientId(long clientId) {
		this.clientId = clientId;
	}

	public Double getOldBalance() {
		return oldBalance;
	}

	public void setOldBalance(Double oldBalance) {
		this.oldBalance = oldBalance;
	}

	public Double getNewBalance() {
		return newBalance;
	}

	public void setNewBalance(Double newBalance) {
		this.newBalance = newBalance;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	

	
	
	
}
