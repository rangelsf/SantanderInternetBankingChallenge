package com.rangel.ibsc.controller;

import java.util.List;
import java.util.Optional;


import com.rangel.ibsc.exceptions.ClientNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.rangel.ibsc.model.Client;
import com.rangel.ibsc.model.Deposit;
import com.rangel.ibsc.model.Withdrawal;
import com.rangel.ibsc.service.ClientService;

import javax.validation.Valid;

@RestController
@Validated
public class ClientController {
	
	@Autowired
	private ClientService clientService;

	@ApiOperation(value = "Find clients in pages",
				notes = "Return clients in a page, can use parameters page(int) and size(int)",
				response = Client.class)
	@GetMapping("/clients/pages")
	@ResponseBody
	public ResponseEntity<Page<Client>> getClientPages(Pageable page){
		return ResponseEntity.ok().body(clientService.getClientPages(page));
	}

	@ApiOperation(value = "Find all clients",
			notes = "Return all clients in the database",
			response = Client.class)
	@GetMapping("/clients")
	@ResponseBody
	public ResponseEntity<List<Client>> getAllClient(){
		return ResponseEntity.ok().body(clientService.getAllClient());
	}

	@ApiOperation(value = "Information about one client",
			notes = "Return the client of the sent ID",
			response = Client.class)
	@GetMapping("/clients/{id}")
	public ResponseEntity<Client> getClientById(@PathVariable long id){
		return ResponseEntity.ok().body(clientService.getClientById(id));
	}

	@ApiOperation(value = "Create a client",
			notes = "Create a client on the database",
			response = Client.class)
	@PostMapping("/clients")
	public ResponseEntity<Client> createClient(@Valid @RequestBody Client client){
		return ResponseEntity.ok().body(clientService.createClient(client));
	}

	@ApiOperation(value = "Update a client",
			notes = "Update the client of the sent ID",
			response = Client.class)
	@PutMapping("/clients/{id}")
	public ResponseEntity<Client> updateClient(@PathVariable long id,@Valid @RequestBody Client client){
		client.setId(id);
		return ResponseEntity.ok().body(clientService.updateClient(client));

	}

	@ApiOperation(value = "Delete a client",
			notes = "Delete the client of the sent ID",
			response = Client.class)
	@DeleteMapping("/clients/{id}")
	public HttpStatus deleteClient(@PathVariable long id){
		this.clientService.deleteClient(id);
		
		return HttpStatus.OK;
		
	}

	@ApiOperation(value = "Withdrawal",
			notes = "Withdrawal a value from the balance and returns a string confirming",
			response = Withdrawal.class)
	@PostMapping("/clients/withdrawal/{id}")
	public ResponseEntity<String> withdrawal(@PathVariable long id,@Valid @RequestBody Withdrawal withdrawal){
		//objectNode.get("value").asDouble();
		Double value = withdrawal.getValue();
		
		return ResponseEntity.ok().body(clientService.withdraw(id, value));
		
	}

	@ApiOperation(value = "Deposit",
			notes = "Deposit a value in the balance and returns a string confirming")
	@PostMapping("/clients/deposit/{id}")
	public ResponseEntity<String> deposit(@PathVariable long id,@Valid @RequestBody Deposit deposit){
		Double value = deposit.getValue();
			
		return ResponseEntity.ok().body(clientService.deposit(id, value));
		
	}
}
