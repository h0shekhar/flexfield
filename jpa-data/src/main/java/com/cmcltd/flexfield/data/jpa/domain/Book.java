package com.cmcltd.flexfield.data.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An entity Book composed by three fields (id, name, author). The Entity
 * annotation indicates that this class is a JPA entity. The Table annotation
 * specifies the name for the table in the db.
 *
 * @author
 */
@Entity(name = "BOOK")
@Table(name = "BOOK")
public class Book extends BaseBusinessEntity {

	private static final Logger logger = LoggerFactory.getLogger(Book.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// ==============
	// PRIVATE FIELDS
	// ==============

	@Id
	@SequenceGenerator(name = "BOOKID_GEN", sequenceName = "BOOK_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOOKID_GEN")
	@Column(name = "ID", nullable = false, length = 10)
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer value) {
		this.id = value;
	}

	// The name of the book

	// The Book's name
	@Column(name = "NAME", nullable = false, length = 100)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String value) {
		this.name = value;
	}

	@Column(name = "AUTHOR", nullable = false, length = 100)
	private String author;

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String value) {
		this.author = value;
	}

	// ==============
	// CONSTRUCTORS
	// ==============

	public Book() {
		logger.trace("Book instance created");
		setEntityName("BOOK");
	}

	public Book(Integer id) {
		logger.trace("Book instance created");
		setEntityName("BOOK");
		this.id = id;
	}

	public Book(String name, String author) {
		logger.trace("Book instance created");
		setEntityName("BOOK");
		this.name = name;
		this.author = author;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", author=" + author
				+ " { CustomField < " + getCustomField() + "> } ]\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Book)) {
			return false;
		}
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null) {
				return false;
			}
		} else if (!author.equals(other.author)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

} // class book
