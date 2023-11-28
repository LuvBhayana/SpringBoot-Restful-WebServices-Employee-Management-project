package com.example.employeeservice.service;

import com.example.employeeservice.Dto.APIResponseDto;
import com.example.employeeservice.Dto.EmployeeDto;

public interface EmployeeService {
	
	EmployeeDto saveEmployee(EmployeeDto employeeDto);
	
	APIResponseDto getEmployeeById(Long employeeId);

}
