package com.dev.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.entity.ExpenseUser;

@Repository
public interface ExpenseUserRepo extends JpaRepository<ExpenseUser, Integer> {

	public ExpenseUser findByUserName(String userName);

}
