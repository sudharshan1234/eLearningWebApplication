package com.myApp.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myApp.model.Feedback;
import com.myApp.repo.FeedbackRepository;

/**
 * Servlet implementation class appController
 */

@SuppressWarnings("serial")
@WebServlet("/feedback")
public class FeedbackController extends HttpServlet {
	private FeedbackRepository feedbackRepository;

	private static String app_FEEDBACK = "content/feedback.jsp";
	private static String LOGIN_SUCCESS = "content/success.jsp";
	private static String LOGIN_FAILURE = "content/failure.jsp";


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FeedbackController() {
		super();
		feedbackRepository = new FeedbackRepository();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String val=(String) session.getAttribute("logged");
		if(val==null) {
			String forward = LOGIN_FAILURE;
			request.setAttribute("failMessage", "Please Login First!");
			RequestDispatcher view = request.getRequestDispatcher(forward);
			view.forward(request, response);
		}
		else
			listCategory(request, response);
		
	}

	private void listCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		String forward = app_FEEDBACK;

		try {
			List<String> listCourse=feedbackRepository.list();
			request.setAttribute("listCourse", listCourse);
			request.setAttribute("selectedRole", "Rat");
			RequestDispatcher view = request.getRequestDispatcher(forward);
			view.forward(request, response);
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String forward = "";	
		Feedback feedback= new Feedback();
//		int courseId = Integer.parseInt(request.getParameter("course"));
		 
//	    request.setAttribute("selectedCId", courseId);
		HttpSession session=request.getSession();
		int userId=(Integer)session.getAttribute("userId");
		String courseName=request.getParameter("course");
		String feedbackMessage=request.getParameter("feedbackMessage");
		feedback.setCourseName(courseName);feedback.setFeedback(feedbackMessage);
		feedback.setUserId(userId);
		
		feedbackRepository.saveFeedback(feedback);

		forward = LOGIN_SUCCESS;
		request.setAttribute("successMessage", "Feedback was added successfully!");
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	    
//	    listCategory(request, response);
	}
}