package hibernate.dto.editions;

import hibernate.dto.writers.AuthorForListDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookDto {
    private int id;
    private String title;
    private List<AuthorForListDto> authorList;
}
