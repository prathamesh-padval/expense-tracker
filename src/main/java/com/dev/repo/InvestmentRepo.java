package com.dev.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.entity.ExpenseUser;
import com.dev.entity.Investment;

public interface InvestmentRepo extends JpaRepository<Investment, Integer> {

	List<Investment> findByUser(ExpenseUser user);

	List<Investment> findByUserAndMonthAndYear(ExpenseUser user, String month, String year);
}
