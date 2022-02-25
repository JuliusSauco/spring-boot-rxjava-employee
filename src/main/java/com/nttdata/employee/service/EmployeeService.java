package com.nttdata.employee.service;

import java.util.List;
import com.nttdata.employee.model.Employee;
import io.reactivex.rxjava3.core.Single;

public interface EmployeeService {

	public Single<Employee> createEmp(Employee e);
	
	public Single<Employee> findByEmpId(String id);
	
	public Single<List<Employee>> findAllEmp();
	
	public Single<Employee> updateEmp(Employee e);
	
	public Single<Void> deleteEmp(String id);
}
