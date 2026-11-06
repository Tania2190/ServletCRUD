package com.comeback.service;

import com.comeback.dao.IEmployeeDao;
import com.comeback.daofactory.EmployeeDaoFactory;
import com.comeback.dto.Employee;

public class EmployeeServiceImpl implements IEmployeeService {
	
	private IEmployeeDao employeeDao;

	@Override
	public String addEmployee(Employee employee) {
		employeeDao = EmployeeDaoFactory.getEmployeeDaoObject();
		return employeeDao.addEmployee(employee);
	}

	@Override
	public Employee searchEmployee(Integer eid) {
		employeeDao = EmployeeDaoFactory.getEmployeeDaoObject();
		return employeeDao.searchEmployee(eid);
	}

	@Override
	public String updateEmployee(Employee employee) {
		employeeDao = EmployeeDaoFactory.getEmployeeDaoObject();
		return employeeDao.updateEmployee(employee);
	}

	@Override
	public String deleteEmployee(Integer eid) {
		employeeDao = EmployeeDaoFactory.getEmployeeDaoObject();
		return employeeDao.deleteEmployee(eid);
	}

	

}
