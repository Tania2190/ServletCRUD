package com.comeback.servicefactory;

import com.comeback.service.EmployeeServiceImpl;
import com.comeback.service.IEmployeeService;

public class EmployeeServiceFactory {
	
	//inhibit Service object creation
	private EmployeeServiceFactory(){
		
	}
	
	private static IEmployeeService employeeService = null;
	
	public static IEmployeeService getEmployeeServiceObject() {
		
		if (employeeService == null) {
			employeeService = new EmployeeServiceImpl();
		}
		return employeeService;
		
	}

}
