package br.com.amaurygomes.ecpay.user.service;

import br.com.amaurygomes.ecpay.user.dto.AdminUserResponse;
import br.com.amaurygomes.ecpay.user.dto.CreateAdminUserRequest;
import br.com.amaurygomes.ecpay.user.entity.AdminUser;
import br.com.amaurygomes.ecpay.user.entity.User;
import br.com.amaurygomes.ecpay.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AdminUserService {
    private     final UserRepository userRepository;

    public AdminUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private boolean isLoginAlreadyRegistered(String login){
        if (userRepository.findByLogin(login).isPresent()){
            throw new IllegalArgumentException("User already registered");
        }
        return false;
    }

    public AdminUserResponse create(CreateAdminUserRequest request){
        isLoginAlreadyRegistered(request.login());
        AdminUser newAdmin = AdminUser.builder()
                .name(request.name())
                .login(request.login())
                .encodedPassword(request.password())
                .role(request.role())
                .build();
        User savedUser = userRepository.save(newAdmin);
        return new AdminUserResponse(savedUser.getId(),
                savedUser.getName(),
                savedUser.getLogin(),
                ((AdminUser) savedUser).getRole().name());
    }
    
    public List<AdminUserResponse> findAll(){
        List<User> users = userRepository.findAll();
        return users.stream()
                .filter(user -> user instanceof AdminUser)
                .map(user -> {
                    AdminUser adminUser = (AdminUser) user;
                    return new AdminUserResponse(
                            adminUser.getId(),
                            adminUser.getName(),
                            adminUser.getLogin(),
                            adminUser.getRole().name()
                    );
                })
                .toList();
    }

    public AdminUserResponse findById(String id){
        return null;
    }

    public void delete(String id){
        if (userRepository.findById(UUID.fromString(id)).isEmpty()){
            throw new IllegalArgumentException("User not found");
        }
        userRepository.deleteById(UUID.fromString(id));
    }
}
