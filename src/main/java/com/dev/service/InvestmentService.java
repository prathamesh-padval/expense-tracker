package com.dev.service;

import java.text.ParseException;
import java.util.List;

import com.dev.entity.Investment;
import com.dev.model.InputModel;

public interface InvestmentService {
	
	public Investment addInvestmentRecord(InputModel input) throws ParseException;
	
	public List<Investment> fetchInvestments(InputModel input);

}
