package br.com.amaurygomes.ecpay.features.user.service;

import br.com.amaurygomes.ecpay.exception.UserNotFoundException;
import br.com.amaurygomes.ecpay.features.user.dto.AdminUserResponse;
import br.com.amaurygomes.ecpay.features.user.dto.CreateAdminUserRequest;
import br.com.amaurygomes.ecpay.features.user.dto.UpdateAdminUserRequest;
import br.com.amaurygomes.ecpay.features.user.entity.AdminUser;
import br.com.amaurygomes.ecpay.features.user.entity.User;
import br.com.amaurygomes.ecpay.features.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static org.springframework.util.StringUtils.hasText;

@Service
public class AdminUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public AdminUserService(UserRepository userRepository,
                            PasswordEncoder passwordEncoder,
                            UserService userService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;

    }

    public AdminUserResponse create(CreateAdminUserRequest request){
        userService.isLoginAlreadyRegistered(request.login());
        AdminUser newAdmin = AdminUser.builder()
                .name(request.name())
                .login(request.login())
                .encodedPassword(passwordEncoder.encode(request.password()))
                .role(request.role())
                .build();
        User savedUser = userRepository.save(newAdmin);
        return new AdminUserResponse(savedUser.getId(),
                savedUser.getName(),
                savedUser.getLogin(),
                ((AdminUser) savedUser).getRole().name());
    }

    public AdminUserResponse update(UUID id, UpdateAdminUserRequest request){
        AdminUser adminUser = (AdminUser) userService.existsUserById(id);
        if(hasText(request.name())){
            adminUser.setName(request.name());
        }
        if(hasText(request.login())){
            userService.isLoginAlreadyRegistered(request.login());
            adminUser.setLogin(request.login());
        }
        if(hasText(request.password())){
            adminUser.setEncodedPassword(passwordEncoder.encode(request.password()));
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

    public AdminUserResponse findById(UUID id){
        User user = userService.existsUserById(id);
        if (user instanceof AdminUser adminUser) {
            return new AdminUserResponse(
                    adminUser.getId(),
                    adminUser.getName(),
                    adminUser.getLogin(),
                    adminUser.getRole().name()
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
