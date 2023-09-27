package hh.sof03.kirjakauppa.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String author;
	@Column(name = "Release_year")
	private int year;
	private String isbn;
	private double price;
	
	
	@ManyToOne //Book ManyToOne Category, monta kirjaa voi liittyä yhteen kategoriaan
	@JoinColumn(name = "categoryid") //viiteavaimen määritys tietokanna Book-taululle
	private Category category; //lisätään category attribuutti Book-luokan käyttöön
	
	public Book(String title, String author, int year, String isbn, double price, Category category) { //lisätään category-parametri konstruktoriin
		super();
		this.title = title;
		this.author = author;
		this.year = year;
		this.isbn = isbn;
		this.price = price;
		this.category = category; //lisätään category-parametri konstruktoriin
	}
	
	public Book() {
		super();
		this.title = null;
		this.author = null;
		this.year = 0;
		this.isbn = null;
		this.price = 0.00;
		this.category = null; //lisätän category-parametri konstruktoriin
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Category getCategory() { //lisätään get- ja set-metodit category-attribuutille
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "title: " + title + ", author: " + author + ", year: " + year + 
				", isbn: " + isbn + ", price: " + price + ", category :" + category; //lisätään myös category-attribuutti toString-metodiin
	}
	
	
	
	
	
}
