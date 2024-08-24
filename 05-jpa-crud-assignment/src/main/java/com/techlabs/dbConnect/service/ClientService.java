package com.techlabs.dbConnect.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.techlabs.dbConnect.entity.Client;

public interface ClientService {

	
	Page<Client> getAllClient(int pageNo, int pageSize);
//	Page<Client> getClientByName(String companyName, int pageNo, int pageSize);
	void addClient(Client client);
	void deleteClient(int clientId);
	void updateClient(int clientId, Client client);
	Optional<Client> getClientById(int clientId);
	Page<Client> findByCompanyName(String companyName, int pageNo, int pageSize);
}
