package com.kingsley.jpahibernate.repo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kingsley.jpahibernate.model.studentpassport.Review;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Integer> {

}
