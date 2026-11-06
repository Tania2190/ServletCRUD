package com.comeback.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.comeback.dto.Employee;
import com.comeback.service.IEmployeeService;
import com.comeback.servicefactory.EmployeeServiceFactory;

@WebServlet("/controller/*")
public class EmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		IEmployeeService empServiceObject = EmployeeServiceFactory.getEmployeeServiceObject();

		System.out.println("Request URI :: " + request.getRequestURI());
		System.out.println("Path info :: " + request.getPathInfo());

		if (request.getRequestURI().endsWith("addform")) {
			String name = request.getParameter("name");
			String age = request.getParameter("age");
			String addr = request.getParameter("addr");

			Employee employee = new Employee();
			employee.setEname(name);
			employee.setEage(Integer.parseInt(age));
			employee.setEaddress(addr);

			String status = empServiceObject.addEmployee(employee);
			PrintWriter out = response.getWriter();

			if (status.equals("success")) {
				out.println("<h1 style='color:green; text-align:center;'>REGISTRATION SUCCESFULL</h1>");
			} else {
				out.println("<h1 style='color:green; text-align:center;'>REGISTRATION FAILED</h1>");
			}
			out.close();
		}

	}
}
