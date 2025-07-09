package br.com.amaurygomes.ecpay.features.user.service;

import br.com.amaurygomes.ecpay.exception.UserNotFoundException;
import br.com.amaurygomes.ecpay.features.user.dto.CreateDeliveryUserRequest;
import br.com.amaurygomes.ecpay.features.user.dto.DeliveryUserResponse;
import br.com.amaurygomes.ecpay.features.user.dto.UpdateDeliveryUserRequest;
import br.com.amaurygomes.ecpay.features.user.entity.DeliveryUser;
import br.com.amaurygomes.ecpay.features.user.entity.User;
import br.com.amaurygomes.ecpay.features.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static org.springframework.util.StringUtils.hasText;

@Service
public class DeliveryUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public DeliveryUserService(UserRepository userRepository,
                               PasswordEncoder passwordEncoder,
                               UserService validUserService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userService = validUserService;

        }

    public DeliveryUserResponse create(CreateDeliveryUserRequest request){
        userService.isLoginAlreadyRegistered(request.login());
        DeliveryUser newDeliveryUser = DeliveryUser.builder()
                .name(request.name())
                .login(request.login())
                .vtr(request.vtr())
                .encodedPassword(passwordEncoder.encode(request.password()))
                .build();
        User savedUser = userRepository.save(newDeliveryUser);
        return new DeliveryUserResponse(savedUser.getId(),
                savedUser.getName(),
                savedUser.getLogin(),
                ((DeliveryUser) savedUser).getVtr(),
                ((DeliveryUser) savedUser).getPlatformStatus(),
                savedUser.isActive());
    }

    public DeliveryUserResponse update(UUID id, UpdateDeliveryUserRequest request){
        DeliveryUser deliveryUser = (DeliveryUser) userService.existsUserById(id);
        if(hasText(request.name())){
            deliveryUser.setName(request.name());
        }
        if(hasText(request.login())){
            userService.isLoginAlreadyRegistered(request.login());
            deliveryUser.setLogin(request.login());
        }
        if(hasText(request.password())){
            deliveryUser.setEncodedPassword(passwordEncoder.encode(request.password()));
        }
        if(request.vtr() != null){
            deliveryUser.setVtr(request.vtr());
        }
        if(request.platformStatus() != null){
            deliveryUser.setPlatformStatus(request.platformStatus());
        }
        deliveryUser.setActive(request.isActive());
        userRepository.save(deliveryUser);
        return new DeliveryUserResponse(deliveryUser.getId(),
                deliveryUser.getName(),
                deliveryUser.getLogin(),
                deliveryUser.getVtr(),
                deliveryUser.getPlatformStatus(),
                deliveryUser.isActive());

    }

    public List<DeliveryUserResponse> findAll(){
        List<User> users = userRepository.findAll();
        return users.stream()
                .filter(user -> user instanceof DeliveryUser)
                .map(user -> {
                    DeliveryUser deliveryUser = (DeliveryUser) user;
                    return new DeliveryUserResponse(
                            deliveryUser.getId(),
                            deliveryUser.getName(),
                            deliveryUser.getLogin(),
                            deliveryUser.getVtr(),
                            deliveryUser.getPlatformStatus(),
                            deliveryUser.isActive()
                    );
                })
                .toList();
    }


    public DeliveryUserResponse findById(UUID id){
        User user = userService.existsUserById(id);
        if (user instanceof DeliveryUser deliveryUser) {
            return new DeliveryUserResponse(
                    deliveryUser.getId(),
                    deliveryUser.getName(),
                    deliveryUser.getLogin(),
                    deliveryUser.getVtr(),
                    deliveryUser.getPlatformStatus(),
                    deliveryUser.isActive()
            );
        } else {
            throw new UserNotFoundException();
        }
    }

    public void delete(UUID id){
        userService.existsUserById(id);
        userRepository.deleteById(id);
    }
}
