package com.lending.api.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateTransactionRequest {
  @NotBlank
  private double amount;

  @NotBlank
  private Date transactionDate;

  @NotBlank
  private String transactionType;

  @NotBlank
  private int userId;
}
