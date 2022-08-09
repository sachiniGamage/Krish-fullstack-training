package com.sachini.spring.repository;

import java.util.List;

import com.sachini.spring.model.Employee;

public interface EmployeesRepository {

	List<Employee> getAllEmployees();

}