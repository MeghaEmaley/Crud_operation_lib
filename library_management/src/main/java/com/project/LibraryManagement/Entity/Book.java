package com.project.LibraryManagement.Entity;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "id_no", length = 50, nullable = false, unique = true)
	private String id_no;

	@Column(name = "name", length = 100, nullable = false)
	private String name;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
	@JoinTable(name = "books_authors", joinColumns = { @JoinColumn(name = "book_id") }, inverseJoinColumns = {
			@JoinColumn(name = "author") })
	private Set<Author> authors = new HashSet<Author>();


	public Book(String id_no, String name) {
		this.id_no = id_no;
		this.name = name;
	}

	public void addAuthors(Author author) {
		this.authors.add(author);
		author.getBooks().add(this);
	}

	public void removeAuthors(Author author) {
		this.authors.remove(author);
		author.getBooks().remove(this);
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getId_no() {
		return id_no;
	}

	public void setId_no(String id_no) {
		this.id_no = id_no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Set<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}

	

	public Book() {
		super();
	}

}