package com.example.employeeservice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.employeeservice.Dto.APIResponseDto;
import com.example.employeeservice.Dto.DepartmentDto;
import com.example.employeeservice.Dto.EmployeeDto;
import com.example.employeeservice.Dto.OrganizationDto;
import com.example.employeeservice.entity.Employee;
import com.example.employeeservice.mapper.EmployeeMapper;
import com.example.employeeservice.repository.EmployeeRepository;
import com.example.employeeservice.service.APIClient;
import com.example.employeeservice.service.EmployeeService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

//	private RestTemplate restTemplate;
	private WebClient webClient;
//	private APIClient apiClient;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	
	private EmployeeRepository employeeRepository;
	
	@Override
	public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
		
//		Employee employee = new Employee(
//			employeeDto.getId(),
//			employeeDto.getFirstName(),
//			employeeDto.getLastName(),
//			employeeDto.getEmail(),
//			employeeDto.getDepartmentCode()
//			);	
		Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
		Employee savedEmployee = employeeRepository.save(employee);
		
//		EmployeeDto savedEmployeeDto = new EmployeeDto(
//				savedEmployee.getId(),
//				savedEmployee.getFirstName(),
//				savedEmployee.getLastName(),
//				savedEmployee.getEmail(),
//				savedEmployee.getDepartmentCode()
//				);
		EmployeeDto savedEmployeeDto = EmployeeMapper.mapToEmployeeDto(savedEmployee);
		
		return savedEmployeeDto;
	}

	
	@Override
	@CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
//	@Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
	public APIResponseDto getEmployeeById(Long employeeId) {
		
		LOGGER.info("inside getEmployeeById() method");
		
		Employee employee = employeeRepository.findById(employeeId).get();
		
//		ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/" +employee.getDepartmentCode(), DepartmentDto.class);
//		
//		DepartmentDto departmentDto = responseEntity.getBody();
		
		DepartmentDto departmentDto = webClient.get()
			.uri("http://localhost:8080/api/departments/" +employee.getDepartmentCode())
			.retrieve()
			.bodyToMono(DepartmentDto.class)
			.block();
		
		OrganizationDto organizationDto = webClient.get()
				.uri("http://localhost:8083/api/organizations/" +employee.getOrganizationCode())
				.retrieve()
				.bodyToMono(OrganizationDto.class)
				.block();
		
//		DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode()); 
		
//		EmployeeDto employeeDto = new EmployeeDto(
//				employee.getId(),
//				employee.getFirstName(),
//				employee.getLastName(),
//				employee.getEmail(),
//				employee.getDepartmentCode()
//				);
		
		EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee);
		APIResponseDto apiResponseDto = new APIResponseDto();
		apiResponseDto.setDepartment(departmentDto);
		apiResponseDto.setEmployee(employeeDto);
		apiResponseDto.setOrganization(organizationDto);
		
		return apiResponseDto;
	}
	
	public APIResponseDto getDefaultDepartment(Long employeeId, Exception exception) {
		LOGGER.info("inside getDefaultDepartment() method");
		
		Employee employee = employeeRepository.findById(employeeId).get();
		
		DepartmentDto departmentDto = new DepartmentDto();
		departmentDto.setDepartmentName("R&D");
		departmentDto.setDepartmentDescription("Research and Development");
		departmentDto.setDepartmentCode("RD001");
		
//		EmployeeDto employeeDto = new EmployeeDto(
//				employee.getId(),
//				employee.getFirstName(),
//				employee.getLastName(),
//				employee.getEmail(),
//				employee.getDepartmentCode()
//				);
		EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee);
		APIResponseDto apiResponseDto = new APIResponseDto();
		apiResponseDto.setDepartment(departmentDto);
		apiResponseDto.setEmployee(employeeDto);
		
		return apiResponseDto;
	}
		
}
