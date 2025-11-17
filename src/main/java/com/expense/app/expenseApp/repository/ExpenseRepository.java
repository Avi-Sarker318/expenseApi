package com.expense.app.expenseApp.repository;

import com.expense.app.expenseApp.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long>{

}
