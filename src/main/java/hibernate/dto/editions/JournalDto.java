package hibernate.dto.editions;

import hibernate.dto.writers.AuthorForListDto;
import hibernate.dto.writers.ColumnistForListDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JournalDto {
    private int id;
    private String title;
    private List<ColumnistForListDto> authorList;
}
