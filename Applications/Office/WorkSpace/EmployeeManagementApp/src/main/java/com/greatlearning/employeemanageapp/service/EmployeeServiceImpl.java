package com.greatlearning.employeemanageapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greatlearning.employeemanageapp.entity.Employee;
import com.greatlearning.employeemanageapp.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	@Transactional
	public List<Employee> findAll() {
		List<Employee> books = employeeRepository.findAll();
		return books;
	}

	@Override
	@Transactional
	public Employee findById(int theId) {
		return employeeRepository.findById(theId).get();
	}

	@Override
	@Transactional
	public void save(Employee theBook) {
		employeeRepository.save(theBook);
	}

	@Override
	@Transactional
	public void deleteById(int theId) {
		employeeRepository.deleteById(theId);
	}

	@Override
	@Transactional
	public List<Employee> searchBy(String firstName, String lastName) {
		List<Employee> employeeList = employeeRepository.findByFirstNameContainsAndLastNameContainsAllIgnoreCase(firstName, lastName);
		return employeeList;
	}
	
	@Override
	@Transactional
	public List<Employee> searchBy(String firstName) {
		List<Employee> employeeList = employeeRepository.findByFirstNameContainsAllIgnoreCase(firstName);
		return employeeList;
	}
}