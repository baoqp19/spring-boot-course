package vn.hoidanit.jobhunter.service;

import org.springframework.stereotype.Service;

import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.repository.UserRepositoty;

@Service

public class UserService {

    private final UserRepositoty UserRepository;

    public UserService(UserRepositoty userRepository){
        this.UserRepository = userRepository;
    }

    public User handleCreateUser(User user){
       return this.UserRepository.save(user);
    }

}
