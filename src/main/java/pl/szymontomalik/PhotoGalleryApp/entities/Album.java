package pl.szymontomalik.PhotoGalleryApp.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "albums")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Album name is mandatory")
    private String name;
    @Size(min = 8)
    private String password;
    @OneToMany
    private List<Photography> photos;
    @NotBlank(message = "Email is mandatory")
    @Email
    private String email;
    private Long adminId;
    private boolean isShared = false;

}
