package com.kingsley.jpahibernate.model.studentpassport;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="passport")
public class Passport implements Serializable {

	/*
		CREATE TABLE `passport` (
		  `passport_id` int NOT NULL AUTO_INCREMENT,
		  `passport_number` varchar(45) DEFAULT NULL,
		  PRIMARY KEY (`passport_id`)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
	 * */
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "passport_id")
	private Integer passportId;

	@Column(name = "passport_number")
	private String passportNumber;

	public Integer getPassportId() {
		return passportId;
	}

	public void setPassportId(Integer passportId) {
		this.passportId = passportId;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}
}
