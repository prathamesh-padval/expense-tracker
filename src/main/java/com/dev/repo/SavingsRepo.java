package com.dev.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dev.entity.ExpenseUser;
import com.dev.entity.Savings;

@Repository
public interface SavingsRepo extends JpaRepository<Savings, Integer> {

	public List<Savings> findByUser(ExpenseUser user);

	public List<Savings> findByUserAndMonthAndYear(ExpenseUser user, String month, String year);

	public List<Savings> findByUserAndYear(ExpenseUser user, String year);

	@Query("SELECT a FROM Savings a WHERE a.user=:user AND a.txnDate BETWEEN :fromDate AND :toDate")
	public List<Savings> findByDates(@Param("user") ExpenseUser user, @Param("fromDate") Date fromDate,
			@Param("toDate") Date toDate);

}
