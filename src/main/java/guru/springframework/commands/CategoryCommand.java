package guru.springframework.commands;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryCommand {
    private String id;
    private String description;
}
