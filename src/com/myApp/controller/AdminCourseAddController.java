package com.myApp.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myApp.model.Course;
import com.myApp.repo.AdminRepository;

/**
 * Servlet implementation class appController
 */

@SuppressWarnings("serial")
public class AdminCourseAddController extends HttpServlet {
	private AdminRepository adminRepository;
	static Logger log = Logger.getLogger(EnrollController.class.getName());  

	private static String app_ADMINCOURSEADD = "content/adminCourseAdd.jsp";
	private static String LOGIN_SUCCESS = "content/adminSuccess.jsp";
	private static String LOGIN_FAILURE = "content/adminFailure.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminCourseAddController() {
		super();
		adminRepository = new AdminRepository();
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		insertCourse(request, response);
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	HttpSession session=request.getSession();
		String val=(String) session.getAttribute("adminLogged");
		if(val==null) {
			String forward = LOGIN_FAILURE;
			request.setAttribute("failMessage", "Please Login First to gain Admin Privileges!");
			RequestDispatcher view = request.getRequestDispatcher(forward);
			view.forward(request, response);
		}
		else {
        String action = request.getServletPath();
        log.info(action);
        String forward = app_ADMINCOURSEADD;
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
		}
 
    }
    
	  private void insertCourse(HttpServletRequest request, HttpServletResponse response)
	  throws IOException {
		String cName = request.getParameter("courseName");
		String cDesp = request.getParameter("courseDesp");
		String cFees = request.getParameter("courseFees");
		String cResource = request.getParameter("courseResource");
		Course course=new Course();
		course.setcName(cName);course.setcDesp(cDesp);course.setcFees(cFees);course.setcResource(cResource);
		if(adminRepository.addCourse(course)) {
			request.setAttribute("successMessage", "Course Was Added Successfully!!");
			RequestDispatcher view = request.getRequestDispatcher(LOGIN_SUCCESS);
			try {
				view.forward(request, response);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			request.setAttribute("failMessage", "Was not able to add the course. Try again later.");
			RequestDispatcher view = request.getRequestDispatcher(LOGIN_FAILURE);
			try {
				view.forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}