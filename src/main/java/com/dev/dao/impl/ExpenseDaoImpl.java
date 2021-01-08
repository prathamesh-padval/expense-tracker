package com.dev.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.dao.ExpenseDao;
import com.dev.entity.ExpenseUser;
import com.dev.entity.Investment;
import com.dev.entity.Savings;
import com.dev.entity.Transaction;
import com.dev.repo.ExpenseUserRepo;
import com.dev.repo.InvestmentRepo;
import com.dev.repo.SavingsRepo;
import com.dev.repo.TransactionRepo;

@Service
public class ExpenseDaoImpl implements ExpenseDao {

	@Autowired
	ExpenseUserRepo userRepo;

	@Autowired
	TransactionRepo txnRepo;

	@Autowired
	SavingsRepo savingsRepo;

	@Autowired
	InvestmentRepo invRepo;

	@Override
	public ExpenseUser addUser(ExpenseUser user) {
		return userRepo.save(user);
	}

	@Override
	public boolean ifUserExist(String userName) {
		ExpenseUser user = userRepo.findByUserName(userName);

		return user != null ? true : false;
	}

	@Override
	public Transaction addTransaction(Transaction txn) {
		return txnRepo.save(txn);
	}

	@Override
	public List<Transaction> findTxnByUser(ExpenseUser user) {
		return txnRepo.findByUser(user);
	}

	@Override
	public List<Transaction> findTxnsByUserAndMonth(ExpenseUser user, String month, String year) {
		return txnRepo.findByUserAndMonthAndYear(user, month, year);
	}

	@Override
	public ExpenseUser findUser(String userName) {
		return userRepo.findByUserName(userName);
	}

	@Override
	public List<Transaction> findTxnsByDate(ExpenseUser user, Date fromDate, Date toDate) {
		return txnRepo.findByDate(user, fromDate, toDate);
	}

	@Override
	public List<Transaction> findTxnsByMode(ExpenseUser user, String txnMode) {
		return txnRepo.findByUserAndTxnMode(user, txnMode);
	}
	
	@Override
	public List<Transaction> findTxnsByModeAndMonthYear(ExpenseUser user, String txnMode, String month, String year) {
		return txnRepo.findByUserAndTxnModeAndMonthAndYear(user, txnMode, month, year);
	}

	@Override
	public List<Transaction> findTxnsByModeAndCustomDate(ExpenseUser user, String txnMode, Date fromDate, Date toDate) {
		return txnRepo.findByModeAndDate(user, txnMode, fromDate, toDate);
	}
	
	@Override
	public Savings addSavingsTxn(Savings saving) {
		return savingsRepo.save(saving);
	}

	@Override
	public List<Savings> fetchSavingsByYear(ExpenseUser user, String year) {
		return savingsRepo.findByUserAndYear(user, year);
	}

	@Override
	public List<Savings> fetchSavingsByDate(ExpenseUser user, Date fromDate, Date toDate) {
		return savingsRepo.findByDates(user, fromDate, toDate);
	}
	
	@Override
	public List<Savings> fetchSavings(ExpenseUser user) {
		return savingsRepo.findByUser(user);
	}

	@Override
	public Investment addInvestmentTxn(Investment investment) {
		return invRepo.save(investment);
	}

	@Override
	public List<Investment> fetchInvestmentsByMonth(ExpenseUser user, String month, String year) {
		return invRepo.findByUserAndMonthAndYear(user, month, year);
	}

	@Override
	public List<Investment> fetchInvestmentsByUser(ExpenseUser user) {
		return invRepo.findByUser(user);
	}

}
