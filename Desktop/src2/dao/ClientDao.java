package dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import model.Client;

public class ClientDao {
	private Connection myConnection;
	
	public ClientDao()throws Exception{
		
		  Properties properties = new Properties(); properties.load(new
		  FileInputStream("sql/library.properties"));
		  
		  String user = properties.getProperty("user"); String password =
		  properties.getProperty("password"); String dburl =
		  properties.getProperty("dburl");
		  
		  myConnection = DriverManager.getConnection(dburl, user, password);
		  System.out.println("Connection succesfull  to "+dburl);

	}
	
	public List<Client> searchClient(String account) throws Exception {
		List<Client> list = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConnection.prepareStatement("select * from client where account like ?");
			myStmt.setString(1, account);
			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				Client tempClient = convertRowToClient(myRs);
				list.add(tempClient);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	
	
	
	  private static void close(Connection myConn, Statement myStmt, ResultSet
	  myRs) throws SQLException {
	  
	  if (myRs != null) { myRs.close(); }
	  
	  if (myStmt != null) {
	  
	  }
	  
	  if (myConn != null) { myConn.close(); } }
	  
	  private void close(Statement myStmt, ResultSet myRs) throws SQLException {
	  close(null, myStmt, myRs); }
	 
	public Client convertRowToClient(ResultSet myResultSet)throws SQLException{
		int id = myResultSet.getInt("idClient");
		String lastName = myResultSet.getString("firstName");
		String firstName = myResultSet.getString("lastName");
		int booksBorrowed = myResultSet.getInt("booksBorrowed");
		String account = myResultSet.getString("account");
		String password = myResultSet.getString("password");
		
		Client temp = new Client(id, firstName, lastName, booksBorrowed, account, password);
		return temp;
		
	}
}
