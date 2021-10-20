package com.cmp.app.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cmp.app.service.ExportService;
import com.cmp.app.service.QualityService;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;

@RestController
@RequestMapping("/system")
public class SystemController {

	@Autowired
	private ExportService export;
	
	@Autowired
	private QualityService quality;
	
	@GetMapping("/sourcesystem")
	public String getSourceSystems() {
		return "Get all the source systems";
	}

	@GetMapping("/decodeCountry")
	public String getDecodeCountries() {
		export.exportconfig();
		quality.add();
		return "Get all Decode Countries";
	}
	
    @RequestMapping("/changelevel")
    public String changeLogLevel(@RequestParam String loggerName, @RequestParam String level){
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        ch.qos.logback.classic.Logger logger = loggerName.equalsIgnoreCase("root")?
                loggerContext.getLogger(loggerName):loggerContext.exists(loggerName);
        if( logger !=null){
            logger.setLevel(Level.toLevel(level));
            return "Changed logger: "+loggerName+" to level : "+level;
        } else {
            return "Logger Not Found Make Sure that logger name is correct";
        }
    }	

}
