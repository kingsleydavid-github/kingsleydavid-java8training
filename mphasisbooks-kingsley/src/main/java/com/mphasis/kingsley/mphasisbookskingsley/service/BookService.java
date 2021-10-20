package com.mphasis.kingsley.mphasisbookskingsley.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mphasis.kingsley.mphasisbookskingsley.model.Book;
import com.mphasis.kingsley.mphasisbookskingsley.repo.BookRepo;

@Service
public class BookService {

	private static String LOAD_BOOKS_URL = "https://s3-ap-southeast-1.amazonaws.com/he-public-data/books8f8fe52.json";

	@Autowired
	BookRepo booksRepo;

	@Autowired
	RestTemplate restTemplate;

	@PostConstruct
	public void runAfterObjectCreated() {
		ObjectMapper mapper = new ObjectMapper();
		ResponseEntity<String> books = restTemplate.getForEntity(LOAD_BOOKS_URL, String.class);
		// System.out.println(books);
		// ResponseEntity< Book[]> books = restTemplate.getForEntity(LOAD_BOOKS_URL,
		// Book[].class);
		
		try {
			Book[] bkl = new Gson().fromJson(books.getBody(), Book[].class);
			List<Book> bookList = Arrays.asList(bkl);
			booksRepo.saveAll(bookList);
			System.out.println("completed parsing");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public List<Book> getAll() {
		return booksRepo.findAll();
	}


	public Book findByBookId(Long bookId) {
		Optional<Book> book = booksRepo.findById(bookId);
		if(! book.isEmpty() && book.isPresent()) {
			return book.get();
		}
		return null;
	}

	public List<Book> findAllByTitleLike(String querySt) {
		List<Book> searchRes = booksRepo.findAll();
		return searchRes;
	}

}
