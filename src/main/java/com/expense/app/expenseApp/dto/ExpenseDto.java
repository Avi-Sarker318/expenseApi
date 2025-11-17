package com.expense.app.expenseApp.dto;

import lombok.Builder;
import lombok.Data;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class ExpenseDto {
    private long id;

    @NotNull(message = "user id should not be null")
    private long user_id;

    @NotNull(message = "amount not available")
    private BigDecimal amount;

    @NotNull(message = "type not be null")
    @NotBlank(message = "I'm sorry, we need a type")
    private String type;

    private Date date;



}
