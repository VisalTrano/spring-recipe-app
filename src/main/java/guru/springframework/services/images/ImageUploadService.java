package guru.springframework.services.images;

import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

public interface ImageUploadService {
    Mono<Void> saveImageFile(String recipe_id, MultipartFile file);
}
