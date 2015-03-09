package com.cmcltd.flexfield.data.jpa.repository;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cmcltd.flexfield.data.jpa.domain.Book;

/**
 * A DAO for the entity User is simply created by extending the CrudRepository
 * interface provided by spring. The following methods are some of the ones
 * available from such interface: save, delete, deleteAll, findOne and findAll.
 * The magic is that such methods must not be implemented, and moreover it is
 * possible create new query methods working only by defining their signature!
 * 
 * @author CMC Limited
 */
@Transactional
public interface BookRepository extends CrudRepository<Book, Long> {

  /**
   * Return the user having the passed email or null if no user is found.
   * 
   * @param Name the Book's Name.
   */
  public Book findByName(String name);
  
  @Query("select b from BOOK b where b.id = ?1")
  public Book findById(Integer id);

} 
