package com.comeback.service;

import com.comeback.dto.Employee;

public interface IEmployeeService {

	// Operations on Employee
	public String addEmployee(Employee employee);

	public Employee searchEmployee(Integer eid);

	public String updateEmployee(Employee employee);

	public String deleteEmployee(Integer eid);

}
