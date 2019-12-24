package library.dto.writers;

import library.dto.editions.BookForListDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class AuthorDto {
    private int id;
    private String name;
    private List<BookForListDto> bookList;
}
