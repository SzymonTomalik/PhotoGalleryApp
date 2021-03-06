package pl.szymontomalik.PhotoGalleryApp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import pl.szymontomalik.PhotoGalleryApp.entities.Album;
import pl.szymontomalik.PhotoGalleryApp.repositories.AlbumRepository;

import java.util.List;
@Service
@RequiredArgsConstructor
public class AlbumService {
    private final AlbumRepository albumRepository;
    private final UserService userService;
    private final EmailServiceImpl emailService;

    public List<Album> getAdminsAlbums(Long adminId) {
        return albumRepository.findAllByAdminId(adminId);
    }
    public List<Album> getUserAlbums(String userEmail) {
        return albumRepository.findAllSharedByEmail(userEmail);
    }
    public Album getAlbumById(Long albumId) {
        return albumRepository.findById(albumId).get();
    }


    public void saveAlbumPassword(Album album) {
        album.setPassword(BCrypt.hashpw(album.getPassword(), BCrypt.gensalt()));
        albumRepository.save(album);
    }
    public void save(Album album) {
        albumRepository.save(album);
    }
    public void share(Album album) {
        album.setShared(true);
        save(album);

    }
    public void sendEmailAboutSharing(Album album, String pass) {
        if (!userService.isUserExist(album.getEmail())) {
            emailService.sendSimpleMessage(album.getEmail(), "Register to see your photos", "New folder shared for you was appears in PhotoGalerryApp.\n" +
                    "Register to receive the album password and view your photos. Regards. www.PhotoGallerryApp.com");
        } else {
            emailService.sendSimpleMessage(album.getEmail(), "Login to see your photos", "New folder shared for you was appears in PhotoGalerryApp.\n" +
                    "Log in and type password: \"" + pass + "\" and view your photos. Regards. www.PhotoGallerryApp.com");
        }
    }

    }
