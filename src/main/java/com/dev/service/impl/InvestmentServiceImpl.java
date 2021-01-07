package com.dev.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.dev.dao.ExpenseDao;
import com.dev.entity.ExpenseUser;
import com.dev.entity.Investment;
import com.dev.entity.Transaction;
import com.dev.model.InputModel;
import com.dev.service.ExpenseService;
import com.dev.service.InvestmentService;
import com.dev.util.ExpenseConstants;
import com.dev.util.ExpenseUtils;

public class InvestmentServiceImpl implements InvestmentService {

	@Autowired
	ExpenseDao dao;

	@Autowired
	ExpenseService expService;

	@Override
	public Investment addInvestmentRecord(InputModel input) throws ParseException {

		ExpenseUser user = dao.findUser(input.getUserName());

		Investment invest = new Investment();

		input.setPurpose(ExpenseConstants.INVESTMENT);

		if (null != input.getDebitFrom()) {
			String debitFrom = input.getDebitFrom();
			input.setTxnMode(debitFrom);
			input.setTxnType(ExpenseConstants.DEBIT);
		} else if (null != input.getCreditTo()) {
			String creditTo = input.getCreditTo();
			input.setTxnMode(creditTo);
			input.setTxnType(ExpenseConstants.CREDIT);
		}

		Transaction txn = expService.logTxn(user, input);

		if (null != txn) {
			if (txn.getTxnType().equals(ExpenseConstants.CREDIT))
				input.setTxnType(ExpenseConstants.DEBIT);
			if (txn.getTxnType().equals(ExpenseConstants.DEBIT))
				input.setTxnType(ExpenseConstants.CREDIT);

			invest.setCategory(input.getInvCategory());
			invest.setTxn(txn);

			Date txnDate = ExpenseUtils.convertDate(input.getTxnDate());
			invest.setTxnDate(txnDate);
			invest.setMonth(ExpenseUtils.fetchMonth(txnDate));
			invest.setYear(ExpenseUtils.fetchYear(txnDate));

			invest.setAmount(input.getAmount());
			invest.setTxnType(input.getTxnType());

			if (user != null) {
				invest.setUser(user);
			}

			return dao.addInvestmentTxn(invest);
		}

		return null;
	}

	@Override
	public List<Investment> fetchInvestments(InputModel input) {

		ExpenseUser user = dao.findUser(input.getUserName());
		if (null != user) {
			if (null != input.getMonth() && null != input.getYear()) {
				return dao.fetchInvestmentsByMonth(user, input.getMonth(), input.getYear());
			} else {
				return dao.fetchInvestmentsByUser(user);
			}
		}	
		return null;
	}
}
