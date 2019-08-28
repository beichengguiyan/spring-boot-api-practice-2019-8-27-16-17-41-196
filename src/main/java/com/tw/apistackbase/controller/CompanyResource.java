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

import com.tw.apistackbase.model.Company;
import com.tw.apistackbase.model.Employee;

@RestController
@RequestMapping("/companies")
public class CompanyResource {
	private static List<Employee> employees = new ArrayList<Employee>() {
		{
			add(new Employee(4, "alibaba1", 20, "male", 6000));
			add(new Employee(11, "tengxun2", 19, "female", 7000));
			add(new Employee(6, "alibaba3", 19, "male", 8000));
		}
	};
	private static List<Employee> employeesTwo = new ArrayList<Employee>() {
		{
			add(new Employee(20, "alibaba11", 20, "male", 6000));
			add(new Employee(21, "tengxun12", 19, "female", 7000));
			add(new Employee(22, "alibaba13", 19, "male", 8000));
		}
	};
	private static List<Company> companies = new ArrayList<Company>() {
		{
			add(new Company(0, "alibaba", 200, employees));
			add(new Company(1, "tengxun", 300, employeesTwo));
			add(new Company());
			add(new Company());
			add(new Company());
		}
	};

	@GetMapping("/")
	public ResponseEntity<List<Company>> getAll() {
		return ResponseEntity.ok(companies);
	}

	@GetMapping(path = "/{companyId}")
	public ResponseEntity<Employee> getCompany(@PathVariable int companyId) {
		return ResponseEntity.ok(employees.get(companyId));
	}

	@GetMapping(path = "/{companyId}/employees")
	public ResponseEntity<List<Employee>> getEmployeeById(@PathVariable int companyId) {
		List<Employee> employees = companies.get(companyId).getEmployees();
		return ResponseEntity.ok(employees);
	}

	@GetMapping()
	public ResponseEntity<List<Company>> getCompanyByPage(@RequestParam(name = "page", required = false) int page,
			@RequestParam(name = "pageSize", required = false) int pageSize) {

		List<Company> companiesResult = new ArrayList<Company>();
		if (page == 1) {
			for (int i = 0; i < pageSize; i++) {
				companiesResult.add(companies.get(i));
			}
		}
		return ResponseEntity.ok(companiesResult);
	}

	@PostMapping(consumes = "application/json")
	public ResponseEntity<List<Company>> addCompany(@RequestBody Company company) {
		companies.add(company);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping(path = "/{companyId}")
	public ResponseEntity<List<Company>> updateEmployee(@PathVariable int companyId, @RequestBody Company company) {
		companies.set(companyId, company);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@DeleteMapping(path = "/{companyId}")
	public ResponseEntity<List<Company>> deldeteEmployee(@PathVariable int companyId) {
		companies.remove(companyId);
		return ResponseEntity.ok(companies);
	}

}
