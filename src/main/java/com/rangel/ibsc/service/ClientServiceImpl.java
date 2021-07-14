package com.rangel.ibsc.service;

import java.util.List;
import java.util.Optional;

import com.rangel.ibsc.util.clientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rangel.ibsc.exceptions.ClientNotFoundException;
import com.rangel.ibsc.exceptions.WithdrawalException;
import com.rangel.ibsc.model.Client;
import com.rangel.ibsc.model.TransactionHistory;
import com.rangel.ibsc.repository.ClientRepository;
import com.rangel.ibsc.repository.TransactionHistoryRepository;



@Service
@Transactional
public class ClientServiceImpl implements ClientService{
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private TransactionHistoryRepository transactionRepository;

	@Override
	public Page<Client> getClientPages(Pageable page) {
		return this.clientRepository.findAll(page);
	}
	
	@Override
	public Client createClient(Client client) {
		return clientRepository.save(client);
	}

	@Override
	public Client updateClient(Client client) {
		Optional<Client> clientDb = this.clientRepository.findById(client.getId());
		
		if(clientDb.isPresent()) {
			Client clientUpdate = clientDb.get();
			clientUpdate.setId(client.getId());
			clientUpdate.setName(client.getName());
			clientUpdate.setExclusivePlan(client.getExclusivePlan());
			clientUpdate.setBalance(client.getBalance());
			clientUpdate.setAccountNumber(client.getAccountNumber());
			clientUpdate.setBirthDate(client.getBirthDate());
			
			clientRepository.save(clientUpdate);
			
			return clientUpdate;
		}else {
			throw new ClientNotFoundException("Cannot update client with id: " + client.getId());
		}
		
		
	}

	@Override
	public List<Client> getAllClient() {
		return this.clientRepository.findAll();
	}

	@Override
	public Client getClientById(long clientId) {
		Optional<Client> clientDb = this.clientRepository.findById(clientId);
		
		if(clientDb.isPresent()) {
			return clientDb.get();
		}else {
			throw new ClientNotFoundException("Cannot get client with id: " + clientId);
		}
	}

	@Override
	public void deleteClient(long id) {
		Optional<Client> clientDb = this.clientRepository.findById(id);
		
		if(clientDb.isPresent()) {
			this.clientRepository.delete(clientDb.get());
		}else {
			throw new ClientNotFoundException("Cannot delete client with id: " + id);
		}
		
	}
	
	@Override
	public String withdraw(long id, Double withdrawValue) {
		Optional<Client> clientDb = this.clientRepository.findById(id);
		
		if(withdrawValue <= 0) {
			return "Cannot withdraw less than zero";
		}
		
		
		if(clientDb.isPresent()) {
			Double currentBalance = clientDb.get().getBalance();
			Double newBalance = 0.0;
			Double tax = 0.0;
						
			if(currentBalance - withdrawValue < 0) {
				return "Cannot withdraw more money than you have";
			}
			
			
			if(clientDb.get().getExclusivePlan() == true) { //Isento de pagar
				newBalance = currentBalance - withdrawValue;
				
			}else if(withdrawValue <= 100) { //Isento de pagar
				newBalance = currentBalance - withdrawValue;
				
			}else if(clientDb.get().getExclusivePlan() == false 
					&& withdrawValue > 100 
					&& withdrawValue <= 300){ //Taxa 0.4%
				tax = withdrawValue * 0.004;
				newBalance = (currentBalance - withdrawValue) - tax;
				
			}else if(clientDb.get().getExclusivePlan() == false && withdrawValue > 300){ //Taxa 1%
				tax = withdrawValue * 0.01;
				newBalance = (currentBalance - withdrawValue) - tax;
			}else {
				throw new WithdrawalException("Unexpected outcome in withdrawal");
			}
			
			
			//salva as mudancas no cliente			
			Client clientUpdate = clientDb.get();
			clientUpdate.setBalance(clientUtils.truncateDecimal(newBalance,2).doubleValue());
			clientRepository.save(clientUpdate);

			//salva o historico da transacao
			TransactionHistory transaction = new TransactionHistory();
			transaction.setClientId(clientDb.get().getId());
			transaction.setAmount(withdrawValue);
			transaction.setOldBalance(currentBalance);
			transaction.setNewBalance(clientUtils.truncateDecimal(newBalance,2).doubleValue());
			transaction.setTax(tax);
			transaction.setType("W");
			transactionRepository.save(transaction);

			return "The new balance is " + newBalance.toString();
		}else {
			throw new ClientNotFoundException("Cannot find client with id: " + id);
		}
		
	}

	@Override
	public String deposit(long id, Double depositValue) {
		Optional<Client> clientDb = this.clientRepository.findById(id);
		
		if(clientDb.isPresent()) {
			Double currentBalance = clientDb.get().getBalance();
			Double newBalance = currentBalance + depositValue;			
			
			//salva novo saldo do cliente
			Client clientUpdate = clientDb.get();
			clientUpdate.setBalance(newBalance);
			clientRepository.save(clientUpdate);
			
			
			//salva o historico da transacao
			TransactionHistory transaction = new TransactionHistory();
			transaction.setClientId(clientDb.get().getId());
			transaction.setAmount(depositValue);
			transaction.setOldBalance(currentBalance);
			transaction.setNewBalance(newBalance);
			transaction.setType("D");
			transactionRepository.save(transaction);
			
			return "The new balance is " + newBalance.toString();
		}else {
			throw new ClientNotFoundException("Cannot find client with id: " + id);
		}
		
	}
	
	
	

}
