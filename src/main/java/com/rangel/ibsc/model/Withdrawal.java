package com.rangel.ibsc.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ApiModel(description = "Model for Withdrawal")
public class Withdrawal {

	@ApiModelProperty(notes = "Value to be withdrawn")
	@NotNull(message = "Value cannot be null")
	@Digits(integer = 100 ,fraction = 2, message = "Value must be only 2 decimal characters")
	private Double value;
	
	public Withdrawal(Double value) {
		super();
		this.value = value;
	}

	public Withdrawal() {
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
	
	
}
