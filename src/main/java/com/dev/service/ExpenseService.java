package com.dev.service;

import java.text.ParseException;
import java.util.List;

import com.dev.entity.ExpenseUser;
import com.dev.entity.Transaction;
import com.dev.model.InputModel;

public interface ExpenseService {

	public boolean makeTransaction(InputModel input) throws ParseException;

	public List<Transaction> findTransactions(InputModel input) throws ParseException;

	public Transaction logTxn(ExpenseUser user, InputModel input) throws ParseException;

}
