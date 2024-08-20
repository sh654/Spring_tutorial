package com.techlabs.dbconnect.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.dbconnect.entity.Payment;
import com.techlabs.dbconnect.service.PaymentService;

@RequestMapping("/paymentapp")
@RestController
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	@GetMapping("/payments")
	public ResponseEntity<List<Payment>> getAllPayments(){
		return ResponseEntity.ok(paymentService.getAllPayments());
	}
	
	@PostMapping("/payments")
	public ResponseEntity<String> addEmployee(@RequestBody Payment payment){
		paymentService.addPayments(payment);
		return ResponseEntity.ok("Added Successfully");
	}
	
	@PutMapping("/payments")
	public ResponseEntity<String> updateEmployee(@RequestBody Payment payment){
		paymentService.updatePayments(payment);
		return ResponseEntity.ok("Update Successfull");
	}
	
	@DeleteMapping("/payments/{paymentId}")
	public ResponseEntity<String> deleteEmployee(@PathVariable int paymentId){
		paymentService.deletePayments(paymentId);
		return ResponseEntity.ok("Delete Successfull");
	}
	
}
