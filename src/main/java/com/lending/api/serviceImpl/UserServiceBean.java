package com.lending.api.serviceImpl;

import com.lending.api.entity.User;
import com.lending.api.repository.UserRepository;
import com.lending.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceBean implements UserService {
  @Autowired
  private UserRepository userRepository;

  @Override
  public void create(User user) { userRepository.save(user); }

  @Override
  public List<User> find() { return userRepository.findAll(); }
}
