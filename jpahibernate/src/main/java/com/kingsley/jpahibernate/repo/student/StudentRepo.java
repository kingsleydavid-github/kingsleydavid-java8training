package com.kingsley.jpahibernate.repo.student;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kingsley.jpahibernate.model.studentpassport.Student;

@Repository
public interface StudentRepo extends CrudRepository<Student, Integer> {

}
