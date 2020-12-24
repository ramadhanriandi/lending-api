package com.lending.api.controller;

import com.lending.api.entity.Transaction;
import com.lending.api.entity.User;
import com.lending.api.model.request.CreateTransactionRequest;
import com.lending.api.model.request.SearchTransactionsRequest;
import com.lending.api.model.response.BaseResponse;
import com.lending.api.model.response.GetUserTransactionsResponse;
import com.lending.api.model.response.ListBaseResponse;
import com.lending.api.model.response.SearchTransactionsResponse;
import com.lending.api.service.TransactionService;
import com.lending.api.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = TransactionControllerPath.BASE_PATH)
public class TransactionController {
  @Autowired
  private TransactionService transactionService;

  @Autowired
  private UserService userService;

  @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity createTransaction(@Valid @RequestBody CreateTransactionRequest createTransactionRequest) {
    Transaction transaction = new Transaction(createTransactionRequest.getAmount(), createTransactionRequest.getTransactionDate(), createTransactionRequest.getTransactionType(), createTransactionRequest.getUserId());
    this.transactionService.create(transaction);

    return ResponseEntity.ok(new BaseResponse(null, null, true));
  }

  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity getUserTransactions(@NotBlank @RequestParam Integer userId) {
    List<Transaction> transactionList = this.transactionService.findByUserId(userId);
    List<GetUserTransactionsResponse> userTransactionsResponseList = new ArrayList<>();

    for (Transaction transaction: transactionList) {
      GetUserTransactionsResponse userTransactionsResponse = GetUserTransactionsResponse.builder().build();
      BeanUtils.copyProperties(transaction, userTransactionsResponse);
      userTransactionsResponseList.add(userTransactionsResponse);
    }

    return ResponseEntity.ok(new ListBaseResponse<>(null, null, true, userTransactionsResponseList));
  }

  @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, value = TransactionControllerPath.SEARCH)
  public ResponseEntity searchTransactions(@Valid @RequestBody SearchTransactionsRequest searchTransactionsRequest) {
    List<Transaction> transactionList = this.transactionService.findByDateRange(searchTransactionsRequest.getStartDate(), searchTransactionsRequest.getEndDate());
    List<User> userList = this.userService.find();

    Map<Integer, String> userIdNameMap = new HashMap<>();
    for (User user: userList) {
      userIdNameMap.put(user.getUserId(), user.getName());
    }

    List<SearchTransactionsResponse> searchTransactionsResponseList = new ArrayList<>();

    for (Transaction transaction: transactionList) {
      SearchTransactionsResponse searchTransactionsResponse = SearchTransactionsResponse.builder().build();
      BeanUtils.copyProperties(transaction, searchTransactionsResponse);
      searchTransactionsResponse.setName(userIdNameMap.get(transaction.getUserId()));

      searchTransactionsResponseList.add(searchTransactionsResponse);
    }

    return ResponseEntity.ok(new ListBaseResponse<>(null, null, true, searchTransactionsResponseList));
  }
}
