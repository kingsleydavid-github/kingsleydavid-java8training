package com.mphasis.kingsley.mphasisbookskingsley.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class Book implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8891541347837121258L;

	@Id
	@Column(name = "bookID")
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookID;
	
	@Column(name = "title", length=1000000)
    private String title;
    
	@Column(name = "authors", length=1000)
    private String authors;
    
	@Column(name = "average_rating", length=1000)
    private String average_rating;
    
	@Column(name = "isbn")
    private String isbn;
    
	@Column(name = "language_code")
    private String language_code;
    
	@Column(name = "ratings_count", length=1000)
    private String ratings_count;
    
	@Column(name = "price")
	private Long price;
	
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authors == null) ? 0 : authors.hashCode());
		result = prime * result + ((average_rating == null) ? 0 : average_rating.hashCode());
		result = prime * result + ((bookID == null) ? 0 : bookID.hashCode());
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		result = prime * result + ((language_code == null) ? 0 : language_code.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((ratings_count == null) ? 0 : ratings_count.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (authors == null) {
			if (other.authors != null)
				return false;
		} else if (!authors.equals(other.authors))
			return false;
		if (average_rating == null) {
			if (other.average_rating != null)
				return false;
		} else if (!average_rating.equals(other.average_rating))
			return false;
		if (bookID == null) {
			if (other.bookID != null)
				return false;
		} else if (!bookID.equals(other.bookID))
			return false;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		if (language_code == null) {
			if (other.language_code != null)
				return false;
		} else if (!language_code.equals(other.language_code))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (ratings_count == null) {
			if (other.ratings_count != null)
				return false;
		} else if (!ratings_count.equals(other.ratings_count))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	public Book(Long bookID, String title, String authors, String average_rating, String isbn, String language_code,
			String ratings_count, Long price) {
		super();
		this.bookID = bookID;
		this.title = title;
		this.authors = authors;
		this.average_rating = average_rating;
		this.isbn = isbn;
		this.language_code = language_code;
		this.ratings_count = ratings_count;
		this.price = price;
	}

	public Book() {}
	
	public Long getBookID() {
		return bookID;
	}

	public void setBookID(Long bookID) {
		this.bookID = bookID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public String getAverage_rating() {
		return average_rating;
	}

	public void setAverage_rating(String average_rating) {
		this.average_rating = average_rating;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getLanguage_code() {
		return language_code;
	}

	public void setLanguage_code(String language_code) {
		this.language_code = language_code;
	}

	public String getRatings_count() {
		return ratings_count;
	}

	public void setRatings_count(String ratings_count) {
		this.ratings_count = ratings_count;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	/*
	@Override
	public String toString() {
		return "Book [bookID=" + bookID + ", title=" + title + ", authors=" + authors + ", average_rating="
				+ average_rating + ", isbn=" + isbn + ", language_code=" + language_code + ", ratings_count="
				+ ratings_count + ", price=" + price + "]";
	}
	*/
}
