package com.dev.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.entity.Savings;
import com.dev.model.InputModel;
import com.dev.service.SavingsService;
import com.dev.util.ExpenseConstants;
import com.dev.util.LoggerMsgSequence;
import com.dev.util.LoggingParams;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class SavingsController {

	@Autowired
	SavingsService service;

	private static final Logger logger = LogManager.getLogger(SavingsController.class);

	@PostMapping("/log-saving")
	public ResponseEntity<?> addRecord(@RequestBody InputModel input) {
		LoggingParams logParams = new LoggingParams(input.getUserName(), ExpenseConstants.LOG_SAVINGS, "");
		try {

			logParams.setMsg("Performing operations");
			logger.info(LoggerMsgSequence.getMsg(logParams));
			
			Savings resp = service.logSavingsTxn(input);

			String response = null != resp ? ExpenseConstants.LOGGING_SUCCESS : ExpenseConstants.LOGGING_FAILED;

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@PostMapping("fetch-savings")
	public ResponseEntity<?> fetchRecords(@RequestBody InputModel input){
		LoggingParams logParams = new LoggingParams(input.getUserName(), ExpenseConstants.SAVINGS, "");
		
		try {
			logParams.setMsg("Performing operations");
			logger.info(LoggerMsgSequence.getMsg(logParams));
			
			List<Savings> list = service.fetchSavings(input);
			
			return new ResponseEntity<>(list,HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
