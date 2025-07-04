package br.com.amaurygomes.ecpay.user.controller;

import br.com.amaurygomes.ecpay.user.dto.CreateDeliveryUserRequest;
import br.com.amaurygomes.ecpay.user.dto.DeliveryUserResponse;
import br.com.amaurygomes.ecpay.user.dto.UpdateDeliveryUserRequest;
import br.com.amaurygomes.ecpay.user.service.DeliveryUserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user/delivery")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Delivery Users" , description = "Endpoints para gerenciamento de entregadores" )
public class DeliveryUserController {
    private final DeliveryUserService deliveryUserService;

    public DeliveryUserController(DeliveryUserService deliveryUserService) {
        this.deliveryUserService = deliveryUserService;
    }

    @PostMapping
    public ResponseEntity<DeliveryUserResponse> create(@Valid @RequestBody CreateDeliveryUserRequest request){
        return ResponseEntity.ok(deliveryUserService.create(request));
    }

    @PutMapping
    public ResponseEntity<DeliveryUserResponse> update(@PathVariable UUID id, @RequestBody UpdateDeliveryUserRequest request){
        return ResponseEntity.ok(deliveryUserService.update(id, request));

    }

    @GetMapping
    public ResponseEntity<List<DeliveryUserResponse>> findAll(){
        return ResponseEntity.ok(deliveryUserService.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<DeliveryUserResponse> findById(@PathVariable UUID id){
        return ResponseEntity.ok(deliveryUserService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        deliveryUserService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
