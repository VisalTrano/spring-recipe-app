package guru.springframework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = { "guru.springframework.repositories" })
public class SpringRecipeAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringRecipeAppApplication.class, args);
    }
}
