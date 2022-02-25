package com.nttdata.employee.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nttdata.employee.dao.EmployeeRepository;
import com.nttdata.employee.model.Employee;
import com.nttdata.employee.service.EmployeeService;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

@Service
public class EmployeeServicceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Single<Employee> createEmp(Employee e) {
		return Single.create(singleSubscriber -> {
			singleSubscriber.onSuccess(employeeRepository.save(e));
		});
	}

	@Override
	public Single<Employee> findByEmpId(String id) {
		return Single.create(singleSubscriber -> {
			Optional<Employee> optionalEmployee = employeeRepository.findById(id);
			if (!optionalEmployee.isPresent()) {
				singleSubscriber.onError(new Exception(String.format("Employee not found with id: ", id)));
			}
			else {
				singleSubscriber.onSuccess(optionalEmployee.get());
			}
		});
	}

	@Override
	public Single<List<Employee>> findAllEmp() {
		return Single.create(singleSubscriber -> {
			singleSubscriber.onSuccess(employeeRepository.findAll());
		});
	}

	@Override
	public Single<Employee> updateEmp(Employee e) {
		Optional<Employee> optionalEmployee = employeeRepository.findById(e.getId());
		
		//El filtro de operador emite solo aquellos elementos de un observable que pasan una prueba de predicado .
		//Filtramos algo obvio, en este caso que el optional sea igual al valor que recibimos.
//		Observable.fromOptional(optionalEmployee)
//			.filter(i -> (optionalEmployee.get().getId().equals(e.getId())))
//			.subscribe();
		
		return Single.create(singleSubscriber -> {
			if (!optionalEmployee.isPresent()) {
				singleSubscriber.onError(new Exception(String.format("Employee not found with id: ", e.getId())));
			}
			else {
				e.setId(optionalEmployee.get().getId());
				e.setName(optionalEmployee.get().getName());
				e.setRole(optionalEmployee.get().getRole());
				singleSubscriber.onSuccess(employeeRepository.save(e));
			}
		});
	}

	@Override
	public Single<Void> deleteEmp(String id) {
		Optional<Employee> optionalEmployee = employeeRepository.findById(id);
		
		return Single.create(singleSubscriber -> {
			if (!optionalEmployee.isPresent()) {
				singleSubscriber.onError(new Exception(String.format("Employee not found with id: ", id)));
			}
			else {
				employeeRepository.delete(optionalEmployee.get());
				singleSubscriber.onSuccess(null);
			}
		});
		
	}
	
}
