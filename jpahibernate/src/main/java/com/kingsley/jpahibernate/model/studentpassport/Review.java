package com.kingsley.jpahibernate.model.studentpassport;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="review")
public class Review implements Serializable {

	/*
		CREATE TABLE `david`.`review` (
		  `review_id` INT NOT NULL AUTO_INCREMENT,
		  `review_rating` INT NULL,
		  `review_desc` VARCHAR(200) NULL,
		  PRIMARY KEY (`review_id`));
	 * */
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "review_id")
	private Integer reviewtId;
	
	@Column(name = "review_rating")
	private Integer reviewRating;

	@Column(name = "review_desc")
	private String reviewDesc;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;
	
	
	
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Review() {
		
	}
	
	public Review(Integer rat, String desc) {
		this.reviewRating = rat;
		this.reviewDesc = desc;
	}
	
	public Integer getReviewtId() {
		return reviewtId;
	}

	public void setReviewtId(Integer reviewtId) {
		this.reviewtId = reviewtId;
	}

	public Integer getReviewRating() {
		return reviewRating;
	}

	public void setReviewRating(Integer reviewRating) {
		this.reviewRating = reviewRating;
	}

	public String getReviewDesc() {
		return reviewDesc;
	}

	public void setReviewDesc(String reviewDesc) {
		this.reviewDesc = reviewDesc;
	}
	
}
