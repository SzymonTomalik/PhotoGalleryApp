package pl.szymontomalik.PhotoGalleryApp.repositories;import org.springframework.data.jpa.repository.JpaRepository;import org.springframework.stereotype.Repository;import pl.szymontomalik.PhotoGalleryApp.entities.Album;@Repositorypublic interface AlbumRepository extends JpaRepository<Album, Long> {}