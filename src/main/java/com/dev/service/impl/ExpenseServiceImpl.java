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
import com.dev.entity.Transaction;
import com.dev.model.InputModel;
import com.dev.service.ExpenseService;
import com.dev.util.ExpenseUtils;
import com.dev.util.LoggerMsgSequence;
import com.dev.util.LoggingParams;

@Service
public class ExpenseServiceImpl implements ExpenseService {

	@Autowired
	ExpenseDao dao;

	private static final Logger logger = LogManager.getLogger(ExpenseServiceImpl.class);

	@Override
	public boolean makeTransaction(InputModel input) throws ParseException {
		LoggingParams logParams = new LoggingParams(input.getUserName(), input.getTxnType(), "");

		logParams.setMsg("Finding User");
		logger.info(LoggerMsgSequence.getMsg(logParams));
		ExpenseUser user = dao.findUser(input.getUserName());

		logParams.setMsg("Praparing Transaction Entry");
		logger.info(LoggerMsgSequence.getMsg(logParams));

		Transaction txn = logTxn(user, input);

		if (txn != null) {
			logParams.setMsg("Logged Transaction in Database");
			logger.info(LoggerMsgSequence.getMsg(logParams));

			return true;
		} else {
			logParams.setMsg("Transaction logging failed");
			logger.error(LoggerMsgSequence.getMsg(logParams));

			return false;
		}
	}

	/**
	 * Finds transactions based on several conditions 1) username/userid, month,
	 * year 2) username/userid, fromDate,toDate 3) username/userid, txnMode 4)
	 * username/userid, txnMode, month, year 5) username/userid, txnMode, fromDate,
	 * toDate 6) username/userid
	 */
	@Override
	public List<Transaction> findTransactions(InputModel input) throws ParseException {
		LoggingParams logParams = new LoggingParams(input.getUserName(), "Fetching Txns", "");

		ExpenseUser user = dao.findUser(input.getUserName());

		if (null != user) {

			// username/userid, month, year
			if ((null != input.getMonth() && null != input.getYear())
					&& (null == input.getFromDate() && null == input.getToDate())) {
				logParams.setMsg("Finding Transactions of User :" + input.getUserName() + " for the Month : "
						+ input.getMonth() + " " + input.getYear());
				logger.info(LoggerMsgSequence.getMsg(logParams));

				return dao.findTxnsByUserAndMonth(user, input.getMonth(), input.getYear());
			}
			// username/userid, fromDate,toDate
			else if ((null == input.getMonth() && null == input.getYear())
					&& (null != input.getFromDate() && null != input.getToDate())) {
				logParams.setMsg("Finding Transactions of User :" + input.getUserName() + " from : "
						+ input.getFromDate() + " to : " + input.getToDate());
				logger.info(LoggerMsgSequence.getMsg(logParams));

				Date fromDate = ExpenseUtils.convertDate(input.getFromDate());
				Date toDate = ExpenseUtils.convertDate(input.getToDate());

				return dao.findTxnsByDate(user, fromDate, toDate);

			}
			// username/userid, txnMode
			else if (null != input.getTxnMode() && (null == input.getMonth() && null == input.getYear()
					&& null == input.getFromDate() && null == input.getToDate())) {
				logParams.setMsg("Finding Transactions of User :" + input.getUserName() + " for " + input.getTxnMode()
						+ " mode.");
				logger.info(LoggerMsgSequence.getMsg(logParams));

				return dao.findTxnsByMode(user, input.getTxnMode());

			}
			// username/userid, txnMode, month, year
			else if (null != input.getTxnMode() && (null != input.getMonth() && null != input.getYear())
					&& (null == input.getFromDate() && null == input.getToDate())) {
				logParams.setMsg("Finding Transactions of User :" + input.getUserName() + " for " + input.getTxnMode()
						+ " mode, for the month " + input.getMonth() + " " + input.getYear());
				logger.info(LoggerMsgSequence.getMsg(logParams));
				return dao.findTxnsByModeAndMonthYear(user, input.getTxnMode(), input.getMonth(), input.getYear());

			}
			// username/userid, txnMode, fromDate, toDate
			else if (null != input.getTxnMode() && (null == input.getMonth() && null == input.getYear())
					&& (null != input.getFromDate() && null != input.getToDate())) {
				logParams.setMsg("Finding Transactions of User :" + input.getUserName() + " for " + input.getTxnMode()
						+ " mode, from :" + input.getFromDate() + " to : " + input.getToDate());
				logger.info(LoggerMsgSequence.getMsg(logParams));

				Date fromDate = ExpenseUtils.convertDate(input.getFromDate());
				Date toDate = ExpenseUtils.convertDate(input.getToDate());

				return dao.findTxnsByModeAndCustomDate(user, input.getTxnMode(), fromDate, toDate);

			}
			// username/userid
			else {
				logParams.setMsg("Finding Transactions of User :" + input.getUserName());
				logger.info(LoggerMsgSequence.getMsg(logParams));

				return dao.findTxnByUser(user);
			}

		} else {
			logParams.setMsg("User not found.!");
			logger.error(LoggerMsgSequence.getMsg(logParams));

			return null;
		}

	}

	@Override
	public Transaction logTxn(ExpenseUser user, InputModel input) throws ParseException {
		Transaction txnDetails = new Transaction();
		txnDetails.setAmount(input.getAmount());
		txnDetails.setPurpose(input.getPurpose());
		txnDetails.setTxnMode(input.getTxnMode());
		txnDetails.setTxnType(input.getTxnType());

		Date txnDate = ExpenseUtils.convertDate(input.getTxnDate());
		txnDetails.setTxnDate(txnDate);
		txnDetails.setMonth(ExpenseUtils.fetchMonth(txnDate));
		txnDetails.setYear(ExpenseUtils.fetchYear(txnDate));

		if (user != null) {
			txnDetails.setUser(user);
		}
		return dao.addTransaction(txnDetails);
	}

}
