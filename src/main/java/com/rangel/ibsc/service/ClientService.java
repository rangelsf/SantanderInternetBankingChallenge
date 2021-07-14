package com.rangel.ibsc.service;

import java.util.List;
import java.util.Optional;

import com.rangel.ibsc.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public interface ClientService {
	
	Client createClient(Client client);
	
	Client updateClient(Client client);
	
	List<Client> getAllClient();

	Page<Client> getClientPages(Pageable page);
	
	Client getClientById(long clientId);
	
	void deleteClient(long id);
	
	String withdraw(long id, Double value);
	
	String deposit(long id, Double value);

}
