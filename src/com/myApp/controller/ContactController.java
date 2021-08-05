package com.myApp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myApp.model.Contact;
import com.myApp.repo.ContactRepository;

/**
 * Servlet implementation class appController
 */

@SuppressWarnings("serial")
public class ContactController extends HttpServlet {
	private ContactRepository contactRepository;

	private static String app_CONTACT = "content/contact.jsp";

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
		String forward = app_CONTACT;
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
		Contact contact= new Contact();
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
		contact.setEmail(email);contact.setName(name);contact.setMessage(message);
		
		contactRepository.saveContact(contact);
		
		forward = app_CONTACT;
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}
}