package library.dto.writers;

import library.dto.editions.BookForListDto;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor


public class AuthorDto {
    private int id;
    private String name;
    private List<BookForListDto> bookList;
}
