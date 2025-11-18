package com.expense.app.expenseApp.controller;

import com.expense.app.expenseApp.dto.BalanceDto;
import com.expense.app.expenseApp.service.BalanceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/balance")
public class BalanceController {
    private final BalanceService balanceService;

    @PostMapping
    public ResponseEntity<BalanceDto> addBalance(@Valid @RequestBody BalanceDto dto) {
        BalanceDto balance = balanceService.addBalance(dto);
        return ResponseEntity.ok(balance);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BalanceDto> getBalanceById(@PathVariable long id) {
        BalanceDto balance = balanceService.getBalance(id);
        return ResponseEntity.ok(balance);
    }
    @PutMapping("/{id}")
    public ResponseEntity<BalanceDto> updateBalance(@PathVariable long id,@Valid @RequestBody BalanceDto dto) {
        BalanceDto balance = balanceService.updateBalance(id, dto);
        return ResponseEntity.ok(balance);
    }

}
