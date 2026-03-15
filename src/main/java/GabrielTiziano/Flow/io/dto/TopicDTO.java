package GabrielTiziano.Flow.io.dto;

import GabrielTiziano.Flow.io.model.enums.Category;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicDTO {
    private Long id;
    private String name;
    private String description;
    private Category category;
}
