package br.com.amaurygomes.ecpay.user.service;

import br.com.amaurygomes.ecpay.user.dto.AdminUserResponse;
import br.com.amaurygomes.ecpay.user.dto.CreateAdminUserRequest;
import br.com.amaurygomes.ecpay.user.dto.UpdateAdminUserRequest;
import br.com.amaurygomes.ecpay.user.entity.AdminUser;
import br.com.amaurygomes.ecpay.user.entity.User;
import br.com.amaurygomes.ecpay.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static org.springframework.util.StringUtils.hasText;

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

    public AdminUserResponse update(String id, UpdateAdminUserRequest request){
        AdminUser adminUser = (AdminUser) userRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        if(hasText(request.name())){
            adminUser.setName(request.name());
        }
        if(hasText(request.login())){
            isLoginAlreadyRegistered(request.login());
            adminUser.setLogin(request.login());
        }
        if(hasText(request.password())){
            adminUser.setEncodedPassword(request.password());
        }
        if(request.role() != null){
            adminUser.setRole(request.role());
        }
        userRepository.save(adminUser);
        return new AdminUserResponse(adminUser.getId(),
                adminUser.getName(),
                adminUser.getLogin(),
                adminUser.getRole().name());
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
        User user = userRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        if (user instanceof AdminUser adminUser) {
            return new AdminUserResponse(
                    adminUser.getId(),
                    adminUser.getName(),
                    adminUser.getLogin(),
                    adminUser.getRole().name()
            );
        } else {
            throw new IllegalArgumentException("User is not an AdminUser");
        }
    }

    public void delete(UUID id){
        if (userRepository.findById(id).isEmpty()){
            throw new IllegalArgumentException("User not found");
        }
        userRepository.deleteById(id);
    }
}
