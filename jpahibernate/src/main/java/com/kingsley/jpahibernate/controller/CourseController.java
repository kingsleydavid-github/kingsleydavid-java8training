package com.kingsley.jpahibernate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kingsley.jpahibernate.model.studentpassport.Course;
import com.kingsley.jpahibernate.service.CourseService;

@RestController
@RequestMapping("/course")
public class CourseController {

	@Autowired
	CourseService courseSvc;
	
	@GetMapping("/{courseId}")
	public @ResponseBody ResponseEntity<?> getCourseById(@PathVariable Integer courseId){
		
		Course courseDetail = courseSvc.getCourseDetails(courseId);
		if(courseDetail != null) {
			return ResponseEntity.ok(courseDetail);
		} else
		{
			return ResponseEntity.notFound().build();
		}
	}
	
}
