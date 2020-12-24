package com.lending.api.service;

import com.lending.api.entity.User;
import com.lending.api.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceBean implements UserService {
  @Autowired
  private UserRepository userRepository;

  @Override
  public void create(User user) { userRepository.save(user); }

  @Override
  public List<User> find() { return userRepository.findAll(); }
}
