package com.myApp.controller;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myApp.model.Contact;
import com.myApp.repo.ContactRepository;

/**
 * Servlet implementation class appController
 */

@SuppressWarnings("serial")
@WebServlet("/contact")
public class ContactController extends HttpServlet {
	private ContactRepository contactRepository;

	private static String app_CONTACT = "content/contact.jsp";
	private static String LOGIN_SUCCESS = "content/success.jsp";
	private static String LOGIN_FAILURE = "content/failure.jsp";


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ContactController() {
		super();
		contactRepository = new ContactRepository();
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
		else {
		String forward = app_CONTACT;
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
		Contact contact= new Contact();
		HttpSession session=request.getSession();
		int userId=(Integer)session.getAttribute("userId");
		String name=request.getParameter("name");
		Long phoneNumber;
		try {
			phoneNumber = Long.parseLong(request.getParameter("phoneNumber"));
			contact.setPhoneNumber(phoneNumber);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String email=request.getParameter("email");
		String message=request.getParameter("message");
		contact.setUserId(userId);
		contact.setEmail(email);contact.setName(name);contact.setMessage(message);
		
		contactRepository.saveContact(contact);
		
		forward = LOGIN_SUCCESS;
		request.setAttribute("successMessage", "Contact was added successfully!");
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}
}