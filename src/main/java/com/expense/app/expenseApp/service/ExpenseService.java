package com.expense.app.expenseApp.service;

import com.expense.app.expenseApp.dto.ExpenseDto;
import com.expense.app.expenseApp.entity.Expense;
import com.expense.app.expenseApp.repository.ExpenseRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final BalanceService balanceService;

    //*ADD
    public ExpenseDto addExpense(ExpenseDto dto) {
        log.info("Adding Expense: {}", dto.getUser_id());
        Expense expense = Expense.builder()
                .user_id(dto.getUser_id())
                .type(dto.getType())
                .amount(dto.getAmount())
                .date(dto.getDate())
                .build();
        Expense saved = expenseRepository.save(expense);
        balanceService.subtractFromBalance(dto.getUser_id(), dto.getAmount());
        return mapToDto(saved);
    }
    //* GET
    public List<ExpenseDto> getExpenses() {
        log.info("Fetching Expense");
        List<Expense> expenses = expenseRepository.findAll();
        if(expenses.isEmpty()) {
            throw new EntityNotFoundException("No Expenses in the database");
        }
        return expenses.stream()
                .map(this::mapToDto).toList();
    }
    //*GET
    public ExpenseDto getExpense(long id) {
        log.info("Fetching expense by Id");
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Expense not found by Id: " + id));
        return mapToDto(expense);
    }
    //*UPDATED
    public ExpenseDto updateExpense(long id, @Valid ExpenseDto dto) {
        log.info("Updating expense with ID {}", id);
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Expense not found by Id: " + id));
        expense.setUser_id(dto.getUser_id());
        expense.setType(dto.getType());
        expense.setAmount(dto.getAmount());
        expense.setDate(dto.getDate());

        Expense updated = expenseRepository.save(expense);
        return mapToDto(updated);
    }
    //*DELETED
    public void deleteExpense(long id) {
        log.info("Deleting expense");
        if(!expenseRepository.existsById(id)) {
            throw new EntityNotFoundException("Expense not in database");
        }
        expenseRepository.deleteById(id);
    }

    //* Utility
    private ExpenseDto mapToDto(Expense expense) {
        return ExpenseDto.builder()
                .id(expense.getId())
                .user_id(expense.getUser_id())
                .type(expense.getType())
                .amount(expense.getAmount())
                .date(expense.getDate())
                .build();
    }
}
