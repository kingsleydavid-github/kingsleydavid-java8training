package com.kingsley.jpahibernate.repo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kingsley.jpahibernate.model.studentpassport.Course;

@Repository
public interface CourseRepo extends JpaRepository<Course, Integer> {

}
