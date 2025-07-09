package br.com.amaurygomes.ecpay.user.service;

import br.com.amaurygomes.ecpay.exception.UserAlreadyRegistredException;
import br.com.amaurygomes.ecpay.exception.UserNotFoundException;
import br.com.amaurygomes.ecpay.user.entity.User;
import br.com.amaurygomes.ecpay.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
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
            throw new UserAlreadyRegistredException();
        }
    }

    public User existsUserById(UUID id){
        if (userRepository.findById(id).isEmpty()){
            throw new UserNotFoundException();
        }
        return userRepository.findById(id).get();
    }

    public UserDetails existsUserByLogin(String login){
        if (userRepository.findByLogin(login).isEmpty()){
            throw new UserNotFoundException();
        }
        return userRepository.findByLogin(login).get();
    }

}
