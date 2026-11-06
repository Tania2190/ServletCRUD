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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteEmployee(Integer eid) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
