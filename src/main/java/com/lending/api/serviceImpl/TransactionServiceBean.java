package com.lending.api.serviceImpl;

import com.lending.api.entity.Transaction;
import com.lending.api.repository.TransactionRepository;
import com.lending.api.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

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
