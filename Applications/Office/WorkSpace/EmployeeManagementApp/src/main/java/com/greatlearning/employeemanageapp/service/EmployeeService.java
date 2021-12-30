package com.greatlearning.employeemanageapp.service;

import java.util.List;

import com.greatlearning.employeemanageapp.entity.Employee;

public interface EmployeeService {
	public List<Employee> findAll();
	public Employee findById(int id);
	public void save(Employee student);
	public void deleteById(int id);
	public List<Employee> searchBy(String firstName, String lastName);
	public List<Employee> searchBy(String firstName);
}
