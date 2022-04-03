package com.example.user.controller;

import com.example.user.entity.User;
import com.example.user.service.UserService;
import com.example.user.vo.UserWithDeptVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/new")
    public User createUser(@RequestBody User user){
      log.info("Inside UserController:createUser");
      return userService.saveUser(user);
    }

    @GetMapping("/{Id}")
    public User findUserById(@PathVariable("Id") Long userId){
        log.info("Inside UserController:findUserById");
        return userService.findUserById(userId);
    }

    @GetMapping("/withdept/{Id}")
    public UserWithDeptVO getUserWithDepartment(@PathVariable("Id") Long userId){
        log.info("Inside UserController:getUserWithDepartment");
        return userService.getUserWithDepartment(userId);
    }
}
