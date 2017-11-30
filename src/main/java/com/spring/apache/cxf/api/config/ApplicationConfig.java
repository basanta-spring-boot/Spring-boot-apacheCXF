package com.spring.apache.cxf.api.config;

import java.util.Arrays;

import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.spring.apache.cxf.api.resource.EmployeeService;

@Configuration
public class ApplicationConfig {
	@Autowired
	private Bus bus;
	@Autowired
	private EmployeeService service;

	@Bean
	public Server rsServer() {
		JAXRSServerFactoryBean endpoint = new JAXRSServerFactoryBean();
		endpoint.setBus(bus);
		endpoint.setAddress("/");
		endpoint.setProvider(new JacksonJsonProvider());
		/*
		 * if i will pass direct Service impl object as below then when my
		 * application will start always it will create new instance it will not
		 * point to ur impl rest resource so for this issue in your service impl
		 * autowire will not work cause spring not consider ur service , as u r
		 * passing new object here ;
		 */
		
		/*
		 * endpoint.setServiceBeans(Arrays.<Object>asList(new
		 * EmployeeServiceImpl()));
		 */
		endpoint.setServiceBeans(Arrays.<Object>asList(service));

		return endpoint.create();
	}

	@Bean
	public ServletRegistrationBean cxfServlet() {
		final ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new CXFServlet(), "/*");
		servletRegistrationBean.setLoadOnStartup(1);
		return servletRegistrationBean;
	}
}
