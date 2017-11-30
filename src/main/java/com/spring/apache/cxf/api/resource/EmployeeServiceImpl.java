package com.spring.apache.cxf.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.apache.cxf.api.model.Employee;
import com.spring.apache.cxf.api.model.Response;
import com.spring.apache.cxf.api.repo.EmployeeRepository;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired(required = true)
	private EmployeeRepository repository;

	/*
	 * some cases autowire not work due to context issue so to avoid this we can
	 * use below pre process logic The current web application context (usually
	 * the one loaded by ContextLoaderListener) will be used for autowiring
	 * 
	 * @PostConstruct public void init() {
	 * SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	 * }
	 */

	public Response insert(Employee employee) {
		repository.save(employee);
		return new Response(true, "Record inserted");
	}

	public List<Employee> getEmployees() {
		return repository.findAll();
	}

}
