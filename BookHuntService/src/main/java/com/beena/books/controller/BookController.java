package com.beena.books.controller;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.beena.books.entity.Book;
import com.beena.books.exceptionHandler.BooksNotFoundException;
import com.beena.books.exceptionHandler.InvalidRequestParameterExeception;
import com.beena.books.service.BooksService;

@RestController
@RequestMapping("/books")
@CrossOrigin
public class BookController {
	
	@Autowired
	BooksService booksService;
	
	@GetMapping("/search")
	public ResponseEntity<Map<String,Book>> getBooks(@RequestParam("q") String query, @RequestParam("page") int page )  {
		
		validateInput(query, page);
		Map<String,Book> filteredBooks = booksService.getBooks(query,page);
		
		if(filteredBooks == null || filteredBooks.isEmpty())
			throw new BooksNotFoundException("Books not found for query"+query+" at page-"+page);
		
		return new ResponseEntity<Map<String,Book>>(filteredBooks, HttpStatus.OK) ;
		
	}

	private void validateInput(String query, int page) {
		if(page >3)
			throw new InvalidRequestParameterExeception("Page request param value must be between 0-3");
		
		Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(query);
		boolean b = m.find();
		if(b)
			throw new InvalidRequestParameterExeception("Special charecters not allowed in query-"+query);
	}
}
