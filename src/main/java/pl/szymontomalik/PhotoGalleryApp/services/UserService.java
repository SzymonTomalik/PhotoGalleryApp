package pl.szymontomalik.PhotoGalleryApp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.szymontomalik.PhotoGalleryApp.entities.AppUser;
import pl.szymontomalik.PhotoGalleryApp.repositories.UserRepository;


@Service
@RequiredArgsConstructor
public class UserService implements AppUserService {
    private final UserRepository userRepository;

    public boolean isEmailUnique(String email) {
        return !userRepository.existsByEmail(email);
    }

    public boolean isLoginUnique(String login) {
        return !userRepository.existsByLogin(login);
    }

    @Override
    public AppUser findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public AppUser findByLogin(String login) { return userRepository.findByLogin(login).orElse(null);
    }

    @Override
    public void addUser(AppUser appUser) {
//        appUser.setEnabled(1);
        userRepository.save(appUser);

    }

    public boolean isLogged() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }

    public boolean isUserExist(String email) {
        return userRepository.existsByEmail(email);
    }
}
