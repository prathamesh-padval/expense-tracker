package com.dev.dao;

import java.util.Date;
import java.util.List;

import com.dev.entity.ExpenseUser;
import com.dev.entity.Investment;
import com.dev.entity.Savings;
import com.dev.entity.Transaction;

public interface ExpenseDao {

	ExpenseUser addUser(ExpenseUser user);

	boolean ifUserExist(String userName);

	ExpenseUser findUser(String userName);

	Transaction addTransaction(Transaction txn);

	List<Transaction> findTxnByUser(ExpenseUser user);

	List<Transaction> findTxnsByUserAndMonth(ExpenseUser user, String month, String year);

	List<Transaction> findTxnsByDate(ExpenseUser user, Date fromDate, Date toDate);

	Savings addSavingsTxn(Savings saving);

	List<Savings> fetchSavingsByYear(ExpenseUser user, String year);
	
	List<Savings> fetchSavingsByDate(ExpenseUser user, Date fromDate, Date toDate);

	List<Savings> fetchSavings(ExpenseUser user);

	List<Transaction> findTxnsByMode(ExpenseUser user, String txnMode);
	
	List<Transaction> findTxnsByModeAndMonthYear(ExpenseUser user, String txnMode,String month,String year);
	
	List<Transaction> findTxnsByModeAndCustomDate(ExpenseUser user, String txnMode,Date fromDate,Date toDate);

	Investment addInvestmentTxn(Investment investment);

	List<Investment> fetchInvestmentsByUser(ExpenseUser user);

	List<Investment> fetchInvestmentsByMonth(ExpenseUser user, String month, String year);
}
