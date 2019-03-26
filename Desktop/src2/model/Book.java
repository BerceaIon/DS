package model;

public class Book {
	private int bookId;
	private String releaseDate;
	private String author;
	private String genre;
	private int noOfBooks;
	private String title;
	
	public Book(int bookId, String releaseDate, String author, String genre, int noOfBooks, String title) {
		super();
		this.bookId = bookId;
		this.releaseDate = releaseDate;
		this.author = author;
		this.genre = genre;
		this.noOfBooks = noOfBooks;
		this.title = title;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public int getNoOfBooks() {
		return noOfBooks;
	}

	public void setNoOfBooks(int noOfBooks) {
		this.noOfBooks = noOfBooks;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", releaseDate=" + releaseDate + ", author=" + author + ", genre=" + genre
				+ ", noOfBooks=" + noOfBooks + ", title=" + title + "]";
	}

	
}
