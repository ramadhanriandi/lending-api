package com.lending.api.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
  @NotBlank
  @Size(min = 3, max = 100)
  private String name;

  @NotBlank
  private Date birthDate;

  @NotBlank
  private String address;
}
