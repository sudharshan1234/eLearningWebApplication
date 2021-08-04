package com.myApp.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class OracleConnUtils {
  private static Connection dbConnection = null;

  public static Connection getConnection() {
      if (dbConnection != null) {
          return dbConnection;
      } else {
          try {
              InputStream inputStream = OracleConnUtils.class.getClassLoader()
                      .getResourceAsStream("db.properties");
              Properties properties = new Properties();
              if (properties != null) {
                  properties.load(inputStream);

                  String dbDriver = properties.getProperty("dbDriver");
                  String connectionUrl = properties
                          .getProperty("connectionUrl");
                  String userName = properties.getProperty("userName");
                  String password = properties.getProperty("password");

                  Class.forName(dbDriver);
                  dbConnection = DriverManager.getConnection(connectionUrl,
                          userName, password);
              }
          } catch (Exception e) {
              e.printStackTrace();
          }
          return dbConnection;
      }
  }
}