package fiap.tech.challenge.restaurant_manager.entites;

import fiap.tech.challenge.restaurant_manager.entites.enums.UserType;
import fiap.tech.challenge.restaurant_manager.entites.request.CreateUserRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;

@Entity(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;
    @Email(message = "Formato do e-mail inválido")
    @Column(unique = true, nullable = false)
    private String email;
    @NotBlank(message = "Usuário de login é obrigatório")
    @Column(unique = true, nullable = false)
    private String login;
    @NotBlank(message = "Senha de login é obrigatório")
    @Column(nullable = false)
    private String password;
    private LocalDateTime lastUpdate;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;


    public User(CreateUserRequest request) {
        this.name = request.name();
        this.email = request.email();
        this.login = request.login();
        this.password = request.password();
        this.address = new Address(request);
        this.userType = request.userType();
        this.lastUpdate = LocalDateTime.now();
    }

}
