package com.example.employeeservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeeservice.Dto.APIResponseDto;
import com.example.employeeservice.Dto.EmployeeDto;
import com.example.employeeservice.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@Tag(
		name = "Employee-Service EmployeeController",
		description = "EmployeeController exposes REST APIs for Employee-Service"
		)
@RestController
@AllArgsConstructor
@RequestMapping("api/employees")
public class EmployeeController {
	
	private EmployeeService employeeService;
	
	@Operation(
			summary = "Save Employee REST API",
			description = "Save Employee is used to save an employee object in a database"
			)
	@ApiResponse(
			responseCode = "201",
			description = "Http Status 201 CREATED"
			)
	@PostMapping
	public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto) {
		
		EmployeeDto savedEmployee =  employeeService.saveEmployee(employeeDto);
		return new ResponseEntity<EmployeeDto>(savedEmployee, HttpStatus.CREATED);
	}

	@Operation(
			summary = "Get Employee REST API",
			description = "Get Employee is used to get an employee object from the database"
			)
	@ApiResponse(
			responseCode = "200",
			description = "Http Status 200 SUCCESS"
			)
	@GetMapping("{id}")
	public ResponseEntity<APIResponseDto> getEmployeeById(@PathVariable("id") Long employeeId) {
		
		APIResponseDto apiResponseDto = employeeService.getEmployeeById(employeeId);
		return new ResponseEntity<APIResponseDto>(apiResponseDto, HttpStatus.OK);
	}
}
