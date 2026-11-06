package com.comeback.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.comeback.dto.Employee;
import com.comeback.util.JdbcUtil;

public class EmployeeDaoImpl implements IEmployeeDao {

	Connection connection = null;
	PreparedStatement pstmt = null;
	ResultSet resultSet = null;

	@Override
	public String addEmployee(Employee employee) {		
		
		String insertQuery = "insert into employee(`name`,`age`,`address`)values(?,?,?)";
		try {
			connection = JdbcUtil.getJdbcConnection();

			if (connection != null)
				pstmt = connection.prepareStatement(insertQuery);

			if (pstmt != null) {

				pstmt.setString(1, employee.getEname());
				pstmt.setInt(2, employee.getEage());
				pstmt.setString(3, employee.getEaddress());

				int rowAffected = pstmt.executeUpdate();
				if (rowAffected == 1) {
					return "success";
				}
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}

		return "failure";
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
	}}
