package com.kingsley.jpahibernate.model.studentpassport;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="student")
public class Student implements Serializable {

	/*
		CREATE TABLE `student` (
		  `student_id` int NOT NULL AUTO_INCREMENT,
		  `student_name` varchar(45) DEFAULT NULL,
		  `passport_id` int DEFAULT NULL,
		  PRIMARY KEY (`student_id`),
		  KEY `fk_passport_id_idx` (`passport_id`),
		  CONSTRAINT `fk_passport_id` FOREIGN KEY (`passport_id`) REFERENCES `passport` (`passport_id`)
		) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
	*
	 * */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "student_id")
	private Integer studentId;
	
	@Column(name = "student_name")
	private String studentName;
	
	//@Column(name = "passport_id")
	//private Integer passportId;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "passport_id")
	private Passport passport;

	public Passport getPassport() {
		return passport;
	}

	public void setPassport(Passport passport) {
		this.passport = passport;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	
}
