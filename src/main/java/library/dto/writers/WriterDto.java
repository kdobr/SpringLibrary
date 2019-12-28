package library.dto.writers;

import library.dto.editions.EditionForListDto;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class WriterDto {
    private int id;
    private String name;
    private List<EditionForListDto> editionList;
}
