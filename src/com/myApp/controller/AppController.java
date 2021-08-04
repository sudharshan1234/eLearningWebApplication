package com.myApp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myApp.model.User1;
import com.myApp.repo.AppRepository;

/**
 * Servlet implementation class appController
 */

@SuppressWarnings("serial")
public class AppController extends HttpServlet {
	private AppRepository appRepository;

	private static String app_SIGNUP = "content/signup.jsp";
	private static String app_LOGIN = "content/login.jsp";
	private static String LOGIN_SUCCESS = "content/success.jsp";
	private static String LOGIN_FAILURE = "content/failure.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AppController() {
		super();
		appRepository = new AppRepository();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {	
		String forward = app_SIGNUP;
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String pageName = request.getParameter("pageName");
		String forward = "";		
		User1 user=new User1();
		if (appRepository != null) {
			if (pageName.equals("signup")) {
				if (appRepository.findByUserName(request
						.getParameter("email"))) {
					request.setAttribute("message", "Email exists. Try another Email");
					forward = app_SIGNUP;
					RequestDispatcher view = request
							.getRequestDispatcher(forward);
					view.forward(request, response);
					return;
				}
				String name=request.getParameter("name");
				Long phoneNumber=Long.parseLong(request.getParameter("phoneNumber"));
				String email=request.getParameter("email");
				String address=request.getParameter("address");
				String password=request.getParameter("password");
				String uploadPhoto=request.getParameter("uploadPhoto");
				user.setAddress(address);user.setEmail(email);user.setName(name);user.setPassword(password);
				user.setPhoneNumber(phoneNumber);user.setUploadPhoto(uploadPhoto);
				appRepository.save(user);
				
				forward = app_LOGIN;
			} else if (pageName.equals("login")) {
				boolean result = appRepository.findByLogin(
						request.getParameter("email"),
						request.getParameter("password"));
						user.setEmail(request.getParameter("email"));user.setPassword(request.getParameter("password"));
				if (result == true) {
					forward = LOGIN_SUCCESS;
				} else {
					forward = LOGIN_FAILURE;
				}
			}
		}
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}
}