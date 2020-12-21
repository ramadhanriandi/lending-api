package com.lending.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = Transaction.TABLE_NAME)
public class Transaction {
  public static final String TABLE_NAME = "transactions";

  public static final String COLUMN_TRANSACTION_ID = "transaction_id";
  public static final String COLUMN_AMOUNT = "amount";
  public static final String COLUMN_TRANSACTION_DATE = "transaction_date";
  public static final String COLUMN_TRANSACTION_TYPE = "transaction_type";
  public static final String COLUMN_USER_ID = "user_id";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = Transaction.COLUMN_TRANSACTION_ID)
  private int transactionId;

  @Column(name = Transaction.COLUMN_AMOUNT)
  private Double amount;

  @Column(name = Transaction.COLUMN_TRANSACTION_DATE)
  private Date transactionDate;

  @Column(name = Transaction.COLUMN_TRANSACTION_TYPE)
  private String transactionType;

  @Column(name = Transaction.COLUMN_USER_ID)
  private int userId;

  public Transaction(Double amount, Date transactionDate, String transactionType, int userId) {
    this.amount = amount;
    this.transactionDate = transactionDate;
    this.transactionType = transactionType;
    this.userId = userId;
  }
}
