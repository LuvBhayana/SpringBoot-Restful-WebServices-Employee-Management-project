package com.example.departmentservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.departmentservice.Dto.DepartmentDto;
import com.example.departmentservice.service.DepartmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@Tag(
		name = "Department-Service DepartmentController",
		description = "DepartmentController exposes REST APIs for Department-Service"
		)
@RestController
@AllArgsConstructor
@RequestMapping("api/departments")
public class DepartmentController {
	
	private DepartmentService departmentService;
	
	@Operation(
			summary = "Save Department REST API",
			description = "Save Department is used to save a department object in a database"
			)
	@ApiResponse(
			responseCode = "201",
			description = "Http Status 201 CREATED"
			)
	@PostMapping
	public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto) {
		
		DepartmentDto savedDepartment =  departmentService.saveDepartment(departmentDto);
		return new ResponseEntity<DepartmentDto>(savedDepartment, HttpStatus.CREATED);
	}
	
	@Operation(
			summary = "Get Department REST API",
			description = "Get Department is used to get a department object from a database"
			)
	@ApiResponse(
			responseCode = "200",
			description = "Http Status 200 SUCCESS"
			)
	@GetMapping("{department-code}")
	public ResponseEntity<DepartmentDto> getDepartment(@PathVariable("department-code") String departmentCode) {
		 
		DepartmentDto departmentDto = departmentService.getDepartmentByCode(departmentCode);
		return new ResponseEntity<DepartmentDto>(departmentDto,HttpStatus.OK);
	}

}
