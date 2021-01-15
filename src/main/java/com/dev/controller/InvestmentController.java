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

import com.dev.entity.Investment;
import com.dev.model.InputModel;
import com.dev.service.InvestmentService;
import com.dev.util.ExpenseConstants;
import com.dev.util.LoggerMsgSequence;
import com.dev.util.LoggingParams;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class InvestmentController {

	@Autowired
	InvestmentService service;

	private static final Logger logger = LogManager.getLogger(InvestmentController.class);

	@PostMapping("fetch-investments")
	public ResponseEntity<?> fetchInvvestments(@RequestBody InputModel input) {
		LoggingParams logParams = new LoggingParams(input.getUserName(), "INVESTMENT", "");

		try {
			logParams.setMsg("Performing operations");
			logger.info(LoggerMsgSequence.getMsg(logParams));

			List<Investment> list = service.fetchInvestments(input);

			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("log-investment")
	public ResponseEntity<?> addInvestments(@RequestBody InputModel input) {
		LoggingParams logParams = new LoggingParams(input.getUserName(), "INVESTMENT", "");
		try {

			logParams.setMsg("Performing operations");
			logger.info(LoggerMsgSequence.getMsg(logParams));

			Investment resp = service.addInvestmentRecord(input);

			String response = null != resp ? ExpenseConstants.LOGGING_SUCCESS : ExpenseConstants.LOGGING_FAILED;

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
