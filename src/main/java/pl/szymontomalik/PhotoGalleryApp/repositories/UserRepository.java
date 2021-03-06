package pl.szymontomalik.PhotoGalleryApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.szymontomalik.PhotoGalleryApp.entities.AppUser;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
    boolean existsByEmail(String email);
    boolean existsByLogin(String login);
    Optional<AppUser> findByEmail(String email);

    Optional<AppUser> findByLogin(String login);
}
