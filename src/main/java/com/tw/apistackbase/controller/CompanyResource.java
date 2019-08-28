package com.tw.apistackbase.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tw.apistackbase.model.Employee;

@RestController
@RequestMapping("/employees")
public class CompanyResource {
	private static List<Employee> employees = new ArrayList<Employee>() {
		{
			add(new Employee(0, "Xiaoming", 20, "Male"));
			add(new Employee(1, "Xiaohong", 19, "Female"));
			add(new Employee(2, "Xiaozhi", 15, "Male"));
			add(new Employee(3, "Xiaogang", 16, "Male"));
			add(new Employee(4, "Xiaoxia", 15, "Female"));
		}
	};

	@GetMapping()
	public ResponseEntity<List<Employee>> getAll() {
		return ResponseEntity.ok(employees);
	}

	@GetMapping(path = "/{employeeId}")
	public ResponseEntity<Employee> getEmployee(@PathVariable int employeeId) {
		return ResponseEntity.ok(employees.get(employeeId));
	}

	@GetMapping(path = "/name")
	public ResponseEntity<List<Employee>> getEmployeeByName(
			@RequestParam(name = "name", required = false) String name) {
		List<Employee> employeesResult = new ArrayList<Employee>();
		for (Employee employee : employees) {
			if (employee.getName().contains(name)) {
				employeesResult.add(employee);
			}
		}
		return ResponseEntity.ok(employeesResult);
	}

	@PostMapping(consumes = "application/json")
	public ResponseEntity<List<Employee>> addEmployee(@RequestBody Employee employee) {
		employees.add(employee);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping(consumes = "application/json")
	public ResponseEntity<List<Employee>> updateEmployee(@RequestBody Employee employee) {
		for (int i = 0; i < employees.size(); i++) {
			if (employees.get(i).getId() == employee.getId()) {
				employees.set(i, employee);
			}
		}
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@DeleteMapping(path = "/{employeeId}")
	public ResponseEntity<List<Employee>> deldeteEmployee(@PathVariable int employeeId) {
				employees.remove(employeeId);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
}
