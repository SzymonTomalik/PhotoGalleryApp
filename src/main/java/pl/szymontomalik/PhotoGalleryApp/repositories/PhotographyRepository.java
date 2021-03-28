package pl.szymontomalik.PhotoGalleryApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.szymontomalik.PhotoGalleryApp.entities.Photography;

import java.util.List;

@Repository
public interface PhotographyRepository extends JpaRepository<Photography, Long> {
    List<Photography> findAllByAlbumId(Long albumId);
}
