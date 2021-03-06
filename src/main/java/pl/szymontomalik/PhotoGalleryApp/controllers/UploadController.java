package pl.szymontomalik.PhotoGalleryApp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.szymontomalik.PhotoGalleryApp.entities.Photography;
import pl.szymontomalik.PhotoGalleryApp.models.CurrentUser;
import pl.szymontomalik.PhotoGalleryApp.services.AlbumService;
import pl.szymontomalik.PhotoGalleryApp.services.ImageUploader;
import pl.szymontomalik.PhotoGalleryApp.services.PhotographyService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/upload")
public class UploadController {
    private final AlbumService albumService;
    private final PhotographyService photographyService;
    private final ImageUploader imageUploader;

    @GetMapping("/photo/{id}")
    public String addPhotosForm(Model model, @PathVariable Long id) {
        List<Photography> photoList = new ArrayList<>();
        model.addAttribute("photoList", photoList);
        model.addAttribute("albumId", id);
        return "uploadPhotoForm";
    }

    @PostMapping("/photo/{id}")
    public String proceedAddPhoto(@RequestParam("files") MultipartFile[] files, @PathVariable Long id, @AuthenticationPrincipal CurrentUser currentUser) {
        if (albumService.getAdminsAlbums(currentUser.getUser().getId()).
                contains(albumService.getAlbumById(id))) {
            List<Photography> photosList = new ArrayList<>();

            String filePathToSave = "/tmp/filesToUpload/";
            File dirPath = new File(filePathToSave);
            if (!dirPath.exists()) {
                dirPath.mkdirs();
            }

            for (MultipartFile mf : files) {
                try {
                    mf.transferTo(new File(filePathToSave+"/"+mf.getOriginalFilename()));
                } catch (IllegalStateException | IOException e) {
                    // TODO
                    e.printStackTrace();
                }
                Photography photography = new Photography();
                photography.setAlbum(albumService.getAlbumById(id));
                photography.setUserFileName(filePathToSave+"/"+mf.getOriginalFilename());
                photography.setServerFileName(mf.getOriginalFilename());
                photography.setSize(mf.getSize());
                photography.setMimeType(mf.getContentType());
                photosList.add(photography);
            }
            for (Photography photo : photosList) {
                photo.setUrl(imageUploader.uploadFile(photo.getUserFileName()));
                photographyService.save(photo);
            }
            return "redirect:/index";
        }return "redirect:/index";
    }
}
