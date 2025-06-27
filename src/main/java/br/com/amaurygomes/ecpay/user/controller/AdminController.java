package br.com.amaurygomes.ecpay.user.controller;

import br.com.amaurygomes.ecpay.user.dto.AdminUserResponse;
import br.com.amaurygomes.ecpay.user.dto.CreateAdminUserRequest;
import br.com.amaurygomes.ecpay.user.service.AdminUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminUserService adminUserService;

    @PostMapping
    public ResponseEntity<AdminUserResponse> createAdmin(@Valid @RequestBody CreateAdminUserRequest request){
        return ResponseEntity.ok(adminUserService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<AdminUserResponse>> findAll(){
        return ResponseEntity.ok(adminUserService.findAll());
    }
}
