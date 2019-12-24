package library.dto.writers;

import library.dto.editions.EditionForListDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class WriterDto {
    private int id;
    private String name;
    private List<EditionForListDto> editionList;
}
