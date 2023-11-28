package com.example.organizationservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.organizationservice.dto.OrganizationDto;
import com.example.organizationservice.service.OrganizationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@Tag(
		name = "Organization-Service OrganizationController",
		description = "OrganizationController exposes REST APIs for Organization-Service"
		)
@RestController
@AllArgsConstructor
@RequestMapping("api/organizations")
public class OrganizationController {
	
	private OrganizationService organizationService;
	
	@Operation(
			summary = "Save Organization REST API",
			description = "Save Organization is used to save a organization object in a database"
			)
	@ApiResponse(
			responseCode = "201",
			description = "Http Status 201 CREATED"
			)
	@PostMapping
	public ResponseEntity<OrganizationDto> saveOrganization(@RequestBody OrganizationDto organizationDto) {
		
		OrganizationDto saveOrganizationDto = organizationService.saveOrganization(organizationDto);
		return new ResponseEntity<OrganizationDto>(saveOrganizationDto,HttpStatus.CREATED);
	}
	
	@Operation(
			summary = "Get Organization REST API",
			description = "Get Organization is used to get an organization object from the database"
			)
	@ApiResponse(
			responseCode = "200",
			description = "Http Status 200 SUCCESS"
			)
	@GetMapping("{code}")
	public ResponseEntity<OrganizationDto> getOrganization(@PathVariable("code") String organizationCode) {
		
		OrganizationDto organizationDto = organizationService.getOrganizationByCode(organizationCode);
		return new ResponseEntity<OrganizationDto>(organizationDto,HttpStatus.OK);
	}

}
