package com.nttdata.employee.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.nttdata.employee.model.Employee;
import com.nttdata.employee.service.EmployeeService;
import io.reactivex.rxjava3.core.Single;

@RestController
public class EmployeeController {
	
	@Autowired private EmployeeService employeeService;
	
	@PostMapping("/create/emp")
	@ResponseStatus(HttpStatus.CREATED)
	public Single<Employee> createEmp(@RequestBody Employee employee) {
		return employeeService.createEmp(employee);
	}
	
	@GetMapping(value = "/get/all")
	@ResponseBody
	public Single<List<Employee>> findAll() {
		return employeeService.findAllEmp();
	}
	
	@GetMapping("/get/{id}")
	public Single<Employee> findEmpById(@PathVariable("id") String id) {
		return employeeService.findByEmpId(id);
	}
	
	@PutMapping("/update/emp")
	@ResponseStatus(HttpStatus.OK)
	public Single<Employee> update(@RequestBody Employee employee) {
		return employeeService.updateEmp(employee);
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Single<String> delete(@PathVariable("id") String id) {
		return employeeService.deleteEmp(id);
	}
	
}
