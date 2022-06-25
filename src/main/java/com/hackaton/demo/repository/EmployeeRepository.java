package com.hackaton.demo.repository;

import com.hackaton.demo.dao.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
