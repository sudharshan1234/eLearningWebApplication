package com.myApp.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.myApp.model.User1;
import com.myApp.util.OracleConnUtils;

public class EnrollRepository {
	private Connection dbConnection;
	  
	  public EnrollRepository() {
	      dbConnection = OracleConnUtils.getConnection();
	  }
	  
	  public void saveCourse(int courseId) {
	      try {
	          PreparedStatement prepStatement = dbConnection.prepareStatement("insert into enroll values (?, ?)");
	          
	          prepStatement.setInt(1, User1.userId);
	          prepStatement.setInt(2, courseId);
	     
	          prepStatement.executeUpdate();
	      } catch (SQLException e) {
	          e.printStackTrace();
	      }
	  }

	public boolean findIfEnrolled(String parameter) {
		try {
	          PreparedStatement prepStatement = dbConnection.prepareStatement("select course_id from enroll where user_id = ?");
	          prepStatement.setString(1, String.valueOf(User1.userId));   
	                      
	          ResultSet result = prepStatement.executeQuery();
	          if (result != null) {   
	              while (result.next()) {
	                  if (result.getInt(1) == Integer.parseInt(parameter)) {
	                      return true;
	                  }               
	              }
	          }
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
		return false;
	}
	  
}
