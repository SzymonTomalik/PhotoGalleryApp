package pl.szymontomalik.PhotoGalleryApp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.szymontomalik.PhotoGalleryApp.models.CurrentUser;
import pl.szymontomalik.PhotoGalleryApp.services.AlbumService;
import pl.szymontomalik.PhotoGalleryApp.services.UserService;

@Controller
@RequiredArgsConstructor
public class MainPageController {
    private final UserService userService;
    private final AlbumService albumService;

    @GetMapping("/index")
    public String mainPage(Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        if (userService.isLogged()) {
            model.addAttribute("adminAlbums",albumService.getAdminsAlbums(currentUser.getUser().getId()));
            model.addAttribute("userAlbums", albumService.getUserAlbums(currentUser.getUser().getEmail()));
            return "index";
        }
        return "login";
    }
}
