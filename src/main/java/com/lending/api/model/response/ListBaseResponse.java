package com.lending.api.model.response;

import lombok.Data;

import java.util.List;

@Data
public class ListBaseResponse<T> extends BaseResponse {

  private List<T> content;

  public ListBaseResponse(String errorMessage, String errorCode, boolean success, List<T> content) {
    super(errorMessage, errorCode, success);
    this.content = content;
  }
}