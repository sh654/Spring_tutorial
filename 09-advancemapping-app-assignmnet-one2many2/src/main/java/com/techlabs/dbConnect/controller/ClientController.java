package com.techlabs.dbConnect.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.dbConnect.dto.ClientDto;
import com.techlabs.dbConnect.entity.Client;
import com.techlabs.dbConnect.entity.Employee;
import com.techlabs.dbConnect.service.ClientService;



@RestController
@RequestMapping("/bank")
public class ClientController {

	@Autowired
	private ClientService clientService;
	
	@PostMapping("/clients")
	public ResponseEntity<Client> addClient(@RequestBody ClientDto clientDto) {
		return ResponseEntity.ok(clientService.addClient(clientDto));
	}
	
	@PutMapping("/clients/employees") // to update employee added
	public ResponseEntity<Client> allocateEmployees(@RequestParam int clientId,@RequestBody List<Employee> employees) {
		
		return ResponseEntity.ok(clientService.allocateEmployees(clientId, employees));
		
	}
	
	@DeleteMapping("/clients/{clientId}")
    public ResponseEntity<String> deleteClient(@PathVariable int clientId) {
        boolean isDeleted = clientService.deleteClient(clientId);
        if (isDeleted) {
            return ResponseEntity.ok("Deleted");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/clients")
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        return ResponseEntity.ok(clients);
    }
}
