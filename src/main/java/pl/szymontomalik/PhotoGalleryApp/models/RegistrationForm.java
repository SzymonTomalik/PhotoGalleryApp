package pl.szymontomalik.PhotoGalleryApp.models;

import lombok.Getter;
import lombok.Setter;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RegistrationForm {
    @NotBlank(message = "Login is mandatory")
    private String login;

    @Email
    @NotBlank (message = "Email is mandatory")
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Wprowadzane danę muszą zawierać przynajmniej {min} znaków")
    private String password;
    //można dodać pole z drugim hasło dla utworzenia późniejszej walidacji hasła.
    //w wymaganiach projektu jednak nie ma walidacji hasła.

    @NotNull(message = "Role is mandatory")
    private int role;
}
