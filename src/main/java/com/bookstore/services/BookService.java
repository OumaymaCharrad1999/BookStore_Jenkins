package com.bookstore.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.dao.IBookDAO;
import com.bookstore.entity.Book;

@Service
public class BookService implements IBookService {
	
	@Autowired
	private IBookDAO dao;

	@Override
	public List<Book> getBooks() {
		return dao.getBooks();
	}
	
	@Override
	public Book getBook(int bookId) {
		return dao.getBook(bookId);
	}

	@Override
	public Book createBook(Book book) {
		return dao.createBook(book);
	}

	@Override
	public Book updateBook(int bookId, Book book) {
		return dao.updateBook(bookId, book);
	}

	@Override
	public boolean deleteBook(int bookId) {
		return dao.deleteBook(bookId);
	}

}