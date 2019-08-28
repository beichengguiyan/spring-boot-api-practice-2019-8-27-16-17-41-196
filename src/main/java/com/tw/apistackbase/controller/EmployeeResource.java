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
public class EmployeeResource {

	private static List<Employee> employees = new ArrayList<Employee>() {
		{
			add(new Employee(4, "alibaba1", 20, "male", 6000));
			add(new Employee(11, "tengxun2", 19, "female", 7000));
			add(new Employee(6, "alibaba3", 19, "male", 8000));
			add(new Employee());
			add(new Employee());
		}
	};

	@GetMapping("/")
	public ResponseEntity<List<Employee>> getAll() {
		return ResponseEntity.ok(employees);
	}

	@GetMapping(path = "/{employeeId}")
	public ResponseEntity<Employee> getEmployee(@PathVariable int employeeId) {
		return ResponseEntity.ok(employees.get(employeeId));
	}

	@GetMapping()
	public ResponseEntity<List<Employee>> getEmployeeByName(@RequestParam(name = "page", required = false) int page,
			@RequestParam(name = "pageSize", required = false) int pageSize){
			List<Employee> employeesResult = new ArrayList<Employee>();
			if (page == 1) {
				for (int i = 0; i < pageSize; i++) {
					employeesResult.add(employees.get(i));
				}
			}
			return ResponseEntity.ok(employeesResult);
	}

	@GetMapping()
	public ResponseEntity<List<Employee>> getEmployeeByName(@RequestParam(name = "gender", required = false) String gender){
			List<Employee> employeesResult = new ArrayList<Employee>();
			for (int i = 0; i < employees.size(); i++) {
				if (employees.get(i).getGender().equals(gender)) {
					employeesResult.add(employees.get(i));
				}
			}
			return ResponseEntity.ok(employeesResult);
	}

	@PostMapping(consumes = "application/json")
	public ResponseEntity<List<Employee>> addEmployee(@RequestBody Employee employee) {
		employees.add(employee);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping(path = "/{employeeId}")
	public ResponseEntity<List<Employee>> updateEmployee(@PathVariable int employeeId,@RequestBody Employee employee) {
		employees.set(employeeId, employee);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@DeleteMapping(path = "/{employeeId}")
	public ResponseEntity<List<Employee>> deldeteEmployee(@PathVariable int employeeId) {
				employees.remove(employeeId);
		return ResponseEntity.ok(employees);
	}

}
