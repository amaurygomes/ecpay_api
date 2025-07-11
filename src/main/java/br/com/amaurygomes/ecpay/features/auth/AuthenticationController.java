package br.com.amaurygomes.ecpay.features.auth;

import br.com.amaurygomes.ecpay.features.user.dto.AdminUserResponse;
import br.com.amaurygomes.ecpay.features.user.dto.CreateAdminUserRequest;
import br.com.amaurygomes.ecpay.features.user.entity.AdminUser;
import br.com.amaurygomes.ecpay.features.user.service.AdminUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication" , description = "Endpoints para autenticação e registro de usuários" )
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final AdminUserService adminUserService;
    private final TokenService tokenService;


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationRequest request){
        var loginPassword = new UsernamePasswordAuthenticationToken(request.login(), request.password());
        var auth = this.authenticationManager.authenticate(loginPassword);
        var token = tokenService.generateToken((AdminUser) auth.getPrincipal());

        return ResponseEntity.ok().body(new LoginResponse(token));
    }

    // Endipoint apenas para testes
    @PostMapping("/register")
    public ResponseEntity<AdminUserResponse> create(@Valid @RequestBody CreateAdminUserRequest request){
        return ResponseEntity.ok(adminUserService.create(request));
    }

}
