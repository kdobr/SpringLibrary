package library.dto.writers;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AuthorForListDto {
    private int id;
    private String name;
}
