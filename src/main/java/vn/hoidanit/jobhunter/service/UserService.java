package vn.hoidanit.jobhunter.service;

import org.springframework.stereotype.Service;

import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.repository.UserRepositoty;

@Service

public class UserService {

    private final UserRepositoty userRepository;

    public UserService(UserRepositoty userRepository){
        this.userRepository = userRepository;
    }

    public User handleCreateUser(User user){
       return this.userRepository.save(user);
    }

    public void handleDeleteUser(long id){
        this.userRepository.deleteById(id);
    }

}
