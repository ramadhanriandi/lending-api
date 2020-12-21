package com.lending.api.service;

import com.lending.api.entity.User;

import java.util.List;

public interface UserService {
  void create(User user);

  List<User> find();
}
