package com.myApp.repo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.myApp.model.User1;
import com.myApp.util.OracleConnUtils;

public class AppRepository {
	private Connection dbConnection;
	  
	  public AppRepository() {
	      dbConnection = OracleConnUtils.getConnection();
	  }
	  
	  public void save(User1 user) {
	      try {
	          PreparedStatement prepStatement = dbConnection.prepareStatement("insert into user1 values (userid.nextval, ?, ?, ?, ?, ?, ?, ?)");
	          prepStatement.setString(1, user.getName());
	          prepStatement.setLong(2, user.getPhoneNumber());
	          prepStatement.setString(3, user.getEmail());
	          prepStatement.setString(4, user.getAddress());
	          prepStatement.setDate(5, new Date(System.currentTimeMillis()));
	          prepStatement.setString(6, user.getPassword());
	          prepStatement.setString(7, user.getUploadPhoto());
	          prepStatement.executeUpdate();
	          
	          prepStatement = dbConnection.prepareStatement("select user_id from user1 where email= ? and password= ? ");
	          prepStatement.setString(1, user.getEmail());
	          prepStatement.setString(2, user.getPassword());
	          ResultSet rs = prepStatement.executeQuery();
	          if(rs!=null) {
	        	  while(rs.next()) {
	        		  User1.userId=rs.getInt("user_id");
	        	  }
	          }
	      } catch (SQLException e) {
	          e.printStackTrace();
	      }
	  }
	  
	  public boolean findByUserName(String email) {
	      try {
	          PreparedStatement prepStatement = dbConnection.prepareStatement("select count(*) from user1 where email = ?");
	          prepStatement.setString(1, email);   
	                      
	          ResultSet result = prepStatement.executeQuery();
	          if (result != null) {   
	              while (result.next()) {
	                  if (result.getInt(1) == 1) {
	                      return true;
	                  }               
	              }
	          }
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	      return false;
	  }
	  
	  public boolean findByLogin(String email, String password) {
	      try {
	          PreparedStatement prepStatement = dbConnection.prepareStatement("select password from user1 where email = ?");
	          prepStatement.setString(1, email);           
	          
	          ResultSet result = prepStatement.executeQuery();
	          
	          prepStatement = dbConnection.prepareStatement("select user_id from user1 where email= ? and password= ? ");
	          prepStatement.setString(1, email);
	          prepStatement.setString(2, password);
	          ResultSet rs = prepStatement.executeQuery();
	          if(rs!=null) {
	        	  while(rs.next()) {
	        		  User1.userId=rs.getInt("user_id");
	        	  }
	          }
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
}
