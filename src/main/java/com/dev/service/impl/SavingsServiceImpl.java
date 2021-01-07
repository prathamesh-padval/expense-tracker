package com.dev.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.dao.ExpenseDao;
import com.dev.entity.ExpenseUser;
import com.dev.entity.Savings;
import com.dev.entity.Transaction;
import com.dev.model.InputModel;
import com.dev.service.ExpenseService;
import com.dev.service.SavingsService;
import com.dev.util.ExpenseConstants;
import com.dev.util.ExpenseUtils;
import com.dev.util.LoggerMsgSequence;
import com.dev.util.LoggingParams;

@Service
public class SavingsServiceImpl implements SavingsService {

	@Autowired
	ExpenseDao dao;

	@Autowired
	ExpenseService expenseService;

	private static final Logger logger = LogManager.getLogger(SavingsServiceImpl.class);

	@Override
	public Savings logSavingsTxn(InputModel input) throws ParseException {
		LoggingParams logParams = new LoggingParams(input.getUserName(), ExpenseConstants.LOG_SAVINGS, "");

		logParams.setMsg("Finding User");
		logger.info(LoggerMsgSequence.getMsg(logParams));
		ExpenseUser user = dao.findUser(input.getUserName());

		Savings saving = new Savings();

		input.setPurpose(ExpenseConstants.SAVINGS);

		if (null != input.getDebitFrom()) {
			String debitFrom = input.getDebitFrom();
			input.setTxnMode(debitFrom);
			input.setTxnType(ExpenseConstants.DEBIT);
			saving.setDebitFrom(debitFrom);
		} else if (null != input.getCreditTo()) {
			String creditTo = input.getCreditTo();
			input.setTxnMode(creditTo);
			input.setTxnType(ExpenseConstants.CREDIT);
			saving.setCreditTo(creditTo);
		}

		logParams.setMsg("Logging Transaction");
		logger.info(LoggerMsgSequence.getMsg(logParams));
		Transaction txn = expenseService.logTxn(user, input);

		if (null != txn) {
			if (txn.getTxnType().equals(ExpenseConstants.CREDIT))
				input.setTxnType(ExpenseConstants.DEBIT);
			if (txn.getTxnType().equals(ExpenseConstants.DEBIT))
				input.setTxnType(ExpenseConstants.CREDIT);
			saving.setTxn(txn);

			Date txnDate = ExpenseUtils.convertDate(input.getTxnDate());
			saving.setTxnDate(txnDate);
			saving.setMonth(ExpenseUtils.fetchMonth(txnDate));
			saving.setYear(ExpenseUtils.fetchYear(txnDate));

			saving.setAmount(input.getAmount());
			saving.setTxnType(input.getTxnType());

			if (user != null) {
				saving.setUser(user);
			}

			logParams.setMsg("Logging savings record");
			logger.info(LoggerMsgSequence.getMsg(logParams));
			return dao.addSavingsTxn(saving);
		}

		return null;
	}

	@Override
	public List<Savings> fetchSavings(InputModel input) {
		LoggingParams logParams = new LoggingParams(input.getUserName(), ExpenseConstants.SAVINGS, "");

		logParams.setMsg("Finding User");
		logger.info(LoggerMsgSequence.getMsg(logParams));
		ExpenseUser user = dao.findUser(input.getUserName());

		if (null != input.getYear()) {

			return dao.fetchSavingsByYear(user, input.getYear());

		} else {
			return dao.fetchSavings(user);
		}
	}
}
