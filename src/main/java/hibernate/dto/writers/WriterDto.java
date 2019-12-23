package hibernate.dto.writers;

import hibernate.dto.editions.BookForListDto;
import hibernate.dto.editions.EditionForListDto;
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
