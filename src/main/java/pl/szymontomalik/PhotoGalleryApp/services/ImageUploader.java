package pl.szymontomalik.PhotoGalleryApp.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.szymontomalik.PhotoGalleryApp.entities.Photography;
import pl.szymontomalik.PhotoGalleryApp.repositories.PhotographyRepository;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class ImageUploader {
    private Cloudinary cloudinary;
    private final PhotographyRepository photoRepository;

    private final String cloudNameValue = "drqptc9ie";
    private final String apiKeyValue = "536853319472229";
    private final String apiSecretValue = "Wsp_-YYs5CFHjzLKD3e_GZLhm9I";

    public ImageUploader(PhotographyRepository photoRepository) {
        this.photoRepository = photoRepository;
        cloudinaryTokens();
    }

    public String uploadFile(String path) {
        File file = new File(path);
        Map uploadResult = null;
        try {
            uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        } catch (IOException e) {
            // todo
        }
        return uploadResult.get("url").toString();
    }

//    public void saveMetadata(Photography photography) {
//        photography.setUrl();
//        photography.setServerFileName();
//        photoRepository.save(photography);
//
//    }
    private void cloudinaryTokens() {
        cloudinary =new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudNameValue,
                "api_key",apiKeyValue,
                "api_secret",apiSecretValue));
    }
}