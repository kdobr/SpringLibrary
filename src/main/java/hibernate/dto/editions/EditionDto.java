package hibernate.dto.editions;

import hibernate.dto.writers.WriterForListDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EditionDto {
    private int id;
    private String name;
    private List<WriterForListDto> bookList;
}
