package com.sachini.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.sachini.spring.repository.EmployeesRepository;
import com.sachini.spring.repository.HibernateEmployeeRepositoryImpl;
import com.sachini.spring.service.EmployeeService;
import com.sachini.spring.service.EmployeeServiceImpl;

@Configuration
@ComponentScan("com.sachini")
@PropertySource("application.properties")
public class ApplicationConfiguration {
	
	@Bean(name = "employeeService")
	@Scope("prototype")
	public EmployeeService getEmployeeService() {
		EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();
//		employeeServiceImpl.setEmployeeRepository(getEmployeeRepository());
		return new EmployeeServiceImpl();
	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer getPropertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
//	@Bean(name = "employeeRepository")
//	public EmployeesRepository getEmployeeRepository() {
//		return new HibernateEmployeeRepositoryImpl();
//	}

}
