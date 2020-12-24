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
public class GetUsersResponse implements Serializable {
  private int userId;
  private String name;
  private Date birthDate;
  private String address;
}
