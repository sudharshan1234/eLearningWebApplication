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

import com.myApp.model.Course;
import com.myApp.repo.AdminRepository;

/**
 * Servlet implementation class appController
 */

@SuppressWarnings("serial")
public class AdminCourseController extends HttpServlet {
	private AdminRepository adminRepository;
	static Logger log = Logger.getLogger(EnrollController.class.getName());  

	private static String app_ADMINCOURSE = "content/adminCourseCRUD.jsp";
	private static String LOGIN_FAILURE = "content/adminFailure.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminCourseController() {
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
        listCourse(request, response);
		}
 
    }
 
    private void listCourse(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        List<Course> list = adminRepository.displayCourse();
        request.setAttribute("listCourse", list);
        RequestDispatcher dispatcher = request.getRequestDispatcher(app_ADMINCOURSE);
        dispatcher.forward(request, response);
    }
 
//    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        RequestDispatcher dispatcher = request.getRequestDispatcher("CourseForm.jsp");
//        dispatcher.forward(request, response);
//    }
// 
//    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        int id = Integer.parseInt(request.getParameter("id"));
//        Course existingCourse = .getCourse(id);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("CourseForm.jsp");
//        request.setAttribute("course", existingCourse);
//        dispatcher.forward(request, response);
// 
//    }
// 
//    private void insertCourse(HttpServletRequest request, HttpServletResponse response)
//            throws IOException {
//        String cName = request.getParameter("c_name");
//        String cDesp = request.getParameter("c_desp");
//        String cFees = request.getParameter("c_fees");
//        String cResource = request.getParameter("c_resource");
//        Course course=new Course();
//        course.setcName(cName);course.setcDesp(cDesp);course.setcFees(cFees);course.setcResource(cResource);
//        adminRepository.addCourse(course);
//        response.sendRedirect("list");
//    }
// 
//    private void updateCourse(HttpServletRequest request, HttpServletResponse response)
//            throws IOException {
//        int id = Integer.parseInt(request.getParameter("course_id"));
//        String cName = request.getParameter("c_name");
//        String cDesp = request.getParameter("c_desp");
//        String cFees = request.getParameter("c_fees");
//        String cResource = request.getParameter("c_resource");
//        Course course=new Course();
//        course.setCourseId(id);
//        course.setcName(cName);course.setcDesp(cDesp);course.setcFees(cFees);course.setcResource(cResource);
//        adminRepository.updateCourse(course);
//        response.sendRedirect("list");
//    }
// 
//    private void deleteCourse(HttpServletRequest request, HttpServletResponse response)
//            throws IOException {
//        int id = Integer.parseInt(request.getParameter("course_id"));
// 
//        Course course = new Course();
//        course.setCourseId(id);
//        adminRepository.deleteCourse(course);
//        response.sendRedirect("list");
// 
//    }
}