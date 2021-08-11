package com.myApp.repo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.myApp.model.Contact;
import com.myApp.model.Course;
import com.myApp.model.Feedback;
import com.myApp.model.User1;
import com.myApp.util.OracleConnUtils;

public class AdminRepository {
	private Connection dbConnection;
	  
	  public AdminRepository() {
	      dbConnection = OracleConnUtils.getConnection();
	  }
	  
	  public boolean findAdminLogin(String email, String password) {
	      try {
	          PreparedStatement prepStatement = dbConnection.prepareStatement("select password from admin where email = ?");
	          prepStatement.setString(1, email);           
	          
	          ResultSet result = prepStatement.executeQuery();
	          
	          if (result != null) {
	              while (result.next()) {
	                  if (result.getString(1).equals(password)) {
	                      return true;
	                  }
	              }               
	          }           
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	      return false;
	  }
	  
	  public List getIdName(String email, String password) {
		  
		  ArrayList out=new ArrayList<>();
          try {
        	  PreparedStatement prepStatement = dbConnection.prepareStatement("select admin_id, name from admin where email= ? and password= ? ");
			  prepStatement.setString(1, email);
			  prepStatement.setString(2, password);
			  ResultSet rs = prepStatement.executeQuery();
			  if(rs!=null) {
				  while(rs.next()) {
					  out.add(rs.getInt("admin_id"));
					  out.add(rs.getString("name"));
					  return out;
				  }
			  }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
        return null;
		  
	  }
	  
	  public boolean addCourse(Course course) {
		  boolean rowInserted=false;
	      try {
	          PreparedStatement prepStatement = dbConnection.prepareStatement("insert into course values (courseid.nextval, ?, ?, ?, ?)");
	          prepStatement.setString(1, course.getcName());
	          prepStatement.setString(2, course.getcDesp());
	          prepStatement.setString(3, course.getcFees());
	          prepStatement.setString(4, course.getcResource());
	          rowInserted = prepStatement.executeUpdate() > 0;
	          
	      } catch (SQLException e) {
	          e.printStackTrace();
	      }
	      return rowInserted;
	  }
	  
	  public List<Course> displayCourse() {
		  List<Course> list=new ArrayList<>();
	      try {
	          PreparedStatement prepStatement = dbConnection.prepareStatement("select * from course");
	          
	          ResultSet result = prepStatement.executeQuery();
	          if (result != null) {
	              while (result.next()) {
	            	  Course course=new Course();
	            	  course.setCourseId(result.getInt(1));
	            	  course.setcName(result.getString(2));
	            	  course.setcDesp(result.getString(3));
	            	  course.setcFees(result.getString(4));
	            	  course.setcResource(result.getString(5));
	            	  list.add(course);
	              }
	          }
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	      return list;
	  }
	  
	  public List<User1> displayUsers() {
		  List<User1> list=new ArrayList<>();
	      try {
	          PreparedStatement prepStatement = dbConnection.prepareStatement("select * from user1 order by user_id");
	          
	          ResultSet result = prepStatement.executeQuery();
	          if (result != null) {
	              while (result.next()) {
	            	  User1 user=new User1();
	            	  user.setUserId(result.getInt(1));
	            	  user.setName(result.getString(2));
	            	  user.setPhoneNumber(result.getLong(3));
	            	  user.setEmail(result.getString(4));
	            	  user.setAddress(result.getString(5));
	            	  user.setReg_date(result.getDate(6));
	            	  user.setUploadPhoto(result.getString(8));
	            	  list.add(user);
	              }
	          }
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	      return list;
	  }
	  
	  public List<Feedback> displayFeedback() {
		  List<Feedback> list=new ArrayList<>();
	      try {
	          PreparedStatement prepStatement = dbConnection.prepareStatement("select * from feedback");
	          
	          ResultSet result = prepStatement.executeQuery();
	          if (result != null) {
	        	  int i=1;
	              while (result.next()) {
	            	  Feedback feedback=new Feedback();
	            	  feedback.setUserId(result.getInt(1));
	            	  feedback.setCourseName(result.getString(2));
	            	  feedback.setFeedback(result.getString(3));
	            	  list.add(feedback);
	              }
	          }
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	      return list;
	  }
	  
	  public boolean deleteCourse(Course course) {
		  boolean rowDeleted=false;
	      try {
	          PreparedStatement prepStatement = dbConnection.prepareStatement("delete from course course_id = ?");
	          prepStatement.setInt(1, course.getCourseId());
	          rowDeleted = prepStatement.executeUpdate() > 0;
	          
	      } catch (SQLException e) {
	          e.printStackTrace();
	      }
	      return rowDeleted;
	  }
	  
	  public boolean updateCourse(Course course) {
		  boolean rowUpdated=false;
	      try {
	          PreparedStatement prepStatement = dbConnection.prepareStatement("update course set c_name = ?, c_desp = ?, c_fees = ?, c_resource = ?"
	          		+ "where course_id = ?");
	          prepStatement.setString(1, course.getcName());
	          prepStatement.setString(2, course.getcDesp());
	          prepStatement.setString(3, course.getcFees());
	          prepStatement.setString(4, course.getcResource());
	          prepStatement.setInt(4, course.getCourseId());
	          rowUpdated = prepStatement.executeUpdate() > 0;
	          
	      } catch (SQLException e) {
	          e.printStackTrace();
	      }
	      return rowUpdated;
	  }

	public List<Contact> displayContact() {
		List<Contact> list=new ArrayList<>();
	      try {
	          PreparedStatement prepStatement = dbConnection.prepareStatement("select * from contact");
	          
	          ResultSet result = prepStatement.executeQuery();
	          if (result != null) {
	              while (result.next()) {
	            	  Contact contact=new Contact();
	            	  contact.setUserId(result.getInt(1));
	            	  contact.setName(result.getString(2));
	            	  contact.setEmail(result.getString(3));
	            	  contact.setPhoneNumber(result.getLong(4));
	            	  contact.setMessage(result.getString(5));	            
	            	  list.add(contact);
	              }
	          }
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	      return list;
	}

}
