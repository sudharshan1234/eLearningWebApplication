package com.myApp.controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myApp.model.Contact;
import com.myApp.repo.AdminRepository;

/**
 * Servlet implementation class appController
 */

@SuppressWarnings("serial")
public class AdminContactController extends HttpServlet {
	private AdminRepository adminRepository;
	static Logger log = Logger.getLogger(EnrollController.class.getName());  

	private static String app_ADMINCONTACT = "content/adminContact.jsp";
	private static String LOGIN_FAILURE = "content/adminFailure.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminContactController() {
		super();
		adminRepository = new AdminRepository();
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
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
        listContact(request, response);
		}
 
    }
 
    private void listContact(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        List<Contact> list = adminRepository.displayContact();
        request.setAttribute("listCourse", list);
        RequestDispatcher dispatcher = request.getRequestDispatcher(app_ADMINCONTACT);
        dispatcher.forward(request, response);
    }
}