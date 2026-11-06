package com.comeback.dao;

import com.comeback.dto.Employee;

public interface IEmployeeDao {
	
	//Operations on Employee
	public String addEmployee(Employee employee);

	public Employee searchEmployee(Integer eid);

	public String updateEmployee(Employee employee);

	public String deleteEmployee(Integer eid);

}
