package com.techlabs.dbConnect.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techlabs.dbConnect.entity.Client;
import com.techlabs.dbConnect.repository.ClientRepository;

@Service
public class ClientServiceImp implements ClientService{

	@Autowired
	private ClientRepository clientRepo;
	
	@Override
	public Page<Client> getAllClient(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		return clientRepo.findAll(pageable);
	}



	@Override
	public void addClient(Client client) {
		// TODO Auto-generated method stub
		clientRepo.save(client);
	}

	@Override
	public void deleteClient(int clientId) {
		// TODO Auto-generated method stub
		clientRepo.deleteById(clientId);
	}

	@Override
	public void updateClient(int clientId, Client client) {
		// TODO Auto-generated method stub
		Optional<Client> clientUpdate = clientRepo.findById(clientId);
		if(clientUpdate.isPresent()) {
			Client clientToget =  clientUpdate.get();
			clientToget.setCompanyName(client.getCompanyName());
			clientToget.setRegistrationNumber(client.getRegistrationNumber());
			clientToget.setContactPerson(client.getContactPerson());
			clientToget.setContactEmail(client.getContactEmail());
			clientToget.setContactNumber(client.getContactNumber());
			clientToget.setAddress(client.getAddress());
			clientToget.setStatus(client.getStatus());
			clientToget.setCreationDate(client.getCreationDate());
			clientToget.setKycStatus(client.getKycStatus());
			
			clientRepo.save(clientToget);
		}
		else {
            throw new RuntimeException("No record for:" +clientId+ " not found.");
        }
	}

	@Override
	public Optional<Client> getClientById(int clientId) {
		// TODO Auto-generated method stub
		return clientRepo.findById(clientId);		
	}


	@Override
	public Page<Client> findByCompanyName(String companyName, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		return clientRepo.findClientByCompanyName(companyName, pageable);
	}
	
	

}
