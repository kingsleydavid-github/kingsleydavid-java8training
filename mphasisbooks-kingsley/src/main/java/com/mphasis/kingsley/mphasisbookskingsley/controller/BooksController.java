package com.mphasis.kingsley.mphasisbookskingsley.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mphasis.kingsley.mphasisbookskingsley.model.Book;
import com.mphasis.kingsley.mphasisbookskingsley.service.BookService;

@RestController
@RequestMapping("/")
public class BooksController {

	@Autowired
	BookService bookService;
	
	
	@GetMapping("/")
	public @ResponseBody ResponseEntity<?> getAll(){
		
		List<Book> books = bookService.getAll(); 
		return ResponseEntity.ok(books);
	}

	@GetMapping("/{bookId}")
	public @ResponseBody ResponseEntity<?> getById(@PathVariable Long bookId){
		Book book = bookService.findByBookId(bookId); 
		return ResponseEntity.ok(book);
	}

	@GetMapping("/search/{querySt}")
	public @ResponseBody ResponseEntity<?> getById(@PathVariable String querySt){
		List<Book> searchResult = bookService.findAllByTitleLike(querySt); 
		return ResponseEntity.ok(searchResult);
	}
	
}
