package pl.szymontomalik.PhotoGalleryApp.services;import lombok.RequiredArgsConstructor;import org.springframework.stereotype.Service;import pl.szymontomalik.PhotoGalleryApp.entities.Album;import pl.szymontomalik.PhotoGalleryApp.repositories.AlbumRepository;import java.util.List;@Service@RequiredArgsConstructorpublic class AlbumService {    private final AlbumRepository albumRepository;    public List<Album> getAdminsAlbums(Long adminId) {        return albumRepository.findAllByAdminId(adminId);    }    public List<Album> getUserAlbums(String userEmail) {        return albumRepository.findAllByEmail(userEmail);    }}