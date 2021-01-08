package com.dev.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dev.entity.ExpenseUser;
import com.dev.entity.Transaction;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Integer> {

	public List<Transaction> findByUser(ExpenseUser user);

	public List<Transaction> findByUserAndMonthAndYear(ExpenseUser user, String month, String year);

	@Query("SELECT a FROM Transaction a WHERE a.user=:user AND a.txnDate BETWEEN :fromDate AND :toDate")
	public List<Transaction> findByDate(@Param("user") ExpenseUser user, @Param("fromDate") Date fromDate,
			@Param("toDate") Date toDate);

	public List<Transaction> findByUserAndTxnMode(ExpenseUser user, String txnMode);
	
	public List<Transaction> findByUserAndTxnModeAndMonthAndYear(ExpenseUser user, String txnMode,String month,String year);

	@Query("SELECT a FROM Transaction a WHERE a.user=:user AND a.txnMode=:txnMode AND a.txnDate BETWEEN :fromDate AND :toDate")
	public List<Transaction> findByModeAndDate(@Param("user") ExpenseUser user, @Param("txnMode")String txnMode,@Param("fromDate") Date fromDate,
			@Param("toDate") Date toDate);
	
	
}
