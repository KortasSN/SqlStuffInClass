package com.josh;
import java.sql.*;

public class Main {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";        //Configure the driver needed
    static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/vet";     //Connection string – where's the database?

    static final String USER = "root";   //TODO replace with your username
    static final String PASSWORD = "itecitec";   //TODO replace with your password

    public static void main(String[] args) throws Exception {      //TODO handle exceptions properly

        Class.forName(JDBC_DRIVER);   //Instantiate the driver class

        Connection connection = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD);  //Create a connection to DB
        Statement statement = connection.createStatement();     //A statement object is used to run SQL statements

        statement.execute("CREATE TABLE IF NOT EXISTS testing (CODE INTEGER, NAME VARCHAR(50))");  //Run some SQL – create table
        statement.execute("INSERT INTO TESTING VALUES (2545, 'Java')  ");      //Add some test data
        statement.execute("INSERT INTO TESTING VALUES (1150, 'Python')  ");    //And some more test data

        ResultSet rs = statement.executeQuery("SELECT * FROM TESTING");   //Fetch all data; data is returned in a ResultSet

        while (rs.next()) {								//Loop over ResultSet, and print data
            System.out.println("The class code is " + rs.getInt(1));
            System.out.println("The name is " + rs.getString(2));
            System.out.println("*****");
        }

        statement.execute("DROP TABLE testing");      //Delete the table (you don't usually do this in your applications :)

        rs.close();          				//Close the result set, statement and connection, release resources
        statement.close();
        connection.close();
    }
}
