package com.cmp.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ExportService {

	private Logger logger = LoggerFactory.getLogger("export");
	
	public void exportconfig() {
		logger.info("Inside exportconfig method - INFO");
		logger.error("Inside exportconfig method - ERROR");
		logger.debug("Inside exportconfig method - DEBUG");
	}
	
}
