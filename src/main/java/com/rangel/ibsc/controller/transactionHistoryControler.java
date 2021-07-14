package com.rangel.ibsc.controller;

import java.util.List
;


import com.rangel.ibsc.model.Client;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rangel.ibsc.model.TransactionHistory;
import com.rangel.ibsc.service.TransactionHistoryService;

import javax.validation.Valid;

@RestController
public class transactionHistoryControler {
	
	@Autowired
	private TransactionHistoryService transactionService;

	@ApiOperation(value = "Get deposits by client id",
			notes = "Get all deposits from a certain client",
			response = TransactionHistory.class)
	@GetMapping("/transactions/clients/{id}/deposits")
	public ResponseEntity<List<TransactionHistory>> getTransactionByClientIdAndTypeD(@PathVariable long id){
		return ResponseEntity.ok().body(transactionService.getAllTransactionHistoryByClientIdAndType(id, "D"));
	}

	@ApiOperation(value = "Get withdrawals by client id",
			notes = "Get all withdrawals from a certain client",
			response = TransactionHistory.class)
	@GetMapping("/transactions/clients/{id}/withdrawals")
	public ResponseEntity<List<TransactionHistory>> getTransactionByClientIdAndTypeW(@PathVariable long id){
		return ResponseEntity.ok().body(transactionService.getAllTransactionHistoryByClientIdAndType(id, "W"));
	}

	@ApiOperation(value = "Get all transactions by client id",
			notes = "Get all transactions from a certain client",
			response = TransactionHistory.class)
	@GetMapping("/transactions/clients/{id}")
	public ResponseEntity<List<TransactionHistory>> getTransactionByClientId(@PathVariable long id){
		return ResponseEntity.ok().body(transactionService.getAllTransactionHistoryByClientId(id));
	}

	@ApiOperation(value = "Get all transactions",
			notes = "Get all transactions from the database",
			response = TransactionHistory.class)
	@GetMapping("/transactions")
	public ResponseEntity<List<TransactionHistory>> getAllTransactions(){
		return ResponseEntity.ok().body(transactionService.getAllTransactionHistory());
	}

	@ApiOperation(value = "Get transaction by id",
			notes = "Get a transaction using id",
			response = TransactionHistory.class)
	@GetMapping("/transactions/{id}")
	public ResponseEntity<TransactionHistory> getTransactionById(@PathVariable long id){
		return ResponseEntity.ok().body(transactionService.getTransactionHistoryById(id));
	}

	@ApiOperation(value = "Create a transaction",
			notes = "Put a new transaction in the database",
			response = TransactionHistory.class)
	@PostMapping("/transactions")
	public ResponseEntity<TransactionHistory> createTransaction(@Valid @RequestBody TransactionHistory transaction){
		return ResponseEntity.ok().body(transactionService.createTransaction(transaction));	
	}

	@ApiOperation(value = "Update a transaction",
			notes = "Update an existing transaction",
			response = TransactionHistory.class)
	@PutMapping("/transactions/{id}")
	public ResponseEntity<TransactionHistory> updateTransaction(@PathVariable long id,@Valid @RequestBody TransactionHistory transaction){
		transaction.setId(id);
		return ResponseEntity.ok().body(transactionService.updateTransaction(transaction));	

	}

	@ApiOperation(value = "Delete a transaction",
			notes = "Delete an existing transaction",
			response = TransactionHistory.class)
	@DeleteMapping("/transactions/{id}")
	public HttpStatus deleteTransaction(@PathVariable long id){
		this.transactionService.deleteTransactionHistory(id);
		
		return HttpStatus.OK;
		
	}
	
}
