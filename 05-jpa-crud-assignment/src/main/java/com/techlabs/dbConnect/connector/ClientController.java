package com.techlabs.dbConnect.connector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.techlabs.dbConnect.entity.Client;
import com.techlabs.dbConnect.service.ClientService;

@RequestMapping("/clientapp")
@RestController
public class ClientController {
	
	@Autowired
	private ClientService clientService;

	@GetMapping("/client")
	public ResponseEntity<Page<Client>> getAllClients(@RequestParam(required = false) String companyName ,@RequestParam int pageNo,@RequestParam int pageSize){
		if(companyName != null) {
			return ResponseEntity.ok(clientService.findByCompanyName(companyName, pageNo, pageSize));
		}
		return ResponseEntity.ok(clientService.getAllClient(pageNo, pageSize));
	}
	
	@PostMapping("/client")
	public ResponseEntity<String> addClient(@RequestBody Client client){
		clientService.addClient(client);
		return ResponseEntity.ok("Added Successfully");
		
	}
	
	@DeleteMapping("/client")
	public ResponseEntity<String> deleteClient(int clientId){
		clientService.deleteClient(clientId);
		return ResponseEntity.ok("Deleted");
	}
	
	@PutMapping("/client/{clientId}")
	public ResponseEntity<String> updateClient(@PathVariable int clientId, @RequestBody Client client) {
	    clientService.updateClient(clientId, client);
	    return ResponseEntity.ok("Updated");
	}

	
}
