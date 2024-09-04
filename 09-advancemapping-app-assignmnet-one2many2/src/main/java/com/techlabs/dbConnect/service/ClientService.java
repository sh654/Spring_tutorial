package com.techlabs.dbConnect.service;

import java.util.List;

import com.techlabs.dbConnect.dto.ClientDto;
import com.techlabs.dbConnect.entity.Client;
import com.techlabs.dbConnect.entity.Employee;

public interface ClientService {
	
	Client addClient(ClientDto clientDto);
	
	Client allocateEmployees(int clientId, List<Employee> employee);

	boolean deleteClient(int clientId);

	List<Client> getAllClients();
	
}
