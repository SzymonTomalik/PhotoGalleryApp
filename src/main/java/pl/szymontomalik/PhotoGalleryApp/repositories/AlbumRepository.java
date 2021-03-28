package pl.szymontomalik.PhotoGalleryApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.szymontomalik.PhotoGalleryApp.entities.Album;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
    List<Album> findAllByAdminId(Long adminId);
    @Query("SELECT a FROM Album a WHERE a.email=?1 AND a.isShared=true" )
    List<Album> findAllSharedByEmail(String receiverEmail);
}
