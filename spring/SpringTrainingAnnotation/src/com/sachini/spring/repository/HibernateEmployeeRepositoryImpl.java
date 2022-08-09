package com.sachini.spring.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.sachini.spring.model.Employee;

@Repository("employeeRepository")
public class HibernateEmployeeRepositoryImpl implements EmployeesRepository {
	
	@Value("${name}")
	private String employeeName;
	@Value("${city}")
	private String EmployeeCity;
	
	@Override
	public List<Employee> getAllEmployees(){
		List<Employee> employees = new ArrayList<>();
		
		Employee employee = new Employee();
		employee.setEmployeeName(employeeName);
		employee.setEmployeeLocation(EmployeeCity);
		employees.add(employee);
		return employees;
	}
	
}
