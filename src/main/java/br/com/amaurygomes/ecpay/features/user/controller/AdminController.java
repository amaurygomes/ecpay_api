package br.com.amaurygomes.ecpay.features.user.controller;

import br.com.amaurygomes.ecpay.features.user.dto.AdminUserResponse;
import br.com.amaurygomes.ecpay.features.user.dto.CreateAdminUserRequest;
import br.com.amaurygomes.ecpay.features.user.dto.UpdateAdminUserRequest;
import br.com.amaurygomes.ecpay.features.user.service.AdminUserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user/admin")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Admin Users" , description = "Endpoints para gerenciamento de administradores" )
public class AdminController {
    private final AdminUserService adminUserService;

    @PostMapping
    public ResponseEntity<AdminUserResponse> create(@Valid @RequestBody CreateAdminUserRequest request){
        return ResponseEntity.ok(adminUserService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdminUserResponse> update(@PathVariable UUID id,@Valid @RequestBody UpdateAdminUserRequest request){
        return ResponseEntity.ok(adminUserService.update(id, request));
    }

    @GetMapping
    public ResponseEntity<List<AdminUserResponse>> findAll(){
        return ResponseEntity.ok(adminUserService.findAll());
    }

    @GetMapping("/{id}" )
    public ResponseEntity<AdminUserResponse> findById(@PathVariable UUID id){
        return ResponseEntity.ok(adminUserService.findById(id));
    }
    @DeleteMapping("/{id}")

    public ResponseEntity<Void> delete(@PathVariable UUID id){
        adminUserService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
