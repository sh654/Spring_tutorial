package com.techlabs.config;

import org.springframework.batch.item.ItemProcessor;

import com.techlabs.entity.Employee;

public class EmployeeProcessor implements ItemProcessor<Employee, Employee>{

	@Override
	public Employee process(Employee item) throws Exception {
		// TODO Auto-generated method stub
		return item;
	}

}
