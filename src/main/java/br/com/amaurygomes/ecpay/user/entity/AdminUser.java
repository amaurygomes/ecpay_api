package br.com.amaurygomes.ecpay.user.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "admin_users")
@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
public class AdminUser extends User{

}
