package com.dev.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.entity.Transaction;
import com.dev.model.InputModel;
import com.dev.service.ExpenseService;
import com.dev.util.ExpenseConstants;
import com.dev.util.LoggerMsgSequence;
import com.dev.util.LoggingParams;

@RestController
@RequestMapping("/api")
public class ExpenseController {

	@Autowired
	ExpenseService service;

	private static final Logger logger = LogManager.getLogger(ExpenseController.class);

	@PostMapping("/fetch-transactions")
	public ResponseEntity<?> findTxns(@RequestBody InputModel input) {
		LoggingParams logParams = new LoggingParams(input.getUserName(), "Fetching Txns", "");
		try {
			logParams.setMsg("Finding Transactions");
			logger.debug(LoggerMsgSequence.getMsg(logParams));

			List<Transaction> list = service.findTransactions(input);
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/log-txn")
	public ResponseEntity<?> makeTransaction(@RequestBody InputModel input) {
		try {
			boolean resp = service.makeTransaction(input);

			String response = resp ? ExpenseConstants.LOGGING_SUCCESS : ExpenseConstants.LOGGING_FAILED;

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error : ", e);
			return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
