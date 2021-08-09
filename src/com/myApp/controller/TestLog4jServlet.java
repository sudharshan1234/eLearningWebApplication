package com.myApp.controller;
 
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.log4j.Logger;

import com.myApp.repo.FeedbackRepository;
 
@WebServlet("/test")
public class TestLog4jServlet extends HttpServlet {
 
    static final Logger LOGGER = Logger.getLogger(TestLog4jServlet.class);
             
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
         
		FeedbackRepository feedbackRepository = new FeedbackRepository();
		List<String> listCourse=null;
		try {
			listCourse=feedbackRepository.list();
			request.setAttribute("listCourse", listCourse);
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new ServletException(e);
		}
        listCourse=(List<String>) request.getAttribute("listCourse");
		Iterator it= listCourse.iterator();
		while(it.hasNext()) {
			LOGGER.info(it.next()+"\n");
		}
        String html = "<%@ taglib uri=\"http://java.sun.com/jsp/jstl/core\" prefix=\"c\" %>\r\n"
        		+ "<%@ page language=\"java\" contentType=\"text/html; charset=ISO-8859-1\"\r\n"
        		+ "    pageEncoding=\"ISO-8859-1\"%>\n"
        		+ "<html><select name=\"course\" id=\"course\">\r\n"
        		+ "<c:forEach items=\"${listCourse}\" var=\"course\">\r\n"
        		+ "<option value=\"${course}\">${course}</option>\r\n"
        		+ "</c:forEach>\r\n"
        		+ "</select></html>";
        response.getWriter().println(html);
    }
 
}