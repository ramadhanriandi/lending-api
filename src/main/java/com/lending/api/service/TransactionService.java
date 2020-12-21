package com.lending.api.service;

import com.lending.api.entity.Transaction;

import java.util.Date;
import java.util.List;

public interface TransactionService {
  void create(Transaction transaction);

  List<Transaction> findByDateRange(Date startDate, Date endDate);

  List<Transaction> findByUserId(Integer userId);
}
