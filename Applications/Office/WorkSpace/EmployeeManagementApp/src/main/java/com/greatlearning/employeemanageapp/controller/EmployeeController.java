package com.greatlearning.employeemanageapp.controller;

import java.security.Principal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greatlearning.employeemanageapp.entity.Employee;
import com.greatlearning.employeemanageapp.entity.Role;
import com.greatlearning.employeemanageapp.entity.User;
import com.greatlearning.employeemanageapp.repository.EmployeeRepository;
import com.greatlearning.employeemanageapp.repository.RolesRepository;
import com.greatlearning.employeemanageapp.repository.UserRepository;
import com.greatlearning.employeemanageapp.service.EmployeeService;

@RestController
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@Autowired
	RolesRepository rolesRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	/**
	 * This method is used to fetch all the employee records
	 * sorted either in ascending or descending order
	 * @return
	 */
	@GetMapping("/sortedemployeelist")
	public ResponseEntity<List<Employee>> sortedlistEmployees(@RequestParam("sortby") String sortBy) {
		try {
			System.out.println("inside list");
			List<Employee> employees = employeeService.findAll();
			if (sortBy != null && "asc".equalsIgnoreCase(sortBy)) {
				Collections.sort(employees, new Comparator<Employee>() {
					@Override
					public int compare(Employee object1, Employee object2) {
						return object1.getFirstName().compareTo(object2.getFirstName());
					}
				});
			} else if (sortBy != null && "desc".equalsIgnoreCase(sortBy)) {
				Collections.sort(employees, new Comparator<Employee>() {
					@Override
					public int compare(Employee object1, Employee object2) {
						return object1.getFirstName().compareTo(object2.getFirstName());
					}
				});
				Collections.reverse(employees);
			}
			return new ResponseEntity<>(employees, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * This method is used to fetch all employee
	 * records
	 * @return
	 */
	@GetMapping("/employeelist")
	public ResponseEntity<List<Employee>> listEmployees() {
		try {
			System.out.println("inside list");
			List<Employee> employees = employeeService.findAll();
			return new ResponseEntity<>(employees, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * This method returns the specific employee record based on the employee id
	 * @param id
	 * @return
	 */
	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee> fetchEmployee(@PathVariable("id") int id) {
		try {
			System.out.println("inside fetchEmployee");
			Employee employee = employeeService.findById(id);
			return new ResponseEntity<>(employee, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * This method returns the specific employee record based on the employee id
	 * @param id
	 * @return
	 */
	@GetMapping("/employeename/{name}")
	public ResponseEntity<List<Employee>> fetchEmployeeByName(@PathVariable("name") String firstName) {
		try {
			System.out.println("inside fetchEmployee");
			List<Employee> employeeList = employeeService.searchBy(firstName);
			if (employeeList != null) {
				System.out.println(employeeList.get(0).getFirstName());
			}
			return new ResponseEntity<>(employeeList, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * This method is responsible to add roles in roles table
	 * @param role
	 * @return
	 */
	@PostMapping("/addroles")
	public ResponseEntity<Role> addRoles(@RequestBody Role role) {
		try {
			Role _role = rolesRepository.save(new Role(role.getName()));
			return new ResponseEntity<>(_role, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * This method is used to add users in User table
	 * @param user
	 * @return
	 */
	@PostMapping("/addusers")
	public ResponseEntity<User> addUsers(@RequestBody User user) {
		try {
			User _user = userRepository.save(new User(user.getUsername(), user.getPassword(), user.getRoles()));
			return new ResponseEntity<>(_user, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * This method is used to add the employee details
	 * @param employee
	 * @return
	 */
	@PostMapping("/addemployee")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {

		try {
			Employee _employee = employeeRepository
					.save(new Employee(employee.getFirstName(), employee.getLastName(), employee.getEmail()));
			return new ResponseEntity<>(_employee, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * This method is used to update employee data in employees table
	 * @param user
	 * @return
	 */
	@PutMapping("/updateemployee")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
		try {
			Employee _employee = employeeRepository.save(new Employee(employee.getFirstName(), employee.getLastName(),
					employee.getEmail(), employee.getId()));
			return new ResponseEntity<>(_employee, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * This method is used to delete employee record from the employee table based
	 * on id
	 * @param theId
	 * @return
	 */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") int theId) {
		try {
			String message = "";
			employeeService.deleteById(theId);
			message = "Deleted employee id -" + theId;
			return new ResponseEntity<>(message, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * This method is used to notify the user whenever there is any unauthorized
	 * access
	 * @param user
	 * @return
	 */
	@GetMapping(value = "/403")
	public ResponseEntity<String> accesssDenied(Principal user) {
		String message = "";
		if (user != null) {
			message = "Hi " + user.getName() + ", you do not have permission to access this page!";
		} else {
			message = "You do not have permission to access this page!";
		}
		return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);

	}
}