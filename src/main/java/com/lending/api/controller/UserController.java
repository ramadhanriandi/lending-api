package com.lending.api.controller;

import com.lending.api.entity.User;
import com.lending.api.model.request.CreateUserRequest;
import com.lending.api.model.response.BaseResponse;
import com.lending.api.model.response.GetUsersResponse;
import com.lending.api.model.response.ListBaseResponse;
import com.lending.api.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = UserControllerPath.BASE_PATH)
public class UserController {
  @Autowired
  private UserService userService;

  @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
    User user = new User(createUserRequest.getName(), createUserRequest.getBirthDate(), createUserRequest.getAddress());
    this.userService.create(user);

    return ResponseEntity.ok(new BaseResponse(null, null, true));
  }

  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity getUsers() {
    List<User> userList = this.userService.find();
    List<GetUsersResponse> usersResponses = new ArrayList<>();

    for (User user: userList) {
      GetUsersResponse usersResponse = GetUsersResponse.builder().build();
      BeanUtils.copyProperties(user, usersResponse);
      usersResponses.add(usersResponse);
    }

    return ResponseEntity.ok(new ListBaseResponse<>(null, null, true, usersResponses));
  }
}
