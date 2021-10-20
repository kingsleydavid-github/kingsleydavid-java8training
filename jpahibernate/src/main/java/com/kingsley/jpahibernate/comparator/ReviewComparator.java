package com.kingsley.jpahibernate.comparator;

import java.util.Comparator;

import org.springframework.stereotype.Component;

import com.kingsley.jpahibernate.model.studentpassport.Review;

@Component
public class ReviewComparator implements Comparator<Review> {

	@Override
	public int compare(Review o1, Review o2) {
		return 0;
	}

}
