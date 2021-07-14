package com.rangel.ibsc.model;

import java.text.SimpleDateFormat;
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
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

@ApiModel(description = "Info about the client")
@Entity
@Table(name = "clients")
@SequenceGenerator(name = "seq_client", sequenceName = "seq_client", initialValue = 1, allocationSize = 1)
public class Client {

	@ApiModelProperty(notes = "Unique ID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull(message = "Id cannot be null")
	private long id;

	@ApiModelProperty(notes = "Client's name")
	@Column(name = "name")
	@NotNull(message = "Name cannot be null")
	@NotEmpty(message = "Name cannot be empty")
	@Size(min=2,max=30,message = "Name must be > 2 and < 30")
	private String name;

	@ApiModelProperty(notes = "Client's type of plan - true = has - false = no plan")
	@Column(name = "exclusive_plan")
	@NotNull(message = "Plan cannot be null")
	private Boolean exclusivePlan;

	@ApiModelProperty(notes = "Client's balance on the account")
	@Column(name = "balance")
	@NotNull(message = "Balance cannot be null")
	@Digits(integer = 100 ,fraction = 2, message = "Balance must be only 2 decimal characters")
	private Double balance;

	@ApiModelProperty(notes = "Client's account number")
	@Column(name = "account_number")
	@NotNull(message = "Account number cannot be null")
	private int accountNumber;

	@ApiModelProperty(notes = "Client's birth date")
	@Column(name = "birth_date")
	@JsonFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "Birth Date cannot be null")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date birthDate;

	public Client() {

	}

	public Client(long id, String name, Boolean exclusivePlan, Double balance, int accountNumber, Date birthDate) {
		this.id = id;
		this.name = name;
		this.exclusivePlan = exclusivePlan;
		this.balance = balance;
		this.accountNumber = accountNumber;
		this.birthDate = birthDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getExclusivePlan() {
		return exclusivePlan;
	}

	public void setExclusivePlan(Boolean exclusivePlan) {
		this.exclusivePlan = exclusivePlan;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		
		this.birthDate = birthDate;
	}


	public String toJsonString() {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String formattedDate = format.format(birthDate);
		return "{" +
				"\"id\" : " + id +
				", \"name\" : \"" + name + "\"" +
				", \"exclusivePlan\" : " + exclusivePlan  +
				", \"balance\" : " + balance +
				", \"accountNumber\" : " + accountNumber +
				", \"birthDate\" : \"" + formattedDate + "\"" +
				'}';
	}
}
