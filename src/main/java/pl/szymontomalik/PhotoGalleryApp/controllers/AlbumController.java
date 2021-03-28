package pl.szymontomalik.PhotoGalleryApp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.szymontomalik.PhotoGalleryApp.entities.Album;
import pl.szymontomalik.PhotoGalleryApp.models.CurrentUser;
import pl.szymontomalik.PhotoGalleryApp.models.Pass;
import pl.szymontomalik.PhotoGalleryApp.services.AlbumService;
import pl.szymontomalik.PhotoGalleryApp.services.PhotographyService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class AlbumController {
    private final AlbumService albumService;
    private final PhotographyService photoService;

    @GetMapping("/album/{id}")
    public String openAlbum(Model model, @PathVariable Long id, @AuthenticationPrincipal CurrentUser currentUser) {
        if (albumService.getUserAlbums(currentUser.getUser().getEmail()).contains(albumService.getAlbumById(id))) {
            model.addAttribute("pass", new Pass());
            model.addAttribute("albumToOpen", albumService.getAlbumById(id));
            return "checkAlbumPass";
        }
        return "redirect:/index";
    }

    @PostMapping("/album/{id}")
    public String checkPassword(@PathVariable Long id, @Valid @ModelAttribute Pass pass, @AuthenticationPrincipal CurrentUser currentUser, BindingResult bindingResult) {
        if (albumService.getUserAlbums(currentUser.getUser().getEmail()).contains(albumService.getAlbumById(id))) {
            if (BCrypt.checkpw(pass.getPassword(), albumService.getAlbumById(id).getPassword())) {
                return "redirect:/album-photos/"+id;
            } else {
                bindingResult.rejectValue("password", "album.access.password.invalid", "Password is incorrect");
                return "checkAlbumPass";
            }
        }
        return "checkAlbumPass";
    }

    @GetMapping("/album-details/{id}")
    public String openAlbumDetails(Model model, @PathVariable Long id, @AuthenticationPrincipal CurrentUser currentUser) {
        if(albumService.getAdminsAlbums(currentUser.getUser().getId()).
                contains(albumService.getAlbumById(id)))    {
        model.addAttribute("album", albumService.getAlbumById(id));
        model.addAttribute("photos", photoService.getAlbumPhotos(id));
        return "albumDetails";
    }
        return"redirect:/index";
}

    @GetMapping("/create-album")
    public String createAlbum(Model model) {
        model.addAttribute("newAlbum", new Album());
        return "addAlbumForm";
    }

    @PostMapping("/create-album")
    public String proceedAlbumForm(@Valid @ModelAttribute Album album, BindingResult bindingResult, @AuthenticationPrincipal CurrentUser currentUser) {
        if (!bindingResult.hasErrors()) {
            album.setAdminId(currentUser.getUser().getId());
            albumService.save(album);
            return "redirect:/index";
        }
        return "addAlbumForm";
    }
    @GetMapping("/share-album/{id}")
    public String shareAlbum(Model model, @PathVariable Long id, @AuthenticationPrincipal CurrentUser currentUser) {
        if (albumService.getAdminsAlbums(currentUser.getUser().getId()).contains(albumService.getAlbumById(id))) {
            model.addAttribute("albumToShare", albumService.getAlbumById(id));
            return "shareAlbumForm";
        }
        return "index";
    }

    @PostMapping("/share-album/{id}")
    public String proceedShareAlbum(@Valid @ModelAttribute Album album, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            String pass = album.getPassword();
            albumService.saveAlbumPassword(album);
            albumService.share(album);
            albumService.sendEmailAboutSharing(album, pass);
            return "redirect:/index";
        }
        return "/index";
    }
    @GetMapping("/album-photos/{id}")
    public String showGallery(Model model, @PathVariable Long id, @AuthenticationPrincipal CurrentUser currentUser) {
        if (albumService.getUserAlbums(currentUser.getUser().getEmail()).contains(albumService.getAlbumById(id))) {
            model.addAttribute("photos", photoService.getAlbumPhotos(id));
            return "albumGallery";
        }
        return "redirect:/index";
    }
}
