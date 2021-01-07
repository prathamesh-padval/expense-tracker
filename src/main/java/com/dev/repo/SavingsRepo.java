package com.dev.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.entity.ExpenseUser;
import com.dev.entity.Savings;

@Repository
public interface SavingsRepo extends JpaRepository<Savings, Integer> {

	public List<Savings> findByUser(ExpenseUser user);

	public List<Savings> findByUserAndMonthAndYear(ExpenseUser user, String month, String year);

	public List<Savings> findByUserAndYear(ExpenseUser user, String year);

}
