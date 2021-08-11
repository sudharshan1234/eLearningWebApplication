package com.myApp.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myApp.model.Admin;
import com.myApp.repo.AdminRepository;

/**
 * Servlet implementation class appController
 */

@SuppressWarnings("serial")
public class AdminController extends HttpServlet {
	private AdminRepository adminRepository;

	private static String app_ADMINLOGIN = "content/adminLogin.jsp";
	private static String LOGIN_SUCCESS = "content/adminSuccess.jsp";
	private static String LOGIN_FAILURE = "content/adminFailure.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminController() {
		super();
		adminRepository = new AdminRepository();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String forward = app_ADMINLOGIN;
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String forward = "";		
		Admin admin=new Admin();
        HttpSession session=request.getSession();  
		boolean logged=false;
		
		if (adminRepository != null) {

			boolean result = adminRepository.findAdminLogin(
					request.getParameter("adminEmail"),
					request.getParameter("adminPassword"));
					admin.setEmail(request.getParameter("adminEmail"));admin.setPassword(request.getParameter("adminPassword"));
			if (result == true) {
				forward = LOGIN_SUCCESS;
				request.setAttribute("successMessage", "You successfully logged-into the system. \r\n"
						+ "              Now you can add, update or delete data");
				logged=true;
			} else {
				forward = LOGIN_FAILURE;
				request.setAttribute("failMessage","Something is wrong. Change a few things up\r\n"
						+ "					and try submitting again.");
				logged=false;
			}
			
			if(logged==true) {
				session.setAttribute("adminLogged","true");
				ArrayList arr= (ArrayList) adminRepository.getIdName(request.getParameter("adminEmail"),
						request.getParameter("adminPassword"));
				session.setAttribute("userId", (Integer)arr.get(0));
				session.setAttribute("userName", (String)arr.get(1));
				admin.setAdminId((Integer)arr.get(0));
				admin.setName((String)arr.get(1));
				
			}

		}
		
		
		
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}
}