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
				out.println("<h1 style='color:red; text-align:center;'>REGISTRATION FAILED</h1>");
			}
			out.close();
		}
		
		if(request.getRequestURI().endsWith("searchform")) {
			String id = request.getParameter("id");
			
			Employee employee = empServiceObject.searchEmployee(Integer.parseInt(id));
			PrintWriter out = response.getWriter();
			if (employee != null) {
				out.println("<body>");
				out.println("<br/><br/><br/>");
				out.println("<center>");
				out.println("<table border='1'>");
				out.println("<tr><th>ID</th><td>" +"\t"+ employee.getEid() +"\t"+ "</td></tr>");
				out.println("<tr><th>NAME</th><td>" +"\t"+ employee.getEname() +"\t"+ "</td></tr>");
				out.println("<tr><th>AGE</th><td>" +"\t"+ employee.getEage() +"\t"+ "</td></tr>");
				out.println("<tr><th>ADDRESS</th><td>" +"\t"+ employee.getEaddress() +"\t"+ "</td></tr>");
				out.println("</table>");
				out.println("</center>");
				out.println("</body>");
			} else {
				out.println("<h1 style='color:red;text-align:center;'>RECORD NOT AVAILABLE FOR THE GIVEN ID " + id
						+ "</h1>");
			}
			out.close();
			
		}
		if(request.getRequestURI().endsWith("editform")) {
			String id = request.getParameter("id");
			
			Employee employee = empServiceObject.searchEmployee(Integer.parseInt(id));
			
			PrintWriter out = response.getWriter();
			if (employee != null) {
				out.println("<body>");
				out.println("<center>");
				out.println("<form method='post' action='./controller/updateRecord'>");
				out.println("<table>");
				
				out.println("<tr><th>ID</th><td>" + employee.getEid() + "</td></tr>");
				out.println("<input type='hidden' name='eid' value='" + employee.getEid() + "'/>");
				out.println("<tr><th>NAME</th><td><input type='text' name='ename' value='" + employee.getEname() + "'/></td></tr>");
				out.println("<tr><th>AGE</th><td><input type='text' name='eage' value='" + employee.getEage() + "'/></td></tr>");
				out.println("<tr><th>ADDRESS</th><td><input type='text' name='eaddress' value='" + employee.getEaddress() + "'/></td></tr>");
				out.println("<tr><td></td><td><input type='submit' value='update'/></td></tr>");
				
				out.println("</table>");
				out.println("</form>");
				out.println("</center>");
				out.println("</body>");
			} else {
				
				out.println("<body>");
				out.println("<h1 style='color:red;text-align:center;'>RECORD NOT AVAILABLE FOR THE GIVEN ID  :: " + id
						+ "</h1>");
				out.println("</body>");
			}
			out.close();
			
		}
		if (request.getRequestURI().endsWith("updateRecord")) {
			String eid = request.getParameter("eid");
			String ename = request.getParameter("ename");
			String eage = request.getParameter("eage");
			String eaddr = request.getParameter("eaddress");

			Employee employee = new Employee();
			employee.setEid(Integer.parseInt(eid));
			employee.setEname(ename);
			employee.setEage(Integer.parseInt(eage));
			employee.setEaddress(eaddr);
			
			String status = empServiceObject.updateEmployee(employee);
			PrintWriter out = response.getWriter();

			if (status.equals("success")) {
				out.println("<h1 style='color:green; text-align:center;'>RECORD UPDATED SUCCESSFULLY</h1>");
			} else {
				out.println("<h1 style='color:red; text-align:center;'>RECORD UPDATION FAILED</h1>");
			}
			out.close();
		}
		
		if(request.getRequestURI().endsWith("deleteform")) {
			String id = request.getParameter("id");
			
			String status = empServiceObject.deleteEmployee(Integer.parseInt(id));
			
			PrintWriter out = response.getWriter();
			if (status.equals("success")) {
				out.println("<body>");
				out.println("<h1 style='color:green;text-align:center;'>RECORD DELETED SUCCESFULLY</h1>");
				out.println("</body>");
			} else if (status.equals("failure")) {
				out.println("<body>");
				out.println("<h1 style='color:red;text-align:center;'>RECORD DELETION FAILED</h1>");
				out.println("</body>");

			} else {
				out.println("<body>");
				out.println("<h1 style='color:red;text-align:center;'>RECORD DOES NOT EXIST</h1>");
				out.println("</body>");

			}
			out.close();
			
		}

	}
}
