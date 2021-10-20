package com.kingsley.jpahibernate.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kingsley.jpahibernate.model.studentpassport.Review;
import com.kingsley.jpahibernate.repo.student.ReviewRepo;

@Service
public class ReviewService {

	@Autowired
	ReviewRepo reviewRepo;
	

	public Review addReview(Review review) {
		reviewRepo.save(review);
		return review;
	}
	
	public Map<Integer, List<Review>> getAllGoodReviewsByRating(){
		Map<Integer, List<Review>> reviewsByCat = new HashMap<>();
		
		//Function<Review, Integer> reviewGrpByCatFn = (review) -> review.getReviewRating();
		
		//Grounping TYPE 1
		reviewsByCat = reviewRepo.findAll().stream().collect(Collectors.groupingBy(Review::getReviewRating));
		
		System.out.println(reviewsByCat);
		return reviewsByCat;

	}

	public Map<String, Set<Integer>> getAllGoodReviewsByDesc(){
		Map<String, Set<Integer>> reviewsByCat = new HashMap<>();
		Function<Review, String> courseNameFn = (review) -> review.getCourse().getCourseName();
		
		//Grounping TYPE 2
		reviewsByCat = reviewRepo.findAll().stream().collect(Collectors.groupingBy(courseNameFn, Collectors.mapping(Review::getReviewRating, Collectors.toSet())));
		System.out.println(reviewsByCat);
		return reviewsByCat;
		
	}

	public Map<String, Set<Integer>> getAllGoodReviewsByDescSupplied(){
		Map<String, Set<Integer>> reviewsByCat = new HashMap<>();
		Function<Review, String> courseNameFn = (review) -> review.getCourse().getCourseName();
		
		Supplier<Map<String, Set<Integer>>> mapSupp = () -> new HashMap<>();
		
		//Grounping TYPE 3
		reviewsByCat = reviewRepo.findAll().stream().collect(Collectors.groupingBy(courseNameFn,mapSupp,Collectors.mapping(Review::getReviewRating,Collectors.toSet())));
		System.out.println(reviewsByCat);
		return reviewsByCat;
		
	}
	public Map<String, Set<Integer>> getAllGoodReviewsByDescVariant3(){
		Map<String, Set<Integer>> reviewsByCat = new HashMap<>();
		
		System.out.println(reviewsByCat);
		return reviewsByCat;
		
	}

	public Map<String, Set<Integer>> getAllReviewVariant1(){
		Map<String, Set<Integer>> reviewsByCat = new HashMap<>();
		Function<Review, String> courseNameFn = (review) -> review.getCourse().getCourseName();
		
		Map<Integer, String> ratMap = new HashMap<>();
		ratMap.put(1, "VeryGood");
		ratMap.put(2, "Good");
		ratMap.put(3, "Bad");
		ratMap.put(4, "Poor");
		ratMap.put(5, "Worst");
		
		Function<Review, String> fnReviewMapper = (review) -> ratMap.get(review.getReviewRating()); 
		
		System.out.println(reviewRepo.findAll().stream().map(fnReviewMapper).collect(Collectors.toList()));
		
		return reviewsByCat;
		
	}
}
