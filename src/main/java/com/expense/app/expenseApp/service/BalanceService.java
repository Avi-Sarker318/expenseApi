package com.expense.app.expenseApp.service;
import com.expense.app.expenseApp.dto.BalanceDto;
import com.expense.app.expenseApp.entity.Balance;
import com.expense.app.expenseApp.repository.BalanceRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
@Slf4j
@RequiredArgsConstructor
public class BalanceService {
    private final BalanceRepository balanceRepository;
    //ADD
    public BalanceDto addBalance(BalanceDto dto) {
        log.info("Add Balance");
        Balance balance = Balance.builder()
                .user_Id(dto.getUser_id())
                .balance(dto.getBalance())
                .build();
        Balance saved = balanceRepository.save(balance);
        return mapToDto(saved);
    }
    //UPDATE
    public BalanceDto updateBalance(long id, BalanceDto dto) {
        Balance balance = balanceRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("No id to update"));
        if(dto.getBalance().compareTo(BigDecimal.ZERO)< 0 ) {
            throw new IllegalArgumentException("Balance cannot be below zero");
        }
        balance.setUser_Id(dto.getUser_id());
        balance.setBalance(dto.getBalance());

        Balance updated = balanceRepository.save(balance);
        return mapToDto(updated);

    }
    //GET
    public BalanceDto getBalance(long id) {
        log.info("Fetching balance by Id");
        Balance balance = balanceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Balance not found by Id: " + id));
        return mapToDto(balance);
    }
    //UTILITY
    @Transactional
    public void subtractFromBalance(long userId, BigDecimal amount) {
        Balance balance = balanceRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Id not found"));
        if(balance.getBalance().compareTo(amount)<0) {
            throw new IllegalArgumentException("Not enough Balance");
        }
        else {
            BigDecimal newBalance = balance.getBalance().subtract(amount);
            balance.setBalance(newBalance);
            balanceRepository.save(balance);
        }
    }
    //UTILITY
    @Transactional
    public void additionFromBalance(long userId, BigDecimal amount) {
        Balance balance = balanceRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Id not found"));
        BigDecimal newBalance = balance.getBalance().add(amount);
        balance.setBalance(newBalance);
        balanceRepository.save(balance);
    }
    //UTILITY
    private BalanceDto mapToDto(Balance balance) {
        return BalanceDto.builder()
                .user_id(balance.getUser_Id())
                .balance(balance.getBalance())
                .build();

    }

}
