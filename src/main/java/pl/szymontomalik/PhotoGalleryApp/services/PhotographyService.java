package pl.szymontomalik.PhotoGalleryApp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.szymontomalik.PhotoGalleryApp.entities.Photography;
import pl.szymontomalik.PhotoGalleryApp.repositories.PhotographyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhotographyService {
    private final PhotographyRepository photoRepo;

    public void save(Photography photography) {
        photoRepo.save(photography);
    }
    public List<Photography> getAlbumPhotos(Long albumId) {
        return photoRepo.findAllByAlbumId(albumId);
    }
}
