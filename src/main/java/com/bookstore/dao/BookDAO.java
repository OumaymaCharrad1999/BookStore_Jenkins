package com.bookstore.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.entity.Book;

@Transactional
@Repository
public class BookDAO implements IBookDAO {
	
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * This method is responsible to get all books available in database and return it as List<Book>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Book> getBooks() {
		String hql = "FROM Book as atcl ORDER BY atcl.id";
		return (List<Book>) entityManager.createQuery(hql).getResultList();
	}

	/**
	 * This method is responsible to get a particular Book detail by given book id 
	 */
	@Override
	public Book getBook(int bookId) {
		return entityManager.find(Book.class, bookId);
	}

	/**
	 * This method is responsible to create new book in database
	 */
	@Override
	public Book createBook(Book book) {
		entityManager.persist(book);
		Book b = getLastInsertedBook();
		return b;
	}

	/**
	 * This method is responsible to update book detail in database
	 */
	@Override
	public Book updateBook(int bookId, Book book) {
		Book bookFromDB = getBook(bookId);
		bookFromDB.setName(book.getName());
		bookFromDB.setAuthor(book.getAuthor());
		bookFromDB.setLanguage(book.getLanguage());
		bookFromDB.setPages(book.getPages());
		bookFromDB.setPrice(book.getPrice());
		entityManager.flush();
		Book updatedBook = getBook(bookId);
		return updatedBook;
	}

	/**
	 * This method is responsible for deleting a particular(which id will be passed that record) 
	 * record from the database
	 */
	@Override
	public boolean deleteBook(int bookId) {
		Book book = getBook(bookId);
		entityManager.remove(book);
		boolean status = entityManager.contains(book);
		if(status){
			return false;
		}
		return true;
	}
	
	/**
	 * This method will get the latest inserted record from the database and return the object of Book class
	 * @return book
	 */
	private Book getLastInsertedBook(){
		String hql = "from Book order by id DESC";
		Query query = entityManager.createQuery(hql);
		query.setMaxResults(1);
		Book book = (Book)query.getSingleResult();
		return book;
	}

}