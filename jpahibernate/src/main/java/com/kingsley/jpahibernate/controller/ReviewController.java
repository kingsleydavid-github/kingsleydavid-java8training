package com.kingsley.jpahibernate.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kingsley.jpahibernate.model.studentpassport.Review;
import com.kingsley.jpahibernate.service.ReviewService;

@RestController
@RequestMapping("/review")
public class ReviewController {

	@Autowired
	ReviewService reviewSvc;
	
	@PostMapping("/save")
	public @ResponseBody ResponseEntity<?> addReview(@RequestBody Review review){
		return ResponseEntity.ok(reviewSvc.addReview(review));
	}
	
	@GetMapping("/byrating")
	public @ResponseBody ResponseEntity<?> getAllGoodReviewsByCategory(){
		Map<Integer, List<Review>> reviews = reviewSvc.getAllGoodReviewsByRating();
		return ResponseEntity.ok(reviews);
	}

	@GetMapping("/bydesc")
	public @ResponseBody ResponseEntity<?> getAllGoodReviewsByDesc(){
		Map<String, Set<Integer>> reviews = reviewSvc.getAllGoodReviewsByDesc();
		return ResponseEntity.ok(reviews);
	}
}
