package com.nttdata.employee.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.nttdata.employee.model.Employee;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {

}
