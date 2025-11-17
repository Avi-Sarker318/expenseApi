package com.expense.app.expenseApp.controller;

import com.expense.app.expenseApp.dto.BalanceDto;
import com.expense.app.expenseApp.dto.ExpenseDto;
import com.expense.app.expenseApp.service.ExpenseService;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Builder

public class ExpenseController {
    public final ExpenseService expenseService;

    @PutMapping
    public ResponseEntity<ExpenseDto> addExpense(@Valid @RequestBody ExpenseDto dto) {
        ExpenseDto expense = expenseService.addExpense(dto);
        return ResponseEntity.ok(expense);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ExpenseDto> updateExpense(@PathVariable long id, @Valid @RequestBody ExpenseDto dto) {
        ExpenseDto expense = expenseService.updateExpense(id, dto);
        return ResponseEntity.ok(expense);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ExpenseDto> getExpenseById(@PathVariable long id) {
        ExpenseDto expense = expenseService.getExpense(id);
        return ResponseEntity.ok(expense);
    }
    @GetMapping
    public ResponseEntity<List<ExpenseDto>> getExpenses() {
        List<ExpenseDto> expenses = expenseService.getExpenses();
        return ResponseEntity.ok(expenses);
    }
}
