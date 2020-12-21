package com.lending.api.repository;

import com.lending.api.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
  List<Transaction> findAllByTransactionDateGreaterThanEqualAndTransactionDateLessThanEqual(Date startDate, Date endDate);

  List<Transaction> findAllByUserId(Integer userId);
}
