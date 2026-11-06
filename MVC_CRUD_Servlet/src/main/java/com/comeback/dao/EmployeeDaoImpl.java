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
		String selectQuery = "select id,name,age,address from employee where id = ?";
		Employee employee = null;

		try {
			connection = JdbcUtil.getJdbcConnection();

			if (connection != null)
				pstmt = connection.prepareStatement(selectQuery);

			if (pstmt != null)
				pstmt.setInt(1, eid);

			if (pstmt != null) {
				resultSet = pstmt.executeQuery();
			}

			if (resultSet != null) {

				if (resultSet.next()) {
					employee = new Employee();

					// copy resultSet data to student object
					employee.setEid(resultSet.getInt(1));
					employee.setEname(resultSet.getString(2));
					employee.setEage(resultSet.getInt(3));
					employee.setEaddress(resultSet.getString(4));

					return employee;
				}

			}

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}

		return employee;
	}

	@Override
	public String updateEmployee(Employee employee) {
		String sqlUpdateQuery = "update employee set name=?,age=?,address=? where id=?";
		try {
			connection = JdbcUtil.getJdbcConnection();

			if (connection != null)
				pstmt = connection.prepareStatement(sqlUpdateQuery);

			if (pstmt != null) {

				pstmt.setString(1, employee.getEname());
				pstmt.setInt(2, employee.getEage());
				pstmt.setString(3, employee.getEaddress());
				pstmt.setInt(4, employee.getEid());

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
	public String deleteEmployee(Integer eid) {
		String deleteQuery = "delete from employee where id = ?";
		try {
			connection = JdbcUtil.getJdbcConnection();

			if (connection != null)
				pstmt = connection.prepareStatement(deleteQuery);

			if (pstmt != null) {

				pstmt.setInt(1, eid);

				int rowAffected = pstmt.executeUpdate();
				if (rowAffected == 1) {
					return "success";
				} else {
					return "not found";
				}
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
			return "failure";
		}
		return "failure";
	}


}
