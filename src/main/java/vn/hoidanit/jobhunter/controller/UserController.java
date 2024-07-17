package vn.hoidanit.jobhunter.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.service.UserService;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService ){
        this.userService = userService;
    }

    @GetMapping("/user/create")

    public String createNewUser(){
        User user = new User();
        user.setEmail("phamquocbao@gmail.com");
        user.setName("Bao");
        user.setPassword("1234567");

        this.userService.handleCreateUser(user);

        return "create user";
    }
}
