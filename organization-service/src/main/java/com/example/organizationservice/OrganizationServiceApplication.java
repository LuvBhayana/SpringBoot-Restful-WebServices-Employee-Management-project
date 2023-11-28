package com.example.organizationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
		info = @Info(
				title = "Organization Service REST APIs ",
				description = "Oraganization Service REST APIs Documentation",
				version = "v1.0",
				contact = @Contact(
						name = "Pragathi",
						email = "pragathi@gmail.com",
						url = "https:www.organizationservice.com"
						),
				license = @License(
						name = "Apache 2.0",
						url = "https:www.organizationservice.com"
						)
				),
		externalDocs = @ExternalDocumentation(
				description = "Employee Service Doc",
				url = "https:www.organizationservice.com"
				)
		)
@SpringBootApplication
public class OrganizationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrganizationServiceApplication.class, args);
	}

}
