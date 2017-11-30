package com.spring.apache.cxf.api.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.spring.apache.cxf.api.model.Employee;
import com.spring.apache.cxf.api.model.Response;

@Path("/resource")
public interface EmployeeService {

	@Path("/add")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insert(Employee employee);

	@Path("/getAll")
	@GET
	@Consumes(value = "application/json")
	@Produces(value = "application/json")
	public List<Employee> getEmployees();
}
