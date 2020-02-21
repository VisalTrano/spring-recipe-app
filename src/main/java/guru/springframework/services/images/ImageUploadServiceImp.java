package guru.springframework.services.images;

import guru.springframework.domian.Recipe;
import guru.springframework.repositories.reactvice.RecipeReactiveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Slf4j
@Service
public class ImageUploadServiceImp implements ImageUploadService {
    private final RecipeReactiveRepository recipeReactiveRepository;

    public ImageUploadServiceImp(RecipeReactiveRepository recipeReactiveRepository) {
        this.recipeReactiveRepository = recipeReactiveRepository;
    }

    @Override
    public Mono<Void> saveImageFile(String recipe_id, MultipartFile file) {

        recipeReactiveRepository.findById(recipe_id)
                .map(recipe -> {
                    Byte[] bytesObjects = new Byte[0];
                    try {
                        bytesObjects = new Byte[file.getBytes().length];
                        int i = 0;
                        for (byte b : file.getBytes()) {
                            bytesObjects[i++] = b;
                        }
                        recipe.setImage(bytesObjects);
                        return recipe;
                    } catch (IOException e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
                }).publish(recipeMono -> recipeReactiveRepository.save(recipeMono.block()));
        return Mono.empty();
//        try {
//            Recipe recipe = recipeReactiveRepository.findById(recipe_id).block();
//            Byte[] byteObjects = new Byte[file.getBytes().length];
//            int i = 0;
//            for (byte b : file.getBytes()) {
//                byteObjects[i++] = b;
//            }
//            recipe.setImage(byteObjects);
//            recipeReactiveRepository.save(recipe).block();
//            return Mono.empty();
//        } catch (IOException e) {
//
//            return Mono.empty();
//        }
    }
}
