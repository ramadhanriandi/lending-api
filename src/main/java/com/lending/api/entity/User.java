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
@Table(name = User.TABLE_NAME)
public class User {
  public static final String TABLE_NAME = "users";

  public static final String COLUMN_USER_ID = "user_id";
  public static final String COLUMN_NAME = "name";
  public static final String COLUMN_BIRTH_DATE = "birth_date";
  public static final String COLUMN_ADDRESS = "address";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = User.COLUMN_USER_ID)
  private int userId;

  @Column(name = User.COLUMN_NAME)
  private String name;

  @Column(name = User.COLUMN_BIRTH_DATE)
  private Date birthDate;

  @Column(name = User.COLUMN_ADDRESS)
  private String address;

  public User(String name, Date birthDate, String address) {
    this.name = name;
    this.birthDate = birthDate;
    this.address = address;
  }
}
