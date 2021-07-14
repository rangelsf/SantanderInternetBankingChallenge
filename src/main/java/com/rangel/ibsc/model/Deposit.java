package com.rangel.ibsc.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ApiModel(description = "Model for Deposit")
public class Deposit {

	@ApiModelProperty(notes = "Value to be deposited")
	@NotNull(message = "Value cannot be null")
	@Digits(integer = 100 ,fraction = 2, message = "Value must be only 2 decimal characters")
	private Double value;
	
	public Deposit(Double value) {
		super();
		this.value = value;
	}

	public Deposit() {
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
	
	
}
