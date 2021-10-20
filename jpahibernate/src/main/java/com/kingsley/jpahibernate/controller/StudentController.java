package com.kingsley.jpahibernate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kingsley.jpahibernate.model.studentpassport.Student;
import com.kingsley.jpahibernate.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	StudentService studService;

	@Value("${server.port}")
	String serverPort;
	
	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<Student> getStudent(@PathVariable(name = "id") Integer studentId) {
		return ResponseEntity.ok(studService.getStudent(studentId));
	}

	@GetMapping("/test")
	public @ResponseBody ResponseEntity<?> studentTest() {
		System.out.println();
		return ResponseEntity.ok(serverPort);
	}
	
	
	@GetMapping("/")
	public @ResponseBody ResponseEntity<List<Student>> getAllStudent() {
		return ResponseEntity.ok(studService.listStudents());
	}

	@PostMapping("/")
	public @ResponseBody ResponseEntity<Student> saveStudent(@RequestBody Student student) {
		return ResponseEntity.ok(studService.saveStudent(student));
	}

}
