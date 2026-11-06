package com.comeback.daofactory;

import com.comeback.dao.EmployeeDaoImpl;
import com.comeback.dao.IEmployeeDao;

public class EmployeeDaoFactory {
	
	//inhibit Dao object creation
	private EmployeeDaoFactory() {
		
	}
	
	private static IEmployeeDao employeeDao = null;
	
	public static IEmployeeDao getEmployeeDaoObject() {
		if (employeeDao == null) {
			employeeDao = new EmployeeDaoImpl();
		}
		return employeeDao;
	}

}
