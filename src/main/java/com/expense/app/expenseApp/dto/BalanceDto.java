package com.expense.app.expenseApp.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class BalanceDto {
    @NotNull(message = "userId should not be null")
    private long user_id;

    @NotNull(message = "balance not available")
    private BigDecimal balance;
}
