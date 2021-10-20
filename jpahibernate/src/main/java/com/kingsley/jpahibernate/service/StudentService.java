package com.kingsley.jpahibernate.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kingsley.jpahibernate.model.studentpassport.Student;
import com.kingsley.jpahibernate.repo.student.StudentRepo;

@Service
public class StudentService {

	@Autowired
	StudentRepo studentRepo;
	
	public Student getStudent(Integer studentId){
		Optional<Student> studObj = studentRepo.findById(studentId);
		if(studObj.isPresent())
		{
			return studObj.get();
		} else {
			return null;
		}
	}
	
	public Student saveStudent(Student student) {
		return studentRepo.save(student);
	}
	
	public List<Student> listStudents(){
		List<Student> studList = new ArrayList<>();
		studentRepo.findAll().forEach(stud -> {
			studList.add(stud);
		});
		return studList;
	}
	
}
