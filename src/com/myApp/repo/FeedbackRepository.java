package com.myApp.repo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.myApp.model.Feedback;
import com.myApp.model.User1;
import com.myApp.util.OracleConnUtils;

public class FeedbackRepository {
	private Connection dbConnection;
	static Logger log = Logger.getLogger(FeedbackRepository.class.getName());  

	  public FeedbackRepository() {
	      dbConnection = OracleConnUtils.getConnection();
	  }
	  
	  public void saveFeedback(Feedback feedback) {
	      try {
	          PreparedStatement prepStatement = dbConnection.prepareStatement("insert into feedback values (?, ?, ?)");
	          
	          prepStatement.setInt(1, feedback.getUserId());
	          prepStatement.setString(2, feedback.getCourseName());
	          prepStatement.setString(3, feedback.getFeedback());
	   
	     
	          prepStatement.executeUpdate();
	      } catch (SQLException e) {
	          e.printStackTrace();
	      }
	  }
	  
	  public List<String> list() throws SQLException {
	        List<String> listCourse = new ArrayList<>();
	        String output="";
	        try {
	        	PreparedStatement prepStatement = dbConnection.prepareStatement("select c_name from course");
		         
	            ResultSet result = prepStatement.executeQuery();
	             
	            while (result.next()) {
	                String cName = result.getString("c_name");
	                     
	                listCourse.add(cName);
	                output+=cName;
	                output+=" ";
	                log.info(cName);  
	                
	            }          
	             
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            throw ex;
	        }      
	        listCourse.add("General Feedback");
	        
	        
	        return listCourse;
	    }
	  
}
