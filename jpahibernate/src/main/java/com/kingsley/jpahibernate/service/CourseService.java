package com.kingsley.jpahibernate.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kingsley.jpahibernate.model.studentpassport.Course;
import com.kingsley.jpahibernate.repo.student.CourseRepo;

@Service
public class CourseService {

	@Autowired
	CourseRepo courseRepo;
	
	public Course getCourseDetails(Integer courseId) {
		Optional<Course> courseObj = courseRepo.findById(courseId);
		if(courseObj.isPresent()) {
			return courseObj.get();
		} else {
			return null;
		}
	}
	
}
