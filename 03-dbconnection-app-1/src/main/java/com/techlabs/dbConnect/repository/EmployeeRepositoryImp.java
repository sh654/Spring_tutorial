package com.techlabs.dbConnect.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.techlabs.dbConnect.entity.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
@Repository
public class EmployeeRepositoryImp implements EmployeeRepository{

	@Autowired
	private EntityManager manager;
	
	@Override
	public List<Employee> getAllEmployee() {
		// TODO Auto-generated method stub
		TypedQuery<Employee> query = manager.createQuery("select s from Employee s", Employee.class);
		return query.getResultList();
	}

	@Override
	public Employee getEmployee(int empid) {
		// TODO Auto-generated method stub
		return manager.find(Employee.class, empid);
	}

	@Override
	@Transactional
	public void addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		manager.persist(employee);
	}

	@Override
	@Transactional
	public void deleteEmployee(int empid) {
		// TODO Auto-generated method stub
		Employee employee = manager.find(Employee.class, empid);
		if(employee != null) {
			manager.remove(employee);
		}
	}

	@Override
	public List<Employee> getAllEmployeeByName(String name) {
		// TODO Auto-generated method stub
		TypedQuery<Employee> query = manager.createQuery("select s from Employee s where s.name=:theName", null);
		query.setParameter("theName", name);
		return query.getResultList();
	}

}
