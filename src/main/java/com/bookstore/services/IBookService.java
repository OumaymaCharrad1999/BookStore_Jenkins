package com.bookstore.services;

import java.util.List;

import com.bookstore.entity.Book;

public interface IBookService {
	
	List<Book> getBooks();
	Book getBook(int bookId);
	Book createBook(Book book);
	Book updateBook(int bookId, Book book);
	boolean deleteBook(int bookId);

}