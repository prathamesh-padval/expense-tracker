package com.dev.service;

import java.text.ParseException;
import java.util.List;

import com.dev.entity.Savings;
import com.dev.model.InputModel;

public interface SavingsService {
	
	public Savings logSavingsTxn(InputModel input) throws ParseException;

	public List<Savings> fetchSavings(InputModel input);

}
