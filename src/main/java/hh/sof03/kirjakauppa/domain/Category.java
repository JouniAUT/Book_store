package hh.sof03.kirjakauppa.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //lisätään categoryid:lle automaattinen arvogeneraattori
	private Long categoryid;
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category") // Category OneToMany Students, Kategoria moneen opiskelijaan
	private List <Book> books; //luodaan lista kirja-olioita varten
	

	public Category() { //luodaan parametriton konstruktori luokalle
		super();
	}
	
	
	public Category(String name) { 	//Luodaan parametrillinen konstruktori luokalle, pelkällä name-attribuutilla.
		super();					//categoryid ei ole varsinaisesti käyttäjää varten, joten sitä ei tarvitse laittaa konstruktoriin. 
		this.name = name;
	}


	public Long getCategoryid() { //getterit, setterit ja toSrtring Category-luokan attribuuteille
		return categoryid;
	}
	public void setCategoryid(Long categoryid) {
		this.categoryid = categoryid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}


	@Override
	public String toString() { //toStringiin ei voi lisätä lista-attribuuttia. Ohjelma joutuu ikuiseen looppiin, jos lista-attribuutti lisätän
		return "Category [categoryid=" + categoryid + ", name=" + name + "]";
	}
	
	
	
}
