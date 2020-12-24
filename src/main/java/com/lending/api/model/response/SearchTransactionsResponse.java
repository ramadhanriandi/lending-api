package com.lending.api.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchTransactionsResponse implements Serializable {
  private int transactionId;
  private double amount;
  private Date transactionDate;
  private String transactionType;
  private int userId;
  private String name;
}
