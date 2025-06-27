package br.com.amaurygomes.ecpay.user.service;

import br.com.amaurygomes.ecpay.user.entity.AdminUser;
import br.com.amaurygomes.ecpay.user.entity.User;
import br.com.amaurygomes.ecpay.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public void isLoginAlreadyRegistered(String login){
        if (userRepository.findByLogin(login).isPresent()){
            throw new IllegalArgumentException("User already registered");
        }
    }

    public User existsUserById(UUID id){
        if (userRepository.findById(id).isEmpty()){
            throw new IllegalArgumentException("User not found");
        }
        return userRepository.findById(id).get();
    }

    public User existsUserByLogin(String login){
        if (userRepository.findByLogin(login).isEmpty()){
            throw new IllegalArgumentException("User not found");
        }
        return userRepository.findByLogin(login).get();
    }

}
