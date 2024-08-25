package com.techlabs.dbConnect.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlabs.dbConnect.dto.ClientDto;
import com.techlabs.dbConnect.entity.Client;
import com.techlabs.dbConnect.entity.Employee;
import com.techlabs.dbConnect.repository.ClientRepo;
import com.techlabs.dbConnect.repository.EmployeeRepo;

@Service
public class ClientServiceImp implements ClientService{
	
	@Autowired
	private ClientRepo clientRepo;
	
	@Autowired
	private EmployeeRepo employeeRepo;

	@Override
	public Client addClient(ClientDto clientDto) {
		// TODO Auto-generated method stub
		Client client = new Client();
		client.setCompanyName(clientDto.getCompanyName());
		client.setRegistationNumber(clientDto.getRegistationNumber());
		client.setContactPerson(clientDto.getContactPerson());
		client.setContactEmail(clientDto.getContactEmail());
		client.setContactNumber(clientDto.getContactNumber());
		client.setAddress(clientDto.getAddress());
		client.setStatus(clientDto.getStatus());
		client.setKycStatus(clientDto.getKycStatus());
		client.setCreationDate(clientDto.getCreationDate());
		return clientRepo.save(client);
	}

	@Override
	public Client allocateEmployees(int clientId, List<Employee> employees) {
		// TODO Auto-generated method stub
		Client clientDb;
		
		Optional<Client> clientOptional = clientRepo.findById(clientId);
		if(!clientOptional.isPresent())
			return null;
		
		clientDb = clientOptional.get();
		List<Employee> employeeDb = clientDb.getEmployee();
		
		employees.forEach((employee) -> {
			Employee temp = employeeRepo.findById(employee.getEmployeeId()).get();
			temp.setClient(clientDb);
			employeeDb.add(temp);
		});
		
		clientDb.setEmployee(employeeDb);
		return clientRepo.save(clientDb);
	}
	
	 @Override
	    public boolean deleteClient(int clientId) {
	        Optional<Client> clientOptional = clientRepo.findById(clientId);
	        if (clientOptional.isPresent()) {
	            clientRepo.deleteById(clientId);
	            return true;
	        }
	        return false;
	    }

	    @Override
	    public List<Client> getAllClients() {
	        return clientRepo.findAll();
	    }

}
