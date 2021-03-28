package pl.szymontomalik.PhotoGalleryApp.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "users")
public class AppUser{
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    @NotBlank (message = "Login is mandatory")
    private String login;

    @Email
    @Column(unique = true)
    @NotBlank (message = "Email is mandatory")
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8)
    private String password = BCrypt.hashpw("", BCrypt.gensalt()); ;

    @NotNull
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @OneToMany //a może ManyToMany
    private List<Album> albums;

//    private int enabled;

    public AppUser() {
    }

    public AppUser(@NotBlank(message = "Login is mandatory") String login, @NotBlank(message = "Password is mandatory") @Size(min = 8) String password, @Email @NotBlank(message = "Email is mandatory") String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public String getUsername() {
        return login;
    }

}
