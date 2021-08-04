package com.myApp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myApp.repo.AppRepository;
import com.myApp.repo.EnrollRepository;

/**
 * Servlet implementation class appController
 */

@SuppressWarnings("serial")
public class EnrollController extends HttpServlet {
	private EnrollRepository enrollRepository;

	private static String app_ENROLL = "content/enroll.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EnrollController() {
		super();
		enrollRepository = new EnrollRepository();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {	
		String forward = app_ENROLL;
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
		
		if (enrollRepository != null) {
				if (enrollRepository.findIfEnrolled(request
						.getParameter("courseId"))) {
					request.setAttribute("message", "Already Enrolled. Try another Course");
					forward = app_ENROLL;
					RequestDispatcher view = request
							.getRequestDispatcher(forward);
					view.forward(request, response);
					return;
				}

				enrollRepository.saveCourse(Integer.parseInt(request.getParameter("courseId")));
				forward = app_ENROLL;
				RequestDispatcher view = request.getRequestDispatcher(forward);
				view.forward(request, response);
			
		}
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}
}