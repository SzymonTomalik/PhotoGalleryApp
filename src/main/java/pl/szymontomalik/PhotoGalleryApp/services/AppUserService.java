package pl.szymontomalik.PhotoGalleryApp.services;

import pl.szymontomalik.PhotoGalleryApp.entities.AppUser;

public interface AppUserService {
    AppUser findByEmail(String email);
    void addUser (AppUser appUser);
}
