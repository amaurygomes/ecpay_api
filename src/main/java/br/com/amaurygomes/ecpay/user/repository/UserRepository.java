package br.com.amaurygomes.ecpay.user.repository;

import br.com.amaurygomes.ecpay.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
