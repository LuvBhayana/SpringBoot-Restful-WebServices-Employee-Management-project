package com.example.employeeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.employeeservice.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
