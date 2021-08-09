package com.myApp.controller;


import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myApp.model.Course;
import com.myApp.repo.EnrollRepository;
import com.myApp.repo.FeedbackRepository;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class appController
 */

@SuppressWarnings("serial")
@WebServlet("/enroll")
public class EnrollController extends HttpServlet {
	private EnrollRepository enrollRepository;
//	static Logger log = Logger.getLogger(EnrollController.class.getName());  
	private static String app_ENROLL = "content/enroll.jsp";
	private static String LOGIN_SUCCESS = "content/success.jsp";
	private static String LOGIN_FAILURE = "content/failure.jsp";


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
		HttpSession session=request.getSession();
		String val=(String) session.getAttribute("logged");
		String nameVal=(String) session.getAttribute("userName");
//		log.info(val+" logged value "+nameVal);
		if(val==null) {
			String forward = LOGIN_FAILURE;
			request.setAttribute("failMessage", "Please Login First!");
			RequestDispatcher view = request.getRequestDispatcher(forward);
			view.forward(request, response);
		}
		else {
		String forward = app_ENROLL;
		HashMap<String,String> listCourse=(HashMap<String, String>) enrollRepository.list();
		request.setAttribute("listCourse", listCourse);
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String forward = "";		
		Course course=new Course();
		HttpSession session=request.getSession();  
		if (enrollRepository != null) {
				course.setUserId((Integer)session.getAttribute("userId"));
				course.setCourseId(Integer.parseInt(request.getParameter("courseId")));
				if (enrollRepository.findIfEnrolled(course) ){
					request.setAttribute("failMessage", "Already Enrolled. Try another Course");
					forward = LOGIN_FAILURE;
					RequestDispatcher view = request
							.getRequestDispatcher(forward);
					view.forward(request, response);
					return;
				}

				try {
					enrollRepository.saveCourse(course);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				forward = LOGIN_SUCCESS;
				request.setAttribute("successMessage", "Course was enrolled successfully!");
				RequestDispatcher view = request.getRequestDispatcher(forward);
				view.forward(request, response);
			
		}

	}
}