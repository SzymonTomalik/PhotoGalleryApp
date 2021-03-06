package pl.szymontomalik.PhotoGalleryApp.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import pl.szymontomalik.PhotoGalleryApp.entities.AppUser;

import java.util.Collection;

public class CurrentUser extends User {
    private final AppUser user;
    public CurrentUser(String username, String password, Collection<? extends GrantedAuthority> authorities, AppUser user) {
        super(username, password,authorities);
        this.user=user;
    }
    public AppUser getUser() {
        return user;
    }
}
