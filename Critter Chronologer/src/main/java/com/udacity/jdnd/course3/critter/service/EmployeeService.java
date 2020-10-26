package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.domain.Employee;

public interface EmployeeService {
    Employee save(Employee employee);
    Employee getEmployeeById(Long employeeId);
}
