package com.myApp.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.myApp.model.Course;
import com.myApp.model.User1;
import com.myApp.util.OracleConnUtils;

public class EnrollRepository {
	private Connection dbConnection;
	  
	  public EnrollRepository() {
	      dbConnection = OracleConnUtils.getConnection();
	  }
	  
	  public void saveCourse(Course course) {
	      try {
	          PreparedStatement prepStatement = dbConnection.prepareStatement("insert into enroll values (?, ?)");
	          
	          prepStatement.setInt(1, course.getUserId());
	          prepStatement.setInt(2, course.getCourseId());
	     
	          prepStatement.executeUpdate();
	      } catch (SQLException e) {
	          e.printStackTrace();
	      }
	  }

	public boolean findIfEnrolled(Course course) {
		try {
	          PreparedStatement prepStatement = dbConnection.prepareStatement("select course_id from enroll where user_id = ?");
	          prepStatement.setString(1, String.valueOf(course.getUserId()));   
	                      
	          ResultSet result = prepStatement.executeQuery();
	          if (result != null) {   
	              while (result.next()) {
	                  if (result.getInt(1) == course.getCourseId()) {
	                      return true;
	                  }               
	              }
	          }
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
		return false;
	}

	public Map<String,String> list() {
		Map<String,String> listCourse = new HashMap<>();
        try {
        	PreparedStatement prepStatement = dbConnection.prepareStatement("select course_id, c_name from course");
	         
            ResultSet result = prepStatement.executeQuery();
             
            while (result.next()) {
                String cName = result.getString("c_name");
                String cId = result.getString("course_id");
                listCourse.put(cId, cName);
                
            }          
             
        } catch (SQLException ex) {
            ex.printStackTrace();
        }      
		return listCourse;
	}
	  
}
