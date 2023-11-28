package com.example.departmentservice.service;

import com.example.departmentservice.Dto.DepartmentDto;

public interface DepartmentService {

	DepartmentDto saveDepartment(DepartmentDto departmentDto);
	
	DepartmentDto getDepartmentByCode(String departmentCode);
}
