package com.cmcltd.flexfield.data.jpa.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmcltd.flexfield.data.jpa.domain.Book;
import com.cmcltd.flexfield.data.jpa.domain.CustomField;
import com.cmcltd.flexfield.data.jpa.repository.BookRepository;

/**
 * A class to test the interaction with Oracle database using the BookDao class.
 *
 * @author CMC Limited
 */

@Controller
public class BookController extends BaseController {
	private static final Logger logger = LoggerFactory
			.getLogger(BookController.class);

	// ==============
	// PRIVATE FIELDS
	// ==============

	@Autowired
	private BookRepository bookDao;

	// ==============
	// PUBLIC METHODS
	// ==============

	/**
	 * Create a new user and save it in the database.
	 * 
	 * @param author
	 *            book's author
	 * @param name
	 *            Book's name
	 * @return a string describing if the user is successfully created or not.
	 */

	@RequestMapping(value = "/create/book/{name}, {author}", method = {
			RequestMethod.PUT, RequestMethod.POST }, produces = "application/json")
	@ResponseBody
	public Book create(@PathVariable String name, @PathVariable String author) {
		Book book = null;

		try {
			logger.debug("Name passed =" + name + "Author passed " + author);
			book = new Book(name, author);

			book = bookDao.save(book);

		} catch (Exception ex) {
			logger.error("Failed to create Boook data with Name : " + name);
			logger.error("Exception : " + ex.getLocalizedMessage());
			return book;
		}
		return book;
	}

	/**
	 * Delete the Book having the passed id.
	 * 
	 * @param id
	 *            the id of the Book to delete
	 * @return a string describing if the Book is successfully deleted or not.
	 */
	@RequestMapping(value = "/delete/book/Id/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@ResponseBody
	public String delete(@PathVariable Long id) {
		try {
			logger.debug("ID passed = " + id.toString());
			Book book = bookDao.findById(id.intValue());

			deleteCustomFieldData(id.intValue());
			bookDao.delete(book);

		} catch (Exception ex) {
			logger.error("Boook not found with ID : " + id);
			logger.error("Exception : " + ex.getLocalizedMessage());
			return "Error deleting the Book : " + ex.toString();
		}
		return "Book succesfully deleted!";
	}

	/**
	 * Return the books .
	 * 
	 * 
	 * @return all the books or a message error if the book is not found.
	 */

	@RequestMapping(value = "/books", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Set<Book> getAll() {
		Set<Book> books = null;
	
		
		try {

			Iterable<Book> bookList = bookDao.findAll();

			if (bookList != null) {
				logger.debug("Found Book records");
			} else {
				logger.debug("Found no book records.");
			}

			for (Book book : bookList) {

				logger.debug("Processing for book no <" + book.getId() + ">");
				if (books == null) {
					books = new HashSet<Book>(0);
					initialize(book.getEntityName());
				}

				book.setCustomField(getCustomField());

				logger.debug("Custom field set for the Book: <"
						+ book.getCustomField() + ">");

				List<CustomField> cfst = getCustomFieldWithValue(
						book.getCustomField(), book.getId());

				if (cfst != null) {
					logger.debug("Adding Custom Field Data Received <" + cfst
							+ ">");
					book.setCustomField(cfst);
				} else {
					logger.debug("Custom Field Data not Found");
					book.setCustomField(null);
				}

				logger.debug("Book Details with Custom Fields: \n" + book);
				logger.debug("Book Data Processed ");
				logger.debug("-----------------------------------------");

				books.add(book);
				logger.debug("Books So Far:\n" + books);
			}

		} catch (Exception ex) {
			logger.error("Book not found. ");
			logger.error("Exception : " + ex.getLocalizedMessage());

			return books;

		}
		logger.debug("No of Books found : " + books.size()
				+ "\n with Details: \n" + books);
		return books;
	}

	/**
	 * Return the book for the passed name.
	 * 
	 * @param name
	 *            the name to search in the database.
	 * @return the books or a message error if the book is not found.
	 */

	@RequestMapping(value = "/book/Name/{name}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Book getByName(@PathVariable String name) {
		Book book = null;

		try {
			logger.debug("Book Name passed = " + name);
			book = bookDao.findByName(name);
			logger.debug("Processing book Data <\n" + book + ">");

			initialize(book.getEntityName());
			book.setCustomField(getCustomField());
			List<CustomField> cfs = getCustomFieldWithValue(
					book.getCustomField(), book.getId());

			logger.debug("Custom Field Data Received <" + cfs + ">");
			logger.debug("Added Book Data ");
			logger.debug("-----------------------------------------");
			book.setCustomField(cfs);

		} catch (Exception ex) {
			logger.error("Boook not found with Name : " + name);
			logger.error("Exception : " + ex.getLocalizedMessage());
			return book;
		}
		return book;

	}

	@RequestMapping(value = "/book/Id/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Book getById(@PathVariable Long id) {
		Book book = null;
		String bookName = null;
		String bookAuthor = null;
		logger.debug("Book ID passed =" + id.toString());
		try {

			book = bookDao.findById(id.intValue());
			logger.debug("Processing book Data <\n" + book + ">");

			initialize(book.getEntityName());
			book.setCustomField(getCustomField());
			List<CustomField> cfs = getCustomFieldWithValue(
					book.getCustomField(), book.getId());

			logger.debug("Custom Field Data Received <" + cfs + ">");
			logger.debug("Added Book Data ");
			logger.debug("-----------------------------------------");
			book.setCustomField(cfs);

			if (!book.equals(null)) {
				bookName = String.valueOf(book.getName());
				bookAuthor = String.valueOf(book.getAuthor());

			}
		} catch (Exception ex) {
			logger.error("Book not found for ID : " + id);
			logger.error("Exception : " + ex.getLocalizedMessage());
			return book;

		}

		logger.debug("The book Name is: " + bookName + " whose author is : "
				+ bookAuthor);
		return book;
	}

	/**
	 * Update the Author and the name for the Book in the database having the
	 * passed id.
	 * 
	 * @param id
	 *            the id for the Book to update.
	 * @param name
	 *            the new book name.
	 * @param author
	 *            the new book author.
	 * @return a string describing if the book is successfully updated or not.
	 */

	@RequestMapping(value = "/update/book/{id},{name},{author}", method = {
			RequestMethod.PUT, RequestMethod.POST }, produces = "application/json")
	@ResponseBody
	public Book updateBook(@PathVariable Long id, @PathVariable String name,
			@PathVariable String author) {
		Book book = null;
		try {

			logger.debug("Book ID passed =" + id.toString());
			book = bookDao.findById(id.intValue());
			logger.debug("Processing book Data <\n" + book + ">");

			book.setAuthor(author);
			book.setName(name);
			bookDao.save(book);
			logger.debug("Updated Book Data with new detail :" + book);
			logger.debug("-----------------------------------------");

		} catch (Exception ex) {
			if (book != null) {
				logger.error("No Book Data Found for ID : " + id);
				logger.error("Exception : " + ex.getLocalizedMessage());
				return book;
			}
		}
		return book;
	}

	@RequestMapping(value = "/update/book", method = { RequestMethod.PUT,
			RequestMethod.POST }, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Book update(@RequestBody Book book) {
		logger.debug("Processing book Data <\n" + book + ">");
		Book bookObj = null;
		try {
			logger.debug("Book ID passed =" + book.getId().toString());
			bookObj = bookDao.findById(book.getId().intValue());
			
			if (bookObj != null) {
				bookObj.setAuthor(book.getAuthor());
				bookObj.setName(book.getName());
				bookDao.save(bookObj);
								
				List<CustomField> cfl = book.getCustomField();
				logger.debug("Custom field received < "+ cfl +" >");
				
				if ( cfl != null){
					logger.debug("Setting new custom field data");
					
					bookObj.setCustomField(cfl);
					setCustomField(cfl);					
					updateCustomFieldData(book.getId());	
				} else {
					logger.debug("No Custom field update necessary");
				}
				
				
				logger.debug("Updated Book Data with new detail :" + bookObj);
				logger.debug("-----------------------------------------");
			} else {
				logger.debug(" Book being Updated doesn't exists.");
			}

		} catch (Exception ex) {

			logger.error("No Book Data Found for ID : " + book.getId());
			logger.error("Exception : " + ex.getLocalizedMessage());
			return book;

		}
		return bookObj;
	}
	
	
	@RequestMapping(value = "/create/book", method = {
			RequestMethod.PUT, RequestMethod.POST }, produces = "application/json")
	@ResponseBody
	public Book createBook(@RequestBody Book book) {
		String name = null, author = null;
		
		try {
			name = book.getName();
			author = book.getAuthor();
			logger.debug("Name passed =" + name + "Author passed " + author);
			Book newBook = new Book(name , author);
			newBook  = bookDao.save(newBook);
			
			List<CustomField> cfl = book.getCustomField();
			logger.debug("Custom field received < "+ cfl +" >");
			
			if ( cfl != null){
				logger.debug("Setting new custom field data");
				
				newBook.setCustomField(cfl);
				setCustomField(cfl);					
				updateCustomFieldData(newBook.getId());	
			} else {
				logger.debug("No Custom field create necessary");
			}

		} catch (Exception ex) {
			logger.error("Failed to create Boook data with Name : " + name);
			logger.error("Exception : " + ex.getLocalizedMessage());
			return book;
		}
		return book;
	}

} // class BookController
