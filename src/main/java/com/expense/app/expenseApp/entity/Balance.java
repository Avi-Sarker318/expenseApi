package com.expense.app.expenseApp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "balance")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Balance {
    @Id
    private long user_Id;

    @Column(precision = 15, scale = 2)
    private BigDecimal balance;
}
