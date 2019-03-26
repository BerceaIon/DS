package dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.List;
import java.util.Properties;


import model.Book;

public class BookDao {
	private Connection myConnection;
	
	public BookDao()throws Exception{
		//get db properties
		Properties properties = new Properties();
		properties.load(new FileInputStream("sql/library.properties"));
		
		String user = properties.getProperty("user");
		String password = properties.getProperty("password");
		String dburl = properties.getProperty("dburl");
	
		//connect to database
		myConnection = (Connection) DriverManager.getConnection(dburl, user, password);		
		System.out.println("Database connection succesfull!" + dburl);
	}
	
	public List<Book> getAllBooks()throws Exception{
		List<Book> list = new ArrayList<>();
		
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			statement = myConnection.createStatement();
			resultSet = statement.executeQuery("select * from books");
			
			while(resultSet.next()) {
				Book temporaryBook = convertRowToBook(resultSet);
				list.add(temporaryBook);
			}
			/*
			 * for(int i=0; i<list.size(); i++) {
			 * System.out.println(list.get(i).toString()); }
			 */
		}
		finally {
			close(statement, resultSet);
		}
		return list;
	}
	
	public List<Book> searchBook(String author)throws Exception{
		List<Book> list = new ArrayList<>();
		
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			author +="%";
			statement = myConnection.prepareStatement("select * from books where author like ? ");
			statement.setString(1, author);
			//statement.setString(2, author);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Book temporaryBook = convertRowToBook(resultSet);
				list.add(temporaryBook);
			}
			return list;
		}
		finally {
			close(statement, resultSet);
		}
	}
	
	public List<Book> searchBookGenre(String genre)throws Exception{
		List<Book> list = new ArrayList<>();
		
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			genre +="%";
			statement = myConnection.prepareStatement("select * from books where genre like ? ");
			statement.setString(1, genre);
			//statement.setString(2, author);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Book temporaryBook = convertRowToBook(resultSet);
				list.add(temporaryBook);
			}
			return list;
		}
		finally {
			close(statement, resultSet);
		}
	}
	public List<Book> searchBookTitle(String title)throws Exception{
		List<Book> list = new ArrayList<>();
		
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			title +="%";
			statement = myConnection.prepareStatement("select * from books where title like ? ");
			statement.setString(1, title);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Book temporaryBook = convertRowToBook(resultSet);
				list.add(temporaryBook);
			}
			return list;
		}
		finally {
			close(statement, resultSet);
		}
	}
	
	
	public Book convertRowToBook(ResultSet resultSet)throws SQLException{
		int id = resultSet.getInt("bookId");
		String releaseDate = resultSet.getString("releaseDate");
		String author = resultSet.getString("author");
		String genre = resultSet.getString("genre");
		int noOfBooks = resultSet.getInt("noOfBooks");
		String title = resultSet.getString("title");
		
		Book temp = new Book(id,releaseDate,author,genre,noOfBooks,title);
		return temp;
	}
	
	private static void close(Connection myConn, Statement myStmt, ResultSet myRs)
			throws SQLException {

		if (myRs != null) {
			myRs.close();
		}

		if (myStmt != null) {
			
		}
		
		if (myConn != null) {
			myConn.close();
		}
	}

	private void close(Statement myStmt, ResultSet myRs) throws SQLException {
		close(null, myStmt, myRs);		
	}
	
public static void main(String[] args) throws Exception {
		
		BookDao dao = new BookDao();
		System.out.println(dao.searchBookTitle("D"));

		//System.out.println(dao.getAllBooks());
	}
}
