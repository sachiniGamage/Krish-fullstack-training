package com.sachini.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sachini.spring.model.Employee;
import com.sachini.spring.repository.EmployeesRepository;
import com.sachini.spring.repository.HibernateEmployeeRepositoryImpl;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {
	
//	@Autowired
	private EmployeesRepository employeeRepository;
	
	public EmployeeServiceImpl() {
		System.out.println("Default constructor");
	}
//	@Autowired
	public EmployeeServiceImpl(EmployeesRepository employeesRepository) {
		System.out.println("Default constructor");
		this.employeeRepository = employeesRepository;
	}
	
	
	

	public EmployeesRepository getEmployeeRepository() {
		return employeeRepository;
	}

	@Autowired
	public void setEmployeeRepository(EmployeesRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}




	public List<Employee> getAllEmployees(){
		return employeeRepository.getAllEmployees();
	}

}
