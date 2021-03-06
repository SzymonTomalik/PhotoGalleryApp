package pl.szymontomalik.PhotoGalleryApp.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.szymontomalik.PhotoGalleryApp.entities.AppUser;
import pl.szymontomalik.PhotoGalleryApp.entities.Role;
import pl.szymontomalik.PhotoGalleryApp.models.RegistrationForm;
import pl.szymontomalik.PhotoGalleryApp.repositories.RoleRepository;

import java.util.Collections;
import java.util.HashSet;

@Component
@RequiredArgsConstructor
public class UserConverter {
    private final RoleRepository roleRepository;
    public AppUser convertRegistrationToUser(RegistrationForm registration) {
        AppUser user = new AppUser();
        user.setLogin(registration.getLogin());
        user.setEmail(registration.getEmail());
        user.setPassword(registration.getPassword());
        int formRole = registration.getRole();
        Role role;
        if (formRole == 2) {
            role = roleRepository.findByName("ROLE_ADMIN");
        } else {
            role = roleRepository.findByName("ROLE_USER");
        }
        user.setRoles(new HashSet<>(Collections.singletonList(role)));
        return user;
    }
}
