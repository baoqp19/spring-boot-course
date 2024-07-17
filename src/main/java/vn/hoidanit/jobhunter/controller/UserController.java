package vn.hoidanit.jobhunter.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.service.UserService;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService ){
        this.userService = userService;
    }

    // @GetMapping("/user/create")
    //  id = 0, id = null tạo mới người dùng
    // Post là truyền data lên
    @PostMapping("/user/create")
    public User createNewUser(
        @RequestBody User postManUser
    ){
        User abcUser = this.userService.handleCreateUser(postManUser);

        return abcUser;
    }
}