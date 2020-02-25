package org.se.lab;

@JSONMapping(name = "book")
public class Book {
	@JSONMapping(name = "title")
	private String title;
	@JSONMapping(name = "isbn")
	private String isbn;

	public Book(String title, String isbn) {
		this.title = title;
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

}
