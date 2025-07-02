package br.com.amaurygomes.ecpay.auth;

import br.com.amaurygomes.ecpay.user.dto.AdminUserResponse;
import br.com.amaurygomes.ecpay.user.dto.CreateAdminUserRequest;
import br.com.amaurygomes.ecpay.user.entity.AdminUser;
import br.com.amaurygomes.ecpay.user.service.AdminUserService;
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

    @PostMapping("/register")
    public ResponseEntity<AdminUserResponse> create(@Valid @RequestBody CreateAdminUserRequest request){
        return ResponseEntity.ok(adminUserService.create(request));
    }

}
