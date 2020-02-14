package guru.springframework.services.images;

import org.springframework.web.multipart.MultipartFile;

public interface ImageUploadService {
    void saveImageFile(Long recipe_id, MultipartFile file);
}
