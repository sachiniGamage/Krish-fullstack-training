package com.sachini.spring;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sachini.spring.config.ApplicationConfiguration;
import com.sachini.spring.model.Employee;
import com.sachini.spring.service.EmployeeService;
import com.sachini.spring.service.EmployeeServiceImpl;

public class Application {
	
	public static void main(String[] args) {
	
//	 ApplicationContext context  = new ClassPathXmlApplicationContext("applicationContexts.xml");
		ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
	
	EmployeeService employeeService = context.getBean("employeeService" , EmployeeService.class);
	System.out.println(employeeService.toString());
	
	EmployeeService employeeService2 = context.getBean("employeeService" , EmployeeService.class);
	System.out.println(employeeService2.toString());
	
	List<Employee> employees = employeeService.getAllEmployees();
	
	for(Employee employee:  employees) {
		System.out.println(employee.getEmployeeName() + " at "+ employee.getEmployeeLocation());
	}
			
	}
}
