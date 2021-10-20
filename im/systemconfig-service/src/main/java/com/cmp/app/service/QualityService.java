package com.cmp.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class QualityService {

	private Logger logger = LoggerFactory.getLogger("Quality");
	
	public void add() {
		logger.info("Inside add method - INFO");
		logger.error("Inside add method - ERROR");
		logger.debug("Inside add method - DEBUG");
	}

}
