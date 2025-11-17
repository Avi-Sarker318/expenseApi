package com.expense.app.expenseApp.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "balance")
@Builder
public class Balance {
    @Id
    private long user_Id;

    @Column(precision = 15, scale = 2)
    private BigDecimal balance;
}
