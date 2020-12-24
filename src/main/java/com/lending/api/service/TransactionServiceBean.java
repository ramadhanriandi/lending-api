package com.lending.api.service;

import com.lending.api.entity.Transaction;
import com.lending.api.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class TransactionServiceBean implements TransactionService {
  @Autowired
  private TransactionRepository transactionRepository;

  @Override
  public void create(Transaction transaction) {
    transactionRepository.save(transaction);
  }

  @Override
  public List<Transaction> findByDateRange(Date startDate, Date endDate) {
    return transactionRepository.findAllByTransactionDateGreaterThanEqualAndTransactionDateLessThanEqual(startDate, endDate);
  }

  @Override
  public List<Transaction> findByUserId(Integer userId) {
    return transactionRepository.findAllByUserId(userId);
  }
}
