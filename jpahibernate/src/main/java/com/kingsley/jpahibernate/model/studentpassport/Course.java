package com.kingsley.jpahibernate.model.studentpassport;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="course")
public class Course implements Serializable {

	/*
		CREATE TABLE `david`.`course` (
		  `course_id` INT NOT NULL,
		  `course_name` VARCHAR(200) NULL,
		  PRIMARY KEY (`course_id`));
	 * */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "course_id")
	private Integer coursetId;
	
	@Column(name = "course_name")
	private String courseName;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "course",fetch = FetchType.LAZY)
	private Set<Review> reviews = new HashSet<>();

	
	
	public Set<Review> getReviews() {
		return reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}

	public Integer getCoursetId() {
		return coursetId;
	}

	public void setCoursetId(Integer coursetId) {
		this.coursetId = coursetId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	
}
