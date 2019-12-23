package hibernate.dto.editions;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookForListDto {
    private int id;
    private String title;
}
