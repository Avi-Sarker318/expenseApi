package com.expense.app.expenseApp.repository;

import com.expense.app.expenseApp.entity.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {

}
