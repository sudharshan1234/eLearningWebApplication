package com.myApp.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.myApp.model.Contact;
import com.myApp.model.User1;
import com.myApp.util.OracleConnUtils;

public class ContactRepository {
	private Connection dbConnection;
	  
	  public ContactRepository() {
	      dbConnection = OracleConnUtils.getConnection();
	  }
	  
	  public void saveContact(Contact contact) {
	      try {
	          PreparedStatement prepStatement = dbConnection.prepareStatement("insert into contact values (?, ?, ?, ?, ?, contactid.nextval)");
	          
	          prepStatement.setInt(1, User1.userId);
	          prepStatement.setString(2, contact.getName());
	          prepStatement.setString(3, contact.getEmail());
	          prepStatement.setLong(4, contact.getPhoneNumber());
	          prepStatement.setString(5, contact.getMessage());
	     
	          prepStatement.executeUpdate();
	      } catch (SQLException e) {
	          e.printStackTrace();
	      }
	  }

	  
}
